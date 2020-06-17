/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.HienThi;

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
 * @author phuon
 */
public class ThemSuaNhanVien extends JFrame {
//
    ArrayList<QuyenDTO> listQ;
    String type;
    NhanVienBUS qlNhanVien = new NhanVienBUS();
    NhanVienDTO nvsua;
    JTextField txManv = new JTextField(15);
    JTextField txHonv = new JTextField(15);
    JTextField txTennv = new JTextField(15);
    JTextField txNgaysinh = new JTextField(10);
    JTextField txSDT = new JTextField(15);
    JComboBox<String> cbChonTrangThai = new JComboBox<>(new String[]{"Ẩn", "Hiện"});
    JComboBox<String> cbChonGT = new JComboBox<>(new String[]{"Nam", "Nữ", "Khác"});
    JComboBox<String> cbChonQuyen = new JComboBox<>();
    JButton btnThem = new JButton("Thêm");
    JButton btnSua = new JButton("Sửa");
    JButton btnHuy = new JButton("Hủy");
    DatePicker dPickerNgaySinh;
//

    public ThemSuaNhanVien(String type, String manv) {
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
        JLabel Title = new JLabel(type + " Nhân Viên", (int) CENTER_ALIGNMENT);
        Title.setFont(new Font("SegoeUI", 1, 30));
        Title.setBackground(Color.DARK_GRAY);
        Title.setOpaque(true);
        Title.setForeground(Color.WHITE);
        Title.setBounds(0, 0, 400, 40);
        add(Title);
        DatePickerSettings pickerSettings = new DatePickerSettings();
        pickerSettings.setVisibleDateTextField(false);
        dPickerNgaySinh = new DatePicker(pickerSettings);
        dPickerNgaySinh.setDateToToday();
        DateButton db = new DateButton(dPickerNgaySinh);
        String[] lbName = new String[]{"Mã nhân viên", "Họ nhân viên", "Tên nhân viên", "Ngày sinh", "Số điện thoại","Quyền", "Giới tính", "Trạng thái"};
        listQ = QuyenDAO.getQuyen();
        for(QuyenDTO q: listQ){
            cbChonQuyen.addItem(q.getTenQuyen());
        }
        JComponent[] com = new JComponent[]{txManv, txHonv, txTennv, txNgaysinh, txSDT,cbChonQuyen, cbChonGT, cbChonTrangThai};
        for (int i = 0; i <= 7; i++) {
            JLabel lb = new JLabel(lbName[i]);
            lb.setBounds(50, 40 * i + 50, 150, 30);
            add(lb);
            if (com[i].equals(txNgaysinh)) {
                com[i].setBounds(180, 40 * i + 50, 150, 30);
                dPickerNgaySinh.setBounds(330, 40 * i + 50, 35, 30);
                add(dPickerNgaySinh);
            } else {
                com[i].setBounds(180, 40 * i + 50, 150, 30);
            }
            add(com[i]);
        }
        if (type.equals("Thêm")) {
            btnThem.setBounds(50, 400, 100, 30);
            add(btnThem);
            txManv.setText(qlNhanVien.getNextID());
            txNgaysinh.setText(dPickerNgaySinh.getDateStringOrEmptyString());
            txManv.setEditable(false);
        }
        if (type.equals("Sửa")) {
            btnSua.setBounds(50, 400, 100, 30);
            add(btnSua);
            nvsua = qlNhanVien.getNV(manv);
            if(nvsua==null){
            }else{
//            String[] lbName = new String[]{"Mã nhân viên", "Họ nhân viên", "Tên nhân viên", "Ngày sinh", "Số điện thoại", "Giới tính", "Trạng thái"};
            txManv.setText(nvsua.getMaNhanVien());
            txManv.setEditable(false);
            txHonv.setText(nvsua.getHoNhanVien());
            txTennv.setText(nvsua.getTenNhanVien());
            txNgaysinh.setText(nvsua.getNgaySinh().toString());
            txSDT.setText(nvsua.getSdt());
            if(nvsua.getGioiTinh().equals("Nam"))
            {
                cbChonGT.setSelectedItem("Nam");
            }else if(nvsua.getGioiTinh().equals("Nữ")){
                cbChonGT.setSelectedItem("Nư");
                    }
            else{
                cbChonGT.setSelectedItem("Khác");
            }
            cbChonTrangThai.setSelectedIndex(nvsua.getTrangThai());
            
            cbChonQuyen.setSelectedItem(new QuyenBUS().getQuyen(nvsua.getMaQuyen()).getTenQuyen());
            }
        }
        btnThem.addActionListener(e -> {
            themNV();
            this.dispose();
            
        });
        btnSua.addActionListener(e->{
            suaNV();
            this.dispose();
            
        });
        btnHuy.setBounds(250, 400, 100, 30);
        add(btnHuy);
        btnHuy.addActionListener((e) -> {
            this.dispose();
        });
        dPickerNgaySinh.addDateChangeListener((dce) -> {
            txNgaysinh.setText(dPickerNgaySinh.getDateStringOrEmptyString());
        });
        txNgaysinh.setEditable(false);
        Tool.AddDocumentListener("Tên", txTennv);
        Tool.AddDocumentListener("Date", txNgaysinh);
        addDocument(txHonv);
        addDocument(txTennv);
        addDocument(txSDT);
        
    }
    public void addDocument(JTextField tx){
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
    public void check(JTextField tx){
        if (tx.equals(txHonv)) {
            if (tx.getText().trim().length() > 40) {
                tx.setForeground(Color.red);
                btnThem.setEnabled(false);
                btnSua.setEnabled(false);
            } else {
                tx.setForeground(Color.black);
                btnThem.setEnabled(true);
                btnSua.setEnabled(true);
            }
        }else
        if (tx.equals(txTennv)) {
            if (tx.getText().trim().length() > 30) {
                tx.setForeground(Color.red);
                btnThem.setEnabled(false);
                btnSua.setEnabled(false);
            } else {
                tx.setForeground(Color.black);
                btnThem.setEnabled(true);
                btnSua.setEnabled(true);
            }
        }else
        if (tx.equals(txSDT)) {
            String vnf_regex = "^\\+?\\d{1,3}?[- .]?\\(?(?:\\d{2,3})\\)?[- .]?\\d\\d\\d[- .]?\\d\\d\\d\\d$";
            if (tx.getText().matches(vnf_regex)) {
                tx.setForeground(Color.black);
                btnThem.setEnabled(true);
                btnSua.setEnabled(true);
            } else {
                tx.setForeground(Color.red);
                btnThem.setEnabled(false);
                btnSua.setEnabled(false);
            }
        }else
        if (txTennv.getText().trim().equals("") || txHonv.getText().trim().equals("") || txSDT.getText().trim().equals("")) {
            btnThem.setEnabled(false);
            btnSua.setEnabled(false);
        } else {
            btnThem.setEnabled(true);
            btnSua.setEnabled(true);
        }
    }
    public static void main(String[] args) {
        new ThemSuaNhanVien("Thêm", "").setVisible(true);
    }

    private void themNV() {
        NhanVienDTO nv = new NhanVienDTO(txManv.getText(), txHonv.getText(), txTennv.getText(), new Date(0), txSDT.getText(), cbChonGT.getSelectedItem().toString(), "zero", 0, 1);
        nv.setMaNhanVien(txManv.getText());
        nv.setHoNhanVien(txHonv.getText());
        nv.setTenNhanVien(txTennv.getText());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
  //convert String to LocalDate
        LocalDate localDate = LocalDate.parse(txNgaysinh.getText(), formatter);
        nv.setNgaySinh(java.sql.Date.valueOf(localDate));
        nv.setSdt(txSDT.getText());
        nv.setMaQuyen(listQ.get(cbChonQuyen.getSelectedIndex()).getMaQuyen());
        nv.setGioiTinh(cbChonGT.getSelectedItem().toString());
        nv.setTrangThai(cbChonTrangThai.getSelectedIndex());
        new NhanVienDAO().insertNhanVien(nv);
        new TaiKhoanDAO().insertTaiKhoan(new TaiKhoanDTO(nv.getMaNhanVien(), "123456"));
        //this.dispose();
    }

    private void suaNV() {
       // NhanVienDTO nv  = qlNhanVien.getNV()
        nvsua.setMaNhanVien(txManv.getText());
        nvsua.setHoNhanVien(txHonv.getText());
        nvsua.setTenNhanVien(txTennv.getText());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
  //convert String to LocalDate
        nvsua.setMaQuyen(listQ.get(cbChonQuyen.getSelectedIndex()).getMaQuyen());
        LocalDate localDate = LocalDate.parse(txNgaysinh.getText(), formatter);
        nvsua.setNgaySinh(java.sql.Date.valueOf(localDate));
        nvsua.setSdt(txSDT.getText());
        nvsua.setGioiTinh(cbChonGT.getSelectedItem().toString());
        nvsua.setTrangThai(cbChonTrangThai.getSelectedIndex());
        int reply = JOptionPane.showConfirmDialog(rootPane,"Bạn có chắc muốn sửa nhân viên");
        if(reply==JOptionPane.YES_OPTION){
            new NhanVienDAO().updateNhanVien(nvsua);
            JOptionPane.showMessageDialog(rootPane, "Sửa thành công");
        }else{
            JOptionPane.showMessageDialog(rootPane, "Sửa không thành công");
        }
    }
}
