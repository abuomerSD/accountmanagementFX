
package com.accountmanagement.models;

public class AccountMovement {
    private String date;
    private int customerId;
    private int currencyId;
    private long incomingDocumentId;
    private long outgoingDocumentId;
    private double incomingValue;
    private double outgoingValue;
    private String comment;
    private double balance;

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public AccountMovement() {
    }

    public AccountMovement(String date, int customerId, int currencyId, long incomingDocumentId, long outgoingDocumentId, double incomingValue, double outgoingValue, String comment, double balance) {
        this.date = date;
        this.customerId = customerId;
        this.currencyId = currencyId;
        this.incomingDocumentId = incomingDocumentId;
        this.outgoingDocumentId = outgoingDocumentId;
        this.incomingValue = incomingValue;
        this.outgoingValue = outgoingValue;
        this.comment = comment;
        this.balance = balance;
    }

    

    public String getDate() {
        return date;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getCurrencyId() {
        return currencyId;
    }

    public long getIncomingDocumentId() {
        return incomingDocumentId;
    }

    public long getOutgoingDocumentId() {
        return outgoingDocumentId;
    }

    public double getIncomingValue() {
        return incomingValue;
    }

    public double getOutgoingValue() {
        return outgoingValue;
    }

    public String getComment() {
        return comment;
    }

    public double getBalance() {
        return balance;
    }
    
    
    
    public static AccountMovementBuilder builder() {
        return new AccountMovementBuilder();
    }
    
}
