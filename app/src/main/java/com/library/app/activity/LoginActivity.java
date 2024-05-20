package com.library.app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.library.app.R;
import com.library.app.api.LoginApiService;
import com.library.app.model.LoginRes;
import com.library.app.model.TokenManager;
import com.library.app.model.User;
import com.library.app.model.UserLogin;
import com.library.app.model.UserMD;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText editTaikhoan,editPassword;
    private TextView txtQuenmk,txtError,txtRegister;
    private Button btnLogin;
    private TokenManager tokenManager;
    private static final String USER_LOGIN = "USER_LOGIN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        anhXa();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               startActivity(RegisterAccountActivity.class);
            }
        });
    }

    private void anhXa(){
        editTaikhoan = (EditText) findViewById(R.id.editTaikhoan);
        editPassword = (EditText) findViewById(R.id.editPassword);
        txtQuenmk = (TextView) findViewById(R.id.txtQuenmk);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        txtError = (TextView) findViewById(R.id.txtError);
        txtRegister = (TextView) findViewById(R.id.txtRegister);
    }

    private void login(){
        String account = editTaikhoan.getText().toString().trim();
        String password = editPassword.getText().toString().trim();
        final int test = 0;
        UserLogin userLogin = new UserLogin();
        userLogin.setUsername(account);
        userLogin.setPassword(password);
        LoginApiService.loginApiService.login(userLogin)
                .enqueue(new Callback<LoginRes>() {
                    @Override
                    public void onResponse(Call<LoginRes> call, Response<LoginRes> response) {
                        if(response.isSuccessful()){
                            Log.d( "onResponse: ",response.body().getToken()+"");
                            tokenManager = TokenManager.getInstance(LoginActivity.this);
                            tokenManager.saveToken(response.body().getToken().toString());
                            startActivity(MainActivity.class);
                        }else{
                            Toast.makeText(LoginActivity.this, "Tài khoản hoặc mật khẩu không chính xác!", Toast.LENGTH_SHORT).show();
                        }


                    }

                    @Override
                    public void onFailure(Call<LoginRes> call, Throwable throwable) {
                        Log.d( "onFailure: ","Lỗi");
                    }
                });

////       for(User user: getUser()){
////            if(user.getAccount().trim().equals(account) && user.getPassword().trim().equals(password)){
////                SharedPreferences sharedPreferences = getSharedPreferences(USER_LOGIN,MODE_PRIVATE);
////                SharedPreferences.Editor editor = sharedPreferences.edit();
////                editor.putString("usercode", user.getUserCode());
////                editor.putString("account",account);
////                editor.putString("password",password);
////                editor.putString("name",user.getName());
////                editor.commit();
////                test = 1;
////                Log.d("test: ", String.valueOf(test));
////            }
//        };
    }

    private List<User> getUser(){
        List<User> userList = new ArrayList<>();
//        userList.add(new User("1", "sv1","12345","Nguyễn Mạnh Đạt"));
//        userList.add(new User("2","sv2","12345","Nguyễn Thị A"));
        return userList;
    }

    private void startActivity(Class<?> cls){
        Intent intent = new Intent(this,cls);
        startActivity(intent);
        finish();
    }
}