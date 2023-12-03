package com.example.duan1_nhom9.DAO;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1_nhom9.DataBase.DbHelper;
import com.example.duan1_nhom9.Model.Giay;
import com.example.duan1_nhom9.Model.Top;
import com.example.duan1_nhom9.Model.TopNv;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ThongKeDao {
    private SQLiteDatabase db;
    private Context context;
    DbHelper dbHelper;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");

    public ThongKeDao(Context context) {
        this.context = context;
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    @SuppressLint("Range")
    public List<Top> getTop() {
        String sqlTop = "SELECT Giay.tenGiay, SUM(Cthd.soLuong) AS soLuongBan\n" +
                "FROM Cthd\n" +
                "JOIN Giay ON Cthd.maGiay = Giay.maGiay\n" +
                "GROUP BY Cthd.maGiay, Giay.tenGiay\n" +
                "ORDER BY soLuongBan DESC\n" +
                "LIMIT 10;";
        List<Top> list = new ArrayList<Top>();
        Cursor c = db.rawQuery(sqlTop, null);
        while (c.moveToNext()) {
            Top top = new Top();
            top.setMaGiay(c.getString(c.getColumnIndex("tenGiay")));
            top.setSoLuong(Integer.parseInt(c.getString(c.getColumnIndex("soLuongBan"))));
            list.add(top);
        }
        return list;
    }

    // thống kê doanh thu
    @SuppressLint("Range")
    public int getDoanhThu(String tuNgay, String denNgay) {
        String sqlDoanhThu = "SELECT SUM(tongTien * soLuong) as doanhThu FROM Cthd INNER JOIN HoaDon ON Cthd.maHoaDon = HoaDon.maHoaDon WHERE HoaDon.ngay BETWEEN ? AND ?";
        List<Integer> list = new ArrayList<Integer>();
        Cursor cursor = db.rawQuery(sqlDoanhThu, new String[]{tuNgay, denNgay});
        while (cursor.moveToNext()) {
            try {
                list.add(Integer.parseInt(cursor.getString(cursor.getColumnIndex("doanhThu"))));
            } catch (Exception e) {
                list.add(0);
            }
        }
        return list.get(0);
    }


    @SuppressLint("Range")
    public List<TopNv> getTop10NhanVienDoanhThu() {

        String query = "SELECT NhanVien.maNv, SUM(Cthd.tongTien * Cthd.soLuong) AS tienBan " +
                "FROM NhanVien " +
                "INNER JOIN HoaDon ON NhanVien.maNv = HoaDon.maNv " +
                "INNER JOIN Cthd ON HoaDon.maHoaDon = Cthd.maHoaDon " +
                "GROUP BY NhanVien.maNv " +
                "ORDER BY tienBan DESC " +
                "LIMIT 10";
        List<TopNv> list = new ArrayList<TopNv>();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            TopNv top = new TopNv();
            top.setMaNv(cursor.getString(cursor.getColumnIndex("maNv")));
            top.setTongTien(Integer.parseInt(cursor.getString(cursor.getColumnIndex("tienBan"))));
            list.add(top);
        }
        return list;
    }
}
