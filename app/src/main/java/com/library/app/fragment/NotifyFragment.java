package com.library.app.fragment;

import static com.library.app.R.color.bg_color;
import static com.library.app.R.color.btn_notclick;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.library.app.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotifyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotifyFragment extends Fragment {

    private Button btnAll, btnReaded;
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_notify, container, false);
        init();
        anhXa();
        setBackBtn();
        return view;
    }

    private void init(){

        replaceFragment(new NotifyAllFragment());
    }

    private void setBackBtn(){
        btnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new NotifyAllFragment());
                btnAll.setBackgroundTintList(ContextCompat.getColorStateList(getActivity(), bg_color));
                btnReaded.setBackgroundTintList(ContextCompat.getColorStateList(getActivity(), btn_notclick));
            }
        });

        btnReaded.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new NotifyReadedFragment());
                btnAll.setBackgroundTintList(ContextCompat.getColorStateList(getActivity(), btn_notclick));
                btnReaded.setBackgroundTintList(ContextCompat.getColorStateList(getActivity(), bg_color));
            }
        });
    }
    private void anhXa(){
        btnAll = view.findViewById(R.id.btn_all_notify);
        btnReaded = view.findViewById(R.id.btn_read_notify);
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout_notify,fragment);
        fragmentTransaction.commit();
    }

}