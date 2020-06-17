/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.HienThi;

import DTO.QuyenDTO;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JPanel;

/**
 *
 * @author phuon
 */
public class ChiTietQuyen extends JPanel {

    String type;
    JComboBox<String> cbBanHang = new JComboBox<>();
    JComboBox<String> cbNhapHang = new JComboBox<>();
    JComboBox<String> cbSanPham = new JComboBox<>();
    JComboBox<String> cbLoaiSanPham = new JComboBox<>();
    JComboBox<String> cbHoaDon = new JComboBox<>();
    JComboBox<String> cbPhieuNhap = new JComboBox<>();
    JComboBox<String> cbKhuyenMai = new JComboBox<>();
    JComboBox<String> cbBaoHanh = new JComboBox<>();
    JComboBox<String> cbNhanVien = new JComboBox<>();
    JComboBox<String> cbKhachHang = new JComboBox<>();
    JComboBox<String> cbNhaCungCap = new JComboBox<>();
    JComboBox<String> cbThuongHieu = new JComboBox<>();
    JComboBox<String> cbQuyen = new JComboBox<>();
    JComboBox<String> cbThongKe = new JComboBox<>();

    public ChiTietQuyen(String type) {
        this.type = type;
        ArrayList<JComboBox<String>> list = new ArrayList<>();
        list.add(cbBanHang);
        list.add(cbNhapHang);
        list.add(cbSanPham);
        list.add(cbLoaiSanPham);
        list.add(cbHoaDon);
        list.add(cbPhieuNhap);
        list.add(cbKhuyenMai);
        list.add(cbBaoHanh);
        list.add(cbNhanVien);
        list.add(cbKhachHang);
        list.add(cbNhaCungCap);
        list.add(cbThuongHieu);
        list.add(cbQuyen);
        list.add(cbThongKe);
        setValueCombo(list);
        cbBanHang.setBorder(BorderFactory.createTitledBorder("Bán hàng"));
        cbNhapHang.setBorder(BorderFactory.createTitledBorder("Nhập hàng"));
        cbSanPham.setBorder(BorderFactory.createTitledBorder("Sản phẩm"));
        cbLoaiSanPham.setBorder(BorderFactory.createTitledBorder("Loại sản phẩm"));
        cbHoaDon.setBorder(BorderFactory.createTitledBorder("Hóa đơn"));
        cbPhieuNhap.setBorder(BorderFactory.createTitledBorder("Phiếu nhập"));
        cbKhuyenMai.setBorder(BorderFactory.createTitledBorder("Khuyến mãi"));
        cbBaoHanh.setBorder(BorderFactory.createTitledBorder("Bảo hành"));
        cbNhanVien.setBorder(BorderFactory.createTitledBorder("Nhân viên"));
        cbKhachHang.setBorder(BorderFactory.createTitledBorder("Khách hàng"));
        cbNhaCungCap.setBorder(BorderFactory.createTitledBorder("Nhà cung cấp"));
        cbThuongHieu.setBorder(BorderFactory.createTitledBorder("Thương hiệu"));
        cbQuyen.setBorder(BorderFactory.createTitledBorder("Quyền"));
        cbThongKe.setBorder(BorderFactory.createTitledBorder("Thống kê"));
    }

    public void setData(QuyenDTO q) {
        String k = q.getChitiet();
        if (k.contains("qlBaoHanh")) {
            cbBaoHanh.setSelectedIndex(2);
        } else if (k.contains("xemBaoHanh")) {
            cbBaoHanh.setSelectedIndex(1);
        } else {
            cbBaoHanh.setSelectedIndex(0);
        }
        if (k.contains("qlNhanVien")) {
            cbNhanVien.setSelectedIndex(2);
        } else if (k.contains("xemNhanVien")) {
            cbNhanVien.setSelectedIndex(1);
        } else {
            cbNhanVien.setSelectedIndex(0);
        }
        if (k.contains("qlThuongHieu")) {
            cbThuongHieu.setSelectedIndex(2);
        } else if (k.contains("xemThuongHieu")) {
            cbThuongHieu.setSelectedIndex(1);
        } else {
            cbThuongHieu.setSelectedIndex(0);
        }
        if (k.contains("qlKhuyenMai")) {
            cbKhuyenMai.setSelectedIndex(2);
        } else if (k.contains("xemKhuyenMai")) {
            cbKhuyenMai.setSelectedIndex(1);
        } else {
            cbKhuyenMai.setSelectedIndex(0);
        }
        if (k.contains("qlPhieuNhap")) {
            cbPhieuNhap.setSelectedIndex(2);
        } else if (k.contains("xemPhieuNhap")) {
            cbPhieuNhap.setSelectedIndex(1);
        } else {
            cbPhieuNhap.setSelectedIndex(0);
        }
        if (k.contains("qlHoaDon")) {
            cbHoaDon.setSelectedIndex(2);
        } else if (k.contains("xemHoaDon")) {
            cbHoaDon.setSelectedIndex(1);
        } else {
            cbHoaDon.setSelectedIndex(0);
        }
        if (k.contains("qlLoaiSanPham")) {
            cbLoaiSanPham.setSelectedIndex(2);
        } else if (k.contains("xemLoaiSanPham")) {
            cbLoaiSanPham.setSelectedIndex(1);
        } else {
            cbLoaiSanPham.setSelectedIndex(0);
        }
        if (k.contains("qlSanPham")) {
            cbSanPham.setSelectedIndex(2);
        } else if (k.contains("xemSanPham")) {
            cbSanPham.setSelectedIndex(1);
        } else {
            cbSanPham.setSelectedIndex(0);
        }
        if (k.contains("qlKhachHang")) {
            cbKhachHang.setSelectedIndex(2);
        } else if (k.contains("xemKhachHang")) {
            cbKhachHang.setSelectedIndex(1);
        } else {
            cbKhachHang.setSelectedIndex(0);
        }
        if (k.contains("qlBanHang")) {
            cbBanHang.setSelectedIndex(2);
        } else {
            cbBanHang.setSelectedIndex(0);
        }
        if (k.contains("qlNhapHang")) {
            cbNhapHang.setSelectedIndex(2);
        } else {
            cbNhapHang.setSelectedIndex(0);
        }
        if (k.contains("qlQuyen")) {
            cbQuyen.setSelectedIndex(2);
        } else if (k.contains("xemQuyen")) {
            cbQuyen.setSelectedIndex(1);
        } else {
            cbQuyen.setSelectedIndex(0);
        }
        if (k.contains("qlNCC")) {
            cbNhaCungCap.setSelectedIndex(2);
        } else if (k.contains("xemNCC")) {
            cbNhaCungCap.setSelectedIndex(1);
        } else {
            cbNhaCungCap.setSelectedIndex(0);
        }
        if (k.contains("qlThongKe")) {
            cbThongKe.setSelectedIndex(2);
        } else if (k.contains("xemThongKe")) {
            cbThongKe.setSelectedIndex(1);
        } else {
            cbThongKe.setSelectedIndex(0);
        }
    }

