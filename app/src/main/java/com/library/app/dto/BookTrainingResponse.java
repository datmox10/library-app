package com.library.app.dto;

public class BookTrainingResponse {
    public Long id;
    public String bookName;
    public String author;
    public String image;

    public String sessionChat;

    public BookTrainingResponse(Long id, String bookName, String author, String image, String sessionChat) {
        this.id = id;
        this.bookName = bookName;
        this.author = author;
        this.image = image;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
