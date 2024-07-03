
package com.accountmanagement.models;

public class Currency {
    
    private int id;
    private String name;

    private Currency() {
    }

    public Currency(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    public static CurrencyBuilder builder() {
        return new CurrencyBuilder();
    }
    
    @Override 
    public String toString() {
        return "[" +this.getId() + " " +this.getName() + "]";
        
    }
    
    
    
}
