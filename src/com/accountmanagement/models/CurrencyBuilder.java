
package com.accountmanagement.models;

public class CurrencyBuilder {
    private int id;
    private String name;
    
    public CurrencyBuilder id(int id) {
        this.id = id;
        return this;
    }
    
    public CurrencyBuilder name(String name) {
        this.name = name ;
        return this;
    }
    
    public Currency build() {
        return new Currency(id, name);
    }
    
}
