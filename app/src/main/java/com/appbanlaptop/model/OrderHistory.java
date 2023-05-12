package com.appbanlaptop.model;

import java.util.List;

public class OrderHistory {
    private String name;
    private int sale_price;
    private String image_url;
    private int quantity;
    private int status;
    private int laptop_id;
    private int isReceived;
    private int detail_id;

    public String getName_product() {
        return name_product;
    }

    public void setName_product(String name_product) {
        this.name_product = name_product;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    private String name_product;
    private int order_id;
    private List<OrderDetail> orderDetails;
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

    private int id;
    private int user_id;
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

    private String name_receive;
    private String address_receive;
    private String phone_receive;
    private String message;
    private int total_price;

    public OrderHistory(String name, int sale_price, String image_url, int quantity, int status,int id, int order_id,int user_id, String name_receive, String address_receive, String phone_receive, String message, int total_price, List<OrderDetail> orderDetails,String name_product) {
        this.name = name;
        this.sale_price = sale_price;
        this.image_url = image_url;
        this.quantity = quantity;
        this.status = status;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSale_price() {
        return sale_price;
    }

    public void setSale_price(int sale_price) {
        this.sale_price = sale_price;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getLaptop_id() {
        return laptop_id;
    }

    public void setLaptop_id(int laptop_id) {
        this.laptop_id = laptop_id;
    }

    public int getIsReceived() {
        return isReceived;
    }

    public void setIsReceived(int isReceived) {
        this.isReceived = isReceived;
    }

    public int getDetail_id() {
        return detail_id;
    }

    public void setDetail_id(int detail_id) {
        this.detail_id = detail_id;
    }
}
