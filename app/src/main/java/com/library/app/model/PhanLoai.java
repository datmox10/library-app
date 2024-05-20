package com.library.app.model;

import java.util.ArrayList;
import java.util.List;

public class PhanLoai {
    String maToai;
    String tenLoai;

    ArrayList<Book> books;


    public PhanLoai(String maToai, String tenLoai, ArrayList<Book> books) {
        this.maToai = maToai;
        this.tenLoai = tenLoai;
        this.books = books;
    }

    public ArrayList<Book> getSachList() {
        return books;
    }

    public void setSachList(ArrayList<Book> books) {
        this.books = books;
    }

    public String getMaToai() {
        return maToai;
    }

    public void setMaToai(String maToai) {
        this.maToai = maToai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }
}
