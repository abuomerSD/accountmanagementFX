
package com.accountmanagement.controllers;

import com.accountmanagement.models.Customer;
import com.accountmanagement.repositories.customer.CustomerSqliteRepository;
import com.accountmanagement.utils.AlertMaker;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class CustomersController implements Initializable {

    @FXML
    private TextField txtCustomerName;
    @FXML
    private TextField txtCustomerPhone;
    @FXML
    private Label lbCustomerStatus;
    @FXML
    private TextField txtCustomerNameSearch;
    @FXML
    private TableView<Customer> tbCustomer;
    @FXML
    private TableColumn<Customer, Integer> colCustomerId;
    @FXML
    private TableColumn<Customer, String> colCustomerName;
    @FXML
    private TableColumn<Customer, String> colCustomerPhone;
    
    CustomerSqliteRepository customerRepo = new CustomerSqliteRepository();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // set tables data
        fillCustomerTable();
        
        // table selection listener
        
        tbCustomer.getFocusModel().focusedCellProperty().addListener(new ChangeListener<TablePosition>() {
            @Override
            public void changed(ObservableValue<? extends TablePosition> observable, TablePosition oldValue, TablePosition newValue) {
                try {
                    Customer customer = tbCustomer.getSelectionModel().getSelectedItem();
                    
                    txtCustomerName.setText(customer.getName());
                    txtCustomerPhone.setText(customer.getPhone());
                    
                } catch(Exception e) {
                    e.printStackTrace();
                    AlertMaker.showErrorALert(e.toString());
                }
            }
            
        });
    }    

    @FXML
    private void saveCustomer(ActionEvent event) {
        try {
            // validation 
            
            if(txtCustomerName.getText().isEmpty()) {
                AlertMaker.showErrorALert("Enter Customer Name");
                return;
            }
            
            if(txtCustomerPhone.getText().isEmpty()) {
                AlertMaker.showErrorALert("Enter Customer Phone");
                return;
            }
            
            String name = txtCustomerName.getText();
            String phone = txtCustomerPhone.getText();
            
            Customer customer = Customer.builder()
                    .name(name)
                    .phone(phone)
                    .build();
            
            if(customerRepo.save(customer)) {
                fillCustomerTable();
                clearCustomerTf();
                txtCustomerName.requestFocus();
                lbCustomerStatus.setText(customer.getName() + " Saved");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
    }

    @FXML
    private void updateCustomer(ActionEvent event) {
        try {
            // validation 
            
            if(txtCustomerName.getText().isEmpty()) {
                AlertMaker.showErrorALert("Enter Customer Name");
                return;
            }
            
            if(txtCustomerPhone.getText().isEmpty()) {
                AlertMaker.showErrorALert("Enter Customer Phone");
                return;
            }
            
            String name = txtCustomerName.getText();
            String phone = txtCustomerPhone.getText();
            
            Customer oldCustomer = tbCustomer.getSelectionModel().getSelectedItem();
            
            if(oldCustomer == null) {
                AlertMaker.showErrorALert("Choose Customer First");
                return;
            }
            
            Customer newCustomer = Customer.builder()
                    .id(oldCustomer.getId())
                    .name(name)
                    .phone(phone)
                    .build();
            
            Optional<ButtonType> response = AlertMaker.showConfirmationAlert("Update " + oldCustomer.getName() + " ?");
            
            if(response.get() == ButtonType.OK) {
                if(customerRepo.update(newCustomer)) {
                    fillCustomerTable();
                    clearCustomerTf();
                    txtCustomerName.requestFocus();
                    lbCustomerStatus.setText(oldCustomer.getName() + " Updated");
                }
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
    }

    @FXML
    private void deleteCustomer(ActionEvent event) {
        try {
            Customer customer = tbCustomer.getSelectionModel().getSelectedItem();
            
            if(customer == null) {
                AlertMaker.showErrorALert("Choose Customer First");
                return;
            }
            
            Optional<ButtonType> response = AlertMaker.showConfirmationAlert("Delete " + customer.getName() + " ?");
            
            if(response.get() == ButtonType.OK) {
                if(customerRepo.delete(customer.getId())) {
                    fillCustomerTable();
                    clearCustomerTf();
                    txtCustomerName.requestFocus();
                    lbCustomerStatus.setText(customer.getName() + " Deleted");
                }
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
    }

    private void fillCustomerTable() {
        try {
            ArrayList<Customer> list = customerRepo.findAll();
            ObservableList<Customer> data = FXCollections.observableArrayList(list);
            
            colCustomerId.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("id"));
            colCustomerName.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
            colCustomerPhone.setCellValueFactory(new PropertyValueFactory<Customer, String>("phone"));
            
            tbCustomer.setItems(data);
            
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
    }

    private void clearCustomerTf() {
        try {
            txtCustomerName.clear();
            txtCustomerPhone.clear();
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
    }
    
    
    @FXML
    private void filterCustomerTable() {
        try {
            String customerName = txtCustomerNameSearch.getText();
            ArrayList<Customer> list = customerRepo.findBySearchWords(customerName);
            ObservableList<Customer> data = FXCollections.observableArrayList(list);
            
            tbCustomer.setItems(data);
            
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
    }
}
