
package com.accountmanagement.controllers;

import com.accountmanagement.utils.AlertMaker;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;


public class HomeController implements Initializable {

    @FXML
    private BorderPane borderPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void openProductsUI(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/accountmanagement/ui/Products.fxml"));
            borderPane.setCenter(root);
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.getMessage());
        }
    }

    @FXML
    private void openInvoicesUI(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/accountmanagement/ui/SalesInvoices.fxml"));
            borderPane.setCenter(root);
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.getMessage());
        }
    }

    @FXML
    private void openCustomersUI(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/accountmanagement/ui/Customers.fxml"));
            borderPane.setCenter(root);
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.getMessage());
        }
    }
    
    
    
}
