package com.example.duan1_nhom9.Model;

public class PhuKien {

    private int maPhuKien;
    private String tenPhuKien;
    private int giaPhuKien;

    public PhuKien() {
    }

    public PhuKien(int maPhuKien, String tenPhuKien, int giaPhuKien) {
        this.maPhuKien = maPhuKien;
        this.tenPhuKien = tenPhuKien;
        this.giaPhuKien = giaPhuKien;
    }

    public int getMaPhuKien() {
        return maPhuKien;
    }

    public void setMaPhuKien(int maPhuKien) {
        this.maPhuKien = maPhuKien;
    }

    public String getTenPhuKien() {
        return tenPhuKien;
    }

    public void setTenPhuKien(String tenPhuKien) {
        this.tenPhuKien = tenPhuKien;
    }

    public int getGiaPhuKien() {
        return giaPhuKien;
    }

    public void setGiaPhuKien(int giaPhuKien) {
        this.giaPhuKien = giaPhuKien;
    }
}
