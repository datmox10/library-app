package com.library.app.model;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

public class Sach {
    String soISBN;
    Float gia;
    String tacGia;
    String nhanDeChinh;
    String nhaXuatBan;
    int namXuatBan;
    String noiXuatBan;
    String anh;
    String phanloai;

    public String getPhanloai() {
        return phanloai;
    }

    public void setPhanloai(String phanloai) {
        this.phanloai = phanloai;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public String getSoISBN() {
        return soISBN;
    }

    public void setSoISBN(String soISBN) {
        this.soISBN = soISBN;
    }

    public Float getGia() {
        return gia;
    }

    public void setGia(Float gia) {
        this.gia = gia;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getNhanDeChinh() {
        return nhanDeChinh;
    }

    public void setNhanDeChinh(String nhanDeChinh) {
        this.nhanDeChinh = nhanDeChinh;
    }

    public String getNhaXuatBan() {
        return nhaXuatBan;
    }

    public void setNhaXuatBan(String nhaXuatBan) {
        this.nhaXuatBan = nhaXuatBan;
    }

    public int getNamXuatBan() {
        return namXuatBan;
    }

    public void setNamXuatBan(int namXuatBan) {
        this.namXuatBan = namXuatBan;
    }

    public String getNoiXuatBan() {
        return noiXuatBan;
    }

    public void setNoiXuatBan(String noiXuatBan) {
        this.noiXuatBan = noiXuatBan;
    }
}
