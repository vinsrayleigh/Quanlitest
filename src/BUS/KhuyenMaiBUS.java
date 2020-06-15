/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.KhuyenMaiDAO;
import DTO.KhuyenMaiDTO;
import DTO.NhanVienDTO;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author phuon
 */
public class KhuyenMaiBUS {
    public ArrayList<KhuyenMaiDTO> list = new ArrayList<>();
    public KhuyenMaiBUS(){
        getData();
    }
    public void getData(){
        list = KhuyenMaiDAO.getKhuyenMai();
    }
    public KhuyenMaiDTO getKM(String maKM){
        for(KhuyenMaiDTO km: list){
            if(km.getMakhuyenmai().equals(maKM)){
                return km;
            }
        }
        return null;
    }
    public String bigNum(){
        int[] a = new int[list.size()];
        for(int i=0 ;i<list.size();i++){
            String temp = list.get(i).getMakhuyenmai().replaceAll("KM","");
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
            return "KM" + bigNum();
        } catch (Exception e) {
            return "KM1";
        }
    }
    public ArrayList<KhuyenMaiDTO> search(String value, String type, LocalDate ngaybatdau, LocalDate ngayketthuc) {
        ArrayList<KhuyenMaiDTO> result = new ArrayList<>();
        
        list.forEach((km) -> {
                switch(type) {
                   case "Tất cả":{
                       if(km.getMakhuyenmai().toLowerCase().contains(value)||
                               km.getTenkhuyenmai().toLowerCase().contains(value)||
                               Tool.removeAccent(km.getTenkhuyenmai().toLowerCase()).contains(value)||
                               km.getMaSanPham().toLowerCase().contains(value)||
                               String.valueOf(km.getGiamgia()).toLowerCase().contains(value)||
                               km.getChitiet().toLowerCase().contains(value)||
                               Tool.removeAccent(km.getChitiet().toLowerCase()).contains(value))
                           result.add(km);
                       break;
                    }
                    case "Mã Khuyến mãi":{
                        if(km.getMakhuyenmai().toLowerCase().contains(value.toLowerCase())){
                            result.add(km);
                        }
                   break;
               }
                    case "Tên Khuyến mãi":{
                        if(km.getTenkhuyenmai().toLowerCase().contains(value.toLowerCase())){
                            result.add(km);
           }
                        break;
                    }
                    case "Giảm Giá":{
                        if(String.valueOf(km.getGiamgia()).toLowerCase().contains(value.toLowerCase())){
                            result.add(km);
           }
                        break;
                    }
                    case "Ngày bắt đầu":{
                        if(km.getNgaybatdau().toString().toLowerCase().contains(value.toLowerCase())){
                            result.add(km);
                        }
                        break;
                    }
                    case "Ngày Kết thúc":{
                        if(km.getNgayketthuc().toString().toLowerCase().contains(value.toLowerCase())){
                            result.add(km);
                        }
                        break;
                    }
                    case "Chi tiết":{
                        if(km.getChitiet().toLowerCase().contains(value.toLowerCase())){
                            result.add(km);
                        }
                        break;
                    }
                }
            });
        for (int i = result.size() - 1; i >= 0; i--) {
            KhuyenMaiDTO km = result.get(i);
             ngaybatdau = km.getNgaybatdau().toLocalDate();
             ngayketthuc = km.getNgayketthuc().toLocalDate();
            LocalDate now = LocalDate.now();
            Boolean ngayKhongThoa = (now.isAfter(ngayketthuc)||now.isBefore(ngaybatdau));

            if (ngayKhongThoa) {
                result.remove(km);
            }
        }
        return result;
    }
}
