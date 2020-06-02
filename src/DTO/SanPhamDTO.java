package DTO;

import BUS.LoaiSPBUS;
import BUS.NhaCungCapBUS;
import BUS.ThuongHieuBUS;
import java.sql.Date;

public class SanPhamDTO {
    private String maSanPham="";
    private String tenSanPham="";
    private int dongia;
    private int soLuong;
    private Date namSx;
    private String maNCC="";
    private String maLoaiSP="";
    private String maThuongHieu="";
    private String image="";
    private String mota="";

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public SanPhamDTO(String maSanPham, String tenSanPham, int dongia, int soLuong, Date namSx, String maNCC, String maLoaiSP, String maThuongHieu, String image) {
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
    public SanPhamDTO() {
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

    public int getDongia() {
        return dongia;
    }

    public void setDongia(int dongia) {
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
    public String getTenLoaiSP(){
        return new LoaiSPBUS().getLoaiSPDTO(this.getMaLoaiSP()).getTenLoaiSP();
    }
    public String getTenNCC(){
        return new NhaCungCapBUS().getNCC(maNCC).getTenNCC();
    }
    public String getTenThuongHieu(){
        return new ThuongHieuBUS().getTH(maThuongHieu).getTenThuongHieu();
    }
}
