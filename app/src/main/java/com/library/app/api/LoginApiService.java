package com.library.app.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.library.app.activity.LoginActivity;
import com.library.app.dto.UserInfoResponse;
import com.library.app.model.LoginRes;
import com.library.app.model.MessRegister;
import com.library.app.model.User;
import com.library.app.model.UserLogin;
import com.library.app.model.UserMD;

import java.util.Currency;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LoginApiService {
    Gson gson = new GsonBuilder()
            .setLenient().create();
    LoginApiService loginApiService = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8081/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(LoginApiService.class);

    @POST("lib/v1/auth/login")
    Call<LoginRes> login(@Body UserLogin userLogin);
    @POST("lib/v1/register")
    Call<MessRegister> register(@Body UserMD userMD);

}
