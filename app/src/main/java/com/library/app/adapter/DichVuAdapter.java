package com.library.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.library.app.R;
import com.library.app.model.DichVu;

import java.util.ArrayList;

public class DichVuAdapter extends RecyclerView.Adapter<DichVuAdapter.DichVuViewHolder>{

    private ArrayList<DichVu> dichVus;
    private Context context;

    public DichVuAdapter(ArrayList<DichVu> dichVus, Context context) {
        this.dichVus = dichVus;
        this.context = context;
    }

    @NonNull
    @Override
    public DichVuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu_home,parent,false);
        return new DichVuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DichVuViewHolder holder, int position) {
        DichVu  dichVu = dichVus.get(position);
        if(dichVu==null){
            return;
        }

//        Picasso.get()
//                .load(playlist.getHinh())
//                .into(holder.img);
        holder.imageView.setImageDrawable(dichVu.getAnhDichVu());
        holder.textView.setText(dichVu.getTenDichVu());
    }

    @Override
    public int getItemCount() {
        if(dichVus!=null){
            return dichVus.size();
        }
        return 0;
    }

    public class DichVuViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textView;
        public DichVuViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView =itemView.findViewById(R.id.img_menu);
            textView = itemView.findViewById(R.id.title_menu);
        }
    }
}
