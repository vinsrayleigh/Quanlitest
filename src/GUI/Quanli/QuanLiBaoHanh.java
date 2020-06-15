/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Quanli;

import DTO.KhachHangDTO;
import GUI.HienThi.HienThiBaoHanh;
import com.github.lgooddatepicker.components.DatePicker;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author phuon
 */
public class QuanLiBaoHanh extends JPanel{
    HienThiBaoHanh hienthi = new HienThiBaoHanh();

     public QuanLiBaoHanh() {
        setLayout(new BorderLayout());
        JPanel plWork = new JPanel(new BorderLayout());
        add(hienthi,BorderLayout.CENTER);
    }
}
