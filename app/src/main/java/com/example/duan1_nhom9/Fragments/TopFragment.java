package com.example.duan1_nhom9.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.duan1_nhom9.Adapter.ImageAdapter;
import com.example.duan1_nhom9.Adapter.TopAdapter;
import com.example.duan1_nhom9.DAO.ThongKeDao;
import com.example.duan1_nhom9.Model.Photo;
import com.example.duan1_nhom9.Model.Top;
import com.example.duan1_nhom9.R;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;


public class TopFragment extends Fragment {
    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private ImageAdapter adapter;
    private List<Photo> mlist;
    ListView lvTop;
    ArrayList<Top> list;
    TopAdapter Topadapter;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            int anhHienTai = viewPager.getCurrentItem();
            int fullItems = mlist.size();

            if (anhHienTai == fullItems - 1) {
                // Nếu đang hiển thị ảnh cuối cùng, chuyển đến ảnh đầu tiên
                viewPager.setCurrentItem(0);
            } else {
                // Nếu không, chuyển đến ảnh kế tiếp
                viewPager.setCurrentItem(anhHienTai+ 1);
            }

            handler.postDelayed(this, 2000);
        }
    };

    public TopFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_top, container, false);
        viewPager = v.findViewById(R.id.viewPager);
        circleIndicator = v.findViewById(R.id.circleId);
        //
        lvTop=v.findViewById(R.id.lvTop);
        ThongKeDao thongKeDAO= new ThongKeDao(getActivity());
        list=(ArrayList<Top>) thongKeDAO.getTop();
        Topadapter = new TopAdapter(getActivity(),this,list);
        lvTop.setAdapter(Topadapter);
        //
        mlist = getListPhoto();
        adapter = new ImageAdapter(getActivity(), getListPhoto());
        viewPager.setAdapter(adapter);
        circleIndicator.setViewPager(viewPager);
        handler.postDelayed(runnable, 2000);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable, 2000);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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