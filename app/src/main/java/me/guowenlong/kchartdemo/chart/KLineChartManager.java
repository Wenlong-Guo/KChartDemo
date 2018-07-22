package me.guowenlong.kchartdemo.chart;

import android.graphics.Color;
import android.graphics.Paint;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

import me.guowenlong.kchartdemo.Utils;
import me.guowenlong.kchartdemo.entity.KLineEntity;
import me.guowenlong.kchartdemo.entity.KLineEntity.DataBean;

/**
 * K线图管理类
 *
 * @author guowenlong
 * 创建时间:2018-07-22-17:50
 */
public class KLineChartManager extends BaseChartManager implements IChart {
    public KLineChartManager(CombinedChart combineChart ) {
        super(combineChart);
    }

    @Override
    public void setData(List<DataBean> lists) {
        super.setData(lists);
        if(lists.size()>20) {
            addLineData(getLineData(lists));
        }else{
            cleanAllData();
        }
        addCandleData(generateCandleData(getYData(lists)));
    }

    private LineData getLineData(List<DataBean> list) {
        LineData lineData = new LineData();
        lineData.addDataSet(getLineDataSet(5, list));
        lineData.addDataSet(getLineDataSet(10, list));
        lineData.addDataSet(getLineDataSet(20, list));
        return lineData;
    }

    private LineDataSet getLineDataSet(int days, List<DataBean> list) {
        List<Entry> strings = Utils.countMA(list, days);
        LineDataSet dataSet = new LineDataSet(strings, "");
        dataSet.setDrawCircles(false);
        dataSet.setDrawValues(false);
        dataSet.setColor(Color.BLUE);
        dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        return dataSet;
    }

    private List<CandleEntry> getYData(List<DataBean> list) {
        List<CandleEntry> mEntries = new ArrayList<>();
        mEntries.clear();
        for (int i = 0, j = list.size(); i < j; i++) {
            DataBean bean = list.get(i);
            CandleEntry candleEntry = new CandleEntry(i, bean.getHigh(), bean.getLow(), bean.getClose(), bean.getOpen());
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
        return new CandleData(set);
    }

    @Override
    void initLabels() {
//        Legend l = mCombineChart.getLegend();  // 设置比例图标示
//        l.setPosition(Legend.LegendPosition.BELOW_CHART_RIGHT);  //显示位置
//        l.setForm(Legend.LegendForm.SQUARE);// 样式
//        l.setFormSize(6f);// 字号
//        l.setTextColor(Color.WHITE);// 颜色
//
//        List<String> labels = new ArrayList<>();
//        labels.add("红涨");
//        labels.add("绿跌");
//        List<Integer> colors = new ArrayList<>();
//        colors.add(Color.RED);
//        colors.add(Color.GREEN);
//        l.setExtra(colors, labels);//设置标注的颜色及内容，设置的效果如下图
//
//        l.setEnabled(true);//决定显不显示标签
    }
}