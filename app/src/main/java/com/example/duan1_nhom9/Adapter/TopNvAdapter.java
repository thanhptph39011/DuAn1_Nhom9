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
import com.example.duan1_nhom9.Fragments.TopNvFragment;
import com.example.duan1_nhom9.Model.Top;
import com.example.duan1_nhom9.Model.TopNv;
import com.example.duan1_nhom9.R;

import java.util.ArrayList;

public class TopNvAdapter extends ArrayAdapter<TopNv> {
    private Context context;
   TopNvFragment topFragment;
    ArrayList<TopNv> list;
    TextView tvTenNv, tvDoanhThu;

    public TopNvAdapter(@NonNull Context context, TopNvFragment topNvFragment, ArrayList<TopNv> list ) {
        super(context, 0, list);
        this.context = context;
        this.topFragment = topNvFragment;
        this.list = list;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v=convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_topnv, null);
        }
        final TopNv item  = list.get(position);
        if(item!=null){
            tvTenNv=v.findViewById(R.id.tenNv_itemTopNv);
            tvDoanhThu=v.findViewById(R.id.doanhThu_itemTopNv);
            //
            tvTenNv.setText(item.getMaNv());
            tvDoanhThu.setText(item.getTongTien()+"  $");
        }
        return v;
    }
}
