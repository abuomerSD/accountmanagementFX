
package com.accountmanagement.repositories.incomingdocument;

import com.accountmanagement.database.DbConnection;
import com.accountmanagement.models.IncomingDocument;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JOptionPane;


public class IncomingDocumentSqliteRepository implements IncomingDocumentRepository{

    @Override
    public int save(IncomingDocument incomingDocument) {
        
        int generatedKey = 0;
        String sql = "INSERT INTO tb_incoming_document (Date, CurrencyId, CustomerId, Value, Comment) VALUES (?, ?, ?, ?, ?) ;";
        
        try {
            Connection con = DbConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            
//            SimpleDateFormat df = new SimpleDateFormat("dd-MMMM-yyyy");
//            Calendar calendar = Calendar.getInstance();
//            String date = df.format(calendar.getTime());
            
            ps.setString(1, incomingDocument.getDate());
            ps.setInt(2, incomingDocument.getCurrencyId());
            ps.setInt(3, incomingDocument.getCustomerId());
            ps.setDouble(4, incomingDocument.getValue());
            ps.setString(5, incomingDocument.getComment());
            
            System.out.println(ps.toString());
            
            if(ps.executeUpdate() == 1) {
                ResultSet rs = ps.getGeneratedKeys();
                while (rs.next()) {                    
                    generatedKey = rs.getInt(1);
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e, "Error", 0);
        }
        
        return generatedKey;
    }

    @Override
    public boolean update(IncomingDocument newIncomingDocument) {
       String sql = "UPDATE tb_incoming_document SET Date=?, CurrencyId=?, CustomerId=?, Value=?, Comment=? WHERE Id = ? ;";
        try {
            Connection con = DbConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, newIncomingDocument.getDate());
            ps.setInt(2, newIncomingDocument.getCurrencyId());
            ps.setInt(3, newIncomingDocument.getCustomerId());
            ps.setDouble(4, newIncomingDocument.getValue());
            ps.setString(5, newIncomingDocument.getComment());
            ps.setLong(6, newIncomingDocument.getId());
            
            System.out.println(ps.toString());
            
            if(ps.executeUpdate() == 1) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e, "Error", 0);
        }
       
       return false;
    }

    @Override
    public boolean delete(long id) {
        String sql = "DELETE FROM tb_incoming_document WHERE Id = ?";
        
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
            JOptionPane.showMessageDialog(null, e, "Error", 0);
        }
        
        return false;
    }

    @Override
    public IncomingDocument findById(long id) {
        IncomingDocument document = null ;
        
        String sql = "SELECT * FROM tb_incoming_document WHERE Id = ?";
        
        try {
            Connection con = DbConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setLong(1, id);
            
            System.out.println(ps.toString());
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                document = IncomingDocument.builder()
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
            JOptionPane.showMessageDialog(null, e, "Error", 0);
        }
        return document;
    }

    @Override
    public ArrayList<IncomingDocument> findAll() {
        ArrayList<IncomingDocument> list = new ArrayList<>();
        
        String sql = "SELECT * FROM tb_incoming_document";
        
        try {
            Connection con = DbConnection.getConnection();
            Statement st = con.createStatement();
            
            ResultSet rs = st.executeQuery(sql);
            
            System.out.println(sql);
            
            while(rs.next()) {
                IncomingDocument document = IncomingDocument.builder()
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
            JOptionPane.showMessageDialog(null, e, "Error", 0);
        }
        
        return list;
    }

    @Override
    public ArrayList<IncomingDocument> findAllDesc() {
        ArrayList<IncomingDocument> list = new ArrayList<>();
        
        String sql = "SELECT * FROM tb_incoming_document ORDER BY Id DESC";
        
        try {
            Connection con = DbConnection.getConnection();
            Statement st = con.createStatement();
            
            ResultSet rs = st.executeQuery(sql);
            
            System.out.println(sql);
            
            while(rs.next()) {
                IncomingDocument document = IncomingDocument.builder()
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
            JOptionPane.showMessageDialog(null, e, "Error", 0);
        }
        
        return list;
    }

    @Override
    public ArrayList<IncomingDocument> filterById(long id) {
        ArrayList<IncomingDocument> list = new ArrayList<>();
        
        String sql = "SELECT * FROM tb_incoming_document WHERE Id LIKE '%"+ id +"%'";
        
        try {
            Connection con = DbConnection.getConnection();
            Statement st = con.createStatement();
            
            ResultSet rs = st.executeQuery(sql);
            
            System.out.println(sql);
            
            while(rs.next()) {
                IncomingDocument document = IncomingDocument.builder()
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
            JOptionPane.showMessageDialog(null, e, "Error", 0);
        }
        
        return list;
    }

    
    
}
