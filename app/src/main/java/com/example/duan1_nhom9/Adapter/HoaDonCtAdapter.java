package com.example.duan1_nhom9.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.duan1_nhom9.DAO.HoaDonCtDao;
import com.example.duan1_nhom9.Model.HoaDonCt;
import com.example.duan1_nhom9.R;

import java.util.ArrayList;

public class HoaDonCtAdapter extends ArrayAdapter<HoaDonCt> {
    private Context context;
    private ArrayList<HoaDonCt> list;
    TextView tvGiay,tvSl,tvThanhTien;
    ImageView btnDelete;
    HoaDonCtDao hoaDonCtDao;
    public HoaDonCtAdapter(@NonNull Context context, ArrayList<HoaDonCt> list) {
        super(context, 0,list);
        this.context = context;
        this.list = list;
        hoaDonCtDao = new HoaDonCtDao(context);
    }
    public int tinhTongTien() {
        int tongTien = 0;
        for (int i = 0; i < getCount(); i++) {
            HoaDonCt item = getItem(i);
            tongTien += item.getGiaMua() * item.getSoLuong();
        }
        return tongTien;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view= convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_hdct_giay, null);
        }
        final HoaDonCt item = list.get(position);
        if (item != null) {
            tvGiay = view.findViewById(R.id.tvTenGiay_itemHoaDonCt);
            tvSl = view.findViewById(R.id.tvSLuong_itemHoaDonCt);
            tvThanhTien = view.findViewById(R.id.tvGia_itemHoaDonCt);
            btnDelete =view.findViewById(R.id.btnDelete_hoaDonCt);
            //
            tvGiay.setText(item.getMaGiay()+"");
            tvSl.setText(item.getSoLuong()+"");
            tvThanhTien.setText(item.getGiaMua()* item.getSoLuong()+"");
        }
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Delete");
                builder.setMessage("Bạn có chắc chắn muốn xóa không");
                builder.setCancelable(true);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (hoaDonCtDao.deleteHoaDonCt(item.getMaCthd())) {
                            list.clear();
                            list.addAll(hoaDonCtDao.getAll());
                            notifyDataSetChanged();
                            Toast.makeText(context, "Delete Succ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "Bạn đã thoát xoá", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        return view;
    }
}
