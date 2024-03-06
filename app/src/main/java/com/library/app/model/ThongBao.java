package com.library.app.model;

public class ThongBao {
    int maThongBao;
    String tieuDe;
    String noiDung;

    int trangThai;

    public ThongBao(int maThongBao, String tieuDe, String noiDung, int trangThai) {
        this.maThongBao = maThongBao;
        this.tieuDe = tieuDe;
        this.noiDung = noiDung;
        this.trangThai = trangThai;
    }

    public int getMaThongBao() {
        return maThongBao;
    }

    public void setMaThongBao(int maThongBao) {
        this.maThongBao = maThongBao;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
}
