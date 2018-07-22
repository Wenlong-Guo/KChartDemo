package me.guowenlong.kchartdemo;

import java.util.ArrayList;
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
     * @param list 集合
     * @param days MA 几 日线
     */
    public static List<Entry> countMA(List<KLineEntity.DataBean> list, int days) {
        if (days < 1||list == null || list.size() == 0||days>list.size()) {
            return null;
        }
        double sum = 0;
        List<Entry> maValues = new ArrayList<>();
        for (int i = days - 1; i < list.size(); i++) {
            if (i == days - 1) {
                for (int j = i - days + 1; j <= i; j++) {
                    sum += list.get(j).getClose();
                }
            } else {
                sum = sum + list.get(i).getClose()
                        - list.get(i - days).getClose();
            }
            maValues.add(new Entry(i,(float) sum / days));
        }
        return maValues;
    }
}