    public String getQuyen() {
        String s = "";
        if (cbBanHang.getSelectedIndex() == 1) {
            s += "xemBanHang";
        } else if (cbBanHang.getSelectedIndex() == 2) {
            s += "qlBanHang";
        }
        if (cbNhapHang.getSelectedIndex() == 1) {
            s += "xemNhapHang";
        } else if (cbNhapHang.getSelectedIndex() == 2) {
            s += "qlNhapHang";
        }
        if (cbSanPham.getSelectedIndex() == 1) {
            s += "xemSanPham";
        } else if (cbSanPham.getSelectedIndex() == 2) {
            s += "qlSanPham";
        }
        if (cbLoaiSanPham.getSelectedIndex() == 1) {
            s += "xemLoaiSanPham";
        } else if (cbLoaiSanPham.getSelectedIndex() == 2) {
            s += "qlLoaiSanPham";
        }
        if (cbHoaDon.getSelectedIndex() == 1) {
            s += "xemHoaDon";
        } else if (cbHoaDon.getSelectedIndex() == 2) {
            s += "qlHoaDon";
        }
        if (cbPhieuNhap.getSelectedIndex() == 1) {
            s += "xemPhieuNhap";
        } else if (cbPhieuNhap.getSelectedIndex() == 2) {
            s += "qlPhieuNhap";
        }
        if (cbKhuyenMai.getSelectedIndex() == 1) {
            s += "xemKhuyenMai";
        } else if (cbKhuyenMai.getSelectedIndex() == 2) {
            s += "qlKhuyenMai";
        }
        if (cbBaoHanh.getSelectedIndex() == 1) {
            s += "xemBaoHanh";
        } else if (cbBaoHanh.getSelectedIndex() == 2) {
            s += "qlBaoHanh";
        }
        if (cbNhanVien.getSelectedIndex() == 1) {
            s += "xemNhanVien";
        } else if (cbNhanVien.getSelectedIndex() == 2) {
            s += "qlNhanVien";
        }
        if (cbKhachHang.getSelectedIndex() == 1) {
            s += "xemKhachHang";
        } else if (cbKhachHang.getSelectedIndex() == 2) {
            s += "qlKhachHang";
        }
        if (cbNhaCungCap.getSelectedIndex() == 1) {
            s += "xemNhaCungCap";
        } else if (cbNhaCungCap.getSelectedIndex() == 2) {
            s += "qlNhaCungCap";
        }
        if (cbThuongHieu.getSelectedIndex() == 1) {
            s += "xemThuongHieu";
        } else if (cbThuongHieu.getSelectedIndex() == 2) {
            s += "qlThuongHieu";
        }
        if (cbQuyen.getSelectedIndex() == 1) {
            s += "xemQuyen";
        } else if (cbQuyen.getSelectedIndex() == 2) {
            s += "qlQuyen";
        }
        if (cbThongKe.getSelectedIndex() == 1) {
            s += "xemThongKe";
        } else if (cbThongKe.getSelectedIndex() == 2) {
            s += "qlThongKe";
        }
        return s;
    }

    public void setValueCombo(ArrayList<JComboBox<String>> list) {

        for (JComboBox<String> cb : list) {
            if (type.equals("xem")) {
                cb.setEditable(false);
                cb.setEnabled(false);
            }
            cb.addItem("Non");
            cb.addItem("Xem");
            cb.addItem("Quản lí");
            cb.setPreferredSize(new Dimension(300, 100));
            add(cb);
            cb.setFont(new Font("SEGUI", 1, 40));
        }
    }
}
