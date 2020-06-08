/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

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
    public static void setPicture(JLabel label, String filename) {
        try {
            BufferedImage image = ImageIO.read(new File("src/Image/"+filename));
            int x = label.getSize().width;
            int y = label.getSize().height;
            int ix = image.getWidth();
            int iy = image.getHeight();
            int dx = 0;
            int dy = 0; 
            if (x / y > ix / iy) {
                dy = y;
                dx = dy * ix / iy;
            } else {
                dx = x;
                dy = dx * iy / ix;
            }
            ImageIcon icon = new ImageIcon(image.getScaledInstance(dx, dy, BufferedImage.SCALE_SMOOTH));
            label.setIcon(icon);
        } catch (IOException ex) {
        }

    }
    private static boolean checkInput(String type,String input){
        if(type.equals("Num")){
            try{
                Integer.parseInt(input);
                return true;
            }catch(NumberFormatException e){
                return false;
            }
        }
        if(type.equals("email")){
            String regex = "/^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$/";
            return input.matches(regex);
        }
        if(type.equals("Date")){
            try {
                Tool.getDate(input);
                return true;
            } catch (DateTimeParseException e) {
                return false;
            }
        }
        if(type.equals("Tên")){
            if(input.contains(" ")){
                return false;
            }else{
                return true;
            }
        }
        
        return false;
    }
    public static void AddDocumentListener(String type,JTextField tx){
        tx.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if(checkInput(type, tx.getText())){
                    tx.setForeground(Color.BLACK);
                }else{
                    tx.setForeground(Color.red);
                }
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if(checkInput(type, tx.getText())){
                    tx.setForeground(Color.BLACK);
                }else{
                    tx.setForeground(Color.red);
                }
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if(checkInput(type, tx.getText())){
                    tx.setForeground(Color.BLACK);
                }else{
                    tx.setForeground(Color.red);
                }
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }
    
}

