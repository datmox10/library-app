package com.library.app.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.library.app.dto.ConversationRequest;
import com.library.app.dto.ConversationResponse;
import com.library.app.repository.BookTrainingRepository;

public class BookAIChatModel extends ViewModel {
    private final BookTrainingRepository bookRepository;
    private LiveData<ConversationResponse> bookListLiveData;
    public BookAIChatModel(BookTrainingRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public LiveData<ConversationResponse> getQuestion(ConversationRequest conversationRequest) {
        if (bookListLiveData == null) {
            bookListLiveData = bookRepository.getQuestionResponse(conversationRequest);
        }
           return bookListLiveData;
    }
}
