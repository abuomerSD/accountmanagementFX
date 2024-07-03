
package com.accountmanagement.models;

import java.time.LocalDateTime;


public class IncomingDocumentBuilder {
    private long id;
    private String date;
    private int currencyId;
    private int customerId;
    private double value;
    private String comment;
    
    public IncomingDocumentBuilder id(long id) {
        this.id = id;
        return this;
    }
    
    public IncomingDocumentBuilder date(String date) {
        this.date = date;
        return this;
    }
    
    public IncomingDocumentBuilder currencyId(int currencyId) {
        this.currencyId = currencyId;
        return this;
    }
    
    public IncomingDocumentBuilder customerId(int customerId) {
        this.customerId = customerId;
        return this;
    }
    
    public IncomingDocumentBuilder value(double value) {
        this.value = value;
        return this;
    }
    
    public IncomingDocumentBuilder comment(String comment) {
        this.comment = comment;
        return this;
    }
    
    public IncomingDocument build() {
        return new IncomingDocument(id, date, currencyId, customerId, value, comment);
    }
    
}
