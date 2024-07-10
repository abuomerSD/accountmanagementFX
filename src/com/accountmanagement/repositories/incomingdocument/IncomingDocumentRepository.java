
package com.accountmanagement.repositories.incomingdocument;

import com.accountmanagement.models.IncomingDocument;
import java.util.ArrayList;

public interface IncomingDocumentRepository {
    
    int save(IncomingDocument incomingDocument);
    boolean update(IncomingDocument newIncomingDocument);
    boolean delete(long id);
    IncomingDocument findById(long id);
    ArrayList<IncomingDocument> findAll();
    ArrayList<IncomingDocument> findAllDesc();
    ArrayList<IncomingDocument> filterById(String id);
    
    
}
