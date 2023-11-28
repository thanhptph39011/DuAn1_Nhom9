package com.example.duan1_nhom9.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.duan1_nhom9.Fragments.TopFragment;
import com.example.duan1_nhom9.Model.Top;
import com.example.duan1_nhom9.R;

import java.util.ArrayList;

public class TopAdapter extends ArrayAdapter<Top> {
    private Context context;
    TopFragment topFragment;
    ArrayList<Top> list;
    TextView tvGiay, tvSoLuong;

    public TopAdapter(@NonNull Context context, TopFragment topFragment, ArrayList<Top> list) {
        super(context, 0, list);
        this.context = context;
        this.topFragment = topFragment;
        this.list = list;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v=convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_top, null);
        }
        final Top item  = list.get(position);
        if(item!=null){
            tvGiay=v.findViewById(R.id.tenGiay_itemTop);
            tvSoLuong=v.findViewById(R.id.SoLuong_itemTop);
            //
            tvGiay.setText(item.getMaGiay());
            tvSoLuong.setText(item.getSoLuong()+"");
        }
        return v;
    }
}
