/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.sql.Date;

/**
 *
 * @author phuon
 */
public class BaoHanhDTO {
    private String maHoaDon;
    private String maSanPham;
    private Date ngayLap;
    private int thoiHan;

    public BaoHanhDTO(String maHoaDon, String maSanPham, Date ngayLap, int thoiHan) {
        this.maHoaDon = maHoaDon;
        this.maSanPham = maSanPham;
        this.ngayLap = ngayLap;
        this.thoiHan = thoiHan;
    }

    public BaoHanhDTO() {
    }

    public String getMaHoaDon() {
        return maHoaDon;
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

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }

    public int getThoiHan() {
        return thoiHan;
    }

    public void setThoiHan(int thoiHan) {
        this.thoiHan = thoiHan;
    }
    
}
