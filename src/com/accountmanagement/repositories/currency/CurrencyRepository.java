
package com.accountmanagement.repositories.currency;

import com.accountmanagement.models.Currency;
import java.util.ArrayList;

public interface CurrencyRepository {
    
    boolean save(Currency currency);
    boolean update(Currency newCurrency);
    boolean delete(int id);
    Currency findById(int id);
    Currency findByName(String name);
    ArrayList<Currency> findAll();
    
}
