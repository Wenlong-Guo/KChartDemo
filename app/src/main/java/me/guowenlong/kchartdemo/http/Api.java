package me.guowenlong.kchartdemo.http;

import me.guowenlong.kchartdemo.entity.KLineEntity;
import me.guowenlong.kchartdemo.entity.SymbolsEntity;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 类的描述
 *
 * @author guowenlong
 * 创建时间:2018-07-18-0:11
 */
public interface Api {
    // 获取支持币种
    @GET("v1/common/currencys")
    Observable<SymbolsEntity> getSymbols(@Query("ACCESS_KEY_ID") String accessKeyId);

    // 获取某币种的K线图
    @GET("market/history/kline")
    Observable<KLineEntity> getKLine(@Query("symbol") String symbol, @Query("period") String period, @Query("size") int size, @Query("ACCESS_KEY_ID") String accessKeyId);
}
