package com.appbanlaptop.activity.admin.thongke;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.appbanlaptop.R;
import com.appbanlaptop.model.ThongKeModel;
import com.appbanlaptop.retrofit.ApiShopLapTop;
import com.appbanlaptop.retrofit.RetrofitClient;
import com.appbanlaptop.utils.Utils;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThongKeSanPhamBanDuocActivity extends AppCompatActivity implements OnChartValueSelectedListener {

    PieChart pieChart;
    ApiShopLapTop apiShopLapTop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongke_sanpham_banduoc);
        apiShopLapTop = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiShopLapTop.class);
        initView();
        getDataChart();
    }

    private void initView(){
        pieChart = findViewById(R.id.pieChart);
        pieChart.setOnChartValueSelectedListener(this);
    }

    private void getDataChart() {
        List<PieEntry> listData = new ArrayList<>();

        Call<ThongKeModel> call = apiShopLapTop.getThongKe();
        call.enqueue(new Callback<ThongKeModel>() {
            @Override
            public void onResponse(Call<ThongKeModel> call, Response<ThongKeModel> response) {
                ThongKeModel thongKeModel = response.body();
                if(thongKeModel.isSuccess()){
                    for (int i = 0;i<thongKeModel.getResult().size();i++){
                        String tenSp = thongKeModel.getResult().get(i).getNameLaptop();
                        int tong = thongKeModel.getResult().get(i).getTong();
                        listData.add(new PieEntry(tong,tenSp));

                    }
                    PieDataSet pieDataSet = new PieDataSet(listData,"Thống kê");
                    PieData data = new PieData();
                    data.setDataSet(pieDataSet);
                    data.setValueTextSize(12f);

                    data.setValueFormatter(new PercentFormatter(pieChart));
                    pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
                    pieChart.setData(data);
                    pieChart.animateXY(2000,2000);
                    pieChart.setUsePercentValues(true);
                    pieChart.getDescription().setEnabled(false);
                    pieChart.invalidate();

                }
            }
            @Override
            public void onFailure(Call<ThongKeModel> call, Throwable t) {
                System.out.println(t);
            }
        });
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        Toast.makeText(this, "Value: "
                + e.getY()
               , Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, "Value: "
//                        +
//                , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected() {

    }


}