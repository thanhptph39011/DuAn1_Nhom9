package com.example.duan1_nhom9.Model;

public class Giay {
    private int maGiay;
    private int maLoai;
    private String tenGiay;
    private int giaMua;
    private String moTa;

    public Giay() {
    }

    public Giay(int maGiay, int maLoai, String tenGiay, int giaMua, String moTa) {
        this.maGiay = maGiay;
        this.maLoai = maLoai;
        this.tenGiay = tenGiay;
        this.giaMua = giaMua;
        this.moTa = moTa;
    }

    public int getMaGiay() {
        return maGiay;
    }

    public void setMaGiay(int maGiay) {
        this.maGiay = maGiay;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenGiay() {
        return tenGiay;
    }

    public void setTenGiay(String tenGiay) {
        this.tenGiay = tenGiay;
    }

    public int getGiaMua() {
        return giaMua;
    }

    public void setGiaMua(int giaMua) {
        this.giaMua = giaMua;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
}
