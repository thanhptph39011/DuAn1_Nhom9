package com.example.duan1_nhom9.Model;

import java.util.Date;

public class HoaDon {
    private int maHoaDon;
    private String soHoaDon;
    private int maKh;
    private String maNv;
    private Date ngayMua;
    private int thanhToan;


    public HoaDon() {
    }

    public HoaDon(int maHoaDon, String soHoaDon, int maKh, String maNv, Date ngayMua, int thanhToan) {
        this.maHoaDon = maHoaDon;
        this.soHoaDon = soHoaDon;
        this.maKh = maKh;
        this.maNv = maNv;
        this.ngayMua = ngayMua;
        this.thanhToan = thanhToan;
    }

    public HoaDon(String soHoaDon, int maKh, String maNv, Date ngayMua, int thanhToan) {
        this.soHoaDon = soHoaDon;
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

    public int getThanhToan() {
        return thanhToan;
    }

    public void setThanhToan(int thanhToan) {
        this.thanhToan = thanhToan;
    }

    public String getSoHoaDon() {
        return soHoaDon;
    }

    public void setSoHoaDon(String soHoaDon) {
        this.soHoaDon = soHoaDon;
    }
}
