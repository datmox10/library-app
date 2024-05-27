package com.library.app.activity;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewDebug;
import android.widget.Button;
import android.widget.DatePicker;

import com.library.app.R;
import com.library.app.adapter.RoomAdapter;
import com.library.app.api.ApiRoomClass;
import com.library.app.dto.RoomsInDateResponse;
import com.library.app.model.MessRegister;
import com.library.app.model.Room;
import com.library.app.model.TokenManager;
import com.library.app.model.UserLogin;
import com.library.app.model.UserMD;

import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingActivity extends AppCompatActivity {
    private RecyclerView recyclerListRoom;
    private RoomAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private Context actContext;
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

        actContext = this;

        DatePicker datePicker = findViewById(R.id.datePicker);
        Button confirmButton = findViewById(R.id.confirm_button);
        Button pickDateButton = findViewById(R.id.pickDateButton);

        linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL,false);
        recyclerListRoom = findViewById(R.id.listRoom);
        recyclerListRoom.setLayoutManager(linearLayoutManager);
        List<Room> rooms = new ArrayList<>();
        adapter = new RoomAdapter(rooms, getApplicationContext(), null);
        recyclerListRoom.setAdapter(adapter);

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
                getData(new java.sql.Date((calendar.getTime()).getTime()));
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
        ApiRoomClass apiRoomClass = new ApiRoomClass(TokenManager.getInstance(this).getToken());
        ApiRoomClass.ApiRoom apiRoom = apiRoomClass.getRetrofitInstance().create(ApiRoomClass.ApiRoom.class);
        Call<List<Room>> call = apiRoom.getRoomInDate(date);
        call.enqueue(
                new Callback<List<Room>>() {
                    @Override
                    public void onResponse(Call<List<Room>> call, Response<List<Room>> response) {
                        if(response.isSuccessful()){
                            adapter = new RoomAdapter(response.body(), actContext, date);
                            recyclerListRoom.setAdapter(adapter);
                        }else{
                            Log.d( "onResponse: ", response.code()+"");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Room>> call, Throwable throwable) {
                        Log.d("onFailure: ",throwable.toString());
                    }
                }
        );
    }
}