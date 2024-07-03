
package com.accountmanagement.repositories.customer;

import com.accountmanagement.models.Customer;
import java.util.ArrayList;

public interface CustomerRepository {
    
    boolean save(Customer customer);
    boolean update(Customer newCustomer);
    boolean delete(int id);
    Customer findById(int id);
    Customer findByName(String name);
    ArrayList<Customer> findAll();
    ArrayList<Customer> findBySearchWords(String searchWords);
    
}
