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

    public List<HoaDonCt> getAll(int maHd) {
        String sql = "Select * from Cthd where maHoaDon ="+maHd;
        return getData(sql);
    }

    public boolean insertItemHoaDonCt(HoaDonCt hdct) {
        ContentValues values = new ContentValues();
        values.put("maGiay", hdct.getMaGiay());
        values.put("soLuong",hdct.getSoLuong());
        values.put("tongTien", hdct.getGiaMua());
        long row = db.insert("Cthd", null, values);
        return (row > 0);
    }
    public boolean insertHoaDonCt(HoaDonCt hdct) {
        ContentValues values = new ContentValues();
        values.put("maHoaDon", hdct.getSoHoaDon());
        values.put("maGiay", hdct.getMaGiay());
        values.put("soLuong",hdct.getSoLuong());
        values.put("tongTien", hdct.getGiaMua());
        long row = db.insert("Cthd", null, values);
        return (row > 0);
    }

    public boolean UpdateHoaDonCt(HoaDonCt hdct) {
        ContentValues values = new ContentValues();
        values.put("maHoaDon", hdct.getSoHoaDon());
        values.put("maGiay", hdct.getMaGiay());
        values.put("soLuong",hdct.getSoLuong());
        values.put("tongTien", hdct.getGiaMua());
        long row = db.update("Cthd", values, " maCthd=?", new String[]{String.valueOf(hdct.getMaCthd())});
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
            hdct.setSoHoaDon(cursor.getInt(1));
            hdct.setMaGiay(cursor.getInt(2));
            hdct.setSoLuong(cursor.getInt(3));
            hdct.setGiaMua(cursor.getInt(4));

            list.add(hdct);
        }
        return list;
    }
    public HoaDonCt getID(String id){
        String sql = "select * from Cthd where maCthd=?";
        List<HoaDonCt> list = getData(sql,id);
        return list.get(0);
    }

}
