package com.library.app.model;

import java.sql.Time;
import java.time.LocalDateTime;

public class MessagesChat {
    private  String content;
    private  int send_id; //0 cau hỏi - 1 cau tra loi

    private LocalDateTime timeRq;

    public MessagesChat(String content, int send_id, LocalDateTime timeRq) {
        this.content = content;
        this.send_id = send_id;
        this.timeRq = timeRq;
    }

    public MessagesChat(String ques, LocalDateTime timeRq) {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getSend_id() {
        return send_id;
    }

    public void setSend_id(int send_id) {
        this.send_id = send_id;
    }

    public LocalDateTime getTimeRq() {
        return timeRq;
    }

    public void setTimeRq(LocalDateTime timeRq) {
        this.timeRq = timeRq;
    }
}
