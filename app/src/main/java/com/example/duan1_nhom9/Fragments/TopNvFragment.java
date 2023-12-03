package com.example.duan1_nhom9.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.duan1_nhom9.Adapter.TopNvAdapter;
import com.example.duan1_nhom9.DAO.ThongKeDao;
import com.example.duan1_nhom9.Model.TopNv;
import com.example.duan1_nhom9.R;

import java.util.ArrayList;


public class TopNvFragment extends Fragment {
    ListView lvTopNv;
    ArrayList<TopNv> list;
    TopNvAdapter topNvAdapter;

    public TopNvFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_top_nv, container, false);
        lvTopNv=view.findViewById(R.id.lvTopnv);
        ThongKeDao thongKeDAO= new ThongKeDao(getActivity());
        list=(ArrayList<TopNv>) thongKeDAO.getTop10NhanVienDoanhThu();
        topNvAdapter = new TopNvAdapter(getActivity(),this,list);
        lvTopNv.setAdapter(topNvAdapter);

        return view;
    }
}