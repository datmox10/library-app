package com.library.app.api;

import com.google.gson.Gson;
import com.library.app.common.NetworkTaskExecutor;

import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HandlerQuestionTraining {
    public static class ConversationResponse {
        public String message;
    }

    public static class ConversationRequest {
        public String sessionChat;
        public String conversation;
    }

    public static String getMessage(final ConversationRequest req) {
        final String[] message = {""};
        Runnable thread = () -> {
            try {
                OkHttpClient client = new OkHttpClient().newBuilder()
                        .connectTimeout(15, TimeUnit.SECONDS)
                        .build();
                MediaType mediaType = MediaType.parse("application/json");
                RequestBody body = RequestBody.create(mediaType,
                        new Gson().toJson(req));
                Request request = new Request.Builder()
                        .url("https://138f-14-160-24-223.ngrok-free.app/api/book/training/conversation")
                        .method("POST", body)
                        .addHeader("Content-Type", "application/json")
                        .build();
                Response response = client.newCall(request).execute();
                if (response.code() == 200)
                    message[0] = (new Gson().fromJson(response.body().string(), ConversationResponse.class)
                            .message);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        };
        thread.run();
        System.out.println("mess " + message[0]);
        return message[0];
    }
}
