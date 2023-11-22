package com.example.duan1_nhom9.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1_nhom9.DataBase.DbHelper;
import com.example.duan1_nhom9.Model.HoaDon;
import com.example.duan1_nhom9.Model.KhachHang;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HoaDonDao {
    private SQLiteDatabase db;

    public HoaDonDao(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public List<HoaDon> getAll() {
        String sql = "Select * from HoaDon";
        return getData(sql);
    }
    public boolean insertHoaDon(HoaDon hd) {
        ContentValues values = new ContentValues();
        values.put("maKh", hd.getMaKh());
        values.put("maNv",hd.getMaNv());
        values.put("ngay",hd.getNgayMua().getTime());
        values.put("thanhToan",hd.getThanhToan());
        long row = db.insert("HoaDon", null, values);
        return (row > 0);

    }
    public boolean updateHoaDon(HoaDon hd) {
        ContentValues values = new ContentValues();
        values.put("maKh", hd.getMaKh());
        values.put("maNv",hd.getMaNv());
        values.put("ngay",hd.getNgayMua().getTime());
        values.put("thanhToan",hd.getThanhToan());
        long row = db.update("HoaDon",  values,"maHoaDon=?",new String[]{String.valueOf(hd.getMaHoaDon())});
        return (row > 0);
    }
    public boolean deleteHoaDon(int mahd){
        long row = db.delete("HoaDon","maHoaDon=?",new String[]{String.valueOf(mahd)});
        return (row>0);
    }
    private List<HoaDon> getData(String sql, String... selectionArgs) {
        List<HoaDon> list = new ArrayList<HoaDon>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            HoaDon hd = new HoaDon();
            hd.setMaHoaDon(cursor.getInt(0));
            hd.setMaKh(cursor.getInt(1));
            hd.setMaNv(cursor.getString(2));
            hd.setNgayMua(new Date(cursor.getLong(4)));
            hd.setThanhToan(cursor.getString(5));
            list.add(hd);
        }
        return list;
    }
}
