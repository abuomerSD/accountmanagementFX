
package com.accountmanagement.models;

public class SalesInvoiceDetailsBuilder {
    
    private long headerId;
    private String productName;
    private double productQty;
    private double productPrice;
    private double productTotal;
    
    public SalesInvoiceDetailsBuilder headerId(long headerId) {
        this.headerId = headerId;
        return this;
    }
    
    public SalesInvoiceDetailsBuilder productName(String productName) {
        this.productName = productName;
        return this;
    }
    
    public SalesInvoiceDetailsBuilder productQty(double productQty) {
        this.productQty = productQty;
        return this;
    }
    
    public SalesInvoiceDetailsBuilder productPrice(double productPrice) {
        this.productPrice = productPrice;
        return this;
    }
    
    public SalesInvoiceDetailsBuilder productTotal(double productTotal) {
        this.productTotal = productTotal;
        return this;
    }
    
    public SalesInvoiceDetails build() {
        return new SalesInvoiceDetails(headerId, productName, productQty, productPrice, productTotal);
    }
    
}
