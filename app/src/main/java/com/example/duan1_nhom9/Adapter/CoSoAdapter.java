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

import com.example.duan1_nhom9.DAO.CoSoDao;
import com.example.duan1_nhom9.Model.CoSo;
import com.example.duan1_nhom9.R;

import java.util.ArrayList;

public class CoSoAdapter extends RecyclerView.Adapter<CoSoAdapter.viewholer> {
    private final Context context;
    private final ArrayList<CoSo> listcs;
    CoSoDao coSoDao;

    public CoSoAdapter(Context context, ArrayList<CoSo> listcs) {
        this.context = context;
        this.listcs = listcs;
        coSoDao = new CoSoDao(context);
    }

    @NonNull
    @Override
    public viewholer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_coso, null);
        return new viewholer(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholer holder, int position) {
        holder.maCoSo.setText(listcs.get(position).getMaCoSo());
        holder.diaChi.setText(listcs.get(position).getDiaChi());
        CoSo cs = listcs.get(position);
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
                        if (coSoDao.deleteCoSo(cs.getMaCoSo())) {
                            listcs.clear();
                            listcs.addAll(coSoDao.GetAll());
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
                opendialogUpdate(cs);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listcs.size();
    }

    public class viewholer extends RecyclerView.ViewHolder {
        TextView maCoSo, diaChi;
        ImageView btnDelete, btnUpdate;

        public viewholer(@NonNull View itemView) {
            super(itemView);
            maCoSo = itemView.findViewById(R.id.tvMaCs_itemCoSo);
            diaChi = itemView.findViewById(R.id.tvDiaChi_itemCoSo);
            btnUpdate = itemView.findViewById(R.id.btnUpdate_CoSo);
            btnDelete = itemView.findViewById(R.id.btnDelete_CoSo);
        }
    }

    public void opendialogUpdate(CoSo cs) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_update_coso, null);
        builder.setView(view);//gán view vào hôp thoại
        Dialog dialog = builder.create();//tạo hộp thoại
        dialog.show();
        //Ánh xạ
        EditText maCs = view.findViewById(R.id.edtTenCs_itemUpCs);
        EditText diaChi = view.findViewById(R.id.edtDiaChi_itemUpCs);
        ImageView btnSave = view.findViewById(R.id.update_Cs);
        ImageView btnHuy = view.findViewById(R.id.huyUpdate_Cs);
        //Gán dl
        maCs.setText(cs.getMaCoSo());
        diaChi.setText(cs.getDiaChi());
        maCs.setEnabled(false);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenCs = maCs.getText().toString();
                String dchi = diaChi.getText().toString();
                if (tenCs.equals("")) {
                    Toast.makeText(context, "Vui lòng nhập tên cơ sở", Toast.LENGTH_SHORT).show();
                    maCs.requestFocus();
                    return;
                }
                if (dchi.equals("")) {
                    Toast.makeText(context, "Vui lòng nhập địa chỉ", Toast.LENGTH_SHORT).show();
                    diaChi.requestFocus();
                    return;
                }
                cs.setMaCoSo(maCs.getText().toString());
                cs.setDiaChi(diaChi.getText().toString());
                if (coSoDao.updateCoSo(cs)) {
                    listcs.clear();
                    listcs.addAll(coSoDao.GetAll());
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
