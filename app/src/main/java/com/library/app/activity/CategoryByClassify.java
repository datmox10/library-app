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
import android.widget.TextView;

import com.library.app.R;
import com.library.app.adapter.MenuSachAdapter;
import com.library.app.api.ApiBook;
import com.library.app.model.Book;
import com.library.app.model.BookResponse;
import com.library.app.model.Sach;
import com.library.app.model.TokenManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryByClassify extends AppCompatActivity {

    private Toolbar toolbar;

    private RecyclerView rcycCategory;

    private MenuSachAdapter menuSachAdapter;

    private TokenManager tokenManager;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_by_classify);
        AnhXa();
        tokenManager = TokenManager.getInstance(this);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
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
        ApiBook apiBook = new ApiBook(tokenManager.getToken());
        ApiBook.MyApi myApi = apiBook.getRetrofitInstance().create(ApiBook.MyApi.class);
        Call<BookResponse> call = myApi.getData();
        call.enqueue(new Callback<BookResponse>() {
            @Override
            public void onResponse(Call<BookResponse> call, Response<BookResponse> response) {

                if(response.isSuccessful()){
                    Log.d( "onResponse: ", String.valueOf(response.body().getBooksEntityList().size()));
                    ArrayList<Book> books = response.body().getBooksEntityList();

                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplication(),3);
                    rcycCategory.setLayoutManager(gridLayoutManager);

                    menuSachAdapter = new MenuSachAdapter(books,getApplication());
                    rcycCategory.setAdapter(menuSachAdapter);
                }else{
                    Log.d( "onResponse: ", String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<BookResponse> call, Throwable throwable) {
                Log.d( "onResponse: ", throwable.toString());
            }
        });
    }



}