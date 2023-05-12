package com.appbanlaptop.activity.shipper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import com.appbanlaptop.R;
import com.appbanlaptop.adapter.admin.OrderAdapter;
import com.appbanlaptop.adapter.shipper.OrderAdapterShipper;
import com.appbanlaptop.adapter.shipper.OrderAdapterShipperDaGiao;
import com.appbanlaptop.adapter.shipper.OrderAdapterShipperDaNhan;
import com.appbanlaptop.model.Order;
import com.appbanlaptop.model.OrderHistory;
import com.appbanlaptop.model.OrderModel;
import com.appbanlaptop.retrofit.ApiShopLapTop;
import com.appbanlaptop.retrofit.RetrofitClient;
import com.appbanlaptop.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DonDaNhanCuaShipperActivity extends AppCompatActivity {

    RecyclerView recyclerViewOrderShipper;
    OrderAdapterShipperDaNhan orderAdapter;
    ApiShopLapTop apiShopLapTop;
    List<OrderHistory> orderList;
    public AppCompatActivity getActivity() {
        return this;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_don_da_nhan_cua_shipper);
        apiShopLapTop = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiShopLapTop.class);
        initView();
        getListUsers();
    }

    private void initView(){
        recyclerViewOrderShipper = findViewById(R.id.recycle_order_shipper_da_nhan);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerViewOrderShipper.setHasFixedSize(true);
        recyclerViewOrderShipper.setLayoutManager(layoutManager);

        // khoi tao list
        orderList = new ArrayList<>();
    }

    public void getListUsers() {
        Call<OrderModel> call = apiShopLapTop.getDonDaNhanCuaShipper(Utils.user_id);
        call.enqueue(new Callback<OrderModel>() {
            @Override
            public void onResponse(Call<OrderModel> call, Response<OrderModel> response) {
                OrderModel orderModel= response.body();
                if (orderModel.isSuccess()) {
                    orderList = orderModel.getResult();
                    orderAdapter = new OrderAdapterShipperDaNhan(orderList,DonDaNhanCuaShipperActivity.this);
                    recyclerViewOrderShipper.setAdapter(orderAdapter);
                }
            }
            @Override
            public void onFailure(Call<OrderModel> call, Throwable t) {
                call.cancel();
            }
        });
    }

}