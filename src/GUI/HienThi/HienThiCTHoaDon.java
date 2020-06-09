/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.HienThi;

import BUS.CTHDBUS;
import DTO.CTHoaDonDTO;
import GUI.MyTable;
import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.plaf.RootPaneUI;

/**
 *
 * @author phuon
 */
public class HienThiCTHoaDon extends JPanel{
    CTHDBUS qlCTHD = new CTHDBUS();
    MyTable tbCTHD = new MyTable();
    String maHD = "";
    public HienThiCTHoaDon(String maHD){
        setSize(700, 500);
        setLayout(new BorderLayout());
        this.maHD=maHD;
        String[] header = new String[]{
            "STT",
            "Mã hóa đơn",
            "Mã sản phẩm",
            "Số lượng",
            "Đơn giá",
            "Thành tiền"
        };
        tbCTHD.setHeaders(header);
        setDataToTable(qlCTHD.Seacrh(maHD));
        add(tbCTHD,BorderLayout.CENTER);
    }
    public void setDataToTable(ArrayList<CTHoaDonDTO> list){
        tbCTHD.clear();
        int stt=1;
        for(CTHoaDonDTO ct: list){
            tbCTHD.addRow(new String[]{
                stt+"",
                ct.getMaHoaDon(),
                ct.getMaSanPham(),
                ct.getSoLuong()+"",
                ct.getDonGia()+"",
                ct.getThanhTien()+""
            });
        }
    }
}
