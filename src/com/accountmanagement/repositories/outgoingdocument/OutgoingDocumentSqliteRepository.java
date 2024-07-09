
package com.accountmanagement.repositories.outgoingdocument;

import com.accountmanagement.database.DbConnection;
import com.accountmanagement.models.OutgoingDocument;
import com.accountmanagement.utils.AlertMaker;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class OutgoingDocumentSqliteRepository implements OutgoingDocumentRepository{

    @Override
    public long save(OutgoingDocument outgoingDocument) {
        long generatedKey = 0;
        String sql = "INSERT INTO tb_outgoing_document (Date, CurrencyId, CustomerId, Value, Comment) VALUES (?, ?, ?, ?, ?) ;";
        
        try {
            Connection con = DbConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            
//            SimpleDateFormat df = new SimpleDateFormat("dd-MMMM-yyyy");
//            Calendar calendar = Calendar.getInstance();
//            String date = df.format(calendar.getTime());
            
            ps.setString(1, outgoingDocument.getDate());
            ps.setInt(2, outgoingDocument.getCurrencyId());
            ps.setInt(3, outgoingDocument.getCustomerId());
            ps.setDouble(4, outgoingDocument.getValue());
            ps.setString(5, outgoingDocument.getComment());
            
            System.out.println(ps.toString());
            
            if(ps.executeUpdate() == 1) {
                ResultSet rs = ps.getGeneratedKeys();
                while(rs.next()) {
                    return rs.getLong(1);
                }
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
        
        return generatedKey;
    }

    @Override
    public boolean update(OutgoingDocument outgoingDocument) {
               String sql = "UPDATE tb_outgoing_document SET Date=?, CurrencyId=?, CustomerId=?, Value=?, Comment=? WHERE Id = ? ;";
        try {
            Connection con = DbConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, outgoingDocument.getDate());
            ps.setInt(2, outgoingDocument.getCurrencyId());
            ps.setInt(3, outgoingDocument.getCustomerId());
            ps.setDouble(4, outgoingDocument.getValue());
            ps.setString(5, outgoingDocument.getComment());
            ps.setLong(6, outgoingDocument.getId());
            
            System.out.println(ps.toString());
            
            if(ps.executeUpdate() == 1) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
       
       return false;
    }

    @Override
    public boolean delete(long id) {
        String sql = "DELETE FROM tb_outgoing_document WHERE Id = ?";
        
        try {
            Connection con = DbConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setLong(1, id);
            
            System.out.println(ps.toString());
            
            if(ps.executeUpdate() == 1) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
        
        return false;
    }

    @Override
    public OutgoingDocument findById(long id) {
        OutgoingDocument document = null ;
        
        String sql = "SELECT * FROM tb_outgoing_document WHERE Id = ?";
        
        try {
            Connection con = DbConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setLong(1, id);
            
            System.out.println(ps.toString());
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                document = OutgoingDocument.builder()
                        .id(id)
                        .date(rs.getString("Date"))
                        .currencyId(rs.getInt("CurrencyId"))
                        .customerId(rs.getInt("CustomerId"))
                        .value(rs.getDouble("Value"))
                        .comment(rs.getString("Comment"))
                        .build();
                        
                        
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
        return document;
    }

    @Override
    public ArrayList<OutgoingDocument> findAll() {
        ArrayList<OutgoingDocument> list = new ArrayList<>();
        
        String sql = "SELECT * FROM tb_outgoing_document";
        
        try {
            Connection con = DbConnection.getConnection();
            Statement st = con.createStatement();
            
            ResultSet rs = st.executeQuery(sql);
            
            System.out.println(sql);
            
            while(rs.next()) {
                OutgoingDocument document = OutgoingDocument.builder()
                        .id(rs.getLong("Id"))
                        .date(rs.getString("Date"))
                        .currencyId(rs.getInt("CurrencyId"))
                        .customerId(rs.getInt("CustomerId"))
                        .value(rs.getDouble("Value"))
                        .comment(rs.getString("Comment"))
                        .build();
                list.add(document);
            }
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
        
        return list;
    }

    public ArrayList<OutgoingDocument> findAllDesc() {
        ArrayList<OutgoingDocument> list = new ArrayList<>();
        
        String sql = "SELECT * FROM tb_outgoing_document ORDER BY Id DESC";
        
        try {
            Connection con = DbConnection.getConnection();
            Statement st = con.createStatement();
            
            ResultSet rs = st.executeQuery(sql);
            
            System.out.println(sql);
            
            while(rs.next()) {
                OutgoingDocument document = OutgoingDocument.builder()
                        .id(rs.getLong("Id"))
                        .date(rs.getString("Date"))
                        .currencyId(rs.getInt("CurrencyId"))
                        .customerId(rs.getInt("CustomerId"))
                        .value(rs.getDouble("Value"))
                        .comment(rs.getString("Comment"))
                        .build();
                list.add(document);
            }
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
        
        return list;
    }

    public ArrayList<OutgoingDocument> filterById(Long id) {
        ArrayList<OutgoingDocument> list = new ArrayList<>();
        
        String sql = "SELECT * FROM tb_outgoing_document WHERE Id LIKE '%"+ id +"%'";
        
        try {
            Connection con = DbConnection.getConnection();
            Statement st = con.createStatement();
            
            ResultSet rs = st.executeQuery(sql);
            
            System.out.println(sql);
            
            while(rs.next()) {
                OutgoingDocument document = OutgoingDocument.builder()
                        .id(rs.getLong("Id"))
                        .date(rs.getString("Date"))
                        .currencyId(rs.getInt("CurrencyId"))
                        .customerId(rs.getInt("CustomerId"))
                        .value(rs.getDouble("Value"))
                        .comment(rs.getString("Comment"))
                        .build();
                list.add(document);
            }
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
        
        return list;
    }
    
    
    
}
