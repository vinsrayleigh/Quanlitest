/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Quanli;

import BUS.NhaCungCapBUS;
import GUI.HienThi.*;
import BUS.NhanVienBUS;
import DAO.NhaCungCapDAO;
import DAO.NhanVienDAO;
import DTO.NhaCungCapDTO;
import DTO.NhanVienDTO;
import GUI.Button.*;
import GUI.*;
import GUI.Excel.DocExcel;
import GUI.Excel.XuatExcel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.TableModelListener;
public class QuanLiNhaCungCap extends JPanel {
     HienThiNhaCungCap formHienThi = new HienThiNhaCungCap();
    ThemButton btnThem = new ThemButton();
    SuaButton btnSua = new SuaButton();
    XoaButton btnXoa = new XoaButton();
    //NhanVienDTO nvSua = new NhanVienDTO();
    ExportExcelButton btnXuatExcel = new ExportExcelButton();
    ImportExcelButton btnNhapExcel = new ImportExcelButton();

    public QuanLiNhaCungCap() {
        setLayout(new BorderLayout());

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
            new XuatExcel().xuatFileExcelNhaCungCap();
        });
        btnNhapExcel.addActionListener((ActionEvent ae) -> {
            new DocExcel().docFileExcelNhaCungCap();
        });
    }

    private void btnThemMouseClicked() {
        ThemSuaNhaCungCap themncc = new ThemSuaNhaCungCap("Thêm", "");
        themncc.setVisible(true);
        themncc.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                formHienThi.refresh();
            }
        });
    }

    private void btnXoaMouseClicked() {
        if (formHienThi.getTable().getTable().getSelectedRow()>=0) {
            NhaCungCapDTO ncc = new NhaCungCapBUS().getNCC(formHienThi.getSelectedRow(1));
//            if (nv.getTrangThai() == 1) {
//                int reply = JOptionPane.showConfirmDialog(formHienThi, "Bạn có muốn ẩn nhân viên?????????");
//                if (reply == JOptionPane.YES_OPTION) {
//                    nv.setTrangThai(0);
//                    NhanVienDAO.updateNhanVien(nv);
//                    JOptionPane.showMessageDialog(formHienThi, "Ẩn nhân viên thành công");
//                    formHienThi.refresh();
//                } else {
//                    JOptionPane.showMessageDialog(formHienThi, "Ẩn nhân viên không thành công");
//                }
//            } else {
//                int reply = JOptionPane.showConfirmDialog(formHienThi, "Bạn có muốn xóa nhân viên?????????");
//                if (reply == JOptionPane.YES_OPTION) {
//                    NhanVienDAO.DeleteNhanVien(nv);
//                    JOptionPane.showMessageDialog(formHienThi, "xóa nhân viên thành công");
//                    formHienThi.refresh();
//                } else {
//                    JOptionPane.showMessageDialog(formHienThi, "xóa nhân viên không thành công");
//                }
           int reply = JOptionPane.showConfirmDialog(formHienThi, "Bạn có muốn xóa nhà cung cấp ?");
           if ( reply == JOptionPane.YES_OPTION) {
               NhaCungCapDAO.DeleteNhaCungCap(ncc);
               JOptionPane.showMessageDialog(this, "Nhà cung cấp đã được xóa");
               formHienThi.refresh();
           }else{
               JOptionPane.showMessageDialog(this,"Xóa nhà cung cấp không thành không");
           }
            
        }else{
            JOptionPane.showMessageDialog(formHienThi,"Bạn phải chọn 1 nhà cung cấp để có thể xóa");
        }
    }

    private void btnSuaMouseClicked() {
        ThemSuaNhaCungCap themncc = new ThemSuaNhaCungCap("Sửa", formHienThi.getSelectedRow(1));
        themncc.setVisible(true);
        themncc.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                formHienThi.refresh();
            }
        });
    }
}

