/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// them 2 form chuyen nghiep
package GUI.Quanli;

import BUS.*;
import BUS.Tool;
import DAO.CTPhieuNhapDAO;
import DAO.ThongKeDAO;
import DTO.CTHoaDonDTO;
import DTO.CTPhieuNhapDTO;
import DTO.HoaDonDTO;
import DTO.KhachHangDTO;
import DTO.NhaCungCapDTO;
import DTO.NhanVienDTO;
import DTO.PhieuNhapDTO;
import DTO.SanPhamDTO;
import GUI.Button.DateButton;
import GUI.Button.RefreshButton;
import GUI.HienThi.FormHienThi;
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
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author phuon
 */
public class ThongKe extends JPanel {

    HoaDonBUS qlHD = new HoaDonBUS();
    NhanVienBUS qlNV = new NhanVienBUS();
    KhachHangBUS qlKH = new KhachHangBUS();
    NhaCungCapBUS qlNCC = new NhaCungCapBUS();
    SanPhamBUS qlSP = new SanPhamBUS();
    PhieuNhapBUS qlPN = new PhieuNhapBUS();
    CTPhieuNhapBUS qlCTPN = new CTPhieuNhapBUS();
    CTHDBUS qlCTHD = new CTHDBUS();
    JButton btnThongKetatca = new JButton("Thống kê tất cả");
    JButton btnThongKeSanPham = new JButton("Sản phẩm");
    JButton btnNhanVien = new JButton("Nhân viên");
    JButton btnKhachHang = new JButton("Khách hàng");
    JButton btnNhaCungCap = new JButton("Nhà cung cấp");
    thongKeTongQuat tkTQ = new thongKeTongQuat();
    thongKeSanPham tkSP = new thongKeSanPham();
    public ThongKe() {
        setLayout(new BorderLayout());
        JPanel plButton = new JPanel(new FlowLayout(FlowLayout.LEFT));
        plButton.add(btnThongKetatca);
        plButton.add(btnThongKeSanPham);
        plButton.add(btnNhanVien);
        plButton.add(btnKhachHang);
        plButton.add(btnNhaCungCap);
        btnThongKetatca.setIcon(new ImageIcon("src/Image/bar_chart_50px.png"));
        btnThongKeSanPham.setIcon(new ImageIcon("src/Image/product_50px.png"));
        JPanel plCenter = new JPanel();
        add(plButton, BorderLayout.NORTH);
        add(plCenter, BorderLayout.CENTER);
        //action
        plCenter.add(tkTQ);
        btnThongKetatca.addActionListener((e) -> {
            plCenter.removeAll();
            plCenter.add(tkTQ);
            revalidate();
            repaint();
        });
        btnThongKeSanPham.addActionListener((e) -> {
           plCenter.removeAll();
           plCenter.add(tkSP);
           revalidate();
           repaint();
        });
    }
    public class thongKeSanPham extends JPanel{
        JComboBox<String> cbNam = new JComboBox<>();
        JComboBox<String> cbTypeSearch = new JComboBox<>();
        JComboBox<String> cbValue = new JComboBox<>();

