package com.appbanlaptop.model;

import java.util.List;

public class Order {
    private int id;
    private int user_id;
    private String name_receive;
    private String address_receive;
    private String phone_receive;
    private String message;
    private int total_price;
    private String pay_method;
    private int paid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;


    public String getName_product() {
        return name_product;
    }

    public void setName_product(String name_product) {
        this.name_product = name_product;
    }

    private String name_product;

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    private int order_id;


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    private int status;
    private List<OrderDetail> orderDetails;
    public Order() {

    }
    public Order(int id, int order_id,int user_id, String name_receive, String address_receive, String phone_receive, String message, int total_price,int status, List<OrderDetail> orderDetails,String name_product) {
        this.id = id;
        this.user_id = user_id;
        this.name_receive = name_receive;
        this.address_receive = address_receive;
        this.phone_receive = phone_receive;
        this.message = message;
        this.total_price = total_price;
        this.status = status;
        this.orderDetails = orderDetails;
        this.name_product = name_product;
        this.order_id = order_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName_receive() {
        return name_receive;
    }

    public void setName_receive(String name_receive) {
        this.name_receive = name_receive;
    }

    public String getAddress_receive() {
        return address_receive;
    }

    public void setAddress_receive(String address_receive) {
        this.address_receive = address_receive;
    }

    public String getPhone_receive() {
        return phone_receive;
    }

    public void setPhone_receive(String phone_receive) {
        this.phone_receive = phone_receive;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public String getPay_method() {
        return pay_method;
    }

    public void setPay_method(String pay_method) {
        this.pay_method = pay_method;
    }

    public int getPaid() {
        return paid;
    }

    public void setPaid(int paid) {
        this.paid = paid;
    }
}
