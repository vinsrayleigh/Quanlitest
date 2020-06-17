/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Quanli;

import BUS.HoaDonBUS;
import BUS.KhuyenMaiBUS;
import BUS.PhieuNhapBUS;
import BUS.SanPhamBUS;
import BUS.Tool;
import DAO.CTHoaDonDAO;
import DAO.CTPhieuNhapDAO;
import DAO.HoaDonDAO;
import DAO.PhieuNhapDAO;
import DAO.SanPhamDAO;
import DTO.CTHoaDonDTO;
import DTO.CTPhieuNhapDTO;
import DTO.HoaDonDTO;
import DTO.KhachHangDTO;
import DTO.KhuyenMaiDTO;
import DTO.NhanVienDTO;
import DTO.PhieuNhapDTO;
import DTO.SanPhamDTO;
import GUI.Button.RefreshButton;
import GUI.Button.SuaButton;
import GUI.Button.XoaButton;
import GUI.DangNhap;
import GUI.HienThi.ChonKhachHangForm;
import GUI.HienThi.ChonKhuyenMai;
import GUI.HienThi.ChonNhaCungCap;
import GUI.Main;
import GUI.MyTable;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.sql.Date;
import java.time.LocalDate;
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
public class PhieuNhap extends JPanel {

    //public static ArrayList<CTHoaDonDTO> list = new ArrayList<>();
    public static KhachHangDTO kh;
    public static NhanVienDTO nv;
    public static int TongTien;
    ArrayList<SanPhamDTO> listSP = new ArrayList<>();
    JTextField txmaHD;
    JTextField txTongTien;
    public JTextField txNCC;
    JTextField txNv;
    JTextField txDate;
    XoaButton xoa = new XoaButton();
    SuaButton sua = new SuaButton();
    RefreshButton lammoi = new RefreshButton();
    MyTable tbctPN = new MyTable();
    JButton huy = new JButton("Hủy");
    PhieuNhapBUS qlPhieuNhap = new PhieuNhapBUS();
    JButton ThanhToan = new JButton("Thanh toán");
    //JButton btnNCC = new JButton("...");

    public PhieuNhap() {
        tbctPN.setHeaders(new String[]{"STT", "Mã", "Tên", "Số Lượng", "Đơn giá", "Thành Tiền"});
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
        ThanhToan.addActionListener((e) -> {
            int reply = JOptionPane.showConfirmDialog(this, "Xác nhận thanh toán");
            if (reply == JOptionPane.YES_OPTION) {
                DoThanhToan();
                JOptionPane.showMessageDialog(this, "Thanh toán thành công\n Tổng thanh toán là: " + txTongTien.getText() + "đ");
                txTongTien.setText("0");
                Main.BacktoMain();
            } else {
                JOptionPane.showMessageDialog(this, "Hủy thanh toán");
            }
        });
        huy.addActionListener((e) -> {
           Main.BacktoMain();
        });
    }

    public void timNCC() {
        //new ChonKhachHangForm(txKh).setVisible(true);
        new ChonNhaCungCap(txNCC);
    }

