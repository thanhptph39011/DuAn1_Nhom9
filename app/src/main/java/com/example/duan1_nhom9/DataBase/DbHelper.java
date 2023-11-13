package com.example.duan1_nhom9.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public static final String Db_name = "AirStore";

    public DbHelper(@Nullable Context context) {
        super(context, Db_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Bảng cơ sơ
        String createTableCoSo =
                "create table CoSo(maCoSo text primary key ,diaChi text not null)";
        sqLiteDatabase.execSQL(createTableCoSo);

         //bảng loại giày
        String createTableLoaiGiay =
                "create table LoaiGiay(maLoai integer primary key autoincrement, tenLoai text, loaiHang text)";
        sqLiteDatabase.execSQL(createTableLoaiGiay);
        //bảng khách hàng
        String createTableKhachHang =
                "create table KhachHang(maKh integer primary key autoincrement,hoTen text not null, sdt integer not null)";
        sqLiteDatabase.execSQL(createTableKhachHang);
        //bảng giày
        String createTableGiay =
                "create table Giay(maGiay integer primary key autoincrement ,maLoai integer references LoaiGiay(maLoai),tenGiay text not null, giaMua integer not null, moTa text not null)";
        sqLiteDatabase.execSQL(createTableGiay);
        //Bảng nhân viên
        String createTableNhanVien =
                "create table NhanVien(maNv text primary key , maCoSo text references CoSo(maCoSo),hoTen text not null, matKhau text not null, cccd integer not null, sdt integer not null)";
        sqLiteDatabase.execSQL(createTableNhanVien);
        //Bảng hoá đơn
        String createTableHoaDon =
                "create table HoaDon(maHoaDon integer not null, maKh integer references KhachHang(maKh),maGiay integer references Giay(maGiay),maNv text references NhanVien(maNv),ngay date not null, size integer not null, tienMua integer not null, thanhToan text not null)";
        sqLiteDatabase.execSQL(createTableHoaDon);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String dropTableCoSo = "drop table if exists CoSo";
        sqLiteDatabase.execSQL(dropTableCoSo);
        String dropTableLoaiGiay = "drop table if exists LoaiGiay";
        sqLiteDatabase.execSQL(dropTableLoaiGiay);
        String dropTableKhachHang = "drop table if exists KhachHang";
        sqLiteDatabase.execSQL(dropTableKhachHang);
        String dropTableGiay = "drop table if exists Giay";
        sqLiteDatabase.execSQL(dropTableGiay);
        String dropTableNhanVien = "drop table if exists NhanVien";
        sqLiteDatabase.execSQL(dropTableNhanVien);
        String dropTableHoaDon = "drop table if exists HoaDon";
        sqLiteDatabase.execSQL(dropTableHoaDon);
        onCreate(sqLiteDatabase);

    }
}
