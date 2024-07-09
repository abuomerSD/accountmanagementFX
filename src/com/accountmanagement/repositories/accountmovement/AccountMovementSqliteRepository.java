
package com.accountmanagement.repositories.accountmovement;

import com.accountmanagement.database.DbConnection;
import com.accountmanagement.models.AccountMovement;
import com.accountmanagement.utils.AlertMaker;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class AccountMovementSqliteRepository implements AccountMovementRepository{

    @Override
    public boolean save(AccountMovement accountMovement) {
        String sql = " INSERT INTO tb_account_movement (Date, CustomerId, CurrencyId, IncomingDocumentId, OutgoingDocumentId, IncomingValue, OutgoingValue, Comment) VALUES (?,?,?,?,?,?,?,?) ;";
        
        try {
            Connection con = DbConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, accountMovement.getDate());
            ps.setInt(2, accountMovement.getCustomerId());
            ps.setInt(3, accountMovement.getCurrencyId());
            ps.setLong(4, accountMovement.getIncomingDocumentId());
            ps.setLong(5, accountMovement.getOutgoingDocumentId());
            ps.setDouble(6, accountMovement.getIncomingValue());
            ps.setDouble(7, accountMovement.getOutgoingValue());
            ps.setString(8, accountMovement.getComment());
            
            
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
    public boolean update(AccountMovement newAccountMovement) {
        String sql = " UPDATE tb_account_movement SET Date=?, CustomerId=?, CurrencyId=?, IncomingValue=?, OutgoingValue=?, Comment=? WHERE IncomingDocumentId=? AND OutgoingDocumentId=? ";
        
        try {
            Connection con = DbConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, newAccountMovement.getDate());
            ps.setInt(2, newAccountMovement.getCustomerId());
            ps.setInt(3, newAccountMovement.getCurrencyId());
            ps.setDouble(4, newAccountMovement.getIncomingValue());
            ps.setDouble(5, newAccountMovement.getOutgoingValue());
            ps.setString(6, newAccountMovement.getComment());
            ps.setLong(7, newAccountMovement.getIncomingDocumentId());
            ps.setLong(8, newAccountMovement.getOutgoingDocumentId());
            
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
    public boolean delete(long incomingDocumentId, long outgoingDocumentId) {
        String sql = "DELETE FROM tb_account_movement WHERE IncomingDocumentId=? AND OutgoingDocumentId=?";
        
        try {
            Connection con = DbConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setLong(1, incomingDocumentId);
            ps.setLong(2, outgoingDocumentId);
            
            
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
    public ArrayList<AccountMovement> findAll() {
        String sql = " SELECT * FROM tb_account_movement; ";
        ArrayList<AccountMovement> list = new ArrayList<>();
        
        try {
            Connection con = DbConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            System.out.println(sql);
            
            while(rs.next()) {
                AccountMovement accountMovement = AccountMovement.builder()
                        .date(rs.getString("Date"))
                        .customerId(rs.getInt("CustomerId"))
                        .currencyId(rs.getInt("CurrencyId"))
                        .incomingDocumentId(rs.getLong("IncomingDocumentId"))
                        .outgoingDocumentId(rs.getLong("OutgoingDocumentId"))
                        .incomingValue(rs.getDouble("IncomingValue"))
                        .outgoingValue(rs.getDouble("OutgoingValue"))
                        .comment(rs.getString("Comment"))
                        .build();
                
                list.add(accountMovement);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
        
        return list;
    }

    @Override
    public ArrayList<AccountMovement> findByCustomerIdAndCurrencyId(int customerId, int currencyId) {
        String sql = " SELECT * FROM tb_account_movement WHERE CustomerId=? AND CurrencyId=? ";
        ArrayList<AccountMovement> list = new ArrayList<>();
        
        try {
            Connection con = DbConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setInt(1, customerId);
            ps.setInt(2, currencyId);
            
            System.out.println(ps.toString());
            
            ResultSet rs = ps.executeQuery();
            
            
            while(rs.next()) {
                AccountMovement accountMovement = AccountMovement.builder()
                        .date(rs.getString("Date"))
                        .customerId(rs.getInt("CustomerId"))
                        .currencyId(rs.getInt("CurrencyId"))
                        .incomingDocumentId(rs.getLong("IncomingDocumentId"))
                        .outgoingDocumentId(rs.getLong("OutgoingDocumentId"))
                        .incomingValue(rs.getDouble("IncomingValue"))
                        .outgoingValue(rs.getDouble("OutgoingValue"))
                        .comment(rs.getString("Comment"))
                        .build();
                
                list.add(accountMovement);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
        
        return list;
    }

     @Override
    public double getCustomerBalance(int customerId, int currencyId) {
        double balance = 0.0;
        String sql = " SELECT * FROM tb_account_movement WHERE CustomerId=? AND CurrencyId=? ";
        ArrayList<AccountMovement> list = new ArrayList<>();
        
        try {
            Connection con = DbConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setInt(1, customerId);
            ps.setInt(2, currencyId);
            
            System.out.println(ps.toString());
            
            ResultSet rs = ps.executeQuery();
            
            
            while(rs.next()) {
                AccountMovement accountMovement = AccountMovement.builder()
                        .date(rs.getString("Date"))
                        .customerId(rs.getInt("CustomerId"))
                        .currencyId(rs.getInt("CurrencyId"))
                        .incomingDocumentId(rs.getLong("IncomingDocumentId"))
                        .outgoingDocumentId(rs.getLong("OutgoingDocumentId"))
                        .incomingValue(rs.getDouble("IncomingValue"))
                        .outgoingValue(rs.getDouble("OutgoingValue"))
                        .comment(rs.getString("Comment"))
                        .build();
                
                double incomingValue = accountMovement.getIncomingValue();
                double outgoingValue = accountMovement.getOutgoingValue();
                
                balance = balance + (outgoingValue - incomingValue);
                
                list.add(accountMovement);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
        
        return balance;
    }
    
    
}
