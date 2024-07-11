
package com.accountmanagement.utils;

import javafx.geometry.Pos;
import org.controlsfx.control.Notifications;


public class NotificationMaker {
        
        public static void showInformation(String text) {
            try {
                Notifications.create()
                        .title("Info")
                        .text(text)
                        .darkStyle()
                        .position(Pos.TOP_RIGHT)
                        .showInformation();
       
            } catch (Exception e) {
                e.printStackTrace();
            }
        }     
    
}
