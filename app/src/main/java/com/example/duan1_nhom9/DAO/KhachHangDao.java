package com.example.duan1_nhom9.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duan1_nhom9.DataBase.DbHelper;
import com.example.duan1_nhom9.Model.KhachHang;

import java.util.ArrayList;
import java.util.List;

public class KhachHangDao {
    private final DbHelper dbHelper;

    public KhachHangDao(Context context) {
        dbHelper = new DbHelper(context);

    }

    public ArrayList<KhachHang> getAll() {
        ArrayList<KhachHang> listTv = new ArrayList<KhachHang>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM KhachHang", null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    KhachHang kh = new KhachHang();
                    kh.setMaKh(cursor.getInt(0));
                    kh.setTenKh(cursor.getString(1));
                    kh.setSdt(cursor.getInt(2));
                    listTv.add(kh);
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            Log.e("zzzzzzzzzzzzzzzzzzzz", "Lỗiiiiii");
        }
        return listTv;
    }

    public boolean insertKh(KhachHang tv) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("hoTen", tv.getTenKh());
        values.put("sdt", tv.getSdt());
        long row = db.insert("KhachHang", null, values);
        return (row > 0);
    }

    public boolean updateKh(KhachHang kh) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("hoTen", kh.getTenKh());
        values.put("sdt", kh.getSdt());
        long row = db.update("KhachHang", values, "maKh=?", new String[]{String.valueOf(kh.getMaKh())});
        return (row > 0);
    }

    public boolean deleteKh(int maKh) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long row = db.delete("KhachHang", "maKh=?", new String[]{String.valueOf(maKh)});
        return (row > 0);
    }
    private List<KhachHang> getData(String sql, String...selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        List<KhachHang> list = new ArrayList<KhachHang>();
        Cursor c =db.rawQuery(sql,selectionArgs);
        while(c.moveToNext()){
            KhachHang obj = new KhachHang();
            obj.setMaKh(c.getInt(0));
            obj.setTenKh(c.getString(1));
            obj.setSdt(c.getInt(2));
            list.add(obj);
        }
        return list;
    }
    public KhachHang getID(String id){
        String sql ="select * from KhachHang where maKh=?";
        List<KhachHang> list = getData(sql,id);
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }


}
