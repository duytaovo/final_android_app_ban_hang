package com.appbanlaptop.model;

import java.util.List;

public class OrderModel {
    private boolean success;
    private String message;

    private List<OrderHistory> result;

    public OrderModel(boolean success, String message, List<OrderHistory> result) {
        this.success = success;
        this.message = message;
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<OrderHistory> getResult() {
        return result;
    }

    public void setResult(List<OrderHistory> result) {
        this.result = result;
    }
}
