
package com.accountmanagement.models;

public class OutgoingDocument {
    
    private long id;
    private String date;
    private int currencyId;
    private int customerId;
    private double value;
    private String comment;

    public OutgoingDocument() {
    }

    public OutgoingDocument(long id, String date, int currencyId, int customerId, double value, String comment) {
        this.id = id;
        this.date = date;
        this.currencyId = currencyId;
        this.customerId = customerId;
        this.value = value;
        this.comment = comment;
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
    
    public static OutgoingDocumentBuilder builder() {
        return new OutgoingDocumentBuilder();
    }
    
}
