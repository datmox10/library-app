package com.library.app.model;

import java.util.ArrayList;

public class BookResponse {
    private ArrayList<Book> booksEntityList;

    public ArrayList<Book> getBooksEntityList() {
        return booksEntityList;
    }

    public void setBooksEntityList(ArrayList<Book> booksEntityList) {
        this.booksEntityList = booksEntityList;
    }
}
