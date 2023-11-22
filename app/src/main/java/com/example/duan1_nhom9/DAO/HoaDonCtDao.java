package com.example.duan1_nhom9.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1_nhom9.DataBase.DbHelper;

import com.example.duan1_nhom9.Model.HoaDonCt;

import java.util.ArrayList;

import java.util.List;

public class HoaDonCtDao {
    private SQLiteDatabase db;

    public HoaDonCtDao(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public List<HoaDonCt> getAll() {
        String sql = "Select * from Cthd";
        return getData(sql);
    }

    public boolean insertHoaDonCt(HoaDonCt hdct) {
        ContentValues values = new ContentValues();
        values.put("maHd", hdct.getMaHoaDon());
        values.put("maGiay", hdct.getMaGiay());
        values.put("maPk", hdct.getMaPk());
        values.put("size", hdct.getSize());
        values.put("giaMua", hdct.getGiaMua());
        long row = db.insert("Cthd", null, values);
        return (row > 0);
    }

    public boolean UpdateHoaDonCt(HoaDonCt hdct) {
        ContentValues values = new ContentValues();
        values.put("maHd", hdct.getMaHoaDon());
        values.put("maGiay", hdct.getMaGiay());
        values.put("maPk", hdct.getMaPk());
        values.put("size", hdct.getSize());
        values.put("giaMua", hdct.getGiaMua());
        long row = db.update("Cthd", values, "where maCthd=?", new String[]{String.valueOf(hdct.getMaCthd())});
        return (row > 0);
    }

    public boolean deleteHoaDonCt(int maCthd) {
        long row = db.delete("Cthd", "maCthd=?", new String[]{String.valueOf(maCthd)});
        return (row > 0);
    }

    private List<HoaDonCt> getData(String sql, String... selectionArgs) {
        List<HoaDonCt> list = new ArrayList<HoaDonCt>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            HoaDonCt hdct = new HoaDonCt();
            hdct.setMaCthd(cursor.getInt(0));
            hdct.setMaHoaDon(cursor.getInt(1));
            hdct.setMaGiay(cursor.getInt(2));
            hdct.setMaPk(cursor.getInt(3));
            hdct.setSize(cursor.getInt(4));
            hdct.setGiaMua(cursor.getInt(5));

            list.add(hdct);
        }
        return list;
    }
}
