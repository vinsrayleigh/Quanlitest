/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.HienThi;

import GUI.*;
import java.awt.BorderLayout;
import javax.swing.JPanel;

/**
 *
 * @author DELL
 */
public class FormHienThi extends JPanel {
    
    MyTable mtb = new MyTable();
    public FormHienThi() {
        setLayout(new BorderLayout());
        add(mtb,BorderLayout.CENTER);
    }
    
    public String getSelectedRow(int col) {
        int i = mtb.getTable().getSelectedRow();
        if (i >= 0) {
            int realI = mtb.getTable().convertRowIndexToModel(i);
            return mtb.getModel().getValueAt(realI, col).toString();
        }
        return null;
    }
    public void setHeaders(String[] a){
        mtb.setHeaders(a);
    }
    public MyTable getTable() {
        return this.mtb;
    }
}
