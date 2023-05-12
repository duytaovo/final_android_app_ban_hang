package com.appbanlaptop.activity.admin.thongke;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.appbanlaptop.R;

import java.util.ArrayList;

public class ThongKeActivity extends AppCompatActivity {
    Button btThongKeSanPhamBanDuoc,btThongKeDoanhThu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke);
        initView();
        initControl();
    }

    private void initView(){
        btThongKeSanPhamBanDuoc = findViewById(R.id.btThongKeSanPhamBanDuoc);
        btThongKeDoanhThu = findViewById(R.id.btThongKeDoanhThu);
    }

    private void initControl(){
        btThongKeSanPhamBanDuoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ThongKeSanPhamBanDuocActivity.class);
                startActivity(intent);
            }
        });

        btThongKeDoanhThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ThongKeDoanhThuActivity.class);
                startActivity(intent);
            }
        });
    }
}