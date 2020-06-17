/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Quanli;

import BUS.SanPhamBUS;
import BUS.Tool;
import DTO.SanPhamDTO;
import GUI.Button.RefreshButton;
import GUI.Button.ThemButton;
import GUI.MyTable;
import com.sun.prism.j2d.J2DPipeline;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author phuon
 */
public class ChonSanPham extends JPanel {

    private static SanPhamDTO sanpham;
    ThemButton thembtn = new ThemButton();
    MyTable tbSanPham = new MyTable();
    SanPhamBUS qlsp = new SanPhamBUS();
    SanPhamDTO sp = new SanPhamDTO();
    JTextField txTimKiem = new JTextField(30);
    RefreshButton btnRefresh = new RefreshButton();
    JTextField txma;
    JTextField txLoai;
    JTextField txTen;
    JTextField txDongia;
    JTextField txNam;
    JTextField txNCC;
    JTextField txThuongHieu;
    JTextField txSoLuong;
    JTextArea txMota;
    JLabel imageSP = new JLabel();
    JPanel thongtinSanPham = new JPanel();
    JPanel info = new JPanel(new GridLayout(4, 2));
    HoaDon tagetHD;
    PhieuNhap tagetPN;
    String type;
    public ChonSanPham() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(700, 0));
        String[] header = new String[]{"Mã", "Loại", "Tên", "Đơn Giá", "Số Lượng"};
        tbSanPham.setHeaders(header);
        setDatatoTable(qlsp.list);

        //thongtinsanpham
        add(tbSanPham, BorderLayout.CENTER);
        add(doHienthiThongtinSanPham(), BorderLayout.SOUTH);
        add(Timkiem(), BorderLayout.NORTH);
        //action
