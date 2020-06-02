/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Quanli;

import BUS.SanPhamBUS;
import BUS.Tool;
import DTO.SanPhamDTO;
import GUI.Button.ThemButton;
import GUI.MyTable;
import com.sun.prism.j2d.J2DPipeline;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

/**
 *
 * @author phuon
 */
public class ChonSanPham extends JPanel{
    private static SanPhamDTO sanpham;
    ThemButton thembtn = new ThemButton();
    MyTable tbSanPham = new MyTable();
    SanPhamBUS qlsp = new SanPhamBUS();
    SanPhamDTO sp = new SanPhamDTO();
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
    public ChonSanPham(){
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(700, 0));
        String[] header = new String[]{"Mã","Loại","Tên","Đơn Giá","Số Lượng"};
        tbSanPham.setHeaders(header);
        setDatatoTable(qlsp.list);
        
        //thongtinsanpham
        add(tbSanPham,BorderLayout.CENTER);
        add(doHienthiThongtinSanPham(),BorderLayout.SOUTH);
        //action
//        tbSanPham.getTable().getSelectionModel().addListSelectionListener((e) -> {
//            int row = tbSanPham.getTable().getSelectedRow();
//            if(row>0){
//                sp = qlsp.getSanPham(tbSanPham.getModel().getValueAt(row, 0).toString());
//                hienthi(sp);
//            }
//        });
        tbSanPham.getTable().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tbSanPham.getTable().getSelectedRow();
            if(row>-1){
                System.out.println(tbSanPham.getModel().getValueAt(row, 0).toString());
                sp = qlsp.getSanPham(tbSanPham.getModel().getValueAt(row, 0).toString());
                if(sp!=null)                  
                    hienthi(sp);
                else{
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
    }
    public void hienthi(SanPhamDTO sp1){
        txma.setText(sp1.getMaSanPham());
        txLoai.setText(sp1.getTenLoaiSP());
        txTen.setText(sp1.getTenSanPham());
        txDongia.setText(sp1.getDongia()+"000đ");
        txNCC.setText(sp1.getTenNCC());
        txThuongHieu.setText(sp1.getTenThuongHieu());
        txMota.setText(sp1.getMota());
        txNam.setText(sp1.getNamSx()+"");
        Tool.setPicture(imageSP, sp.getImage());
        imageSP.invalidate();
        imageSP.validate();
        imageSP.repaint();
    }
    public JPanel doHienthiThongtinSanPham(){
        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(0, 500));
        thongtinSanPham.setLayout(null);
        
        imageSP.setBounds(30, 30, 250, 300);
        //String maSanPham, String tenSanPham, double dongia, int soLuong, Date namSx, String maNCC, String maLoaiSP, String maThuongHieu, String image) {
        String[] Title = new String[]{"Mã","Loại","Tên","Đơn giá","Năm sản xuất","Nhà Cung Cấp","Thương Hiệu"};
        //JTextField[] tx = new JTextField[]{txma,txLoai,txTen,txDongia,txNCC,txThuongHieu,txThuongHieu};
        txma = new JTextField();
        txNam = new JTextField();
        txLoai= new JTextField();
        txTen= new JTextField();
        txDongia= new JTextField();
        txNCC= new JTextField();
        txThuongHieu= new JTextField();
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
        info.add(txSoLuong);
        info.setBounds(300, 30, 400, 200);
        txMota = new JTextArea();
        JScrollPane jc = new JScrollPane(txMota);
        jc.setBounds(300,250, 300, 150);
        txMota.setBorder(BorderFactory.createTitledBorder("Mô tả"));
        txMota.setFont(new Font("Segui", 1, 15));
        txMota.setEditable(false);
        Tool.setPicture(imageSP,"src/Image/"+"sanpham.png");
        thongtinSanPham.add(info);
        thongtinSanPham.add(jc);
        thongtinSanPham.add(imageSP);
        panel.add(thongtinSanPham,BorderLayout.CENTER);
        panel.add(thembtn,BorderLayout.SOUTH);
        return panel;
    }
    public void setDatatoTable(ArrayList<SanPhamDTO> list){
        tbSanPham.clear();
        list.forEach((sp) -> {
            System.out.println(sp.getMaSanPham());
            tbSanPham.addRow(new String[]{sp.getMaSanPham(),sp.getTenLoaiSP(),sp.getTenSanPham(),String.valueOf(sp.getDongia()),String.valueOf(sp.getSoLuong())});
        });
    }
}