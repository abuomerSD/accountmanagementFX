
package com.accountmanagement.models;

public class OutgoingDocumentBuilder {
    
    private long id;
    private String date;
    private int currencyId;
    private int customerId;
    private double value;
    private String comment;
    
    public OutgoingDocumentBuilder id(long id) {
        this.id = id;
        return this;
    }
    
    public OutgoingDocumentBuilder date(String date) {
        this.date = date;
        return this;
    }
    
    public OutgoingDocumentBuilder currencyId(int currencyId) {
        this.currencyId = currencyId;
        return this;
    }
    
    
    public OutgoingDocumentBuilder customerId(int customerId) {
        this.customerId = customerId;
        return this;
    }
    
    public OutgoingDocumentBuilder value(double value) {
        this.value = value;
        return this;
    }
    
    public OutgoingDocumentBuilder comment(String comment) {
        this.comment = comment;
        return this;
    }
    
    
    public OutgoingDocument build() {
        return new OutgoingDocument(id, date, currencyId, customerId, value, comment);
    }
    
    
}
