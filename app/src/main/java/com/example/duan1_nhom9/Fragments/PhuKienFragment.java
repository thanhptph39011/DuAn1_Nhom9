package com.example.duan1_nhom9.Fragments;

import android.app.Dialog;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.duan1_nhom9.Adapter.KhachHangAdapter;
import com.example.duan1_nhom9.Adapter.PhuKienAdapter;
import com.example.duan1_nhom9.DAO.KhachHangDao;
import com.example.duan1_nhom9.DAO.PhuKienDao;
import com.example.duan1_nhom9.Model.KhachHang;
import com.example.duan1_nhom9.Model.PhuKien;
import com.example.duan1_nhom9.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class PhuKienFragment extends Fragment {

    RecyclerView rycPhuKien;
    FloatingActionButton fltAddPhuKien;
    PhuKienDao phuKienDao;
    PhuKienAdapter adapter;
    EditText edtTim;
    ArrayList<PhuKien> tempList;
    private ArrayList<PhuKien> list = new ArrayList<>();

    public PhuKienFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_phu_kien, container, false);
        rycPhuKien = view.findViewById(R.id.rycPhuKien);
        fltAddPhuKien = view.findViewById(R.id.fltAddPhuKien);
        edtTim = view.findViewById(R.id.edtTimKiemPk);
        phuKienDao = new PhuKienDao(getActivity());
        list = (ArrayList<PhuKien>) phuKienDao.GetAll();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        tempList =(ArrayList<PhuKien>)phuKienDao.GetAll();
        rycPhuKien.setLayoutManager(layoutManager);
        adapter = new PhuKienAdapter(getActivity(),list);
        rycPhuKien.setAdapter(adapter);
        fltAddPhuKien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendialogAdd();
            }
        });
        edtTim.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                list.clear();
                for (PhuKien pk : tempList) {
                    if (pk.getTenPhuKien().contains(charSequence.toString())) {
                        list.add(pk);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        return view;
    }

    private void opendialogAdd() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.item_add_phu_kien, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        EditText edtTenPK_itemAddPhuKien = view.findViewById(R.id.edtTenPK_itemAddPhuKien);
        EditText edtGiaPK_itemAddPhuKien = view.findViewById(R.id.edtGia_itemAddPhuKien);
        ImageView btnSave_itemAddPhuKien = view.findViewById(R.id.btnSave_itemAddPhuKien);
        ImageView btnHuy_itemAddPhuKien = view.findViewById(R.id.btnHuy_itemAddPhuKien);
        btnSave_itemAddPhuKien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tenPk = edtTenPK_itemAddPhuKien.getText().toString();
                String giaTien = edtGiaPK_itemAddPhuKien.getText().toString();
                if (tenPk.isEmpty() || giaTien.isEmpty()) {
                    Toast.makeText(getActivity(), "Nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {

                    PhuKien pk = new PhuKien(tenPk, Integer.parseInt(giaTien));
                    if (phuKienDao.insertPhuKien(pk)) {
                        list.clear();
                        list.addAll(phuKienDao.GetAll());
                        adapter.notifyDataSetChanged();
                        dialog.dismiss();
                        Toast.makeText(getActivity(), "Add Thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), "Add Fail", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(getActivity(), "Giá tiền không đúng định dạng", Toast.LENGTH_SHORT).show();
                    return;
                }

//

            }
        });
        btnHuy_itemAddPhuKien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Thoát Add", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }
}