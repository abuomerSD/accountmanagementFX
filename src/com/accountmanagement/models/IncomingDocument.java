
package com.accountmanagement.models;

public class IncomingDocument {
    private long id;
    private String date;
    private int currencyId;
    private int customerId;
    private double value;
    private String comment;
    private String customerName;
    private String currencyName;

    public IncomingDocument() {
    }

    public IncomingDocument(long id, String date, int currencyId, int customerId, double value, String comment, String customerName, String currencyName) {
        this.id = id;
        this.date = date;
        this.currencyId = currencyId;
        this.customerId = customerId;
        this.value = value;
        this.comment = comment;
        this.customerName = customerName;
        this.currencyName = currencyName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    

    public long getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public int getCurrencyId() {
        return currencyId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public double getValue() {
        return value;
    }

    public String getComment() {
        return comment;
    }

    
    
    
    public static IncomingDocumentBuilder builder() {
        return new IncomingDocumentBuilder();
    }
    
}
