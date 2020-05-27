/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

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
}
