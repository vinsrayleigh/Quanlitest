/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Quanli;

import java.awt.BorderLayout;
import javax.swing.*;

/**
 *
 * @author phuon
 */
public class BanHangForm extends JPanel{
    ChonSanPham chonsp = new ChonSanPham();
    public BanHangForm(){
        setLayout(new BorderLayout());
        add(chonsp,BorderLayout.WEST);
    }
    
}
