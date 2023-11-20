package com.example.duan1_nhom9.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1_nhom9.DataBase.DbHelper;
import com.example.duan1_nhom9.Model.PhuKien;

import java.util.ArrayList;
import java.util.List;

public class PhuKienDao {

    private SQLiteDatabase db;

    public PhuKienDao(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public List<PhuKien> GetAll() {
        String sql = "Select * from PhuKien";
        return getData(sql);
    }

    public boolean insertPhuKien(PhuKien pk) {
        ContentValues values = new ContentValues();
        values.put("tenPhuKien", pk.getTenPhuKien());
        values.put("giaPhuKien", pk.getGiaPhuKien());
        long row = db.insert("PhuKien", null, values);
        return (row > 0);
    }

    public boolean updatePhuKien(PhuKien pk) {
        ContentValues values = new ContentValues();
        values.put("tenPhuKien", pk.getTenPhuKien());
        values.put("giaPhuKien", pk.getGiaPhuKien());
        long row = db.update("PhuKien", values, "maPhuKien=?", new String[]{String.valueOf(pk.getMaPhuKien())});
        return (row > 0);
    }

    public boolean deletePhuKien(int maPhuKien) {
        long row = db.delete("PhuKien", "maPhuKien=?", new String[]{String.valueOf(maPhuKien)});
        return (row > 0);
    }

    private List<PhuKien> getData(String sql, String... selectionArgs) {
        List<PhuKien> list = new ArrayList<PhuKien>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            PhuKien phuKien = new PhuKien();
            phuKien.setMaPhuKien(cursor.getInt(0));
            phuKien.setTenPhuKien(cursor.getString(1));
            phuKien.setGiaPhuKien(cursor.getInt(2));
            list.add(phuKien);
        }
        return list;
    }
    public PhuKien getID(String id){
        String sql ="select * from PhuKien where maPhuKien=?";
        List<PhuKien> list = getData(sql,id);
        if(!list.isEmpty()){
            return list.get(0);
        }else{
            return null;
        }

    }

}
