package com.library.app.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.library.app.dto.RoomInDateResponse;
import com.library.app.model.LoginRes;
import com.library.app.model.MessRegister;
import com.library.app.model.Room;
import com.library.app.model.UserLogin;
import com.library.app.model.UserMD;

import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiRoom {
    Gson gson = new GsonBuilder()
            .setLenient().create();
    ApiRoom apiRoom = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8081/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiRoom.class);

    @POST("api/booking/room/getRoom")
    Call<ArrayList<Room>> getRoomInDate(@Body Date date);

}
