
package com.accountmanagement.controllers;

import com.accountmanagement.models.AccountMovement;
import com.accountmanagement.models.Currency;
import com.accountmanagement.models.Customer;
import com.accountmanagement.models.IncomingDocument;
import com.accountmanagement.repositories.accountmovement.AccountMovementSqliteRepository;
import com.accountmanagement.repositories.currency.CurrencySqliteRepository;
import com.accountmanagement.repositories.customer.CustomerSqliteRepository;
import com.accountmanagement.repositories.incomingdocument.IncomingDocumentSqliteRepository;
import com.accountmanagement.utils.AlertMaker;
import com.accountmanagement.utils.NotificationMaker;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;


public class CustomersController implements Initializable {
    
    
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMMM-yyyy");
    DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd-MMMM-yyyy");
    CustomerSqliteRepository customerRepo = new CustomerSqliteRepository();
    CurrencySqliteRepository currencyRepo = new CurrencySqliteRepository();
    IncomingDocumentSqliteRepository inDocRepo = new IncomingDocumentSqliteRepository();
    AccountMovementSqliteRepository accountMovementRepo = new AccountMovementSqliteRepository();
    

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
    
    
    @FXML
    private TextField txtCurrencyName;
    @FXML
    private Label lbCurrencyStatus;
    @FXML
    private TextField txtCurrencyNameSearch;
    @FXML
    private TableView<Currency> tbCurrency;
    @FXML
    private DatePicker txtInDocDate;
    @FXML
    private ComboBox<String> cbInDocCustomerName;
    @FXML
    private ComboBox<String> cbInDocCurrencyName;
    @FXML
    private TextField txtInDocValue;
    @FXML
    private TextField txtInDocComment;
    @FXML
    private Label lbInDocStatus;
    @FXML
    private TextField txtInDocIdSearch;
    @FXML
    private TableView<IncomingDocument> tbInDoc;
    @FXML
    private DatePicker txtOutDocDate;
    @FXML
    private ComboBox<String> cbOutDocCustomerName;
    @FXML
    private ComboBox<String> cbOutDocCurrencyName;
    @FXML
    private TextField txtOutDocValue;
    @FXML
    private TextField txtOutDocComment;
    @FXML
    private Label lbOutDocStatus;
    @FXML
    private TextField txtOutDocIdSearch;
    @FXML
    private TableView<?> tbOutDoc;
    @FXML
    private ComboBox<String> cbReportCustomerName;
    @FXML
    private ComboBox<String> cbReportCurrencyName;
    @FXML
    private TableColumn<Currency, Integer> colCurrencyId;
    @FXML
    private TableColumn<Currency, String> colCurrencyName;
    @FXML
    private TableColumn<IncomingDocument, Integer> colInDocId;
    @FXML
    private TableColumn<IncomingDocument, String> colInDocDate;
    @FXML
    private TableColumn<IncomingDocument, String> colInDocCustomerName;
    @FXML
    private TableColumn<IncomingDocument, Double> colInDocValue;
    @FXML
    private TableColumn<IncomingDocument, String> colInDocComment;
    @FXML
    private TableColumn<IncomingDocument, String> colInDocCurrencyName;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // set dates
        setDates();
        
        // set tables data
        fillCustomerTable();
        fillCurrencyTable();
        fillInDocTable();
        
        // set cb's data
        setAllCbsCustomerName();
        setAllCbsCurrencyName();
        
        // table selection listener
        
        tbCustomer.getFocusModel().focusedCellProperty().addListener(new ChangeListener<TablePosition>() {
            @Override
            public void changed(ObservableValue<? extends TablePosition> observable, TablePosition oldValue, TablePosition newValue) {
                try {
                    Customer customer = tbCustomer.getSelectionModel().getSelectedItem();
                    
                    if(customer == null) return;
                    
                    txtCustomerName.setText(customer.getName());
                    txtCustomerPhone.setText(customer.getPhone());
                    
                } catch(Exception e) {
                    e.printStackTrace();
                    AlertMaker.showErrorALert(e.toString());
                }
            }
            
        });
        
        tbCurrency.getFocusModel().focusedCellProperty().addListener(new ChangeListener<TablePosition>() {
            @Override
            public void changed(ObservableValue<? extends TablePosition> observable, TablePosition oldValue, TablePosition newValue) {
                try{
                    Currency currency = tbCurrency.getSelectionModel().getSelectedItem();
                
                    if(currency == null) return;

                    txtCurrencyName.setText(currency.getName());
                } catch(Exception e) {
                    e.printStackTrace();
                    AlertMaker.showErrorALert(e.toString());
                }
            }
            
        });
        
