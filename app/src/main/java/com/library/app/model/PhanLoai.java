package com.library.app.model;

import java.util.ArrayList;
import java.util.List;

public class PhanLoai {
    String maToai;
    String tenLoai;

    ArrayList<Sach> sachList;


    public PhanLoai(String maToai, String tenLoai, ArrayList<Sach> sachList) {
        this.maToai = maToai;
        this.tenLoai = tenLoai;
        this.sachList = sachList;
    }

    public ArrayList<Sach> getSachList() {
        return sachList;
    }

    public void setSachList(ArrayList<Sach> sachList) {
        this.sachList = sachList;
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
