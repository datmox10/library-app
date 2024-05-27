package com.library.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.library.app.R;
import com.library.app.activity.BookingConfirmActivity;
import com.library.app.activity.RoomBookingDetailActivity;
import com.library.app.dto.RoomBookingResponse;
import com.library.app.dto.RoomsBookedHistoryResponse;
import com.library.app.model.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RoomHistoryAdapter extends RecyclerView.Adapter<RoomHistoryAdapter.RoomViewHolder>{
    List<RoomsBookedHistoryResponse.RoomBookResponse> listRoomBooked;

    String user_name;
    Context context;

    public RoomHistoryAdapter(Set<RoomsBookedHistoryResponse.RoomBookResponse> listRoomBooked, Context context, String userName) {
        this.listRoomBooked = new ArrayList<>(listRoomBooked);
        Log.d("Set count:", listRoomBooked.size()+"");
        Log.d("List count:", this.listRoomBooked.size()+"");
        this.context = context;
        this.user_name = userName;
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_row_history,parent,false);
        return new RoomHistoryAdapter.RoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, int position) {
        RoomsBookedHistoryResponse.RoomBookResponse room = listRoomBooked.get(position);
        if(room == null){
            return;
        }
        if (room.getStatus().equals("REJECT")){
            return;
        }

        RoomsBookedHistoryResponse.RoomBookDetailResponse firstRoom = room.getRoomBookDetailResponse().stream().findFirst().get();
        holder.roomCode.setText(firstRoom.getRoomResponse().getRoomCode());
        holder.bookedDate.setText(room.getDateBook());
        holder.bookedUser.setText(user_name);
        holder.roomBookingID = firstRoom.getRoomBookingId();
    }

    @Override
    public int getItemCount() {
        return listRoomBooked.size();
    }

    public class RoomViewHolder extends RecyclerView.ViewHolder {
        TextView roomCode;
        TextView bookedDate;
        TextView bookedUser;

        String roomBookingID;
        public RoomViewHolder(@NonNull View itemView) {
            super(itemView);

            roomCode = itemView.findViewById(R.id.room_name_item);
            bookedDate = itemView.findViewById(R.id.room_time_item);
            bookedUser = itemView.findViewById(R.id.room_info_item);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, RoomBookingDetailActivity.class);
                    Bundle bundle = new Bundle();

                    bundle.putString("booking_id", roomBookingID);
                    bundle.putString("roomCode", roomCode.getText().toString());
                    bundle.putString("userName", user_name);

                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });
        }



    }
}
