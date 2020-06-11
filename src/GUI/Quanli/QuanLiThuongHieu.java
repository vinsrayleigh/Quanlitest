/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Quanli;

import BUS.ThuongHieuBUS;
import GUI.HienThi.*;
import DAO.ThuongHieuDAO;
import DAO.NhanVienDAO;
import DTO.ThuongHieuDTO;
import GUI.Button.*;
import GUI.*;
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
public class QuanLiThuongHieu extends JPanel {
    HienThiThuongHieu formHienThi = new HienThiThuongHieu();
    ThemButton btnThem = new ThemButton();
    SuaButton btnSua = new SuaButton();
    XoaButton btnXoa = new XoaButton();
    //NhanVienDTO nvSua = new NhanVienDTO();
    ExportExcelButton btnXuatExcel = new ExportExcelButton();
    ImportExcelButton btnNhapExcel = new ImportExcelButton();

    public QuanLiThuongHieu() {
        setLayout(new BorderLayout());

        // buttons
        if (!DangNhap.quyenLogin.getChitiet().contains("qlThuongHieu")) {
            btnThem.setEnabled(false);
            btnXoa.setEnabled(false);
            btnSua.setEnabled(false);
            btnNhapExcel.setEnabled(false);
            btnXuatExcel.setEnabled(false);
            formHienThi.getTable().getTable().setEnabled(false);
        }

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
            new XuatExcel().xuatFileExcelNhanVien();
        });
        btnNhapExcel.addActionListener((ActionEvent ae) -> {
            //new DocExcel().docFileExcelNhanVien();
        });
    }

    private void btnThemMouseClicked() {
        ThemSuaThuongHieu themthuonghieu = new ThemSuaThuongHieu("Thêm", "");
        themthuonghieu.setVisible(true);
        themthuonghieu.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                formHienThi.refresh();
            }
        });
    }

    private void btnXoaMouseClicked() {
        if (formHienThi.getTable().getTable().getSelectedRow()>=0) {
            ThuongHieuDTO th = new ThuongHieuBUS().getTH(formHienThi.getSelectedRow(1));
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
           int reply = JOptionPane.showConfirmDialog(formHienThi, "Bạn có muốn ẩn thương hiệu ?");
           if ( reply == JOptionPane.YES_OPTION) {
               ThuongHieuDAO.DeleteThuongHieu(th);
               JOptionPane.showMessageDialog(this, "Thương Hiệu đã được xóa");
               formHienThi.refresh();
           }else{
               JOptionPane.showMessageDialog(this,"Xóa thương hiệu không thành không");
           }
            
        }else{
            JOptionPane.showMessageDialog(formHienThi,"Bạn phải chọn 1 nhân viên để có thể xóa");
        }
    }

    private void btnSuaMouseClicked() {
        ThemSuaThuongHieu themTH = new ThemSuaThuongHieu("Sửa", formHienThi.getSelectedRow(1));
        themTH.setVisible(true);
        themTH.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                formHienThi.refresh();
            }
        });
    }
}

