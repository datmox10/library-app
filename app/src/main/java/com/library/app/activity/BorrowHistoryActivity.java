package com.library.app.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.library.app.R;
import com.library.app.adapter.BorrowAdapter;
import com.library.app.adapter.BorrowBookAdapter;
import com.library.app.adapter.ClassifyAdapter;
import com.library.app.api.ApiBook;
import com.library.app.model.BorrowBookMD;
import com.library.app.model.BorrowBookResponse;
import com.library.app.model.TokenManager;

import java.util.ArrayList;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BorrowHistoryActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;

    private BorrowAdapter borrowAdapter;

    private TokenManager tokenManager;
    private RelativeLayout returnLayout;
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow_history);
        tokenManager = TokenManager.getInstance(this);
        anhXa();
        setToolbar(toolbar);
        getData();
    }

    public void setToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void anhXa(){
        toolbar = (Toolbar) findViewById(R.id.toolbar_return);
        recyclerView = (RecyclerView) findViewById(R.id.rcyc_return);
        returnLayout =(RelativeLayout) findViewById(R.id.return_layout);
        imageView = (ImageView) findViewById(R.id.qrCodeImageView);
    }


    private void getData(){
        ApiBook apiBook = new ApiBook(tokenManager.getToken());
        ApiBook.MyApi myApi = apiBook.getRetrofitInstance().create(ApiBook.MyApi.class);
        Call<BorrowBookResponse> call = myApi.getBorrowHistory();
        call.enqueue(new Callback<BorrowBookResponse>() {
            @Override
            public void onResponse(Call<BorrowBookResponse> call, Response<BorrowBookResponse> response) {

                if(response.isSuccessful()){
                    ArrayList<BorrowBookMD> books = response.body().getBorrowBookMDS();
                    Log.d( "history: ",books.size()+"" );

                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplication(),RecyclerView.VERTICAL,false);
                    recyclerView.setLayoutManager(linearLayoutManager);

                    borrowAdapter = new BorrowAdapter(books,getApplication(),returnLayout,imageView);
                    recyclerView.setAdapter(borrowAdapter);
                }else{
                    Log.d( "history: ", String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<BorrowBookResponse> call, Throwable throwable) {
                Log.d( "history: ", throwable.toString());
            }
        });
    }


}