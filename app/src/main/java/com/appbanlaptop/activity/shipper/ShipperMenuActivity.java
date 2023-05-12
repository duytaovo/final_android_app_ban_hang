package com.appbanlaptop.activity.shipper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.appbanlaptop.R;
import com.appbanlaptop.activity.admin.brand.QuanLyBrandActivity;
import com.appbanlaptop.activity.admin.order.QuanLyOrderActivity;
import com.appbanlaptop.activity.admin.product.AdminActivity;
import com.appbanlaptop.activity.admin.thongke.ThongKeActivity;
import com.appbanlaptop.activity.admin.user.QuanLyUsersActivity;

public class ShipperMenuActivity extends AppCompatActivity {

    Button btDonDaXN, btDonDaGiao,btDonDaHuy,btDonDaNhan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_shipper);
        initView();
        initControl();
    }

    private void initView(){
        btDonDaXN = findViewById(R.id.btNhanDonShipper);
        btDonDaGiao = findViewById(R.id.btDonDaGiaoShipper);
        btDonDaHuy = findViewById(R.id.btDonDaHuyShipper);
        btDonDaNhan = findViewById(R.id.btDonDaNhanShipper);

    }

    private void initControl(){
        btDonDaXN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DonDaXacNhanActivity.class);
                startActivity(intent);
            }
        });

        btDonDaGiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DonDaGiaoActivity.class);
                startActivity(intent);
            }
        });

        btDonDaHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DonDaHuyActivity.class);
                startActivity(intent);
            }
        });
        btDonDaNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DonDaNhanCuaShipperActivity.class);
                startActivity(intent);
            }
        });
    }
}