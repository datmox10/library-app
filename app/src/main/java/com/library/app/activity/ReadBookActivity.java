package com.library.app.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.library.app.R;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ReadBookActivity extends AppCompatActivity {

    private PDFView pdfView;
    private Toolbar toolbar;
    String id,ten,anh;
    Integer pageNumber =0;
    private TextView txtPage;
    String pdf = "https://file.nhasachmienphi.com/pdf/nhasachmienphi-206-mon-canh-dinh-duong-cho-tre-em.pdf";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_book);

        AnhXa();
        init();
        new RetrievePDFfromUrl().execute(pdf);
    }

    class RetrievePDFfromUrl extends AsyncTask<String,Void,InputStream> implements OnPageChangeListener {
        @Override
        protected InputStream doInBackground(String... strings) {
            InputStream inputStream = null;
            try{
                URL url = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                if(urlConnection.getResponseCode() == 200){
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }
            }catch (IOException e){
                e.printStackTrace();
                return null;
            }
            return inputStream;
        }

        @Override
        protected void onPostExecute(InputStream inputStream) {

            pdfView.fromStream(inputStream)
                    .enableSwipe(true)
                    .swipeHorizontal(false)
                    .defaultPage(0)
                    .onPageChange(this)
                    .spacing(10)
                    .load();
        }

        @Override
        public void onPageChanged(int page, int pageCount) {
            pageNumber = page;
            txtPage.setText(String.format("%s / %s",page+1, pageCount));
        }
    }

    private void AnhXa(){
        pdfView = (PDFView) findViewById(R.id.pdf_view);
        toolbar = (Toolbar) findViewById(R.id.tool_bar_read_book);
        txtPage = (TextView) findViewById(R.id.txtPage);
    }

    private void init(){
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            id = bundle.getString("id", "");
            ten = bundle.getString("ten", "");
            anh = bundle.getString("anh", "");
        }
        setSupportActionBar(toolbar);
        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(ten);
        }
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