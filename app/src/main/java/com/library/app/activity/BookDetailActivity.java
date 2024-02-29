package com.library.app.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.library.app.R;
import com.library.app.model.Sach;

import java.lang.reflect.Field;

public class BookDetailActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Button btnMuon, btnYeuthich;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        AnhXa();
        init();
        intent();

        btnMuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookDetailActivity.this, BorrowActivity.class);
                intent.putExtra("a","a");
                startActivity(intent);

            }
        });

        btnYeuthich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookDetailActivity.this, FavouriteActivity.class);
                intent.putExtra("a","a");
                startActivity(intent);
            }
        });


    }
    private void AnhXa(){
        btnMuon = (Button) findViewById(R.id.btn_muon);
        btnYeuthich = (Button) findViewById(R.id.btn_yeuthich);
        toolbar = (Toolbar) findViewById(R.id.tool_bar_detail_book);
    }
    private void init(){
        setSupportActionBar(toolbar);
        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null) {
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