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

import com.library.app.R;
import com.library.app.adapter.AIBookAdapter;
import com.library.app.adapter.MenuSachAdapter;
import com.library.app.model.Sach;

import java.util.ArrayList;

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
            Sach sach1 = new Sach();
            sach1.setSoISBN("1");
            sach1.setNhanDeChinh("Advanced C");
            sach1.setTacGia("Mạnh Đạt");
            sach1.setAnh("R.drawable.newbook");
            Sach sach2 = new Sach();
            sach2.setSoISBN("2");
            sach2.setNhanDeChinh("Advanced D");
            sach1.setTacGia("Mạnh Đạt");
            sach2.setAnh("R.drawable.newbook");
            Sach sach3 = new Sach();
            sach3.setSoISBN("2");
            sach1.setTacGia("Mạnh Đạt");
            sach3.setNhanDeChinh("Advanced D");
            sach3.setAnh("R.drawable.newbook");
            Sach sach4 = new Sach();
            sach4.setSoISBN("2");
            sach4.setNhanDeChinh("Advanced D");
            sach1.setTacGia("Mạnh Đạt");
            sach4.setAnh("R.drawable.newbook");
            Sach sach5 = new Sach();
            sach5.setSoISBN("2");
            sach5.setNhanDeChinh("Advanced D");
            sach1.setTacGia("Mạnh Đạt");
            sach5.setAnh("R.drawable.newbook");
            sachs.add(sach1);
            sachs.add(sach2);
            sachs.add(sach3);
            sachs.add(sach4);
            sachs.add(sach5);



            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
            rcycCategory.setLayoutManager(linearLayoutManager);

            aiBookAdapter = new AIBookAdapter(sachs,this);
            rcycCategory.setAdapter(aiBookAdapter);
        }

}