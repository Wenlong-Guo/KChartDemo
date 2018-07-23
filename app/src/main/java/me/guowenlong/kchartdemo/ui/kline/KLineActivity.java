package me.guowenlong.kchartdemo.ui.kline;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;

import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.CombinedChart;
import java.util.Collections;

import me.guowenlong.kchartdemo.App;
import me.guowenlong.kchartdemo.C;
import me.guowenlong.kchartdemo.R;
import me.guowenlong.kchartdemo.chart.KLineChartManager;
import me.guowenlong.kchartdemo.chart.VolChartManager;
import me.guowenlong.kchartdemo.entity.KLineEntity;
import me.guowenlong.kchartdemo.http.RxSchedulers;
import me.guowenlong.kchartdemo.ui.main.MainActivity;
import rx.Observable;
import rx.Observer;

/**
 * K线图页面
 *
 * @author guowenlong
 * 创建时间:2018-07-18-2:43
 * todo MACD线 EMA线
 * todo MarkerView 展示数据
 */
public class KLineActivity extends AppCompatActivity implements View.OnClickListener {
    public static String period = C.TAB_TIME_DEFAULT;
    private CombinedChart mChart;
    private CombinedChart mChartBottom;
    private KLineChartManager mKLineChartManager;
    private VolChartManager mVolChartManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kline);
        initView();
        initKLineChart();
        initChartBottom();
    }

    /**
     * 初始化视图
     */
    private void initView() {
        findViewById(R.id.rb_5min).setOnClickListener(this);
        findViewById(R.id.rb_30min).setOnClickListener(this);
        findViewById(R.id.rb_60min).setOnClickListener(this);
        findViewById(R.id.rb_1day).setOnClickListener(this);
        findViewById(R.id.rb_1mon).setOnClickListener(this);
        mChart = findViewById(R.id.candler_chart);
        mChartBottom = findViewById(R.id.candler_chart_bottom);
    }

    private void initKLineChart() {
        mKLineChartManager = new KLineChartManager(mChart);
        mKLineChartManager.bindChart(new Chart[]{mChartBottom});
    }

    private void initChartBottom() {
        mVolChartManager = new VolChartManager(mChartBottom);
        mVolChartManager.bindChart(new Chart[]{mChart});
    }

    @Override
    protected void onResume() {
        super.onResume();
        getKLine(getIntent().getStringExtra(MainActivity.INTENT_SYMBOL));
    }

    /**
     * 获取某币种的K线图
     */
    private void getKLine(String symbol) {
        Observable<KLineEntity> kLines = App.getApi().getKLine(symbol, period, C.ITEM_DEFAULT_SIZE, C.ACCESS_KEY_ID).compose(RxSchedulers.<KLineEntity>io_main());
        kLines.subscribe(new Observer<KLineEntity>() {
            @Override
            public void onNext(KLineEntity kLine) {
                if (kLine != null && TextUtils.equals(kLine.getStatus(), C.STATUS_OK) && kLine.getData() != null) {
                    Collections.reverse(kLine.getData());

                    mKLineChartManager.setData(kLine.getData());
                    mKLineChartManager.showChart();

                    mVolChartManager.setData(kLine.getData());
                    mVolChartManager.showChart();
                }
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.rb_5min:
                period = C.TAB_TIME_5_MIN;
                break;
            case R.id.rb_30min:
                period = C.TAB_TIME_30_MIN;
                break;
            case R.id.rb_60min:
                period = C.TAB_TIME_60_MIN;
                break;
            case R.id.rb_1day:
                period = C.TAB_TIME_1_DAY;
                break;
            case R.id.rb_1mon:
                period = C.TAB_TIME_1_MON;
                break;
        }
        getKLine(getIntent().getStringExtra(MainActivity.INTENT_SYMBOL));
    }
}