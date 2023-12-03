package com.example.duan1_nhom9;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1_nhom9.Adapter.ImageAdapter;
import com.example.duan1_nhom9.DAO.NhanVienDao;
import com.example.duan1_nhom9.Fragments.ChangePassFragment;
import com.example.duan1_nhom9.Fragments.CoSoFragment;
import com.example.duan1_nhom9.Fragments.DoanhThuFragment;
import com.example.duan1_nhom9.Fragments.GiayFragment;
import com.example.duan1_nhom9.Fragments.HoaDonFragment;
import com.example.duan1_nhom9.Fragments.KhachHangFragment;
import com.example.duan1_nhom9.Fragments.LoaiGiayFragment;
import com.example.duan1_nhom9.Fragments.NhanVienFragment;
import com.example.duan1_nhom9.Fragments.TopFragment;
import com.example.duan1_nhom9.Fragments.TopNvFragment;
import com.example.duan1_nhom9.Model.NhanVien;
import com.example.duan1_nhom9.Model.Photo;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class Home_Activity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView nav;
    BottomNavigationView bottomNavigationView;
    View view;
    TextView tvuser;
    NhanVienDao nhanVienDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //ánh xạ
        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.toolbar);
        nav = findViewById(R.id.nav);
        bottomNavigationView = findViewById(R.id.botomMenu);
//đổi màu title Toolbar
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
// gán toolbar
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        //sét mẫu icon về bản gốc
        nav.setItemIconTintList(null);
        view = nav.getHeaderView(0);
        tvuser = view.findViewById(R.id.tvWellcome);
        Intent intent = getIntent();
        String user = intent.getStringExtra("user");
        if (user.equalsIgnoreCase("admin")) {
            nav.getMenu().findItem(R.id.QuanLyNhanVien).setVisible(true);
            bottomNavigationView.getMenu().findItem(R.id.coSo).setVisible(true);
            Toast.makeText(this, "Wellcome Admin", Toast.LENGTH_SHORT).show();
        } else {
            nhanVienDao = new NhanVienDao(this);
            NhanVien nv = nhanVienDao.getID(user);
            String userName = nv.getHoTen();
            tvuser.setText("Wellcome: " + userName);
            nav.getMenu().findItem(R.id.DoiMk).setVisible(true);
            bottomNavigationView.getMenu().findItem(R.id.topNv).setVisible(false);
            Toast.makeText(this, "Wellcome nhân viên", Toast.LENGTH_SHORT).show();
        }
        HoaDonFragment fragment = new HoaDonFragment();
        replaceFrg(fragment);

        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.QuanLyHoaDon) {
                    HoaDonFragment hoaDonFragment = new HoaDonFragment();
                    replaceFrg(hoaDonFragment);
                } else if (item.getItemId() == R.id.QuanLyLoaiGiay) {
                    LoaiGiayFragment loaiGiayFragment = new LoaiGiayFragment();
                    replaceFrg(loaiGiayFragment);
                } else if (item.getItemId() == R.id.QuanLyGiay) {
                    GiayFragment giayFragment = new GiayFragment();
                    replaceFrg(giayFragment);
                } else if (item.getItemId() == R.id.QuanLyKhachHang) {
                    KhachHangFragment khachHangFragment = new KhachHangFragment();
                    replaceFrg(khachHangFragment);
                } else if (item.getItemId() == R.id.DoiMk) {
                    ChangePassFragment changePassFragment = new ChangePassFragment();
                    replaceFrg(changePassFragment);
                } else if (item.getItemId() == R.id.QuanLyNhanVien) {
                    NhanVienFragment nhanVienFragment = new NhanVienFragment();
                    replaceFrg(nhanVienFragment);

                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Home_Activity.this);
                    builder.setTitle("Cảnh báo");
                    builder.setIcon(R.drawable.baseline_warning_24);
                    builder.setMessage("Bạn có muốn đăng xuất k?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(Home_Activity.this, "Đăng xuất Succ", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Home_Activity.this, Login_Activity.class);
                            startActivity(intent);
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.show();
                }
                getSupportActionBar().setTitle(item.getTitle()); //khi click vào item hiển thị tieu de lên toolbar
                drawerLayout.close(); //đóng nav
                return true;
            }
        });
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.coSo) {
                    CoSoFragment coSoFragment = new CoSoFragment();
                    replaceFrg(coSoFragment);
                } else if (item.getItemId() == R.id.topgiayMuaNhieuNhat) {
                    TopFragment fragment = new TopFragment();
                    replaceFrg(fragment);
                } else if (item.getItemId() == R.id.topNv) {
                    TopNvFragment fragment1 = new TopNvFragment();
                    replaceFrg(fragment1);
                } else {
                    DoanhThuFragment doanhThuFragment = new DoanhThuFragment();
                    replaceFrg(doanhThuFragment);
                }
                getSupportActionBar().setTitle(item.getTitle());
                return true;
            }
        });
    }

    public void replaceFrg(Fragment frg) {
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.frmnav, frg).commit();
    }

}