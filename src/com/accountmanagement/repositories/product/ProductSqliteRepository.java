
package com.accountmanagement.repositories.product;

import com.accountmanagement.database.DbConnection;
import com.accountmanagement.models.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author asdf
 */
public class ProductSqliteRepository implements ProductRepository{

    @Override
    public boolean save(Product product) {
        try {
            String sql = "INSERT INTO tb_products (Serial, Buyer_Name, Buyer_Phone, Buyer_Email, Password, Subscribtion_Date, Subscribtion_Value) VALUES(?,?,?,?,?,?,?)";
            Connection con = DbConnection.getConnection();
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, product.getSerial());
            ps.setString(2, product.getBuyerName());
            ps.setString(3, product.getBuyerPhone());
            ps.setString(4, product.getBuyerEmail());
            ps.setString(5, product.getPassword());
            ps.setString(6, product.getSubscribtionDate());
            ps.setDouble(7, product.getSubscribtionValue());
//            System.out.println(ps.toString());

            int status = ps.executeUpdate();
            if(status == 1){
                
                System.out.println(ps.toString());
                return true;
            }
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
        return false;
    }

    @Override
    public boolean update(Product newProduct) {
        String sql = "UPDATE tb_products SET Serial = '"+ newProduct.getSerial() +"' , "
                                            + "Buyer_Name = '" + newProduct.getBuyerName() + "' , "
                                            + "Buyer_Phone = '" + newProduct.getBuyerPhone() + "' , "
                                            + "Buyer_Email = '" + newProduct.getBuyerEmail() + "' , "
                                            + "Password = '" + newProduct.getPassword() + "' , "
                                            + "Subscribtion_Date = '" + newProduct.getSubscribtionDate() + "' , "
                                            + "Subscribtion_Value = '"+ newProduct.getSubscribtionValue() 
                                            + "' WHERE Id =" + newProduct.getId();
        try {
            Connection con = DbConnection.getConnection();
            Statement st = con.createStatement();
            
//            System.out.println(sql);
            
            int result = st.executeUpdate(sql);
            
            
            if(result == 1) {
//                System.out.println(st.toString());
                return true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
        return false;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM tb_products WHERE Id = ?";
        
        try {
            Connection con = DbConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            
            System.out.println(ps.toString());
            
            int result = ps.executeUpdate();
            
            if(result == 1) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
        }
        
        return false;
    }

    @Override
    public Product findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Product> findAll() {
        ArrayList<Product> list = new ArrayList<Product>();
        
        try {
            
            String sql = "SELECT * FROM tb_products";
            Connection con = DbConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                Product product = new Product();
                product.setBuyerEmail(rs.getString("Buyer_Email"));
                product.setBuyerName(rs.getString("Buyer_Name"));
                product.setBuyerPhone(rs.getString("Buyer_Phone"));
                product.setId(rs.getInt("Id"));
                product.setPassword(rs.getString("Password"));
                product.setSerial(rs.getString("Serial"));
                product.setSubscribtionDate(rs.getString("Subscribtion_Date"));
                product.setSubscribtionValue(rs.getDouble("Subscribtion_Value"));
                
                list.add(product);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "خطأ", JOptionPane.ERROR_MESSAGE);
        }
        
        
        
        return list;
    }

    @Override
    public ArrayList<Product> findBySerialOrBuyerName(String serial, String buyerName) {
        ArrayList<Product> list = new ArrayList<Product>();
        String sql = "SELECT * FROM tb_products WHERE Serial LIKE '%" + serial +"%' "
                                                + "AND Buyer_Name LIKE '%"+ buyerName +"%'";
        
        try {
            Connection con = DbConnection.getConnection();
            Statement ps = con.createStatement();
            System.out.println(sql);
            System.out.println(ps.toString());
            
            ResultSet rs = ps.executeQuery(sql);
            
            while(rs.next()){
                Product product = new Product();
                product.setBuyerEmail(rs.getString("Buyer_Email"));
                product.setBuyerName(rs.getString("Buyer_Name"));
                product.setBuyerPhone(rs.getString("Buyer_Phone"));
                product.setId(rs.getInt("Id"));
                product.setPassword(rs.getString("Password"));
                product.setSerial(rs.getString("Serial"));
                product.setSubscribtionDate(rs.getString("Subscribtion_Date"));
                product.setSubscribtionValue(rs.getDouble("Subscribtion_Value"));
                
                list.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        return list;
        
    }

    @Override
    public ArrayList<Product> findByBuyerName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
