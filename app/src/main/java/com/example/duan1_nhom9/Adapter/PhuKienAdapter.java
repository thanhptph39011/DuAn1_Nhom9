package com.example.duan1_nhom9.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.duan1_nhom9.DAO.PhuKienDao;
import com.example.duan1_nhom9.Model.PhuKien;
import com.example.duan1_nhom9.R;

import java.util.ArrayList;

public class PhuKienAdapter extends RecyclerView.Adapter<PhuKienAdapter.viewholer> {
    private final Context context;
    private final ArrayList<PhuKien> listpk;
    PhuKienDao phuKienDao;

    public PhuKienAdapter(Context context, ArrayList<PhuKien> listpk) {
        this.context = context;
        this.listpk = listpk;
        phuKienDao = new PhuKienDao(context);
    }

    @NonNull
    @Override
    public viewholer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_phu_kien, null);
        return new viewholer(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholer holder, int position) {
        holder.maPhuKien.setText(listpk.get(position).getMaPhuKien()+"");
        holder.tenPhuKien.setText(listpk.get(position).getTenPhuKien());
        holder.giaPhuKien.setText(listpk.get(position).getGiaPhuKien()+"");
        PhuKien pk = listpk.get(position);
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Cảnh báo");//set tiêu đề
                builder.setIcon(R.drawable.baseline_warning_24);//set icon
                builder.setMessage("Bạn có chắc chắn muốn xóa không?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (phuKienDao.deletePhuKien(pk.getMaPhuKien())) {
                            listpk.clear();
                            listpk.addAll(phuKienDao.GetAll());
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
            public void onClick(View view) {
                opendialogUpdate(pk);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listpk.size();
    }

    public class viewholer extends RecyclerView.ViewHolder {
        TextView maPhuKien, tenPhuKien, giaPhuKien;
        ImageView btnDelete, btnUpdate;

        public viewholer(@NonNull View itemView) {
            super(itemView);
            maPhuKien = itemView.findViewById(R.id.tvMaPk_itemPhuKien);
            tenPhuKien = itemView.findViewById(R.id.tvTenPK_itemPhuKien);
            giaPhuKien = itemView.findViewById(R.id.tvGiaPK_itemPhuKien);
            btnUpdate = itemView.findViewById(R.id.btnUpdate_PhuKien);
            btnDelete = itemView.findViewById(R.id.btnDelete_PhuKien);
        }
    }

    public void opendialogUpdate(PhuKien pk) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_update_phukien, null);
        builder.setView(view);//gán view vào hôp thoại
        Dialog dialog = builder.create();//tạo hộp thoại
        dialog.show();
        //Ánh xạ
        EditText maPk = view.findViewById(R.id.edtMaPK_itemUpPK);
        EditText tenPk = view.findViewById(R.id.edttenPK_itemUpPK);
        EditText giaPk = view.findViewById(R.id.edtGiaPK_itemUpPK);
        ImageView btnSave = view.findViewById(R.id.update_Pk);
        ImageView btnHuy = view.findViewById(R.id.huyUpdate_Pk);
        //Gán dl
        maPk.setText(String.valueOf(pk.getMaPhuKien()));
        tenPk.setText(pk.getTenPhuKien());
        giaPk.setText(pk.getGiaPhuKien()+"");
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ten = tenPk.getText().toString();
                String gia = giaPk.getText().toString();
                if (ten.equals("")) {
                    Toast.makeText(context, "Vui lòng nhập tên phụ kiện", Toast.LENGTH_SHORT).show();
                    tenPk.requestFocus();
                    return;
                }
                if (gia.equals("")) {
                    Toast.makeText(context, "Vui lòng nhập giá phụ kiện", Toast.LENGTH_SHORT).show();
                    giaPk.requestFocus();
                    return;
                }
                pk.setTenPhuKien(tenPk.getText().toString());
                pk.setGiaPhuKien(Integer.parseInt(giaPk.getText().toString()));
                if (phuKienDao.updatePhuKien(pk)) {
                    listpk.clear();
                    listpk.addAll(phuKienDao.GetAll());
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
            public void onClick(View view) {
                Toast.makeText(context, "Huỷ Update", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

    }
}
