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
import GUI.Excel.DocExcel;
import GUI.Excel.XuatExcel;
import GUI.HienThi.HienThiQuyen;
import GUI.HienThi.HienThiSanPham;
import GUI.HienThi.ThemSuaQuyen;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JPanel;

/**
 *
 * @author phuon
 */
public class QuanLiQuyen extends JPanel {

    ThemButton btnThem = new ThemButton();
    SuaButton btnSua = new SuaButton();
    ExportExcelButton btnXuatExcel = new ExportExcelButton();
    ImportExcelButton btnNhapExcel = new ImportExcelButton();
    HienThiQuyen formHienThi = new HienThiQuyen();

    public QuanLiQuyen() {
        setLayout(new BorderLayout());
        if (!DangNhap.quyenLogin.getChitiet().contains("qlQuyen")) {
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
        btnNhapExcel.addActionListener((e) -> {
           new DocExcel().docFileExcelQuyen();
        });
        btnXuatExcel.addActionListener((e) -> {
           new XuatExcel().xuatFileExcelQuyen();
        });
        btnThem.addActionListener((e) -> {
            ThemSuaQuyen themsua = new ThemSuaQuyen("Thêm", "");
            themsua.setVisible(true);
            themsua.setAlwaysOnTop(true);
            themsua.addWindowListener(new WindowAdapter() {
                @Override
                public void windowOpened(WindowEvent e) {
                    btnThem.setEnabled(false);
                    btnSua.setEnabled(false);
                    btnNhapExcel.setEnabled(false);
                    btnXuatExcel.setEnabled(false);
                }

                @Override
                public void windowClosed(WindowEvent e) {
                    btnThem.setEnabled(true);
                    btnSua.setEnabled(true);
                    btnNhapExcel.setEnabled(true);
                    btnXuatExcel.setEnabled(true);
                    formHienThi.refresh();
                }
            });
        });
        btnSua.addActionListener((e) -> {
            ThemSuaQuyen themsua = new ThemSuaQuyen("Sửa", formHienThi.getSelectedRow(1));
            themsua.setVisible(true);
            themsua.setAlwaysOnTop(true);
            themsua.addWindowListener(new WindowAdapter() {
                @Override
                public void windowOpened(WindowEvent e) {
                    btnThem.setEnabled(false);
                    btnSua.setEnabled(false);
                    btnNhapExcel.setEnabled(false);
                    btnXuatExcel.setEnabled(false);
                }

                @Override
                public void windowClosed(WindowEvent e) {
                    btnThem.setEnabled(true);
                    btnSua.setEnabled(true);
                    btnNhapExcel.setEnabled(true);
                    btnXuatExcel.setEnabled(true);
                    formHienThi.refresh();
                }
            });
        });
        this.add(plBtn, BorderLayout.NORTH);
        this.add(formHienThi, BorderLayout.CENTER);
    }
}
