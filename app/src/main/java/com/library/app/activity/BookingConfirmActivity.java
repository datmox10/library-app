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
    private TextView startDate, endDate;
    private TextView startTime, endTime;
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
            startDate.setText(bundle.getString("date"));
            endDate.setText(bundle.getString("date"));
            startTime.setText(bundle.getString("startTime")+":00");
            endTime.setText(bundle.getString("endTime")+":00");
        }

    }

    private void anhxa(){
        roomName = findViewById(R.id.room_name_confirm);
        roomCapable = findViewById(R.id.room_capable_confirm);
        roomDevices = findViewById(R.id.room_device_confirm);

        startDate = findViewById(R.id.room_start_day);
        endDate = findViewById(R.id.room_end_day);
        startTime = findViewById(R.id.room_start_time);
        endTime = findViewById(R.id.room_end_time);
    }
}