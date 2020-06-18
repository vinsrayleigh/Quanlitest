/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Quanli;

import BUS.KhuyenMaiBUS;
import DAO.KhuyenMaiDAO;
import DTO.KhuyenMaiDTO;
import GUI.Button.ExportExcelButton;
import GUI.Button.ImportExcelButton;
import GUI.Button.SuaButton;
import GUI.Button.ThemButton;
import GUI.Button.XoaButton;
import GUI.Excel.XuatExcel;
import GUI.HienThi.HienThiKhuyenMai;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author minkuppj
 */
public class QuanLiKhuyenMaiForm extends JPanel{
    HienThiKhuyenMai formHienThi = new HienThiKhuyenMai();
    ThemButton btnThem = new ThemButton();
    SuaButton btnSua = new SuaButton();
    XoaButton btnXoa = new XoaButton();
    //KhuyenMaiDTO nvSua = new KhuyenMaiDTO();
    ExportExcelButton btnXuatExcel = new ExportExcelButton();
    ImportExcelButton btnNhapExcel = new ImportExcelButton();

    public QuanLiKhuyenMaiForm() {
        setLayout(new BorderLayout());

        // buttons
        
        JPanel plBtn = new JPanel();
        plBtn.add(btnThem);
        plBtn.add(btnXoa);
        plBtn.add(btnSua);
        plBtn.add(btnXuatExcel);
        plBtn.add(btnNhapExcel);

        this.add(plBtn, BorderLayout.NORTH);
        this.add(formHienThi, BorderLayout.CENTER);
        
        //action Listener
        btnThem.addActionListener((ActionEvent ae) -> {
            btnThemMouseClicked();
        });
        btnXoa.addActionListener((ActionEvent ae) -> {
            btnXoaMouseClicked();
        });
        btnSua.addActionListener((ActionEvent ae) -> {
            btnSuaMouseClicked();
        });
        btnXuatExcel.addActionListener((ActionEvent ae) -> {
        //    new XuatExcel().xuatFileExcelKhuyenMai();
        });
        btnNhapExcel.addActionListener((ActionEvent ae) -> {
            //new DocExcel().docFileExcelKhuyenMai();
        });
    }

    private void btnThemMouseClicked() {
        ThemSuaKhuyenMai themnv = new ThemSuaKhuyenMai("Thêm", "");
        themnv.setVisible(true);
        themnv.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                formHienThi.refresh();
            }
        });
    }

    private void btnXoaMouseClicked() {
        if (formHienThi.getTable().getTable().getSelectedRow()>=0) {
            KhuyenMaiDTO nv = new KhuyenMaiBUS().getKM(formHienThi.getSelectedRow(1));
            
            int reply = JOptionPane.showConfirmDialog(formHienThi, "Bạn có muốn xóa khuyến mại?????????");
                if (reply == JOptionPane.YES_OPTION) {
                    if(KhuyenMaiDAO.DeleteKhuyenMai(nv)){
                        JOptionPane.showMessageDialog(formHienThi, "xóa khuyến mại thành công");
                    }else{
                        JOptionPane.showMessageDialog(formHienThi, "xóa khuyến mại không thành công");
                    }
                    formHienThi.refresh();
            } else {
                    JOptionPane.showMessageDialog(formHienThi, "xóa khuyến mại không thành công");
                }
            }
        }

    private void btnSuaMouseClicked() {
        ThemSuaKhuyenMai themnv = new ThemSuaKhuyenMai("Sửa", formHienThi.getSelectedRow(1));
        themnv.setVisible(true);
        themnv.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                formHienThi.refresh();
            }
        });
    }
}
