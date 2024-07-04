
package com.accountmanagement.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormater {
    
    public static SimpleDateFormat df = new SimpleDateFormat("dd-MMMM-yyyy");
    
    public static String format(Date date) {
        String formatedDate = df.format(date);
        return formatedDate;
    }
    
}
