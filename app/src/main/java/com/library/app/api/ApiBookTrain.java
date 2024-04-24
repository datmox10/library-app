package com.library.app.api;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiBookTrain {
    public static final String baseUrl = "http://localhost:8080/";

    ApiBookTraining apiBookTraining = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiBookTraining.class);
}
