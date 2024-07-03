
package com.accountmanagement.repositories.outgoingdocument;

import com.accountmanagement.models.OutgoingDocument;
import java.util.ArrayList;


public interface OutgoingDocumentRepository {
    
    long save(OutgoingDocument outgoingDocument);
    boolean update(OutgoingDocument outgoingDocument);
    boolean delete(long id);
    OutgoingDocument findById(long id);
    ArrayList<OutgoingDocument> findAll();
    ArrayList<OutgoingDocument> findAllDesc();
    ArrayList<OutgoingDocument> filterById(Long id);
    
}
