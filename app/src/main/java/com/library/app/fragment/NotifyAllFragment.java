package com.library.app.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.library.app.R;
import com.library.app.adapter.NotifyAdapter;
import com.library.app.model.ThongBao;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotifyAllFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotifyAllFragment extends Fragment {

    private NotifyAdapter notifyAdapter;
    private View view;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_notify_all, container, false);
        recyclerView = view.findViewById(R.id.rcyc_notify_all);
        getThongBao();
        // Inflate the layout for this fragment
        return view;
    }

    private void getThongBao(){
        ArrayList<ThongBao> thongBaos = new ArrayList<>();
        thongBaos.add(new ThongBao(1,"Thông báo 1", "Thông báo đầu tiên",0));
        thongBaos.add(new ThongBao(2,"Thông báo 2", "Thông báo đầu tiên 2",0));
        thongBaos.add(new ThongBao(3,"Thông báo 3", "Thông báo đầu tiên 3",0));
        thongBaos.add(new ThongBao(4,"Thông báo 4", "Thông báo đầu tiên 4",0));
        notifyAdapter = new NotifyAdapter(thongBaos,getActivity());
        recyclerView.setAdapter(notifyAdapter);
        linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        RecyclerView.ItemDecoration divider = new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(divider);

    }
}