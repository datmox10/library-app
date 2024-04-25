package com.library.app.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;

import com.library.app.R;
import com.library.app.adapter.ChatAdapter;
import com.library.app.model.MessagesChat;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class AIBookChatActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private EditText editText;
    private ImageButton btn;
    private ChatAdapter chatAdapter;
    private List<MessagesChat> messagesChatList;
    private String sessionChat;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // lấy ra thông tin session chat được truyền vào
        if (null == savedInstanceState) {
            this.sessionChat = "60dd9bd7-1145-40d2-a209-80750d63ee32";
        } else {
            this.sessionChat = savedInstanceState.getString("id");
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aibook_chat);
        AnhXa();
        String name = "Hỏi đáp AI";
        setToolbar(toolbar, name);
        messagesChatList = new ArrayList<>();
        chatAdapter = new ChatAdapter(getApplicationContext(), messagesChatList);
        initView();
        initControll();
    }

    private void initControll() {
        btn.setOnClickListener(v -> {
            String ques = editText.getText().toString().trim();
            addQuesToRecycle(ques, 0);
            editText.setText("");
            postData(ques);
        });
    }

    public void postData(String ques) {
//        HandlerQuestionTraining.ConversationRequest request = new HandlerQuestionTraining.ConversationRequest();
//        request.conversation = ques;
//        request.sessionChat = this.sessionChat;
//        addQuesToRecycle(ques, 0);
//        try {
//            Thread.sleep(1000 * 10);
//            Log.d("messsage = ", message);
//            addQuesToRecycle(message, 1);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private void addQuesToRecycle(String ques, int send_id) {
        messagesChatList.add(new MessagesChat(ques, send_id, null));
        chatAdapter.notifyDataSetChanged();
        recyclerView.smoothScrollToPosition(chatAdapter.getItemCount());

    }

    private void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(chatAdapter);
    }

    private void AnhXa() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_chat);
        recyclerView = (RecyclerView) findViewById(R.id.layout_chat_answer);
        editText = (EditText) findViewById(R.id.edit_query);
        btn = (ImageButton) findViewById(R.id.send);

    }

    public void setToolbar(Toolbar toolbar, String name) {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(name);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}