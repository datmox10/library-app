package com.library.app.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.library.app.R;
import com.library.app.activity.BookDetailActivity;
import com.library.app.activity.BookingConfirmActivity;
import com.library.app.model.Book;
import com.library.app.model.Room;

import java.util.ArrayList;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomViewHolder>{

    private ArrayList<Room> rooms;
    private Context context;

    public RoomAdapter(ArrayList<Room> rooms, Context context){
        this.rooms = rooms;
        this.context = context;
    }
    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_room,parent,false);
        return new RoomAdapter.RoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, int position) {
        Room room = rooms.get(position);
        if(room == null){
            return;
        }

        holder.room_name.setText(room.getRoomCode());

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class RoomViewHolder extends RecyclerView.ViewHolder{

        private TextView room_name;
        private LinearLayout room_capable;
        private LinearLayout device_container;
        private Button placeRoom_button;
        public RoomViewHolder(@NonNull View itemView) {
            super(itemView);

            room_name = itemView.findViewById(R.id.room_name);
            room_capable = itemView.findViewById(R.id.room_container);
            device_container = itemView.findViewById(R.id.device_container);

            placeRoom_button = itemView.findViewById(R.id.place_room_button);

            placeRoom_button.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, BookingConfirmActivity.class);
                    Bundle bundle = new Bundle();
                }
            });
        }
    }
}
