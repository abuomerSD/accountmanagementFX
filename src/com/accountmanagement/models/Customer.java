
package com.accountmanagement.models;


public class Customer {
    
    private int id;
    private String name;
    private String phone;

    private Customer() {
    }

    public Customer(int id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }
    
    public static CustomerBuilder builder(){
        return new CustomerBuilder();
    }
    
    
    
}
