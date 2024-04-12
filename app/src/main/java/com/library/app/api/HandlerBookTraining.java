package com.library.app.api;

import android.util.JsonReader;
import android.util.Log;

import com.google.gson.Gson;
import com.library.app.dto.BookTrainingResponse;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import  java.util.List;

public class HandlerBookTraining {

        public static List<BookTrainingResponse> getAllBookTraining() {
        Gson gson = new Gson();
        List<BookTrainingResponse> result = new ArrayList<>();
        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("text/plain");
            RequestBody body = RequestBody.create(mediaType, "");
            Request request = new Request.Builder()
                    .url("http://localhost:8080/api/book/training/findAll")
                    .method("GET", null)
                    .addHeader("Cookie", "JSESSIONID=EE1637F3E67E007413996CEFA4CFDF31")
                    .build();
            Response response = client.newCall(request).execute();
            if (response.code() == 200) {
                assert response.body() != null;
                String res = response.body().string();
                result = List.of(gson.fromJson(res, BookTrainingResponse[].class));
                System.out.println(result.size());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } // gọi vào hàm này ở app tôi phát.. gọi hàm này nhá là sao
        return result;
    }



}
