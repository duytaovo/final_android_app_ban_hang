package com.appbanlaptop.activity.admin.brand;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ViewFlipper;

import com.appbanlaptop.R;
import com.appbanlaptop.adapter.LaptopAdapter;
import com.appbanlaptop.adapter.admin.BrandAdapter;
import com.appbanlaptop.model.Brand;
import com.appbanlaptop.model.BrandModel;
import com.appbanlaptop.model.LaptopModel;
import com.appbanlaptop.retrofit.ApiShopLapTop;
import com.appbanlaptop.retrofit.RetrofitClient;
import com.appbanlaptop.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuanLyBrandActivity extends AppCompatActivity {
    ApiShopLapTop apiShopLapTop;
    com.appbanlaptop.adapter.admin.BrandAdapter brandAdapter;
    List<Brand> arrayBrand;
    RecyclerView recyclerViewBrand;

    Button addBrand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_brand);
        apiShopLapTop = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiShopLapTop.class);
        AnhXa();
        initControl();
        getListBrands();
    }
    private void initControl(){
        addBrand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AddBrandActivity.class);
                startActivity(intent);
            }
        });

    }
    public void getListBrands() {
        Call<BrandModel> call = apiShopLapTop.getBrandsAdmin();
        call.enqueue(new Callback<BrandModel>() {
            @Override
            public void onResponse(Call<BrandModel> call, Response<BrandModel> response) {
                BrandModel brandModel = response.body();
                if (brandModel.isSuccess()) {
                    arrayBrand = brandModel.getResult();
                    brandAdapter = new BrandAdapter(arrayBrand);
                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(QuanLyBrandActivity.this, 2);
                    recyclerViewBrand.setLayoutManager(layoutManager);
                    recyclerViewBrand.setHasFixedSize(true);
                    recyclerViewBrand.setAdapter(brandAdapter);
                }
            }
            @Override
            public void onFailure(Call<BrandModel> call, Throwable t) {
                call.cancel();
            }
        });
    }
    private void AnhXa() {
        //AnhXa
        recyclerViewBrand = findViewById(R.id.recycle_clBrand);
        addBrand = findViewById(R.id.bt_Add_Brand);

        // khoi tao list
        arrayBrand = new ArrayList<>();
    }
}