//        tbSanPham.getTable().getSelectionModel().addListSelectionListener((e) -> {
//            int row = tbSanPham.getTable().getSelectedRow();
//            if(row>0){
//                sp = qlsp.getSanPham(tbSanPham.getModel().getValueAt(row, 0).toString());
//                hienthi(sp);
//            }
//        });
        Tool.AddDocumentListener("Num", txDongia);
        Tool.AddDocumentListener("Num", txSoLuong);
        tbSanPham.getTable().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tbSanPham.getTable().getSelectedRow();
                if (row > -1) {
                    sp = qlsp.getSanPham(tbSanPham.getModel().getValueAt(row, 0).toString());
                    if (sp != null) {
                        hienthi(sp);
                    } else {
                    }
                }
                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mousePressed(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        //Action
        thembtn.addActionListener((e) -> {
            Them();
        });
        type = "";
        if (type.equals("PN")) {
//            public ArrayList<SanPhamDTO> search(String value, String type, int soluong1, int soluong2, float gia1, float gia2) {
            setDatatoTable(qlsp.search(tagetPN.txNCC.getText(), "Nhà cung cấp", -1, -1, -1, -1));
        }

    }
    public static JFrame chonSanPham(JTextField tx){
        ChonSanPham chon = new ChonSanPham();
        chon.type="";
        JFrame main = new JFrame();
        main.setLayout(new BorderLayout());
        main.setSize(700,900);
        main.add(chon,BorderLayout.CENTER); 
        main.setAlwaysOnTop(true);
        chon.txSoLuong.setEditable(false);
        chon.txSoLuong.setText("1");
        chon.txDongia.setText("11");
        try {
            chon.thembtn.addActionListener((e) -> {
                int row = chon.tbSanPham.getTable().getSelectedRow();
                if(row>-1){
                    tx.setText((String)chon.tbSanPham.getTable().getValueAt(row, 0));
                }
                main.dispose();
            });
        } catch (Exception e) {
            main.dispose();
        }   
        return main;
    }
    public void Them() {
        int row = tbSanPham.getTable().getSelectedRow();
        if (row > -1) {
            try {
                int sl = 0;
                sl = Integer.parseInt(txSoLuong.getText());
                if (type.equals("HD")) {
                    if(tagetHD.addCTHD(SanPhamBUS.getClone(sp, sl,sp.getDongia()))){
                    }
                    //tbSanPham.getTable().getModel().setValueAt((Tool.getInt((String)tbSanPham.getTable().getValueAt(row, 4))-sl)+"", row, 4);
                } else {
                    int dongia=0;
                    try {
                        dongia = Integer.parseInt(txDongia.getText());
                        tagetPN.addCTPN(SanPhamBUS.getClone(sp, sl,dongia));
                        //tbSanPham.getTable().getModel().setValueAt((Tool.getInt((String)tbSanPham.getTable().getValueAt(row, 4))+sl)+"", row, 4);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(this, "Đơn giá phải là số nguyên dương");
                    }
                    
                }
            } catch (Exception e) {
                if(!type.equals(""))
                JOptionPane.showMessageDialog(this, "Số lượng phải là số nguyên dương và nhỏ hơn số lượng của sp");
            }
        } else {
        }

        //HoaDon.list.add();
    }

    public void setTaget(HoaDon taget, String type) {
        this.tagetHD = taget;
        txSoLuong.setText("1");
        txSoLuong.setEditable(false);
        this.type = type;
    }

    public void setTaget(PhieuNhap taget, String type) {
        this.tagetPN = taget;
        this.type = type;
        txDongia.setEditable(true);
        tagetPN.txNCC.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                setDatatoTable(qlsp.search(tagetPN.txNCC.getText(), "Nhà cung cấp", -1, -1, -1, -1));
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                setDatatoTable(qlsp.search(tagetPN.txNCC.getText(), "Nhà cung cấp", -1, -1, -1, -1));
                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                setDatatoTable(qlsp.search(tagetPN.txNCC.getText(), "Nhà cung cấp", -1, -1, -1, -1));
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }

    public void hienthi(SanPhamDTO sp1) {
        txma.setText(sp1.getMaSanPham());
        txLoai.setText(sp1.getTenLoaiSP());
        txTen.setText(sp1.getTenSanPham());
        if(type.equals("PN")){
            txDongia.setText((int)(sp.getDongia()-sp.getDongia()*0.4)+"");
        }else{
            txDongia.setText(sp.getDongia()+"");
        }
        txNCC.setText(sp1.getTenNCC());
        txThuongHieu.setText(sp1.getTenThuongHieu());
        txMota.setText(sp1.getMota());
        txNam.setText(sp1.getNamSx() + "");
        Tool.setPicture(imageSP, sp.getImage());
        imageSP.invalidate();
        imageSP.validate();
        imageSP.repaint();
    }

    public JPanel Timkiem() {
        JPanel plTimKiem = new JPanel();
        txTimKiem.setBorder(BorderFactory.createTitledBorder("Tìm kiếm"));
        txTimKiem.setHorizontalAlignment(JLabel.CENTER);
        addDocumentListener(txTimKiem);
        plTimKiem.add(txTimKiem);
        btnRefresh.addActionListener((ae) -> {
            refreshTable();
        });
        plTimKiem.add(btnRefresh);
        return plTimKiem;
    }

    private void addDocumentListener(JTextField tx) {
        // https://stackoverflow.com/questions/3953208/value-change-listener-to-jtextfield
        if (!tx.equals(txSoLuong)) {
            tx.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void changedUpdate(DocumentEvent e) {
                    txSearchOnChange();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    txSearchOnChange();
                }

                @Override
                public void insertUpdate(DocumentEvent e) {
                    txSearchOnChange();
                }
            });
        } else {
            tx.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void changedUpdate(DocumentEvent e) {
                    try {
                        if (!txSoLuong.getText().equals("")) {
                            int i = Integer.parseInt(txSoLuong.getText());
                            txSoLuong.setForeground(Color.BLACK);
                            if (i > sp.getSoLuong()) {
                                txSoLuong.setForeground(Color.red);
                            }
                        }
                    } catch (NumberFormatException ex) {
                        txSoLuong.setForeground(Color.red);
                    }
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    try {
                        if (!txSoLuong.getText().equals("")) {
                            int i = Integer.parseInt(txSoLuong.getText());
                            txSoLuong.setForeground(Color.BLACK);
                        }
                    } catch (NumberFormatException ex) {
                        txSoLuong.setForeground(Color.red);
                    }
                }

                @Override
                public void insertUpdate(DocumentEvent e) {
                    try {
                        if (!txSoLuong.getText().equals("")) {
                            int i = Integer.parseInt(txSoLuong.getText());
                            txSoLuong.setForeground(Color.BLACK);
                        }
                    } catch (NumberFormatException ex) {
                        txSoLuong.setForeground(Color.red);
                    }
                }
            });

        }
    }

    private void txSearchOnChange() {
        setDatatoTable(qlsp.search(txTimKiem.getText().toLowerCase(), "Tất cả", -1, -1, -1, -1));
    }

    public JPanel doHienthiThongtinSanPham() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(0, 500));
        thongtinSanPham.setLayout(null);

        imageSP.setBounds(30, 30, 250, 300);
        //String maSanPham, String tenSanPham, double dongia, int soLuong, Date namSx, String maNCC, String maLoaiSP, String maThuongHieu, String image) {
        String[] Title = new String[]{"Mã", "Loại", "Tên", "Đơn giá", "Năm sản xuất", "Nhà Cung Cấp", "Thương Hiệu"};
        //JTextField[] tx = new JTextField[]{txma,txLoai,txTen,txDongia,txNCC,txThuongHieu,txThuongHieu};
        txma = new JTextField();
        txNam = new JTextField();
        txLoai = new JTextField();
        txTen = new JTextField();
        txDongia = new JTextField();
        txNCC = new JTextField();
        txThuongHieu = new JTextField();
        txma.setEditable(false);
        txLoai.setEditable(false);
        txTen.setEditable(false);
        txDongia.setEditable(false);
        txNCC.setEditable(false);
        txThuongHieu.setEditable(false);
        txNam.setEditable(false);
        txma.setBorder(BorderFactory.createTitledBorder("Mã"));
        txLoai.setBorder(BorderFactory.createTitledBorder("Loại"));
        txTen.setBorder(BorderFactory.createTitledBorder("Tên"));
        txDongia.setBorder(BorderFactory.createTitledBorder("Đơn giá"));
        txNam.setBorder(BorderFactory.createTitledBorder("Năm sản xuất"));
        txNCC.setBorder(BorderFactory.createTitledBorder("Nhà Cung Cấp"));
        txThuongHieu.setBorder(BorderFactory.createTitledBorder("Thương Hiệu"));
        //txma.setBorder(BorderFactory.createTitledBorder("Mã"));
        info.add(txLoai);
        info.add(txDongia);
        info.add(txNCC);
        info.add(txThuongHieu);
        info.add(txTen);
        info.add(txma);
        info.add(txNam);
        txSoLuong = new JTextField();
        txSoLuong.setBorder(BorderFactory.createTitledBorder("Số Lượng"));
        addDocumentListener(txSoLuong);
        info.add(txSoLuong);
        info.setBounds(300, 30, 400, 200);
        txMota = new JTextArea();
        JScrollPane jc = new JScrollPane(txMota);
        jc.setBounds(300, 250, 300, 150);
        txMota.setBorder(BorderFactory.createTitledBorder("Mô tả"));
        txMota.setFont(new Font("Segui", 1, 15));
        txMota.setEditable(false);
        Tool.setPicture(imageSP, "src/Image/" + "sanpham.png");
        thongtinSanPham.add(info);
        thongtinSanPham.add(jc);
        thongtinSanPham.add(imageSP);
        panel.add(thongtinSanPham, BorderLayout.CENTER);
        panel.add(thembtn, BorderLayout.SOUTH);
        return panel;
    }

    public void setDatatoTable(ArrayList<SanPhamDTO> list) {
        tbSanPham.clear();
        list.forEach((sp) -> {
            tbSanPham.addRow(new String[]{sp.getMaSanPham(), sp.getTenLoaiSP(), sp.getTenSanPham(), String.valueOf(sp.getDongia()), String.valueOf(sp.getSoLuong())});
        });
    }

    private void refreshTable() {
        qlsp.getData();
        setDatatoTable(qlsp.list);
        revalidate();
        repaint();
    }
}
