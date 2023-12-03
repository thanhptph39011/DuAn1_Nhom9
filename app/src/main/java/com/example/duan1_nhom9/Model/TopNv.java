package com.example.duan1_nhom9.Model;

public class TopNv {
    private String maNv;

    private int tongTien;

    public TopNv() {
    }

    public TopNv(String maNv, int tongTien) {
        this.maNv = maNv;
        this.tongTien = tongTien;
    }

    public String getMaNv() {
        return maNv;
    }

    public void setMaNv(String maNv) {
        this.maNv = maNv;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }
}
