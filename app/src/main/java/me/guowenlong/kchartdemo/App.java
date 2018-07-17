package me.guowenlong.kchartdemo;

import android.app.Application;

import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 类的描述
 *
 * @author guowenlong
 * 创建时间:2018-07-17-23:59
 */
public class App extends Application {
    private static Api api;

    private static String url = "https://api.hadax.com/market";

    //后来的api
    public static synchronized Api getApi() {
        if (null == api) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder okBuilder = new OkHttpClient.Builder()
                    .addInterceptor(httpLoggingInterceptor);
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okBuilder.build())
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl(url)
                    .build();
            api = retrofit.create(Api.class);
        }
        return api;
    }
}
