package com.library.app.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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
import com.library.app.api.HandlerBookTraining;
import com.library.app.dto.BookTrainingResponse;
import com.library.app.model.Sach;

import java.util.ArrayList;
import java.util.List;

public class AIBook extends AppCompatActivity {

        private Toolbar toolbar;

        private RecyclerView rcycCategory;

        private AIBookAdapter aiBookAdapter;
        @SuppressLint("MissingInflatedId")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_aibook);
            AnhXa();

            String name = "Hỏi đáp AI";
            setToolbar(toolbar,name);
            getBook();
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

        private void getBook(){
            ArrayList<Sach> sachs = new ArrayList<>();
            HandlerBookTraining handlerBookTraining = new HandlerBookTraining();
            List<BookTrainingResponse> books = handlerBookTraining.getAllBookTraining();
            Log.d( "getBook: %s", String.valueOf(books.size()));
            books.forEach(book-> {
                Sach sach = new Sach();
                sach.setNhanDeChinh(book.bookName);
                sach.setSoISBN(String.valueOf(book.id));
                sachs.add(sach);
            });



            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
            rcycCategory.setLayoutManager(linearLayoutManager);

            aiBookAdapter = new AIBookAdapter(sachs,this);
            rcycCategory.setAdapter(aiBookAdapter);
        }

}