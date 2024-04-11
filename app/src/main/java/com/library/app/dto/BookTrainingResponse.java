package com.library.app.dto;

public class BookTrainingResponse {
    public Long id;
    public String bookName;
    public String sessionChat;

    public BookTrainingResponse(Long id, String bookName, String sessionChat) {
        this.id = id;
        this.bookName = bookName;
        this.sessionChat = sessionChat;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getSessionChat() {
        return sessionChat;
    }

    public void setSessionChat(String sessionChat) {
        this.sessionChat = sessionChat;
    }
}
