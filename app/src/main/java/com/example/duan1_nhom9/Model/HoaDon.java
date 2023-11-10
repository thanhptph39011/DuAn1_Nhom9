package com.example.duan1_nhom9.Model;

import java.util.Date;

public class HoaDon {
    private int maHoaDon;
    private int maKh;
    private int maGiay;
    private String maNv;
    private Date ngayMua;
    private int size;
    private int giaMua;
    private String thanhToan;

    public HoaDon() {
    }

    public HoaDon(int maHoaDon, int maKh, int maGiay, String maNv, Date ngayMua, int size, int giaMua, String thanhToan) {
        this.maHoaDon = maHoaDon;
        this.maKh = maKh;
        this.maGiay = maGiay;
        this.maNv = maNv;
        this.ngayMua = ngayMua;
        this.size = size;
        this.giaMua = giaMua;
        this.thanhToan = thanhToan;
    }

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public int getMaKh() {
        return maKh;
    }

    public void setMaKh(int maKh) {
        this.maKh = maKh;
    }

    public int getMaGiay() {
        return maGiay;
    }

    public void setMaGiay(int maGiay) {
        this.maGiay = maGiay;
    }

    public String getMaNv() {
        return maNv;
    }

    public void setMaNv(String maNv) {
        this.maNv = maNv;
    }

    public Date getNgayMua() {
        return ngayMua;
    }

    public void setNgayMua(Date ngayMua) {
        this.ngayMua = ngayMua;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getGiaMua() {
        return giaMua;
    }

    public void setGiaMua(int giaMua) {
        this.giaMua = giaMua;
    }

    public String getThanhToan() {
        return thanhToan;
    }

    public void setThanhToan(String thanhToan) {
        this.thanhToan = thanhToan;
    }
}
