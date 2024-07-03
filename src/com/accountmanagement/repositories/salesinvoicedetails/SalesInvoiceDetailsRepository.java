
package com.accountmanagement.repositories.salesinvoicedetails;

import com.accountmanagement.models.SalesInvoiceDetails;
import java.util.ArrayList;


public interface SalesInvoiceDetailsRepository {
    
    boolean save(SalesInvoiceDetails salesInvoiceDetails);
//    boolean update(SalesInvoiceDetails salesInvoiceDetails);
    boolean delete(long HeaderId);
    ArrayList<SalesInvoiceDetails> findByHeaderID(long HeaderId);
    ArrayList<SalesInvoiceDetails> findAll();
    
}
