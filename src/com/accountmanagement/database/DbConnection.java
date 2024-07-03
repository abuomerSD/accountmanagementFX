
package com.accountmanagement.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {

    private DbConnection() {
    }
    
     
    
    
    
    static Connection con = null;
    
    public static Connection getConnection() {
        
        
        try {
            
            if(con == null){
                Class.forName("org.sqlite.JDBC");
                con = DriverManager.getConnection("jdbc:sqlite:Database.db");  
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return con;
    }
    
}
