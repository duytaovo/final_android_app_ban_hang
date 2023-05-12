package com.appbanlaptop.model;

import com.github.mikephil.charting.data.DataSet;

import java.util.Date;

public class ThongKe {
    private String name;
    private int laptop_id;
    private int tong;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    private String month;

    public int getDoanhThu() {
        return doanhThu;
    }

    public ThongKe(int laptop_id, int tong, int doanhThu,String month) {
        this.laptop_id = laptop_id;
        this.tong = tong;
        this.doanhThu = doanhThu;
        this.month = month;
    }
    public ThongKe(int doanhThu) {
        this.doanhThu = doanhThu;
    }
    public void setDoanhThu(int doanhThu) {
        this.doanhThu = doanhThu;
    }

    private int doanhThu;

    public String getNameLaptop() {
        return name;
    }

    public void setNameLaptop(String nameLaptop) {
        this.name = nameLaptop;
    }

    public int getLaptop_id() {
        return laptop_id;
    }

    public void setLaptop_id(int laptop_id) {
        this.laptop_id = laptop_id;
    }

    public int getTong() {
        return tong;
    }

    public void setTong(int tong) {
        this.tong = tong;
    }


}
