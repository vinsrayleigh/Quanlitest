
package DTO;

import java.sql.Date;

public class KhuyenMaiDTO {
    private String makhuyenmai;
    private String tenkhuyenmai;
    private String maSanPham;
    private int giamgia;

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public int getGiamgia() {
        return giamgia;
    }

    public void setGiamgia(int giamgia) {
        this.giamgia = giamgia;
    }
    private Date ngaybatdau;
    private Date ngayketthuc;
    private String chitiet;

    public KhuyenMaiDTO(String makhuyenmai, String tenkhuyenmai, String maSanPham, int giamgia, Date ngaybatdau, Date ngayketthuc, String chitiet) {
        this.makhuyenmai = makhuyenmai;
        this.tenkhuyenmai = tenkhuyenmai;
        this.maSanPham = maSanPham;
        this.giamgia = giamgia;
        this.ngaybatdau = ngaybatdau;
        this.ngayketthuc = ngayketthuc;
        this.chitiet = chitiet;
    }


    public String getMakhuyenmai() {
        return makhuyenmai;
    }

    public void setMakhuyenmai(String makhuyenmai) {
        this.makhuyenmai = makhuyenmai;
    }

    public String getTenkhuyenmai() {
        return tenkhuyenmai;
    }

    public void setTenkhuyenmai(String tenkhuyenmai) {
        this.tenkhuyenmai = tenkhuyenmai;
    }

    public Date getNgaybatdau() {
        return ngaybatdau;
    }

    public void setNgaybatdau(Date ngaybatdau) {
        this.ngaybatdau = ngaybatdau;
    }

    public Date getNgayketthuc() {
        return ngayketthuc;
    }

    public void setNgayketthuc(Date ngayketthuc) {
        this.ngayketthuc = ngayketthuc;
    }

    public String getChitiet() {
        return chitiet;
    }

    public void setChitiet(String chitiet) {
        this.chitiet = chitiet;
    }

    @Override
    public String toString() {
        return "KhuyenMaiDTO{" + "makhuyenmai=" + makhuyenmai + ", tenkhuyenmai=" + tenkhuyenmai + ", ngaybatdau=" + ngaybatdau + ", ngayketthuc=" + ngayketthuc + ", chitiet=" + chitiet + '}';
    }
    
    
    
}
