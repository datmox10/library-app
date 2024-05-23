package com.library.app.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import com.library.app.dto.RoomsInDateResponse;
import com.library.app.model.Room;

import java.io.IOException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import kotlin.ParameterName;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class ApiRoomClass {
    private static final String BASE_URL = "https://managelibrarybackend-e16fdf1245d3.herokuapp.com/";
    private String token;
    public ApiRoomClass(String token) {
        this.token = token;
    }
    Gson gson = new GsonBuilder()
            .setLenient().create();

    public interface ApiRoom{
        @GET("api/booking/room/getRoom")
        Call<List<Room>> getRoomInDate(@Query("date") Date date);
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
