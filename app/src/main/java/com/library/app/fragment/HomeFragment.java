package com.library.app.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import com.library.app.R;
import com.library.app.adapter.DichVuAdapter;
import com.library.app.adapter.MenuSachAdapter;
import com.library.app.model.DichVu;
import com.library.app.model.Sach;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    private View view;
    private RecyclerView recyclerMenu, recyclerNewBook, recyclerHotBook;

    private DichVuAdapter dichVuAdapter;
    private MenuSachAdapter menuSachAdapter;
    private Toolbar toolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        AnhXa();
        getDichVu();
        getNewBook();
        getHotBook();

        return view;
    }

    private void AnhXa(){
        recyclerMenu = view.findViewById(R.id.recycler_menu);
        recyclerNewBook = view.findViewById(R.id.recycler_new_book);
        recyclerHotBook = view.findViewById(R.id.recycler_hot_book);
    }

    private void getDichVu(){
        ArrayList<DichVu> dichVus = new ArrayList<>();
        dichVus.add(new DichVu("Đặt phòng", getResources().getDrawable(R.drawable.datphong)));
        dichVus.add(new DichVu("Yêu thích",getResources().getDrawable(R.drawable.yeuthich)));
        dichVus.add(new DichVu("Khóa luận",getResources().getDrawable(R.drawable.khoaluan)));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false);
        recyclerMenu.setLayoutManager(linearLayoutManager);

        dichVuAdapter = new DichVuAdapter(dichVus,getActivity());
        recyclerMenu.setAdapter(dichVuAdapter);
    }
    private void getNewBook(){
        ArrayList<Sach> sachs = new ArrayList<>();
        Sach sach1 = new Sach();
        sach1.setSoISBN("1");
        sach1.setNhanDeChinh("Advanced C");
        sach1.setAnh("R.drawable.newbook");
        Sach sach2 = new Sach();
        sach2.setSoISBN("2");
        sach2.setNhanDeChinh("Advanced D");
        sach2.setAnh("R.drawable.newbook");
        sachs.add(sach1);
        sachs.add(sach2);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false);
        recyclerNewBook.setLayoutManager(linearLayoutManager);

        menuSachAdapter = new MenuSachAdapter(sachs,getActivity());
        recyclerNewBook.setAdapter(menuSachAdapter);
    }

    private void getHotBook(){
        ArrayList<Sach> hotSach = new ArrayList<>();
        Sach sach3 = new Sach();
        sach3.setSoISBN("3");
        sach3.setNhanDeChinh("Advanced C");
        sach3.setAnh("R.drawable.newbook");
        Sach sach4 = new Sach();
        sach4.setSoISBN("4");
        sach4.setNhanDeChinh("Advanced C");
        sach4.setAnh("R.drawable.newbook");
        hotSach.add(sach3);
        hotSach.add(sach4);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false);
        recyclerHotBook.setLayoutManager(linearLayoutManager);

        menuSachAdapter = new MenuSachAdapter(hotSach,getActivity());
        recyclerHotBook.setAdapter(menuSachAdapter);
    }
}