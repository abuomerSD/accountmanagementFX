/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accountmanagement.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author asdf
 */
public class DatabaseTablesCreator {
    
    public static void createDbTables() {
        createProductsTable();
        createCustomersTable();
        createSCustomersTable();
        createCurrencyTable();
        createIncomingDocumentTable();
        createOutgoingDocumentTable();
        createAccountMovementTable();
        createSalesInvoiceHeaderTable();
        createSalesInvoiceDetailsTable();
    }

    private static void createProductsTable() {
        
        String sql = "CREATE TABLE IF NOT EXISTS tb_products (\n" +
                            "	Id  INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                            "	Serial VARCHAR(255) UNIQUE,\n" +
                            "	Buyer_Name VARCHAR(100),\n" +
                            "	Buyer_Phone VARCHAR(50),\n" +
                            "	Buyer_Email VARCHAR(100),\n" +
                            "	Password VARCHAR(100),\n" +
                            "	Subscribtion_Date VARCHAR(50),\n" +
                            "	Subscribtion_Value DOUBLE\n" +
                            ");";
        try {
            Connection con = DbConnection.getConnection();
            PreparedStatement statement = con.prepareStatement(sql);
            statement.execute();
//            System.out.println(sql);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private static void createCustomersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS tb_customers (\n" +
                            "	Id INTEGER   PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
                            "	Name VARCHAR(50) NOT NULL UNIQUE,\n" +
                            "	Phone VARCHAR(50) NOT NULL\n" +
                            ");";
        
        try {
            Connection con = DbConnection.getConnection();
            Statement st = con .createStatement();
            st.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createCurrencyTable() {
        String sql = "CREATE TABLE IF NOT EXISTS \"tb_currency\" (\n" +
                            "	\"Id\"	INTEGER NOT NULL,\n" +
                            "	\"Name\"	VARCHAR(50) UNIQUE,\n" +
                            "	PRIMARY KEY(\"Id\" AUTOINCREMENT)\n" +
                            ");";
        
        try {
            Connection con = DbConnection.getConnection();
            Statement st = con.createStatement();
            st.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e, "Error", 0);
        }
    }

    private static void createIncomingDocumentTable() {
        String sql = "CREATE TABLE IF NOT EXISTS \"tb_incoming_document\" (\n" +
                            "	\"Id\"	INTEGER NOT NULL,\n" +
                            "	\"Date\"	VARCHAR(50),\n" +
                            "	\"CurrencyId\"	INTEGER,\n" +
                            "	\"CustomerId\"	INTEGER,\n" +
                            "	\"Value\"	DOUBLE,\n" +
                            "	\"Comment\"	VARCHAR(100),\n" +
                            "	PRIMARY KEY(\"Id\" AUTOINCREMENT)\n" +
                            ");";
        
        try {
            Connection con = DbConnection.getConnection();
            Statement st = con.createStatement();
            st.execute(sql);
        } catch(Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e, "Error", 0);
        }
    }

    private static void createOutgoingDocumentTable() {
        String sql = "CREATE TABLE IF NOT EXISTS \"tb_outgoing_document\" (\n" +
                            "	\"Id\"	INTEGER NOT NULL,\n" +
                            "	\"Date\"	VARCHAR(50),\n" +
                            "	\"CurrencyId\"	INTEGER,\n" +
                            "	\"CustomerId\"	INTEGER,\n" +
                            "	\"Value\"	DOUBLE,\n" +
                            "	\"Comment\"	VARCHAR(100),\n" +
                            "	PRIMARY KEY(\"Id\" AUTOINCREMENT)\n" +
                            ");";
        
        try {
            Connection con = DbConnection.getConnection();
            Statement st = con.createStatement();
            st.execute(sql);
        } catch(Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e, "Error", 0);
        }
    }

    private static void createAccountMovementTable() {
        String sql = "CREATE TABLE  IF NOT EXISTS \"tb_account_movement\" (\n" +
                            "	\"Date\"	VARCHAR(50),\n" +
                            "	\"CustomerId\"	INTEGER,\n" +
                            "   \"CurrencyId\"	INTEGER,\n" +
                            "	\"IncomingDocumentId\"	INTEGER,\n" +
                            "	\"OutgoingDocumentId\"	INTEGER,\n" +
                            "	\"IncomingValue\"	DOUBLE,\n" +
                            "	\"OutgoingValue\"	DOUBLE,\n" +
                            "	\"Comment\"	INTEGER\n" +
                            ");";
        
        try {
            Connection con = DbConnection.getConnection();
            Statement st = con.createStatement();
            st.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e, "Error", 0);
        }
    }

    private static void createSCustomersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS tb_s_customers (\n" +
                            "	Id INTEGER   PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
                            "	Name VARCHAR(50) NOT NULL UNIQUE,\n" +
                            "	Phone VARCHAR(50) NOT NULL\n" +
                            ");";
        
        try {
            Connection con = DbConnection.getConnection();
            Statement st = con .createStatement();
            st.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createSalesInvoiceHeaderTable() {
        String sql = "CREATE TABLE IF NOT EXISTS \"tb_sales_invoice_header\"  (\n" +
                            "	\"Id\"	INTEGER NOT NULL,\n" +
                            "	\"Date\"	VARCHAR(50),\n" +
                            "	\"CustomerId\"	INTEGER,\n" +
                            "	\"Total\"	DOUBLE,\n" +
                            "	\"IsFileType\"	BOOLEAN,\n" +
                            "	\"FilePath\"	VARCHAR(100),\n" +
                            "	\"Tax\"	DOUBLE,\n" +
                            "	\"Discount\"	DOUBLE,\n" +
                            "	\"Comment\"	VARCHAR(100),\n" +
                            "	PRIMARY KEY(\"Id\" AUTOINCREMENT)\n" +
                            ");";
        
        try {
            Connection con = DbConnection.getConnection();
            Statement st = con.createStatement();
            st.execute(sql);
        } catch (Exception e) {
        }
    }

    private static void createSalesInvoiceDetailsTable() {
        String sql = "CREATE TABLE  IF NOT EXISTS \"tb_sales_invoice_details\" (\n" +
                            "	\"HeaderId\"	INTEGER NOT NULL,\n" +
                            "	\"ProductName\"	VARCHAR(100),\n" +
                            "	\"ProductQuantity\"	DOUBLE,\n" +
                            "	\"ProductPrice\"	DOUBLE,\n" +
                            "	\"ProductTotal\"	DOUBLE\n" +
                            ");";
        
        try {
            Connection con = DbConnection.getConnection();
            Statement st = con.createStatement();
            st.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    
    
}
