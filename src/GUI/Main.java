/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.HienThi.QuanLiKhachHangForm;
import GUI.NavBar.*;
import GUI.Quanli.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import static java.awt.Frame.ICONIFIED;
import java.awt.Rectangle;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author phuon
 */
public class Main extends JFrame implements MouseListener {

    final int WIDTH = 1920, HEIGHT = 1000;
    int px, py;
    NavBarContainer menu, header;
    NavBarButton currentTab;
    JPanel plContent = new JPanel();
    NavBarTitle headerTitle;
    EmptyPage emptypage = new EmptyPage();
    QuanliNhanVienForm nhanvien;
    BanHangForm banhang;
    QuanLiKhachHangForm khachhang;
    QuanLiSanPhamForm sanpham;
    //test
    String quyen = "qlBanHangqlNhapHangqlNCCqlQuyenqlKhachHangqlSanPhamqlLoaiSanPhamqlHoaDonqlPhieuNhapqlKhuyenMai";

    public Main() {
        setLayout(new BorderLayout());
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        //setResizable(false);
        //setResizable(true);
        setLocationRelativeTo(null);
        String[] navItemInfo = {
            "seperate", "2", "", "",
            "Bán hàng", "banhang.png", "qlBanHang", "qlBanHang",
            "Nhập hàng", "nhaphang.png", "qlNhapHang", "qlNhapHang",
            "seperate", "2", "", "",
            "Sản phẩm", "sanpham.png", "xemSanPham", "qlSanPham",
            "Loại sản phẩm", "loaisp.png", "xemLoaiSanPham", "qlLoaiSanPham",
            "seperate", "2", "", "",
            "Hóa đơn", "hoadon.png", "xemHoaDon", "qlHoaDon",
            "Phiếu nhập", "truck_50px.png", "xemPheuNhap", "qlPhieuNhap",
            "Khuyến mãi", "gift_50px.png", "xemKhuyenMai", "qlKhuyenMai",
            "seperate", "1", "", "",
            "Nhân viên", "support_50px.png", "xemNhanVien", "qlNhanVien",
            "Khách hàng", "user_male_50px.png", "xemKhachHang", "qlKhachHang",
            "Nhà cung cấp", "company_50px.png", "xemNCC", "qlNCC",
            "Thương Hiệu", "company_50px.png", "xemThuongHieu", "qlThuongHieu",
            "seperate", "1", "", "",
            "Tài khoản", "key_50px.png", "xemTaiKhoan", "qlTaiKhoan",
            "Quyền", "lock_50px.png", "xemQuyen", "qlQuyen",
            "seperate", "1", "", "",
            "Thống kê", "bar_chart_50px.png", "", "",
            "Công cụ", "maintenance_50px.png", "", "",
            "Cài đặt", "services_50px.png", "", ""
        };
        int menuW = 250;
        int menuH = 0;
        menu = new NavBarContainer(new Rectangle(0, 0, menuW, HEIGHT));
        //menu.addItem(new NavBarTitle(new Rectangle(0, 0, 0, 55), "CHỨC NĂNG"));
        for (int i = 0; i < navItemInfo.length; i += 4) {
            if (navItemInfo[i].equals("seperate")) {
                NavBarSeperator s = new NavBarSeperator(new Rectangle(0, 0, 0, Integer.parseInt(navItemInfo[i + 1])));
                menu.addItem(s);
            } else {

                String chitietquyen = DangNhap.quyenLogin.getChitiet();
                if (chitietquyen.contains(navItemInfo[i + 2]) || chitietquyen.contains(navItemInfo[i + 3])) {
                    System.out.println(i);
                    NavBarButton nb = new NavBarButton(new Rectangle(0, 0, 0, 60), navItemInfo[i], navItemInfo[i + 1]);
                    nb.addMouseListener(this);
                    menu.addItem(nb);
                    menuH += 60;
                }
            }
        }

        //https://stackoverflow.com/questions/1385737/scrollable-jpanel
        //https://stackoverflow.com/questions/5590242/how-to-hide-the-jscrollbars-in-a-jscrollpane
        //https://stackoverflow.com/questions/5583495/how-do-i-speed-up-the-scroll-speed-in-a-jscrollpane-when-using-the-mouse-wheel
        JScrollPane scrollMenu = new JScrollPane(menu, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        menu.setAutoscrolls(true);
        menu.setPreferredSize(new Dimension(menuW, menuH + 100));
        scrollMenu.setPreferredSize(new Dimension(menuW, HEIGHT));
        scrollMenu.setBorder(BorderFactory.createEmptyBorder());
        scrollMenu.getVerticalScrollBar().setUnitIncrement(5);
// ================ Header ===================
        int headerBg = 30;
        int headerH = 55;
        header = new NavBarContainer(new Rectangle(0, 0, WIDTH, headerH));
        header.setBackground(new Color(headerBg, headerBg, headerBg));

        headerTitle = new NavBarTitle(new Rectangle((WIDTH - 400) / 2, 0, 400, headerH), "QUẢN LÝ");
        headerTitle.setColorDefault(new Color(200, 200, 200));
        headerTitle.setBgDefault(new Color(headerBg, headerBg, headerBg));
        headerTitle.setFontSize(23);
        header.addItem(headerTitle, false);

        // Close Button
        int btnWidth = 50;
        int iconSize = 30;
        NavBarButton btnClose = new NavBarButton(new Rectangle(WIDTH - btnWidth, 0, btnWidth, headerH), "", "cancel_100px.png");
        btnClose.setIconLocation(new Rectangle((btnWidth - iconSize) / 2, (headerH - iconSize) / 2, iconSize, iconSize));
        btnClose.setBgDefault(new Color(headerBg, headerBg, headerBg));
        btnClose.setBgHover(new Color(190, 45, 45));
        btnClose.setToolTipText("Thoát");
        btnClose.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                int reply = JOptionPane.showConfirmDialog(getRootPane(),
                        "Bạn có chắc muốn thoát chương trình Quản Lý?", "Chú ý",
                        JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
        header.addItem(btnClose, false);

        // Minimize Button
        NavBarButton btnMinimize = new NavBarButton(new Rectangle(WIDTH - btnWidth * 2, 0, btnWidth, headerH), "", "below_50px.png");
        btnMinimize.setIconLocation(new Rectangle((btnWidth - iconSize) / 2, (headerH - iconSize) / 2, iconSize, iconSize));
        btnMinimize.setBgDefault(new Color(headerBg, headerBg, headerBg));
        btnMinimize.setBgHover(new Color(49, 49, 49));
        btnMinimize.setToolTipText("Thu nhỏ");
        btnMinimize.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                setState(ICONIFIED);
            }
        });
        header.addItem(btnMinimize, false);

