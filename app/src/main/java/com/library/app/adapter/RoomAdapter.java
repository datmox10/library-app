package com.library.app.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomViewHolder>{
    private List<Room> rooms;
    private Context context;

    public RoomAdapter(List<Room> rooms, Context context){
        Log.d( "onCreateAdapter: ", "Adapter created!");

        this.rooms = rooms;
        this.context = context;
        Log.d( "this context: ", this.context+"");

    }
    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d( "onCreatViewHolder: ", "viewHolder created!");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_room,parent,false);
        return new RoomAdapter.RoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, int position) {
        Room room = rooms.get(position);
        if(room == null){
            return;
        }
        Log.d( "onBindViewHolder: ", "viewHolder binded!");

        holder.room_name.setText(room.getRoomCode());
        holder.room_capable.setText(room.getQuantityAllowed());
        String devices = "";
        for (int i = 0; i < room.getEquipment().size(); i++){
            devices += room.getEquipment().get(i) +" ";
        }
        holder.device_container.setText(devices);
    }

    @Override
    public int getItemCount() {
        return rooms.size();
    }

    public class RoomViewHolder extends RecyclerView.ViewHolder{

        private TextView room_name;
        private TextView room_capable;
        private TextView device_container;
        private Button placeRoom_button;
        public RoomViewHolder(@NonNull View itemView) {
            super(itemView);

            room_name = itemView.findViewById(R.id.room_name);
            room_capable = itemView.findViewById(R.id.room_capable);
            device_container = itemView.findViewById(R.id.device_list);

            placeRoom_button = itemView.findViewById(R.id.place_room_button);

            placeRoom_button.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, BookingConfirmActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("roomCode", room_name.getText().toString());
                    bundle.putString("roomCapable", room_capable.getText().toString());
                    bundle.putString("roomDevice", device_container.getText().toString());
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });
        }
    }
}
