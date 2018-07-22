package me.guowenlong.kchartdemo.chart;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.LineData;

import java.util.List;

import me.guowenlong.kchartdemo.entity.KLineEntity;

/**
 * 类的描述
 *
 * @author guowenlong
 * 创建时间:2018-07-22-21:22
 */
public interface IChart {
     void initChart();
     void initChartSetting();
     void showChart();
     void addLineData(LineData lineData);
     void addCandleData(CandleData candleData);
     void addBarData(BarData barData);
     void setData(List<KLineEntity.DataBean> lists);
}
