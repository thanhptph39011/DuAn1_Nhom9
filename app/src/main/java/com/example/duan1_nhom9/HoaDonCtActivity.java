package com.example.duan1_nhom9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1_nhom9.Adapter.GiaySpinerAdapter;

import com.example.duan1_nhom9.Adapter.HoaDonCtAdapter;
import com.example.duan1_nhom9.DAO.GiayDao;
import com.example.duan1_nhom9.DAO.HoaDonCtDao;

import com.example.duan1_nhom9.Fragments.HoaDonFragment;
import com.example.duan1_nhom9.Model.Giay;
import com.example.duan1_nhom9.Model.HoaDonCt;


import java.util.ArrayList;

public class HoaDonCtActivity extends AppCompatActivity {
    ListView lvSp;
    EditText edtsoHd;
    Spinner spnGiay;
    TextView tvTongTien;
    EditText edtSoLuong;
    Button btnSave, btnLuuHd;

    GiaySpinerAdapter giaySpinerAdapter;
    ArrayList<Giay> listGiay;
    HoaDonCtDao hoaDonCtDao;
    ArrayList<HoaDonCt> list;
    ArrayList<HoaDonCt> danhSachMuc;
    HoaDonCtAdapter adapter;
    HoaDonCt hoaDonCt;
    GiayDao giayDao;
    int maGiay, giatien;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don_ct);
        edtsoHd = findViewById(R.id.edtSoHd_itemHdCt);
        lvSp = findViewById(R.id.lvHdCt);
        tvTongTien = findViewById(R.id.tvTongTien);
        edtSoLuong = findViewById(R.id.edtSoLuong_hdct);
        btnSave = findViewById(R.id.btnSave_hdct);
        btnLuuHd = findViewById(R.id.btnLuuHd_hdct);
        spnGiay = findViewById(R.id.spGiay_itemHdCt);
        hoaDonCtDao = new HoaDonCtDao(this);
        danhSachMuc = new ArrayList<>();
        //
        String soHd = getIntent().getStringExtra("soHd");
        edtsoHd.setText(soHd);
        //Sp giày
        giayDao = new GiayDao(this);
        listGiay = new ArrayList<>();
        listGiay = (ArrayList<Giay>) giayDao.GetAll();
        giaySpinerAdapter = new GiaySpinerAdapter(this, listGiay);
        spnGiay.setAdapter(giaySpinerAdapter);
        spnGiay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                maGiay = listGiay.get(i).getMaGiay();
                giatien = listGiay.get(i).getGiaMua();

                Toast.makeText(HoaDonCtActivity.this, "Chọn:" + listGiay.get(i).getTenGiay(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btnLuuHd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (danhSachMuc.isEmpty()) {
                    Toast.makeText(HoaDonCtActivity.this, "Danh sách trống. Vui lòng thêm hàng hóa.", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Lưu dữ liệu vào cơ sở dữ liệu
               hoaDonCt = new HoaDonCt();
                hoaDonCt.setSoHoaDon(edtsoHd.getText().toString());
                hoaDonCt.setMaGiay(maGiay);
                int tongTien = adapter.tinhTongTien();
                hoaDonCt.setGiaMua(tongTien);
                hoaDonCt.setSoLuong(Integer.parseInt(edtSoLuong.getText().toString()));
                if(hoaDonCtDao.insertHoaDonCt(hoaDonCt)){
                    Toast.makeText(HoaDonCtActivity.this, "Lưu thành công", Toast.LENGTH_SHORT).show();
                    danhSachMuc.clear();
                    capNhapLv();
                }else{
                    Toast.makeText(HoaDonCtActivity.this, "Lưu thất bại", Toast.LENGTH_SHORT).show();
                }

            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String soLuong = edtSoLuong.getText().toString().trim();

                if (soLuong.isEmpty()) {
                    Toast.makeText(HoaDonCtActivity.this, "Nhập số lượng", Toast.LENGTH_SHORT).show();
                    edtSoLuong.requestFocus();
                    return;
                } else {
                    hoaDonCt = new HoaDonCt();
                    hoaDonCt.setSoLuong(Integer.parseInt(soLuong));
                    hoaDonCt.setMaGiay(maGiay);
                    hoaDonCt.setGiaMua(giatien);
                    danhSachMuc.add(hoaDonCt);

                    if (hoaDonCtDao.insertItemHoaDonCt(hoaDonCt)) {
                        Toast.makeText(HoaDonCtActivity.this, "Thêm  succ", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(HoaDonCtActivity.this, "Thêm fail", Toast.LENGTH_SHORT).show();
                    }
                }
                capNhapLv();
            }
        });

    }

    public void capNhapLv() {
        list = (ArrayList<HoaDonCt>) hoaDonCtDao.getAll();
     //   list = danhSachMuc;
        adapter = new HoaDonCtAdapter(this, list);
        lvSp.setAdapter(adapter);
        int tongTien = adapter.tinhTongTien();
        tvTongTien.setText(tongTien + " VND");
    }
}