/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Quanli;

import BUS.HoaDonBUS;
import BUS.SanPhamBUS;
import DTO.*;
import GUI.Button.*;
import GUI.DangNhap;
import GUI.MyTable;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

/**
 *
 * @author phuon
 */
public class HoaDon extends JPanel {

    public static ArrayList<CTHoaDonDTO> list = new ArrayList<>();
    public static KhachHangDTO kh;
    public static NhanVienDTO nv;
    public static int TongTien;
    ArrayList<SanPhamDTO> listSP = new ArrayList<>();
    JTextField txmaHD;
    JTextField txTongTien;
    JTextField txKh;
    JTextField txNv;
    JTextField txDate;
    JTextField txKm;
    XoaButton xoa = new XoaButton();
    SuaButton sua = new SuaButton();
    RefreshButton lammoi = new RefreshButton();
    MyTable tbctHD = new MyTable();
    JButton huy = new JButton("Hủy");
    HoaDonBUS qlHD = new HoaDonBUS();
    JButton ThanhToan = new JButton("Thanh toán");
    JButton btnTimKhach = new JButton("...");
    JButton btnKhuyenMai = new JButton("...");
    public HoaDon() {
        tbctHD.setHeaders(new String[]{"STT", "Mã", "Tên", "Số Lượng", "Đơn giá", "Thành Tiền"});
        setBorder(new EtchedBorder(3));
        setPreferredSize(new Dimension(950, 0));
        setLayout(new BorderLayout());
        add(thongtinHoaDon(), BorderLayout.NORTH);
        add(ctHoaDon(), BorderLayout.CENTER);
        add(thanhToan(), BorderLayout.SOUTH);
        //actionListener
        lammoi.addActionListener((e) -> {
            refreshTable();
        });
        xoa.addActionListener((e) -> {
            Xoa();
        });
        sua.addActionListener((e) -> {
            Sua();
        });
        btnTimKhach.addActionListener((e) -> {
            
        });
    }

    public void Sua() {
        int row = tbctHD.getTable().getSelectedRow();
        if (row > -1) {
            int value = 0;
            try {
                value = Integer.parseInt(JOptionPane.showInputDialog("Nhập số lượng mới"));
                SanPhamDTO sp = listSP.get(row);
                if (value > new SanPhamBUS().getSanPham(sp.getMaSanPham()).getSoLuong()) {
                    JOptionPane.showMessageDialog(this, "Số lượng vượt quá số sản phẩm trong kho");
                } else {
                    listSP.remove(row);
                    addCTHD(SanPhamBUS.getClone(sp, value));
                }
            } catch (Exception e) {
            }
        }
    }

    public void refreshTable() {
        tbctHD.clear();
        listSP.clear();
        list.clear();
        txTongTien.setText("0");
    }

    public void addCTHD(SanPhamDTO sp) {
//        CTHoaDonDTO cthd = new CTHoaDonDTO(new HoaDonBUS().getNextMaHD(), sp.getMaSanPham(), sp.getSoLuong(), sp.getDongia()*sp.getSoLuong(), sp.getDongia());
//        list.add(cthd);
        if (listSP.size() == 0) {
            listSP.add(sp);
        } else {
            listSP.forEach((SP) -> {
                if (SP.getMaSanPham().equals(sp.getMaSanPham())) {
                    if (SP.getSoLuong() + sp.getSoLuong() <= new SanPhamBUS().getSanPham(sp.getMaSanPham()).getSoLuong()) {
                        SP.setSoLuong(SP.getSoLuong() + sp.getSoLuong());
                    } else {
                        JOptionPane.showMessageDialog(this, "Số lượng vượt quá số sản phẩm trong kho");
                    }
                } else {
                    listSP.add(sp);
                }
            });
        }
        setDatatoTable(listSP);
    }

