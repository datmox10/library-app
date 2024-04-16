package com.library.app.api;

import com.library.app.dto.BookTrainingResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("api/book/training/findAll")
    Call<List<BookTrainingResponse>> getAllBookTraining();

}
