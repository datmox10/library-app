package com.library.app.adapter;

import static com.library.app.R.color.opacoty;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.library.app.R;
import com.library.app.model.ThongBao;

import java.util.ArrayList;

public class NotifyAdapter extends RecyclerView.Adapter<NotifyAdapter.NotifyViewHolder> {

    private ArrayList<ThongBao> thongBaoArrayList;
    private Context context;

    public NotifyAdapter(ArrayList<ThongBao> thongBaoArrayList, Context context) {
        this.thongBaoArrayList = thongBaoArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public NotifyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notify,parent,false);
        return new NotifyViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull NotifyViewHolder holder, int position) {
        ThongBao thongBao = thongBaoArrayList.get(position);
        if(thongBao==null){
            return;
        }

        holder.title.setText(thongBao.getTieuDe());
        holder.contain.setText(thongBao.getNoiDung());

        if(thongBao.getTrangThai() == 0){
            holder.relativeLayout.setBackgroundResource(R.drawable.bg_notify);
        }
    }

    @Override
    public int getItemCount() {
        if(thongBaoArrayList!=null){
            return thongBaoArrayList.size();
        }
        return 0;
    }

    public class NotifyViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView title, contain;

        private RelativeLayout relativeLayout;


        public NotifyViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.item_notify_img);
            title = itemView.findViewById(R.id.item_notify_title);
            contain = itemView.findViewById(R.id.item_notify_contain);
            relativeLayout = itemView.findViewById(R.id.item_notify_bg);
        }
    }
}
