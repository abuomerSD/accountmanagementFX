
package com.accountmanagement.repositories.salesinvoicedetails;

import com.accountmanagement.database.DbConnection;
import com.accountmanagement.models.SalesInvoiceDetails;
import com.accountmanagement.utils.AlertMaker;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class SalesInvoiceDetailsSqliteRepository implements SalesInvoiceDetailsRepository{

    @Override
    public boolean save(SalesInvoiceDetails salesInvoiceDetails) {
        String sql = "INSERT INTO tb_sales_invoice_details VALUES (?,?,?,?,?)";
        
        try {
            Connection con = DbConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setLong(1, salesInvoiceDetails.getHeaderId());
            ps.setString(2, salesInvoiceDetails.getProductName());
            ps.setDouble(3, salesInvoiceDetails.getProductQty());
            ps.setDouble(4, salesInvoiceDetails.getProductPrice());
            ps.setDouble(5, salesInvoiceDetails.getProductTotal());
            
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
    public boolean delete(long HeaderId) {
        String sql = "DELETE FROM tb_sales_invoice_details WHERE HeaderId = ?;";
        
        try {
            Connection con = DbConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setLong(1, HeaderId);
            
            System.out.println(ps.toString());
            
            if(ps.executeUpdate() > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
        return false;
    }

    @Override
    public ArrayList<SalesInvoiceDetails> findByHeaderID(long HeaderId) {
        ArrayList<SalesInvoiceDetails> list = new ArrayList<>();
        String sql = "SELECT * FROM tb_sales_invoice_details WHERE HeaderId = ?;";
        
        try {
            Connection con = DbConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setLong(1, HeaderId);
            
            System.out.println(ps.toString());
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                SalesInvoiceDetails details = SalesInvoiceDetails.builder()
                        .headerId(rs.getLong("HeaderId"))
                        .productName(rs.getString("ProductName"))
                        .productPrice(rs.getDouble("ProductPrice"))
                        .productQty(rs.getDouble("ProductQuantity"))
                        .productTotal(rs.getDouble("ProductTotal"))
                        .build();
                
                list.add(details);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
        return list;
    }

    @Override
    public ArrayList<SalesInvoiceDetails> findAll() {
        ArrayList<SalesInvoiceDetails> list = new ArrayList<>();
        String sql = "SELECT * FROM tb_sales_invoice_details;";
        
        try {
            Connection con = DbConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            
//            ps.setLong(1, HeaderId);
            
            System.out.println(ps.toString());
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                SalesInvoiceDetails details = SalesInvoiceDetails.builder()
                        .headerId(rs.getLong("HeaderId"))
                        .productName(rs.getString("ProductName"))
                        .productPrice(rs.getDouble("ProductPrice"))
                        .productQty(rs.getDouble("ProductQuantity"))
                        .productTotal(rs.getDouble("ProductTotal"))
                        .build();
                
                list.add(details);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
        return list;
    }
    
}
