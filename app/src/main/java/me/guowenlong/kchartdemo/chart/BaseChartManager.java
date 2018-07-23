package me.guowenlong.kchartdemo.chart;


import android.graphics.Color;

import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.List;

import me.guowenlong.kchartdemo.entity.KLineEntity;

/**
 * 类的描述
 *
 * @author guowenlong
 * 创建时间:2018-07-22-21:27
 */
public abstract class BaseChartManager implements IChart {
    CombinedChart mCombineChart;
    YAxis mLeftYAxis;
    private YAxis mRightYAxis;
    XAxis mXAxis;
    Legend mLegend;
    private CombinedData mCombinedData;
    int mSize;

    BaseChartManager(CombinedChart combineChart) {
        mCombineChart = combineChart;
        mLeftYAxis = mCombineChart.getAxisLeft();
        mRightYAxis = mCombineChart.getAxisRight();
        mXAxis = mCombineChart.getXAxis();
        mLegend = mCombineChart.getLegend();
        mCombinedData = new CombinedData();
        initChart();
    }

    private void initChart() {
        initChartSetting();
        initLeftY();
        initRightY();
        initX();
        initLabels();
    }
    public void initChartSetting() {
        mCombineChart.setDragDecelerationEnabled(false);
        mCombineChart.setDoubleTapToZoomEnabled(false);
        mCombineChart.getDescription().setEnabled(false);
        mCombineChart.setDrawGridBackground(false); // 是否显示表格颜色
        mCombineChart.setTouchEnabled(true); // enable touch gestures
        mCombineChart.setDragEnabled(true);// 是否可以拖拽
        mCombineChart.setScaleEnabled(true);// 是否可以缩放
        mCombineChart.setPinchZoom(false);// if disabled, scaling can be done on x- and y-axis separately
        mCombineChart.setScaleYEnabled(false);// if disabled, scaling can be done on x-axis
        mCombineChart.setScaleXEnabled(true);// if disabled, scaling can be done on  y-axis
        mCombineChart.setDrawBorders(false);
        mCombineChart.setAutoScaleMinMaxEnabled(true);
        mCombineChart.setVisibleXRangeMinimum(2);
        mCombineChart.setBackgroundColor(Color.WHITE);// 设置背景
        mCombineChart.setGridBackgroundColor(Color.GRAY);//设置表格背景色
        mCombineChart.animateX(2000); // 立即执行的动画,x轴
    }

    void initLeftY() {
        mLeftYAxis.setEnabled(true);
        mLeftYAxis.setLabelCount(4, false);
        mLeftYAxis.setDrawGridLines(true);
        mLeftYAxis.setDrawAxisLine(false);
        mLeftYAxis.setGridColor(Color.GRAY);
        mLeftYAxis.setTextColor(Color.BLACK);
        mLeftYAxis.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
    }

    private void initRightY() {
        mRightYAxis.setEnabled(false);
    }

    void initX() {
        mXAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        mXAxis.setDrawGridLines(false);
        mXAxis.setGridColor(Color.BLUE);//X轴刻度线颜色
        mXAxis.setTextColor(Color.BLACK);//X轴文字颜色
        mXAxis.setLabelCount(3);
        mXAxis.setAvoidFirstLastClipping(true);
    }

    abstract void initLabels();

    @Override
    public void bindChart(Chart[] charts) {
        mCombineChart.setOnChartGestureListener(new CoupleChartGestureListener(mCombineChart, charts));
    }

    @Override
    public void setData(List<KLineEntity.DataBean> lists) {
        mSize = null == lists ? 0 : lists.size();
    }

    @Override
    public void showChart() {
        mCombineChart.clear();
        mCombineChart.setData(mCombinedData);
        mXAxis.setAxisMinimum(-0.5f);
        mXAxis.setAxisMaximum(mSize - 0.5f);
        mCombineChart.setVisibleXRangeMinimum(3);
        mCombineChart.zoom(0f,0f,0,0);
        mCombineChart.zoom(mSize < 20 ? 0f : 15f, 0f, 0, 0);
        mCombineChart.moveViewToX(mSize);
        mCombineChart.invalidate();
    }

    void setValueFormatter(IAxisValueFormatter iAxisValueFormatter) {
        mXAxis.setValueFormatter(iAxisValueFormatter);
    }

    void addLineData(LineData lineData) {
        mCombinedData.setData(lineData);
    }

    void addCandleData(CandleData candleData) {
        mCombinedData.setData(candleData);
    }

    void addBarData(BarData barData) {
        mCombinedData.setData(barData);
    }

    void cleanAllData() {
        mCombinedData = new CombinedData();
    }
}