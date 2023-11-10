package com.example.duan1_nhom9.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duan1_nhom9.Adapter.ImageAdapter;
import com.example.duan1_nhom9.Model.Photo;
import com.example.duan1_nhom9.R;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;


public class TopFragment extends Fragment {
    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private ImageAdapter adapter;

    public TopFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_top, container, false);
        viewPager = v.findViewById(R.id.viewPager);
        circleIndicator =v.findViewById(R.id.circleId);
        adapter = new ImageAdapter(getActivity(), getListPhoto());
        viewPager.setAdapter(adapter);
        circleIndicator.setViewPager(viewPager);
        adapter.registerDataSetObserver(circleIndicator.getDataSetObserver());
        return v;
    }
    private List<Photo> getListPhoto() {
        List<Photo> list = new ArrayList<>();
        list.add(new Photo(R.drawable.anh1));
        list.add(new Photo(R.drawable.anh2));
        list.add(new Photo(R.drawable.anh3));
        list.add(new Photo(R.drawable.anh4));
        list.add(new Photo(R.drawable.anh5));
        return list;
    }
}