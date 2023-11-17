package com.example.duan1_nhom9.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_nhom9.DAO.LoaiGiayDao;
import com.example.duan1_nhom9.Model.LoaiGiay;
import com.example.duan1_nhom9.R;

import java.util.ArrayList;

public class LoaiGiayAdapter extends RecyclerView.Adapter<LoaiGiayAdapter.viewholer> {
    Context context;
    private final ArrayList<LoaiGiay> listlg;
    LoaiGiayDao loaiGiayDao;

    public LoaiGiayAdapter(Context context, ArrayList<LoaiGiay> listlg) {
        this.context = context;
        this.listlg = listlg;
        loaiGiayDao = new LoaiGiayDao(context);
    }

    @NonNull
    @Override
    public viewholer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_loaigiay, null);
        return new viewholer(view);
    }



    @Override
    public void onBindViewHolder(@NonNull viewholer holder, int position) {
        holder.maLoai.setText(String.valueOf(listlg.get(position).getMaLoai()));
        holder.tenLoai.setText(listlg.get(position).getTenLoai());
        holder.loaiHang.setText(listlg.get(position).getLoaiHang());
        LoaiGiay lg = listlg.get(position);
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Cảnh báo");//set tiêu đề
                builder.setIcon(R.drawable.baseline_warning_24);//set icon
                builder.setMessage("Bạn có chắc chắn muốn xóa không?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (loaiGiayDao.deleteLoaiGiay(lg.getMaLoai())) {
                            listlg.clear();
                            listlg.addAll(loaiGiayDao.GetAll());
                            notifyDataSetChanged();
                            Toast.makeText(context, "Delete Succ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "Huỷ xoá", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDiaLogUpdate(lg);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listlg.size();
    }

    public class viewholer extends RecyclerView.ViewHolder {
        TextView maLoai, tenLoai, loaiHang;
        ImageView btnDelete, btnUpdate;

        public viewholer(@NonNull View itemView) {
            super(itemView);
            maLoai = itemView.findViewById(R.id.tvMaLoai_itemLoaiGiay);
            tenLoai = itemView.findViewById(R.id.tvTenLoai_itemLoaiGiay);
            loaiHang = itemView.findViewById(R.id.tvLoaiHang_itemLoaiGiay);
            btnUpdate = itemView.findViewById(R.id.item_Update);
            btnDelete = itemView.findViewById(R.id.item_Delete);
        }
    }

    public void openDiaLogUpdate(LoaiGiay lg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_update_loaigiay, null);
        builder.setView(view);//gán view vào hôp thoại
        Dialog dialog = builder.create();//tạo hộp thoại
        dialog.show();
        EditText maLoai = view.findViewById(R.id.edtMaLoai_itemUpGiay);
        EditText tenLoai = view.findViewById(R.id.edtTenLoai_itemUpGiay);
        EditText loaiHang = view.findViewById(R.id.edtLoaiHang_itemUpLoaiGiay);
        ImageView btnSave = view.findViewById(R.id.update_lg);
        ImageView btnHuy = view.findViewById(R.id.huyUpdate_lg);
//gán dl
        maLoai.setText(String.valueOf(lg.getMaLoai()));
        tenLoai.setText(lg.getTenLoai());
        loaiHang.setText(lg.getLoaiHang());

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = tenLoai.getText().toString();
                String loai = loaiHang.getText().toString();
                if (ten.equals("")) {
                    Toast.makeText(context, "Nhập tên loại", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (loai.equals("")) {
                    Toast.makeText(context, "Nhập tên loại hàng", Toast.LENGTH_SHORT).show();
                    return;
                }
                lg.setTenLoai(tenLoai.getText().toString());
                lg.setLoaiHang(loaiHang.getText().toString());
                if (loaiGiayDao.updateLoaiGiay(lg)) {
                    listlg.clear();
                    listlg.addAll(loaiGiayDao.GetAll());
                    notifyDataSetChanged();
                    dialog.dismiss();
                    Toast.makeText(context, "Update Succ", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Update Fail", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Huỷ Update", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }
}
