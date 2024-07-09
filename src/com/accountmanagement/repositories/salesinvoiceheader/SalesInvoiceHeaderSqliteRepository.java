
package com.accountmanagement.repositories.salesinvoiceheader;

import com.accountmanagement.database.DbConnection;
import com.accountmanagement.models.SalesInvoiceHeader;
import com.accountmanagement.utils.AlertMaker;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class SalesInvoiceHeaderSqliteRepository implements SalesInvoiceHeaderRepository{

    @Override
    public long save(SalesInvoiceHeader invoiceHeader) {
        String sql = "INSERT INTO tb_sales_invoice_header (Date, CustomerId, Total, IsFileType, FilePath, Tax, Discount, Comment) VALUES (?,?,?,?,?,?,?,?);";
        
        try {
            Connection con = DbConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, invoiceHeader.getDate());
            ps.setInt(2, invoiceHeader.getCustomerId());
            ps.setDouble(3, invoiceHeader.getTotal());
            ps.setBoolean(4, invoiceHeader.isIsFileType());
            ps.setString(5, invoiceHeader.getFilePath());
            ps.setDouble(6, invoiceHeader.getTax());
            ps.setDouble(7, invoiceHeader.getDiscount());
            ps.setString(8, invoiceHeader.getComment());
            
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
        
        return 0;
    }

    @Override
    public boolean update(SalesInvoiceHeader invoiceHeader) {
        String sql = "UPDATE tb_sales_invoice_header SET Date=?, CustomerId=?, Total=?, IsFileType=?, FilePath=?, Tax=?, Discount=?, Comment=? WHERE Id=?;";
        
        try {
            Connection con = DbConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, invoiceHeader.getDate());
            ps.setInt(2, invoiceHeader.getCustomerId());
            ps.setDouble(3, invoiceHeader.getTotal());
            ps.setBoolean(4, invoiceHeader.isIsFileType());
            ps.setString(5, invoiceHeader.getFilePath());
            ps.setDouble(6, invoiceHeader.getTax());
            ps.setDouble(7, invoiceHeader.getDiscount());
            ps.setString(8, invoiceHeader.getComment());
            ps.setLong(9, invoiceHeader.getId());
            
            System.out.println(ps.toString());
            
            if(ps.executeUpdate() > 0 ) {
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
        String sql = "DELETE FROM tb_sales_invoice_header WHERE Id = ?";
        
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
    public SalesInvoiceHeader findById(long id) {
        SalesInvoiceHeader invoiceHeader  = null;
        String sql = "SELECT * FROM tb_sales_invoice_header WHERE Id = ?";
        
        try {
            Connection con = DbConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setLong(1, id);
            
            System.out.println(ps.toString());
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                invoiceHeader = SalesInvoiceHeader.builder()
                        .date(rs.getString("Date"))
                        .customerId(rs.getInt("CustomerId"))
                        .total(rs.getDouble("Total"))
                        .isFileType(rs.getBoolean("IsFileType"))
                        .filePath(rs.getString("FilePath"))
                        .tax(rs.getDouble("Tax"))
                        .discount(rs.getDouble("Discount"))
                        .comment(rs.getString("Comment"))
                        .build();
                
            }
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
        
        return invoiceHeader;
    }

    @Override
    public ArrayList<SalesInvoiceHeader> findAll() {
        ArrayList<SalesInvoiceHeader> list = new ArrayList<>();
        String sql = "SELECT * FROM tb_sales_invoice_header";
        
        try {
            Connection con = DbConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                SalesInvoiceHeader invoiceHeader = SalesInvoiceHeader.builder()
                        .id(rs.getLong("Id"))
                        .date(rs.getString("Date"))
                        .customerId(rs.getInt("CustomerId"))
                        .total(rs.getDouble("Total"))
                        .isFileType(rs.getBoolean("IsFileType"))
                        .filePath(rs.getString("FilePath"))
                        .tax(rs.getDouble("Tax"))
                        .discount(rs.getDouble("Discount"))
                        .comment(rs.getString("Comment"))
                        .build();
                
                list.add(invoiceHeader);
            }
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
        
        return list;
    }
    
    @Override
    public ArrayList<SalesInvoiceHeader> findAllDesc() {
        ArrayList<SalesInvoiceHeader> list = new ArrayList<>();
        String sql = "SELECT * FROM tb_sales_invoice_header  ORDER BY Id DESC";
        
        try {
            Connection con = DbConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                SalesInvoiceHeader invoiceHeader = SalesInvoiceHeader.builder()
                        .id(rs.getLong("Id"))
                        .date(rs.getString("Date"))
                        .customerId(rs.getInt("CustomerId"))
                        .total(rs.getDouble("Total"))
                        .isFileType(rs.getBoolean("IsFileType"))
                        .filePath(rs.getString("FilePath"))
                        .tax(rs.getDouble("Tax"))
                        .discount(rs.getDouble("Discount"))
                        .comment(rs.getString("Comment"))
                        .build();
                
                list.add(invoiceHeader);
            }
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
        
        return list;
    }

    @Override
    public ArrayList<SalesInvoiceHeader> findAllBySearchWords(String customerId, String isFileType) {
        ArrayList<SalesInvoiceHeader> list = new ArrayList<>();
        String sql = "SELECT * FROM tb_sales_invoice_header  WHERE CustomerId LIKE '%" + customerId + "%' "
                + "AND IsFileType LIKE '%" + isFileType + "%' ;";
                
        
        try {
            Connection con = DbConnection.getConnection();
            Statement st = con.createStatement();
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                SalesInvoiceHeader invoiceHeader = SalesInvoiceHeader.builder()
                        .id(rs.getLong("Id"))
                        .date(rs.getString("Date"))
                        .customerId(rs.getInt("CustomerId"))
                        .total(rs.getDouble("Total"))
                        .isFileType(rs.getBoolean("IsFileType"))
                        .filePath(rs.getString("FilePath"))
                        .tax(rs.getDouble("Tax"))
                        .discount(rs.getDouble("Discount"))
                        .comment(rs.getString("Comment"))
                        .build();
                
                list.add(invoiceHeader);
            }
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
        
        return list;
    }

    @Override
    public ArrayList<SalesInvoiceHeader> findAllById(String id) {
        ArrayList<SalesInvoiceHeader> list = new ArrayList<>();
        String sql = "SELECT * FROM tb_sales_invoice_header  WHERE Id LIKE '%" + id + "%' ;";
                
                
        
        try {
            Connection con = DbConnection.getConnection();
            Statement st = con.createStatement();
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                SalesInvoiceHeader invoiceHeader = SalesInvoiceHeader.builder()
                        .id(rs.getLong("Id"))
                        .date(rs.getString("Date"))
                        .customerId(rs.getInt("CustomerId"))
                        .total(rs.getDouble("Total"))
                        .isFileType(rs.getBoolean("IsFileType"))
                        .filePath(rs.getString("FilePath"))
                        .tax(rs.getDouble("Tax"))
                        .discount(rs.getDouble("Discount"))
                        .comment(rs.getString("Comment"))
                        .build();
                
                list.add(invoiceHeader);
            }
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
        
        return list;
    }
    
}
