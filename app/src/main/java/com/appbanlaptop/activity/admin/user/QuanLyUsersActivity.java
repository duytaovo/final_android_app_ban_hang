package com.appbanlaptop.activity.admin.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.appbanlaptop.R;
import com.appbanlaptop.activity.admin.brand.QuanLyBrandActivity;
import com.appbanlaptop.activity.admin.product.AddSPActivity;
import com.appbanlaptop.adapter.admin.LaptopAdapter;
import com.appbanlaptop.adapter.admin.UserAdapter;
import com.appbanlaptop.model.Brand;
import com.appbanlaptop.model.LaptopModel;
import com.appbanlaptop.model.User;
import com.appbanlaptop.model.UserModel;
import com.appbanlaptop.retrofit.ApiShopLapTop;
import com.appbanlaptop.retrofit.RetrofitClient;
import com.appbanlaptop.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuanLyUsersActivity extends AppCompatActivity {
    RecyclerView recyclerViewUser;
    UserAdapter userAdapter;

    ApiShopLapTop apiShopLapTop;
    List<User> arrayUser,arrayUserNew;
    public AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_user);
        apiShopLapTop = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiShopLapTop.class);
        initView();
        getListUsers();
    }

    private void initView(){
        recyclerViewUser = findViewById(R.id.recycle_user_admin);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerViewUser.setHasFixedSize(true);
        recyclerViewUser.setLayoutManager(layoutManager);
        // khoi tao list
        arrayUser = new ArrayList<>();
        arrayUserNew = new ArrayList<>();
    }

    public void getListUsers() {
        Call<UserModel> call = apiShopLapTop.getUsers();
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                UserModel userModel = response.body();
                if (userModel.isSuccess()) {
                    arrayUser = userModel.getResult();
                    for (int i = 0 ; i< arrayUser.size();i++){
                        if(arrayUser.get(i).getStatus() == 0){
                            arrayUserNew.add(arrayUser.get(i));
                        }
                    }
                    userAdapter = new UserAdapter(arrayUserNew,QuanLyUsersActivity.this);
                    recyclerViewUser.setAdapter(userAdapter);
                }
            }
            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                call.cancel();
                Toast.makeText(QuanLyUsersActivity.this, "Có lỗi", Toast.LENGTH_LONG).show();

            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}