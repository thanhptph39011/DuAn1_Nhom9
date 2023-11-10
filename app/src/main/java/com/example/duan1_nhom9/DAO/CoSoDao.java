package com.example.duan1_nhom9.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.duan1_nhom9.DataBase.DbHelper;
import com.example.duan1_nhom9.Model.CoSo;
import java.util.ArrayList;
import java.util.List;

public class CoSoDao {
    private SQLiteDatabase db;

    public CoSoDao(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public List<CoSo> GetAll() {
        String sql = "Select * from CoSo";
        return getData(sql);
    }

    public boolean insertCoSo(CoSo cs) {
        ContentValues values = new ContentValues();
        values.put("maCoSo", cs.getMaCoSo());
        values.put("diaChi", cs.getDiaChi());
        long row = db.insert("CoSo", null, values);
        return (row > 0);
    }

    public boolean updateCoSo(CoSo cs) {
        ContentValues values = new ContentValues();
        values.put("maCoSo", cs.getMaCoSo());
        values.put("diaChi", cs.getDiaChi());
        long row = db.update("CoSo", values, "maCoSo=?", new String[]{cs.getMaCoSo()});
        return (row > 0);
    }

    public boolean deleteCoSo(String maCoSo) {
        long row = db.delete("CoSo", "maCoSo=?", new String[]{maCoSo});
        return (row > 0);
    }

    private List<CoSo> getData(String sql, String... selectionArgs) {
        List<CoSo> list = new ArrayList<CoSo>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            CoSo coSo = new CoSo();
            coSo.setMaCoSo(cursor.getString(0));
            coSo.setDiaChi(cursor.getString(1));
            list.add(coSo);
        }
        return list;
    }
}
