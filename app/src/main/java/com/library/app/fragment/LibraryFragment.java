package com.library.app.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.library.app.R;
import com.library.app.adapter.ClassifyAdapter;
import com.library.app.model.PhanLoai;
import com.library.app.model.Sach;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LibraryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LibraryFragment extends Fragment {

    private View view;
    private RecyclerView rcycLibrary;

    private ClassifyAdapter classifyAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_library, container, false);
        AnhXa();
        setClassifyAdapter();
        return view;
    }
    private void AnhXa(){
        rcycLibrary = view.findViewById(R.id.rcyc_library);
    }

    private void setClassifyAdapter(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        rcycLibrary.setLayoutManager(linearLayoutManager);

        classifyAdapter = new ClassifyAdapter(phanLoais(),getActivity());
        rcycLibrary.setAdapter(classifyAdapter);
    }

    private ArrayList<PhanLoai> phanLoais(){
        ArrayList<PhanLoai> phanLoais = new ArrayList<>();
        ArrayList<Sach> sachArrayList = new ArrayList<>();

        phanLoais.add(new PhanLoai("loai1","Sách văn học", sachArrayList));
        phanLoais.add(new PhanLoai("loai2","Sách anh học", sachArrayList));
        phanLoais.add(new PhanLoai("loai3","Sách toán học", sachArrayList));
        phanLoais.add(new PhanLoai("loai4","Sách Địa học", sachArrayList));

        Sach sach3 = new Sach();
        sach3.setSoISBN("3");
        sach3.setNhanDeChinh("Advanced C");
        sach3.setAnh("R.drawable.newbook");
        Sach sach4 = new Sach();
        sach4.setSoISBN("4");
        sach4.setNhanDeChinh("Advanced C");
        sach4.setAnh("R.drawable.newbook");
        sachArrayList.add(sach3);
        sachArrayList.add(sach4);

        return phanLoais;
    }


}