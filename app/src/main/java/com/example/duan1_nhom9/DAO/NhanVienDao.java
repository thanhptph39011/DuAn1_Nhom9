package com.example.duan1_nhom9.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1_nhom9.DataBase.DbHelper;
import com.example.duan1_nhom9.Model.NhanVien;

import java.util.ArrayList;
import java.util.List;

public class NhanVienDao {

    private SQLiteDatabase db;

    public NhanVienDao(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public List<NhanVien> getAll() {
        String sql = "Select * from NhanVien";
        return getData(sql);
    }

    public boolean insertNv(NhanVien nv) {
        ContentValues values = new ContentValues();
        values.put("maNv", nv.getMaNv());
        values.put("hoTen", nv.getHoTen());
        values.put("matKhau", nv.getMatKhau());
        values.put("cccd", nv.getCccd());
        values.put("sdt", nv.getSdt());
        values.put("maCoSo", nv.getMaCoSo());
        long row = db.insert("NhanVien", null, values);
        return (row > 0);
    }
    public long updatePass(NhanVien obj) {
        ContentValues values = new ContentValues();
        values.put("hoTen", obj.getHoTen());
        values.put("matKhau", obj.getMatKhau());
        values.put("cccd",obj.getCccd());
        values.put("sdt",obj.getSdt());
        values.put("maCoSo",obj.getMaCoSo());
        return db.update("NhanVien", values, "maNv = ?", new String[]{String.valueOf(obj.getMaNv())});
    }


    public boolean deleteNv(String maNv) {
        long row = db.delete("NhanVien", "maNv=?", new String[]{maNv});
        return (row > 0);
    }

    private List<NhanVien> getData(String sql, String... selectionArgs) {
        List<NhanVien> list = new ArrayList<NhanVien>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()) {
            NhanVien nv = new NhanVien();
            nv.setMaNv(c.getString(0));
            nv.setHoTen(c.getString(1));
            nv.setMatKhau(c.getString(2));
            nv.setCccd(c.getString(3));
            nv.setSdt(c.getInt(4));
            nv.setMaCoSo(c.getString(5));
            list.add(nv);
        }
        return list;
    }

    public NhanVien getID(String id) {
        String sql = "select * from NhanVien where maNv=?";
        List<NhanVien> list = getData(sql, id);
        return list.get(0);
    }
    public int CheckLogin(String id,String password){
        String sql = "select * from NhanVien where maNv=? and matKhau=?";
        List<NhanVien> list=getData(sql,id,password);
        if (list.size()==0){
            return -1;
        }return 1;
    }
}