        public thongKeSanPham() {
            //menu.setPreferredSize(new Dimension(1920 - 300, 750));
            setPreferredSize(new Dimension(1920-300, 800));
            setBackground(Color.DARK_GRAY);
            setLayout(new BorderLayout());
            JPanel plHeader = new JPanel(new BorderLayout());
            JPanel plheadLeft = new JPanel();
            JPanel plheadvalue = new JPanel(new FlowLayout(FlowLayout.LEFT));
            plHeader.add(plheadLeft,BorderLayout.WEST);
            plHeader.add(plheadvalue,BorderLayout.CENTER);
            for(int i= 2010;i<=LocalDate.now().getYear();i++){
                cbNam.addItem(i+"");
            }
            cbNam.setBorder(BorderFactory.createTitledBorder("Chọn năm"));
            cbTypeSearch.setBorder(BorderFactory.createTitledBorder("Thống kê theo"));
            cbValue.setBorder(BorderFactory.createTitledBorder("Tháng"));
            cbNam.setPreferredSize(new Dimension(100, 60));
            cbTypeSearch.setPreferredSize(new Dimension(100, 60));
            cbValue.setPreferredSize(new Dimension(100, 60));
            plheadLeft.add(cbNam);
            plheadLeft.add(cbTypeSearch);
            cbTypeSearch.addItem("Năm");
            cbTypeSearch.addItem("Quý");
            cbTypeSearch.addItem("Tháng");
            cbTypeSearch.addItemListener((e) -> {
                String type = (String) cbTypeSearch.getSelectedItem();
                plheadvalue.removeAll();
                cbValue.removeAllItems();
                switch(type){
                    case "Năm":{
                        break;
                    }
                    case "Quý":{
                        cbValue.addItem("1");
                        cbValue.addItem("2");
                        cbValue.addItem("3");
                        cbValue.addItem("4");
                        plheadvalue.add(cbValue);
                        cbValue.setBorder(BorderFactory.createTitledBorder("Quý"));
                        break;
                    }
                    case "Tháng":{
                        cbValue.addItem("1");
                        cbValue.addItem("2");
                        cbValue.addItem("3");
                        cbValue.addItem("4");
                        cbValue.addItem("5");
                        cbValue.addItem("6");
                        cbValue.addItem("7");
                        cbValue.addItem("8");
                        cbValue.addItem("9");
                        cbValue.addItem("10");
                        cbValue.addItem("11");
                        cbValue.addItem("12");
                        plheadvalue.add(cbValue);
                        cbValue.setBorder(BorderFactory.createTitledBorder("Tháng"));
                        break;
                    }
                }
                revalidate();
                repaint();
            });
            JPanel work = new JPanel();
            work.setBackground(Color.DARK_GRAY);
            this.add(plHeader,BorderLayout.NORTH);
            this.add(work,BorderLayout.CENTER);
        }
        
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
        JPanel pltimKiemSanPham = new JPanel();
        JPanel pltimKiemNhanVien = new JPanel();
        JPanel pltimKiemNhaCungCap = new JPanel();

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
            dPicker1.addDateChangeListener((dce) -> {
                txKhoangNgay1.setText(dPicker1.getDateStringOrEmptyString());
            });
            dPicker2.addDateChangeListener((dce) -> {
                txKhoangNgay2.setText(dPicker2.getDateStringOrEmptyString());
            });
            JPanel plHeader = new JPanel();
            JPanel plTimKiemKhoangNgay = new JPanel();
            plTimKiemKhoangNgay.setBorder(BorderFactory.createTitledBorder("Ngày lập:"));
            plTimKiemKhoangNgay.add(txKhoangNgay1);
            plTimKiemKhoangNgay.add(dPicker1);
            plTimKiemKhoangNgay.add(txKhoangNgay2);
            plTimKiemKhoangNgay.add(dPicker2);
            plHeader.add(plTimKiemKhoangNgay);
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
            this.add(plHeader, BorderLayout.NORTH);
            this.add(wp, BorderLayout.CENTER);
            DocumentListenner(txSanPham);
            DocumentListenner(txKhoangNgay1);
            DocumentListenner(txKhoangNgay2);
            DocumentListenner(txNhaCungCap);
            DocumentListenner(txNhanVien);
            btnRefresh.addActionListener((e) -> {
                refresh();
            });
        }

        private void refresh() {
            txKhoangNgay1.setText("");
            txKhoangNgay2.setText("");
            txNhaCungCap.setText("");
            txNhanVien.setText("");
            txSanPham.setText("");
            qlCTHD.getData();
            qlCTPN.getData();
            if (wp.menu.getSelectedComponent().equals(wp.banra)) {
                wp.banra.setDatatoTable(qlCTHD.list);
            }
            if (wp.menu.getSelectedComponent().equals(wp.nhaphang)) {
                wp.nhaphang.setDatatoTable(qlCTPN.list);
            }
        }

