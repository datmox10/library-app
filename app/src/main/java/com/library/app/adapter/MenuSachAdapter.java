package com.library.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.library.app.R;
import com.library.app.activity.BookDetailActivity;
import com.library.app.model.Sach;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;

public class MenuSachAdapter extends RecyclerView.Adapter<MenuSachAdapter.MenuSachViewHolder> {
    private ArrayList<Sach> listSach;
    private Context context;

    public MenuSachAdapter(ArrayList<Sach> listSach, Context context) {
        this.listSach = listSach;
        this.context = context;
    }

    @NonNull
    @Override
    public MenuSachViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu_newbook,parent,false);
        return new MenuSachViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuSachViewHolder holder, int position) {
        Sach sach = listSach.get(position);
        if(sach==null){
            return;
        }

//        holder.imageView.setBackground(sach.getAnh());

        holder.title.setText(sach.getNhanDeChinh());
        Picasso.get()
                .load(sach.getAnh())
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        if(listSach!=null){
            return listSach.size();
        }
        return 0;
    }

    public class MenuSachViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView title;
        public MenuSachViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.img_book);
            title = itemView.findViewById(R.id.title_book);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,BookDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("id",listSach.get(getPosition()).getSoISBN());
                    bundle.putString("ten",listSach.get(getPosition()).getNhanDeChinh());
                    bundle.putString("anh", listSach.get(getPosition()).getAnh().toString());
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });

        }
    }

}
