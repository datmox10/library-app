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

import com.library.app.R;
import com.library.app.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class LoginActivity extends AppCompatActivity {

    private EditText editTaikhoan,editPassword;
    private TextView txtQuenmk,txtError;
    private Button btnLogin;
    private static final String USER_LOGIN = "USER_LOGIN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        anhXa();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(login() == 1){
                    startActivity(MainActivity.class);
                }else{
                    txtError.setVisibility(View.VISIBLE);
                }

            }
        });
    }

    private void anhXa(){
        editTaikhoan = (EditText) findViewById(R.id.editTaikhoan);
        editPassword = (EditText) findViewById(R.id.editPassword);
        txtQuenmk = (TextView) findViewById(R.id.txtQuenmk);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        txtError = (TextView) findViewById(R.id.txtError);
    }

    private int login(){
        String account = editTaikhoan.getText().toString().trim();
        String password = editPassword.getText().toString().trim();
        int test = 0;

       for(User user: getUser()){
            if(user.getAccount().trim().equals(account) && user.getPassword().trim().equals(password)){
                SharedPreferences sharedPreferences = getSharedPreferences(USER_LOGIN,MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("usercode", user.getUserCode());
                editor.putString("account",account);
                editor.putString("password",password);
                editor.putString("name",user.getName());
                editor.commit();
                test = 1;
                Log.d("test: ", String.valueOf(test));
            }
        };
       return test;
    }

    private List<User> getUser(){
        List<User> userList = new ArrayList<>();
        userList.add(new User("1", "sv1","12345","Nguyễn Mạnh Đạt"));
        userList.add(new User("2","sv2","12345","Nguyễn Thị A"));
        return userList;
    }

    private void startActivity(Class<?> cls){
        Intent intent = new Intent(this,cls);
        startActivity(intent);
        finish();
    }
}