
package com.accountmanagement.models;

public class SalesInvoiceHeader {
    private long id;
    private String date;
    private int customerId;
    private double total;
    private boolean isFileType;
    private String filePath;
    private double tax;
    private double discount;
    private String comment;
    private String invoiceType;
    private String customerName;
    

    public SalesInvoiceHeader() {
    }

    public SalesInvoiceHeader(long id, String date, int customerId, double total, boolean isFileType, String filePath, double tax, double discount, String comment) {
        this.id = id;
        this.date = date;
        this.customerId = customerId;
        this.total = total;
        this.isFileType = isFileType;
        this.filePath = filePath;
        this.tax = tax;
        this.discount = discount;
        this.comment = comment;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    
    
    public long getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public int getCustomerId() {
        return customerId;
    }

    public double getTotal() {
        return total;
    }

    public boolean isIsFileType() {
        return isFileType;
    }

    public String getFilePath() {
        return filePath;
    }

    public double getTax() {
        return tax;
    }

    public double getDiscount() {
        return discount;
    }

    public String getComment() {
        return comment;
    }
    
    
    public static SalesInvoiceHeaderBuilder builder() {
        return new SalesInvoiceHeaderBuilder();
    }
    
    
}
