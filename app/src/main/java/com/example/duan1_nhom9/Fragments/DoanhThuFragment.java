package com.example.duan1_nhom9.Fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1_nhom9.Adapter.CoSoSpinerAdaper;
import com.example.duan1_nhom9.DAO.CoSoDao;
import com.example.duan1_nhom9.DAO.HoaDonCtDao;
import com.example.duan1_nhom9.DAO.ThongKeDao;
import com.example.duan1_nhom9.Model.CoSo;
import com.example.duan1_nhom9.Model.HoaDonCt;
import com.example.duan1_nhom9.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class DoanhThuFragment extends Fragment {
    EditText edtStartDate, edtEndDate;
    Button btnDoanhThu,btnDoanhThuAll;
    TextView tvDoanhThu;
    Spinner spinner;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    int mYear, mMonth, mDay;
    CoSoSpinerAdaper spinerAdaper;
    ArrayList<CoSo> listCoSo;
    CoSoDao coSoDao;

    public DoanhThuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_doanh_thu, container, false);
        edtStartDate = v.findViewById(R.id.edtStartDate);
        edtEndDate = v.findViewById(R.id.edtEndDate);
        btnDoanhThu = v.findViewById(R.id.btnDoanhThu);
        btnDoanhThuAll =v.findViewById(R.id.btnDoanhThuAll);
        tvDoanhThu = v.findViewById(R.id.tvDoanhThu);
        spinner =v.findViewById(R.id.spinerCoSo);
        //
        listCoSo = new ArrayList<CoSo>();
        coSoDao = new CoSoDao(getContext());
        listCoSo = (ArrayList<CoSo>) coSoDao.GetAll();
        spinerAdaper = new CoSoSpinerAdaper(getContext(), listCoSo);
        spinner.setAdapter(spinerAdaper);
        //
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        edtStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d = new DatePickerDialog(getActivity(), 0, mDateTuNgay, mYear, mMonth, mDay);
                d.show();
            }
        });
        edtEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d = new DatePickerDialog(getActivity(), 0, mDateDenNgay, mYear, mMonth, mDay);
                d.show();
            }
        });
        btnDoanhThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tuNgay = edtStartDate.getText().toString().trim();
                String denNgay = edtEndDate.getText().toString().trim();
                CoSo objCs = listCoSo.get(spinner.getSelectedItemPosition());
                String coso = objCs.getMaCoSo();
                ThongKeDao thongKeDao = new ThongKeDao(getActivity());
                tvDoanhThu.setText("Doanh Thu: " + thongKeDao.getDoanhThuTheoCoSo(tuNgay,denNgay,coso) + "$");
            }
        });
        btnDoanhThuAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tuNgay = edtStartDate.getText().toString().trim();
                String denNgay = edtEndDate.getText().toString().trim();
                ThongKeDao thongKeDao = new ThongKeDao(getActivity());
                tvDoanhThu.setText("Doanh Thu: " + thongKeDao.getDoanhThu(tuNgay,denNgay) + "$");
            }
        });
        return v;
    }

    DatePickerDialog.OnDateSetListener mDateTuNgay = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mYear = year;
            mMonth = month;
            mDay = dayOfMonth;
            GregorianCalendar c = new GregorianCalendar(mYear, mMonth, mDay);
            edtStartDate.setText(sdf.format(c.getTime()));
        }
    };

    DatePickerDialog.OnDateSetListener mDateDenNgay = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mYear = year;
            mMonth = month;
            mDay = dayOfMonth;
            GregorianCalendar c = new GregorianCalendar(mYear, mMonth, mDay);
            edtEndDate.setText(sdf.format(c.getTime()));
        }
    };
}