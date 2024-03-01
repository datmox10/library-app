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
import android.view.MenuItem;
import android.widget.TextView;

import com.library.app.R;
import com.library.app.adapter.MenuSachAdapter;
import com.library.app.model.Sach;

import java.util.ArrayList;

public class CategoryByClassify extends AppCompatActivity {

    private Toolbar toolbar;

    private RecyclerView rcycCategory;

    private MenuSachAdapter menuSachAdapter;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_by_classify);
        AnhXa();

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
        ArrayList<Sach> sachs = new ArrayList<>();
        Sach sach1 = new Sach();
        sach1.setSoISBN("1");
        sach1.setNhanDeChinh("Advanced C");
        sach1.setAnh("R.drawable.newbook");
        Sach sach2 = new Sach();
        sach2.setSoISBN("2");
        sach2.setNhanDeChinh("Advanced D");
        sach2.setAnh("R.drawable.newbook");
        Sach sach3 = new Sach();
        sach3.setSoISBN("2");
        sach3.setNhanDeChinh("Advanced D");
        sach3.setAnh("R.drawable.newbook");
        Sach sach4 = new Sach();
        sach4.setSoISBN("2");
        sach4.setNhanDeChinh("Advanced D");
        sach4.setAnh("R.drawable.newbook");
        Sach sach5 = new Sach();
        sach5.setSoISBN("2");
        sach5.setNhanDeChinh("Advanced D");
        sach5.setAnh("R.drawable.newbook");
        sachs.add(sach1);
        sachs.add(sach2);
        sachs.add(sach3);
        sachs.add(sach4);
        sachs.add(sach5);



        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
        rcycCategory.setLayoutManager(gridLayoutManager);

        menuSachAdapter = new MenuSachAdapter(sachs,this);
        rcycCategory.setAdapter(menuSachAdapter);
    }



}