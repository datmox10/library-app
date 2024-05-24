package com.library.app.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.library.app.R;
import com.library.app.model.Room;

import java.util.List;

public class TimeFrameAdapter extends RecyclerView.Adapter<TimeFrameAdapter.TimeFrameViewHolder>{
    private List<Room.RoomTimeFrameResponse> listFrame;
    private Context context;
    public RoomAdapter.RoomViewHolder parentViewHolder;
    public TimeFrameAdapter(List<Room.RoomTimeFrameResponse> listTimeFrame, Context context, RoomAdapter.RoomViewHolder viewHolder){
        this.listFrame = listTimeFrame;
        this.context = context;
        this.parentViewHolder = viewHolder;
    }
    @NonNull
    @Override
    public TimeFrameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_timeframe,parent,false);
        return new TimeFrameAdapter.TimeFrameViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull TimeFrameViewHolder holder, int position) {
        Room.RoomTimeFrameResponse time = listFrame.get(position);
        if (time == null){
            return;
        }

        holder.timeLabel.setText(time.getStartTime()+":00");
        holder.timeLabel.setBackgroundColor(time.getStatus().equals("Available") ? Color.parseColor("#FFD188") : Color.parseColor("#d9d9d9"));
        holder.timeFrame = time;
    }

    @Override
    public int getItemCount() {
        return listFrame.size();
    }

    public class TimeFrameViewHolder extends RecyclerView.ViewHolder{
        public TextView timeLabel;
        public Room.RoomTimeFrameResponse timeFrame;
        public TimeFrameViewHolder(@NonNull View itemView) {
            super(itemView);

            timeLabel = itemView.findViewById(R.id.item_timeframe);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!timeFrame.getStatus().equals("Available")) return;
                    if (parentViewHolder.selectedTimeFrames.contains(timeFrame)){
                        parentViewHolder.selectedTimeFrames.remove(timeFrame);
                        timeLabel.setBackgroundColor(timeFrame.getStatus().equals("Available") ? Color.parseColor("#FFD188") : Color.parseColor("#d9d9d9"));
                    }else{
                        parentViewHolder.selectedTimeFrames.add(timeFrame);
                        timeLabel.setBackgroundColor(Color.parseColor("#fd4e16"));
                    }
                }
            });
        }
    }
}
