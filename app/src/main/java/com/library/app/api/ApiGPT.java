package com.library.app.api;

import androidx.lifecycle.Observer;

import com.library.app.model.MessParamPost;
import com.library.app.model.MessResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiGPT {
    @Headers(
            {"Content-Type: application/json",
                    "Authorization: Bearer $KEY_API"
            }
    )
    @POST("chat/ompletions")
    Observable<MessResponse> PostQues(
            @Body MessParamPost messParamPost
            );
}
