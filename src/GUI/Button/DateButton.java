/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Button;

import com.github.lgooddatepicker.components.DatePicker;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author DELL
 */
public class DateButton extends JButton {
    
    public DateButton(DatePicker dp) {
        setSize(30,30);
        ImageIcon dPickerIcon = new ImageIcon("src/Image/calendar_50px.png");
        JButton datePickerButton = dp.getComponentToggleCalendarButton();
        datePickerButton.setText("");
        datePickerButton.setIcon(dPickerIcon);
    }
    
}
