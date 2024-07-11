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
            
            // check if the app activated
            
            if(!isActivated()){
                AlertMaker.showMessageAlert("Please Update the System");
                return;
            } 
            
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
