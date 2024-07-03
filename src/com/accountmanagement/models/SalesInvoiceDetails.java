
package com.accountmanagement.models;

public class SalesInvoiceDetails {
    private long headerId;
    private String productName;
    private double productQty;
    private double productPrice;
    private double productTotal;

    public SalesInvoiceDetails() {
    }

    public SalesInvoiceDetails(long headerId, String productName, double productQty, double productPrice, double productTotal) {
        this.headerId = headerId;
        this.productName = productName;
        this.productQty = productQty;
        this.productPrice = productPrice;
        this.productTotal = productTotal;
    }

    public long getHeaderId() {
        return headerId;
    }

    public String getProductName() {
        return productName;
    }

    public double getProductQty() {
        return productQty;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public double getProductTotal() {
        return productTotal;
    }
    
    public static SalesInvoiceDetailsBuilder builder() {
        return new SalesInvoiceDetailsBuilder();
    }
    
    
    
}
