/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Quanli;

import BUS.*;
import BUS.Tool;
import GUI.Button.DateButton;
import GUI.Button.RefreshButton;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

/**
 *
 * @author phuon
 */
public class ThongKe extends JPanel {
    HoaDonBUS qlHD = new HoaDonBUS();
    NhanVienBUS  qlNV = new NhanVienBUS();
    KhachHangBUS qlKH = new KhachHangBUS();
    NhaCungCapBUS qlNCC = new NhaCungCapBUS();
    SanPhamBUS qlSP = new SanPhamBUS();
    JButton btnThongKetatca = new JButton("Thống kê tất cả");
    JButton btnThongKeSanPham = new JButton("Sản phẩm");
    JButton btnNhanVien = new JButton("Nhân viên");
    JButton btnKhachHang = new JButton("Khách hàng");
    JButton btnNhaCungCap = new JButton("Nhà cung cấp");
    thongKeTongQuat tkTQ = new thongKeTongQuat();
    public ThongKe(){
        setLayout(new BorderLayout());
        JPanel plButton = new JPanel(new FlowLayout(FlowLayout.LEFT));
        plButton.add(btnThongKetatca);
        plButton.add(btnThongKeSanPham);
        plButton.add(btnNhanVien);
        plButton.add(btnKhachHang);
        plButton.add(btnNhaCungCap);
        JPanel plCenter = new JPanel();
        add(plButton,BorderLayout.NORTH);
        add(plCenter,BorderLayout.CENTER);
        //action
        btnThongKetatca.addActionListener((e) -> {
            plCenter.removeAll();
            plCenter.add(tkTQ);
            revalidate();
            repaint();
        });
    }
    public class thongKeTongQuat extends JPanel {

        RefreshButton btnRefresh = new RefreshButton();
        JTextField txKhoangNgay1 = new JTextField(8);
        JTextField txKhoangNgay2 = new JTextField(8);
        DatePicker dPicker1;
        DatePicker dPicker2;
        JTextField txNhanVien = new JTextField(8);
        JTextField txSanPham = new JTextField(8);
        JTextField txNhaCungCap = new JTextField(8);
        JButton btnNhanVien = new JButton("...");
        JButton btnSanPham = new JButton("...");
        JButton btnNhaCungCap = new JButton("...");
        workspace wp = new workspace();
        public thongKeTongQuat() {
            setLayout(new BorderLayout());
            //khoang ngay
            DatePickerSettings pickerSettings = new DatePickerSettings();
            pickerSettings.setVisibleDateTextField(false);
            dPicker1 = new DatePicker(pickerSettings);
            dPicker2 = new DatePicker(pickerSettings.copySettings());
            dPicker1.setDateToToday();
            dPicker2.setDateToToday();
            //button
            DateButton db = new DateButton(dPicker1);
            DateButton db2 = new DateButton(dPicker2);
            txKhoangNgay1.setBorder(BorderFactory.createTitledBorder("Từ:"));
            txKhoangNgay2.setBorder(BorderFactory.createTitledBorder("Đến:"));
            JPanel plHeader = new JPanel();
            JPanel plTimKiemKhoangNgay = new JPanel();
            plTimKiemKhoangNgay.setBorder(BorderFactory.createTitledBorder("Ngày sinh:"));
            plTimKiemKhoangNgay.add(txKhoangNgay1);
            plTimKiemKhoangNgay.add(dPicker1);
            plTimKiemKhoangNgay.add(txKhoangNgay2);
            plTimKiemKhoangNgay.add(dPicker2);
            plHeader.add(plTimKiemKhoangNgay);
            JPanel pltimKiemSanPham = new JPanel();
            JPanel pltimKiemNhanVien = new JPanel();
            JPanel pltimKiemNhaCungCap = new JPanel();
            pltimKiemSanPham.setBorder(BorderFactory.createTitledBorder("Sản phẩm"));
            pltimKiemNhanVien.setBorder(BorderFactory.createTitledBorder("Nhân viên"));
            pltimKiemNhaCungCap.setBorder(BorderFactory.createTitledBorder("Nhà cung cấp"));
            pltimKiemSanPham.add(txSanPham);
            pltimKiemSanPham.add(btnSanPham);
            pltimKiemNhanVien.add(txNhanVien);
            pltimKiemNhanVien.add(btnNhanVien);
            pltimKiemNhaCungCap.add(txNhaCungCap);
            pltimKiemNhaCungCap.add(btnNhaCungCap);
            plHeader.add(pltimKiemSanPham);
            plHeader.add(pltimKiemNhanVien);
            plHeader.add(pltimKiemNhaCungCap);
            plHeader.add(btnRefresh);
            this.add(plHeader,BorderLayout.NORTH);
            this.add(wp,BorderLayout.CENTER);
        }
        private class workspace extends JPanel{
            JTabbedPane menu = new JTabbedPane(JTabbedPane.TOP);
            public workspace(){
                menu.setPreferredSize(new Dimension(1920-300, 750));
                add(menu);
                JPanel Tong = new JPanel(new FlowLayout());
                //Tong.setBackground(Color.darkGray);
                Tong.add(TongSo("Sản phẩm",qlSP.list.size()));
                Tong.add(TongSo("Nhân viên",qlNV.list.size()));
                Tong.add(TongSo("Khách hàng",qlKH.list.size()));
                Tong.add(TongSo("Nhà cung cấp",qlNCC.list.size()));
                Tong.setPreferredSize(new Dimension(1620,700));
                menu.addTab("Tổng",new ImageIcon("src/Image/bar_chart_100px.png"), Tong);
                
            }
            public JPanel TongSo(String type,int sl){
                JPanel panel = new JPanel(null);
                Font font = new Font("Segui", 0, 25);
                Font font1 = new Font("Segui", 0, 30);
                panel.setSize(350, 300);
                panel.setPreferredSize(new Dimension(350, 300));
                //panel.setBackground(Color.DARK_GRAY);
                JLabel image = new JLabel();
                JLabel title = new JLabel();
                JLabel lbsl = new JLabel();
                image.setBounds(30, 30, 100, 150);
                title.setBounds(150, 50, 150, 50);
                title.setFont(font);
                lbsl.setBounds(200, 150, 100, 50);
                lbsl.setFont(font1);
                if(type.equals("Sản phẩm")){
                    Tool.setPicture(image,"product_100px.png");
                    title.setText("Sản phẩm");
                    lbsl.setText(sl+"");
                }
                if(type.equals("Nhân viên")){
                    Tool.setPicture(image,"online_support_100px.png");
                    title.setText("Nhân Viên");
                    lbsl.setText(sl+"");
                }
                if(type.equals("Khách hàng")){
                    Tool.setPicture(image,"account_100px.png");
                    title.setText("Khách hàng");
                    lbsl.setText(sl+"");
                }
                if(type.equals("Nhà cung cấp")){
                    Tool.setPicture(image,"company_50px.png");
                    title.setText("Nhà cung cấp");
                    lbsl.setText(sl+"");
                }
                panel.add(image);
                panel.add(lbsl);
                panel.add(title);
                panel.setBorder(BorderFactory.createBevelBorder(1));
                return panel;
            }
        }
    }
}
