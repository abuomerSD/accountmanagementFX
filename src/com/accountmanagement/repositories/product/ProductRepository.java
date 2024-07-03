
package com.accountmanagement.repositories.product;

import com.accountmanagement.models.Product;
import java.util.ArrayList;


public interface ProductRepository {
    
    boolean save(Product product);
    boolean update(Product newProduct);
    boolean delete(int id);
    Product findById(int id);
    ArrayList<Product> findAll();
    ArrayList<Product> findBySerialOrBuyerName(String serial, String buyerName);
    ArrayList<Product> findByBuyerName();
    
}
