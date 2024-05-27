package com.library.app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.library.app.R;

public class SplashActivity extends AppCompatActivity {

    private static final String KEY_FIRST_INSTALL = "MY_SHARE_PREFERENCES";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final MySharedPreferences mySharedPreferences = new MySharedPreferences(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mySharedPreferences.getBooleanValue(KEY_FIRST_INSTALL)){
                    startActivity(LoginActivity.class);
                }else {
                    startActivity(OnboardingActivity.class);
                    mySharedPreferences.putBooleanValue(KEY_FIRST_INSTALL,true);
                }
            }
        },2000);
    }

    private void startActivity(Class<?> cls){
        Intent intent = new Intent(this,cls);
        startActivity(intent);
        finish();
    }
}