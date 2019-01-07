package com.beksultan.e_trans;

import android.app.Application;
import android.content.SharedPreferences;

import com.beksultan.e_trans.network.ApiService;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.beksultan.e_trans.Constant.BASE_URL;
import static com.beksultan.e_trans.Constant.KEY;

public class App extends Application {

    public static App instance;

    private SharedPreferences preferences;

    public static Retrofit retrofit;

    public static ApiService apiService;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        preferences = getSharedPreferences("app", MODE_PRIVATE);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request compressedRequest = originalRequest.newBuilder()
                        .header("Authorization", preferences.getString(KEY, ""))
                        .method(originalRequest.method(), originalRequest.body())
                        .build();
                return chain.proceed(compressedRequest);
            }
        }).build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    public static App getInstance() { return instance; }

    public SharedPreferences getPreferences() {
        return preferences;
    }

    public ApiService getApiService() {
        return apiService;
    }
}
