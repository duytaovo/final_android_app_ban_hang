package com.appbanlaptop.activity.shipper;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.appbanlaptop.R;
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

public class DetailOrderShipperActivity extends AppCompatActivity {

    EditText etOrderNameReceive,etAddress,etPhone,etMessage,etTotalPrice,etNameProduct;
    ApiShopLapTop apiShopLapTop;
    int orderId;
    Spinner mySpinner ;
    List<String> listStatus ;
    List<String> listStatusFirst ;
    Button updateStatus;
    int status;
    ArrayAdapter<String> adapter;
    public AppCompatActivity getActivity() {
        return this;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_order_shipper);

        apiShopLapTop = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiShopLapTop.class);
        orderId = getIntent().getIntExtra("id", -1);
        if(orderId != -1){
            getOrderDetail(String.valueOf(orderId));
        }
        initView();
        initData();
    }

    private void initView() {
        etOrderNameReceive = findViewById(R.id.etOrderNameReceiveShipper);
        etAddress = findViewById(R.id.etAddressShipper);
        etPhone = findViewById(R.id.etPhoneShipper);
        etMessage = findViewById(R.id.etMessageShipper);
        etTotalPrice = findViewById(R.id.etTotalPriceShipper);
        etNameProduct = findViewById(R.id.etOrderNameProductShipper);
        mySpinner = findViewById(R.id.mySpinnerShipper);
        updateStatus = findViewById(R.id.updateStatusShipper);
        listStatus = new ArrayList<>();
        listStatusFirst = new ArrayList<>();
        listStatus.add("Đơn hàng đang được xử lý");
        listStatus.add("Đơn hàng đã chấp nhận");
        listStatus.add("Đơn hàng giao cho đơn vị vận chuyển");
        listStatus.add("Đơn hàng đã giao thành công");
        listStatus.add("Đơn hàng đã huỷ");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,listStatus);
        mySpinner.setAdapter(adapter);
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                status = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        updateStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateOrder();
            }
        });

    }

    private void updateOrder() {
        Call<OrderModel> call = apiShopLapTop.updateOrderStatus(orderId,status);
        call.enqueue(new Callback<OrderModel>() {
            @Override
            public void onResponse(Call<OrderModel> call, Response<OrderModel> response) {
                OrderModel orderModel = response.body();
                if(orderModel.isSuccess()){
                    System.out.println("Cập nhật thành công");
                    Toast.makeText(DetailOrderShipperActivity.this,"Cập nhật thành công",Toast.LENGTH_LONG).show();
                    getOrderDetail(String.valueOf(orderId));
                    //setDataResponseToView(orderModel);
                }
            }
            @Override
            public void onFailure(Call<OrderModel> call, Throwable t) {
                call.cancel(); System.out.println("cập nhật thất bại");
                Toast.makeText(DetailOrderShipperActivity.this,"Cập nhật thất bại",Toast.LENGTH_LONG).show();

                System.out.println(t);
            }
        });
    }
    private void initData() {

    }
    private void getOrderDetail(String id) {
        Call<OrderModel> call = apiShopLapTop.getOrderDetailShipper(id);
        call.enqueue(new Callback<OrderModel>() {
            @Override
            public void onResponse(Call<OrderModel> call, Response<OrderModel> response) {
                OrderModel orderModel = response.body();
                if(orderModel.isSuccess()){
                    System.out.println("lay chi tiet thành công");
                    setDataResponseToView(orderModel);
                }
            }
            @Override
            public void onFailure(Call<OrderModel> call, Throwable t) {
                call.cancel(); System.out.println("lay chi tiet user thất bại");
            }
        });
    }

    private void setDataResponseToView(OrderModel orderModel) {
//            listStatusFirst.clear();
        listStatusFirst.clear();

        OrderHistory order = orderModel.getResult().get(0);
        etOrderNameReceive.setText(order.getName_receive());
        etAddress.setText(order.getAddress_receive());
        etNameProduct.setText(order.getName());
        etPhone.setText(order.getPhone_receive());
        etMessage.setText(order.getMessage());
        etTotalPrice.setText(String.valueOf(order.getTotal_price()));
//            etStatus.setText(order.get());
        int trangthai = order.getStatus();

        String trangThaiDau = trangThaiDon(trangthai);
        listStatusFirst.add(trangThaiDau);
        int position = adapter.getPosition(trangThaiDau);
        mySpinner.setSelection(position);

    }

    private String trangThaiDon(int status){
        String result = "";
        switch (status){
            case 0:
                result = "Đơn hàng đang được xử lý";
                break;
            case 1:
                result = "Đơn hàng đã chấp nhận";
                break;
            case 2:
                result = "Đơn hàng giao cho đơn vị vận chuyển";
                break;
            case 3:
                result = "Đơn hàng đã giao thành công";
                break;
            case 4:
                result = "Đơn hàng đã huỷ";
                break;

        }
        return result;
    }
}