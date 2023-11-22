package com.example.duan1_nhom9.Model;

public class HoaDonCt {
    private int maCthd;
    private int maHoaDon;
    private  int maGiay;
    private int maPk;
    private int giaMua;
    private int size;

    public HoaDonCt() {
    }

    public HoaDonCt(int maCthd, int maHoaDon, int maGiay, int maPk, int giaMua,int size) {
        this.maCthd = maCthd;
        this.maHoaDon = maHoaDon;
        this.maGiay = maGiay;
        this.maPk = maPk;
        this.giaMua = giaMua;
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getMaCthd() {
        return maCthd;
    }

    public void setMaCthd(int maCthd) {
        this.maCthd = maCthd;
    }

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public int getMaGiay() {
        return maGiay;
    }

    public void setMaGiay(int maGiay) {
        this.maGiay = maGiay;
    }

    public int getMaPk() {
        return maPk;
    }

    public void setMaPk(int maPk) {
        this.maPk = maPk;
    }

    public int getGiaMua() {
        return giaMua;
    }

    public void setGiaMua(int giaMua) {
        this.giaMua = giaMua;
    }
}
