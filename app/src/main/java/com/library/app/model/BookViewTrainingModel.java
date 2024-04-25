package com.library.app.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.library.app.dto.BookTrainingResponse;
import com.library.app.repository.BookTrainingRepository;
import com.library.app.repository.remote.RemoteBookTrainingRepository;

import java.util.List;

public class BookViewTrainingModel extends ViewModel {
    private final BookTrainingRepository bookRepository;
    private LiveData<List<BookTrainingResponse>> bookListLiveData;

    public BookViewTrainingModel(BookTrainingRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public LiveData<List<BookTrainingResponse>> getBookListLiveData() {
        if (bookListLiveData == null) {
            bookListLiveData = bookRepository.getBooks();
        }
        return bookListLiveData;
    }
}
