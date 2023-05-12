package com.appbanlaptop.activity.admin.thongke;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.appbanlaptop.R;

import com.appbanlaptop.retrofit.ApiShopLapTop;
import com.appbanlaptop.retrofit.RetrofitClient;
import com.appbanlaptop.utils.Utils;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;


import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class ThongKeDoanhThuActivity extends AppCompatActivity implements OnChartValueSelectedListener {

    LineChart lineChart;
    ApiShopLapTop apiShopLapTop;
    private CombinedChart mChart;
    ArrayList<Integer> data = new ArrayList<>();


    CompositeDisposable compositeDisposable = new CompositeDisposable();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thongke_combinedchart);
//        data2 = new ArrayList<>();
        apiShopLapTop = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiShopLapTop.class);
        getDataChart();
        initView();

    }

    private void initView(){
        mChart = (CombinedChart) findViewById(R.id.combinedChart);
        mChart.getDescription().setEnabled(false);
        mChart.setBackgroundColor(Color.WHITE);
        mChart.setDrawGridBackground(false);
        mChart.setDrawBarShadow(false);
        mChart.setHighlightFullBarEnabled(false);
        mChart.setOnChartValueSelectedListener(this);


   }

    private void getDataChart() {
        compositeDisposable.add(apiShopLapTop.getThongKeDoanhThu()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        thongKeModel -> {
                            if (thongKeModel.isSuccess()) {
                                List<String> xLabel = new ArrayList<>();
//                                xLabel.add("Jan");
//                                xLabel.add("Feb");
//                                xLabel.add("Mar");
//                                xLabel.add("Apr");
//                                xLabel.add("May");
//                                xLabel.add("Jun");
//                                xLabel.add("Jul");
//                                xLabel.add("Aug");
//                                xLabel.add("Sep");
//                                xLabel.add("Oct");
//                                xLabel.add("Nov");
//                                xLabel.add("Dec");
                                for (int i = 0; i < thongKeModel.getResult().size(); i++) {
                                    int doanhThu = thongKeModel.getResult().get(i).getDoanhThu();
                                    data.add(doanhThu);
                                    xLabel.add(thongKeModel.getResult().get(i).getMonth());
                                }
                             //set up base
                                YAxis rightAxis = mChart.getAxisRight();
                                rightAxis.setDrawGridLines(false);
                                rightAxis.setAxisMinimum(0f);

                                YAxis leftAxis = mChart.getAxisLeft();
                                leftAxis.setDrawGridLines(false);
                                leftAxis.setAxisMinimum(0f);



                                XAxis xAxis = mChart.getXAxis();
                                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                                xAxis.setAxisMinimum(0f);
                                xAxis.setGranularity(1f);
                                xAxis.setValueFormatter(new ValueFormatter() {
                                    @Override
                                    public String getFormattedValue(float value) {
                                        return xLabel.get((int) value);
                                    }
                                });

                                //add data
                                LineData d = new LineData();

                                ArrayList<Entry> entries = new ArrayList<Entry>();
                                for (int index = 0; index < data.size(); index++) {
                                    entries.add(new Entry(index, data.get(index)));
                                }

                                LineDataSet set = new LineDataSet(entries, "Doanh thu theo từng tháng");
                                set.setColor(Color.GREEN);
                                set.setLineWidth(1f);
                                set.setCircleColor(Color.GREEN);
                                set.setCircleRadius(5f);
                                set.setFillColor(Color.GREEN);
                                set.setMode(LineDataSet.Mode.CUBIC_BEZIER);
                                set.setDrawValues(true);
                                set.setValueTextSize(10f);
                                set.setValueTextColor(Color.GREEN);

                                set.setAxisDependency(YAxis.AxisDependency.LEFT);
                                d.addDataSet(set);

                                CombinedData data = new CombinedData();
                                LineData lineDatas = new LineData();
                                lineDatas.addDataSet((ILineDataSet) set);

                                data.setData(lineDatas);

                                xAxis.setAxisMaximum(data.getXMax());

                                mChart.setData(data);
                                mChart.invalidate();

                            }
                        }


                )

        );
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        Toast.makeText(this, "Value: "
                + e.getY()
               , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected() {

    }

//    private DataSet<Entry> dataChart() {
////        System.out.println(data);
//        LineData d = new LineData();
//        int[] data = new int[] {1,2};
//
//        ArrayList<Entry> entries = new ArrayList<Entry>();
//
//        for (int index = 0; index < 2; index++) {
//            entries.add(new Entry(index, data[index]));
//        }
//
//        LineDataSet set = new LineDataSet(entries, "Revenue");
//        set.setColor(Color.GREEN);
//        set.setLineWidth(1f);
//        set.setCircleColor(Color.GREEN);
//        set.setCircleRadius(5f);
//        set.setFillColor(Color.GREEN);
//        set.setMode(LineDataSet.Mode.CUBIC_BEZIER);
//        set.setDrawValues(true);
//        set.setValueTextSize(10f);
//        set.setValueTextColor(Color.GREEN);
//
//        set.setAxisDependency(YAxis.AxisDependency.LEFT);
//        d.addDataSet(set);
//
//        return set;
//    }
}