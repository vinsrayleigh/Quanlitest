/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.HoaDonDAO;
import DTO.HoaDonDTO;
import DTO.KhachHangDTO;
import DTO.NhanVienDTO;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author phuon
 */
public class HoaDonBUS {

    public static ArrayList<HoaDonDTO> list;

    public HoaDonBUS() {
        getData();
    }
    public String bigNum(){
        int[] a = new int[list.size()];
        for(int i=0 ;i<list.size();i++){
            String temp = list.get(i).getMaHoaDon().replaceAll("HD","");
            try {
                a[i]=Integer.parseInt(temp);
            } catch (Exception e) {
                a[i] = 0;
            }
            
        }
        Arrays.sort(a);
        return String.valueOf(a[a.length-1]+1);
    }
    public String getNextMaHD() {
        try {
            return "HD" + bigNum();
        } catch (Exception e) {
            return "HD1";
        }
    }

    public HoaDonDTO getHD(String mahd){
        for(HoaDonDTO hd : list){
            if(hd.getMaHoaDon().equals(mahd)){
                return hd;
            }
        }
        return null;
    }
    public void getData() {
        list = HoaDonDAO.getHoaDon();
    }
    public ArrayList<HoaDonDTO> Search(String value,String type, LocalDate date1 , LocalDate date2,int gia1,int gia2){
        ArrayList<HoaDonDTO> result = new ArrayList<>();
        NhanVienBUS qlNhanVien= new NhanVienBUS();
        KhachHangBUS qlKhachHang = new KhachHangBUS();
//        "Tất cả", "Mã hóa đơn", "Nhân viên", "Khách hàng", "Ngày lập", "Tổng tiền", "Khuyến mãi"});
        list.forEach((hd) -> {
            NhanVienDTO NV = qlNhanVien.getNV(hd.getMaNhanVien());
            KhachHangDTO KH = qlKhachHang.getKH(hd.getMaKhachHang());
            if(NV==null){
                NV.setHoNhanVien("");
                NV.setTenNhanVien("");
            }
            if(KH==null){
                KH.setHoKhachHang("");
                KH.setTenKhachHang("");
            }
           switch(type){
               case "Tất cả":{
                   if(hd.getMaHoaDon().toLowerCase().contains(value.toLowerCase())
                           ||Tool.removeAccent(NV.getFullFame()).contains(Tool.removeAccent(value))
                           ||Tool.removeAccent(KH.getFullName()).contains(Tool.removeAccent(value))
                           ||hd.getMaKhachHang().toLowerCase().contains(value.toLowerCase())
                           ||hd.getMaNhanVien().toLowerCase().contains(value.toLowerCase())
                           ||hd.getMaKM().toLowerCase().contains(value.toLowerCase()))
                       result.add(hd);
                   break;
               }
               case "Khuyến mãi":{
                   if(hd.getMaKM().toLowerCase().contains(value.toLowerCase()))
                       result.add(hd);
                   break;
               }
               case "Khách hàng":{
                   if(Tool.removeAccent(KH.getFullName()).contains(Tool.removeAccent(value))
                           ||hd.getMaKhachHang().toLowerCase().contains(value.toLowerCase())){
                       result.add(hd);
                   }
                   break;
               }
               case "Mã hóa đơn":{
                   if(hd.getMaHoaDon().toLowerCase().contains(value.toLowerCase())){
                       result.add(hd);
                   }
                   break;
               }
               case "Nhân viên":{
                   if(Tool.removeAccent(NV.getFullFame()).contains(Tool.removeAccent(value))||
                           hd.getMaNhanVien().toLowerCase().contains(value.toLowerCase()))
                       result.add(hd);
                       break;
               }
           }
           
        });
         for (int i = result.size() - 1; i >= 0; i--) {
             HoaDonDTO hd = result.get(i);
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
