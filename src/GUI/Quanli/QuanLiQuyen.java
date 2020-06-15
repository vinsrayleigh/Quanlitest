/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Quanli;

import GUI.Button.ExportExcelButton;
import GUI.Button.ImportExcelButton;
import GUI.Button.SuaButton;
import GUI.Button.ThemButton;
import GUI.Button.XoaButton;
import GUI.DangNhap;
import GUI.HienThi.HienThiQuyen;
import GUI.HienThi.HienThiSanPham;
import GUI.HienThi.ThemSuaQuyen;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

/**
 *
 * @author phuon
 */
public class QuanLiQuyen extends JPanel{
     ThemButton btnThem = new ThemButton();
    SuaButton btnSua = new SuaButton();
    ExportExcelButton btnXuatExcel = new ExportExcelButton();
    ImportExcelButton btnNhapExcel = new ImportExcelButton();
    HienThiQuyen formHienThi = new HienThiQuyen();
    public QuanLiQuyen(){
        setLayout(new BorderLayout());
        if(!DangNhap.quyenLogin.getChitiet().contains("qlQuyen")){
            btnThem.setEnabled(false);
            btnSua.setEnabled(false);
            btnNhapExcel.setEnabled(false);
            btnXuatExcel.setEnabled(false);
            formHienThi.getTable().getTable().setEnabled(false);
        }
        JPanel plBtn = new JPanel();
        plBtn.add(btnThem);
        plBtn.add(btnSua);
        plBtn.add(btnNhapExcel);
        plBtn.add(btnXuatExcel);
        btnThem.addActionListener((e) -> {
            ThemSuaQuyen themsua = new ThemSuaQuyen("Thêm", "");
            themsua.setVisible(true);
            themsua.setAlwaysOnTop(true);
        });
        btnSua.addActionListener((e) -> {
            ThemSuaQuyen themsua = new ThemSuaQuyen("Sửa",formHienThi.getSelectedRow(1));
            themsua.setVisible(true);
            themsua.setAlwaysOnTop(true);
        });
        this.add(plBtn, BorderLayout.NORTH);
        this.add(formHienThi, BorderLayout.CENTER);
    }
}
