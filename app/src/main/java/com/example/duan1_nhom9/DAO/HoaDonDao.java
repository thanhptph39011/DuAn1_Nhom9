package com.example.duan1_nhom9.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1_nhom9.DataBase.DbHelper;
import com.example.duan1_nhom9.Model.HoaDon;
import com.example.duan1_nhom9.Model.KhachHang;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HoaDonDao {
    private SQLiteDatabase db;
    private Context context;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    public HoaDonDao(Context context) {
        this.context = context;
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public List<HoaDon> getAll() {
        String sql = "Select * from HoaDon";
        return getData(sql);
    }

    public boolean insertHoaDon(HoaDon hd) {
        ContentValues values = new ContentValues();
        values.put("soHoaDon", hd.getSoHoaDon());
        values.put("maKh", hd.getMaKh());
        values.put("maNv", hd.getMaNv());
        values.put("ngay", sdf.format(hd.getNgayMua()));
        values.put("thanhToan", hd.getThanhToan());
        long row = db.insert("HoaDon", null, values);
        return (row > 0);
    }

    public boolean updateHoaDon(HoaDon hd) {
        ContentValues values = new ContentValues();
        values.put("soHoaDon", hd.getSoHoaDon());
        values.put("maKh", hd.getMaKh());
        values.put("maNv", hd.getMaNv());
        values.put("ngay", sdf.format(hd.getNgayMua()));
        values.put("thanhToan", hd.getThanhToan());
        long row = db.update("HoaDon", values, "maHoaDon=?", new String[]{String.valueOf(hd.getMaHoaDon())});
        return (row > 0);
    }

    public boolean deleteHoaDon(int mahd) {
        long row = db.delete("HoaDon", "maHoaDon=?", new String[]{String.valueOf(mahd)});
        return (row > 0);
    }


    @SuppressLint("Range")
    private List<HoaDon> getData(String sql, String... selectionArgs) {
        List<HoaDon> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            HoaDon obj = new HoaDon();
            obj.setMaHoaDon(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maHoaDon"))));
            obj.setSoHoaDon(cursor.getString(cursor.getColumnIndex("soHoaDon")));
            obj.setMaKh(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maKh"))));
            obj.setMaNv(cursor.getString(cursor.getColumnIndex("maNv")));
            try {
                obj.setNgayMua(sdf.parse(cursor.getString(cursor.getColumnIndex("ngay"))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            obj.setThanhToan(Integer.parseInt(cursor.getString(cursor.getColumnIndex("thanhToan"))));
            list.add(obj);
        }
        return list;
    }

    public HoaDon getID(String id) {
        String sql = "select * from HoaDon where maHoaDon=?";
        List<HoaDon> list = getData(sql, id);
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }
}
