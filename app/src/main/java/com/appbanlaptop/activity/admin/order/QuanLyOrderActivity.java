package com.appbanlaptop.activity.admin.order;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.appbanlaptop.R;
import com.appbanlaptop.adapter.LaptopAdapter;
import com.appbanlaptop.adapter.admin.OrderAdapter;
import com.appbanlaptop.adapter.admin.UserAdapter;
import com.appbanlaptop.model.Laptop;
import com.appbanlaptop.model.LaptopModel;
import com.appbanlaptop.model.MessageModel;
import com.appbanlaptop.model.Order;
import com.appbanlaptop.model.OrderHistory;
import com.appbanlaptop.model.OrderModel;
import com.appbanlaptop.model.User;
import com.appbanlaptop.model.UserModel;
import com.appbanlaptop.retrofit.ApiShopLapTop;
import com.appbanlaptop.retrofit.RetrofitClient;
import com.appbanlaptop.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuanLyOrderActivity extends AppCompatActivity {

    RecyclerView recyclerViewStatus1, recyclerViewStatus2, recyclerViewStatus3, recyclerViewStatus4,recyclerViewStatus0,recyclerViewStatus_huy;
    OrderAdapter orderAdapter;
    List<OrderHistory> arrayStatus1, arrayStatus2, arrayStatus3,arrayStatus4,arrayStatus0,arrayStatus_huy;

    ApiShopLapTop apiShopLapTop;
    List<OrderHistory> orderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_order);
        apiShopLapTop = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiShopLapTop.class);
        initView();
        getListLaptops(0);
        getListLaptops(1);
        getListLaptops(2);
        getListLaptops(3);
        getListLaptops(4);
        getListLaptops(-1);
    }

    private void initView(){
        recyclerViewStatus1 = findViewById(R.id.recyclerViewStatus_1);
        recyclerViewStatus2 = findViewById(R.id.recyclerViewStatus_2);
        recyclerViewStatus3 = findViewById(R.id.recyclerViewStatus_3);
        recyclerViewStatus4 = findViewById(R.id.recyclerViewStatus_4);
        recyclerViewStatus0 = findViewById(R.id.recyclerViewStatus_0);
        recyclerViewStatus_huy = findViewById(R.id.recyclerViewStatus_huyDon);
        RecyclerView.LayoutManager layoutManager0 = new GridLayoutManager(this, 2);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        RecyclerView.LayoutManager layoutManager2 = new GridLayoutManager(this, 2);
        RecyclerView.LayoutManager layoutManager3 = new GridLayoutManager(this, 2);
        RecyclerView.LayoutManager layoutManager4 = new GridLayoutManager(this, 2);
        RecyclerView.LayoutManager layoutManager5 = new GridLayoutManager(this, 2);
        recyclerViewStatus1.setHasFixedSize(true);
        recyclerViewStatus1.setLayoutManager(layoutManager);

        recyclerViewStatus2.setHasFixedSize(true);
        recyclerViewStatus2.setLayoutManager(layoutManager2);

        recyclerViewStatus3.setHasFixedSize(true);
        recyclerViewStatus3.setLayoutManager(layoutManager3);

        recyclerViewStatus4.setHasFixedSize(true);
        recyclerViewStatus4.setLayoutManager(layoutManager4);

        recyclerViewStatus0.setHasFixedSize(true);
        recyclerViewStatus0.setLayoutManager(layoutManager5);

        recyclerViewStatus_huy.setHasFixedSize(true);
        recyclerViewStatus_huy.setLayoutManager(layoutManager0);

        // khoi tao list
        arrayStatus1 = new ArrayList<>();
        arrayStatus2 = new ArrayList<>();
        arrayStatus3 = new ArrayList<>();
        arrayStatus4 = new ArrayList<>();
        arrayStatus_huy = new ArrayList<>();

    }
    private void setAdapterForRecyclerViewProduct(OrderModel laptopModel, int status) {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(recyclerViewStatus0.getContext(), 2);
        RecyclerView.LayoutManager layoutManager1 = new GridLayoutManager(recyclerViewStatus1.getContext(), 2);
        RecyclerView.LayoutManager layoutManager2 = new GridLayoutManager(recyclerViewStatus2.getContext(), 2);
        RecyclerView.LayoutManager layoutManager3 = new GridLayoutManager(recyclerViewStatus3.getContext(), 2);
        RecyclerView.LayoutManager layoutManager4 = new GridLayoutManager(recyclerViewStatus4.getContext(), 2);
        RecyclerView.LayoutManager layoutManager_huy = new GridLayoutManager(recyclerViewStatus_huy.getContext(), 2);
        switch (status) {
            case 0: {
                arrayStatus0 = laptopModel.getResult();
                orderAdapter = new OrderAdapter(arrayStatus0);
                recyclerViewStatus0.setLayoutManager(layoutManager);
                recyclerViewStatus0.setAdapter(orderAdapter);
                break;
            }
            case 1: {
                arrayStatus1 = laptopModel.getResult();
                orderAdapter = new OrderAdapter(arrayStatus1);
                recyclerViewStatus1.setLayoutManager(layoutManager1);
                recyclerViewStatus1.setAdapter(orderAdapter);
                break;
            }
            case 2: {
                arrayStatus2 = laptopModel.getResult();
                orderAdapter = new OrderAdapter(arrayStatus2);
                recyclerViewStatus2.setLayoutManager(layoutManager2);
                recyclerViewStatus2.setAdapter(orderAdapter);
                break;
            }
            case 3: {
                arrayStatus3 = laptopModel.getResult();
                orderAdapter = new OrderAdapter(arrayStatus3);
                recyclerViewStatus3.setLayoutManager(layoutManager3);
                recyclerViewStatus3.setAdapter(orderAdapter);
                break;
            }
            case 4: {
                arrayStatus4 = laptopModel.getResult();
                orderAdapter = new OrderAdapter(arrayStatus4);
                recyclerViewStatus4.setLayoutManager(layoutManager4);
                recyclerViewStatus4.setAdapter(orderAdapter);
                break;
            }
            case -1: {
                arrayStatus_huy = laptopModel.getResult();
                orderAdapter = new OrderAdapter(arrayStatus0);
                recyclerViewStatus_huy.setLayoutManager(layoutManager_huy);
                recyclerViewStatus_huy.setAdapter(orderAdapter);
                break;
            }
            default: {
                break;
            }
        }
    }
    public void getListLaptops(int status) {
        Call<OrderModel> call = apiShopLapTop.getOrders(status);
        call.enqueue(new Callback<OrderModel>() {
            @Override
            public void onResponse(Call<OrderModel> call, Response<OrderModel> response) {
                OrderModel orderModel= response.body();
                if (orderModel.isSuccess()) {
                    setAdapterForRecyclerViewProduct(orderModel, status);
                }
            }
            @Override
            public void onFailure(Call<OrderModel> call, Throwable t) {
                call.cancel();
            }
        });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}