package com.library.app.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;

import com.library.app.R;
import com.library.app.adapter.ChatAdapter;
import com.library.app.dto.BookTrainingResponse;
import com.library.app.dto.ConversationRequest;
import com.library.app.dto.ConversationResponse;
import com.library.app.model.BookAIChatModel;
import com.library.app.model.MessagesChat;
import com.library.app.repository.ApiClient;
import com.library.app.repository.BookTrainingRepository;
import com.library.app.repository.remote.RemoteBookTrainingRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class AIBookChatActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private EditText editText;
    private ImageButton btn;
    private ChatAdapter chatAdapter;
    private List<MessagesChat> messagesChatList;
    private String sessionChat;
    private Observer<ConversationResponse> currentObserver;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    private BookAIChatModel bookAIChatModel;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        // lấy ra thông tin session chat được truyền vào
        if (null == extras) {
            this.sessionChat = "";
        } else {
            this.sessionChat = extras.getString("id");
        }
        setContentView(R.layout.activity_aibook_chat);
        AnhXa();
        String name = "Hỏi đáp AI";
        setToolbar(toolbar, name);
        messagesChatList = new ArrayList<>();
        chatAdapter = new ChatAdapter(getApplicationContext(), messagesChatList);
        BookTrainingRepository bookRepository = new RemoteBookTrainingRepository(ApiClient.getApiService());
        bookAIChatModel = new BookAIChatModel(bookRepository);
        initView();
        initControll();
    }

    private void initControll() {
        btn.setOnClickListener(v -> {
            String ques = editText.getText().toString().trim();
            editText.setText("");
            postData(ques);
        });
    }

    public void postData(String ques) {
        if (bookAIChatModel != null) {

            ConversationRequest request = new ConversationRequest();
            request.setConversation(ques);
            request.setSessionChat(this.sessionChat);
            addQuesToRecycle(ques, 0);

            LiveData<ConversationResponse> responseLiveData = bookAIChatModel.getQuestion(request);
            if (responseLiveData != null) {
                Log.d("postData", "responseLiveData is not null");

                Observer<ConversationResponse> observer = new Observer<ConversationResponse>() {
                    @Override
                    public void onChanged(ConversationResponse conversationResponse) {
                        if (conversationResponse != null) {
                            Log.d("postData", "Received new response: " + conversationResponse.message);
                            addQuesToRecycle(conversationResponse.message, 1);
                        }
                    }
                };

                // Hủy bỏ observer cũ nếu có
                if (currentObserver != null) {
                    responseLiveData.removeObserver(currentObserver);
                }

                // Đăng ký observer mới
                currentObserver = observer;
                responseLiveData.observe(this, observer);

//                responseLiveData.observe(this, conversationResponse -> {
//                    if (conversationResponse != null) {
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                addQuesToRecycle(conversationResponse.message, 1);
//                                Log.d( "postData: ",ques);
//                                Log.d("run: ",conversationResponse.message);
//                            }
//                        });
//                    }
//                });


            } else {
                // Xử lý trường hợp responseLiveData là null
                System.out.println("data response live null");
            }
        } else {
            System.out.println("data bookAIChatModel live null");
            // Xử lý trường hợp bookAIChatModel là null
        }
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
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) // Press Back Icon
        {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}