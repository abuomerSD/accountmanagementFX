
package com.accountmanagement.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class SalesInvoicesController implements Initializable {

    @FXML
    private TextField txtCustomerName;
    @FXML
    private TextField txtCustomerPhone;
    @FXML
    private Label lbCustomerStatus;
    @FXML
    private TextField txtCustomerNameSearch;
    @FXML
    private TableView<?> tbCustomer;
    @FXML
    private TableColumn<?, ?> colCustomerId;
    @FXML
    private TableColumn<?, ?> colCustomerName;
    @FXML
    private TableColumn<?, ?> colCustomerPhone;
    @FXML
    private DatePicker txtDate;
    @FXML
    private ComboBox<?> cbInvoiceType;
    @FXML
    private TextField txtFilePath;
    @FXML
    private ComboBox<?> cbCustomerName;
    @FXML
    private TextField txtProductName;
    @FXML
    private TextField txtQty;
    @FXML
    private TextField txtPrice;
    @FXML
    private Label lbInvoiceTotal;
    @FXML
    private Label lbDiscount;
    @FXML
    private Label lbTax;
    @FXML
    private Label lbTotal;
    @FXML
    private TextField txtTax;
    @FXML
    private TextField txtDiscount;
    @FXML
    private TextField txtComment;
    @FXML
    private Label lbInvoiceStatus;
    @FXML
    private TextField txtInvoiceIdSearch;
    @FXML
    private ComboBox<?> cbCustomerSearch;
    @FXML
    private ComboBox<?> cbInvoiceTypeSearch;
    @FXML
    private Button resetInvoicesListTable;
    @FXML
    private TableView<?> tbInvoicesList;
    @FXML
    private TableView<?> tbProducts;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void saveCustomer(ActionEvent event) {
    }

    @FXML
    private void updateCustomer(ActionEvent event) {
    }

    @FXML
    private void deleteCustomer(ActionEvent event) {
    }

    @FXML
    private void filterCustomerTable(KeyEvent event) {
    }

    @FXML
    private void openFileChooser(ActionEvent event) {
    }

    @FXML
    private void addProductToTable(ActionEvent event) {
    }

    @FXML
    private void deleteProduct(ActionEvent event) {
    }

    @FXML
    private void deleteAllProducts(ActionEvent event) {
    }

    @FXML
    private void saveInvoice(ActionEvent event) {
    }

    @FXML
    private void printInvoice(ActionEvent event) {
    }

    @FXML
    private void newInvoice(ActionEvent event) {
    }

    @FXML
    private void viewInvoice(ActionEvent event) {
    }

    @FXML
    private void updateInvoice(ActionEvent event) {
    }

    @FXML
    private void deleteInvoice(ActionEvent event) {
    }
    
}
