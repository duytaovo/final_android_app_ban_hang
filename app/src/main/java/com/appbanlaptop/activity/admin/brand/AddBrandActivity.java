package com.appbanlaptop.activity.admin.brand;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.JsonReader;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.appbanlaptop.R;
import com.appbanlaptop.activity.admin.order.DetailOrderActivity;
import com.appbanlaptop.activity.admin.order.QuanLyOrderActivity;
import com.appbanlaptop.adapter.admin.BrandAdapter;
import com.appbanlaptop.databinding.ActivityAddSpBinding;
import com.appbanlaptop.model.Brand;
import com.appbanlaptop.model.BrandModel;
import com.appbanlaptop.model.Laptop;
import com.appbanlaptop.model.LaptopModel;
import com.appbanlaptop.model.MessageModel;
import com.appbanlaptop.retrofit.ApiShopLapTop;
import com.appbanlaptop.retrofit.RetrofitClient;
import com.appbanlaptop.utils.Utils;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddBrandActivity extends AppCompatActivity {
    EditText etNameBrand,etAddressBrand,etImageBrand;
    Button btAddBrand;
    ApiShopLapTop apiShopLapTop;
    int brandId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_brand);
        apiShopLapTop = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiShopLapTop.class);
        brandId = getIntent().getIntExtra("id", -1);
        if(brandId != -1){
            getBrandDetail(String.valueOf(brandId));
        }
        initView();
        initData();
    }
    private void initView() {
        etNameBrand = findViewById(R.id.etNameBrand);
        etAddressBrand = findViewById(R.id.etAddressBrand);
        etImageBrand = findViewById(R.id.etImageUrl_Brand);
        btAddBrand = findViewById(R.id.btAddBrand);
    }

    private void initData() {



        if(brandId != -1){
            btAddBrand.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    updateBrand();
                    finish();
                }
            });
        }
        else{
            btAddBrand.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addBrand();
                    finish();
//                notifyDataSetChanged();

                }
            });
        }
    }
    private void addBrand(){
        try {
            String str_name = etNameBrand.getText().toString().trim();
            String str_address = etAddressBrand.getText().toString().trim();
            String str_image_url = etImageBrand.getText().toString().trim();
            System.out.println(str_name);
            if( TextUtils.isEmpty(str_name)){
                Toast.makeText(getApplicationContext(),"vui long nhap day du",Toast.LENGTH_LONG).show();
            } else {

                Call<BrandModel> call = apiShopLapTop.insertBrand(str_name,str_address,str_image_url);
                call.enqueue(new Callback<BrandModel>() {
                    @Override
                    public void onResponse(Call<BrandModel> call, Response<BrandModel> response) {
                        BrandModel brandModel = response.body();
                        if (brandModel.isSuccess()) {
                            Toast.makeText(AddBrandActivity.this, "Thêm thương hiệu thành công.", Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<BrandModel> call, Throwable t) {
                        call.cancel();
                        System.out.println(t);
                        Toast.makeText(AddBrandActivity.this, "Thêm thương hiệu thất bại.", Toast.LENGTH_LONG).show();

                    }
                });
            }
        }
        catch (Exception e){
            System.out.println("Lỗi" + e);
        }
}
    private void getBrandDetail(String id) {
        Call<BrandModel> call = apiShopLapTop.getBrandDetail(id);
        call.enqueue(new Callback<BrandModel>() {
            @Override
            public void onResponse(Call<BrandModel> call, Response<BrandModel> response) {
                BrandModel brandModel = response.body();
                if(brandModel.isSuccess()){
                    setDataResponseToView(brandModel);
                }
            }
            @Override
            public void onFailure(Call<BrandModel> call, Throwable t) {
                call.cancel();
                System.out.println(t);
            }
        });
    }

    private void setDataResponseToView(BrandModel brandModel) {
        try {
            Brand brand = brandModel.getResult().get(0);
            etNameBrand.setText(brand.getName());
            etAddressBrand.setText(brand.getAddress());
            etImageBrand.setText(brand.getImage_url());
            btAddBrand.setText("Cập nhật thương hiệu");

        }
        catch (Exception e){
            System.out.println(e);
        }

    }
    private void updateBrand(){
        try {
            int brand_id = brandId;
            String str_name = etNameBrand.getText().toString().trim();
            String str_address = etAddressBrand.getText().toString().trim();
            String str_image_url = etImageBrand.getText().toString().trim();
            System.out.println("id"+ brand_id);
            if( TextUtils.isEmpty(str_name) || TextUtils.isEmpty(str_address) || TextUtils.isEmpty(str_image_url)){
                Toast.makeText(getApplicationContext(),"Vui lòng nhập đầy đủ",Toast.LENGTH_LONG).show();
            } else {

                Call<BrandModel> call = apiShopLapTop.updateBrand(brand_id,str_name,str_address,str_image_url);
                call.enqueue(new Callback<BrandModel>() {
                    @Override
                    public void onResponse(Call<BrandModel> call, Response<BrandModel> response) {
                        BrandModel brandModel = response.body();
                        if (brandModel.isSuccess()) {
                            Toast.makeText(AddBrandActivity.this, "Cập nhật thành công.", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(AddBrandActivity.this, QuanLyBrandActivity.class);
                            AddBrandActivity.this.startActivity(intent);
                        }
                    }
                    @Override
                    public void onFailure(Call<BrandModel> call, Throwable t) {
                        call.cancel();
                        Toast.makeText(AddBrandActivity.this, "Cập nhật  thất bại.", Toast.LENGTH_LONG).show();

                    }
                });
            }
        }
        catch (Exception e){
            System.out.println("Lỗi" + e);
        }
    }
}