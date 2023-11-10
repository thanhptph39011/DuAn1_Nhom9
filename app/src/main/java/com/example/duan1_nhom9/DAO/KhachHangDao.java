package com.example.duan1_nhom9.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1_nhom9.DataBase.DbHelper;
import com.example.duan1_nhom9.Model.Giay;
import com.example.duan1_nhom9.Model.KhachHang;

import java.util.ArrayList;
import java.util.List;

public class KhachHangDao {
    private SQLiteDatabase db;

    public KhachHangDao(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public List<KhachHang> getAll() {
        String sql = "Select * from KhachHang";
        return getData(sql);
    }

    public boolean insertKh(KhachHang kh) {
        ContentValues values = new ContentValues();
        values.put("hoTen", kh.getTenKh());
        values.put("sdt", kh.getSdt());
        long row = db.insert("KhachHang", null, values);
        return (row > 0);

    }

    public boolean updateKh(KhachHang kh) {
        ContentValues values = new ContentValues();
        values.put("hoTen", kh.getTenKh());
        values.put("sdt", kh.getSdt());
        long row = db.update("KhachHang", values, "maKh=?", new String[]{String.valueOf(kh.getMaKh())});
        return (row > 0);

    }

    public boolean deleteKh(int makh) {
        long row = db.delete("KhachHang", "maKh=?", new String[]{String.valueOf(makh)});
        return (row > 0);
    }


    private List<KhachHang> getData(String sql, String... selectionArgs) {
        List<KhachHang> list = new ArrayList<KhachHang>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            KhachHang kh = new KhachHang();
            kh.setMaKh(cursor.getInt(0));
            kh.setTenKh(cursor.getString(1));
            kh.setSdt(cursor.getInt(2));
            list.add(kh);
        }
        return list;
    }


}
