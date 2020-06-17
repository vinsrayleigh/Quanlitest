/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.HoaDonDAO;
import DAO.PhieuNhapDAO;
import DTO.HoaDonDTO;
import DTO.PhieuNhapDTO;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author phuon
 */
public class PhieuNhapBUS {

    public  ArrayList<PhieuNhapDTO> list;

    public PhieuNhapBUS() {
        getData();
    }
    public String bigNum(){
        int[] a = new int[list.size()];
        for(int i=0 ;i<list.size();i++){
            String temp = list.get(i).getMaPhieuNhap().replaceAll("PN","");
            try {
                a[i]=Integer.parseInt(temp);
            } catch (Exception e) {
                a[i] = 0;
            }
            
        }
        Arrays.sort(a);
        return String.valueOf(a[a.length-1]+1);
    }
    public String getNextMaPN() {
        try {
            return "PN" + bigNum();
        } catch (Exception e) {
            return "PN1";
        }
    }   
    public PhieuNhapDTO getPN(String maPN){
        for(PhieuNhapDTO ph:list){
            if(ph.getMaPhieuNhap().equals(maPN))
                return ph;
        }
        return null;
    }
    public void getData() {
        list = PhieuNhapDAO.getPhieuNhap();
    }
    public ArrayList<PhieuNhapDTO> Search(String value,String type, LocalDate date1 , LocalDate date2,int gia1,int gia2){
        ArrayList<PhieuNhapDTO> result = new ArrayList<>();
        NhanVienBUS qlNhanVien = new NhanVienBUS();
        NhaCungCapBUS qlNCC = new NhaCungCapBUS();
//        "Tất cả", "Mã hóa đơn", "Nhân viên", "Khách hàng", "Ngày lập", "Tổng tiền", "Khuyến mãi"});
        for(PhieuNhapDTO hd: list) {
            if(value.equals("")){
                result=this.list;
            }else
           switch(type){
               case "Tất cả":{
                   if(hd.getMaPhieuNhap().toLowerCase().contains(value.toLowerCase())
                           ||hd.getMaNhanVien().toLowerCase().contains(value.toLowerCase())
                           ||hd.getMaNCC().toLowerCase().contains(value.toLowerCase())
                           ||qlNCC.getNCC(hd.getMaNCC()).getTenNCC().toLowerCase().contains(value.toLowerCase())
                           ||hd.getNgayLap().toString().toLowerCase().contains(value.toLowerCase())
                           ||String.valueOf(hd.getTongTien()).toLowerCase().contains(value.toLowerCase())
                           ||Tool.removeAccent(qlNhanVien.getNV(hd.getMaNhanVien()).getFullFame()).contains(Tool.removeAccent(value)))
                    
                       result.add(hd);
                   break;
               }
               
               case "Mã Phiếu Nhập":{
                   if(hd.getMaPhieuNhap().toLowerCase().contains(value.toLowerCase())){
                       result.add(hd);
                   }
                   break;
               }
               case "Mã NCC":{
                   if(hd.getMaNCC().toLowerCase().contains(value.toLowerCase())){
                       result.add(hd);
                   }
                   break;
               }
               case "Ngày Lập":{
                   if(hd.getNgayLap().toString().toLowerCase().contains(value.toLowerCase())){
                       result.add(hd);
                   }
                   break;
               }
               case "Tổng Tiền":{
                   if(String.valueOf(hd.getTongTien()).toLowerCase().contains(value.toLowerCase())){
                       result.add(hd);
                   }
                   break;
               }
               
               case "Nhân viên":{
                   if(Tool.removeAccent(qlNhanVien.getNV(hd.getMaNhanVien()).getFullFame()).contains(Tool.removeAccent(value))||
                           hd.getMaNhanVien().toLowerCase().contains(value.toLowerCase()))
                       result.add(hd);
                       break;
               }
           }
           
        }
         for (int i = result.size() - 1; i >= 0; i--) {
             PhieuNhapDTO hd = result.get(i);
            LocalDate ngaysinh = hd.getNgayLap().toLocalDate();
            boolean ngayKhongThoa = (date1 != null && ngaysinh.isBefore(date1)) || (date2 != null && ngaysinh.isAfter(date2));
            Boolean giaKhongThoa =(gia1!=0&&hd.getTongTien()<gia1)||(gia2!=0&&hd.getTongTien()>gia2);
            if (ngayKhongThoa||giaKhongThoa) {
                result.remove(hd);
            }
        }
        return result;
    }
}