    public void Sua() {
        int row = tbctPN.getTable().getSelectedRow();
        if (row > -1) {
            int value = 0;
            try {
                value = Integer.parseInt(JOptionPane.showInputDialog("Nhập số lượng mới"));
                if (value > 0) {
                    SanPhamDTO sp = listSP.get(row);
                    listSP.remove(row);
                    addCTPN(SanPhamBUS.getClone(sp, value,sp.getDongia()));
                } else {
                    JOptionPane.showMessageDialog(this, "Số lượng phải là số nguyên dương");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Số lượng phải là số nguyên dương");
            }
        }
    }

    public void refreshTable() {
        tbctPN.clear();
        listSP.clear();
        //list.clear();
        txTongTien.setText("0");
    }

    public void addCTPN(SanPhamDTO sp) {
//        CTHoaDonDTO cthd = new CTHoaDonDTO(new HoaDonBUS().getNextMaHD(), sp.getMaSanPham(), sp.getSoLuong(), sp.getDongia()*sp.getSoLuong(), sp.getDongia());
//        list.add(cthd);
        if (listSP.size() == 0) {
            listSP.add(sp);
        } else {
            int k =0;
            for(SanPhamDTO SP: listSP){
                if (SP.getMaSanPham().equals(sp.getMaSanPham())) {
                    SP.setSoLuong(SP.getSoLuong() + sp.getSoLuong());
                    k=1;
                    break;
                }
            }
            if(k==0){
                listSP.add(sp);
            }
        }
        setDatatoTable(listSP);
    }

    public JPanel thongtinHoaDon() {
        JPanel panel = new JPanel(new GridLayout(4, 2));
        txmaHD = new JTextField(qlPhieuNhap.getNextMaPN());
        txTongTien = new JTextField();
        txNCC = new JTextField();
        txNv = new JTextField(DangNhap.getTenNV());
        txDate = new JTextField();
        //editable
        txmaHD.setEditable(false);
        txTongTien.setEditable(false);
        txNCC.setEditable(false);
        txNv.setEditable(false);
        txDate.setEditable(false);
        txmaHD.setBorder(BorderFactory.createTitledBorder("Mã Hóa Đơn"));
        txNCC.setBorder(BorderFactory.createTitledBorder("Nhà cung cấp"));
        txTongTien.setBorder(BorderFactory.createTitledBorder("Tổng tiền (vnđ)"));
        txNv.setBorder(BorderFactory.createTitledBorder("Nhân viên"));
        txDate.setBorder(BorderFactory.createTitledBorder("Ngày lập"));
        //khachhang
        JPanel NhaCungCap = new JPanel(new BorderLayout());

        txNCC.setPreferredSize(new Dimension(400, 0));
        NhaCungCap.add(txNCC, BorderLayout.WEST);
        //khuyenMai
        panel.add(txmaHD);
        panel.add(txTongTien);
        panel.add(NhaCungCap);
        panel.add(txNv);
        panel.add(txDate);
        return panel;
    }

    public void setDatatoTable(ArrayList<SanPhamDTO> list) {
        int stt = 1;
        tbctPN.clear();
        for (SanPhamDTO sp : list) {
            tbctPN.addRow(new String[]{
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
        panel.add(tbctPN, BorderLayout.CENTER);
        tbctPN.getTable().getModel().addTableModelListener((e) -> {
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
                        || txNCC.getText().equals("")
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
        TongTien = Tong;
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
        int row = tbctPN.getTable().getSelectedRow();
        if (row > -1) {
            listSP.remove(row);
            setDatatoTable(listSP);
        }
    }

    private void DoThanhToan() {
        SanPhamBUS qlsp = new SanPhamBUS();
//        HoaDonDTO hoadon = new HoaDonDTO(txmaHD.getText(), DangNhap.nhanVienLogin.getMaNhanVien(), txNCC.getText(), TongTien, Tool.getDate(txDate.getText()), Giamgia);        
        //HoaDonDAO.insertHoaDon(hoadon);
        PhieuNhapDTO phieunhap = new PhieuNhapDTO(txmaHD.getText(), DangNhap.nhanVienLogin.getMaNhanVien(), txNCC.getText(), TongTien,Tool.getDate(txDate.getText()));
        PhieuNhapDAO.insertPhieuNhap(phieunhap);
        listSP.forEach((sp) -> {
//            CTHoaDonDTO cthd = new CTHoaDonDTO(txmaHD.getText(), sp.getMaSanPham(), sp.getSoLuong(), sp.getDongia() * sp.getSoLuong(), sp.getDongia());
//            CTHoaDonDAO.insertCTHD(cthd);
            CTPhieuNhapDTO ctPN = new CTPhieuNhapDTO(txmaHD.getText(), sp.getMaSanPham(), sp.getSoLuong(), sp.getDongia());
            CTPhieuNhapDAO.insertCTPhieuNhap(ctPN);
            SanPhamDTO spkho = qlsp.getSanPham(ctPN.getMaSanPham());
            spkho.setSoLuong(spkho.getSoLuong() + sp.getSoLuong());
            SanPhamDAO.updateSanPham(spkho);
        });
        clear();
    }

    private void clear() {
        qlPhieuNhap.getData();
        txmaHD.setText(qlPhieuNhap.getNextMaPN());
        //txNCC.setText("");
        //txNv.setText(DangNhap.getTenNV());
        //list.clear();
        listSP.clear();
        tbctPN.clear();
    }
}
