package com.appbanlaptop.model;

public class MessageModel {
    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    boolean success;
    String message;

    public boolean isSuccess(){
        return  success;
    }

}
