package com.example.duan1_nhom9.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.duan1_nhom9.Model.PhuKien;
import com.example.duan1_nhom9.R;

import java.util.ArrayList;

public class PhuKienSpinerAdaper extends ArrayAdapter<PhuKien> {
    private Context context;
    private ArrayList<PhuKien> list;
    TextView tvMaPhuKien, tvTenPhuKien;

    public PhuKienSpinerAdaper(@NonNull Context context, ArrayList<PhuKien> list) {
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
            v = inflater.inflate(R.layout.item_spiner_phukien, null);
        }
        final PhuKien item = list.get(position);
        if (item != null) {
            tvMaPhuKien = v.findViewById(R.id.tvTenPhuKien_spinner);
            tvMaPhuKien.setText(item.getMaPhuKien() + ". ");
            tvTenPhuKien = v.findViewById(R.id.tvTenPhuKien_spinner);
            tvTenPhuKien.setText(item.getTenPhuKien());
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_spiner_phukien, null);
        }
       PhuKien item = list.get(position);
        if (item != null) {
            tvMaPhuKien = v.findViewById(R.id.tvTenPhuKien_spinner);
            tvMaPhuKien.setText(item.getMaPhuKien() + ". ");
            tvTenPhuKien = v.findViewById(R.id.tvTenPhuKien_spinner);
            tvTenPhuKien.setText(item.getTenPhuKien());
        }
        return v;
    }
}
