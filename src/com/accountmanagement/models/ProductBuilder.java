
package com.accountmanagement.models;

public class ProductBuilder {
    
    private int id;
    private String serial;
    private String buyerName;
    private String buyerPhone;
    private String buyerEmail;
    private String password;
    private String subscribtionDate;
    private double subscribtionValue;
    
    public ProductBuilder id(int id) {
        this.id = id;
        return this;
    }
    
    public ProductBuilder serial(String serial) {
        this.serial = serial;
        return this;
    }
    
    public ProductBuilder buyerName(String buyerName) {
        this.buyerName = buyerName;
        return this;
    }
    
    public ProductBuilder buyerPhone(String buyerPhone) {
        this.buyerPhone = buyerPhone;
        return this;
    }
    
    public ProductBuilder buyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
        return this;
    }
    
    public ProductBuilder password(String password) {
        this.password = password;
        return this;
    }
    
    public ProductBuilder subscribtionDate(String subscribtionDate) {
        this.subscribtionDate = subscribtionDate;
        return this;
    }
    
    public ProductBuilder subscribtionValue(double subscribtionValue) {
        this.subscribtionValue = subscribtionValue;
        return this;
    }
    
    public Product build() {
        return new Product(id, serial, buyerName, buyerPhone, buyerEmail, password, subscribtionDate, subscribtionValue);
    }
}
