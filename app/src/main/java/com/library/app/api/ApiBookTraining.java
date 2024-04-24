package com.library.app.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.library.app.dto.BookTrainingResponse;
import com.library.app.model.MessParamPost;
import com.library.app.model.MessResponse;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiBookTraining {
    public static final String baseUrl = "http://127.0.0.1:8081/";

    ApiBookTraining apiBookTraining = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiBookTraining.class);

    @GET("api/book/training/findAll")
    Call<List<BookTrainingResponse>> bookTraningReq();
}