        tbInDoc.getFocusModel().focusedCellProperty().addListener(new ChangeListener<TablePosition>(){
            @Override
            public void changed(ObservableValue<? extends TablePosition> observable, TablePosition oldValue, TablePosition newValue) {
                try{
                    IncomingDocument doc = tbInDoc.getSelectionModel().getSelectedItem();
                    
                    if(doc == null) return;
                    
                    txtInDocDate.setValue(LocalDate.parse(doc.getDate(), dateTimeFormat));
                    cbInDocCurrencyName.setValue(doc.getCurrencyName());
                    cbInDocCustomerName.setValue(doc.getCustomerName());
                    txtInDocValue.setText(String.valueOf(doc.getValue()));
                    txtInDocComment.setText(doc.getComment());
                    
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
                setAllCbsCustomerName();        
                fillCustomerTable();
                clearCustomerTf();
                txtCustomerName.requestFocus();
                lbCustomerStatus.setText(customer.getName() + " Saved");
                NotificationMaker.showInformation(customer.getName() + " Saved");
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
                    setAllCbsCustomerName();
                    fillCustomerTable();
                    clearCustomerTf();
                    txtCustomerName.requestFocus();
                    lbCustomerStatus.setText(oldCustomer.getName() + " Updated");
                    NotificationMaker.showInformation(oldCustomer.getName() + " Updated");
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
                    setAllCbsCustomerName();
                    fillCustomerTable();
                    clearCustomerTf();
                    txtCustomerName.requestFocus();
                    lbCustomerStatus.setText(customer.getName() + " Deleted");
                    NotificationMaker.showInformation(customer.getName() + " Deleted");
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

    @FXML
    private void saveCurrency(ActionEvent event) {
        try {
            // validation 
            
            if(txtCurrencyName.getText().isEmpty()) {
                AlertMaker.showErrorALert("Enter Currency Name");
                return;
            }
            
            String currencyName = txtCurrencyName.getText();
            
            Currency currency = Currency.builder().name(currencyName).build();
            
            if(currencyRepo.save(currency)) {
                fillCurrencyTable();
                txtCurrencyName.requestFocus();
                txtCurrencyName.clear();
                setAllCbsCurrencyName();
                lbCurrencyStatus.setText(currency.getName() + " saved");
                NotificationMaker.showInformation(currency.getName() + " saved");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void updateCurrency(ActionEvent event) {
        try {
            
            Currency currency = tbCurrency.getSelectionModel().getSelectedItem();
            
            // validation 
            
            if(currency == null) {
                AlertMaker.showErrorALert("Choose Currency");
                return;
            }
            
            if(txtCurrencyName.getText().isEmpty()) {
                AlertMaker.showErrorALert("Enter Currency Name");
                return;
            }
            
            
            
            String currencyName = txtCurrencyName.getText();
            
            Currency newCurrency = Currency.builder().id(currency.getId()).name(currencyName).build();
            
            Optional<ButtonType> result = AlertMaker.showConfirmationAlert("update " + currency.getName() + " ?");
            
            if(!(result.get() == ButtonType.OK)) {
                return;
            }
            
            if(currencyRepo.update(newCurrency)) {
                fillCurrencyTable();
                txtCurrencyName.requestFocus();
                txtCurrencyName.clear();
                setAllCbsCurrencyName();
                lbCurrencyStatus.setText(currency.getName() + " updated");
                NotificationMaker.showInformation(currency.getName() + " updated");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void deleteCurrency(ActionEvent event) {
        try {
            Currency currency = tbCurrency.getSelectionModel().getSelectedItem();
            
            // validation
            
            if(currency == null) {
                AlertMaker.showErrorALert("Choose Currency");
            }
            
            Optional<ButtonType> result = AlertMaker.showConfirmationAlert("delete " + currency.getName() + " ?");
            
            if(!(result.get() == ButtonType.OK)) {
                return;
            }
            
            if(currencyRepo.delete(currency.getId())) {
                fillCurrencyTable();
                setAllCbsCurrencyName();
                lbCurrencyStatus.setText(currency.getName() + " deleted");
                NotificationMaker.showInformation(currency.getName() + " deleted");
            }
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
    }

    @FXML
    private void saveIncomingDocument(ActionEvent event) {
        try {
            // validation
            if(txtInDocDate.getValue() == null) {
                AlertMaker.showErrorALert("Choose Date");
                return;
            }
            
            if(cbInDocCustomerName.getSelectionModel().getSelectedItem().isEmpty()){
                AlertMaker.showErrorALert("Choose Customer");
                return;
            }
            
            if(cbInDocCurrencyName.getSelectionModel().getSelectedItem().isEmpty()){
                AlertMaker.showErrorALert("Choose Currency");
                return;
            }
            
            if(txtInDocValue.getText().isEmpty()) {
                AlertMaker.showErrorALert("Enter Document Value");
                return;
            }
            
            if(Double.valueOf(txtInDocValue.getText()) <= 0) {
                AlertMaker.showErrorALert("Document Value Must Be > 0");
                return;
            }
            
            LocalDate localDate = txtInDocDate.getValue();
            
            String date = dateFormat.format(Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
            String customerName = cbInDocCustomerName.getSelectionModel().getSelectedItem();
            String currencyName = cbInDocCurrencyName.getSelectionModel().getSelectedItem();
            double value = Double.valueOf(txtInDocValue.getText());
            String comment = txtInDocComment.getText();
            
            HashMap<String, Integer> customersMap = customerRepo.getCustomerMap();
            HashMap<String, Integer> currencysMap = currencyRepo.getCurrencyMap();
            
            int customerId = customersMap.get(customerName);
            int currencyId = currencysMap.get(currencyName);
            
            IncomingDocument incomingDocument = IncomingDocument.builder()
                    .date(date)
                    .customerId(customerId)
                    .currencyId(currencyId)
                    .value(value)
                    .comment(comment)
                    .build();
            
            
            
            int inDocId = inDocRepo.save(incomingDocument);
            
            AccountMovement accountMovement = AccountMovement.builder()
                        .date(date)
                        .customerId(customerId)
                        .currencyId(currencyId)
                        .incomingDocumentId(inDocId)
                        .outgoingDocumentId(0)
                        .incomingValue(value)
                        .outgoingValue(0)
                        .comment(comment)
                        .build();
            
            if(inDocId > 0) {
                fillInDocTable();
                clearInDocFields();
                lbInDocStatus.setText("Document Saved with Id : "+ inDocId);
                NotificationMaker.showInformation("Document Saved with Id : "+ inDocId);
                accountMovementRepo.save(accountMovement);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
    }

    @FXML
    private void updateIncomingDocument(ActionEvent event) {
        try {
            // validation
            if(txtInDocDate.getValue() == null) {
                AlertMaker.showErrorALert("Choose Date");
                return;
            }
            
            if(cbInDocCustomerName.getSelectionModel().getSelectedItem().isEmpty()){
                AlertMaker.showErrorALert("Choose Customer");
                return;
            }
            
            if(cbInDocCurrencyName.getSelectionModel().getSelectedItem().isEmpty()){
                AlertMaker.showErrorALert("Choose Currency");
                return;
            }
            
            if(txtInDocValue.getText().isEmpty()) {
                AlertMaker.showErrorALert("Enter Document Value");
                return;
            }
            
            if(Double.valueOf(txtInDocValue.getText()) <= 0) {
                AlertMaker.showErrorALert("Document Value Must Be > 0");
                return;
            }
            
            LocalDate localDate = txtInDocDate.getValue();
            
            String date = dateFormat.format(Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
            String customerName = cbInDocCustomerName.getSelectionModel().getSelectedItem();
            String currencyName = cbInDocCurrencyName.getSelectionModel().getSelectedItem();
            double value = Double.valueOf(txtInDocValue.getText());
            String comment = txtInDocComment.getText();
            
            HashMap<String, Integer> customersMap = customerRepo.getCustomerMap();
            HashMap<String, Integer> currencysMap = currencyRepo.getCurrencyMap();
            
            int customerId = customersMap.get(customerName);
            int currencyId = currencysMap.get(currencyName);
            
            IncomingDocument oldDoc = tbInDoc.getSelectionModel().getSelectedItem();
            
            if(oldDoc == null) {
                AlertMaker.showErrorALert("Choose Document First");
                return;
            }
            
            IncomingDocument newIncomingDocument = IncomingDocument.builder()
                    .id(oldDoc.getId())
                    .date(date)
                    .customerId(customerId)
                    .currencyId(currencyId)
                    .value(value)
                    .comment(comment)
                    .build();

            
            AccountMovement accountMovement = AccountMovement.builder()
                        .date(date)
                        .customerId(customerId)
                        .currencyId(currencyId)
                        .incomingDocumentId(oldDoc.getId())
                        .outgoingDocumentId(0)
                        .incomingValue(value)
                        .outgoingValue(0)
                        .comment(comment)
                        .build();
            
            Optional<ButtonType> response = AlertMaker.showConfirmationAlert("update document with id : " + oldDoc.getId());
            
            if(!(response.get() == ButtonType.OK)) return;
            
            if(inDocRepo.update(newIncomingDocument)) {
                fillInDocTable();
                clearInDocFields();
                lbInDocStatus.setText("Document with Id : "+ oldDoc.getId() + " updated");
                NotificationMaker.showInformation("Document with Id : "+ oldDoc.getId() + " updated");
                accountMovementRepo.update(accountMovement);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
    }

    @FXML
    private void deleteIncomingDocument(ActionEvent event) {
        try {
            IncomingDocument doc = tbInDoc.getSelectionModel().getSelectedItem();
            
            if (doc == null) {
                AlertMaker.showErrorALert("Choose Document First");
                return;
            }
            
            Optional<ButtonType> response = AlertMaker.showConfirmationAlert("delete Document with id: "+ doc.getId() + " ?");
            
            if(!(response.get() == ButtonType.OK)) return;
            
            if(inDocRepo.delete(doc.getId())) {
                accountMovementRepo.delete(doc.getId(), 0);
                fillInDocTable();
                lbInDocStatus.setText("Document with id : " + doc.getId() + " deleted");
                NotificationMaker.showInformation("Document with id : " + doc.getId() + " deleted");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
    }

    @FXML
    private void saveOutgoingDocument(ActionEvent event) {
    }

    @FXML
    private void updateOutgoingDocument(ActionEvent event) {
    }

    @FXML
    private void showCustomerReportPerCurrency(ActionEvent event) {
    }

    @FXML
    private void showCustomerTotalReport(ActionEvent event) {
    }

    private void setAllCbsCustomerName() {
        try {
            ArrayList<Customer> list = customerRepo.findAll();
            ObservableList<String> obList = FXCollections.observableArrayList();
                
            list.forEach(customer -> obList.add(customer.getName()));
            
            // remove old values
            
            cbInDocCustomerName.getItems().clear();
            cbOutDocCustomerName.getItems().clear();
            cbReportCustomerName.getItems().clear();
            
            cbInDocCustomerName.getItems().addAll(obList);
            cbOutDocCustomerName.getItems().addAll(obList);
            cbReportCustomerName.getItems().addAll(obList);
        } catch(Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
    }

    private void fillCurrencyTable() {
        try {
            ArrayList<Currency> list = currencyRepo.findAll();
            ObservableList<Currency> obList = FXCollections.observableArrayList(list);
            
            colCurrencyId.setCellValueFactory(new PropertyValueFactory<Currency, Integer>("id"));
            colCurrencyName.setCellValueFactory(new PropertyValueFactory<Currency, String>("name"));
            
            tbCurrency.setItems(obList);
            
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
    }

    private void setAllCbsCurrencyName() {
        try {
            ArrayList<Currency> list = currencyRepo.findAll();
            ObservableList<String> obList = FXCollections.observableArrayList();
            
            list.forEach(currency -> obList.add(currency.getName()));
            
            cbInDocCurrencyName.getItems().clear();
            cbOutDocCurrencyName.getItems().clear();
            cbReportCurrencyName.getItems().clear();
            
            cbInDocCurrencyName.getItems().addAll(obList);
            cbOutDocCurrencyName.getItems().addAll(obList);
            cbReportCurrencyName.getItems().addAll(obList);
            
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
    }
    
    void setDates() {
        try {
            txtInDocDate.setValue(LocalDate.now());
            txtOutDocDate.setValue(LocalDate.now());
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
        
    }

    private void fillInDocTable() {
        try {
            ArrayList<IncomingDocument> list = inDocRepo.findAllDesc();
            list.forEach(doc -> {
                doc.setCurrencyName(currencyRepo.findById(doc.getCurrencyId()).getName());
                doc.setCustomerName(customerRepo.findById(doc.getCustomerId()).getName());
            });
            ObservableList ob = FXCollections.observableArrayList(list);
            
            colInDocId.setCellValueFactory(new PropertyValueFactory<IncomingDocument, Integer>("id"));
            colInDocDate.setCellValueFactory(new PropertyValueFactory<IncomingDocument, String>("date"));
            colInDocCustomerName.setCellValueFactory(new PropertyValueFactory<IncomingDocument, String>("customerName"));
            colInDocCurrencyName.setCellValueFactory(new PropertyValueFactory<IncomingDocument, String>("currencyName"));
            colInDocValue.setCellValueFactory(new PropertyValueFactory<IncomingDocument, Double>("value"));
            colInDocComment.setCellValueFactory(new PropertyValueFactory<IncomingDocument, String>("comment"));
            
            tbInDoc.setItems(ob);
            
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
    }

    private void clearInDocFields() {
        try {
            txtInDocComment.clear();
            txtInDocDate.setValue(LocalDate.now());
            txtInDocValue.clear();
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
    }

    @FXML
    private void filterCurrencyTable(KeyEvent event) {
        try {
            
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
    }

    @FXML
    private void filterInDocTable(KeyEvent event) {
        try {
            
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
    }

    @FXML
    private void filterOutDocTable(KeyEvent event) {
        try {
            
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
    }
    
    
    
    
}
