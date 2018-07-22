package me.guowenlong.kchartdemo.ui.kline;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;

import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.guowenlong.kchartdemo.App;
import me.guowenlong.kchartdemo.C;
import me.guowenlong.kchartdemo.R;
import me.guowenlong.kchartdemo.Utils;
import me.guowenlong.kchartdemo.entity.KLineEntity;
import me.guowenlong.kchartdemo.http.RxSchedulers;
import rx.Observable;
import rx.Observer;

/**
 * K线图页面
 *
 * @author guowenlong
 * 创建时间:2018-07-18-2:43
 * todo MACD线
 * todo MarkerView
 */
public class KLineActivity extends AppCompatActivity implements View.OnClickListener {
    public static String period = "5min";
    public static final int size = 300;
    private CombinedChart mChart;
    private CombinedChart mChartBottom;
    private KLineEntity mKLine;
    private List<CandleEntry> mEntries = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kline);
        initView();
        mChart = findViewById(R.id.candler_chart);
        mChartBottom = findViewById(R.id.candler_chart_bottom);
        initChart();
        initX();
        initY();
        initLabels();

        initChartBottom();
    }

    private void initChartBottom() {
        mChartBottom.setDoubleTapToZoomEnabled(false);
        mChartBottom.setDrawBarShadow(false);
        mChartBottom.setHighlightFullBarEnabled(false);
        mChartBottom.setDrawBorders(true);
        mChartBottom.setDrawGridBackground(false); // 是否显示表格颜色
        mChartBottom.setBackgroundColor(Color.BLACK);// 设置背景
        mChartBottom.setGridBackgroundColor(Color.GRAY);//设置表格背景色
        mChartBottom.getDescription().setEnabled(false);
        mChartBottom.setTouchEnabled(true); // enable touch gestures
        mChartBottom.setDragEnabled(true);// 是否可以拖拽
        mChartBottom.setScaleEnabled(true);// 是否可以缩放
        mChartBottom.setPinchZoom(false);// if disabled, scaling can be done on x- and y-axis separately
        mChartBottom.setScaleYEnabled(false);// if disabled, scaling can be done on x-axis
        mChartBottom.setScaleXEnabled(true);// if disabled, scaling can be done on  y-axis
        mChartBottom.zoom(15f, 1f, 0, 0);
        mChartBottom.animateX(2000); // 立即执行的动画,x轴
        mChartBottom.setExtraOffsets(-50,0,-50,0);
        mChartBottom.setOnChartGestureListener(new CoupleChartGestureListener(mChartBottom,new Chart[]{mChart}));
        XAxis xAxis = mChartBottom.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(true);
        xAxis.setGridColor(Color.BLUE);//X轴刻度线颜色
        xAxis.setTextColor(Color.GREEN);//X轴文字颜色
        xAxis.setLabelCount(5);
        xAxis.setAvoidFirstLastClipping(true);

        YAxis leftAxis = mChartBottom.getAxisLeft();
        leftAxis.setEnabled(true);
        leftAxis.setLabelCount(2, false);
        leftAxis.setDrawGridLines(true);
        leftAxis.setDrawAxisLine(false);
        leftAxis.setGridColor(Color.BLUE);
        leftAxis.setTextColor(Color.GREEN);

        leftAxis.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setEnabled(false);
    }
    private BarData getBarData(List<KLineEntity.DataBean> beans) {
        BarData barData = new BarData();
        ArrayList<BarEntry> yRedValues = new ArrayList<>();
        ArrayList<BarEntry> yGreenValues = new ArrayList<>();
        for (int i = 0; i < beans.size(); i++) {
            if(beans.get(i).getClose()>beans.get(i).getOpen()){
                yRedValues.add(new BarEntry(i,(float)beans.get(i).getVol()));
            }else{
                yGreenValues.add(new BarEntry(i,(float)beans.get(i).getVol()));
            }
        }

        BarDataSet greenSet = new BarDataSet(yGreenValues, "");
        BarDataSet redSet = new BarDataSet(yRedValues, "");
        greenSet.setColor(Color.GREEN);
        redSet.setColor(Color.RED);
        greenSet.setValueTextSize(10f);
        redSet.setValueTextSize(10f);
        greenSet.setValueTextColor(Color.TRANSPARENT);
        redSet.setValueTextColor(Color.TRANSPARENT);
        greenSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        redSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        barData.addDataSet(greenSet);
        barData.addDataSet(redSet);

        //以下是为了解决 柱状图 左右两边只显示了一半的问题 根据实际情况 而定
        XAxis xAxis = mChartBottom.getXAxis();
        xAxis.setAxisMinimum(-0.5f);
        xAxis.setAxisMaximum((float) (beans.size()- 0.5));
        return barData;
    }

    private void initChart() {
        mChart.setDoubleTapToZoomEnabled(false);
        mChart.setDrawGridBackground(false); // 是否显示表格颜色
        mChart.setBackgroundColor(Color.BLACK);// 设置背景
        mChart.setGridBackgroundColor(Color.GRAY);//设置表格背景色
        mChart.getDescription().setEnabled(false);
        mChart.setTouchEnabled(true); // enable touch gestures
        mChart.setDragEnabled(true);// 是否可以拖拽
        mChart.setScaleEnabled(true);// 是否可以缩放
        mChart.setPinchZoom(false);// if disabled, scaling can be done on x- and y-axis separately
        mChart.setScaleYEnabled(false);// if disabled, scaling can be done on x-axis
        mChart.setScaleXEnabled(true);// if disabled, scaling can be done on  y-axis
        mChart.zoom(15f, 1f, 0, 0);
        mChart.setDrawBorders(true);
        mChart.setOnChartGestureListener(new CoupleChartGestureListener(mChart,new Chart[]{mChartBottom}));
    }

    private void initX() {
        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(true);
        xAxis.setGridColor(Color.BLUE);//X轴刻度线颜色
        xAxis.setTextColor(Color.GREEN);//X轴文字颜色
        xAxis.setLabelCount(5);
        xAxis.setAvoidFirstLastClipping(true);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                int i = (int) value % mKLine.getData().size();
                @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                if (i > mKLine.getData().size() - 1) return "";
                return sdf.format(mKLine.getData().get(i).getId() * 1000L);
            }
        });
    }

    private void initY() {
        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setEnabled(true);
        leftAxis.setLabelCount(7, false);
        leftAxis.setDrawGridLines(true);
        leftAxis.setDrawAxisLine(false);
        leftAxis.setGridColor(Color.BLUE);
        leftAxis.setTextColor(Color.GREEN);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);

        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setEnabled(false);
    }

    private void initLabels() {
        Legend l = mChart.getLegend();  // 设置比例图标示
        l.setPosition(Legend.LegendPosition.BELOW_CHART_RIGHT);  //显示位置
        l.setForm(Legend.LegendForm.SQUARE);// 样式
        l.setFormSize(6f);// 字号
        l.setTextColor(Color.WHITE);// 颜色

        List<String> labels = new ArrayList<>();
        labels.add("红涨");
        labels.add("绿跌");
        List<Integer> colors = new ArrayList<>();
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        l.setExtra(colors, labels);//设置标注的颜色及内容，设置的效果如下图

        l.setEnabled(true);//决定显不显示标签
    }

    @Override
    protected void onResume() {
        super.onResume();
        getKLine(getIntent().getStringExtra("symbol"));
    }

    /**
     * 获取某币种的K线图
     */
    private void getKLine(String symbol) {
        Observable<KLineEntity> kLines = App.getApi().getKLine(symbol, period, size, C.ACCESS_KEY_ID).compose(RxSchedulers.<KLineEntity>io_main());
        kLines.subscribe(new Observer<KLineEntity>() {
            @Override
            public void onNext(KLineEntity kLine) {
                if (kLine != null && TextUtils.equals(kLine.getStatus(), C.STATUS_OK) && kLine.getData() != null) {
                    Collections.reverse(kLine.getData());
                    mKLine = kLine;
                    mChart.clear();
                    CombinedData combinedData = new CombinedData();

                    if (kLine.getData().size() > 20) {
                        LineData lineData = getLineData(kLine.getData());
                        combinedData.setData(lineData);
                    }
                    combinedData.setData(generateCandleData(getYData(kLine)));
                    mChart.setData(combinedData);//当前屏幕会显示所有的数据
                    mChart.setAutoScaleMinMaxEnabled(true);
                    mChart.getXAxis().setAxisMinimum(-0.5f);
                    mChart.getXAxis().setAxisMaximum(mKLine.getData().size()-0.5f);
                    mChart.setVisibleXRangeMinimum(1);
                    mChart.moveViewToX(mKLine.getData().size());
                    mChart.invalidate();

                    CombinedData combinedDataBottom = new CombinedData();
                    combinedDataBottom.setData(getBarData(mKLine.getData()));
                    mChartBottom.setData(combinedDataBottom);
                    mChartBottom.moveViewToX(mKLine.getData().size());
                    mChartBottom.setAutoScaleMinMaxEnabled(true);

                    mChartBottom.invalidate();                }
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    private List<CandleEntry> getYData(KLineEntity kLine) {
        mEntries.clear();
        for (int i = 0, j = kLine.getData().size(); i < j; i++) {
            KLineEntity.DataBean bean = kLine.getData().get(i);
            CandleEntry candleEntry = new CandleEntry(i, bean.getHigh(), bean.getLow(), bean.getClose(), bean.getOpen(), i + "1000");
            mEntries.add(candleEntry);
        }
        return mEntries;
    }

    private CandleData generateCandleData(List<CandleEntry> yVals) {
        CandleDataSet set = new CandleDataSet(yVals, "");
        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        set.setShadowWidth(0.7f);
        set.setDecreasingColor(Color.RED);
        set.setDecreasingPaintStyle(Paint.Style.FILL);
        set.setIncreasingColor(Color.GREEN);
        set.setIncreasingPaintStyle(Paint.Style.STROKE);
        set.setNeutralColor(Color.RED);
        set.setShadowColorSameAsCandle(true);
        set.setHighlightLineWidth(0.5f);
        set.setHighLightColor(Color.WHITE);
        set.setDrawValues(false);
//
        return new CandleData(set);
    }

    private LineData getLineData(List<KLineEntity.DataBean> list) {
        LineData lineData = new LineData();
        lineData.addDataSet(getLineData(5, list));
        lineData.addDataSet(getLineData(10, list));
        lineData.addDataSet(getLineData(20, list));
        return lineData;
    }
    private LineDataSet getLineData(int days, List<KLineEntity.DataBean> list) {
        List<Entry> strings = Utils.countMA(list, days);
        LineDataSet dataSet = new LineDataSet(strings, "");
        dataSet.setDrawCircles(false);
        dataSet.setColor(Color.BLUE);
        dataSet.setValueTextSize(10f);
        dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        return dataSet;
    }

    private void initView() {
        findViewById(R.id.rb_5min).setOnClickListener(this);
        findViewById(R.id.rb_30min).setOnClickListener(this);
        findViewById(R.id.rb_60min).setOnClickListener(this);
        findViewById(R.id.rb_1day).setOnClickListener(this);
        findViewById(R.id.rb_1mon).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.rb_5min:
                period = "5min";
                break;
            case R.id.rb_30min:
                period = "30min";
                break;
            case R.id.rb_60min:
                period = "60min";
                break;
            case R.id.rb_1day:
                period = "1day";
                break;
            case R.id.rb_1mon:
                period = "1mon";
                break;
        }
        getKLine(getIntent().getStringExtra("symbol"));
    }
}