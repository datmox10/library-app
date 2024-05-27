package com.library.app.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.library.app.R;
import com.library.app.activity.BookingActivity;
import com.library.app.activity.BorrowHistoryActivity;
import com.library.app.activity.FavouriteActivity;
import com.library.app.activity.LoginActivity;
import com.library.app.activity.RoomBookingHistoryActivity;
import com.library.app.api.ApiBook;
import com.library.app.model.TokenManager;
import com.library.app.model.UserMD;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserFragment extends Fragment {
    private View view;
    private TextView user_name,user_masinhvien;
    private TokenManager tokenManager;
    private LinearLayout userBookRead,user_logout,user_borrow,user_favorite,user_booking,user_print;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_user, container, false);
        tokenManager = TokenManager.getInstance(getActivity());
        anhXa();
        getUser();
        init();
        return view;
    }

    private void getUser(){
        ApiBook apiBook = new ApiBook(tokenManager.getToken());
        ApiBook.MyApi myApi = apiBook.getRetrofitInstance().create(ApiBook.MyApi.class);
        Call<UserMD> user = myApi.getUser();
        user.enqueue(new Callback<UserMD>() {
            @Override
            public void onResponse(Call<UserMD> call, Response<UserMD> response) {
                if(response.isSuccessful()){
                    Log.d("onResponse: ",response.body().getUserName());
                    user_name.setText(response.body().getFullName());
                    user_masinhvien.setText(response.body().getEmail());
                }else{
                    Log.d("onResponse: ",response.code()+"");
                }

            }

            @Override
            public void onFailure(Call<UserMD> call, Throwable throwable) {
                Log.d("onResponse: ",throwable.toString());
            }
        });

    }
    private void anhXa(){
        userBookRead = view.findViewById(R.id.user_book_read);
        user_logout = view.findViewById(R.id.user_logout);
        user_name = view.findViewById(R.id.user_name);
        user_borrow = view.findViewById(R.id.user_borrow);
        user_favorite = view.findViewById(R.id.user_favorite);
        user_booking = view.findViewById(R.id.user_booking);
        user_print = view.findViewById(R.id.user_print);
        user_masinhvien = view.findViewById(R.id.user_masinhvien);
    }

    private void init(){
        user_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("USER_LOGIN", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();

                startActivity(LoginActivity.class);
            }
        });

        user_borrow.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(BorrowHistoryActivity.class);
            }
        }));
        user_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(FavouriteActivity.class);
            }
        });
        user_booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(RoomBookingHistoryActivity.class);
            }
        });
    }
    private void startActivity(Class<?> cls){
        Intent intent = new Intent(getActivity(),cls);
        startActivity(intent);
        getActivity().finish();
    }

}