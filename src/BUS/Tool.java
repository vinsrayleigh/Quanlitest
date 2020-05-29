/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author phuon
 */
public class Tool {
    public static boolean isNum(String input){
        try {
            Integer.parseInt(input);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    public static boolean isName(String input) {
        
        return true;
    }
    
    public static int getInt(String input){
        int a=0;
        try {
            a= Integer.valueOf(input);
        } catch (Exception e) {
        }
        return a;
    }
    public static double getDouble(String input){
        double a=0;
        try {
            a= Double.valueOf(input);
        } catch (Exception e) {
        }
        return a;
    }
    public static Date getDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //convert String to LocalDate
        LocalDate localDate = LocalDate.parse(date, formatter);
        return Date.valueOf(localDate);
    }
    public static void main(String[] args) {
        String ngay ="2000/04/23";
    //    System.out.println(Tool.getDate(ngay).toLocalDate().toString());
        
    }
}

