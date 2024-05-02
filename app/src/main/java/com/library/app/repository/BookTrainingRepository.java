package com.library.app.repository;

import androidx.lifecycle.LiveData;

import com.library.app.dto.BookTrainingResponse;
import com.library.app.dto.ConversationRequest;
import com.library.app.dto.ConversationResponse;

import java.util.List;

public interface BookTrainingRepository {
    LiveData<List<BookTrainingResponse>> getBooks();

    LiveData<ConversationResponse> getQuestionResponse(ConversationRequest request);
}
