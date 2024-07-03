
package com.accountmanagement.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


public class ProductsController implements Initializable {

    @FXML
    private TextField txtSerial;
    @FXML
    private TextField txtBuyerName;
    @FXML
    private TextField txtPhone;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPassword;
    @FXML
    private DatePicker txtSubsDate;
    @FXML
    private TextField txtSubsValue;
    @FXML
    private TextField txtSerialSearch;
    @FXML
    private TextField txtBNSearch;
    @FXML
    private TableView<?> tbProducts;
    @FXML
    private TextField txtSerialSearchPL;
    @FXML
    private TextField txtBNSearchPL;
    @FXML
    private TableView<?> tbProductsPL;
    @FXML
    private Label lbStatus;
    @FXML
    private TableColumn<?, ?> colId;
    @FXML
    private TableColumn<?, ?> colSerial;
    @FXML
    private TableColumn<?, ?> colByerName;
    @FXML
    private TableColumn<?, ?> volPhone;
    @FXML
    private TableColumn<?, ?> colEmail;
    @FXML
    private TableColumn<?, ?> colPassword;
    @FXML
    private TableColumn<?, ?> colSubsDate;
    @FXML
    private TableColumn<?, ?> colSubsValue;
    @FXML
    private TableColumn<?, ?> coldPL;
    @FXML
    private TableColumn<?, ?> colSerialPL;
    @FXML
    private TableColumn<?, ?> colBuyerNamePL;
    @FXML
    private TableColumn<?, ?> colPhonePL;
    @FXML
    private TableColumn<?, ?> colEmailPL;
    @FXML
    private TableColumn<?, ?> colPasswordPL;
    @FXML
    private TableColumn<?, ?> colSubsDatePL;
    @FXML
    private TableColumn<?, ?> colSubsValuePL;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void save(ActionEvent event) {
    }

    @FXML
    private void update(ActionEvent event) {
    }

    @FXML
    private void delete(ActionEvent event) {
    }
    
}
