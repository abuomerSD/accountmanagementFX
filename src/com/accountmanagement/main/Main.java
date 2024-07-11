package com.accountmanagement.main;

import com.accountmanagement.models.AccountMovement;
import com.accountmanagement.repositories.accountmovement.AccountMovementSqliteRepository;
import com.accountmanagement.utils.AlertMaker;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class Main  extends Application{
    public static void main(String [] args) throws SQLException{
        
        // Fix Arabic letters in JavaFX
//        System.setProperty("prism.lcdtext", "false");
//        System.setProperty("prism.text", "t2k");

        
        
        launch(args);
        

    }
    
    

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            
            
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/accountmanagement/ui/Login.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Login");         
            
            primaryStage.show();
                     
                        
        } catch (Exception e) {
            AlertMaker.showErrorALert(e.toString());
            e.printStackTrace();
        }
    }
   
}
