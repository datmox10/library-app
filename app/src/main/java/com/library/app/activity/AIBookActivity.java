package com.library.app.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.library.app.R;
import com.library.app.adapter.AIBookAdapter;
import com.library.app.dto.BookTrainingResponse;
import com.library.app.model.BookViewTrainingModel;
import com.library.app.model.Sach;
import com.library.app.repository.ApiClient;
import com.library.app.repository.BookTrainingRepository;
import com.library.app.repository.remote.RemoteBookTrainingRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class AIBookActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private RecyclerView rcycCategory;

    private AIBookAdapter aiBookAdapter;
    private BookViewTrainingModel bookViewTrainingModel;

    private final Observer<List<BookTrainingResponse>> booksObserver = bookTrainingResponses -> {
        if (bookTrainingResponses != null) {
            getBook(bookTrainingResponses);
        }
    };

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        BookTrainingRepository bookRepository = new RemoteBookTrainingRepository(ApiClient.getApiService());
        bookViewTrainingModel = new BookViewTrainingModel(bookRepository);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aibook);
        AnhXa();
        String name = "Hỏi đáp AI";
        setToolbar(toolbar, name);
        bookViewTrainingModel.getBookListLiveData().observe(this, booksObserver);
    }



    private void AnhXa() {
        toolbar = findViewById(R.id.toolbar_aibook);
        rcycCategory = findViewById(R.id.rcyc_category);
    }


    private void getBook(List<BookTrainingResponse> books) {
        try {
            ArrayList<Sach> sachs = new ArrayList<>();
            Log.d("getBook training: %s", String.valueOf(books.size()));
            books.forEach(book -> {
                Sach sach = new Sach();
                sach.setNhanDeChinh(book.bookName);
                sach.setSoISBN(String.valueOf(book.sessionChat));
                sach.setTacGia(book.getAuthor());
                sach.setAnh(book.getImage());
                sachs.add(sach);
            });
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
            rcycCategory.setLayoutManager(linearLayoutManager);
            aiBookAdapter = new AIBookAdapter(sachs, this);
            rcycCategory.setAdapter(aiBookAdapter);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void setToolbar(Toolbar toolbar, String name) {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(name);
        }

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}