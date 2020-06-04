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
import GUI.HienThi.HienThiSanPham;
import java.awt.BorderLayout;
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
        
        this.add(plBtn, BorderLayout.NORTH);
        this.add(formHienThi, BorderLayout.CENTER);
    }
}
