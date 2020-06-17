/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.HienThi;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author phuon
 */
public class ChonKhuyenMai extends JFrame{
    HienThiKhuyenMai formHienThi = new HienThiKhuyenMai();
     JButton btnOK = new JButton("Chọn");
    JButton btnCancel = new JButton("Thoát");
    JTextField txTarget;

    public ChonKhuyenMai(JTextField _txTarget) {
        this.setTitle("Chọn khuyến mại");
        this.setLayout(new BorderLayout());
        this.setSize(1200 - 200, 600);
        this.setLocationRelativeTo(null);
        
        this.txTarget = _txTarget;
        setUndecorated(true);
        // ======= Buttons Panel ===========
        btnCancel.setIcon(new ImageIcon("src/Image/cancel_100px.png"));
        btnOK.setIcon(new ImageIcon("src/Image/add_new_50px.png"));
        
        JPanel plBtns = new JPanel();
        plBtns.add(btnOK);
        plBtns.add(btnCancel);
        
        this.add(formHienThi, BorderLayout.CENTER);
        this.add(plBtns, BorderLayout.SOUTH);
        this.setVisible(true);

        // actionlistener
        btnOK.addActionListener((ActionEvent ae) -> {
            String makh = formHienThi.getSelectedRow(1);
            if (makh != null) {
                this.txTarget.setText(makh);
                this.dispose();

            } else {
                JOptionPane.showMessageDialog(this, "Chưa chọn khuyến nào!");
            }
        });
        
        btnCancel.addActionListener((ae) -> {
            this.dispose();
        });
    }
}
