package com.library.app.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.library.app.R;
import com.library.app.adapter.BorrowAdapter;
import com.library.app.adapter.BorrowBookAdapter;
import com.library.app.api.ApiBook;
import com.library.app.model.BorrowBook;
import com.library.app.model.BorrowBookMD;
import com.library.app.model.BorrowBookResponse;
import com.library.app.model.TokenManager;

import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BorrowBookHistoryActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private TokenManager tokenManager;
    private RecyclerView recyclerView;

    private BorrowBookAdapter borrowBookAdapter;

    private BorrowAdapter borrowAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow_book_history);
        tokenManager = TokenManager.getInstance(this);
        anhXa();
        setToolbar(toolbar);
        getBookMD();
    }

    private void anhXa(){
        toolbar =(Toolbar) findViewById(R.id.tool_bar_detail_book);
        recyclerView = (RecyclerView) findViewById(R.id.rcyc_history_borrow);
    }

    private void setToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    private void getBookMD(){
        ArrayList<BorrowBookMD> borrowBookMDS = new ArrayList<>();
        BorrowBookMD borrowBookMD1= new BorrowBookMD();
        borrowBookMD1.setBorrowDate("a");
        borrowBookMD1.setBookId("1");
        borrowBookMD1.setOverdue("a");
        borrowBookMD1.setId("a");

        BorrowBookMD borrowBookMD2= new BorrowBookMD();
        borrowBookMD2.setBorrowDate("a");
        borrowBookMD2.setBookId("1");
        borrowBookMD2.setOverdue("a");
        borrowBookMD2.setId("a");

        borrowBookMDS.add(borrowBookMD1);
        borrowBookMDS.add(borrowBookMD2);


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

                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplication(),3);
                    recyclerView.setLayoutManager(gridLayoutManager);

                    borrowBookAdapter = new BorrowBookAdapter(books,getApplication());
                    recyclerView.setAdapter(borrowBookAdapter);
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