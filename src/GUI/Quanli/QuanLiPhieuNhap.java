/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Quanli;

import GUI.Button.ExportExcelButton;
import GUI.HienThi.HienThiPhieuNhap;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.colorchooser.AbstractColorChooserPanel;

/**
 *
 * @author phuon
 */
public class QuanLiPhieuNhap extends  JPanel{
    HienThiPhieuNhap hoadon = new HienThiPhieuNhap();

    public QuanLiPhieuNhap() {
        setLayout(new BorderLayout());
        JPanel plWork = new JPanel(new BorderLayout());
        add(hoadon,BorderLayout.CENTER);
    }
    
}
