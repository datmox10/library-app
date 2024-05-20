package com.library.app.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.library.app.R;
import com.library.app.adapter.AIBookAdapter;
import com.library.app.adapter.MenuSachAdapter;
import com.library.app.api.ApiBookTrain;
import com.library.app.api.ApiBookTraining;
import com.library.app.api.HandlerBookTraining;
import com.library.app.dto.BookTrainingResponse;
import com.library.app.model.BookViewTrainingModel;
import com.library.app.model.Sach;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AIBook extends AppCompatActivity {

        private final String TAG = "AIBook";

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
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_aibook);
            AnhXa();

            String name = "Hỏi đáp AI";
            setToolbar(toolbar,name);
            bookViewTrainingModel.getBookListLiveData().observe(this, booksObserver);
        }

        public void setToolbar(Toolbar toolbar, String name) {
            setSupportActionBar(toolbar);
            ActionBar actionBar = getSupportActionBar();
            if(actionBar!=null){
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setTitle(name);
            }

        }

        private void AnhXa(){
            toolbar = (Toolbar) findViewById(R.id.toolbar_category);
            rcycCategory = (RecyclerView) findViewById(R.id.rcyc_category);
        }

        @Override
        public boolean onOptionsItemSelected(@NonNull MenuItem item) {

            if(item.getItemId() == android.R.id.home){
                finish();
            }
            return super.onOptionsItemSelected(item);
        }

    private void getBook(List<BookTrainingResponse> books) {
        try {
            ArrayList<Sach> sachs = new ArrayList<>();
            Log.d("getBook training: %s", String.valueOf(books.size()));
            books.forEach(book -> {
                Sach sach = new Sach();
                sach.setNhanDeChinh(book.bookName);
                sach.setSoISBN(String.valueOf(book.sessionChat));
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

}