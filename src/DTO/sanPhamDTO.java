package DTO;

import java.sql.Date;

public class sanPhamDTO {
    private String maSanPham;
    private String tenSanPham;
    private double dongia;
    private int soLuong;
    private Date namSx;
    private String maNCC;
    private String maLoaiSP;
    private String maThuongHieu;
    private String image;
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public sanPhamDTO(String maSanPham, String tenSanPham, double dongia, int soLuong, Date namSx, String maNCC, String maLoaiSP, String maThuongHieu, String image) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.dongia = dongia;
        this.soLuong = soLuong;
        this.namSx = namSx;
        this.maNCC = maNCC;
        this.maLoaiSP = maLoaiSP;
        this.maThuongHieu = maThuongHieu;
        this.image = image;
    }
    public sanPhamDTO() {
    }
    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public double getDongia() {
        return dongia;
    }

    public void setDongia(double dongia) {
        this.dongia = dongia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public Date getNamSx() {
        return namSx;
    }

    public void setNamSx(Date namSx) {
        this.namSx = namSx;
    }

    public String getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(String maNCC) {
        this.maNCC = maNCC;
    }

    public String getMaLoaiSP() {
        return maLoaiSP;
    }

    public void setMaLoaiSP(String maLoaiSP) {
        this.maLoaiSP = maLoaiSP;
    }

    public String getMaThuongHieu() {
        return maThuongHieu;
    }

    public void setMaThuongHieu(String maThuongHieu) {
        this.maThuongHieu = maThuongHieu;
    }
}
