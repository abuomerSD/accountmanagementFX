package com.accountmanagement.main;

import com.accountmanagement.database.DatabaseTablesCreator;
import com.accountmanagement.models.AccountMovement;
import com.accountmanagement.repositories.accountmovement.AccountMovementSqliteRepository;
import com.formdev.flatlaf.FlatLightLaf;
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
        System.setProperty("prism.lcdtext", "false");
        System.setProperty("prism.text", "t2k");
        
        launch(args);
   
    }
    
    private static boolean isActivated() {
        
        boolean activated = true;
        
        try {
            AccountMovementSqliteRepository repo = new AccountMovementSqliteRepository();
            ArrayList<AccountMovement> list = repo.findAll();
            
            if(activated == false){
                
                if(list.size() > 100) {                    
                    return activated;
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
        }
        return true;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            // create Database Tables 
            DatabaseTablesCreator.createDbTables();
            
            // flatlaf look and feel
            
            FlatLightLaf.setup();
            
            // check if the app activated
            
            if(!isActivated()){
                JOptionPane.showMessageDialog(null, "يرجى تحديث النظام");
                return;
            } 
            
            Parent root = FXMLLoader.load(getClass().getResource("/com/accountmanagement/ui/Home.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
                     
                        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
   
}
