package com.example.duan1_nhom9.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.duan1_nhom9.Model.Giay;
import com.example.duan1_nhom9.Model.LoaiGiay;
import com.example.duan1_nhom9.R;

import java.util.ArrayList;

public class GiaySpinerAdapter extends ArrayAdapter<Giay> {
    private Context context;
    private ArrayList<Giay> list;
    TextView tvMaGiay, tvTenGiay;

   public GiaySpinerAdapter(@NonNull Context context, ArrayList<Giay> list){
       super(context,0,list);
       this.context = context;
       this.list = list;
   }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_spinner_giay, null);
        }
        final Giay item = list.get(position);
        if (item != null) {
            tvMaGiay = v.findViewById(R.id.tvMaGiay_spinner);
            tvMaGiay.setText(item.getMaGiay() + ". ");
            tvTenGiay = v.findViewById(R.id.tvTenGiay_spinner);
            tvTenGiay.setText(item.getTenGiay());
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_spinner_giay, null);
        }
        Giay item = list.get(position);
        if (item != null) {
            tvMaGiay = v.findViewById(R.id.tvMaGiay_spinner);
            tvMaGiay.setText(item.getMaGiay() + ". ");
            tvTenGiay = v.findViewById(R.id.tvTenGiay_spinner);
            tvTenGiay.setText(item.getTenGiay());
        }
        return v;
    }
}
