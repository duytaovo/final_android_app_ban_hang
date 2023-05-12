package com.appbanlaptop.activity.admin.thongke;

import android.database.Observable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.appbanlaptop.R;
import com.appbanlaptop.model.ThongKe;
import com.appbanlaptop.model.ThongKeModel;
import com.appbanlaptop.model.User;
import com.appbanlaptop.retrofit.ApiShopLapTop;
import com.appbanlaptop.retrofit.RetrofitClient;
import com.appbanlaptop.utils.Utils;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThongKeDoanhThuActivity2 extends AppCompatActivity {

    BarChart barChart;
    ApiShopLapTop apiShopLapTop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke_doanh_thu);
        apiShopLapTop = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiShopLapTop.class);
        initView();
        getDataChart();
    }

    private void initView(){
        barChart = findViewById(R.id.barChart);
        barChart.setTouchEnabled(true);
        barChart.setDragEnabled(true);
        barChart.setScaleEnabled(true);
    }

    private void getDataChart() {
        ArrayList<BarEntry> entries = new ArrayList<>();

//        Observable<ThongKeModel> call = apiShopLapTop.getThongKeDoanhThu();
//        call.enqueue(new Callback<ThongKeModel>() {
//            @Override
//            public void onResponse(Call<ThongKeModel> call, Response<ThongKeModel> response) {
//                ThongKeModel thongKeModel = response.body();
//                float[] revenues = { 1000f, 2000f, 3000f, 4000f, 5000f, 6000f, 7000f, 8000f, 9000f, 10000f, 11000f, 12000f };
//                System.out.println(thongKeModel.getResult().get(0).getDoanhThu());
//                if(thongKeModel.isSuccess()){
//                    for (int i = 0; i < thongKeModel.getResult().size(); i++) {
//                        ThongKe thongKe = thongKeModel.getResult().get(i);
////                        float doanhThu = thongKeModel.getResult().get(i).getDoanhThu();
//                        entries.add(new BarEntry(i, thongKe.getDoanhThu()));
////                        entries.add(new BarEntry(i, revenues[i]));
//                    }
//                    for (int i = 0; i < 10; i++) {
//                        entries.add(new BarEntry(i, revenues[i]));
//                    }
//                    Description description = new Description();
//                    description.setText("Biểu đồ cột");
//                    description.setTextSize(14);
//                    barChart.setDescription(description);
//                    System.out.println(entries);
//                    BarDataSet barDataSet = new BarDataSet(entries, "Doanh thu");
//
//// Cấu hình màu sắc và các thuộc tính khác của LineDataSet
//                    barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
//
//// Tạo một BarData chứa BarDataSet vừa tạo
//                    BarData barData = new BarData(barDataSet);
//
//// Thiết lập dữ liệu cho biểu đồ
//                    barChart.setData(barData);
//
//// Cập nhật biểu đồ
//                    barChart.invalidate();
//                }
//            }
//            @Override
//            public void onFailure(Call<ThongKeModel> call, Throwable t) {
//                System.out.println(t);
//            }
//        });
    }
}