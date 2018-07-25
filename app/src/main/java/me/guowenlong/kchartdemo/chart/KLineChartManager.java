package me.guowenlong.kchartdemo.chart;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.ColorRes;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.guowenlong.kchartdemo.R;
import me.guowenlong.kchartdemo.Utils;
import me.guowenlong.kchartdemo.entity.KLineEntity.DataBean;

/**
 * K线图管理类
 *
 * @author guowenlong
 * 创建时间:2018-07-22-17:50
 */
public class KLineChartManager extends BaseChartManager implements IChart {
    public KLineChartManager(CombinedChart combineChart) {
        super(combineChart);
    }

    @Override
    public void setData(final List<DataBean> lists) {
        super.setData(lists);
        setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                int i = (int) value % mSize;
                @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
                if (i > mSize - 1) return "";
                return sdf.format(lists.get(i).getId() * 1000L);
            }
        });
        if (lists.size() > 20) {
            addLineData(getLineData(lists));
        } else {
            cleanAllData();
        }
        addCandleData(generateCandleData(getYData(lists)));
    }

    private LineData getLineData(List<DataBean> list) {
        LineData lineData = new LineData();
        lineData.addDataSet(getMALineDataSet(5, list, getColor(R.color.MA5), "ma5"));
        lineData.addDataSet(getMALineDataSet(10, list, getColor(R.color.MA10), "ma10"));
        lineData.addDataSet(getMALineDataSet(20, list, getColor(R.color.MA20), "ma20"));
        return lineData;
    }

    private int getColor(@ColorRes int id) {
        return mCombineChart.getContext().getResources().getColor(id);
    }

    private LineDataSet getMALineDataSet(int days, List<DataBean> list, int color, String label) {
        ArrayList<Float> floats = new ArrayList<>();
        for (DataBean b : list) floats.add(b.getClose());
        List<Entry> strings = Utils.countMA(floats, days);
        LineDataSet dataSet = new LineDataSet(strings, label);
        dataSet.setDrawCircles(false);
        dataSet.setDrawValues(false);
        dataSet.setColor(color);
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
        CandleDataSet set = new CandleDataSet(yVals, "涨跌");
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
    }
}