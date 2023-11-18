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

import com.example.duan1_nhom9.Adapter.CoSoAdapter;
import com.example.duan1_nhom9.DAO.CoSoDao;
import com.example.duan1_nhom9.Model.CoSo;
import com.example.duan1_nhom9.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class CoSoFragment extends Fragment {

    RecyclerView rycCoSo;
    FloatingActionButton fltAddCoSo;
    EditText edtTimKiem;
    CoSoDao coSoDao;
    CoSoAdapter adapter;
    ArrayList<CoSo> tempList; //khai báo mảng đệm
    private ArrayList<CoSo> listcs = new ArrayList<CoSo>();

    public CoSoFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_co_so, container, false);
        rycCoSo = v.findViewById(R.id.rycCoSo);
        fltAddCoSo = v.findViewById(R.id.fltAddCoSo);
        edtTimKiem = v.findViewById(R.id.edtTimKiem);
        coSoDao = new CoSoDao(getActivity());
        listcs = (ArrayList<CoSo>) coSoDao.GetAll();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        tempList = (ArrayList<CoSo>) coSoDao.GetAll();
        rycCoSo.setLayoutManager(layoutManager);
        adapter = new CoSoAdapter(getActivity(), listcs);
        rycCoSo.setAdapter(adapter);
        fltAddCoSo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opendialogAdd();
            }
        });
        edtTimKiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//code
                listcs.clear();
                for (CoSo cs : tempList) {
                    if (cs.getMaCoSo().contains(charSequence.toString())) {
                        listcs.add(cs);
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

    private void opendialogAdd() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.item_add_coso, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        EditText edtTenCs = view.findViewById(R.id.edtTenCs_itemAddCs);
        EditText edtDiaChi = view.findViewById(R.id.edtDiaChi_itemAddCs);
        ImageView btnSave = view.findViewById(R.id.Add_Cs);
        ImageView btnHuy = view.findViewById(R.id.huyAdd_Cs);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenCs = edtTenCs.getText().toString();
                String dchi = edtDiaChi.getText().toString();
                if (tenCs.equals("")) {
                    Toast.makeText(getActivity(), "Vui lòng nhập tên cơ sở", Toast.LENGTH_SHORT).show();
                    edtTenCs.requestFocus();
                    return;
                }
                if (dchi.equals("")) {
                    Toast.makeText(getActivity(), "Vui lòng nhập địa chỉ", Toast.LENGTH_SHORT).show();
                    edtDiaChi.requestFocus();
                    return;
                }
                CoSo cs = new CoSo(tenCs, dchi);
                if (coSoDao.insertCoSo(cs)) {
                    listcs.clear();
                    listcs.addAll(coSoDao.GetAll());
                    adapter.notifyDataSetChanged();
                    Toast.makeText(getActivity(), "Add Succ", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                } else {
                    Toast.makeText(getActivity(), "Add Fail", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Huỷ Add", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }
}