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

/**
 *
 * @author Admin
 */
public class ThemSuaNhaCungCap extends JFrame {

    ArrayList<QuyenDTO> listQ;
    String type;
    NhaCungCapBUS qlNhaCungCap = new NhaCungCapBUS();
    NhaCungCapDTO NCCsua;
    JTextField txmaNCC = new JTextField(15);
    JTextField txtenNCC = new JTextField(15);
    JTextField txDiaChi = new JTextField(10);
    JTextField txEmail = new JTextField(15);
    JComboBox<String> cbChonQuyen = new JComboBox<>();
    JButton btnThem = new JButton("Thêm");
    JButton btnSua = new JButton("Sửa");
    JButton btnHuy = new JButton("Hủy");

//
    public ThemSuaNhaCungCap(String type, String MaNCC) {
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
        JLabel Title = new JLabel(type + " Nhà Cung Cấp", (int) CENTER_ALIGNMENT);
        Title.setFont(new Font("SegoeUI", 1, 30));
        Title.setBackground(Color.DARK_GRAY);
        Title.setOpaque(true);
        Title.setForeground(Color.WHITE);
        Title.setBounds(0, 0, 400, 40);
        add(Title);
        DatePickerSettings pickerSettings = new DatePickerSettings();
        pickerSettings.setVisibleDateTextField(false);
        String[] lbName = new String[]{"Mã nhà cung cấp", "Tên nhà cung cấp", "địa chỉ", "email"};
        JComponent[] com = new JComponent[]{txmaNCC, txtenNCC, txDiaChi, txEmail};
        for (int i = 0; i <= 3; i++) {
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
            txmaNCC.setText(qlNhaCungCap.getNextID());
            txmaNCC.setEditable(false);
        }
        if (type.equals("Sửa")) {
            btnSua.setBounds(50, 400, 100, 30);
            add(btnSua);
            NCCsua = qlNhaCungCap.getNCC(MaNCC);
            if (NCCsua == null) {
                JOptionPane.showMessageDialog(rootPane, "Không tìm thấy nhà cung cấp");
            } else {
//            String[] lbName = new String[]{"Mã nhân viên", "Họ nhân viên", "Tên nhân viên", "Ngày sinh", "Số điện thoại", "Giới tính", "Trạng thái"};
                txmaNCC.setText(NCCsua.getMaNCC());
                txtenNCC.setText(NCCsua.getTenNCC());
                txDiaChi.setText(NCCsua.getDiaChi());
                txEmail.setText(NCCsua.getEmail());
            }
        }
        btnThem.addActionListener(e -> {
            themncc();
            //this.dispose();

        });
        btnSua.addActionListener(e -> {
            suaNCC();
            //this.dispose();

        });
        btnHuy.setBounds(250, 400, 100, 30);
        add(btnHuy);
        btnHuy.addActionListener((e) -> {
            this.dispose();
        });
        addDocumentListener(txtenNCC);
        addDocumentListener(txDiaChi);
        addDocumentListener(txEmail);
        btnSua.setEnabled(false);
        btnThem.setEnabled(false);
    }

    public void addDocumentListener(JTextField tx) {
        tx.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                check(tx);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                check(tx);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                check(tx);
            }
        });
    }

    public void check(JTextField tx) {
        if (tx.equals(txtenNCC)) {
            if (tx.getText().length() > 40) {
                tx.setForeground(Color.red);
                btnThem.setEnabled(false);
                btnSua.setEnabled(false);
            } else {
                tx.setForeground(Color.black);
                btnThem.setEnabled(true);
                btnSua.setEnabled(true);
            }
        }else if(tx.equals(txDiaChi)){
            if (tx.getText().length() > 50) {
                tx.setForeground(Color.red);
                btnThem.setEnabled(false);
                btnSua.setEnabled(false);
            } else {
                tx.setForeground(Color.black);
                btnThem.setEnabled(true);
                btnSua.setEnabled(true);
            }
        }else if(tx.equals(txEmail)){
            String regex;
            regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
            if (tx.getText().length() > 40||!tx.getText().matches(regex)) {
                tx.setForeground(Color.red);
                btnThem.setEnabled(false);
                btnSua.setEnabled(false);
            } else {
                tx.setForeground(Color.black);
                btnThem.setEnabled(true);
                btnSua.setEnabled(true);
            }
        }else
        if (txDiaChi.getText().trim().equals("") || txEmail.getText().trim().equals("") || txtenNCC.getText().trim().equals("")) {
            btnThem.setEnabled(false);
            btnSua.setEnabled(false);
        } else {
            btnThem.setEnabled(true);
            btnSua.setEnabled(true);
        }
    }

    private void themncc() {
        NhaCungCapDTO ncc = new NhaCungCapDTO();
        ncc.setMaNCC(txmaNCC.getText());
        ncc.setTenNCC(txtenNCC.getText());
        ncc.setDiaChi(txDiaChi.getText());
        ncc.setEmail(txEmail.getText());
        NhaCungCapDAO.insertNhaCungCap(ncc);
        this.dispose();
    }

    private void suaNCC() {
        // NhanVienDTO nv  = qlNCC.getNV()
        NCCsua.setMaNCC(txmaNCC.getText());
        NCCsua.setTenNCC(txtenNCC.getText());
        NCCsua.setDiaChi(txDiaChi.getText());
        NCCsua.setEmail(txEmail.getText());
        //convert String to LocalDate
        int reply = JOptionPane.showConfirmDialog(rootPane, "Bạn có chắc muốn sửa nhà cung cấp");
        if (reply == JOptionPane.YES_OPTION) {
            NhaCungCapDAO.updateNhaCungCap(NCCsua);
            JOptionPane.showMessageDialog(rootPane, "Sửa thành công");
        } else {
            JOptionPane.showMessageDialog(rootPane, "Sửa không thành công");
        }
        this.dispose();
    }

    public static void main(String[] args) {
        new ThemSuaNhaCungCap("Thêm", "").setVisible(true);
    }
}
