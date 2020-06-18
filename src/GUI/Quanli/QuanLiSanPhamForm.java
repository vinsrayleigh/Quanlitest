/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Quanli;

import BUS.KhachHangBUS;
import BUS.SanPhamBUS;
import DAO.KhachHangDAO;
import DAO.SanPhamDAO;
import DTO.KhachHangDTO;
import DTO.SanPhamDTO;
import GUI.Button.ExportExcelButton;
import GUI.Button.ImportExcelButton;
import GUI.Button.SuaButton;
import GUI.Button.ThemButton;
import GUI.Button.XoaButton;
import GUI.DangNhap;
import GUI.Excel.DocExcel;
import GUI.Excel.XuatExcel;
import GUI.HienThi.HienThiSanPham;
import GUI.HienThi.HienThiThongTinSP;
import GUI.HienThi.ThemSuaKhachHang;
import GUI.HienThi.ThemSuaSanPham;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author phuon
 */
public class QuanLiSanPhamForm extends JPanel{
    ThemButton btnThem = new ThemButton();
    SuaButton btnSua = new SuaButton();
    XoaButton btnXoa = new XoaButton();
    //NhanVienDTO nvSua = new NhanVienDTO();
    ExportExcelButton btnXuatExcel = new ExportExcelButton();
    ImportExcelButton btnNhapExcel = new ImportExcelButton();
    HienThiSanPham formHienThi = new HienThiSanPham();
   HienThiThongTinSP HienThi=new HienThiThongTinSP();
    public QuanLiSanPhamForm(){
        setLayout(new BorderLayout());
        if(!DangNhap.quyenLogin.getChitiet().contains("qlSanPham")){
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
        plBtn.add(btnNhapExcel);
        plBtn.add(btnXuatExcel);
        formHienThi.setTaget(HienThi);
        this.add(HienThi,BorderLayout.SOUTH);
        this.add(plBtn, BorderLayout.NORTH);
        this.add(formHienThi, BorderLayout.CENTER);
       btnThem.addActionListener((ActionEvent ae) -> {
            btnThemMouseClicked();
        });
        btnXoa.addActionListener((ActionEvent ae) -> {
            btnXoaMouseClicked();
        });
        btnSua.addActionListener((ActionEvent ae) -> {
            btnSuaMouseClicked();
        });
        btnNhapExcel.addActionListener((ActionEvent ae) -> {
            new DocExcel().docFileExcelSanPham();
        });
        btnXuatExcel.addActionListener((ActionEvent ae) -> {
            new XuatExcel().xuatFileExcelSanPham();
        });
    }
    private void btnThemMouseClicked() {
        ThemSuaSanPham themKh = new ThemSuaSanPham("Thêm", "");
        themKh.setVisible(true);
        themKh.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowevent) {
                formHienThi.refresh();
            }
        });
    }

    private void btnXoaMouseClicked() {
        SanPhamDTO sp = new SanPhamBUS().getSanPham(formHienThi.getSelectedRow(1));
        int reply = JOptionPane.showConfirmDialog(formHienThi, "Bạn có muốn xóa sản phẩm?????????");
        if (sp != null) {
            if (reply == JOptionPane.YES_OPTION) {
                if(SanPhamDAO.DeleteSanPham(sp)){
                JOptionPane.showMessageDialog(formHienThi, "xóa sản phẩm thành công");
                }else{
                    JOptionPane.showMessageDialog(formHienThi, "xóa sản phẩm không thành công");
                }
                formHienThi.refresh();
            } else {
                JOptionPane.showMessageDialog(formHienThi, "xóa sản phẩm không thành công");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Chọn sản phẩm để xóa");
        }

    }

    private void btnSuaMouseClicked() {
        ThemSuaSanPham themKh = new ThemSuaSanPham("Sửa", formHienThi.getSelectedRow(1));
        themKh.setVisible(true);
        themKh.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowevent) {
                formHienThi.refresh();
            }
        });
    }
}
