package com.library.app.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.library.app.R;

public class UserFragment extends Fragment {
    private View view;

    private LinearLayout userBookRead;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_user, container, false);

        anhXa(); 
        return view;
    }

    private void anhXa(){
        userBookRead = view.findViewById(R.id.user_book_read);
    }



}