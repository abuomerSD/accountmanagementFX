
package com.accountmanagement.controllers;

import com.accountmanagement.database.DatabaseTablesCreator;
import com.accountmanagement.utils.AlertMaker;
import com.accountmanagement.utils.Constants;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController implements Initializable {

    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void login(ActionEvent event) {
        try {
            // validation 
            if(txtUsername.getText().isEmpty()) {
                AlertMaker.showErrorALert("Enter Your Username");
                return;
            }
            
            if(txtPassword.getText().isEmpty()) {
                AlertMaker.showErrorALert("Enter Your Password");
                return;
            }
            
            String username = txtUsername.getText();
            String password = txtPassword.getText();
            
            if(username.equals(Constants.ADMIN_USER_NAME) && password.equals(Constants.ADMIN_PASSWORD)){
                
                // create Database Tables 
                DatabaseTablesCreator.createDbTables();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/accountmanagement/ui/Home.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage primaryStage = new Stage();
                primaryStage.setScene(scene);
                primaryStage.setTitle("Account Management");

                HomeController controller = loader.getController();

                primaryStage.setOnHidden(e -> controller.shutDown());


                primaryStage.show();
                
                Stage stage = (Stage) txtPassword.getScene().getWindow();
                stage.close();
                
            } else {
                AlertMaker.showErrorALert("Username or Password is incorrect");
            }
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
    }

    @FXML
    private void cancel(ActionEvent event) {
        try {
            Stage stage = (Stage) txtPassword.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.showErrorALert(e.toString());
        }
    }
    
    
    
}
