package com.example.duan1_nhom9.Model;

import java.util.Date;

public class HoaDon {
    private int maHoaDon;
    private int maKh;
    private String maNv;
    private Date ngayMua;
    private String thanhToan;


    public HoaDon() {
    }

    public HoaDon(int maHoaDon, int maKh, String maNv, Date ngayMua, String thanhToan) {
        this.maHoaDon = maHoaDon;
        this.maKh = maKh;
        this.maNv = maNv;
        this.ngayMua = ngayMua;
        this.thanhToan = thanhToan;
    }

    public HoaDon(int maKh, String maNv, Date ngayMua, String thanhToan) {
        this.maKh = maKh;
        this.maNv = maNv;
        this.ngayMua = ngayMua;
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

    public String getThanhToan() {
        return thanhToan;
    }

    public void setThanhToan(String thanhToan) {
        this.thanhToan = thanhToan;
    }
}
