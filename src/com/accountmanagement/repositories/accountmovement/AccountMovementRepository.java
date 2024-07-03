
package com.accountmanagement.repositories.accountmovement;

import com.accountmanagement.models.AccountMovement;
import java.util.ArrayList;

public interface AccountMovementRepository {
    
    boolean save(AccountMovement accountMovement);
    boolean update(AccountMovement newAccountMovement);
    boolean delete(long incomingDocumentId , long outgoingDocumentId);
    ArrayList<AccountMovement> findAll();
    ArrayList<AccountMovement> findByCustomerIdAndCurrencyId(int customerId, int currencyId);
    double getCustomerBalance(int customerId, int currencyId);
}
