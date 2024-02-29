package com.library.app.model;

import android.graphics.drawable.Drawable;

public class DichVu {
    String tenDichVu;
    Drawable anhDichVu;

    public DichVu(String tenDichVu, Drawable anhDichVu) {
        this.tenDichVu = tenDichVu;
        this.anhDichVu = anhDichVu;
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
