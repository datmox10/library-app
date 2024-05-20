package com.library.app.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.library.app.R;
import com.library.app.activity.CategoryByClassify;
import com.library.app.model.PhanLoai;

import java.util.ArrayList;

public class ClassifyAdapter extends RecyclerView.Adapter<ClassifyAdapter.ClassifyViewHolder> {

    private ArrayList<PhanLoai> phanLoais;
    private Context context;

    private MenuSachAdapter menuSachAdapter;

    public ClassifyAdapter(ArrayList<PhanLoai> phanLoais, Context context) {
        this.phanLoais = phanLoais;
        this.context = context;
    }

    @NonNull
    @Override
    public ClassifyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_classify,parent,false);
        return new ClassifyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassifyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        PhanLoai phanLoai = phanLoais.get(position);
        if(phanLoai==null){
            return;
        }

        holder.title.setText(phanLoai.getTenLoai());
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);
        holder.rcycClassify.setLayoutManager(linearLayoutManager);
        menuSachAdapter = new MenuSachAdapter(phanLoai.getSachList(),context);
        holder.rcycClassify.setAdapter(menuSachAdapter);

        holder.btnClassify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CategoryByClassify.class);
                intent.putExtra("name", phanLoais.get(position).getTenLoai());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        if(phanLoais!=null){
            return phanLoais.size();
        }
        return 0;
    }

    public class ClassifyViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private RecyclerView rcycClassify;
        private Button btnClassify;
        public ClassifyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_item_classify);
            rcycClassify = itemView.findViewById(R.id.rcyc_item_classify);
            btnClassify = itemView.findViewById(R.id.btn_item_classify);
        }
    }

}
