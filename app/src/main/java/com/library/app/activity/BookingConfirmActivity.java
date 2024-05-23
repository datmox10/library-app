package com.library.app.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.library.app.R;

public class BookingConfirmActivity extends AppCompatActivity {

    private TextView roomName;
    private TextView roomCapable;
    private TextView roomDevices;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_booking_confirm);
        Log.d( "bundle: ", (savedInstanceState != null) + "");

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        anhxa();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            roomName.setText("Tên phòng: " + bundle.getString("roomCode"));
            roomCapable.setText("Sức chứa: " + bundle.getString("roomCapable"));
            roomDevices.setText("Thiết bị: " + bundle.getString("roomDevice"));
        }

    }

    private void anhxa(){
        roomName = findViewById(R.id.room_name_confirm);
        roomCapable = findViewById(R.id.room_capable_confirm);
        roomDevices = findViewById(R.id.room_device_confirm);
    }
}