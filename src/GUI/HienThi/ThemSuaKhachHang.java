package GUI.HienThi;

import BUS.KhachHangBUS;
import BUS.Tool;
import DAO.KhachHangDAO;
import DAO.QuyenDAO;
import DAO.TaiKhoanDAO;
import DTO.KhachHangDTO;
import DTO.QuyenDTO;
import DTO.TaiKhoanDTO;
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
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.text.SimpleDateFormat;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ThemSuaKhachHang extends JFrame {
//

    ArrayList<QuyenDTO> listQ;
    String type;
    KhachHangBUS qlKhachHang = new KhachHangBUS();
    KhachHangDTO khsua;
    JTextField txMakh = new JTextField(15);
    JTextField txHokh = new JTextField(15);
    JTextField txTenkh = new JTextField(15);
    JTextField txNgaysinh = new JTextField(10);
    JTextField txSDT = new JTextField(15);
    JComboBox<String> txloaikh = new JComboBox<>(new String[]{"VIP", "Thường"});
    JTextField txTichluy = new JTextField(15);
    JButton btnThem = new JButton("Thêm");
    JButton btnSua = new JButton("Sửa");
    JButton btnHuy = new JButton("Hủy");
    DatePicker dPickerNgaySinh;
//

    public ThemSuaKhachHang(String type, String makh) {
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
        JLabel Title = new JLabel(type + " Khách Hàng", (int) CENTER_ALIGNMENT);
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
        String[] lbName = new String[]{"Mã khách hàng", "Họ khách hàng", "Tên khách hàng", "Ngày sinh", "Số điện thoại", "Loại Khách Hàng", "Tích Lũy"};
        JComponent[] com = new JComponent[]{txMakh, txHokh, txTenkh, txNgaysinh, txSDT, txloaikh, txTichluy};
        for (int i = 0; i <= 6; i++) {
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
        txTichluy.setEditable(false);
        if (type.equals("Thêm")) {
            btnThem.setBounds(50, 400, 100, 30);
            add(btnThem);
            txMakh.setText(qlKhachHang.getNextID());
            txNgaysinh.setText(dPickerNgaySinh.getDateStringOrEmptyString());
            txMakh.setEditable(false);
        }
        if (type.equals("Sửa")) {
            btnSua.setBounds(50, 400, 100, 30);
            add(btnSua);
            khsua = qlKhachHang.getKH(makh);
            if (khsua == null) {
            } else {
//            String[] lbName = new String[]{"Mã nhân viên", "Họ nhân viên", "Tên nhân viên", "Ngày sinh", "Số điện thoại", "Giới tính", "Trạng thái"};
                txMakh.setText(khsua.getMaKhachHang());
                txMakh.setEditable(false);
                txHokh.setText(khsua.getHoKhachHang());
                txTenkh.setText(khsua.getTenKhachHang());
                txNgaysinh.setText(khsua.getNgaySinh().toString());
                txSDT.setText(khsua.getSdt());
                if (khsua.getLoaiKhachHang().equals("VIP")) {
                    txloaikh.setSelectedItem("VIP");
                } else if (khsua.getLoaiKhachHang().equals("Thường")) {
                    txloaikh.setSelectedItem("Thường");
                }
            }
        }
        btnThem.addActionListener(e -> {
            themKH();
            this.dispose();

        });
        btnSua.addActionListener(e -> {
            suaKH();
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
        txTenkh.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (txTenkh.getText().contains(" ") || txTenkh.getText().length() > 40) {
                    txTenkh.setForeground(Color.red);
                    btnThem.setEnabled(false);
                    btnSua.setEnabled(false);
                } else {
                    txTenkh.setForeground(Color.black);
                    btnThem.setEnabled(true);
                    btnSua.setEnabled(true);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (txTenkh.getText().contains(" ") || txTenkh.getText().length() > 40) {
                    txTenkh.setForeground(Color.red);
                    btnThem.setEnabled(false);
                    btnSua.setEnabled(false);
                } else {
                    txTenkh.setForeground(Color.black);
                    btnThem.setEnabled(true);
                    btnSua.setEnabled(true);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (txTenkh.getText().contains(" ") || txTenkh.getText().length() > 40) {
                    txTenkh.setForeground(Color.red);
                    btnThem.setEnabled(false);
                    btnSua.setEnabled(false);
                } else {
                    txTenkh.setForeground(Color.black);
                    btnThem.setEnabled(true);
                    btnSua.setEnabled(true);
                }
            }
        });
        btnSua.setEnabled(false);
        btnThem.setEnabled(false);
        txNgaysinh.setEditable(false);
        addDocumentListener(txSDT);
        addDocumentListener(txHokh);
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
        if (tx.equals(txHokh)) {
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
        if (tx.equals(txTenkh)) {
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
        if (txTenkh.getText().trim().equals("") || txHokh.getText().trim().equals("") || txSDT.getText().trim().equals("")) {
            btnThem.setEnabled(false);
            btnSua.setEnabled(false);
        } else {
            btnThem.setEnabled(true);
            btnSua.setEnabled(true);
        }
    }

    private void themKH() {
        txTichluy.setText("0");
        KhachHangDTO kh = new KhachHangDTO(txMakh.getText(), txHokh.getText(), txTenkh.getText(), new Date(0), txSDT.getText(), txloaikh.getSelectedItem().toString(), Tool.getInt(txTichluy.getText()));
        kh.setMaKhachHang(txMakh.getText());
        kh.setHoKhachHang(txHokh.getText());
        kh.setTenKhachHang(txTenkh.getText());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //cokhert String to LocalDate
        LocalDate localDate = LocalDate.parse(txNgaysinh.getText(), formatter);
        kh.setNgaySinh(java.sql.Date.valueOf(localDate));
        kh.setSdt(txSDT.getText());
        kh.setLoaiKhachHang(txloaikh.getSelectedItem().toString());
        kh.setTichLuy(Tool.getInt(txTichluy.getText()));
        new KhachHangDAO().insertKhachHang(kh);
        //new TaiKhoanDAO().insertTaiKhoan(new TaiKhoanDTO(kh.getMaKhachHang(), "123456"));
        //this.dispose();
    }

    private void suaKH() {
        // KhachHangDTO kh  = qlKhachHang.getKH()
        khsua.setMaKhachHang(txMakh.getText());
        khsua.setHoKhachHang(txHokh.getText());
        khsua.setTenKhachHang(txTenkh.getText());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //cokhert String to LocalDate
        LocalDate localDate = LocalDate.parse(txNgaysinh.getText(), formatter);
        khsua.setNgaySinh(java.sql.Date.valueOf(localDate));
        khsua.setSdt(txSDT.getText());
        khsua.setLoaiKhachHang(txloaikh.getSelectedItem().toString());
        khsua.setTichLuy(Tool.getInt(txTichluy.getText()));
        int reply = JOptionPane.showConfirmDialog(rootPane, "Bạn có chắc muốn sửa nhân viên");
        if (reply == JOptionPane.YES_OPTION) {
            new KhachHangDAO().updateKhachHang(khsua);
            JOptionPane.showMessageDialog(rootPane, "Sửa thành công");
        } else {
            JOptionPane.showMessageDialog(rootPane, "Sửa không thành công");
        }
    }

    public static void main(String[] args) {
        new ThemSuaKhachHang("Thêm", "").setVisible(true);
    }
}
