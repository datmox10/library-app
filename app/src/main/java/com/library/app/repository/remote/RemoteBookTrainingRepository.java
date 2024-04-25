package com.library.app.repository.remote;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.library.app.dto.BookTrainingResponse;
import com.library.app.repository.ApiService;
import com.library.app.repository.BookTrainingRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteBookTrainingRepository implements BookTrainingRepository {
    private final ApiService apiService;

    public RemoteBookTrainingRepository(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public LiveData<List<BookTrainingResponse>> getBooks() {
        MutableLiveData<List<BookTrainingResponse>> data = new MutableLiveData<>();
        apiService.getBooks().enqueue(new Callback<List<BookTrainingResponse>>() {
            @Override
            public void onResponse(@NonNull Call<List<BookTrainingResponse>> call,
                                   @NonNull Response<List<BookTrainingResponse>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                    System.out.println("total book training == " + data.getValue().size());
                } else {
                    System.out.println("error ");
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<BookTrainingResponse>> call, @NonNull Throwable t) {
                // Xử lý lỗi
                throw new RuntimeException(t);
            }
        });
        return data;
    }
}
