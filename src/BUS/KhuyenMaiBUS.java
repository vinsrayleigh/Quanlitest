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
    public ArrayList<KhuyenMaiDTO> search(String value, String type) {
        ArrayList<KhuyenMaiDTO> result = new ArrayList<>();
        list.forEach((km) -> {
           switch(type) {
               case "Tất cả":{
                   if(km.getMakhuyenmai().toLowerCase().contains(value)||
                           km.getTenkhuyenmai().toLowerCase().contains(value)||
                           km.getMaSanPham().toLowerCase().contains(value)||
                           String.valueOf(km.getGiamgia()).toLowerCase().contains(value)||
                           km.getChitiet().toLowerCase().contains(value))
                       result.add(km);
                   break;
               }
           }
        });
        for (int i = result.size() - 1; i >= 0; i--) {
            KhuyenMaiDTO km = result.get(i);
            LocalDate ngaybatdau = km.getNgaybatdau().toLocalDate();
            LocalDate ngayketthuc = km.getNgayketthuc().toLocalDate();
            LocalDate now = LocalDate.now();
            Boolean ngayKhongThoa = (now.isAfter(ngayketthuc)||now.isBefore(ngaybatdau));

            if (ngayKhongThoa) {
                result.remove(km);
            }
        }
        return result;
    }
}
