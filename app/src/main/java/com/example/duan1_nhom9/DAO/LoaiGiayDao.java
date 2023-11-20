package com.example.duan1_nhom9.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1_nhom9.DataBase.DbHelper;
import com.example.duan1_nhom9.Model.LoaiGiay;

import java.util.ArrayList;
import java.util.List;

public class LoaiGiayDao {
    private SQLiteDatabase db;

    public LoaiGiayDao(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public List<LoaiGiay> GetAll() {
        String sql = "Select * from LoaiGiay ";
        return getData(sql);
    }

    public boolean insertLoaiGiay(LoaiGiay loaiGiay) {
        ContentValues values = new ContentValues();
        values.put("tenLoai", loaiGiay.getTenLoai());
        values.put("loaiHang",loaiGiay.getLoaiHang());
        long row = db.insert("LoaiGiay", null, values);
        return (row > 0);
    }

    public boolean updateLoaiGiay(LoaiGiay loaiGiay) {
        ContentValues values = new ContentValues();
        values.put("tenLoai", loaiGiay.getTenLoai());
        values.put("loaiHang",loaiGiay.getLoaiHang());
        long row = db.update("LoaiGiay", values, "maLoai=?", new String[]{String.valueOf(loaiGiay.getMaLoai())});
        return (row > 0);
    }

    public boolean deleteLoaiGiay(int maloai) {
        long row = db.delete("LoaiGiay", "maLoai=?", new String[]{String.valueOf(maloai)});
        return (row>0);
    }

    private List<LoaiGiay> getData(String sql, String... selectionArgs) {
        List<LoaiGiay> list = new ArrayList<LoaiGiay>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            LoaiGiay loaiGiay = new LoaiGiay();
            loaiGiay.setMaLoai(Integer.parseInt(cursor.getString(0)));
            loaiGiay.setTenLoai(cursor.getString(1));
            loaiGiay.setLoaiHang(cursor.getString(2));
            list.add(loaiGiay);
        }
        return list;
    }
    public LoaiGiay getID(String id){
        String sql ="select * from LoaiGiay where maLoai=?";
        List<LoaiGiay> list = getData(sql,id);
        if(!list.isEmpty()){
            return list.get(0);
        }else{
            return null;
        }

    }

}
