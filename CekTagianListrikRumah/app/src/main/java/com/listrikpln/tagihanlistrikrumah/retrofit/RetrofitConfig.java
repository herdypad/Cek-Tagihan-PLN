package com.listrikpln.tagihanlistrikrumah.retrofit;



import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    public static ApiService apiService(){
        OkHttpClient client = new OkHttpClient();
        client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request requestWithUserAgent = originalRequest.newBuilder().header("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36").build();
                return  chain.proceed(requestWithUserAgent);
            }
        }).build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("https://www.bukalapak.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);
        return service;
    }

    public static ApiService apiService2(){
        OkHttpClient client = new OkHttpClient();
        client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request requestWithUserAgent = originalRequest.newBuilder().header("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36").build();
                return  chain.proceed(requestWithUserAgent);
            }
        }).build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("https://api.bukalapak.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);
        return service;
    }

    public static ApiService cekConfig(){
        OkHttpClient client = new OkHttpClient();
        client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request requestWithUserAgent = originalRequest.newBuilder().header("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36").build();
                return  chain.proceed(requestWithUserAgent);
            }
        }).build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("https://raw.githubusercontent.com/herdypad/JsonConfig/main/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);
        return service;
    }

}
