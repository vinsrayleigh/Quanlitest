/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import BUS.QuyenBUS;
import java.sql.Date;

/**
 *
 * @author phuon
 */
public class NhanVienDTO {
    private String maNhanVien;
    private String tenNhanVien;
    private String hoNhanVien;
    private Date ngaySinh;
    private String gioiTinh;
    private String sdt;

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getMaQuyen() {
        return maQuyen;
    }

    public void setMaQuyen(String maQuyen) {
        this.maQuyen = maQuyen;
    }

    public NhanVienDTO(String maNhanVien, String tenNhanVien, String hoNhanVien, Date ngaySinh, String gioiTinh, String sdt, String maQuyen, double luong, int trangThai) {
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.hoNhanVien = hoNhanVien;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.sdt = sdt;
        this.maQuyen = maQuyen;
        this.luong = luong;
        this.trangThai = trangThai;
    }
    private String maQuyen;
    private double luong;
    private int trangThai;

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public NhanVienDTO() {
    }

    public NhanVienDTO(String maNhanVien, String tenNhanVien, String hoNhanVien, Date ngaySinh, String gioiTinh, String maChucVu, double luong) {
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.hoNhanVien = hoNhanVien;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.maQuyen = maChucVu;
        this.luong = luong;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }
    public String getFullFame(){
        return hoNhanVien+" "+tenNhanVien;
    }
    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getHoNhanVien() {
        return hoNhanVien;
    }

    public void setHoNhanVien(String hoNhanVien) {
        this.hoNhanVien = hoNhanVien;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getQuyen() {
        return new QuyenBUS().getQuyen(this.maQuyen).getTenQuyen();
    }

    public void setQuyen(String maChucVu) {
        this.maQuyen = maChucVu;
    }

    public double getLuong() {
        return luong;
    }

    public void setLuong(double luong) {
        this.luong = luong;
    }
}
