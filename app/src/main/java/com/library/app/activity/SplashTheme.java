package com.library.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SplashTheme extends AppCompatActivity {
    private static final String KEY_FIRST_INSTALL = "MY_SHARE_PREFERENCES";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        final MySharedPreferences mySharedPreferences = new MySharedPreferences(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mySharedPreferences.getBooleanValue(KEY_FIRST_INSTALL)){
                    startActivity(MainActivity.class);
                }else {
                    startActivity(OnboardingActivity.class);
//                    mySharedPreferences.putBooleanValue(KEY_FIRST_INSTALL,true);
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
