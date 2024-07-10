
package com.accountmanagement.models;


public class SProduct {
    
    private String name;
    private double qty;
    private double price;
    private double total;

    public SProduct() {
    }

    public SProduct(String name, double qty, double price, double total) {
        this.name = name;
        this.qty = qty;
        this.price = price;
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    
    
}
