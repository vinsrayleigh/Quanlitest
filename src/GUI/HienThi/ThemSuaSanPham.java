package GUI.HienThi;

import BUS.LoaiSPBUS;
import BUS.NhaCungCapBUS;
import BUS.SanPhamBUS;
import BUS.ThuongHieuBUS;
import BUS.Tool;
import DAO.SanPhamDAO;
import DTO.LoaiSPDTO;
import DTO.NhaCungCapDTO;
import DTO.SanPhamDTO;
import DTO.ThuongHieuDTO;
import GUI.Button.DateButton;
import GUI.NavBar.NavBarSeperator;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ThemSuaSanPham extends JFrame {

    String file;
    String type;
    SanPhamDTO nvsua;
    SanPhamBUS qlSanPham = new SanPhamBUS();
    JTextField txMasp = new JTextField(15);
    JTextField txten = new JTextField(15);
    JTextField txdongia = new JTextField(15);
    JTextField txsoluong = new JTextField(15);
    JTextField txNamSX = new JTextField(15);
    JComboBox<String> txNCC = new JComboBox<>();
    JComboBox<String> txLoaisp = new JComboBox<>();
    JComboBox<String> txMaTH = new JComboBox<>();
    JButton btnThem = new JButton("Thêm");
    JButton btnSua = new JButton("Sửa");
    JButton btnHuy = new JButton("Hủy");
    DatePicker dPickerNamSX;
    JLabel image = new JLabel();

    public ThemSuaSanPham(String type, String manv) {
        setUndecorated(true);
        this.setLayout(null);
        this.setSize(800, 450);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.type = type;
        NavBarSeperator s = new NavBarSeperator(new Rectangle(0, 0, 0, 2));
        s.setBounds(0, 405, 800, 2);
        s.setBackground(Color.DARK_GRAY);
        add(s);
        JLabel Title = new JLabel(type + " sản phẩm", (int) CENTER_ALIGNMENT);
        Title.setFont(new Font("SegoeUI", 1, 30));
        Title.setBackground(Color.DARK_GRAY);
        Title.setOpaque(true);
        Title.setForeground(Color.WHITE);
        Title.setBounds(0, 0, 800, 40);
        add(Title);
        DatePickerSettings pickerSettings = new DatePickerSettings();
        pickerSettings.setVisibleDateTextField(false);
        dPickerNamSX = new DatePicker(pickerSettings);
        dPickerNamSX.setDateToToday();
        DateButton db = new DateButton(dPickerNamSX);
        String[] lbName = new String[]{"Mã sản phẩm", "Tên sản phẩm", "Đơn giá", "Số lượng", "Năm sản xuất", "Nhà cung cấp", "Loại sản phẩm", "Mã thương hiệu"};
        image.setBounds(410, 50, 300, 300);
        JButton btnSelectImage = new JButton();
        btnSelectImage.setIcon(new ImageIcon("src/Image/add_new_50px.png"));
        btnSelectImage.setBounds(450, 360, 30, 30);
        add(image);
        add(btnSelectImage);
        JComponent[] com = new JComponent[]{txMasp, txten, txdongia, txsoluong, txNamSX, txNCC, txLoaisp, txMaTH};
        for (int i = 0; i < 8; i++) {
            JLabel lb = new JLabel(lbName[i]);
            lb.setBounds(50, 40 * i + 50, 150, 30);
            add(lb);
            if (com[i].equals(txNamSX)) {
                com[i].setBounds(180, 40 * i + 50, 150, 30);
                dPickerNamSX.setBounds(330, 40 * i + 50, 35, 30);
                add(dPickerNamSX);
            } else {
                com[i].setBounds(180, 40 * i + 50, 150, 30);
            }
            add(com[i]);
        }
        NhaCungCapBUS qlNCC = new NhaCungCapBUS();
        ThuongHieuBUS qlTH = new ThuongHieuBUS();
        LoaiSPBUS qlLoaiSP = new LoaiSPBUS();
        txNCC.removeAllItems();
        for (NhaCungCapDTO ncc : qlNCC.list) {
            txNCC.addItem(ncc.getMaNCC());
        }
        for (ThuongHieuDTO th : qlTH.getTH()) {
            txMaTH.addItem(th.getMaThuongHieu());
        }
        for (LoaiSPDTO lsp : qlLoaiSP.list) {
            txLoaisp.addItem(lsp.getMaLoaiSP());
        }
        if (type.equals("Thêm")) {
            btnThem.setBounds(50, 410, 100, 30);
            add(btnThem);
            txMasp.setText(qlSanPham.getNextID());
            txNamSX.setText(dPickerNamSX.getDateStringOrEmptyString());
            txMasp.setEditable(false);
            Tool.setPicture(image, "account_100px.png");
        }
        if (type.equals("Sửa")) {
            btnSua.setBounds(50, 400, 100, 30);
            add(btnSua);
            nvsua = qlSanPham.getSanPham(manv);
            if (nvsua != null) {
//            String[] lbName = new String[]{"Mã nhân viên", "Họ nhân viên", "Tên nhân viên", "Ngày sinh", "Số điện thoại", "Giới tính", "Trạng thái"};
                txMasp.setText(nvsua.getMaSanPham());
                txMasp.setEditable(false);
                txten.setText(nvsua.getTenSanPham());
                txdongia.setText(String.valueOf(nvsua.getDongia()));
                txsoluong.setText(String.valueOf(nvsua.getSoLuong()));
                txNamSX.setText(nvsua.getNamSx().toString());
                Tool.setPicture(image, nvsua.getImage());
            }
        }
        btnThem.addActionListener(e -> {
            if (txsoluong.getForeground().equals(Color.black) && txdongia.getForeground().equals(Color.black)&&txten.getForeground().equals(Color.black)) {
                themSP();
                this.dispose();
            } else {
                if (txsoluong.getForeground().equals(Color.red)) {
                    txsoluong.requestFocus();
                } else {
                    txdongia.requestFocus();
                }
            }

        });
        btnSua.addActionListener(e -> {
            if (txsoluong.getForeground().equals(Color.black) && txdongia.getForeground().equals(Color.black)&&txten.getForeground().equals(Color.black)) {
                suaNV();
                this.dispose();
            } else {
                if (txsoluong.getForeground().equals(Color.red)) {
                    txsoluong.requestFocus();
                } else {
                    txdongia.requestFocus();
                }
            }
        });
        btnHuy.setBounds(250, 410, 100, 30);
        add(btnHuy);
        btnHuy.addActionListener((e) -> {
            this.dispose();
        });
        dPickerNamSX.addDateChangeListener((dce) -> {
            txNamSX.setText(dPickerNamSX.getDateStringOrEmptyString());
        });
        btnSelectImage.addActionListener(e -> {
            FileDialog dialog = new FileDialog((Frame) null, "Select Image to Open");
            dialog.setMode(FileDialog.LOAD);
            dialog.setVisible(true);
            file = dialog.getDirectory() + dialog.getFile();
            Tool.setPicture1(image, dialog.getDirectory() + dialog.getFile());
        });
        txNamSX.setEditable(false);
        addDocumentListener(txdongia);
        addDocumentListener(txsoluong);
        addDocumentListener(txten);
    }

    public void setAnh() {
        try {
            BufferedImage img = null;
            File f = new File(file);
            img = ImageIO.read(f);
            try {
                ImageIO.write(img, "jpg", new File("src/Image/" + txMasp.getText() + ".jpg"));
            } catch (IOException ex) {
                System.out.println(ex);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
        if (tx.equals(txdongia) || tx.equals(txsoluong)) {
            try {
                Integer.parseInt(tx.getText());
                tx.setForeground(Color.black);
            } catch (NumberFormatException e) {
                tx.setForeground(Color.red);
            }
        }
        if(tx.equals(txten)){
            if(tx.getText().length()>100){
                tx.setForeground(Color.red);
            }else{
                tx.setForeground(Color.black);
            }
        }

    }
    private void themSP() {
//        String maSanPham, String tenSanPham, int dongia, int soLuong, Date namSx, String maNCC, String maLoaiSP, String maThuongHieu, String image) { 
        SanPhamDTO nv = new SanPhamDTO(txMasp.getText(), txten.getText(), Integer.valueOf(txdongia.getText()), Integer.valueOf(txsoluong.getText()), new Date(0), "", "", "", "");
        nv.setMaSanPham(txMasp.getText());
        nv.setTenSanPham(txten.getText());
        nv.setDongia(Integer.valueOf(txdongia.getText()));
        nv.setSoLuong(Integer.valueOf(txsoluong.getText()));
        nv.setMaNCC((String) txNCC.getSelectedItem());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //convert String to LocalDate
        LocalDate localDate = LocalDate.parse(txNamSX.getText(), formatter);
        nv.setNamSx(java.sql.Date.valueOf(localDate));
        nv.setMaLoaiSP((String) txLoaisp.getSelectedItem());
        nv.setMaThuongHieu((String) txMaTH.getSelectedItem());
        nv.setImage(txMasp.getText() + ".jpg");
        setAnh();
        new SanPhamDAO().insertSanPham(nv);
        //this.dispose();
    }

    private void suaNV() {
        // SanPhamDTO nv  = qlSanPham.getNV()
        nvsua.setMaSanPham(txMasp.getText());
        nvsua.setTenSanPham(txten.getText());
        nvsua.setDongia(Integer.valueOf(txdongia.getText()));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //convert String to LocalDate
        LocalDate localDate = LocalDate.parse(txNamSX.getText(), formatter);
        nvsua.setNamSx(java.sql.Date.valueOf(localDate));
        nvsua.setMaNCC((String) txNCC.getSelectedItem());
        nvsua.setMaThuongHieu((String) txMaTH.getSelectedItem());
        nvsua.setMaLoaiSP((String) txLoaisp.getSelectedItem());
        int reply = JOptionPane.showConfirmDialog(rootPane, "Bạn có chắc muốn sửa nhân viên");
        if (reply == JOptionPane.YES_OPTION) {
            new SanPhamDAO().updateSanPham(nvsua);
            JOptionPane.showMessageDialog(rootPane, "Sửa thành công");
        } else {
            JOptionPane.showMessageDialog(rootPane, "Sửa không thành công");
        }
    }

    public static void main(String[] args) {
        new ThemSuaSanPham("Thêm", "").setVisible(true);
    }
}
