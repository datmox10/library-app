package com.library.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.library.app.R;
import com.library.app.model.MessagesChat;

import java.util.Date;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {

    private Context context;
    private List<MessagesChat> messagesChatList;

    public ChatAdapter(Context context, List<MessagesChat> messagesChatList) {
        this.context = context;
        this.messagesChatList = messagesChatList;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat,parent,false);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        MessagesChat messagesChat = messagesChatList.get(position);
        Date date = new Date();
        if(messagesChat.getSend_id() == 0 ){
            holder.layout2.setVisibility(View.VISIBLE);
            holder.layout1.setVisibility(View.GONE);
            holder.textRight.setText(messagesChat.getContent()+ ' ');
            holder.time_rq.setText( date +"");
        }else{
            holder.layout2.setVisibility(View.GONE);
            holder.layout1.setVisibility(View.VISIBLE);
            holder.textLeft.setText(messagesChat.getContent()+ ' ');
            holder.time_rq.setText(date+"");
        }
    }

    @Override
    public int getItemCount() {
        return messagesChatList.size();
    }

    public class ChatViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout layout1, layout2;
        private TextView textLeft,textRight,time_rq;

        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            layout1= itemView.findViewById(R.id.layout1);
            layout2= itemView.findViewById(R.id.layout2);
            textLeft = itemView.findViewById(R.id.txt_chat_left);
            textRight = itemView.findViewById(R.id.txt_chat_right);
            time_rq = itemView.findViewById(R.id.time_rq);
        }
    }
}
