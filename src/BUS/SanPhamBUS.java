/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.SanPhamDAO;
import DTO.*;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author phuon
 */
public class SanPhamBUS {
    public ArrayList<SanPhamDTO> list = new ArrayList<>();
    public SanPhamBUS(){
        getData();
    }
    public SanPhamDTO getSanPham(String masp){
        for(SanPhamDTO sp: list){
            if(sp.getMaSanPham().equals(masp)){
                System.out.println("found");
                return sp;
            }
        }
        return null;
    }
//    private String maSanPham="";
//    private String tenSanPham="";
//    private int dongia;
//    private int soLuong;
//    private Date namSx;
//    private String maNCC="";
//    private String maLoaiSP="";
//    private String maThuongHieu="";
//    private String image="";
//    private String mota="";
    public static SanPhamDTO getClone(SanPhamDTO sp,int sl){
        SanPhamDTO clone = new SanPhamDTO();
        clone.setMaSanPham(sp.getMaSanPham());
        clone.setTenSanPham(sp.getTenSanPham());
        clone.setDongia(sp.getDongia());
        clone.setSoLuong(sl);
        clone.setNamSx(sp.getNamSx());
        clone.setMaNCC(sp.getMaNCC());
        clone.setMaLoaiSP(sp.getMaLoaiSP());
        clone.setMaThuongHieu(sp.getMaThuongHieu());
        clone.setImage(sp.getImage());
        clone.setMota(sp.getMota());
        return clone;
    }
    public void getData(){
        list = SanPhamDAO.getSanPham();
    };
    //String maSanPham, String tenSanPham, int dongia, int soLuong, Date namSx, String maNCC, String maLoaiSP, String maThuongHieu, String image
    public ArrayList<SanPhamDTO> search(String value, String type, int soluong1, int soluong2, float gia1, float gia2) {
        
        ArrayList<SanPhamDTO> result = new ArrayList<>();
        list.forEach((sp) -> {
            switch(type){
                case "Tất cả":{
                    if(sp.getMaSanPham().toLowerCase().contains(value)||
                            sp.getTenSanPham().toLowerCase().contains(value)||
                            String.valueOf(sp.getDongia()).toLowerCase().contains(value)||
                            sp.getNamSx().toString().toLowerCase().contains(value)||
                            sp.getMaLoaiSP().toLowerCase().contains(value)||
                            sp.getTenLoaiSP().toLowerCase().contains(value)||
                            sp.getMaThuongHieu().toLowerCase().contains(value)||
                            sp.getTenThuongHieu().toLowerCase().contains(value)||
                            sp.getMaNCC().toLowerCase().contains(value)||
                            sp.getTenNCC().toLowerCase().contains(value))
                        result.add(sp);
                    break;
                }
            }
        });
        return  result;
    }
}
