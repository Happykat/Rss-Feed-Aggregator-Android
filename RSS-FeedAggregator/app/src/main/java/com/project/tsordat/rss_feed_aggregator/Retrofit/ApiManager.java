package com.project.tsordat.rss_feed_aggregator.Retrofit;

import com.project.tsordat.rss_feed_aggregator.Interface.WebServerIntf;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class ApiManager {
    public static final String BASE_URL = "http://79.137.78.39:4242/";

    private static WebServerIntf restClient;
    private static Retrofit retrofit;

    private ApiManager() {
    }

    public static Retrofit getRetrofit() {
        return (retrofit);
    }

    public static WebServerIntf getWebServerIntf() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        restClient = retrofit.create(WebServerIntf.class);
        return restClient;
    }
}
