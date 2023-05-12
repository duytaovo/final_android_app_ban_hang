package com.appbanlaptop.activity.admin.product;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.appbanlaptop.R;
import com.appbanlaptop.activity.LoginActivity;
import com.appbanlaptop.activity.admin.brand.AddBrandActivity;
import com.appbanlaptop.activity.admin.brand.QuanLyBrandActivity;
import com.appbanlaptop.activity.admin.order.DetailOrderActivity;
import com.appbanlaptop.activity.admin.order.QuanLyOrderActivity;
import com.appbanlaptop.adapter.BrandAdapter;
import com.appbanlaptop.databinding.ActivityAddSpBinding;
import com.appbanlaptop.fragment.LaptopDetailFragment;
import com.appbanlaptop.model.Brand;
import com.appbanlaptop.model.BrandModel;
import com.appbanlaptop.model.Laptop;
import com.appbanlaptop.model.LaptopModel;
import com.appbanlaptop.model.MessageModel;
import com.appbanlaptop.model.UserModel;
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

public class AddSPActivity extends AppCompatActivity {
    public List<Brand> arrayBrand;
    ApiShopLapTop apiShopLapTop;

    ActivityAddSpBinding binding;
    int productId;
    EditText etName,etScreen,etCpu,etCard,etRam,etRom,etPin,etWeight,etOs,etConnector,etPrice,etSale_price,etSpecial,etYearLaunch,etImage,etDescription;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    List<String> stringList ;
    Spinner mySpinnerAddSp ;
    String nameBrand;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sp);
        binding = ActivityAddSpBinding.inflate(getLayoutInflater());
        apiShopLapTop = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiShopLapTop.class);
        setContentView(binding.getRoot());
        productId = getIntent().getIntExtra("id", -1);
        if (productId != -1){
            getLaptopDetail(String.valueOf(productId));
        }
        initView();
    }

    private void addSP(){
        try {
            String _nameBrand = nameBrand;
            String str_name = etName.getText().toString().trim();
            String str_screen = etScreen.getText().toString().trim();
            String str_cpu = etCpu.getText().toString().trim();
            String str_card = etCard.getText().toString().trim();
            String str_ram = etRam.getText().toString().trim();
            String str_rom = etRom.getText().toString().trim();
            float weight = Float.valueOf(etWeight.getText().toString().trim());
            String str_Pin = etPin.getText().toString().trim();
            String str_os = etOs.getText().toString().trim();
            String str_connector = etConnector.getText().toString().trim();
            int price = Integer.parseInt(etPrice.getText().toString().trim());
            int sale_price = Integer.parseInt(etSale_price.getText().toString().trim());
            String str_special = etSpecial.getText().toString().trim();
            int  year_launch= Integer.parseInt(etYearLaunch.getText().toString().trim());
            String str_image_url = etImage.getText().toString().trim();
            String str_description = etDescription.getText().toString().trim();
            
            if( TextUtils.isEmpty(str_name) ||TextUtils.isEmpty(str_screen) ||TextUtils.isEmpty(str_cpu) ||TextUtils.isEmpty(str_card) ||TextUtils.isEmpty(str_ram) ||TextUtils.isEmpty(str_rom) ||TextUtils.isEmpty(str_Pin) || TextUtils.isEmpty(str_os) ||TextUtils.isEmpty(str_connector)   ||TextUtils.isEmpty(str_special)  ||TextUtils.isEmpty(str_image_url) ||TextUtils.isEmpty(str_description) ){
                Toast.makeText(AddSPActivity.this,"Vui lòng nhập đầy đủ",Toast.LENGTH_LONG).show();
            } else {
                Call<LaptopModel> call = apiShopLapTop.insertSP(_nameBrand,str_name,str_screen,str_cpu,str_card,str_ram,str_rom,str_Pin,weight,str_os,str_connector,price,sale_price,str_special,year_launch,str_image_url,str_description);
                call.enqueue(new Callback<LaptopModel>() {
                    @Override
                    public void onResponse(Call<LaptopModel> call, Response<LaptopModel> response) {
                        LaptopModel laptopModel = response.body();
                        if (laptopModel.isSuccess()) {
                            Toast.makeText(AddSPActivity.this, "Thêm sản phẩm thành công.", Toast.LENGTH_LONG).show();
                            finish();
                            Intent intent = new Intent(AddSPActivity.this, AdminActivity.class);
                            AddSPActivity.this.startActivity(intent);
                        }
                    }
                    @Override
                    public void onFailure(Call<LaptopModel> call, Throwable t) {
                        call.cancel();
                        System.out.println(t);
                        Toast.makeText(AddSPActivity.this, "Thêm sản phẩm thất bại.", Toast.LENGTH_LONG).show();

                    }
                });
            }
        }
        catch (Exception e){
        System.out.println(e);
        }
        }
    private void getLaptopDetail(String id) {
        Call<LaptopModel> call = apiShopLapTop.getLaptopDetail(id);
        call.enqueue(new Callback<LaptopModel>() {
            @Override
            public void onResponse(Call<LaptopModel> call, Response<LaptopModel> response) {
                LaptopModel laptopModel = response.body();
                if (laptopModel.isSuccess()) {
                    setDataResponseToView(laptopModel);
                }
            }
            @Override
            public void onFailure(Call<LaptopModel> call, Throwable t) {
                call.cancel();
            }
        });
    }

    private void setDataResponseToView(LaptopModel laptopModel) {
        try {
            Laptop laptop = laptopModel.getResult().get(0);
            String _nameBrand = laptop.getBrand().getName();

            int position = adapter.getPosition(_nameBrand);
            mySpinnerAddSp.setSelection(position);
            etName.setText(laptop.getName());
            etScreen.setText(laptop.getScreen());
            etCpu.setText(laptop.getCpu());
            etCard.setText(laptop.getCard());
            etRam.setText(laptop.getRam());
            etRom.setText(laptop.getRom());
            etPin.setText(laptop.getPin());
            etWeight.setText(String.valueOf(laptop.getWeight()));
            etOs.setText(laptop.getOs());
            etConnector.setText(laptop.getConnector());
            etPrice.setText(String.valueOf(laptop.getPrice()));
            etSale_price.setText(String.valueOf(laptop.getSale_price()));
            etSpecial.setText(laptop.getSpecial());
            etYearLaunch.setText(String.valueOf(laptop.getYear_launch()));
            etImage.setText(laptop.getImage_url());
            etDescription.setText(laptop.getDescription());

            binding.btAddSP.setText("Cập nhật sản phẩm");
        }
        catch (Exception e){
            System.out.println(e);
        }

    }
    private void updateSP(){
        try {
            String _nameBrand = nameBrand;
            String str_name = etName.getText().toString().trim();
            String str_screen = etScreen.getText().toString().trim();
            String str_cpu = etCpu.getText().toString().trim();
            String str_card = etCard.getText().toString().trim();
            String str_ram = etRam.getText().toString().trim();
            String str_rom = etRom.getText().toString().trim();
            float weight = Float.valueOf(etWeight.getText().toString().trim());
            String str_Pin = etPin.getText().toString().trim();
            String str_os = etOs.getText().toString().trim();
            String str_connector = etConnector.getText().toString().trim();
            int price = Integer.parseInt(etPrice.getText().toString().trim());
            int sale_price = Integer.parseInt(etSale_price.getText().toString().trim());
            String str_special = etSpecial.getText().toString().trim();
            int  year_launch= Integer.parseInt(etYearLaunch.getText().toString().trim());
            String str_image_url = etImage.getText().toString().trim();
            String str_description = etDescription.getText().toString().trim();

            if( TextUtils.isEmpty(str_name) ||TextUtils.isEmpty(str_screen) ||TextUtils.isEmpty(str_cpu) ||TextUtils.isEmpty(str_card) ||TextUtils.isEmpty(str_ram) ||TextUtils.isEmpty(str_rom) ||TextUtils.isEmpty(str_Pin) || TextUtils.isEmpty(str_os) ||TextUtils.isEmpty(str_connector)   ||TextUtils.isEmpty(str_special)  ||TextUtils.isEmpty(str_image_url) ||TextUtils.isEmpty(str_description) ){
                Toast.makeText(AddSPActivity.this,"Vui lòng nhập đầy đủ",Toast.LENGTH_LONG).show();
            } else {
                Call<LaptopModel> call = apiShopLapTop.updateSP(productId,_nameBrand,str_name,str_screen,str_cpu,str_card,str_ram,str_rom,str_Pin,weight,str_os,str_connector,price,sale_price,str_special,year_launch,str_image_url,str_description);
                call.enqueue(new Callback<LaptopModel>() {
                    @Override
                    public void onResponse(Call<LaptopModel> call, Response<LaptopModel> response) {
                        LaptopModel laptopModel = response.body();
                        if (laptopModel.isSuccess()) {
                            Toast.makeText(AddSPActivity.this, "Cập nhật sản phẩm thành công.", Toast.LENGTH_LONG).show();
                            finish();
                            Intent intent = new Intent(AddSPActivity.this, AdminActivity.class);
                            AddSPActivity.this.startActivity(intent);
                        }
                    }
                    @Override
                    public void onFailure(Call<LaptopModel> call, Throwable t) {
                        call.cancel();
                        System.out.println("cap nhat that bai" + t);
                        Toast.makeText(AddSPActivity.this, "Cập nhất sản phẩm thành công.", Toast.LENGTH_LONG).show();

                    }
                });
            }
        }
        catch (Exception e){
            System.out.println("loi"+ e);
        }
    }
    private void getListBrands() {
        compositeDisposable.add(apiShopLapTop.getBrands()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        brandModel -> {
                            if (brandModel.isSuccess()) {
                                arrayBrand = brandModel.getResult();
                                for (int i=0;i<arrayBrand.size();i++){
                                    stringList.add(arrayBrand.get(i).getName());
                                }
                            }
                        }
                ));
    }

    private void initView() {
        mySpinnerAddSp  = findViewById(R.id.mySpinnerAddSp);
        etName = findViewById(R.id.etName);
        etScreen = findViewById(R.id.etScreen);
        etCpu= findViewById(R.id.etCPU);
        etCard = findViewById(R.id.etCard);
        etRam = findViewById(R.id.etRam);
        etRom = findViewById(R.id.etRom);
        etPin = findViewById(R.id.etPin);
        etWeight = findViewById(R.id.etWeight);
        etOs = findViewById(R.id.etOs);
        etConnector = findViewById(R.id.etConnector);
        etPrice = findViewById(R.id.etPrice);
        etSale_price = findViewById(R.id.etSalePrice);
        etSpecial = findViewById(R.id.etSpecial);
        etYearLaunch = findViewById(R.id.etYear_launch);
        etImage = findViewById(R.id.etImage_url);
        etDescription = findViewById(R.id.etDescription);
        arrayBrand = new ArrayList<>();
        stringList = new ArrayList<>();

        getListBrands();
        stringList.add("Macbook ");
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,stringList);

        mySpinnerAddSp.setAdapter(adapter);
        mySpinnerAddSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                nameBrand = stringList.get(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        if(productId != -1){
            binding.btAddSP.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    updateSP();
                    finish();
                }
            });
        }
        else {
            binding.btAddSP.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addSP();
                    finish();
                }
            });
        }

    }

}