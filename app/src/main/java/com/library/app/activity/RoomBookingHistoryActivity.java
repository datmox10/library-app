package com.library.app.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.library.app.R;
import com.library.app.adapter.RoomAdapter;
import com.library.app.adapter.RoomHistoryAdapter;
import com.library.app.api.ApiRoomClass;
import com.library.app.dto.RoomsBookedHistoryResponse;
import com.library.app.model.Room;
import com.library.app.model.TokenManager;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.library.app.adapter.RoomAdapter;
import com.library.app.adapter.RoomHistoryAdapter;
import com.library.app.api.ApiRoomClass;
import com.library.app.api.ApiUser;
import com.library.app.dto.RoomsBookedHistoryResponse;
import com.library.app.dto.UserInfoResponse;
import com.library.app.model.Room;
import com.library.app.model.TokenManager;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RoomBookingHistoryActivity extends AppCompatActivity {

    private RecyclerView recyclerListRoom;
    private RoomHistoryAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private Context actContext;
    private Button backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_room_booking_history);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        actContext = this;
        backButton = findViewById(R.id.back_btn);

        linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL,false);
        recyclerListRoom = findViewById(R.id.room_history);
        recyclerListRoom.setLayoutManager(linearLayoutManager);
        Set<RoomsBookedHistoryResponse.RoomBookResponse> rooms = new HashSet<>();
        adapter = new RoomHistoryAdapter(rooms, getApplicationContext(), null);
        recyclerListRoom.setAdapter(adapter);


        ApiUser apiUser = new ApiUser(TokenManager.getInstance(this).getToken());
        ApiUser.ApiUserInterface apiUserInterface = apiUser.getRetrofitInstance().create(ApiUser.ApiUserInterface.class);
        Call<UserInfoResponse> call = apiUserInterface.getUser();
        call.enqueue(new Callback<UserInfoResponse>(){
            @Override
            public void onResponse(Call<UserInfoResponse> call, Response<UserInfoResponse> response) {
                Log.d( "onResponse: ",response.code()+"");
                Log.d( "userName: ",response.body().getFullName());
                String userID = response.body().getId();
                String userName = response.body().getFullName();
                getData(userID, userName);
            }

            @Override
            public void onFailure(Call<UserInfoResponse> call, Throwable throwable) {
                Log.d( "onFailure: ","Lỗi");
            }
        });

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            String userID = bundle.getString("user_id");
            String userName = bundle.getString("userName");
            getData(userID, userName);
        }


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(actContext, MainActivity.class);
                actContext.startActivity(intent);
            }
        });
    }

    private void getData(String userID, String userName){
        ApiRoomClass apiRoomClass = new ApiRoomClass(TokenManager.getInstance(this).getToken());
        ApiRoomClass.ApiRoom apiRoom = apiRoomClass.getRetrofitInstance().create(ApiRoomClass.ApiRoom.class);
        Call<RoomsBookedHistoryResponse> call = apiRoom.getBookingHistory(userID);
        call.enqueue(
                new Callback<RoomsBookedHistoryResponse>() {
                    @Override
                    public void onResponse(Call<RoomsBookedHistoryResponse> call, Response<RoomsBookedHistoryResponse> response) {
                        if(response.isSuccessful()){
                            adapter = new RoomHistoryAdapter(response.body().getRoomBookResponses(), actContext, userName);
                            recyclerListRoom.setAdapter(adapter);
                        }else{
                            Log.d( "onResponse: ", response.code()+"");
                        }
                    }

                    @Override
                    public void onFailure(Call<RoomsBookedHistoryResponse> call, Throwable throwable) {
                        Log.d("onFailure: ",throwable.toString());
                    }
                }
        );
    }
}