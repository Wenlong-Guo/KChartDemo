package me.guowenlong.kchartdemo.chart;

import android.graphics.Color;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

import me.guowenlong.kchartdemo.entity.KLineEntity;
import me.guowenlong.kchartdemo.entity.KLineEntity.DataBean;

/**
 * 成交量管理类
 *
 * @author guowenlong
 * 创建时间:2018-07-22-17:50
 */
public class VolChartManager extends BaseChartManager implements IChart {
    public VolChartManager(CombinedChart combineChart ) {
        super(combineChart );
    }

    @Override
    public void initChartSetting() {
        super.initChartSetting();
        mCombineChart.setExtraOffsets(-50, 0, -50, 0);
    }

    @Override
    public void setData(List<DataBean> lists) {
        super.setData(lists);
        addBarData(getBarData(lists));
    }

    @Override
    void initLabels() {

    }

    private BarData getBarData(List<DataBean> beans) {
        BarData barData = new BarData();
        ArrayList<BarEntry> yRedValues = new ArrayList<>();
        ArrayList<BarEntry> yGreenValues = new ArrayList<>();
        for (int i = 0; i < beans.size(); i++) {
            if (beans.get(i).getClose() > beans.get(i).getOpen()) {
                yRedValues.add(new BarEntry(i, (float) beans.get(i).getVol()));
            } else {
                yGreenValues.add(new BarEntry(i, (float) beans.get(i).getVol()));
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
        return barData;
    }
}