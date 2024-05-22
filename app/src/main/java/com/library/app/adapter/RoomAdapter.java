package com.library.app.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.library.app.model.Book;
import com.library.app.model.Room;

import java.util.ArrayList;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomViewHolder>{

    private ArrayList<Room> rooms;
    private Context context;

    public  RoomAdapter(ArrayList<Room> rooms, Context context){
        this.rooms = rooms;
        this.context = context;
    }
    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class RoomViewHolder extends RecyclerView.ViewHolder{

        public RoomViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
