package com.library.app.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.library.app.R;
import com.library.app.api.ApiRoomClass;
import com.library.app.api.ApiUser;
import com.library.app.dto.RoomBookingCancelResponse;
import com.library.app.dto.RoomBookingResponse;
import com.library.app.dto.RoomDetailResponse;
import com.library.app.dto.UserInfoResponse;
import com.library.app.model.RoomBooking;
import com.library.app.model.RoomBookingDetail;
import com.library.app.model.TokenManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RoomBookingDetailActivity extends AppCompatActivity {

    private TextView roomCode;
    private TextView roomCapable;
    private TextView bookingDateStart, bookingDateEnd;
    private TextView bookingTimeStart, bookingTimeEnd;
    private TextView userName;
    private TextView bookingTimestamp;
    private Button cancelButton, backButton;

    private String userID;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_room_booking_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        context = this;

        ApiUser apiUser = new ApiUser(TokenManager.getInstance(this).getToken());
        ApiUser.ApiUserInterface apiUserInterface = apiUser.getRetrofitInstance().create(ApiUser.ApiUserInterface.class);
        Call<UserInfoResponse> call = apiUserInterface.getUser();
        call.enqueue(new Callback<UserInfoResponse>(){
            @Override
            public void onResponse(Call<UserInfoResponse> call, Response<UserInfoResponse> response) {
                Log.d( "onResponse: ",response.code()+"");
                Log.d( "userName: ",response.body().getFullName());
                userID = response.body().getId();
            }

            @Override
            public void onFailure(Call<UserInfoResponse> call, Throwable throwable) {
                Log.d( "onFailure: ","Lỗi");
            }
        });

        Bundle bundle = getIntent().getExtras();
        anhxa();

        if (bundle != null){
            String booking_id = bundle.getString("booking_id");
            ApiRoomClass apiRoomClass = new ApiRoomClass(TokenManager.getInstance(context).getToken());
            ApiRoomClass.ApiRoom apiRoom = apiRoomClass.getRetrofitInstance().create(ApiRoomClass.ApiRoom.class);
            Call<RoomDetailResponse> call2 = apiRoom.getDetail(booking_id);
            call2.enqueue(new Callback<RoomDetailResponse>() {
                @Override
                public void onResponse(Call<RoomDetailResponse> call2, Response<RoomDetailResponse> response) {
                    Log.d("OnResponse", response.code()+"");
                    if (response.isSuccessful()){
                        RoomBooking roomBooking = response.body().getRoom_booking();
                        //List<RoomBookingDetail> listRoomDetail = response.body().getList_room_detail();

                        roomCode.setText("Tên phòng: " + bundle.getString("roomCode"));
                        roomCapable.setText("Số lượng: " + roomBooking.getQuantity());

                        bookingDateStart.setText(roomBooking.getDateBook().toString());
                        bookingDateEnd.setText(roomBooking.getDateBook().toString());

                        userName.setText(bundle.getString("userName"));
                        bookingTimestamp.setText(roomBooking.getCreatedDate()+"");
                    }
                }

                @Override
                public void onFailure(Call<RoomDetailResponse> call2, Throwable throwable) {
                    Log.d("onFailure", throwable.toString());
                }
            });

            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ApiRoomClass apiRoomClass = new ApiRoomClass(TokenManager.getInstance(context).getToken());
                    ApiRoomClass.ApiRoom apiRoom = apiRoomClass.getRetrofitInstance().create(ApiRoomClass.ApiRoom.class);
                    Call<RoomBookingCancelResponse> call = apiRoom.cancelBooking(booking_id);
                    call.enqueue(new Callback<RoomBookingCancelResponse>(){
                        @Override
                        public void onResponse(Call<RoomBookingCancelResponse> call, Response<RoomBookingCancelResponse> response) {
                            if (response.isSuccessful()){
                                Toast.makeText(context, "Hủy phòng thành công!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(context, RoomBookingHistoryActivity.class);
                                Bundle bundle1 = new Bundle();
                                bundle1.putString("user_id", userID);
                                bundle1.putString("userName", userName.getText().toString());
                                intent.putExtras(bundle1);
                                context.startActivity(intent);
                            }else{
                                Log.d("onResponse:", response.code()+"");
                            }
                        }

                        @Override
                        public void onFailure(Call<RoomBookingCancelResponse> call, Throwable throwable) {
                            Log.d("onFailure:", throwable.toString());

                        }
                    });
                }
            });

            backButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, RoomBookingHistoryActivity.class);
                    Bundle bundle1 = new Bundle();
                    bundle1.putString("user_id", userID);
                    bundle1.putString("userName", userName.getText().toString());
                    intent.putExtras(bundle1);
                    context.startActivity(intent);
                }
            });
        }

    }

    private void anhxa(){
        roomCode = findViewById(R.id.roomCode_detail);
        roomCapable = findViewById(R.id.roomCapable_detail);

        bookingDateStart = findViewById(R.id.bookingDateStart_detail);
        bookingDateEnd = findViewById(R.id.bookingDateEnd_detail);
        bookingTimeStart = findViewById(R.id.bookingTime_start_detail);
        bookingTimeEnd = findViewById(R.id.bookingTime_end_detail);

        userName = findViewById(R.id.userName_detail);
        bookingTimestamp = findViewById(R.id.bookingTime);

        cancelButton = findViewById(R.id.cancelRoom_btn);
        backButton = findViewById(R.id.back_to_history_btn);
    }
}