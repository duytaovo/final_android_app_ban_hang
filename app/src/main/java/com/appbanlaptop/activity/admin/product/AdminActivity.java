package com.appbanlaptop.activity.admin.product;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appbanlaptop.R;
import com.appbanlaptop.adapter.admin.LaptopAdapter;
import com.appbanlaptop.model.Brand;
import com.appbanlaptop.model.Laptop;
import com.appbanlaptop.model.LaptopModel;
import com.appbanlaptop.retrofit.ApiShopLapTop;
import com.appbanlaptop.retrofit.RetrofitClient;
import com.appbanlaptop.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminActivity extends AppCompatActivity  {
    Button addSP,editSP;
    RecyclerView recyclerView;
    LaptopAdapter laptopAdapter;

    ApiShopLapTop apiShopLapTop;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    List<Brand> arrayBrand;
    List<Laptop> arrayLaptop,arrayLaptopGaming;
    public AppCompatActivity getActivity() {
        return this;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity_main);
        apiShopLapTop = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiShopLapTop.class);
        initView();
        initControl();
        getListLaptops("");
    }

    private void initControl(){
        addSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminActivity.this, AddSPActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView(){
        addSP = findViewById(R.id.bt_Them);
        editSP = findViewById(R.id.btnSuaDetailLaptop);
        recyclerView = findViewById(R.id.recycle_cl);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        // khoi tao list
        arrayBrand = new ArrayList<>();

        arrayLaptop = new ArrayList<>();
        arrayLaptopGaming = new ArrayList<>();
    }

    public void getListLaptops(String type) {
        Call<LaptopModel> call = apiShopLapTop.getLaptops(type);
        call.enqueue(new Callback<LaptopModel>() {
            @Override
            public void onResponse(Call<LaptopModel> call, Response<LaptopModel> response) {
                LaptopModel laptopModel = response.body();

                if (laptopModel.isSuccess()) {
                    arrayLaptopGaming = laptopModel.getResult();
                    for (int i = 0 ; i< arrayLaptopGaming.size();i++){
                        if(arrayLaptopGaming.get(i).getStatus() == 0){
                            arrayLaptop.add(arrayLaptopGaming.get(i));
                        }
                    }
                    laptopAdapter = new LaptopAdapter(arrayLaptop,AdminActivity.this);
                    recyclerView.setAdapter(laptopAdapter);
                }
            }
            @Override
            public void onFailure(Call<LaptopModel> call, Throwable t) {
                call.cancel();
            }
        });
    }


    @Override
    public void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }


}
