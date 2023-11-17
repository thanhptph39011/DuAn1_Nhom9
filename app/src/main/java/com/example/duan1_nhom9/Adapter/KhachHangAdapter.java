package com.example.duan1_nhom9.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
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

import com.example.duan1_nhom9.DAO.KhachHangDao;
import com.example.duan1_nhom9.Model.KhachHang;
import com.example.duan1_nhom9.R;

import java.util.ArrayList;

public class KhachHangAdapter extends RecyclerView.Adapter<KhachHangAdapter.viewholer> {
    private final Context context;
    private final ArrayList<KhachHang> listKh;
    KhachHangDao khachHangDAO;

    public KhachHangAdapter(Context context, ArrayList<KhachHang> listKh) {
        this.context = context;
        this.listKh = listKh;
        khachHangDAO = new KhachHangDao(context);
    }


    @NonNull
    @Override
    public viewholer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_khach_hang, null);
        return new viewholer(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholer holder, int position) {
        holder.khMaKH_itemKhachHang.setText(String.valueOf(listKh.get(position).getMaKh()));
        holder.khHoTen_itemKhachHang.setText(listKh.get(position).getTenKh());
        holder.khSdt_itemKhachHang.setText(listKh.get(position).getSdt()+"");
        KhachHang kh = listKh.get(position);
        holder.btnDelete_ThanhVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Cảnh báo");//set tiêu đề
                builder.setIcon(R.drawable.baseline_warning_24);//set icon
                builder.setMessage("Bạn có chắc chắn muốn xóa không?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (khachHangDAO.deleteKh(kh.getMaKh())) {
                            listKh.clear();
                            listKh.addAll(khachHangDAO.getAll());
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
        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                View view = inflater.inflate(R.layout.item_update_khachhang, null);
                builder.setView(view);
                Dialog dialog = builder.create();
                dialog.show();
                EditText edtMakh = view.findViewById(R.id.edtMaKH_itemUpKhachHang);
                EditText edtTenkh = view.findViewById(R.id.edtTenKH_itemUpKhachHang);
                EditText edtSdt = view.findViewById(R.id.edtSdt_itemUpKhachHang);
                Button btnSave = view.findViewById(R.id.btnSave_itemUpKhachHang);
                Button btnHuy = view.findViewById(R.id.btnHuy_itemUpKhachHang);
                //gán dl
                edtMakh.setText(String.valueOf(kh.getMaKh()));
                edtTenkh.setText(kh.getTenKh());
                edtSdt.setText(kh.getSdt() + "");
                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String ten = edtTenkh.getText().toString();
                        String sdt = edtSdt.getText().toString();
                        if (ten.isEmpty() || sdt.isEmpty()) {
                            Toast.makeText(context, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        try {
                           int phoneNumber = Integer.parseInt(sdt);
                           if(sdt.length()!=10){
                            Toast.makeText(context, "Số điện thoại phải là 10 số", Toast.LENGTH_SHORT).show();
                            return;
                            }
                            kh.setTenKh(edtTenkh.getText().toString());
                            kh.setSdt(phoneNumber);
                            if (khachHangDAO.updateKh(kh)) {
                                listKh.clear();
                                listKh.addAll(khachHangDAO.getAll());
                                notifyDataSetChanged();
                                dialog.dismiss();
                                Toast.makeText(context, "Update Succ", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Update Fail", Toast.LENGTH_SHORT).show();
                            }
                        }catch (Exception e){
                            Toast.makeText(context, "Số điện thoại không đúng định dạng", Toast.LENGTH_SHORT).show();
                            return;
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
        });

    }

    @Override
    public int getItemCount() {
        return listKh.size();
    }

    public class viewholer extends RecyclerView.ViewHolder {
        TextView khMaKH_itemKhachHang, khHoTen_itemKhachHang, khSdt_itemKhachHang;
        ImageView btnDelete_ThanhVien, btnUpdate;

        public viewholer(@NonNull View itemView) {
            super(itemView);
            khMaKH_itemKhachHang = itemView.findViewById(R.id.tvMaKH_itemKhachHang);
            khHoTen_itemKhachHang = itemView.findViewById(R.id.tvHoTen_itemKhachHang);
            khSdt_itemKhachHang = itemView.findViewById(R.id.tvSdt_itemKhachHang);
            btnDelete_ThanhVien = itemView.findViewById(R.id.btnDelete_KhachHang);
            btnUpdate = itemView.findViewById(R.id.btnUpdate_KhachHang);
        }
    }

}