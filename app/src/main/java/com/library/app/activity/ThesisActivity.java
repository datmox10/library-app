package com.library.app.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.library.app.R;

public class ThesisActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_thesis2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView text1 = findViewById(R.id.text1);

        final String link = "https://forms.gles/h9uo9XLrLrvyPkoVa";

        String textToShow = "1. Đăng ký vào đường link sau : " + link;

        SpannableString spannableString = new SpannableString(textToShow);

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                startActivity(intent);
            }
        };

        spannableString.setSpan(clickableSpan, textToShow.indexOf(link), textToShow.indexOf(link) + link.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        text1.setText(spannableString);

        text1.setMovementMethod(LinkMovementMethod.getInstance());


    }
}