/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DTO.*;
import DAO.*;
import BUS.*;
import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

/**
 *
 * @author DELL
 */
public class DoiMatKhauForm extends JFrame {

    TaiKhoanDTO tk;

    JPasswordField txMatKhauCu = new JPasswordField(15);
    JPasswordField txMatKhauMoi = new JPasswordField(15);
    JPasswordField txXacNhanMatKhau = new JPasswordField(15);

    JButton btnDongY = new JButton("Đồng ý");
    JButton btnHuy = new JButton("Hủy");

    public DoiMatKhauForm(String matk) {
        setLayout(new BorderLayout());
        setSize(350, 350);
        setTitle("Đổi mật khẩu");
        setLocationRelativeTo(null);
        tk = new TaiKhoanBUS().getTK(matk);

        // input
        JPanel plInput = new JPanel();
        txMatKhauCu.setBorder(BorderFactory.createTitledBorder("Mật khẩu cũ: "));
        txMatKhauMoi.setBorder(BorderFactory.createTitledBorder("Mật khẩu mới: "));
        txXacNhanMatKhau.setBorder(BorderFactory.createTitledBorder("Xác nhận mật khẩu: "));

        plInput.add(txMatKhauCu);
        plInput.add(txMatKhauMoi);
        plInput.add(txXacNhanMatKhau);

        this.add(plInput, BorderLayout.CENTER);

        // button
        JPanel plButton = new JPanel();
        plButton.add(btnDongY);
        plButton.add(btnHuy);

        btnHuy.setIcon(new ImageIcon());
        btnDongY.setIcon(new ImageIcon());

        btnHuy.addActionListener((ae) -> {
            this.dispose();
        });
        btnDongY.addActionListener((ae) -> {
            if(checkPass()) {
                if(new TaiKhoanBUS().update(new TaiKhoanDTO(tk.getMaNhanVien(), txMatKhauMoi.getText()))) {
                    JOptionPane.showMessageDialog(this, "Đổi mật khẩu thành công!");
                    this.dispose();
                }
                
            }
        });

        this.add(plButton, BorderLayout.SOUTH);
    }

    private Boolean checkPass() {
        String mkcu = txMatKhauCu.getText();
        String mkmoi = txMatKhauMoi.getText();
        String xnmk = txXacNhanMatKhau.getText();

        if (!mkcu.equals(tk.getPassword())) {
            JOptionPane.showMessageDialog(txMatKhauCu, "Mật khẩu cũ không đúng!");
            txMatKhauCu.requestFocus();
            return false;

        } else if (mkmoi.equals("") || xnmk.equals("")) {
            JOptionPane.showMessageDialog(txMatKhauMoi, "Mật khẩu mới không được để trống!");
            txMatKhauMoi.requestFocus();
            return false;
            
        } else if (!mkmoi.equals(xnmk)) {
            JOptionPane.showMessageDialog(txXacNhanMatKhau, "Mật khẩu mới không khớp!");
            txXacNhanMatKhau.requestFocus();
            return false;
        }

        return true;
    }

}
