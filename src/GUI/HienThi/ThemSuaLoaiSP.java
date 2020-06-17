/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Quanli;

import javax.swing.JFrame;
import BUS.*;
import DTO.*;
import DAO.*;
import GUI.Button.DateButton;
import GUI.NavBar.NavBarSeperator;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import java.awt.BorderLayout;
import java.awt.Color;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javafx.util.converter.LocalDateStringConverter;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ThemSuaLoaiSP extends JFrame {

    ArrayList<QuyenDTO> listQ;
    String type;
    LoaiSPBUS qlLoaiSP = new LoaiSPBUS();
    LoaiSPDTO spsua;
    JTextField txmaLoaiSP = new JTextField(15);
    JTextField txtenLoaiSP = new JTextField(15);
    JComboBox<String> cbChonQuyen = new JComboBox<>();
    JButton btnThem = new JButton("Thêm");
    JButton btnSua = new JButton("Sửa");
    JButton btnHuy = new JButton("Hủy");

//
    public ThemSuaLoaiSP(String type, String maLoaiSP) {
        setUndecorated(true);
        this.setLayout(null);
        this.setSize(400, 450);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.type = type;
        NavBarSeperator s = new NavBarSeperator(new Rectangle(0, 0, 0, 2));
        s.setBounds(0, 380, 400, 2);
        s.setBackground(Color.DARK_GRAY);
        add(s);
        JLabel Title = new JLabel(type + "Loại SP", (int) CENTER_ALIGNMENT);
        Title.setFont(new Font("SegoeUI", 1, 30));
        Title.setBackground(Color.DARK_GRAY);
        Title.setOpaque(true);
        Title.setForeground(Color.WHITE);
        Title.setBounds(0, 0, 400, 40);
        add(Title);
        DatePickerSettings pickerSettings = new DatePickerSettings();
        pickerSettings.setVisibleDateTextField(false);
        String[] lbName = new String[]{"Mã Loại Sản Phẩm", "Tên Loại Sản Phẩm"};
        JComponent[] com = new JComponent[]{txmaLoaiSP, txtenLoaiSP};
        for (int i = 0; i <= 1; i++) {
            JLabel lb = new JLabel(lbName[i]);
            lb.setBounds(50, 40 * i + 50, 150, 30);
            add(lb);
            com[i].setBounds(180, 40 * i + 50, 150, 30);
            add(com[i]);
        }
        btnHuy.setBounds(250, 400, 100, 30);
        add(btnHuy);
        btnHuy.addActionListener((e) -> {
            this.dispose();
        });
        if (type.equals("Thêm")) {
            btnThem.setBounds(50, 400, 100, 30);
            add(btnThem);
            txmaLoaiSP.setText(qlLoaiSP.getNextID());
        }
        if (type.equals("Sửa")) {
            btnSua.setBounds(50, 400, 100, 30);
            add(btnSua);
            spsua = qlLoaiSP.getLoaiSP(maLoaiSP);
            if (spsua == null) {
                System.out.println("Không tìm thấy loại sản phẩm");
            } else {
//            String[] lbName = new String[]{"Mã nhân viên", "Họ nhân viên", "Tên nhân viên", "Ngày sinh", "Số điện thoại", "Giới tính", "Trạng thái"};
                txmaLoaiSP.setText(spsua.getMaLoaiSP());
                txtenLoaiSP.setText(spsua.getTenLoaiSP());
            }

        }
        btnThem.addActionListener(e -> {
            if (txtenLoaiSP.getForeground().equals(Color.black) && txmaLoaiSP.getForeground().equals(Color.black)) {
                themLoaiSP();
                this.dispose();
            } else {
                if (txtenLoaiSP.getForeground().equals(Color.red)) {
                    txtenLoaiSP.requestFocus();
                }
                if (txmaLoaiSP.getForeground().equals(Color.red)) {
                    txmaLoaiSP.requestFocus();
                }
            }

        });
        btnSua.addActionListener(e -> {

            if (txtenLoaiSP.getForeground().equals(Color.black) && txmaLoaiSP.getForeground().equals(Color.black)) {
                suaLoaiSP();
                this.dispose();
            } else {
                if (txtenLoaiSP.getForeground().equals(Color.red)) {
                    txtenLoaiSP.requestFocus();
                }
                if (txmaLoaiSP.getForeground().equals(Color.red)) {
                    txmaLoaiSP.requestFocus();
                }
            }
        });
        btnHuy.setBounds(250, 400, 100, 30);
        add(btnHuy);
        btnHuy.addActionListener((e) -> {
            this.dispose();
        });
        txtenLoaiSP.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (txtenLoaiSP.getText().length() > 40) {
                    txtenLoaiSP.setForeground(Color.red);
                } else {
                    txtenLoaiSP.setForeground(Color.black);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (txtenLoaiSP.getText().length() > 40) {
                    txtenLoaiSP.setForeground(Color.red);
                } else {
                    txtenLoaiSP.setForeground(Color.black);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (txtenLoaiSP.getText().length() > 40) {
                    txtenLoaiSP.setForeground(Color.red);
                } else {
                    txtenLoaiSP.setForeground(Color.black);
                }
            }
        });
        txmaLoaiSP.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (txmaLoaiSP.getText().contains(" ") || txmaLoaiSP.getText().length() > 10) {
                    txmaLoaiSP.setForeground(Color.red);
                } else {
                    txmaLoaiSP.setForeground(Color.black);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (txmaLoaiSP.getText().contains(" ") || txmaLoaiSP.getText().length() > 10) {
                    txmaLoaiSP.setForeground(Color.red);
                } else {
                    txmaLoaiSP.setForeground(Color.black);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (txmaLoaiSP.getText().contains(" ") || txmaLoaiSP.getText().length() > 10) {
                    txmaLoaiSP.setForeground(Color.red);
                } else {
                    txmaLoaiSP.setForeground(Color.black);
                }
            }
        });
    }

    private void themLoaiSP() {
        LoaiSPDTO lsp = new LoaiSPDTO();
        lsp.setTenLoaiSP(txtenLoaiSP.getText());
        lsp.setMaLoaiSP(txmaLoaiSP.getText());

        //convert String to LocalDate
        LoaiSPDAO.insertMaSP(lsp);
        this.dispose();
    }

    private void suaLoaiSP() {
        // NhanVienDTO nv  = qlNCC.getNV()
        spsua.setMaLoaiSP(txmaLoaiSP.getText());
        spsua.setTenLoaiSP(txtenLoaiSP.getText());
        //convert String to LocalDate
        int reply = JOptionPane.showConfirmDialog(rootPane, "Bạn có chắc muốn sửa loại sản phẩm");
        if (reply == JOptionPane.YES_OPTION) {
            LoaiSPDAO.updateMaSP(spsua);
            JOptionPane.showMessageDialog(rootPane, "Sửa thành công");
        } else {
            JOptionPane.showMessageDialog(rootPane, "Sửa không thành công");
        }
        this.dispose();
    }

    public static void main(String[] argv) {
        new ThemSuaLoaiSP("Sửa", "").setVisible(true);
    }
}
