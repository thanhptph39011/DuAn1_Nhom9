package com.example.duan1_nhom9.Fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.duan1_nhom9.Adapter.CoSoSpinerAdaper;
import com.example.duan1_nhom9.Adapter.NhanVienAdapter;
import com.example.duan1_nhom9.DAO.CoSoDao;
import com.example.duan1_nhom9.DAO.NhanVienDao;
import com.example.duan1_nhom9.Model.CoSo;
import com.example.duan1_nhom9.Model.NhanVien;
import com.example.duan1_nhom9.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class NhanVienFragment extends Fragment {
    ListView lvNhanVien;
    ArrayList<NhanVien> listNv;
    ArrayList<NhanVien> tempList;
    Dialog dialog;
    FloatingActionButton flab;
    EditText edtMaNv, edtHoTen, edtPass, edtSdt, edtCccd, editTextTimKiem;
    Spinner spinner;
    ImageView btnSave, btnHuy;
    static NhanVienDao nhanVienDao;
    NhanVienAdapter adapter;

    CoSoSpinerAdaper spinerAdaper;
    ArrayList<CoSo> listCoSo;
    CoSoDao coSoDao;


    public NhanVienFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_nhan_vien, container, false);
        lvNhanVien = v.findViewById(R.id.lvNhanVien);
        flab = v.findViewById(R.id.fltAddNhanVien);
        editTextTimKiem = v.findViewById(R.id.edtTimKiemNv);
        nhanVienDao = new NhanVienDao(getActivity());
        listNv = (ArrayList<NhanVien>) nhanVienDao.getAll();
        tempList = (ArrayList<NhanVien>) nhanVienDao.getAll();
        adapter = new NhanVienAdapter(getActivity(), this, listNv);
        lvNhanVien.setAdapter(adapter);
        editTextTimKiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                listNv.clear();
                for (NhanVien nv : tempList
                ) {
                    if (nv.getHoTen().contains(charSequence.toString())) {
                        listNv.add(nv);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        flab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
        lvNhanVien.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getContext(), "Không thể sửa thông tin nhân viên", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        return v;
    }

    public void openDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.item_dialog_nhanvien, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        edtMaNv = dialog.findViewById(R.id.edtMaNv_itemNhanVien);
        edtHoTen = dialog.findViewById(R.id.edtHoTen_itemNhanVien);
        edtPass = dialog.findViewById(R.id.edtPassword_itemNhanVien);
        edtCccd = dialog.findViewById(R.id.edtCccd_itemNhanVien);
        edtSdt = dialog.findViewById(R.id.edtSdt_itemNhanVien);
        btnSave = dialog.findViewById(R.id.Add_NhanVien);
        btnHuy = dialog.findViewById(R.id.huyAdd_NhanVien);
        spinner = dialog.findViewById(R.id.spCoSo_itemNhanVien);
        listCoSo = new ArrayList<CoSo>();
        coSoDao = new CoSoDao(getContext());
        listCoSo = (ArrayList<CoSo>) coSoDao.GetAll();
        spinerAdaper = new CoSoSpinerAdaper(getContext(), listCoSo);
        spinner.setAdapter(spinerAdaper);
//
        if (listCoSo.isEmpty()) {
            Toast.makeText(getContext(), "Vui lòng thêm cơ sở trước", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
            return;
        }
//lấy mã cơ sở
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getContext(), "Chọn:" + listCoSo.get(i).getMaCoSo(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String maNv = edtMaNv.getText().toString().trim();
                String hoTen = edtHoTen.getText().toString().trim();
                String pass = edtPass.getText().toString().trim();
                String sdt = edtSdt.getText().toString().trim();
                String cccd = edtCccd.getText().toString().trim();
                CoSo objCs = listCoSo.get(spinner.getSelectedItemPosition());
                String coso = objCs.getMaCoSo();
                if (maNv.equals("")) {
                    Toast.makeText(getActivity(), "Vui lòng nhập mã nv", Toast.LENGTH_SHORT).show();
                    edtMaNv.requestFocus();
                    return;
                }
                if (hoTen.equals("")) {
                    Toast.makeText(getActivity(), "Vui lòng nhập họ tên", Toast.LENGTH_SHORT).show();
                    edtHoTen.requestFocus();
                    return;
                }
                if (pass.equals("")) {
                    Toast.makeText(getActivity(), "Vui lòng nhập pass", Toast.LENGTH_SHORT).show();
                    edtPass.requestFocus();
                    return;
                }
                if (cccd.equals("")) {
                    Toast.makeText(getActivity(), "Vui lòng nhập Cccd", Toast.LENGTH_SHORT).show();
                    edtCccd.requestFocus();
                    return;
                }

                if (sdt.equals("")) {
                    Toast.makeText(getActivity(), "Vui lòng nhập sđt", Toast.LENGTH_SHORT).show();
                    edtSdt.requestFocus();
                    return;
                }
                if (sdt.length() != 10 || !sdt.matches("\\d+")) {
                    Toast.makeText(getActivity(), "Số điện thoại không hợp lệ", Toast.LENGTH_SHORT).show();
                    return;
                }
                NhanVien nv = new NhanVien(maNv, coso, hoTen, pass, cccd, Integer.parseInt(sdt));
                if (nhanVienDao.insertNv(nv)) {
                    listNv.clear();
                    listNv.addAll(nhanVienDao.getAll());
                    adapter.notifyDataSetChanged();
                    Toast.makeText(getActivity(), "Add Succ", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                } else {
                    Toast.makeText(getActivity(), "Add fail", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    public void xoa(String Id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Cảnh báo");
        builder.setIcon(R.drawable.baseline_warning_24);
        builder.setMessage("Bạn có chắc chắn muốn xóa không");
        builder.setCancelable(true);
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                nhanVienDao.deleteNv(Id);
                capNhatLv();
                dialog.dismiss();
                Toast.makeText(getContext(), "Xóa thành công", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        builder.show();
    }
    void capNhatLv() {
        listNv = (ArrayList<NhanVien>) nhanVienDao.getAll();
        adapter = new NhanVienAdapter(getActivity(), this, listNv);
        lvNhanVien.setAdapter(adapter);
    }
}