package com.example.duan1_nhom9.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.duan1_nhom9.Adapter.GiayAdapter;
import com.example.duan1_nhom9.Adapter.LoaiGiaySpinerAdapter;
import com.example.duan1_nhom9.DAO.GiayDao;
import com.example.duan1_nhom9.DAO.LoaiGiayDao;
import com.example.duan1_nhom9.Model.Giay;
import com.example.duan1_nhom9.Model.LoaiGiay;
import com.example.duan1_nhom9.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class GiayFragment extends Fragment {
    ListView lvGiay;
    ArrayList<Giay> list;
    ArrayList<Giay> templist;
    Dialog dialog;
    FloatingActionButton fab;
    EditText edtMaGiay, edtTenGiay, edtGia, edtGhiChu, edtTmKiemGiay;
    Spinner spinner;
    ImageView btnSave, btnHuy;
    static GiayDao giayDao;
    GiayAdapter adapter;
    Giay item;
    LoaiGiaySpinerAdapter spinerAdapter;
    ArrayList<LoaiGiay> listlg;
    LoaiGiayDao loaiGiayDao;
    int maLoaiGiay, position;

    public GiayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_giay, container, false);
        //ánh xạ
        lvGiay = v.findViewById(R.id.lvGiay);
        fab = v.findViewById(R.id.fltAddGiay);
        edtTmKiemGiay = v.findViewById(R.id.edtTimKiemGiay);
        //
        giayDao = new GiayDao(getActivity());
        list = (ArrayList<Giay>) giayDao.GetAll();
        templist = (ArrayList<Giay>) giayDao.GetAll();
        adapter = new GiayAdapter(getActivity(), this, list);
        lvGiay.setAdapter(adapter);
        //Tìm Kiếm
        edtTmKiemGiay.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //code
                list.clear();
                for (Giay giay : templist
                ) {
                    if (giay.getTenGiay().contains(charSequence.toString())) {
                        list.add(giay);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog(getActivity(), 0);
            }
        });
        lvGiay.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                item = list.get(i);
                openDialog(getActivity(), 1); //update
                return false;
            }
        });
        return v;
    }

    public void capNhapLv() {
        list = (ArrayList<Giay>) giayDao.GetAll();
        adapter = new GiayAdapter(getActivity(), this, list);
        lvGiay.setAdapter(adapter);
    }

    public void openDialog(Context context, int type) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.item_dialog_giay); //lấy dialog
        //ánh xạ
        edtMaGiay = dialog.findViewById(R.id.edtMaGiay_itemGiay);
        edtTenGiay = dialog.findViewById(R.id.edtTenGiay_itemGiay);
        edtGia = dialog.findViewById(R.id.edtGia_itemGiay);
        edtGhiChu = dialog.findViewById(R.id.edtGhiChu_itemGiay);
        spinner = dialog.findViewById(R.id.spLoaiGiay_itemGiay);
        btnSave = dialog.findViewById(R.id.btnAdd_Giay);
        btnHuy = dialog.findViewById(R.id.btnHuyAdd_Giay);
        //
        listlg = new ArrayList<LoaiGiay>();
        loaiGiayDao = new LoaiGiayDao(context);
        listlg = (ArrayList<LoaiGiay>) loaiGiayDao.GetAll();
        spinerAdapter = new LoaiGiaySpinerAdapter(context, listlg);
        spinner.setAdapter(spinerAdapter);
        //check nếu listLoaiGiay Null
        if (listlg.isEmpty()) {
            Toast.makeText(context, "Vui lòng thêm loại giày trước", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
            return;
        }
//Lấy mã loại giày
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                maLoaiGiay = listlg.get(i).getMaLoai();
                Toast.makeText(context, "Chọn " + listlg.get(i).getTenLoai(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //kiểm tra type insert 0 hay update 1
        edtMaGiay.setEnabled(false);
        if (type != 0) {
            edtMaGiay.setText(String.valueOf(item.getMaGiay()));
            edtTenGiay.setText(item.getTenGiay());
            edtGia.setText(String.valueOf(item.getGiaMua()));
            edtGhiChu.setText(item.getMoTa());
            for (int i = 0; i < listlg.size(); i++) {
                if (item.getMaLoai() == (listlg.get(i).getMaLoai())) {
                    position = i;
                }
                Log.i("zzzzzzzzzzzzz", "posGiay" + position);
                spinner.setSelection(position);
            }
        }
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             try {
                 item = new Giay();
                 item.setTenGiay(edtTenGiay.getText().toString());
                 item.setGiaMua(Integer.parseInt(edtGia.getText().toString()));
                 item.setMoTa(edtGhiChu.getText().toString());
                 item.setMaLoai(maLoaiGiay);
                 if (validate() > 0) {
                     if (type == 0) {
//insert
                         if (giayDao.insertGiay(item)) {
                             Toast.makeText(context, "Add Succ", Toast.LENGTH_SHORT).show();
                         } else {
                             Toast.makeText(context, "Add Fail", Toast.LENGTH_SHORT).show();
                         }
                     } else {
                         item.setMaGiay(Integer.parseInt(edtMaGiay.getText().toString()));
                         if (giayDao.updateGiay(item)) {
                             Toast.makeText(context, "Update Succ", Toast.LENGTH_SHORT).show();
                         } else {
                             Toast.makeText(context, "Update Fail", Toast.LENGTH_SHORT).show();
                         }
                     }
                     capNhapLv();
                     dialog.dismiss();
                 }
             }catch (Exception e){
                 Toast.makeText(context, "Giá là số", Toast.LENGTH_SHORT).show();
             }
            }
        });
        dialog.show();
    }

    public int validate() {
        int check = 1;
        if (edtTenGiay.length() == 0 || edtGia.length() == 0 || edtGhiChu.length() == 0) {
            Toast.makeText(getActivity(), "Vui lòng nhập dầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        } else {
            try {
                int gia = Integer.parseInt(edtGia.getText().toString());
                if (gia < 0) {
                    Toast.makeText(getActivity(), "Nhập giá >0", Toast.LENGTH_SHORT).show();
                    edtGia.requestFocus();
                    check = -1;
                }
            } catch (Exception e) {
                Toast.makeText(getActivity(), "Giá là số", Toast.LENGTH_SHORT).show();
                edtGia.requestFocus();
                check = -1;
            }
        }
        return check;
    }
    public void xoa(int Id){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Cảnh báo");
        builder.setIcon(R.drawable.baseline_warning_24);
        builder.setMessage("Bạn có chắc chắn muốn xóa không");
        builder.setCancelable(true);
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                giayDao.deleteGiay(Id);
                capNhapLv();
                dialog.cancel();
                Toast.makeText(getContext(), "Delete Succ", Toast.LENGTH_SHORT).show();
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
}