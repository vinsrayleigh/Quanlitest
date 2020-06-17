package GUI.Quanli;

import BUS.HoaDonBUS;
import BUS.KhuyenMaiBUS;
import BUS.SanPhamBUS;
import BUS.Tool;
import DAO.BaoHanhDAO;
import DAO.CTHoaDonDAO;
import DAO.HoaDonDAO;
import DAO.SanPhamDAO;
import DTO.*;
import GUI.Button.*;
import GUI.DangNhap;
import GUI.HienThi.ChonKhachHangForm;
import GUI.HienThi.ChonKhuyenMai;
import GUI.Main;
import GUI.MyTable;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
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

    //public static ArrayList<CTHoaDonDTO> list = new ArrayList<>();
    public KhachHangDTO kh;
    public NhanVienDTO nv;
    public int TongTien;
    public int Giamgia;
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
        Giamgia = 0;
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
            timKhach();
        });
        btnKhuyenMai.addActionListener((e) -> {
            timKhuyenMai();
        });
        ThanhToan.addActionListener((e) -> {
            int reply = JOptionPane.showConfirmDialog(this, "Xác nhận thanh toán");
            if (reply == JOptionPane.YES_OPTION) {
                int money = (TongTien - Giamgia);
                DoThanhToan();
                JOptionPane.showMessageDialog(this, "Thanh toán thành công\n Tổng thanh toán là: " + money + "000đ");
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

    public void timKhuyenMai() {
        btnKhuyenMai.setEnabled(false);
        btnTimKhach.setEnabled(false);
        txKm.setText("");
        ChonKhuyenMai chonkm = new ChonKhuyenMai(txKm);
        chonkm.setVisible(true);
        chonkm.setAlwaysOnTop(true);
        chonkm.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                btnKhuyenMai.setEnabled(true);
                btnTimKhach.setEnabled(true);
                String maKM = txKm.getText();
                KhuyenMaiDTO km = new KhuyenMaiBUS().getKM(maKM);
                boolean check = false;
                for (SanPhamDTO sp : listSP) {
                    if (sp.getMaSanPham().equals(km.getMaSanPham())) {
                        check = true;
                    }
                }
                if (check) {
                    Giamgia = km.getGiamgia();
                    txTongTien.setText((TongTien - Giamgia) + "000");
                } else {
                    JOptionPane.showMessageDialog(null, "Khuyến mại không phù hợp");
                    txKm.setText("");
                }
            }

        });

    }

    public void timKhach() {
        txKh.setText("");
        btnTimKhach.setEnabled(false);
        btnKhuyenMai.setEnabled(false);
        ChonKhachHangForm chon = new ChonKhachHangForm(txKh);
        chon.setAlwaysOnTop(true);
        chon.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                btnKhuyenMai.setEnabled(true);
                btnTimKhach.setEnabled(true);
            }

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
                    addCTHD(SanPhamBUS.getClone(sp, value,sp.getDongia()));
                }
            } catch (Exception e) {
            }
        }
    }

    public void refreshTable() {
        tbctHD.clear();
        listSP.clear();
        //list.clear();
        txTongTien.setText("0");
    }

    public boolean addCTHD(SanPhamDTO sp) {
//        CTHoaDonDTO cthd = new CTHoaDonDTO(new HoaDonBUS().getNextMaHD(), sp.getMaSanPham(), sp.getSoLuong(), sp.getDongia()*sp.getSoLuong(), sp.getDongia());
//        list.add(cthd);
        if (listSP.size() == 0) {
            listSP.add(sp);
        } else {
            int k=0;
            for(SanPhamDTO SP :listSP)
                if (SP.getMaSanPham().equals(sp.getMaSanPham())) {
                    SP.setSoLuong(SP.getSoLuong() + sp.getSoLuong());
                    k=1;
                    return false;
                }
            if(k==0){
                listSP.add(sp);
            }
        }
        updateThanhTien();
        setDatatoTable(listSP);
        return true;
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
        int row = tbctHD.getTable().getSelectedRow();
        if (row > -1) {
            listSP.remove(row);
            setDatatoTable(listSP);
        }
    }

    private void DoThanhToan() {
        SanPhamBUS qlsp = new SanPhamBUS();
        HoaDonDTO hoadon = new HoaDonDTO(txmaHD.getText(), DangNhap.nhanVienLogin.getMaNhanVien(), txKh.getText(), TongTien, Tool.getDate(txDate.getText()), txKm.getText(), Giamgia);
        HoaDonDAO.insertHoaDon(hoadon);
        listSP.forEach((sp) -> {
            CTHoaDonDTO cthd = new CTHoaDonDTO(txmaHD.getText(), sp.getMaSanPham(), sp.getSoLuong(), sp.getDongia() * sp.getSoLuong(), sp.getDongia());
            CTHoaDonDAO.insertCTHD(cthd);
            int temp = (int)(sp.getDongia()/100)%2==0?2:1;
            BaoHanhDTO bh = new BaoHanhDTO(txmaHD.getText(), sp.getMaSanPham(), Tool.getDate(txDate.getText()), temp);
            SanPhamDTO spkho = qlsp.getSanPham(cthd.getMaSanPham());
            spkho.setSoLuong(spkho.getSoLuong() - sp.getSoLuong());
            SanPhamDAO.updateSanPham(spkho);
            BaoHanhDAO.insertBaoHanh(bh);
        });
        clear();
    }

    private void clear() {
        qlHD.getData();
        txmaHD.setText(qlHD.getNextMaHD());
        txKh.setText("");
        txNv.setText(DangNhap.getTenNV());
        txKm.setText("");
        //list.clear();
        listSP.clear();
        tbctHD.clear();
    }
}
