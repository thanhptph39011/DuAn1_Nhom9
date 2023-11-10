package com.example.duan1_nhom9.Model;

public class CoSo {
    private String maCoSo;
    private String diaChi;

    public CoSo() {
    }

    public CoSo(String maCoSo, String diaChi) {
        this.maCoSo = maCoSo;
        this.diaChi = diaChi;
    }

    public String getMaCoSo() {
        return maCoSo;
    }

    public void setMaCoSo(String maCoSo) {
        this.maCoSo = maCoSo;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
}
