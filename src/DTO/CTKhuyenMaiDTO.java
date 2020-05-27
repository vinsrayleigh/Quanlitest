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
public class CTKhuyenMaiDTO {
    private String maKhuyenMai;
    private String maSanPham;
    private int GiamGia;

    public CTKhuyenMaiDTO(String maKhuyenMai, String maSanPham, int GiamGia) {
        this.maKhuyenMai = maKhuyenMai;
        this.maSanPham = maSanPham;
        this.GiamGia = GiamGia;
    }

    public CTKhuyenMaiDTO() {
    }

    public String getMaKhuyenMai() {
        return maKhuyenMai;
    }

    public void setMaKhuyenMai(String maKhuyenMai) {
        this.maKhuyenMai = maKhuyenMai;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public int getGiamGia() {
        return GiamGia;
    }

    public void setGiamGia(int GiamGia) {
        this.GiamGia = GiamGia;
    }
    
}
