/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Button;


import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author DELL
 */
public class XoaButton extends JButton {
    public XoaButton() {
        this.setText("Xóa");
        this.setIcon(new ImageIcon("src/Image/delete_50px.png"));
    }
}