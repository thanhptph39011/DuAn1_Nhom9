package com.example.duan1_nhom9.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.duan1_nhom9.Model.KhachHang;
import com.example.duan1_nhom9.Model.LoaiGiay;
import com.example.duan1_nhom9.R;


import java.util.ArrayList;

public class KhachHangSpinnerAdapter extends ArrayAdapter<KhachHang> {
    private Context context;
    private ArrayList<KhachHang> list;
    TextView tvMaKh, tvTenKh;
    public KhachHangSpinnerAdapter(@NonNull Context context,ArrayList<KhachHang> list) {
        super(context, 0,list);
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_spiner_khachhang, null);
        }
        final KhachHang item = list.get(position);
        if (item != null) {
            tvMaKh = v.findViewById(R.id.tvMaKh_spinner);
            tvMaKh.setText(item.getMaKh() + ". ");
            tvTenKh = v.findViewById(R.id.tvTenKh_spinner);
            tvTenKh.setText(item.getTenKh());
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_spiner_khachhang, null);
        }
        KhachHang item = list.get(position);
        if (item != null) {
            tvMaKh = v.findViewById(R.id.tvMaKh_spinner);
            tvMaKh.setText(item.getMaKh() + ". ");
            tvTenKh = v.findViewById(R.id.tvTenKh_spinner);
            tvTenKh.setText(item.getTenKh());
        }
        return v;
    }
}
