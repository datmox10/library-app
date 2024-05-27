package com.library.app.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.library.app.dto.RoomBookingRequest;
import com.library.app.dto.RoomBookingResponse;
import com.library.app.dto.UserInfoResponse;
import com.library.app.model.Room;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public class ApiUser {
    private static final String BASE_URL = "http://10.0.2.2:8081/";
    private String token;
    public ApiUser(String token) {
        this.token = token;
    }
    public interface ApiUserInterface{
        @GET("lib/v1/auth/user")
        Call<UserInfoResponse> getUser();

    }
    public Retrofit getRetrofitInstance() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request request = original.newBuilder()
                        .header("Authorization", "Bearer " + token)
                        .method(original.method(), original.body())
                        .build();
                return chain.proceed(request);
            }
        });

        OkHttpClient client = httpClient.build();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }
}