    public JPanel thongtinHoaDon() {
        JPanel panel = new JPanel(new GridLayout(4, 2));
        txmaHD = new JTextField(qlHD.getNextMaHD());
        txTongTien = new JTextField();
        txKh = new JTextField();
        txNv = new JTextField(DangNhap.getTenNV());
        txDate = new JTextField();
        txKm = new JTextField();
        //editable
        txmaHD.setEditable(false);
        txTongTien.setEditable(false);
        txKh.setEditable(false);
        txNv.setEditable(false);
        txDate.setEditable(false);
        txKm.setEditable(false);
        txmaHD.setBorder(BorderFactory.createTitledBorder("Mã Hóa Đơn"));
        txKh.setBorder(BorderFactory.createTitledBorder("Khách hàng"));
        txTongTien.setBorder(BorderFactory.createTitledBorder("Tổng tiền (vnđ)"));
        txNv.setBorder(BorderFactory.createTitledBorder("Nhân viên"));
        txDate.setBorder(BorderFactory.createTitledBorder("Ngày lập"));
        txKm.setBorder(BorderFactory.createTitledBorder("Khuyến Mại"));
        //khachhang
        JPanel Khachhang = new JPanel(new BorderLayout());
        JPanel KhuyenMai = new JPanel(new BorderLayout());

        btnTimKhach.setPreferredSize(new Dimension(50, 50));
        txKh.setPreferredSize(new Dimension(400, 0));
        Khachhang.add(txKh, BorderLayout.WEST);
        Khachhang.add(btnTimKhach, BorderLayout.EAST);
        //khuyenMai

        btnKhuyenMai.setPreferredSize(new Dimension(50, 50));
        txKm.setPreferredSize(new Dimension(400, 0));
        KhuyenMai.add(txKm, BorderLayout.WEST);
        KhuyenMai.add(btnKhuyenMai, BorderLayout.EAST);
        panel.add(txmaHD);
        panel.add(txTongTien);
        panel.add(Khachhang);
        panel.add(txNv);
        panel.add(txDate);
        panel.add(KhuyenMai);
        return panel;
    }

    public void setDatatoTable(ArrayList<SanPhamDTO> list) {
        int stt = 1;
        tbctHD.clear();
        for (SanPhamDTO sp : list) {
            tbctHD.addRow(new String[]{
                stt + "",
                sp.getMaSanPham(),
                sp.getTenSanPham(),
                sp.getSoLuong() + "",
                sp.getDongia() + "000",
                sp.getSoLuong() * sp.getDongia() + "000"
            });
            stt++;
        }
    }

    public JPanel ctHoaDon() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(tbctHD, BorderLayout.CENTER);
        tbctHD.getTable().getModel().addTableModelListener((e) -> {
            updateThanhTien();
        });
        JPanel button = new JPanel(new FlowLayout());
        button.add(xoa);
        button.add(sua);
        button.add(lammoi);
        //button.setPreferredSize(new Dimension(0, 50));
        panel.add(button, BorderLayout.SOUTH);
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                txDate.setText(LocalDate.now().toString());
                //txGioLap.setText(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
                if (txNv.getText().equals("")
                        || txKh.getText().equals("")
                        || txKm.getText().equals("")
                        || listSP.isEmpty()) {
                    ThanhToan.setEnabled(false);
                } else {
                    ThanhToan.setEnabled(true);
                }
            }
        }, 0, 1000);
        return panel;
    }

    public void updateThanhTien() {
        int Tong = 0;
        for (SanPhamDTO sp : listSP) {
            Tong += sp.getSoLuong() * sp.getDongia();
        }
        if (Tong != 0) {
            txTongTien.setText(Tong + "000");
        } else {
            txTongTien.setText("0");
        }
    }

    public JPanel thanhToan() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        huy.setIcon(new ImageIcon("src/Image/cancel_100px.png"));
        ThanhToan.setIcon(new ImageIcon("src/Image/money_50px.png"));
        panel.add(ThanhToan);
        panel.add(huy);
        return panel;
    }

    private void Xoa() {
        int row = tbctHD.getTable().getSelectedRow();
        if (row > -1) {
            listSP.remove(row);
            setDatatoTable(listSP);
        }
    }
}
