package com.accountmanagement.models;

public class Product {
    private int id;
    private String serial;
    private String buyerName;
    private String buyerPhone;
    private String buyerEmail;
    private String password;
    private String subscribtionDate;
    private double subscribtionValue;

    public Product() {
    }

    public Product(String serial, String buyerName, String buyerPhone, String buyerEmail, String password, String subscribtionDate, double subscribtionValue) {
        this.serial = serial;
        this.buyerName = buyerName;
        this.buyerPhone = buyerPhone;
        this.buyerEmail = buyerEmail;
        this.password = password;
        this.subscribtionDate = subscribtionDate;
        this.subscribtionValue = subscribtionValue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerPhone() {
        return buyerPhone;
    }

    public void setBuyerPhone(String buyerPhone) {
        this.buyerPhone = buyerPhone;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSubscribtionDate() {
        return subscribtionDate;
    }

    public void setSubscribtionDate(String subscribtionDate) {
        this.subscribtionDate = subscribtionDate;
    }

    public double getSubscribtionValue() {
        return subscribtionValue;
    }

    public void setSubscribtionValue(double subscribtionValue) {
        this.subscribtionValue = subscribtionValue;
    }
    
    public static ProductBuilder builder() {
        return new ProductBuilder();
    }
    
    
}
