
package com.accountmanagement.models;

public class AccountMovementBuilder {
    private String date;
    private int customerId;
    private int currencyId;
    private long incomingDocumentId;
    private long outgoingDocumentId;
    private double incomingValue;
    private double outgoingValue;
    private String comment;
    private double balance;

    
    public AccountMovementBuilder date(String date) {
        this.date = date;
        return this;
    }
    
    public AccountMovementBuilder customerId(int customerId) {
        this.customerId = customerId;
        return this;
    }
    
    public AccountMovementBuilder currencyId(int currencyId) {
        this.currencyId = currencyId;
        return this;
    }
    
    public AccountMovementBuilder incomingDocumentId(long incomingDocumentId) {
        this.incomingDocumentId = incomingDocumentId;
        return this;
    }
    
    public AccountMovementBuilder outgoingDocumentId(long outgoingDocumentId) {
        this.outgoingDocumentId = outgoingDocumentId;
        return this;
    }
    
    public AccountMovementBuilder incomingValue(double incomingValue) {
        this.incomingValue = incomingValue;
        return this;
    }
    
    public AccountMovementBuilder outgoingValue(double outgoingValue) {
        this.outgoingValue = outgoingValue;
        return this;
    }
    
    public AccountMovementBuilder comment(String comment) {
        this.comment = comment;
        return this;
    }
    
    public AccountMovementBuilder balance(double balance) {
        this.balance = balance;
        return this;
    }
    
    public AccountMovement build() {
        return new AccountMovement(date, customerId, currencyId, incomingDocumentId, outgoingDocumentId, incomingValue, outgoingValue, comment, balance);
    }
    
}
