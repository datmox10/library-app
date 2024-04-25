package com.library.app.repository;

import androidx.lifecycle.LiveData;

import com.library.app.dto.BookTrainingResponse;

import java.util.List;

public interface BookTrainingRepository {
    LiveData<List<BookTrainingResponse>> getBooks();

}
