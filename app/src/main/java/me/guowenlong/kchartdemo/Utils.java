package me.guowenlong.kchartdemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.github.mikephil.charting.data.Entry;

import me.guowenlong.kchartdemo.entity.KLineEntity;

/**
 * 工具类
 *
 * @author guowenlong
 * 创建时间:2018-07-20-19:44
 */
public class Utils {
    /**
     * 获取MA线
     *
     * @param list 集合
     * @param days MA 几 日线
     */
    public static List<Entry> countMA(List<Float> list, int days) {
        if (days < 1 || list == null || list.size() == 0 || days > list.size()) {
            return null;
        }
        double sum = 0;
        List<Entry> maValues = new ArrayList<>();
        for (int i = days - 1; i < list.size(); i++) {
            if (i == days - 1) {
                for (int j = i - days + 1; j <= i; j++) {
                    sum += list.get(j);
                }
            } else {
                sum = sum + list.get(i)
                        - list.get(i - days);
            }
            maValues.add(new Entry(i, (float) sum / days));
        }
        return maValues;
    }

    /**
     * Calculate EMA,
     *
     * @param list :Price list to calculate，the first at head, the last at tail.
     */
    public static  Double getEXPMA(final List<Double> list, final int number) {
        // 开始计算EMA值，
        Double k = 2.0 / (number + 1.0);// 计算出序数
        Double ema = list.get(0);// 第一天ema等于当天收盘价
        for (int i = 1; i < list.size(); i++) {
            // 第二天以后，当天收盘 收盘价乘以系数再加上昨天EMA乘以系数-1
            ema = list.get(i) * k + ema * (1 - k);
        }
        return ema;
    }

    /**
     * calculate MACD values
     *
     * @param list::Price list to calculate，the first at head, the last at tail.
     * @param shortPeriod :the short period value.
     * @param longPeriod  :the long period value.
     * @param midPeriod   :the mid period value.
     */
    public static HashMap<String, Double> getMACD(final List<Double> list, final int shortPeriod, final int longPeriod, int midPeriod) {
        HashMap<String, Double> macdData = new HashMap<>();
        List<Double> diffList = new ArrayList<>();
        Double shortEMA = 0.0;
        Double longEMA = 0.0;
        Double dif = 0.0;
        Double dea = 0.0;

        for (int i = list.size() - 1; i >= 0; i--) {
            List<Double> sublist = list.subList(0, list.size() - i);
            shortEMA = Utils.getEXPMA(sublist, shortPeriod);
            longEMA = Utils.getEXPMA(sublist, longPeriod);
            dif = shortEMA - longEMA;
            diffList.add(dif);
        }
        dea = Utils.getEXPMA(diffList, midPeriod);
        macdData.put("DIF", dif);
        macdData.put("DEA", dea);
        macdData.put("MACD", (dif - dea) * 2);
        return macdData;
    }
}