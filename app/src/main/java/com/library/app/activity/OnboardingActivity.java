package com.library.app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.library.app.R;

public class OnboardingActivity extends AppCompatActivity {

    private static final String KEY_FIRST_INSTALL = "MY_SHARE_PREFERENCES";
    Button button;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        button = (Button) findViewById(R.id.submit_onboarding);
        final MySharedPreferences mySharedPreferences = new MySharedPreferences(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mySharedPreferences.putBooleanValue(KEY_FIRST_INSTALL,true);
                startActivity(MainActivity.class);
            }
        });

    }
    private void startActivity(Class<?> cls){
        Intent intent = new Intent(this,cls);
        startActivity(intent);
        finish();
    }
}