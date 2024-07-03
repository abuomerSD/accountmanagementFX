
package com.accountmanagement.utils;

import javafx.scene.control.Alert;

public class AlertMaker {
    
    public static void showErrorALert(String body) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("خطأ");
        alert.setContentText(body);
        alert.show();
    }
    
    public static void showMessageAlert(String body) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("تنبيه");
        alert.setContentText(body);
        alert.show();
    }
}
