package com.library.app.model;

import android.graphics.drawable.Drawable;

public class DichVu {
    int id;
    String tenDichVu;
    Drawable anhDichVu;

    public DichVu(int id,String tenDichVu, Drawable anhDichVu) {
        this.id = id;
        this.tenDichVu = tenDichVu;
        this.anhDichVu = anhDichVu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenDichVu() {
        return tenDichVu;
    }

    public void setTenDichVu(String tenDichVu) {
        this.tenDichVu = tenDichVu;
    }

    public Drawable getAnhDichVu() {
        return anhDichVu;
    }

    public void setAnhDichVu(Drawable anhDichVu) {
        this.anhDichVu = anhDichVu;
    }
}
