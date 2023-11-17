package com.example.duan1_nhom9.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_nhom9.Adapter.LoaiGiayAdapter;
import com.example.duan1_nhom9.DAO.LoaiGiayDao;
import com.example.duan1_nhom9.Model.CoSo;
import com.example.duan1_nhom9.Model.LoaiGiay;
import com.example.duan1_nhom9.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class LoaiGiayFragment extends Fragment {
    FloatingActionButton btnAdd;
    LoaiGiayDao loaiGiayDao;
    EditText edtTimKiemLG;
    LoaiGiayAdapter adapter;
    ArrayList<LoaiGiay> tempList; //khai báo mảng đệm
    RecyclerView rcLG;
    ArrayList<LoaiGiay> listlg = new ArrayList<LoaiGiay>();

    public LoaiGiayFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_loai_giay, container, false);
        rcLG=v.findViewById(R.id.rycLoaiGiay);
        btnAdd=v.findViewById(R.id.fltAddLoaiGiay);
        edtTimKiemLG = v.findViewById(R.id.edtTimKiemLoaiGiay);
        loaiGiayDao =new LoaiGiayDao(getActivity());
        listlg = (ArrayList<LoaiGiay>) loaiGiayDao.GetAll();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        tempList = (ArrayList<LoaiGiay>) loaiGiayDao.GetAll();
        rcLG.setLayoutManager(layoutManager);
        adapter = new LoaiGiayAdapter(getActivity(), listlg);
        rcLG.setAdapter(adapter);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog();
            }
        });
        edtTimKiemLG.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                listlg.clear();
                for (LoaiGiay lg : tempList) {
                    if (lg.getTenLoai().contains(charSequence.toString())) {
                        listlg.add(lg);
                    }
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        return v;
    }
    void dialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getLayoutInflater();

        View view=inflater.inflate(R.layout.item_add_loai_giay,null);
        builder.setView(view);
        AlertDialog dialog= builder.create();
        dialog.show();
        EditText edTen= view.findViewById(R.id.edtTenLoai_itemAddGiay);
        EditText edLoaiHang = view.findViewById(R.id.edtLoaiHang_itemAddGiay);
        ImageView btnLuuLG= view.findViewById(R.id.Add_Lg);
        ImageView btnHuyLG= view.findViewById(R.id.huyAdd_Lg);
        btnLuuLG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenLoai = edTen.getText().toString();
                String loaiHang = edLoaiHang.getText().toString();
                if (tenLoai.equals("")) {
                    Toast.makeText(getActivity(), "Nhập tên loại", Toast.LENGTH_SHORT).show();
                    edTen.requestFocus();
                    return;
                }
                if (loaiHang.equals("")) {
                    Toast.makeText(getActivity(), "Nhập tên loại hàng", Toast.LENGTH_SHORT).show();
                    edLoaiHang.requestFocus();
                    return;
                }
                LoaiGiay lg = new LoaiGiay(tenLoai,loaiHang);
                if (loaiGiayDao.insertLoaiGiay(lg)) {
                    listlg.clear();
                    listlg.addAll(loaiGiayDao.GetAll());
                    adapter.notifyDataSetChanged();
                    Toast.makeText(getActivity(), "Add Succ", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                } else {
                    Toast.makeText(getActivity(), "Add Fail", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnHuyLG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    dialog.dismiss();

            }
        });

    }
}