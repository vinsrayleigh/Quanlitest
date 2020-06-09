/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Quanli;

import BUS.*;
import BUS.Tool;
import DAO.ThongKeDAO;
import DTO.CTHoaDonDTO;
import DTO.HoaDonDTO;
import DTO.KhachHangDTO;
import DTO.NhanVienDTO;
import DTO.SanPhamDTO;
import GUI.Button.DateButton;
import GUI.Button.RefreshButton;
import GUI.MyTable;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.LayoutManager;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
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
    CTHDBUS qlCTHD = new CTHDBUS();
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
        plCenter.add(tkTQ);
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
            JPanel Tong = new JPanel(new FlowLayout());
            HoaDon banra = new HoaDon();
            public workspace(){
                menu.setPreferredSize(new Dimension(1920-300, 750));
                add(menu);
                //Tong.setBackground(Color.darkGray);
                Tong.add(TongSo("Sản phẩm",qlSP.list.size()));
                Tong.add(TongSo("Nhân viên",qlNV.list.size()));
                Tong.add(TongSo("Khách hàng",qlKH.list.size()));
                Tong.add(TongSo("Nhà cung cấp",qlNCC.list.size()));
                Date now = Tool.getDate(LocalDate.now().toString());
                int month = now.getMonth()+1;
                Tong.add(TongSo("Tháng thu",ThongKeDAO.getThuNhapThang(month+"", month+1+"",now.getYear()+1900+"")));
                Tong.add(TongSo("Tháng chi",ThongKeDAO.getChiThang(month+"", month+1+"",now.getYear()+1900+"")));
                Tong.add(TongSo("Năm thu",ThongKeDAO.getThuNhapNam(now.getYear()+1900+"")));
                Tong.add(TongSo("Năm chi",ThongKeDAO.getChiNam((now.getYear()+1900+""))));
                Tong.setPreferredSize(new Dimension(1620,700));
                
                menu.addTab("Tổng",new ImageIcon("src/Image/bar_chart_50px.png"), Tong);    
                menu.setSelectedComponent(Tong);
                //BanRa
                banra.setPreferredSize(new Dimension(1620,700));
                menu.addTab("Bán ra",new ImageIcon("src/Image/banhang.png"),banra);
                
            }
            public class HoaDon extends JPanel{
                MyTable tb = new MyTable();
                JPanel TongKet = new JPanel(new FlowLayout(FlowLayout.LEFT));
                JLabel lbHD = new JLabel();
                JLabel lbSP= new JLabel();
                JLabel lbKH= new JLabel();
                JLabel lbNV= new JLabel();
                JLabel lbBanra = new JLabel();
                public HoaDon(){
                    setLayout(new BorderLayout());
                    String[] header = new String[]{
                        "Hóa đơn",
                        "Tên nhân viên",
                        "Tên khách hàng",
                        "Tên sản phẩm",
                        "Số Lượng", 
                        "Đơn giá",
                        "Thành tiền"
                    };
                    tb.setHeaders(header);
                    setDatatoTable(qlCTHD.list);
                    add(tb,BorderLayout.CENTER);
                    TongKet.add(lbHD);
                    TongKet.add(lbSP);
                    TongKet.add(lbKH);
                    TongKet.add(lbNV);
                    TongKet.add(lbBanra);
                    lbHD.setSize(200,100);
                    lbSP.setSize(200,100);
                    lbKH.setSize(200,100);
                    lbNV.setSize(200,100);
                    lbBanra.setSize(300,100);
                    lbHD.setPreferredSize(new Dimension(200, 100));;
                    lbSP.setPreferredSize(new Dimension(200, 100));;
                    lbKH.setPreferredSize(new Dimension(200, 100));;
                    lbNV.setPreferredSize(new Dimension(200, 100));;
                    lbBanra.setPreferredSize(new Dimension(300, 100));
                    lbHD.setBorder(BorderFactory.createEtchedBorder(1));
                    lbSP.setBorder(BorderFactory.createEtchedBorder(1));
                    lbKH.setBorder(BorderFactory.createEtchedBorder(1));
                    lbNV.setBorder(BorderFactory.createEtchedBorder(1));
                    lbBanra.setBorder(BorderFactory.createEtchedBorder(1));
                    add(TongKet,BorderLayout.SOUTH);
                }
                public void setDatatoTable(ArrayList<CTHoaDonDTO> list){
                    tb.clear();
                    String containKhach="";
                    String containNhanVien="";
                    String containSanPham="";
                    String containHoaDon="";
                    int demkhach=0;
                    int demnhanvien=0;
                    int demsanpham=0;
                    int demhoadon=0;
                    int Tong=0;
                    for(CTHoaDonDTO ct: list){
                        HoaDonDTO hd = qlHD.getHD(ct.getMaHoaDon());
                        NhanVienDTO nv = qlNV.getNV(hd.getMaNhanVien());
                        KhachHangDTO kh = qlKH.getKH(hd.getMaKhachHang());
                        SanPhamDTO sp = qlSP.getSanPham(ct.getMaSanPham());
                        if(!containKhach.contains(kh.getMaKhachHang())){
                            containKhach=containKhach+kh.getMaKhachHang();
                            demkhach++;
                        }
                        if(!containNhanVien.contains(nv.getMaNhanVien())){
                            containNhanVien+=nv.getMaNhanVien();
                            demnhanvien++;
                        }
                        if(!containSanPham.contains(sp.getMaSanPham())){
                            containSanPham+=sp.getMaSanPham();
                            demsanpham++;
                        }
                        if(!containHoaDon.contains(hd.getMaHoaDon())){
                            containHoaDon+=hd.getMaHoaDon();
                            demhoadon++;
                        }
                        Tong += ct.getThanhTien();
                        tb.addRow(new String[]{
                            ct.getMaHoaDon(),
                            nv.getFullFame(),
                            kh.getFullName(),
                            sp.getTenSanPham(),
                            ct.getSoLuong()+"",
                            ct.getDonGia()+"",
                            ct.getThanhTien()+"",
                        });
                    }
                    lbHD.setText(demhoadon+" Hóa đơn");
                    lbSP.setText(demsanpham+" Sản phẩm");
                    lbKH.setText(demkhach+" Khách hàng");
                    lbNV.setText(demnhanvien+" Nhân viên");
                    lbBanra.setText("Tổng: "+Tool.getMonney(Tong)+",000 vnđ");
                    
                }
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
                }else
                if(type.equals("Nhân viên")){
                    Tool.setPicture(image,"online_support_100px.png");
                    title.setText("Nhân Viên");
                    lbsl.setText(sl+"");
                }else
                if(type.equals("Khách hàng")){
                    Tool.setPicture(image,"account_100px.png");
                    title.setText("Khách hàng");
                    lbsl.setText(sl+"");
                }else
                if(type.equals("Nhà cung cấp")){
                    Tool.setPicture(image,"company_50px.png");
                    title.setText("Nhà cung cấp");
                    lbsl.setText(sl+"");
                }else{
                    Tool.setPicture(image,"money_100px.png");
                    title.setText(type);
                    lbsl.setText(Tool.getMonney(sl)+",000 vnđ");
                    lbsl.setBounds(10, 200, 300, 40);
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
