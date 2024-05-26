package com.library.app.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.library.app.R;
import com.library.app.api.ApiRoomClass;
import com.library.app.api.ApiUser;
import com.library.app.dto.RoomBookingRequest;
import com.library.app.dto.RoomBookingResponse;
import com.library.app.dto.UserInfoResponse;
import com.library.app.model.TokenManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingConfirmActivity extends AppCompatActivity {

    private TextView roomName;
    private TextView roomCapable;
    private TextView roomDevices;
    private TextView startDate, endDate;
    private TextView startTime, endTime;
    private EditText reason_txt;
    private EditText amount_txt;
    private Button save_btn;
    private Context context;
    private List<String> timeFrames;
    String userID;
    String userName;
    String roomCode;
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

        context = this;
        anhxa();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            roomCode = bundle.getString("roomCode");
            roomName.setText("Tên phòng: " + roomCode);
            roomCapable.setText("Sức chứa: " + bundle.getString("roomCapable"));
            roomDevices.setText("Thiết bị: " + bundle.getString("roomDevice"));
            startDate.setText(bundle.getString("date"));
            endDate.setText(bundle.getString("date"));
            startTime.setText(bundle.getString("startTime")+":00");
            endTime.setText(bundle.getString("endTime")+":00");

            timeFrames = bundle.getStringArrayList("listTimeFrame");
            Log.d("TimeFrame:", timeFrames.get(0));
        }
        ApiUser apiUser = new ApiUser(TokenManager.getInstance(this).getToken());
        ApiUser.ApiUserInterface apiUserInterface = apiUser.getRetrofitInstance().create(ApiUser.ApiUserInterface.class);
        Call<UserInfoResponse> call = apiUserInterface.getUser();
        call.enqueue(new Callback<UserInfoResponse>(){
                    @Override
                    public void onResponse(Call<UserInfoResponse> call, Response<UserInfoResponse> response) {
                        Log.d( "onResponse: ",response.code()+"");
                        Log.d( "userCode: ",response.body().getId());
                        userID = response.body().getId();
                        userName = response.body().getFullName();
                    }

                    @Override
                    public void onFailure(Call<UserInfoResponse> call, Throwable throwable) {
                        Log.d( "onFailure: ","Lỗi");
                    }
                });

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String reason = reason_txt.getText().toString();
                int amount = Integer.parseInt(amount_txt.getText().toString());

                RoomBookingRequest newRequest = new RoomBookingRequest(bundle.getString("roomCode"), timeFrames, userID, bundle.getString("date"), reason, amount);

                ApiRoomClass apiRoomClass = new ApiRoomClass(TokenManager.getInstance(context).getToken());
                ApiRoomClass.ApiRoom apiRoom = apiRoomClass.getRetrofitInstance().create(ApiRoomClass.ApiRoom.class);
                Call<RoomBookingResponse> call = apiRoom.checkoutBooking(newRequest);
                call.enqueue(new Callback<RoomBookingResponse>() {
                    @Override
                    public void onResponse(Call<RoomBookingResponse> call, Response<RoomBookingResponse> response) {
                        Log.d("OnResponse", response.code()+"");
                        if (response.isSuccessful()){
                            String bookingId = response.body().getBooking_id();
                            String message = response.body().getMess();
                            Log.d("Booking code", bookingId);
                            Log.d("Mess", message);
                            if (bookingId != null){
                                Intent intent = new Intent(context, RoomBookingHistoryActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putString("user_id", userID);
                                bundle.putString("userName", userName);
                                intent.putExtras(bundle);
                                context.startActivity(intent);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<RoomBookingResponse> call, Throwable throwable) {
                        Log.d("onFailure", throwable.toString());
                    }
                });
            }
        });
    }

    private void anhxa(){
        roomName = findViewById(R.id.room_name_confirm);
        roomCapable = findViewById(R.id.room_capable_confirm);
        roomDevices = findViewById(R.id.room_device_confirm);

        startDate = findViewById(R.id.room_start_day);
        endDate = findViewById(R.id.room_end_day);
        startTime = findViewById(R.id.room_start_time);
        endTime = findViewById(R.id.room_end_time);

        reason_txt = findViewById(R.id.booking_reason_txt);
        amount_txt = findViewById(R.id.booking_amount_txt);

        save_btn = findViewById(R.id.save_booking_btn);
    }
}