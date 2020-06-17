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
import java.util.Arrays;

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
                return sp;
            }
        }
        return null;
    }
    public String bigNum(){
        int[] a = new int[list.size()];
        for(int i=0 ;i<list.size();i++){
            String temp = list.get(i).getMaSanPham().replaceAll("SP","");
            try {
                a[i]=Integer.parseInt(temp);
            } catch (Exception e) {
                a[i] = 0;
            }
            
        }
        Arrays.sort(a);
        return String.valueOf(a[a.length-1]+1);
    }
    public String getNextID() {
        try {
            return "SP" + bigNum();
        } catch (Exception e) {
            return "SP1";
        }
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
    public static SanPhamDTO getClone(SanPhamDTO sp,int sl,int dongia){
        SanPhamDTO clone = new SanPhamDTO();
        clone.setMaSanPham(sp.getMaSanPham());
        clone.setTenSanPham(sp.getTenSanPham());
        clone.setDongia(dongia);
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
                            Tool.removeAccent(sp.getTenSanPham().toLowerCase()).contains(value)||
                            String.valueOf(sp.getDongia()).toLowerCase().contains(value)||
                            sp.getNamSx().toString().toLowerCase().contains(value)||
                            sp.getMaLoaiSP().toLowerCase().contains(value)||
                            sp.getTenLoaiSP().toLowerCase().contains(value)||
                            Tool.removeAccent(sp.getTenLoaiSP().toLowerCase()).contains(value)||
                            sp.getMaThuongHieu().toLowerCase().contains(value)||
                            sp.getTenThuongHieu().toLowerCase().contains(value)||
                            Tool.removeAccent(sp.getTenThuongHieu().toLowerCase()).contains(value)||
                            sp.getMaNCC().toLowerCase().contains(value)||
                            sp.getTenNCC().toLowerCase().contains(value)||
                            Tool.removeAccent(sp.getTenNCC().toLowerCase()).contains(value))
                        result.add(sp);
                    break;
                }
                case "Nhà cung cấp":{
                    if(sp.getMaNCC().toLowerCase().contains(value.toLowerCase())){
                        result.add(sp);
                    }
                    break;
                }
            }
        });
        return  result;
    }
}
