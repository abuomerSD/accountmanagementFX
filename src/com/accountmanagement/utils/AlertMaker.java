
package com.accountmanagement.utils;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class AlertMaker {
    
    public static void showErrorALert(String body) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Error");
        alert.setContentText(body);
        alert.show();
    }
    
    public static void showMessageAlert(String body) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Info");
        alert.setContentText(body);
        alert.show();
    }
    
    public static Optional<ButtonType> showConfirmationAlert(String body) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("CONFIRMATION");
        alert.setContentText(body);
        Optional<ButtonType> result = alert.showAndWait();
        
        return result;
    }
}
