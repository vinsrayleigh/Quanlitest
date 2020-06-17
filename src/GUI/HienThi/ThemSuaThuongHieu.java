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

public class ThemSuaThuongHieu extends JFrame {

    ArrayList<QuyenDTO> listQ;
    String type;
    ThuongHieuBUS qlThuongHieu = new ThuongHieuBUS();
    ThuongHieuDTO thsua;
    JTextField txmaThuongHieu = new JTextField(15);
    JTextField txtenThuongHieu = new JTextField(15);
    JTextArea txmoTa = new JTextArea();
    JComboBox<String> cbChonQuyen = new JComboBox<>();
    JButton btnThem = new JButton("Thêm");
    JButton btnSua = new JButton("Sửa");
    JButton btnHuy = new JButton("Hủy");

//
    public ThemSuaThuongHieu(String type, String maThuongHieu) {
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
        JLabel Title = new JLabel(type + " Thương Hiệu", (int) CENTER_ALIGNMENT);
        Title.setFont(new Font("SegoeUI", 1, 30));
        Title.setBackground(Color.DARK_GRAY);
        Title.setOpaque(true);
        Title.setForeground(Color.WHITE);
        Title.setBounds(0, 0, 400, 40);
        add(Title);
        DatePickerSettings pickerSettings = new DatePickerSettings();
        pickerSettings.setVisibleDateTextField(false);
        String[] lbName = new String[]{"Mã Thương Hiệu", "Tên Thương Hiệu", "Mô Tả"};
        JComponent[] com = new JComponent[]{txmaThuongHieu, txtenThuongHieu, txmoTa};
        for (int i = 0; i <= 2; i++) {
            JLabel lb = new JLabel(lbName[i]);
            lb.setBounds(50, 40 * i + 50, 150, 30);
            add(lb);
            com[i].setBounds(180, 40 * i + 50, 150, 30);
            add(com[i]);
        }
        txmoTa.setSize(new Dimension(150, 200));
        txmoTa.setBorder(BorderFactory.createEtchedBorder());
        btnHuy.setBounds(250, 400, 100, 30);
        add(btnHuy);
        btnHuy.addActionListener((e) -> {
            this.dispose();
        });
        if (type.equals("Thêm")) {
            btnThem.setBounds(50, 400, 100, 30);
            add(btnThem);
            txmaThuongHieu.setText(qlThuongHieu.getNextID());
            txmaThuongHieu.setEditable(false);
        }
        if (type.equals("Sửa")) {
            btnSua.setBounds(50, 400, 100, 30);
            add(btnSua);
            thsua = qlThuongHieu.getThuongHieu(maThuongHieu);
            if (thsua == null) {
                System.out.println("Không tìm thấy thương hiệu");
            } else {
//            String[] lbName = new String[]{"Mã nhân viên", "Họ nhân viên", "Tên nhân viên", "Ngày sinh", "Số điện thoại", "Giới tính", "Trạng thái"};
                txmaThuongHieu.setText(thsua.getMaThuongHieu());
                txtenThuongHieu.setText(thsua.getTenThuongHieu());
                txmoTa.setText(thsua.getMoTa());
            }

        }
        btnThem.addActionListener(e -> {
            themTH();
            this.dispose();

        });
        btnSua.addActionListener(e -> {
            suaTH();
            this.dispose();

        });
        btnHuy.setBounds(250, 400, 100, 30);
        add(btnHuy);
        btnHuy.addActionListener((e) -> {
            this.dispose();
        });
        btnThem.setEnabled(false);
        btnSua.setEnabled(false);
        txtenThuongHieu.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (txtenThuongHieu.getText().length() > 40||txtenThuongHieu.getText().trim().equals("")) {
                    txtenThuongHieu.setForeground(Color.red);
                    btnThem.setEnabled(false);
                    btnSua.setEnabled(false);
                } else {
                    txtenThuongHieu.setForeground(Color.black);
                    btnThem.setEnabled(true);
                    btnSua.setEnabled(true);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (txtenThuongHieu.getText().length() > 40||txtenThuongHieu.getText().trim().equals("")) {
                    txtenThuongHieu.setForeground(Color.red);
                    btnThem.setEnabled(false);
                    btnSua.setEnabled(false);
                } else {
                    txtenThuongHieu.setForeground(Color.black);
                    btnThem.setEnabled(true);
                    btnSua.setEnabled(true);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (txtenThuongHieu.getText().length() > 40||txtenThuongHieu.getText().trim().equals("")) {
                    txtenThuongHieu.setForeground(Color.red);
                    btnThem.setEnabled(false);
                    btnSua.setEnabled(false);
                } else {
                    txtenThuongHieu.setForeground(Color.black);
                    btnThem.setEnabled(true);
                    btnSua.setEnabled(true);
                }
            }
        });
    }

    private void themTH() {
        ThuongHieuDTO th = new ThuongHieuDTO();
        th.setMaThuongHieu(txmaThuongHieu.getText());
        th.setTenThuongHieu(txtenThuongHieu.getText());
        th.setMoTa(txmoTa.getText());

        //convert String to LocalDate
        ThuongHieuDAO.insertThuongHieu(th);
        this.dispose();
    }

    private void suaTH() {
        // NhanVienDTO nv  = qlNCC.getNV()
        thsua.setMaThuongHieu(txmaThuongHieu.getText());
        thsua.setTenThuongHieu(txtenThuongHieu.getText());
        thsua.setMoTa(txmoTa.getText());
        //convert String to LocalDate
        int reply = JOptionPane.showConfirmDialog(rootPane, "Bạn có chắc muốn sửa thương hiệu");
        if (reply == JOptionPane.YES_OPTION) {
            new ThuongHieuDAO().updateThuongHieu(thsua);
            JOptionPane.showMessageDialog(rootPane, "Sửa thành công");
        } else {
            JOptionPane.showMessageDialog(rootPane, "Sửa không thành công");
        }
        this.dispose();
    }

    public static void main(String[] argv) {
        new ThemSuaThuongHieu("Thêm", "").setVisible(true);
    }
}
