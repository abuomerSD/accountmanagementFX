
package com.accountmanagement.repositories.sCustomer;

import com.accountmanagement.models.Customer;
import java.util.ArrayList;


// this repo is for saving customers in sales invoices customer
// sales invoices customers is not the same regular customers

public interface ScustomerRepository {
    boolean save(Customer customer);
    boolean update(Customer newCustomer);
    boolean delete(int id);
    Customer findById(int id);
    Customer findByName(String name);
    ArrayList<Customer> findAll();
    ArrayList<Customer> findBySearchWords(String searchWords);
}
