package com.yun.ma.yi.app.api;


import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yun.ma.yi.app.api.util.LoggerInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 使用Retrofit获取信息
 * Created by ys on 17/5/27.
 */
public class ApiManager {

    /** 默认超时时间 单位/秒*/
    private static  int DEFAULT_TIME_OUT = 30;
    private static BaseHttpService apiManagerService;
    private static OkHttpClient okHttpClient;
    private static Gson gson;
    public static BaseHttpService getApiManager() {
        if (apiManagerService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(buildGson()))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(getOKHttpClient(false))
                    .baseUrl(Config.SERVER_ROOT)
                    .build();
            apiManagerService = retrofit.create(BaseHttpService.class);
        }

        return apiManagerService;
    }

    public static BaseHttpService getApiProgressManager() {
        if (apiManagerService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(buildGson()))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(getOKHttpClient(true))
                    .baseUrl(Config.SERVER_ROOT)
                    .build();
            apiManagerService = retrofit.create(BaseHttpService.class);
        }

        return apiManagerService;
    }
    public static OkHttpClient getOKHttpClient(boolean isProgress){
        //日志显示级别
        HttpLoggingInterceptor.Level level= HttpLoggingInterceptor.Level.BODY;
        //新建log拦截器
        HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i("zcb","OkHttp====Message:"+message);
            }
        });
        loggingInterceptor.setLevel(level);
        //打印日志
        LoggerInterceptor interceptor = new LoggerInterceptor("okHttp");

        if(okHttpClient == null){
            synchronized (BaseHttpService.class){
                if(okHttpClient == null){
                OkHttpClient.Builder builder = new OkHttpClient.Builder();
                    if (isProgress){
                        DEFAULT_TIME_OUT = 60*5;
                    }
                    builder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);
                    builder.readTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);
                    builder.writeTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);
                    builder.addInterceptor(loggingInterceptor);
                    okHttpClient =builder.build();
                }
            }
        }
        return okHttpClient;
    }
    public static Gson buildGson() {
        if (gson == null) {
            gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd HH:mm:ss")
                    .registerTypeAdapter(Integer.class, new IntegerDefault0Adapter())
                    .registerTypeAdapter(int.class, new IntegerDefault0Adapter())
                    .create();
        }
        return gson;
    }
}
