
package com.accountmanagement.models;

public class CustomerBalance {
    
    private String currencyName;
    private String balance;

    public CustomerBalance() {
    }

    public CustomerBalance(String currencyName, String balance) {
        this.currencyName = currencyName;
        this.balance = balance;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
    
    
    
}
