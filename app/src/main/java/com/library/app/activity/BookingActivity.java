package com.library.app.activity;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import com.library.app.R;
import com.library.app.adapter.RoomAdapter;
import com.library.app.api.ApiRoom;
import com.library.app.dto.RoomInDateResponse;
import com.library.app.model.MessRegister;
import com.library.app.model.Room;
import com.library.app.model.TokenManager;
import com.library.app.model.UserLogin;
import com.library.app.model.UserMD;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_booking);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        DatePicker datePicker = findViewById(R.id.datePicker);
        Button confirmButton = findViewById(R.id.confirm_button);
        Button pickDateButton = findViewById(R.id.pickDateButton);

        datePicker.init(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // Hiển thị nút Confirm khi đã chọn ngày
                confirmButton.setVisibility(View.VISIBLE);
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hiển thị thông tin ngày chọn
                String selectedDate = datePicker.getDayOfMonth() + "/" + (datePicker.getMonth() + 1) + "/" + datePicker.getYear();
                pickDateButton.setText("Ngày đặt phòng: " + selectedDate);

                // Ẩn DatePicker và nút Confirm
                datePicker.setVisibility(View.GONE);
                confirmButton.setVisibility(View.GONE);

                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth();
                int year = datePicker.getYear();

                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, day);
                getData(calendar.getTime());
            }
        });

        pickDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hiển thị DatePicker khi nút được nhấn
                datePicker.setVisibility(View.VISIBLE);
            }
        });


    }

    private void getData(Date date){
        ApiRoom.apiRoom.getRoomInDate(date).enqueue(
                new Callback<ArrayList<Room>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Room>> call, Response<ArrayList<Room>> response) {
                        if(response.isSuccessful()){
                            RoomAdapter adapter = new RoomAdapter(response.body(), getApplicationContext());

                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Room>> call, Throwable throwable) {

                    }
                }
        );
    }
}