        private void DocumentListenner(JTextField tx) {
            tx.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    txSearchOnChange();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    txSearchOnChange();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    txSearchOnChange();
                }
            });
        }

        public void txSearchOnChange() {
            LocalDate ngay1 = null, ngay2 = null;
            try {
                ngay1 = java.time.LocalDate.parse(txKhoangNgay1.getText());
                txKhoangNgay1.setForeground(Color.black);
            } catch (DateTimeParseException e) {
                txKhoangNgay1.setForeground(Color.red);
            }
            try {
                ngay2 = java.time.LocalDate.parse(txKhoangNgay2.getText());
                txKhoangNgay2.setForeground(Color.black);
            } catch (DateTimeParseException e) {
                txKhoangNgay2.setForeground(Color.red);
            }
            if (txKhoangNgay1.getText().equals("") && txKhoangNgay2.getText().equals("") && txNhaCungCap.getText().equals("") && txNhanVien.getText().equals("") && txSanPham.getText().equals("")) {
                if (wp.menu.getSelectedComponent().equals(wp.banra)) wp.banra.setDatatoTable(qlCTHD.list);
                if (wp.menu.getSelectedComponent().equals(wp.nhaphang)) wp.nhaphang.setDatatoTable(qlCTPN.list);
                return;
            }
            if (wp.menu.getSelectedComponent().equals(wp.banra)) {
                wp.banra.setDatatoTable(getCTHOADON(ngay1, ngay2, txSanPham.getText(), txNhanVien.getText(), txNhaCungCap.getText()));
            }
            if(wp.menu.getSelectedComponent().equals(wp.nhaphang)) {
                wp.nhaphang.setDatatoTable(getCTPhieuNhap(ngay1, ngay2, txSanPham.getText(), txNhanVien.getText(), txNhaCungCap.getText()));
            }
        }

        private ArrayList<CTPhieuNhapDTO> getCTPhieuNhap(LocalDate date1, LocalDate date2, String SP, String NV, String KH) {
            ArrayList<CTPhieuNhapDTO> result = new ArrayList<>();
            for (CTPhieuNhapDTO ctpn : qlCTPN.list) {
                PhieuNhapDTO pn = qlPN.getPN(ctpn.getMaPhieuNhap());
                NhanVienDTO nv = qlNV.getNV(pn.getMaNhanVien());
                NhaCungCapDTO kh = qlNCC.getNCC(pn.getMaNCC());
                SanPhamDTO sp = qlSP.getSanPham(ctpn.getMaSanPham());
                if((Tool.removeAccent(sp.getTenSanPham()).contains(Tool.removeAccent(SP))&&!SP.equals(""))
                        ||(Tool.removeAccent(sp.getMaSanPham()).contains((Tool.removeAccent(SP)))&&!SP.equals(""))
                        ||(Tool.removeAccent(kh.getTenNCC()).contains((Tool.removeAccent(KH)))&&!KH.equals(""))
                        ||(Tool.removeAccent(kh.getMaNCC()).contains((Tool.removeAccent(KH)))&&!KH.equals(""))
                        ||(Tool.removeAccent(nv.getFullFame()).contains(Tool.removeAccent(NV))&&!NV.equals(""))
                        ||(Tool.removeAccent(nv.getMaNhanVien()).contains(Tool.removeAccent(NV))&&!NV.equals("")))
                {
                    result.add(ctpn);
                }
                if(date1!=null)
                if(pn.getNgayLap().before(Date.valueOf(date1)))
                    result.remove(ctpn);
                if(date2!=null){
                if(pn.getNgayLap().after(Date.valueOf(date2)))
                    result.remove(ctpn);    
                }
            }
            return result;
        }

        private ArrayList<CTHoaDonDTO> getCTHOADON(LocalDate date1, LocalDate date2, String SP, String NV, String KH) {
            ArrayList<CTHoaDonDTO> result = new ArrayList<>();
            for (CTHoaDonDTO ct : qlCTHD.list) {
                HoaDonDTO hd = qlHD.getHD(ct.getMaHoaDon());
                NhanVienDTO nv = qlNV.getNV(hd.getMaNhanVien());
                KhachHangDTO kh = qlKH.getKH(hd.getMaKhachHang());
                SanPhamDTO sp = qlSP.getSanPham(ct.getMaSanPham());
                boolean check = true;

                if (date1 != null) {
                    if (!(hd.getNgayLap().after(Date.valueOf(date1)))) {
                        check = false;
                    }
                }
                if (date2 != null) {
                    if (!(hd.getNgayLap().before(Date.valueOf(date2)))) {
                        check = false;
                    }
                }
                if (!KH.equals("")) {
                    if (!hd.getMaKhachHang().contains(KH)&&!Tool.removeAccent(kh.getFullName()).contains(Tool.removeAccent(KH))) {
                        System.out.println(KH);
                        check = false;
                    }
                }
                if (!NV.equals("")) {
                    if (!hd.getMaNhanVien().contains(NV) && !NV.equals("")&&!Tool.removeAccent(nv.getFullFame()).contains(Tool.removeAccent(NV)) && !NV.equals("")) {
                        check = false;
                    }
                }
                if (!SP.equals("")) {
                    if (!Tool.removeAccent(ct.getMaSanPham()).toLowerCase().contains(Tool.removeAccent(SP).toLowerCase()) && !Tool.removeAccent(sp.getTenSanPham()).toLowerCase().contains(Tool.removeAccent(SP).toLowerCase())) {
                        check = false;
                    }
                }

                if (check) {
                    result.add(ct);
                }

            }
            return result;
        }

        private class workspace extends JPanel {

            JTabbedPane menu = new JTabbedPane(JTabbedPane.TOP);
            JPanel Tong = new JPanel(new FlowLayout());
            HoaDon banra = new HoaDon();
            NhapHang nhaphang = new NhapHang();

            public workspace() {
                menu.setPreferredSize(new Dimension(1920 - 300, 750));
                add(menu);
                //Tong.setBackground(Color.darkGray);
                Tong.add(TongSo("Sản phẩm", qlSP.list.size()));
                Tong.add(TongSo("Nhân viên", qlNV.list.size()));
                Tong.add(TongSo("Khách hàng", qlKH.list.size()));
                Tong.add(TongSo("Nhà cung cấp", qlNCC.list.size()));
                Date now = Tool.getDate(LocalDate.now().toString());
                int month = now.getMonth() + 1;
                Tong.add(TongSo("Tháng thu", ThongKeDAO.getThuNhapThang(month + "", month + 1 + "", now.getYear() + 1900 + "")));
                Tong.add(TongSo("Tháng chi", ThongKeDAO.getChiThang(month + "", month + 1 + "", now.getYear() + 1900 + "")));
                Tong.add(TongSo("Năm thu", ThongKeDAO.getThuNhapNam(now.getYear() + 1900 + "")));
                Tong.add(TongSo("Năm chi", ThongKeDAO.getChiNam((now.getYear() + 1900 + ""))));
                Tong.setPreferredSize(new Dimension(1620, 700));

                menu.addTab("Tổng", new ImageIcon("src/Image/bar_chart_50px.png"), Tong);
                menu.setSelectedComponent(Tong);
                //BanRa
                banra.setPreferredSize(new Dimension(1620, 700));
                menu.addTab("Bán ra", new ImageIcon("src/Image/banhang.png"), banra);
                menu.addTab("Nhập hàng", new ImageIcon("src/Image/banhang.png"), nhaphang);
                menu.addChangeListener((e) -> {
                    if (menu.getSelectedComponent().equals(banra)) {
                        pltimKiemNhaCungCap.setBorder(BorderFactory.createTitledBorder("Khách hàng"));
                    }
                    if (menu.getSelectedComponent().equals(nhaphang)) {
                        pltimKiemNhaCungCap.setBorder(BorderFactory.createTitledBorder("Nhà cung cấp"));
                    }
                });

            }

        }

        public class NhapHang extends JPanel {

            FormHienThi tb = new FormHienThi();
            JPanel TongKet = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel lbHD = new JLabel();
            JLabel lbSP = new JLabel();
            JLabel lbKH = new JLabel();
            JLabel lbNV = new JLabel();
            JLabel lbBanra = new JLabel();

            public NhapHang() {
                setLayout(new BorderLayout());
                String[] header = new String[]{
                    "STT",
                    "Phiếu nhập",
                    "Tên nhân viên",
                    "Tên nhà cung cấp",
                    "Tên sản phẩm",
                    "Số lượng",
                    "Đơn giá",
                    "Thành tiền"
                };
                tb.setHeaders(header);
                setDatatoTable(qlCTPN.list);
                add(tb, BorderLayout.CENTER);
                TongKet.add(lbHD);
                TongKet.add(lbSP);
                TongKet.add(lbKH);
                TongKet.add(lbNV);
                TongKet.add(lbBanra);
                lbHD.setSize(200, 100);
                lbSP.setSize(200, 100);
                lbKH.setSize(200, 100);
                lbNV.setSize(200, 100);
                lbBanra.setSize(300, 100);
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
                add(TongKet, BorderLayout.SOUTH);
                tb.getTable().setupSort();
            }

            public void setDatatoTable(ArrayList<CTPhieuNhapDTO> list) {
                tb.getTable().clear();
                int stt = 1;
                String containKhach = "";
                String containNhanVien = "";
                String containSanPham = "";
                String containHoaDon = "";
                int demkhach = 0;
                int demnhanvien = 0;
                int demsanpham = 0;
                int demhoadon = 0;
                int Tong = 0;
                for (CTPhieuNhapDTO ct : list) {
                    PhieuNhapDTO pn = qlPN.getPN(ct.getMaPhieuNhap());
                    NhanVienDTO nv = qlNV.getNV(pn.getMaNhanVien());
                    NhaCungCapDTO kh = qlNCC.getNCC(pn.getMaNCC());
                    SanPhamDTO sp = qlSP.getSanPham(ct.getMaSanPham());
                    if (!containKhach.contains(kh.getMaNCC())) {
                        containKhach = containKhach + kh.getMaNCC();
                        demkhach++;
                    }
                    if (!containNhanVien.contains(nv.getMaNhanVien())) {
                        containNhanVien += nv.getMaNhanVien();
                        demnhanvien++;
                    }
                    if (!containSanPham.contains(sp.getMaSanPham())) {
                        containSanPham += sp.getMaSanPham();
                        demsanpham++;
                    }
                    if (!containHoaDon.contains(pn.getMaPhieuNhap())) {
                        containHoaDon += pn.getMaPhieuNhap();
                        demhoadon++;
                    }
                    Tong += ct.getDongia() * ct.getSoLuong();
                    tb.getTable().addRow(new String[]{
                        stt + "",
                        ct.getMaPhieuNhap(),
                        nv.getFullFame(),
                        kh.getTenNCC(),
                        sp.getTenSanPham(),
                        ct.getSoLuong() + "",
                        ct.getDongia() + "",
                        ct.getDongia() * ct.getSoLuong() + "",});
                    stt++;
                }
                lbHD.setText(demhoadon + " Phiếu nhập");
                lbSP.setText(demsanpham + " Sản phẩm");
                lbKH.setText(demkhach + " Nhà cung cấp");
                lbNV.setText(demnhanvien + " Nhân viên");
                lbBanra.setText("Tổng: " + Tool.getMonney(Tong) + ",000 vnđ");

            }
        }

        public class HoaDon extends JPanel {

            MyTable tb = new MyTable();
            JPanel TongKet = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel lbHD = new JLabel();
            JLabel lbSP = new JLabel();
            JLabel lbKH = new JLabel();
            JLabel lbNV = new JLabel();
            JLabel lbBanra = new JLabel();

            public HoaDon() {
                setLayout(new BorderLayout());
                String[] header = new String[]{
                    "STT",
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
                add(tb, BorderLayout.CENTER);
                TongKet.add(lbHD);
                TongKet.add(lbSP);
                TongKet.add(lbKH);
                TongKet.add(lbNV);
                TongKet.add(lbBanra);
                lbHD.setSize(200, 100);
                lbSP.setSize(200, 100);
                lbKH.setSize(200, 100);
                lbNV.setSize(200, 100);
                lbBanra.setSize(300, 100);
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
                add(TongKet, BorderLayout.SOUTH);
                tb.setupSort();
            }

            public void setDatatoTable(ArrayList<CTHoaDonDTO> list) {
                tb.clear();
                int stt = 0;
                String containKhach = "";
                String containNhanVien = "";
                String containSanPham = "";
                String containHoaDon = "";
                int demkhach = 0;
                int demnhanvien = 0;
                int demsanpham = 0;
                int demhoadon = 0;
                int Tong = 0;
                for (CTHoaDonDTO ct : list) {
                    HoaDonDTO hd = qlHD.getHD(ct.getMaHoaDon());
                    NhanVienDTO nv = qlNV.getNV(hd.getMaNhanVien());
                    KhachHangDTO kh = qlKH.getKH(hd.getMaKhachHang());
                    SanPhamDTO sp = qlSP.getSanPham(ct.getMaSanPham());
                    if (!containKhach.contains(kh.getMaKhachHang())) {
                        containKhach = containKhach + kh.getMaKhachHang();
                        demkhach++;
                    }
                    if (!containNhanVien.contains(nv.getMaNhanVien())) {
                        containNhanVien += nv.getMaNhanVien();
                        demnhanvien++;
                    }
                    if (!containSanPham.contains(sp.getMaSanPham())) {
                        containSanPham += sp.getMaSanPham();
                        demsanpham++;
                    }
                    if (!containHoaDon.contains(hd.getMaHoaDon())) {
                        containHoaDon += hd.getMaHoaDon();
                        demhoadon++;
                    }
                    Tong += ct.getThanhTien();
                    tb.addRow(new String[]{
                        stt++ + "",
                        ct.getMaHoaDon(),
                        nv.getFullFame(),
                        kh.getFullName(),
                        sp.getTenSanPham(),
                        ct.getSoLuong() + "",
                        ct.getDonGia() + "",
                        ct.getThanhTien() + "",});
                }
                lbHD.setText(demhoadon + " Hóa đơn");
                lbSP.setText(demsanpham + " Sản phẩm");
                lbKH.setText(demkhach + " Khách hàng");
                lbNV.setText(demnhanvien + " Nhân viên");
                lbBanra.setText("Tổng: " + Tool.getMonney(Tong) + ",000 vnđ");

            }
        }

        public JPanel TongSo(String type, int sl) {
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
            if (type.equals("Sản phẩm")) {
                Tool.setPicture(image, "product_100px.png");
                title.setText("Sản phẩm");
                lbsl.setText(sl + "");
            } else if (type.equals("Nhân viên")) {
                Tool.setPicture(image, "online_support_100px.png");
                title.setText("Nhân Viên");
                lbsl.setText(sl + "");
            } else if (type.equals("Khách hàng")) {
                Tool.setPicture(image, "account_100px.png");
                title.setText("Khách hàng");
                lbsl.setText(sl + "");
            } else if (type.equals("Nhà cung cấp")) {
                Tool.setPicture(image, "company_50px.png");
                title.setText("Nhà cung cấp");
                lbsl.setText(sl + "");
            } else {
                Tool.setPicture(image, "money_100px.png");
                title.setText(type);
                lbsl.setText(Tool.getMonney(sl) + ",000 vnđ");
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
