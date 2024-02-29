package com.library.app.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.library.app.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QrcodeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QrcodeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_qrcode, container, false);
    }
}