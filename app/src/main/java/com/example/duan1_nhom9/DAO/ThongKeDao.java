package com.example.duan1_nhom9.DAO;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1_nhom9.DataBase.DbHelper;
import com.example.duan1_nhom9.Model.Giay;
import com.example.duan1_nhom9.Model.Top;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ThongKeDao {
    private SQLiteDatabase db;
    private Context context;
    DbHelper dbHelper;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public ThongKeDao(Context context) {
        this.context = context;
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    @SuppressLint("Range")
    public List<Top> getTop() {
        String sqlTop = "Select maGiay,count(maGiay) as soLuong From HoaDon group by maGiay order by soLuong Desc Limit 10";
        List<Top> list = new ArrayList<Top>();
        GiayDao giayDao = new GiayDao(context);
        Cursor c = db.rawQuery(sqlTop, null);
        while (c.moveToNext()) {
            Top top = new Top();
            Giay giay = giayDao.getID(c.getString(c.getColumnIndex("maGiay")));
            top.setMaGiay((giay.getTenGiay()));
            top.setSoLuong(Integer.parseInt(c.getString(c.getColumnIndex("soLuong"))));
            list.add(top);
        }
        return list;
    }
}
