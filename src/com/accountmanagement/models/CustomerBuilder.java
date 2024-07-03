
package com.accountmanagement.models;


public class CustomerBuilder {
    
    public int id;
    private String name;
    private String phone;
    
    public CustomerBuilder id(int id) {
        this.id = id;
        return this;
    }
    
    public CustomerBuilder name(String name) {
        this.name = name;
        return this;
    }
    
    public CustomerBuilder phone(String phone) {
        this.phone = phone;
        return this;
    }
    
    public Customer build() {
        return new Customer(id, name, phone);
    }
    
}
