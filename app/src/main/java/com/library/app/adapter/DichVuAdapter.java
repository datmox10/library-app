package com.library.app.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.library.app.R;
import com.library.app.activity.AIBook;
import com.library.app.activity.BookingActivity;
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
        holder.imageView.setImageDrawable(dichVu.getAnhDichVu());
        holder.textView.setText(dichVu.getTenDichVu());
            holder.relativeLayout.setOnClickListener(v -> {
                if(dichVu.getId() == 1){
                    startActivity(BookingActivity.class);
                }
                if(dichVu.getId() == 4){
                    startActivity(AIBook.class);
                }
            });
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
        private RelativeLayout relativeLayout;
        public DichVuViewHolder(@NonNull View itemView) {
            super(itemView);
            relativeLayout = itemView.findViewById(R.id.layout_dv);
            imageView =itemView.findViewById(R.id.img_menu);
            textView = itemView.findViewById(R.id.title_menu);
        }
    }
    private void startActivity(Class<?> cls){
        Intent intent = new Intent(context,cls);
        context.startActivity(intent);
        ((Activity)context).finish();
    }
}
