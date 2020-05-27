/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author phuon
 */
public class EditString {
    public static String DateToString(Date date){
        String dateString="";
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        dateString = df.format(date);
        return dateString;
    }
}
