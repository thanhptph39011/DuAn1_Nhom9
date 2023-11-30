package com.example.duan1_nhom9;

import androidx.appcompat.app.AppCompatActivity;

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
import com.example.duan1_nhom9.Model.Giay;
import com.example.duan1_nhom9.Model.HoaDonCt;

import java.util.ArrayList;

public class HoaDonCtActivity extends AppCompatActivity {
    ListView lvSp;
    EditText edtsoHd, edtSoLuong;
    Spinner spnGiay;
    TextView tvTongTien;
    Button btnSave;
    GiaySpinerAdapter giaySpinerAdapter;
    ArrayList<Giay> listGiay;
    HoaDonCtDao hoaDonCtDao;
    ArrayList<HoaDonCt> list;
    HoaDonCtAdapter adapter;
    HoaDonCt hoaDonCt;
    GiayDao giayDao;
    String tenGiay;
    int maGiay, giatien;
    int soHd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don_ct);
        edtsoHd = findViewById(R.id.edtSoHd_itemHdCt);
        lvSp = findViewById(R.id.lvHdCt);
        tvTongTien = findViewById(R.id.tvTongTien);
        edtSoLuong = findViewById(R.id.edtSoLuong_hdct);
        btnSave = findViewById(R.id.btnSave_hdct);
        spnGiay = findViewById(R.id.spGiay_itemHdCt);
        //
        hoaDonCtDao = new HoaDonCtDao(this);
        //
        soHd = getIntent().getIntExtra("soHd", 0);
        edtsoHd.setText(String.valueOf(soHd));
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
                tenGiay = listGiay.get(i).getTenGiay();
                Toast.makeText(HoaDonCtActivity.this, "Chọn:" + listGiay.get(i).getTenGiay(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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
                    hoaDonCt.setSoHoaDon(soHd);
                    hoaDonCt.setMaGiay(maGiay);
                    hoaDonCt.setGiaMua(giatien);
                    if (hoaDonCtDao.insertHoaDonCt(hoaDonCt)) {
                        Toast.makeText(HoaDonCtActivity.this, "Thêm  succ", Toast.LENGTH_SHORT).show();
                        edtSoLuong.setText("");
                        list.clear();
                        list.addAll(hoaDonCtDao.getAll(soHd));
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(HoaDonCtActivity.this, "Thêm fail", Toast.LENGTH_SHORT).show();
                    }
                }
               capNhapLv();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        capNhapLv();
    }

    public void capNhapLv() {
        list = (ArrayList<HoaDonCt>) hoaDonCtDao.getAll(soHd);
        adapter = new HoaDonCtAdapter(this, list);
        adapter.setOnDeleteSuccessListener(new HoaDonCtAdapter.OnDeleteSuccessListener() {
            @Override
            public void onDeleteSuccess() {
                int tongTien = adapter.tinhTongTien();
                tvTongTien.setText(tongTien + " VND");
            }
        });
        lvSp.setAdapter(adapter);
        int tongTien = adapter.tinhTongTien();
        tvTongTien.setText(tongTien + " VND");
    }
}