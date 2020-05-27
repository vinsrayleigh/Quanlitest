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
public class TaiKhoanDTO {
    private String maNhanVien;
    private String Password;

    public TaiKhoanDTO(String maNhanVien, String Password) {
        this.maNhanVien = maNhanVien;
        this.Password = Password;
    }

    public TaiKhoanDTO() {
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }
}
