package com.library.app.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.library.app.R;
import com.library.app.api.LoginApiService;
import com.library.app.model.MessRegister;
import com.library.app.model.UserMD;

import java.util.Currency;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterAccountActivity extends AppCompatActivity {
    EditText editTaiKhoan, editPassword,editEmail,editPhone,editFullName;
    Button btnRegister;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_account);
        AnhXa();
        setToolbar(toolbar);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                if(editTaiKhoan.getText().toString().trim() != "" && editPassword.getText().toString().trim() != "" && editEmail.getText().toString().trim() != "" && editPhone.getText().toString().trim() != "" && editFullName.getText().toString().trim() != ""){
                  if(isEmptyEdit()){
                      register();

                }else{
                    Toast.makeText(RegisterAccountActivity.this, "Vui lòng không để trống!", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void AnhXa(){
        editTaiKhoan = (EditText) findViewById(R.id.editTaikhoan1);
        editPassword = (EditText) findViewById(R.id.editPassword1);
        editEmail = (EditText) findViewById(R.id.editEmail1);
        editPhone = (EditText) findViewById(R.id.editPhone1);
        editFullName = (EditText) findViewById(R.id.editFullName1);
        toolbar = (Toolbar) findViewById(R.id.register_toolbar1);
        btnRegister = (Button) findViewById(R.id.btnRegister);
    }
    public void setToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    private boolean isEmptyEdit(){
        if(editTaiKhoan.getText().toString().trim().isEmpty() || editPassword.getText().toString().trim().isEmpty()|| editEmail.getText().toString().trim().isEmpty() || editPhone.getText().toString().trim().isEmpty() || editFullName.getText().toString().trim().isEmpty()){
            return false;
        }
        return true;
    }

    private void register(){
        UserMD userMD = new UserMD();
        userMD.setUserName(editTaiKhoan.getText().toString().trim());
        userMD.setPassWord(editPassword.getText().toString().trim());
        userMD.setEmail(editEmail.getText().toString().trim());
        userMD.setPhoneNumber(editPhone.getText().toString().trim());
        userMD.setFullName(editFullName.getText().toString().trim());

        LoginApiService.loginApiService.register(userMD)
                        .enqueue(new Callback<MessRegister>() {
                            @Override
                            public void onResponse(Call<MessRegister> call, Response<MessRegister> response) {
                                if(response.isSuccessful() && response.body() != null) {
                                    MessRegister mess = response.body();
                                    Toast.makeText(RegisterAccountActivity.this, "Đăng kí tài khoản thành công!", Toast.LENGTH_SHORT).show();
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            startActivity(LoginActivity.class);
                                        }
                                    },1000);
                                }else{
                                    Toast.makeText(RegisterAccountActivity.this, "Đã tồn tại tài khoản này!", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<MessRegister> call, Throwable throwable) {
                                Toast.makeText(RegisterAccountActivity.this, "loi", Toast.LENGTH_SHORT).show();
                            }
                        });
    }
    private void startActivity(Class<?> cls){
        Intent intent = new Intent(this,cls);
        startActivity(intent);
        finish();
    }
}