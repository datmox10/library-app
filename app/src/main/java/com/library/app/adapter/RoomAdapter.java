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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.library.app.R;
import com.library.app.activity.BookDetailActivity;
import com.library.app.activity.BookingConfirmActivity;
import com.library.app.model.Book;
import com.library.app.model.Room;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomViewHolder>{
    private List<Room> rooms;
    private Context context;
    private Date pickedDate;
    public RoomAdapter(List<Room> rooms, Context context, Date date){
        Log.d( "onCreateAdapter: ", "Adapter created!");

        this.rooms = rooms;
        this.context = context;
        this.pickedDate = date;

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
        holder.room_capable.setText(room.getQuantityAllowed());
        String devices = "";
        for (int i = 0; i < room.getEquipment().size(); i++){
            devices += room.getEquipment().get(i) +" ";
        }
        holder.device_container.setText(devices);

        Log.d( "timeFrame: ", room.getRoomTimeFrameStatus().get(0).getStatus()+"");
        List<Room.RoomTimeFrameResponse> listTF_1 = new ArrayList<>();
        List<Room.RoomTimeFrameResponse> listTF_2 = new ArrayList<>();
        for (int i = 0; i < room.getRoomTimeFrameStatus().size(); i++){
            if (i < 6){
                listTF_1.add(room.getRoomTimeFrameStatus().get(i));
            }else{
                listTF_2.add(room.getRoomTimeFrameStatus().get(i));
            }
        }

        TimeFrameAdapter adapter1 = new TimeFrameAdapter(listTF_1, context, holder);
        TimeFrameAdapter adapter2 = new TimeFrameAdapter(listTF_2, context, holder);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(context, RecyclerView.HORIZONTAL,false);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(context, RecyclerView.HORIZONTAL,false);
        holder.listTimeFrame1.setLayoutManager(linearLayoutManager1);
        holder.listTimeFrame1.setAdapter(adapter1);
        holder.listTimeFrame2.setLayoutManager(linearLayoutManager2);
        holder.listTimeFrame2.setAdapter(adapter2);
    }

    @Override
    public int getItemCount() {
        return rooms.size();
    }

    public class RoomViewHolder extends RecyclerView.ViewHolder{

        private TextView room_name;
        private TextView room_capable;
        private TextView device_container;
        private RecyclerView listTimeFrame1, listTimeFrame2;
        private Button placeRoom_button;
        public List<Room.RoomTimeFrameResponse> selectedTimeFrames = new ArrayList<>();

        public RoomViewHolder(@NonNull View itemView) {
            super(itemView);

            room_name = itemView.findViewById(R.id.room_name);
            room_capable = itemView.findViewById(R.id.room_capable);
            device_container = itemView.findViewById(R.id.device_list);

            listTimeFrame1 = itemView.findViewById(R.id.listTimeFrame_1);
            listTimeFrame2 = itemView.findViewById(R.id.listTimeFrame_2);

            placeRoom_button = itemView.findViewById(R.id.place_room_button);


            placeRoom_button.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if (selectedTimeFrames.isEmpty()) return;

                    ArrayList<String> timeFrames = new ArrayList<>();
                    ArrayList<String> startTimes = new ArrayList<>();
                    ArrayList<String> endTimes = new ArrayList<>();
                    for(int i = 0; i < selectedTimeFrames.size(); i++){
                        timeFrames.add(selectedTimeFrames.get(i).getTimeFrame());
                        startTimes.add(selectedTimeFrames.get(i).getStartTime());
                        endTimes.add(selectedTimeFrames.get(i).getEndTime());
                    }

                    Intent intent = new Intent(context, BookingConfirmActivity.class);
                    Bundle bundle = new Bundle();

                    bundle.putString("roomCode", room_name.getText().toString());
                    bundle.putString("roomCapable", room_capable.getText().toString());
                    bundle.putString("roomDevice", device_container.getText().toString());
                    bundle.putString("date", pickedDate.toString());

                    bundle.putStringArrayList("listTimeFrame", timeFrames);
                    bundle.putStringArrayList("listStartTime", startTimes);
                    bundle.putStringArrayList("listEndTime", endTimes);

                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });
        }
    }
}
