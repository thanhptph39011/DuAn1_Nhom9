package com.example.duan1_nhom9.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1_nhom9.DataBase.DbHelper;
import com.example.duan1_nhom9.Model.Giay;

import java.util.ArrayList;
import java.util.List;

public class GiayDao {
    private SQLiteDatabase db;
    public GiayDao(Context context){
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public List<Giay> GetAll() {
        String sql = "Select * from Giay";
        return getData(sql);
    }
    public boolean insertGiay(Giay giay){
        ContentValues values = new ContentValues();
        values.put("maLoai",giay.getMaLoai());
        values.put("tenGiay",giay.getTenGiay());
        values.put("giaMua",giay.getGiaMua());
        values.put("moTa",giay.getMoTa());
        long row = db.insert("Giay",null,values);
        return (row>0);
    }
    public boolean updateGiay(Giay giay){
        ContentValues values = new ContentValues();
        values.put("maLoai",giay.getMaLoai());
        values.put("tenGiay",giay.getTenGiay());
        values.put("giaMua",giay.getGiaMua());
        values.put("moTa",giay.getMoTa());
        long row = db.update("Giay",values,"maGiay=?",new String[]{String.valueOf(giay.getMaGiay())});
        return (row>0);
    }
    public boolean deleteGiay(int maGiay){
        long row = db.delete("Giay","maGiay=?",new String[]{String.valueOf(maGiay)});
        return (row>0);
    }
    private List<Giay> getData(String sql, String... selectionArgs) {
        List<Giay> list = new ArrayList<Giay>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
          Giay giay = new Giay();
          giay.setMaGiay(Integer.parseInt(cursor.getString(0)));
          giay.setMaLoai(cursor.getInt(1));
          giay.setTenGiay(cursor.getString(2));
          giay.setGiaMua(cursor.getInt(3));
          giay.setMoTa(cursor.getString(4));
          list.add(giay);
        }
        return list;
    }
    public Giay getID(String id){
        String sql ="select * from Giay where maGiay=?";
        List<Giay> list = getData(sql,id);
        return list.get(0);
    }

}
