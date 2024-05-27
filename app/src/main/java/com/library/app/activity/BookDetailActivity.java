package com.library.app.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.library.app.R;
import com.library.app.adapter.MenuSachAdapter;
import com.library.app.api.ApiBook;
import com.library.app.model.Book;
import com.library.app.model.BookResponse;
import com.library.app.model.Sach;
import com.library.app.model.TokenManager;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Field;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookDetailActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Button btnMuon, btnYeuthich,btnDoc;
    String id,ten,anh;
    private TextView txtName, txtTacgia;

    private  ImageView imageView;

    private RecyclerView ecycler_related_book;
    private TokenManager tokenManager;
    private MenuSachAdapter menuSachAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        tokenManager = TokenManager.getInstance(this);
        AnhXa();
        init();
        intent();
        setToolbar(toolbar);
        btnMuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookDetailActivity.this, BorrowActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);

            }
        });

        btnYeuthich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BookDetailActivity.this, "da them vào yeu thich", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(BookDetailActivity.this, FavouriteActivity.class);
                        intent.putExtra("id",id);
                        startActivity(intent);
                    }
                },2000);
            }
        });

//        btnDoc.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(BookDetailActivity.this, ReadBookActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("id",id);
//                bundle.putString("ten",ten);
//                intent.putExtras(bundle);
//                startActivity(intent);
//            }
//        });


    }
    private void AnhXa(){
        btnMuon = (Button) findViewById(R.id.btn_muon);
        btnYeuthich = (Button) findViewById(R.id.btn_yeuthich);
        toolbar = (Toolbar) findViewById(R.id.tool_bar_detail_book);
        txtName = (TextView) findViewById(R.id.txtName);
        txtTacgia = (TextView) findViewById(R.id.txtTacgia);
        imageView = (ImageView) findViewById(R.id.img);
        ecycler_related_book = (RecyclerView) findViewById(R.id.recycler_related_book);
    }
    private void init(){
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            id = bundle.getString("id", "");
             ten = bundle.getString("ten", "");
        }

        ApiBook apiBook = new ApiBook(tokenManager.getToken());
        ApiBook.MyApi myApi = apiBook.getRetrofitInstance().create(ApiBook.MyApi.class);
        Call<Book> call = myApi.getBookDetail(id);
        call.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                if(response.isSuccessful()){
                    Log.d("onResponse: ",response.code()+"");
                    Book book = response.body();
                    txtName.setText(book.getTitle());
                    txtTacgia.setText(book.getAuthor());
                    Picasso.get()
                            .load(book.getImage())
                            .into(imageView);
                }else{
                    Log.d("onResponse: ",response.code()+"");
                }
            }

            @Override
            public void onFailure(Call<Book> call, Throwable throwable) {
                Log.d("onResponse: ",throwable.toString());
            }
        });
        Call<BookResponse> call1 = myApi.getData();
        call1.enqueue(new Callback<BookResponse>() {
            @Override
            public void onResponse(Call<BookResponse> call, Response<BookResponse> response) {
    
                if(response.isSuccessful()){
                    Log.d( "onResponse: ", String.valueOf(response.body().getBooksEntityList().size()));
                    ArrayList<Book> books = response.body().getBooksEntityList();

                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),RecyclerView.HORIZONTAL,false);
                    ecycler_related_book.setLayoutManager(linearLayoutManager);

                    menuSachAdapter = new MenuSachAdapter(books,getApplicationContext());
                    ecycler_related_book.setAdapter(menuSachAdapter);
                }else{
                    Log.d( "onResponse: ", String.valueOf(response.code()));
                }

            }

            @Override
            public void onFailure(Call<BookResponse> call, Throwable throwable) {
                Log.d( "onResponse: ", throwable.toString());
            }
        });


        setToolbar(toolbar);
    }

    public void setToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }
    private void intent(){
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) // Press Back Icon
        {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }


}