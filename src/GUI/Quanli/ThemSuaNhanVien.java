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
                System.out.println("SAI CMNR");
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

//
//        // inputs
//        txManv.setBorder(BorderFactory.createTitledBorder("Mã nhân viên"));
//        txHonv.setBorder(BorderFactory.createTitledBorder("Họ nhân viên"));
//        txTennv.setBorder(BorderFactory.createTitledBorder("Tên nhân viên"));
//        txSDT.setBorder(BorderFactory.createTitledBorder("Số điện thoại"));
//        cbChonTrangThai = new JComboBox<>(new String[]{"Ẩn", "Hiện"});
//        cbChonGT = new JComboBox<>(new String[]{"Nam", "Nữ", "Khác"});
//        JPanel plChonTT = new JPanel();
//        plChonTT.setBorder(BorderFactory.createTitledBorder("Trạng thái"));
//        JLabel lbChonTT = new JLabel("Trạng thái: ");
//        plChonTT.add(lbChonTT);
//        plChonTT.add(cbChonTrangThai);
//        JPanel plChonGT = new JPanel();
//        plChonGT.setBorder(BorderFactory.createTitledBorder("Giới Tính"));
//        JLabel lbChonGT = new JLabel("Giới tính: ");
//        plChonGT.add(lbChonGT);
//        plChonGT.add(cbChonGT);
//
//        JPanel plInput = new JPanel();
//        plInput.add(txManv);
//        plInput.add(txHonv);
//        plInput.add(txTennv);
//        plInput.add(plNgaysinh);
//        plInput.add(txSDT);
//        plInput.add(plChonTT);
//        plInput.add(plChonGT);
//        JPanel plButton = new JPanel();
//
//        // 2 case Thêm - Sửa
//        if (this.type.equals("Thêm")) {
//            this.setTitle("Thêm nhân viên");
//            txManv.setText(qlNhanVien.getNextID());
//            cbChonTrangThai.setSelectedItem("Hiện");
//
//            btnThem.setIcon(new ImageIcon("src/Image/add_new_50px.png"));
//            plButton.add(btnThem);
//
//        } else {
//            this.setTitle("Sửa nhân viên");
//            for (NhanVienDTO nv : qlNhanVien.list) {
//                if (nv.getMaNhanVien().equals(manv)) {
//                    this.nvsua = nv;
//                }
//            }
//            if (this.nvsua == null) {
//                JOptionPane.showMessageDialog(null, "Lỗi, không tìm thấy nhân viên");
//                this.dispose();
//            }
//
//            cbChonTrangThai.setSelectedItem(this.nvsua.getTrangThai() == 0 ? "Hiện" : "Ẩn");
//            txManv.setText(this.nvsua.getMaNhanVien());
//            txHonv.setText(this.nvsua.getHoNhanVien());
//            txTennv.setText(this.nvsua.getTenNhanVien());
//            txNgaysinh.setText(this.nvsua.getNgaySinh().toString());
//            dPickerNgaySinh.setDate(this.nvsua.getNgaySinh().toLocalDate());
//            txSDT.setText(this.nvsua.getSdt());
//            txManv.setEditable(false);
//            txManv.addFocusListener(new FocusListener() {
//                @Override
//                public void focusGained(FocusEvent e) {
//                    txHonv.requestFocus();
//                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                }
//
//                @Override
//                public void focusLost(FocusEvent e) {
//                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                }
//            });
//            btnSua.setIcon(new ImageIcon("src/Image/maintenance_50px.png"));
//            plButton.add(btnSua);
//        }
//        btnHuy.setIcon(new ImageIcon("src/Image/delete_50px.png"));
//        plButton.add(btnHuy);
//        this.add(plInput, BorderLayout.CENTER);
//        this.add(plButton, BorderLayout.SOUTH);
//        btnThem.addActionListener((ae) -> {
//            btnThemMouseClicked();
//        });
//        btnSua.addActionListener((ae) -> {
//            btnSuaMouseClicked();
//        });
//        btnHuy.addActionListener((ae) -> {
//            this.dispose();
//        });
//        dPickerNgaySinh.addDateChangeListener((dce) -> {
//            txNgaysinh.setText(dPickerNgaySinh.getDateStringOrEmptyString());
//        });
//
//        this.setVisible(true);
    }
//
//    private Boolean checkEmpty() {
//        String manv = txManv.getText();
//        String honv = txHonv.getText();
//        String tennv = txTennv.getText();
//        String ngaysinh = txNgaysinh.getText();
//        String sdt = txSDT.getText();
//
//        if (manv.trim().equals("")) {
//            return showErrorTx(txManv, "Mã nhân viên không được để trống");
//
//        } else if (tennv.trim().equals("")) {
//            return showErrorTx(txTennv, "Tên nhân viên không được để trống");
//
//        } else if (ngaysinh.trim().equals("")) {
//            return showErrorTx(txTennv, "Ngày sinh không được để trống");
//
//        } else if (honv.trim().equals("")) {
//            return showErrorTx(txTennv, "Địa chỉ không được để trống");
//
//        } else if (sdt.trim().equals("")) {
//            return showErrorTx(txTennv, "Số điện thoại không được để trống");
//
//        } else {
//            try {
//                LocalDate.parse(ngaysinh);
//            } catch (Exception e) {
//                return showErrorTx(txTennv, "Ngày sinh không hợp lệ");
//            }
//        }
//
//        return true;
//    }
//
//    private Boolean showErrorTx(JTextField tx, String errorInfo) {
//        JOptionPane.showMessageDialog(tx, errorInfo);
//        tx.requestFocus();
//        return false;
//    }
//
//    public static void main(String[] args) {
//        new ThemSuaNhanVien("Thêm", "ADMIN").setVisible(true);
//    }
//
//    private void btnThemMouseClicked() {
//        if (checkEmpty()) {
//            String manv = txManv.getText();
//            String honv = txHonv.getText();
//            String tennv = txTennv.getText();
//            String ngaysinh = txNgaysinh.getText();
//            String sdt = txSDT.getText();
//            int trangthai = (cbChonTrangThai.getSelectedItem().toString().equals("Hiện") ? 1 : 0);
//            String GT = (String) cbChonGT.getSelectedItem();
//            NhanVienDTO nv = new NhanVienDTO(manv, tennv, honv,Date.valueOf(dPickerNgaySinh.getDate()),GT,sdt, "zero", 0,trangthai);
//            qlNhanVien.add(nv);
//            //NhanVienDTO nv  = new NhanVienDTO("admin", "Phuong Tay", "Le", new Date(0), "Nam","0393203261", "admin", 0,1);
//        }
//    }
//
//    private void btnSuaMouseClicked() {
//            if (checkEmpty()) {
//            //String manv = txManv.getText();
//            String honv = txHonv.getText();
//            String tennv = txTennv.getText();
//            String ngaysinh = txNgaysinh.getText();
//            String sdt = txSDT.getText();
//            int trangthai = (cbChonTrangThai.getSelectedItem().toString().equals("Hiện") ? 1 : 0);
//            String GT = (String) cbChonGT.getSelectedItem();
//            NhanVienDTO nv = new NhanVienDTO(nvsua.getMaNhanVien(), tennv, honv,Date.valueOf(dPickerNgaySinh.getDate()),GT,sdt, "zero", 0,trangthai);
//            qlNhanVien.add(nv);
//            //NhanVienDTO nv  = new NhanVienDTO("admin", "Phuong Tay", "Le", new Date(0), "Nam","0393203261", "admin", 0,1);
//        }
//
//        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

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
        System.out.println(nvsua.getMaQuyen()+"   "+nvsua.getQuyen());
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
