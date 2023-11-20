package com.example.duan1_nhom9.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.duan1_nhom9.Model.LoaiGiay;
import com.example.duan1_nhom9.R;

import java.util.ArrayList;

public class LoaiGiaySpinerAdapter extends ArrayAdapter<LoaiGiay> {
    private Context context;
    private ArrayList<LoaiGiay> list;
    TextView tvMaLoaiGiay, tvTenLoaiGiay;

    public LoaiGiaySpinerAdapter(@NonNull Context context, ArrayList<LoaiGiay> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_spinner_loaigiay, null);
        }
        final LoaiGiay item = list.get(position);
        if (item != null) {
            tvMaLoaiGiay = v.findViewById(R.id.tvMaLoai_spinner);
            tvMaLoaiGiay.setText(item.getMaLoai() + ". ");
            tvTenLoaiGiay = v.findViewById(R.id.tvTenLoai_spinner);
            tvTenLoaiGiay.setText(item.getTenLoai());
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_spinner_loaigiay, null);
        }
        LoaiGiay item = list.get(position);
        if (item != null) {
            tvMaLoaiGiay = v.findViewById(R.id.tvMaLoai_spinner);
            tvMaLoaiGiay.setText(item.getMaLoai() + ". ");
            tvTenLoaiGiay = v.findViewById(R.id.tvTenLoai_spinner);
            tvTenLoaiGiay.setText(item.getTenLoai());
        }
        return v;
    }
}
