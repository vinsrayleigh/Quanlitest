package GUI;

import DTO.*;
import BUS.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class DangNhap extends JFrame {
    public static String saveFileName = "temp";
    public static QuyenDTO quyenLogin;
    public static NhanVienDTO nhanVienLogin;
    public static TaiKhoanDTO taiKhoanLogin;
    JTextField txTaiKhoan;
    JPasswordField txMatKhau;
    JCheckBox nhoMK = new JCheckBox("Giữ đăng nhập");   
    public DangNhap() {
        setUndecorated(true);
        setSize(350, 400);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        JLabel title = new JLabel("QUẢN LÍ MÁY TÍNH", (int) CENTER_ALIGNMENT);
        title.setBounds(0, 0, 350, 70);
        title.setBackground(new Color(10, 10, 10));
        title.setFont(new Font("Segoe", 1, 24));
        title.setForeground(Color.WHITE);
        title.setOpaque(true);
        JLabel icon = new JLabel();
        icon.setBounds(140, 70, 350, 70);
        setPicture(icon, "src/Image/user.png");
        icon.setBackground(Color.WHITE);
        icon.setOpaque(true);
        JLabel id = new JLabel();
        JLabel pass = new JLabel();
        id.setBounds(10, 140, 40, 40);
        pass.setBounds(10, 210, 40, 40);
        setPicture(id, "src/Image/user_male_50px.png");
        setPicture(pass, "src/Image/key_50px.png");
        txTaiKhoan = new JTextField();
        txTaiKhoan.setToolTipText("Mã nhân viên");
        txMatKhau = new JPasswordField();
        txMatKhau.setToolTipText("MK: mặc định là 123456");
        txTaiKhoan.setBounds(60, 140, 270, 40);
        txMatKhau.setBounds(60, 210, 270, 40);
        nhoMK.setBounds(60, 250, 300, 40);
        nhoMK.setBackground(Color.WHITE);
        JButton btnDangNhap = new JButton("ĐĂNG NHẬP");
        btnDangNhap.setFont(new Font("Segoe", 1, 36));
        btnDangNhap.setBounds(0, 290, 350, 110);
        btnDangNhap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkDangNhap();
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        KeyAdapter ka = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnDangNhap.doClick();
                }
                if (ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    int reply = JOptionPane.showConfirmDialog(getRootPane(),
                            "Bạn có chắc muốn thoát chương trình Quản Lý?", "Chú ý",
                            JOptionPane.YES_NO_OPTION);
                    if (reply == JOptionPane.YES_OPTION) {
                        System.exit(0);
                    }
                }
            }
        };
        txTaiKhoan.addKeyListener(ka);
        txMatKhau.addKeyListener(ka);
        //addÂDMI
        add(btnDangNhap);
        add(nhoMK);
        add(txTaiKhoan);
        add(txMatKhau);
        add(id);
        add(pass);
        add(title);
        add(icon);
    }

    private void checkDangNhap() {
        String tentk = txTaiKhoan.getText();
        String mk = txMatKhau.getText();
        TaiKhoanBUS ql = new TaiKhoanBUS();
        TaiKhoanDTO tk = ql.getTK(tentk);
        if (tk != null) {
            NhanVienDTO nv = new NhanVienBUS().getNV(tentk);
            if (nv.getTrangThai() == 0) {
                JOptionPane.showMessageDialog(this, "Tài khoản này đã bị khóa, do chủ nhân tài khoản này đã bị ẨN khỏi hệ thống!");
                return;
            }
            if (tk.getPassword().equals(mk)) {
                taiKhoanLogin = tk;
                nhanVienLogin = nv;
                quyenLogin = new QuyenBUS().getQuyen(nhanVienLogin.getQuyen());
                new Main().setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Sai mật khẩu!");
                txMatKhau.requestFocus();
            }

        } else {
            JOptionPane.showMessageDialog(this, "Sai tên đăng nhập!");
            txTaiKhoan.requestFocus();
        }

    }
    public void setPicture(JLabel label, String filename) {
        try {
            BufferedImage image = ImageIO.read(new File(filename));
            int x = label.getSize().width;
            int y = label.getSize().height;
            int ix = image.getWidth();
            int iy = image.getHeight();
            int dx = 0;
            int dy = 0; 
            if (x / y > ix / iy) {
                dy = y;
                dx = dy * ix / iy;
            } else {
                dx = x;
                dy = dx * iy / ix;
            }
            ImageIcon icon = new ImageIcon(image.getScaledInstance(dx, dy, BufferedImage.SCALE_SMOOTH));
            label.setIcon(icon);
        } catch (IOException ex) {
        }

    }
    public static String getTenNV(){
        return nhanVienLogin.getHoNhanVien()+" "+nhanVienLogin.getTenNhanVien();
    }
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {

        }
        new DangNhap().setVisible(true);
    }
}
