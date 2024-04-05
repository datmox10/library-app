package com.library.app.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.library.app.R;
import com.library.app.activity.BookingActivity;
import com.library.app.activity.CategoryByClassify;
import com.library.app.activity.FavouriteActivity;
import com.library.app.activity.LoginActivity;
import com.library.app.activity.RoomBookingActivity;

public class UserFragment extends Fragment {
    private View view;
    private TextView user_name;
    private static final String USER_LOGIN = "USER_LOGIN";
    private LinearLayout userBookRead,user_logout,user_borrow,user_favorite,user_booking,user_print;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_user, container, false);

        anhXa();
        getUser();
        init();
        return view;
    }

    private void getUser(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("USER_LOGIN", Context.MODE_PRIVATE);
        user_name.setText(sharedPreferences.getString("name",""));
    }
    private void anhXa(){
        userBookRead = view.findViewById(R.id.user_book_read);
        user_logout = view.findViewById(R.id.user_logout);
        user_name = view.findViewById(R.id.user_name);
        user_borrow = view.findViewById(R.id.user_borrow);
        user_favorite = view.findViewById(R.id.user_favorite);
        user_booking = view.findViewById(R.id.user_booking);
        user_print = view.findViewById(R.id.user_print);
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
                startActivity(CategoryByClassify.class);
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
                startActivity(BookingActivity.class);
            }
        });
    }
    private void startActivity(Class<?> cls){
        Intent intent = new Intent(getActivity(),cls);
        startActivity(intent);
        getActivity().finish();
    }

}