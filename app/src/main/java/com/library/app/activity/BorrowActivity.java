package com.library.app.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.library.app.R;
import com.library.app.api.ApiBook;
import com.library.app.fragment.HomeFragment;
import com.library.app.model.Book;
import com.library.app.model.BorrowBook;
import com.library.app.model.BorrowResponse;
import com.library.app.model.TokenManager;
import com.library.app.model.UserMD;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BorrowActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView txtName,txtTacgia,txtsl,txt_user,txt_email,txt_ngay_dat,txt_ngay_tra;
    private Button btn_borrow_book;

    private RelativeLayout layout_qrcode,mainLayout;
    private ImageView qrCodeImageView,img;

    private TokenManager tokenManager;
    String id;

    private String qrText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow);
        tokenManager = TokenManager.getInstance(this);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            id = bundle.getString("id", "");
        }

        AnhXa();
        setToolbar();

        getThongTin();


        btn_borrow_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_qrcode.setVisibility(View.VISIBLE);
                String qrText = txtName + "\n" +txt_email +"\n" + id;
                Log.d("qrText: ",qrText);
                try {
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.encodeBitmap(qrText, BarcodeFormat.QR_CODE, 400, 400);
                    qrCodeImageView.setImageBitmap(bitmap);
                } catch (WriterException e) {
                    e.printStackTrace();
                }
            }
        });

        layout_qrcode.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(layout_qrcode.getVisibility()==View.VISIBLE){
                    layout_qrcode.setVisibility(View.GONE);

                    Toast.makeText(BorrowActivity.this, "Mượn sách thành công!", Toast.LENGTH_SHORT).show();

                    ApiBook apiBook = new ApiBook(tokenManager.getToken());
                    ApiBook.MyApi myApi = apiBook.getRetrofitInstance().create(ApiBook.MyApi.class);
                    BorrowBook borrowBook = new BorrowBook();
                    borrowBook.setBookId(id);
                    borrowBook.setQuantity("1");
                    Call<BorrowResponse> call2 = myApi.borrowBook(borrowBook);
                    call2.enqueue(new Callback<BorrowResponse>() {
                        @Override
                        public void onResponse(Call<BorrowResponse> call, Response<BorrowResponse> response) {
                            if(response.isSuccessful()){
                                Log.d( "muonsach: ","muon thanh cong");
                            }else{
                                Log.d( "muonsach: ",response.code()+"");
                            }

                        }

                        @Override
                        public void onFailure(Call<BorrowResponse> call, Throwable throwable) {
                            Log.d( "muonsach1: ",throwable.toString());
                        }
                    });
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(MainActivity.class);
                        }
                    },2000);

                }
                return false;
            }
        });
    }

    private void setToolbar(){
        setSupportActionBar(toolbar);
        ActionBar actionBar = this.getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
    private void AnhXa(){
        toolbar = (Toolbar) findViewById(R.id.toolbar_borrow_book);
        txt_email = (TextView) findViewById(R.id.txt_email);
        txt_ngay_dat = (TextView) findViewById(R.id.txt_ngay_dat);
        txtsl = (TextView) findViewById(R.id.txtsl);
        txt_user = (TextView) findViewById(R.id.txt_user);
        txt_ngay_tra=(TextView) findViewById(R.id.txt_ngay_tra);
        txtName = (TextView) findViewById(R.id.txtName);
        txtTacgia = (TextView) findViewById(R.id.txtTacgia);

        btn_borrow_book = (Button) findViewById(R.id.btn_borrow_book);
        img = (ImageView) findViewById(R.id.img);
        qrCodeImageView =(ImageView) findViewById(R.id.qrCodeImageView);
        layout_qrcode = (RelativeLayout) findViewById(R.id.layout_qrcode);
        mainLayout =(RelativeLayout) findViewById(R.id.mainLayout);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()== android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void getThongTin(){
        ApiBook apiBook = new ApiBook(tokenManager.getToken());
        ApiBook.MyApi myApi = apiBook.getRetrofitInstance().create(ApiBook.MyApi.class);

        Call<UserMD> user = myApi.getUser();
        user.enqueue(new Callback<UserMD>() {
            @Override
            public void onResponse(Call<UserMD> call, Response<UserMD> response) {
                if(response.isSuccessful()){
                        UserMD user = response.body();
                        txt_user.setText(user.getFullName());
                        txt_email.setText(user.getEmail());

                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                    String currentDate = dateFormat.format(calendar.getTime());
                        txt_ngay_dat.setText(currentDate);

                    calendar.add(Calendar.DAY_OF_YEAR, 7);
                    String newDate = dateFormat.format(calendar.getTime());

                    txt_ngay_tra.setText(newDate);

                }else{
                    Log.d("onResponse1: ",response.code()+"");
                }
            }

            @Override
            public void onFailure(Call<UserMD> call, Throwable throwable) {
                Log.d("onResponse1: ",throwable.toString());
            }
        });

        Call<Book> book = myApi.getBookDetail(id);
        book.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                if(response.isSuccessful()){
                    Book book = response.body();
                    txtName.setText(book.getTitle());
                    txtTacgia.setText(book.getAuthor());
                    Picasso.get()
                            .load(book.getImage())
                            .into(img);

                }else{
                    Log.d("onResponse: ",response.code()+"");
                }

            }

            @Override
            public void onFailure(Call<Book> call, Throwable throwable) {
                Log.d("onResponse: ",throwable.toString());
            }
        });


    }
    private void startActivity(Class<?> cls){
        Intent intent = new Intent(this,cls);
        startActivity(intent);
        finish();
    }
}