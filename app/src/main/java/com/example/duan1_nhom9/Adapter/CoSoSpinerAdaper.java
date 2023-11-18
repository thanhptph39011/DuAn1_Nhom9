package com.example.duan1_nhom9.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.duan1_nhom9.Model.CoSo;
import com.example.duan1_nhom9.R;

import java.util.ArrayList;

public class CoSoSpinerAdaper extends ArrayAdapter<CoSo> {
    private Context context;
    private ArrayList<CoSo> list;
    TextView tvMaCoSo;

    public CoSoSpinerAdaper(@NonNull Context context, ArrayList<CoSo> list) {
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
            v = inflater.inflate(R.layout.item_spiner_coso, null);
        }
        final CoSo item = list.get(position);
        if (item != null) {
            tvMaCoSo = v.findViewById(R.id.tvMaCoSo_spinner);
            tvMaCoSo.setText(item.getMaCoSo());
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_spiner_coso, null);
        }
       CoSo item = list.get(position);
        if (item != null) {
            tvMaCoSo = v.findViewById(R.id.tvMaCoSo_spinner);
            tvMaCoSo.setText(item.getMaCoSo());
        }
        return v;
    }
}
