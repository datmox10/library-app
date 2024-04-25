package com.library.app.repository;

import com.library.app.dto.BookTrainingResponse;
import com.library.app.dto.ConversationRequest;
import com.library.app.dto.ConversationResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @GET("/api/book/training/findAll")
    Call<List<BookTrainingResponse>> getBooks();
    @POST("/")
    Call<ConversationResponse> question(ConversationRequest request);
}
