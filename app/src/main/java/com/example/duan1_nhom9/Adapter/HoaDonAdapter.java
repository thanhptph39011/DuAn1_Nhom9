package com.example.duan1_nhom9.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.duan1_nhom9.DAO.KhachHangDao;
import com.example.duan1_nhom9.DAO.NhanVienDao;
import com.example.duan1_nhom9.Fragments.HoaDonFragment;

import com.example.duan1_nhom9.Model.HoaDon;
import com.example.duan1_nhom9.Model.KhachHang;
import com.example.duan1_nhom9.Model.NhanVien;
import com.example.duan1_nhom9.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class HoaDonAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<HoaDon> list;
    HoaDonFragment fragment;
    TextView tvMaHd, tvSoHD, tvMaNv, tvMaKh, tvThanhToan, tvNgay;
    ImageView btnDelete;
    KhachHangDao khachHangDao;
    boolean isAdmin = false;
    SimpleDateFormat sfd = new SimpleDateFormat("yyyy/MM/dd");


    public HoaDonAdapter(@NonNull Context context, HoaDonFragment fragment, ArrayList<HoaDon> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
        this.fragment = fragment;
        khachHangDao = new KhachHangDao(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_hoadon, null);
        }
        final HoaDon item = list.get(position);
        if (item != null) {
            tvMaHd = v.findViewById(R.id.tvHd_itemHoaDon);
            tvSoHD = v.findViewById(R.id.tvSoHoaDon_itemHoaDon);
            tvMaNv = v.findViewById(R.id.tvMaNv_itemHoaDon);
            tvMaKh = v.findViewById(R.id.tvTenKh_itemHoaDon);
            tvThanhToan = v.findViewById(R.id.tvThanhToan_itemHoaDon);
            tvNgay = v.findViewById(R.id.tvNgay_itemHoaDon);
            btnDelete = v.findViewById(R.id.btnDelete_hoaDon);
            tvMaHd.setText(item.getMaHoaDon() + "");
            tvSoHD.setText(item.getSoHoaDon());
//
            tvMaNv.setText(item.getMaNv());
            //
            khachHangDao = new KhachHangDao(context);
            KhachHang kh = khachHangDao.getID(String.valueOf(item.getMaKh()));
            if (item != null && kh != null) {
                String tenkh = kh.getTenKh();
                tvMaKh.setText(tenkh);
            } else {
                tvMaKh.setText("Không xác định");
            }
            try {
                tvNgay.setText(sfd.format(item.getNgayMua()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (item.getThanhToan() == 0) {
                tvThanhToan.setText("Tiền mặt");
            } else {
                tvThanhToan.setText("Chuyển khoản");
            }
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

// Lấy vai trò người dùng từ shared preferences hoặc cơ chế xác thực
                    SharedPreferences preferences = context.getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
                    String userRole = preferences.getString("userName", "");

// Kiểm tra xem vai trò người dùng có phải là admin không
                    if (userRole.equals("admin")) {
                        isAdmin = true;
                        fragment.xoa(String.valueOf(item.getMaHoaDon()));
                    } else {
                        Toast.makeText(context, "Bạn không có quyền xoá hoá đơn", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
        return v;
    }
}
