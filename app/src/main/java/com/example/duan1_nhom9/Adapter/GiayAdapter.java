package com.example.duan1_nhom9.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.duan1_nhom9.DAO.LoaiGiayDao;
import com.example.duan1_nhom9.Fragments.GiayFragment;
import com.example.duan1_nhom9.Model.Giay;
import com.example.duan1_nhom9.Model.LoaiGiay;
import com.example.duan1_nhom9.R;

import java.util.ArrayList;

public class GiayAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<Giay> list;
    GiayFragment fragment;
    TextView tvMaGiay, tvTenGiay, tvGia, tvGhiChu, tvMaLoai;
    ImageView btnDelete;

    public GiayAdapter(@NonNull Context context, GiayFragment fragment, ArrayList<Giay> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_giay, null);
        }
        Giay item = list.get(position);
        if (item != null) {
            LoaiGiayDao loaiGiayDao = new LoaiGiayDao(context);
            LoaiGiay loaiGiay = loaiGiayDao.getID(String.valueOf(item.getMaLoai()));
            tvMaGiay = v.findViewById(R.id.tvMaGiay_itemGiay);
            tvTenGiay = v.findViewById(R.id.tvTenGiay_itemGiay);
            tvGia = v.findViewById(R.id.tvGiaMua_itemGiay);
            tvGhiChu = v.findViewById(R.id.tvGhiChu_itemGiay);
            tvMaLoai = v.findViewById(R.id.tvLoaiGiay_itemGiay);
            btnDelete = v.findViewById(R.id.btnDelete_itemGiay);
            //
            tvMaGiay.setText(item.getMaGiay() + "");
            tvTenGiay.setText(item.getTenGiay());
            tvGia.setText(item.getGiaMua() + "");
            tvGhiChu.setText(item.getMoTa());
            if (loaiGiay != null) {
                tvMaLoai.setText(loaiGiay.getTenLoai());
            } else {
                tvMaLoai.setText("Không xác định");
            }
        }
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment.xoa(item.getMaGiay());
            }
        });
        return v;
    }
}