        // logout button
        if (DangNhap.taiKhoanLogin != null) {

            String tenNhanVien = DangNhap.getTenNV();

            NavBarButton btnLogout = new NavBarButton(new Rectangle(0, 0, menuW - btnWidth, headerH), tenNhanVien, "sign_out_50px.png");
            btnLogout.setBgDefault(new Color(headerBg, headerBg, headerBg));
            btnLogout.setBgHover(new Color(49, 49, 49));
            btnLogout.relocate2();
            btnLogout.setToolTipText("Đăng xuất (" + tenNhanVien + " - " + DangNhap.nhanVienLogin.getMaNhanVien() + ")");
            btnLogout.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent me) {
                    logout();
                }
            });
            header.addItem(btnLogout, false);

            // btn Xem tài khoản đăng nhập
            NavBarButton btnSettingUser = new NavBarButton(new Rectangle(menuW - btnWidth, 0, btnWidth, headerH), "", "services_50px.png");
            btnSettingUser.setIconLocation(new Rectangle((btnWidth - iconSize) / 2, (headerH - iconSize) / 2, iconSize, iconSize));
            btnSettingUser.setBgDefault(new Color(headerBg, headerBg, headerBg));
            btnSettingUser.setBgHover(new Color(49, 49, 49));
            btnSettingUser.setToolTipText("Tài khoản");
            btnSettingUser.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent me) {
                    new DoiMatKhauForm(DangNhap.taiKhoanLogin.getMaNhanVien()).setVisible(true);
                }
            });
            header.addItem(btnSettingUser, false);
        }

        // ========= Draggable ===========
        header.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                px = me.getX();
                py = me.getY();
            }
        });

        header.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent me) {
                setLocation(getLocation().x + me.getX() - px, getLocation().y + me.getY() - py);
            }
        });

        plContent.setLayout(new BorderLayout());
        JScrollPane workSpace = new JScrollPane(plContent);
        plContent.add(new BeginForm("Chào "), BorderLayout.CENTER);
        add(header, BorderLayout.NORTH);
        add(scrollMenu, BorderLayout.WEST);
        add(workSpace, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        new Main().setVisible(true);
    }

    public void doAction(String nameAction) {
        plContent.removeAll();
        switch (nameAction) {
            case "Nhân viên":
                if (nhanvien == null) {
                    nhanvien = new QuanliNhanVienForm();
                }
                plContent.setBackground(Color.DARK_GRAY);
                plContent.add(nhanvien, BorderLayout.CENTER);
                break;
            case "Khách hàng":
                if (khachhang == null) {
                    khachhang = new QuanLiKhachHangForm();
                }
                plContent.setBackground(Color.DARK_GRAY);
                plContent.add(khachhang, BorderLayout.CENTER);
                break;
                case "Sản phẩm":
                if (sanpham == null) {
                    sanpham = new  QuanLiSanPhamForm();
                            
                }
                plContent.setBackground(Color.DARK_GRAY);
                plContent.add(sanpham, BorderLayout.CENTER);
                break;
            case "Bán hàng":
                if (banhang == null) {
                    banhang = new BanHangForm();
                }
                plContent.setBackground(Color.DARK_GRAY);
                plContent.add(banhang, BorderLayout.CENTER);
                break;
            case "Công cụ":
                emptypage.setLabelText("Công cụ đang bảo trì");
                plContent.add(emptypage, BorderLayout.CENTER);
                break;

            case "Cài đặt":
                emptypage.setLabelText("Cài đặt đang bảo trì");
                plContent.add(emptypage, BorderLayout.CENTER);
                break;
        }
        headerTitle.setLabel(nameAction.toUpperCase());
        // https://stackoverflow.com/questions/12989388/switching-panels-with-menubar
        revalidate();//refresh ui and layout
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource() instanceof NavBarButton) {

            NavBarButton btn = (NavBarButton) e.getSource();
            if (currentTab != null) {
                currentTab.setActive(false);
            }

            btn.setActive(true);
            currentTab = btn;
            doAction(btn.text);
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void logout() {
        int reply = JOptionPane.showConfirmDialog(getRootPane(),
                "Bạn có chắc muốn đăng xuất khỏi " + DangNhap.getTenNV() + "?", "Chú ý",
                JOptionPane.YES_NO_OPTION);

        if (reply == JOptionPane.YES_OPTION) {
            //new WorkWithFile(LoginForm.saveFileName).write(""); // xóa dữ liệu đăng nhập
            new DangNhap().setVisible(true);
            this.dispose();
        }
    }
}
