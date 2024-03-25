package com.library.app.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.library.app.R;
import com.library.app.activity.BookingActivity;
import com.library.app.activity.BorrowHistoryActivity;
import com.library.app.activity.MainActivity;
import com.library.app.activity.ThesisActivity;
import com.library.app.activity.UserDetails;
import com.library.app.databinding.FragmentUserBinding;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserFragment extends Fragment {
    private View view;

    private LinearLayout userBookRead;
    private LinearLayout userPrint;
    private LinearLayout userBorrowHistory;
    private LinearLayout userBooking;
    private CircleImageView circleImageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_user, container, false);

        anhXa();
        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(getActivity(), UserDetails.class);
                startActivity(intent);
            }
        });

        userPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(getActivity(), ThesisActivity.class);
                startActivity(intent);
            }
        });

        userBorrowHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(getActivity(), BorrowHistoryActivity.class);
                startActivity(intent);
            }
        });

        userBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(getActivity(), BookingActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private void anhXa(){
        userBookRead = view.findViewById(R.id.user_book_read);
        circleImageView = view.findViewById(R.id.user_image);
        userPrint = view.findViewById(R.id.user_print);
        userBorrowHistory = view.findViewById(R.id.user_borrow);
        userBooking = view.findViewById(R.id.user_booking);
    }



}