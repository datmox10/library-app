package com.library.app.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.library.app.R;
import com.library.app.adapter.ChatAdapter;
import com.library.app.api.ApiGPT;
import com.library.app.api.ApiGPTClient;
import com.library.app.model.MessParamPost;
import com.library.app.model.Message;
import com.library.app.model.MessagesChat;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AIBookChatActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView ;
    private EditText editText;
    private ImageButton btn;
    private ChatAdapter chatAdapter;
    private List<MessagesChat> messagesChatList;

    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ApiGPT apiGPT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aibook_chat);
        apiGPT = ApiGPTClient.getInstance().create(ApiGPT.class);
        AnhXa();
        String name = "Hỏi đáp AI";
        setToolbar(toolbar,name);
        messagesChatList= new ArrayList<>();
        chatAdapter = new ChatAdapter(getApplicationContext(),messagesChatList);


        initView();
        initControll();
    }

    private void initControll() {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ques = editText.getText().toString().trim();
                addQuesToRecycle(ques,0);
                editText.setText("");
                postData(ques);
            }
        });
    }

    public void postData(String ques){
        List<Message> messages= new ArrayList<>();
        messages.add(new Message(ques));
        MessParamPost messParamPost = new MessParamPost();
        messParamPost.setModel("gpt-3.5-turbo");
        messParamPost.setList(messages);
        messParamPost.setTemperature(0.7F);
        compositeDisposable.add(apiGPT.PostQues(messParamPost)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        messResponse -> {
                            String result = messResponse.getList().get(0).getList().get(0).getContent().toString();
                            addQuesToRecycle(result,1);
                        },
                        throwable -> {
                            Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                ));

    }

    private void addQuesToRecycle(String ques, int send_id) {
        LocalDateTime timeRq = LocalDateTime.now();
        messagesChatList.add(new MessagesChat(ques,send_id,timeRq));
        chatAdapter.notifyDataSetChanged();
        recyclerView.smoothScrollToPosition(chatAdapter.getItemCount());

    }
    private void initView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(chatAdapter);
    }
    private void AnhXa(){
        toolbar = (Toolbar) findViewById(R.id.toolbar_chat);
        recyclerView = (RecyclerView) findViewById(R.id.layout_chat_answer);
        editText = (EditText) findViewById(R.id.edit_query);
        btn = (ImageButton) findViewById(R.id.send);

    }
    public void setToolbar(Toolbar toolbar, String name) {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
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