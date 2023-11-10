package com.example.duan1_nhom9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duan1_nhom9.DAO.NhanVienDao;
import com.example.duan1_nhom9.Model.NhanVien;

public class Login_Activity extends AppCompatActivity {
    EditText edtUserName, edtPassWord;
    CheckBox chkLuuMk;
    Button btnLogin, btnHuy;
    NhanVienDao nhanVienDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtUserName = findViewById(R.id.edtUsername);
        edtPassWord = findViewById(R.id.edtPassword);
        chkLuuMk = findViewById(R.id.chkLuuMatKhau);
        btnLogin = findViewById(R.id.btnLogin);
        btnHuy = findViewById(R.id.btnHuy);
        nhanVienDao = new NhanVienDao(this);
//Đọc User,Pass
        SharedPreferences preferences = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        edtUserName.setText(preferences.getString("userName", ""));
        edtPassWord.setText((preferences.getString("passWord", "")));
        chkLuuMk.setChecked(preferences.getBoolean("remember", false));
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = edtUserName.getText().toString();
                String passWord = edtPassWord.getText().toString();
                if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(passWord)) {
                    Toast.makeText(Login_Activity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (nhanVienDao.CheckLogin(userName, passWord) > 0 || (userName.equals("admin") && passWord.equals("admin"))) {
                    Toast.makeText(Login_Activity.this, "Đăng nhập Succ", Toast.LENGTH_SHORT).show();
                    rememberUser(userName, passWord, chkLuuMk.isChecked());
                    Intent intent = new Intent(Login_Activity.this, Home_Activity.class);
                    intent.putExtra("user", userName);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(Login_Activity.this, "Đăng nhập thất bại. Vui lòng kiểm tra lại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtUserName.setText("");
                edtPassWord.setText("");

            }
        });
    }

    public void rememberUser(String user, String pass, boolean satus) {
        SharedPreferences preferences = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        if (!satus) {
            editor.clear();
        } else {
            editor.putString("userName", user);
            editor.putString("passWord", pass);
            editor.putBoolean("remember", satus);
        }
        editor.commit();
    }
}