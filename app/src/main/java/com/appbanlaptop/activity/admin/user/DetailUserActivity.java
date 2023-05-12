package com.appbanlaptop.activity.admin.user;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.appbanlaptop.R;
import com.appbanlaptop.model.Brand;
import com.appbanlaptop.model.BrandModel;
import com.appbanlaptop.model.User;
import com.appbanlaptop.model.UserModel;
import com.appbanlaptop.retrofit.ApiShopLapTop;
import com.appbanlaptop.retrofit.RetrofitClient;
import com.appbanlaptop.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailUserActivity extends AppCompatActivity {
    EditText etUserName,etEmail,etFullName,etAddress,etPhoneNUmber,etImageUser;
    ApiShopLapTop apiShopLapTop;
    int userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user);

        apiShopLapTop = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiShopLapTop.class);
        userId = getIntent().getIntExtra("id", -1);
        if(userId != -1){
            getUserDetail(String.valueOf(userId));
        }
        initView();
    }

    private void initView() {
        etUserName = findViewById(R.id.etUserName);
        etEmail = findViewById(R.id.etEmail);
        etFullName = findViewById(R.id.etFullName);
        etAddress = findViewById(R.id.etAddress);
        etPhoneNUmber = findViewById(R.id.etPhoneNUmber);
        etImageUser = findViewById(R.id.etImageUser);

    }
    private void getUserDetail(String id) {
        Call<UserModel> call = apiShopLapTop.getUserDetail(id);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                UserModel userModel = response.body();
                if(userModel.isSuccess()){
                    setDataResponseToView(userModel);
                }
            }
            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                call.cancel();
            }
        });
    }

    private void setDataResponseToView(UserModel userModel) {
        try {
            User user = userModel.getResult().get(0);
            etUserName.setText(user.getUsername());
            etEmail.setText(user.getEmail());
            etFullName.setText(user.getFullname());
            etAddress.setText(user.getAddress());
            etPhoneNUmber.setText(user.getPhone_number());
            etImageUser.setText(user.getImage_url());

        }
        catch (Exception e){
            System.out.println(e);
        }

    }
}