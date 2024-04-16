package com.library.app.api;

import com.google.gson.Gson;
import com.library.app.common.NetworkTaskExecutor;
import com.library.app.dto.BookTrainingResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HandlerBookTraining {

    public static List<BookTrainingResponse> getAllBookTraining() {
        List<BookTrainingResponse> result = new ArrayList<>();
        NetworkTaskExecutor.execute(() -> {
            Gson gson = new Gson();
            try {
                OkHttpClient client = new OkHttpClient().newBuilder()
                        .connectTimeout(5, TimeUnit.SECONDS)
                        .build();
                Request request = new Request.Builder()
                        .url("https://138f-14-160-24-223.ngrok-free.app/api/book/training/findAll")
                        .method("GET", null)
                        .addHeader("Cookie", "JSESSIONID=EE1637F3E67E007413996CEFA4CFDF31")
                        .build();
                Response response = client.newCall(request).execute();
                if (response.code() == 200) {
                    assert response.body() != null;
                    String res = response.body().string();
                    BookTrainingResponse[] arr = gson.fromJson(res, BookTrainingResponse[].class);
                    result.addAll(Arrays.asList(arr));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        System.out.println("total book = " + result.size());
        return result;
    }
}
