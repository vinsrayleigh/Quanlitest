package DTO;

import java.sql.Date;

public class KhachHangDTO {
    private String maKhachHang;
    private String tenKhachHang;
    private String hoKhachHang;
    private Date ngaySinh;
    private String sdt;
    private String loaiKhachHang;
    private int tichLuy;

    public KhachHangDTO(String maKhachHang, String tenKhachHang, String hoKhachHang, Date ngaySinh, String sdt, String loaiKhachHang, int tichLuy) {
        this.maKhachHang = maKhachHang;
        this.tenKhachHang = tenKhachHang;
        this.hoKhachHang = hoKhachHang;
        this.ngaySinh = ngaySinh;
        this.sdt = sdt;
        this.loaiKhachHang = loaiKhachHang;
        this.tichLuy = tichLuy;
    }

    public KhachHangDTO() {
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }
    public String getFullName(){
        return hoKhachHang+" "+tenKhachHang;
    }
    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getHoKhachHang() {
        return hoKhachHang;
    }

    public void setHoKhachHang(String hoKhachHang) {
        this.hoKhachHang = hoKhachHang;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getLoaiKhachHang() {
        return loaiKhachHang;
    }

    public void setLoaiKhachHang(String loaiKhachHang) {
        this.loaiKhachHang = loaiKhachHang;
    }

    public int getTichLuy() {
        return tichLuy;
    }

    public void setTichLuy(int tichLuy) {
        this.tichLuy = tichLuy;
    }
}
