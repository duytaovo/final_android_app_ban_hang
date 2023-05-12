package com.appbanlaptop.activity.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.appbanlaptop.R;
import com.appbanlaptop.activity.admin.brand.QuanLyBrandActivity;
import com.appbanlaptop.activity.admin.order.DonShipperDaNhanActivity;
import com.appbanlaptop.activity.admin.order.QuanLyOrderActivity;
import com.appbanlaptop.activity.admin.product.AdminActivity;
import com.appbanlaptop.activity.admin.thongke.ThongKeActivity;
import com.appbanlaptop.activity.admin.user.QuanLyUsersActivity;

public class MenuActivity extends AppCompatActivity {
    Button btQuanLySP, btQuanLyBrand,btQuanLyUser,btQuanLyOrder,btThongKe, btDonHangShipperDaNhan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_admin);
        initView();
        initControl();
    }

    private void initView(){
        btQuanLySP = findViewById(R.id.btQuanLySP);
        btQuanLyBrand = findViewById(R.id.btQuanLyBrand);
        btQuanLyUser = findViewById(R.id.btQuanLyUser);
        btQuanLyOrder = findViewById(R.id.btQuanLyDonHang);
        btThongKe = findViewById(R.id.btThongKe);
        btDonHangShipperDaNhan = findViewById(R.id.btDonHangShipperXacNhan);

    }

    private void initControl(){
        btQuanLySP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                startActivity(intent);
            }
        });

        btQuanLyBrand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), QuanLyBrandActivity.class);
                startActivity(intent);
            }
        });

        btQuanLyUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), QuanLyUsersActivity.class);
                startActivity(intent);
            }
        });

        btQuanLyOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), QuanLyOrderActivity.class);
                startActivity(intent);
            }
        });

        btThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ThongKeActivity.class);
                startActivity(intent);
            }
        });

        btDonHangShipperDaNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DonShipperDaNhanActivity.class);
                startActivity(intent);
            }
        });


    }
}