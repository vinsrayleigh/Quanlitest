/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.HienThi;

import BUS.SanPhamBUS;
import BUS.Tool;
import DTO.SanPhamDTO;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author phuon
 */
public class HienThiThongTinSP extends JPanel {
    JLabel img;
    JPanel ThongTin;
    JTextField txma;
    JTextField txLoai;
    JTextField txTen;
    JTextField txDongia;
    JTextField txNam;
    JTextField txNCC;
    JTextField txThuongHieu;
    JTextField txSoLuong;
    JTextArea txMota;

    public HienThiThongTinSP (){
        setBorder(BorderFactory.createEtchedBorder());
        setLayout(new BorderLayout());
        Font font = new Font("Segui", 0,30);
        img=new JLabel();
        img.setSize(300,300);
        img.setPreferredSize(new Dimension(300,300));
        add(img,BorderLayout.WEST);
        txma = new JTextField();
        txNam = new JTextField();
        txLoai = new JTextField();
        txTen = new JTextField();
        txDongia = new JTextField();
        txNCC = new JTextField();
        txThuongHieu = new JTextField();
        txSoLuong=new JTextField();
        txma.setEditable(false);
        txLoai.setEditable(false);
        txTen.setEditable(false);
        txDongia.setEditable(false);
        txNCC.setEditable(false);
        txThuongHieu.setEditable(false);
        txNam.setEditable(false);
        txSoLuong.setEditable(false);
        txma.setFont(font);
        txLoai.setFont(font);
        txTen.setFont(font);
        txDongia.setFont(font);
        txNCC.setFont(font);
        txThuongHieu.setFont(font);
        txNam.setFont(font);
        txSoLuong.setFont(font);
        txma.setBorder(BorderFactory.createTitledBorder("Mã"));
        txLoai.setBorder(BorderFactory.createTitledBorder("Loại"));
        txTen.setBorder(BorderFactory.createTitledBorder("Tên"));
        txDongia.setBorder(BorderFactory.createTitledBorder("Đơn giá"));
        txNam.setBorder(BorderFactory.createTitledBorder("Năm sản xuất"));
        txNCC.setBorder(BorderFactory.createTitledBorder("Nhà Cung Cấp"));
        txThuongHieu.setBorder(BorderFactory.createTitledBorder("Thương Hiệu"));
        txSoLuong.setBorder(BorderFactory.createTitledBorder("số lượng"));
        ThongTin =new JPanel(new GridLayout(2,4));
        ThongTin.add(txma);
        ThongTin.add(txNam);
        ThongTin.add(txLoai);
        ThongTin.add(txTen);
        ThongTin.add(txDongia);
        ThongTin.add(txNCC);
        ThongTin.add(txThuongHieu);
        ThongTin.add(txSoLuong);
        txMota=new JTextArea();
        JScrollPane jc = new JScrollPane(txMota);
        jc.setPreferredSize(new Dimension(300, 0));
//        jc.setBounds(300, 250, 300, 150);
        txMota.setBorder(BorderFactory.createTitledBorder("Mô tả"));
        txMota.setFont(new Font("Segui", 1, 15));
        txMota.setEditable(false);
        ThongTin.setPreferredSize(new Dimension(700 ,150));
        add(ThongTin,BorderLayout.CENTER);
        add(jc,BorderLayout.EAST);
    }
    public void doAction(SanPhamDTO sp){
           txma.setText(sp.getMaSanPham());
           txLoai.setText(sp.getTenLoaiSP());
           txTen.setText(sp.getTenSanPham());
           txDongia.setText(Tool.getMonney(sp.getDongia())+",000VND");
           txNam.setText(sp.getNamSx().getYear()+1900+"");
           txNCC.setText(sp.getTenNCC());
           txThuongHieu.setText(sp.getTenThuongHieu());
           txSoLuong.setText(sp.getSoLuong()+"");
           Tool.setPicture(img, sp.getImage());
           txMota.setText(sp.getMota());
    }

}
