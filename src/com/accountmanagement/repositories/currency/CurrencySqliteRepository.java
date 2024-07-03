
package com.accountmanagement.repositories.currency;

import com.accountmanagement.database.DbConnection;
import com.accountmanagement.models.Currency;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class CurrencySqliteRepository implements CurrencyRepository {

    @Override
    public boolean save(Currency currency) {
        String sql = "INSERT INTO tb_currency (Name) VALUES (?);";
        
        try {
            Connection con = DbConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, currency.getName());
            
            System.out.println(ps.toString());
            
            int result = ps.executeUpdate();
            
            if(result == 1) {
                return true;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e, "Error", 0);
        }
        
        return false;
    }

    @Override
    public boolean update(Currency newCurrency) {
        String sql = "UPDATE tb_currency SET Name = ? WHERE Id = ?";
        
        try {
            Connection con = DbConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, newCurrency.getName());
            ps.setInt(2, newCurrency.getId());
            
            System.out.println(ps.toString());
            
            int result = ps.executeUpdate();
            
            if(result == 1) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e, "Error", 0);
        }
        
        return false;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM tb_currency WHERE ID = ?";
        
        try {
            Connection con = DbConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setInt(1, id);
            
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
    public Currency findById(int id) {
        Currency currency = null;
        String sql = "SELECT * FROM tb_currency WHERE id = ?;";
        
        try {
            Connection con = DbConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                currency = Currency.builder()
                        .id(rs.getInt("Id"))
                        .name(rs.getString("Name"))
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e, "Error", 0);
        }
        
        return currency;
    }

    @Override
    public ArrayList<Currency> findAll() {
        ArrayList<Currency> list = new ArrayList<>();
        
        String sql = "SELECT * FROM tb_currency;";
        
        try {
            Connection con = DbConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            
            System.out.println(ps.toString());
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                Currency currency = Currency.builder()
                        .id(rs.getInt("Id"))
                        .name(rs.getString("Name"))
                        .build();
                
                list.add(currency);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e, "Error", 0);
        }
        
        return list;
    }

    @Override
    public Currency findByName(String name) {
        Currency currency = null;
        String sql = "SELECT * FROM tb_currency WHERE Name = ? ;";
        
        try {
            Connection con = DbConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, name);
            
            System.out.println(ps.toString());
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                currency = Currency.builder()
                        .id(rs.getInt("Id"))
                        .name(name)
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e, "Error", 0);
        }
        
        return currency;
    }
    
}
