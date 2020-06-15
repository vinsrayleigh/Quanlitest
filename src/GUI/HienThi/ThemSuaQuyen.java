/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.HienThi;

import BUS.QuyenBUS;
import DTO.QuyenDTO;
import GUI.Button.SuaButton;
import GUI.Button.ThemButton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author phuon
 */
public class ThemSuaQuyen extends JFrame{
        QuyenBUS qlQuyen = new QuyenBUS();
        ChiTietQuyen ct = new ChiTietQuyen("");
        String type;
        ThemButton btnthem = new ThemButton();
        SuaButton btnsua = new SuaButton();
        JButton btnHuy = new JButton("Hủy");
        JTextField txMaQuyen = new JTextField(10);
        JTextField txTenQuyen = new JTextField(10);
        public ThemSuaQuyen(String type,String ma){
            this.type = type;
            setLayout(new BorderLayout());
            setLocation(200,200);
            setSize(1300, 800);
            JPanel plLeft = new JPanel(null);
            plLeft.setPreferredSize(new Dimension(500, 0));
            add(plLeft,BorderLayout.WEST);
            add(ct,BorderLayout.EAST);
            txMaQuyen.setBounds(100, 100, 300, 60);
            txMaQuyen.setBorder(BorderFactory.createTitledBorder("Mã quyền"));
            txTenQuyen.setBorder(BorderFactory.createTitledBorder("Tên quyền"));
            txTenQuyen.setBounds(100, 180, 300, 60);
            plLeft.add(txMaQuyen);
            plLeft.add(txTenQuyen);
            btnthem.setBounds(100, 250, 150, 60);
            btnsua.setBounds(100, 250, 150, 60);
            btnHuy.setBounds(250, 250, 150, 60);
            plLeft.add(btnHuy);
            ct.setPreferredSize(new Dimension(800, 0));
            if(type.equals("Sửa")){
                QuyenDTO q = qlQuyen.getQuyen(ma);
                txMaQuyen.setText(ma);
                txMaQuyen.setEditable(false);
                txTenQuyen.setText(q.getTenQuyen());
                ct.setData(q);
                plLeft.add(btnsua);
            }
            if(type.equals("Thêm")){
                plLeft.add(btnthem);
            }
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }