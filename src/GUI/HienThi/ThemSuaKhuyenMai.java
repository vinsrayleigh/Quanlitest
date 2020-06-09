/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Quanli;

import BUS.KhuyenMaiBUS;
import DAO.KhuyenMaiDAO;
import DTO.KhuyenMaiDTO;
import GUI.Button.DateButton;
import GUI.NavBar.NavBarSeperator;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author minkuppj
 */
public class ThemSuaKhuyenMai extends JFrame{
    
    String type;
    KhuyenMaiBUS qlKhuyenMai = new KhuyenMaiBUS();
    KhuyenMaiDTO kmsua;
    
    JTextField txMakm = new JTextField(15);
    JTextField txMaSP = new JTextField(15);
    JTextField txTenkm = new JTextField(15);
    JTextField txNgayBD = new JTextField(15);
    JTextField txNgayKT = new JTextField(15);
    JTextField txGiamGia = new JTextField(15);
    JTextField txChiTiet = new JTextField(15);
    
    JButton btnThem = new JButton("Thêm");
    JButton btnSua = new JButton("Sửa");
    JButton btnHuy = new JButton("Hủy");
    DatePicker dPickerGiamGia;
//

    public ThemSuaKhuyenMai(String type, String makm) {
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
        JLabel Title = new JLabel(type + " Khuyến Mãi", (int) CENTER_ALIGNMENT);
        Title.setFont(new Font("SegoeUI", 1, 30));
        Title.setBackground(Color.DARK_GRAY);
        Title.setOpaque(true);
        Title.setForeground(Color.WHITE);
        Title.setBounds(0, 0, 400, 40);
        add(Title);
        DatePickerSettings pickerSettings = new DatePickerSettings();
        pickerSettings.setVisibleDateTextField(false);
        dPickerGiamGia = new DatePicker(pickerSettings);
        dPickerGiamGia.setDateToToday();
        DateButton db = new DateButton(dPickerGiamGia);
        String[] lbName = new String[]{"Mã khuyến mãi", "Mã sản phẩm", "Tên khuyến mãi", "Giảm giá","Ngày Bắt đầu", "ngày kết thúc",  "Chi tiết"};
        
        JComponent[] com = new JComponent[]{txMakm, txMaSP, txTenkm, txGiamGia, txNgayBD, txNgayKT, txChiTiet};
        for (int i = 0; i <= 6; i++) {
            JLabel lb = new JLabel(lbName[i]);
            lb.setBounds(50, 40 * i + 50, 150, 30);
            add(lb);
            if (com[i].equals(txNgayBD )) {
                com[i].setBounds(180, 40 * i + 50, 150, 30);
                dPickerGiamGia.setBounds(330, 40 * i + 50, 35, 30);
                add(dPickerGiamGia);
            } else if (com[i].equals(txNgayKT )) {
                com[i].setBounds(180, 40 * i + 50, 150, 30);
                dPickerGiamGia.setBounds(330, 40 * i + 50, 35, 30);
                add(dPickerGiamGia);
            }else{
                com[i].setBounds(180, 40 * i + 50, 150, 30);
            }
            add(com[i]);
        }
        if (type.equals("Thêm")) {
            btnThem.setBounds(50, 400, 100, 30);
            add(btnThem);
            txMakm.setText(qlKhuyenMai.getNextID());
            txNgayBD.setText(dPickerGiamGia.getDateStringOrEmptyString());
            txNgayKT.setText(dPickerGiamGia.getDateStringOrEmptyString());
            txMakm.setEditable(false);
        }
        if (type.equals("Sửa")) {
            btnSua.setBounds(50, 400, 100, 30);
            add(btnSua);
            kmsua = qlKhuyenMai.getKM(makm);
            if(kmsua==null){
                System.out.println("SAI CMNR");
            }else{
//            String[] lbName = new String[]{"Mã nhân viên", "Họ nhân viên", "Tên nhân viên", "Ngày sinh", "Số điện thoại", "Giới tính", "Trạng thái"};
            txMakm.setText(kmsua.getMakhuyenmai());
            txMakm.setEditable(false);
            txTenkm.setText(kmsua.getTenkhuyenmai());
            txMaSP.setText(kmsua.getMaSanPham());
            
            }
        }
        btnThem.addActionListener(e -> {
            themKM();
            this.dispose();
            
        });
        btnSua.addActionListener(e->{
            suaKM();
            this.dispose();
            
        });
        btnHuy.setBounds(250, 400, 100, 30);
        add(btnHuy);
        btnHuy.addActionListener((e) -> {
            this.dispose();
        });
        dPickerGiamGia.addDateChangeListener((dce) -> {
            txNgayBD.setText(dPickerGiamGia.getDateStringOrEmptyString());
        });
        dPickerGiamGia.addDateChangeListener((dce) -> {
            txNgayKT.setText(dPickerGiamGia.getDateStringOrEmptyString());
        });
    }
    
    private void themKM() {
        KhuyenMaiDTO km = new KhuyenMaiDTO(txMakm.getText(), txTenkm.getText(), txMaSP.getText(), Integer.valueOf(txGiamGia.getText()), new Date(0), new Date(0), txChiTiet.getText());
        km.setMakhuyenmai(txMakm.getText());
        km.setTenkhuyenmai(txTenkm.getText());
        km.setMaSanPham(txMaSP.getText());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //cokmert String to LocalDate
        LocalDate localDate = LocalDate.parse(txNgayBD.getText(), formatter);
        LocalDate localDate1 = LocalDate.parse(txNgayKT.getText(), formatter);
        km.setNgaybatdau(java.sql.Date.valueOf(localDate));
        km.setNgaybatdau(java.sql.Date.valueOf(localDate1));
        km.setChitiet(txChiTiet.getText());
        new KhuyenMaiDAO().insertKhuyenMai(km);
        //new TaiKhoanDAO().insertTaiKhoan(new TaiKhoanDTO(km.getMaKhuyenMai(), "123456"));
        //this.dispose();
    }

    private void suaKM() {
       // KhuyenMaiDTO km  = qlKhuyenMai.getKH()
        kmsua.setMakhuyenmai(txMakm.getText());
        kmsua.setTenkhuyenmai(txTenkm.getText());
        kmsua.setMaSanPham(txMaSP.getText());
        kmsua.setGiamgia(Integer.valueOf(txGiamGia.getText()));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //cokmert String to LocalDate
        LocalDate localDate = LocalDate.parse(txNgayBD.getText(), formatter);
        LocalDate localDate1 = LocalDate.parse(txNgayKT.getText(), formatter);
        kmsua.setNgaybatdau(java.sql.Date.valueOf(localDate));
        kmsua.setNgayketthuc(java.sql.Date.valueOf(localDate1));
        kmsua.setChitiet(txChiTiet.getText());
        int reply = JOptionPane.showConfirmDialog(rootPane,"Bạn có chắc muốn sửa nhân viên");
        if(reply==JOptionPane.YES_OPTION){
            new KhuyenMaiDAO().updateKhuyenMai(kmsua);
            JOptionPane.showMessageDialog(rootPane, "Sửa thành công");
        }else{
            JOptionPane.showMessageDialog(rootPane, "Sửa không thành công");
        }
    }
        public static void main(String[] args) {
           new ThemSuaKhuyenMai("Thêm","").setVisible(true);
        }
}
