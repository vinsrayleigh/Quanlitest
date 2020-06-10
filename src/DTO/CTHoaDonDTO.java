/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author phuon
 */
public class CTHoaDonDTO {
    private String maHoaDon;
    private String maSanPham;
    private int soLuong;
    private int thanhTien;
    private int donGia;

    public CTHoaDonDTO(String maHoaDon, String maSanPham, int soLuong, int thanhTien, int donGia) {
        this.maHoaDon = maHoaDon;
        this.maSanPham = maSanPham;
        this.soLuong = soLuong;
        this.thanhTien = thanhTien;
        this.donGia = donGia;
    }

    public CTHoaDonDTO() {
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }
    public boolean equal(CTHoaDonDTO cthd){
        return (this.maHoaDon.equals(cthd.getMaHoaDon())&&this.maSanPham.equals(cthd.getMaSanPham()));
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(int thanhTien) {
        this.thanhTien = thanhTien;
    }

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }
    
}
