
package com.accountmanagement.models;

public class SalesInvoiceHeaderBuilder {
    
    private long id;
    private String date;
    private int customerId;
    private double total;
    private boolean isFileType;
    private String filePath;
    private double tax;
    private double discount;
    private String comment;
    
    public SalesInvoiceHeaderBuilder id(long id) {
        this.id = id;
        return this;
    }
    
    public SalesInvoiceHeaderBuilder date(String date) {
        this.date = date;
        return this;
    }
    
    public SalesInvoiceHeaderBuilder customerId(int customerId) {
        this.customerId = customerId;
        return this;
    }
    
    public SalesInvoiceHeaderBuilder total(double total) {
        this.total = total;
        return this;
    }
    
    public SalesInvoiceHeaderBuilder isFileType(boolean isFileType) {
        this.isFileType = isFileType;
        return this;
    }
    
    public SalesInvoiceHeaderBuilder filePath(String filePath) {
        this.filePath = filePath;
        return this;
    }
    
    public SalesInvoiceHeaderBuilder tax(double tax) {
        this.tax = tax;
        return this;
    }
    
    public SalesInvoiceHeaderBuilder discount(double discount) {
        this.discount = discount;
        return this;
    }
    
    public SalesInvoiceHeaderBuilder comment(String comment) {
        this.comment = comment;
        return this;
    }
    
    public SalesInvoiceHeader build() {
        return new SalesInvoiceHeader(id, date, customerId, total, isFileType, filePath, tax, discount, comment);
    }
    
    
}
