/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.ThuongHieuDAO;
import java.util.ArrayList;
import DTO.*;
/**
 *
 * @author phuon
 */
public class ThuongHieuBUS {
     ArrayList<ThuongHieuDTO> list;
    public ThuongHieuBUS(){
        getData();
    }
    public void getData(){
        list = ThuongHieuDAO.getThuongHieu();
    }
    public ThuongHieuDTO getTH(String math){
        for(ThuongHieuDTO th:list){
            if(th.getMaThuongHieu().equals(math))
                return th;
        }
        return null;
    }
    public ArrayList<ThuongHieuDTO> getTH(){
        return list;
    }
    public ThuongHieuDTO getThuongHieu(String maThuongHieu){
        for(ThuongHieuDTO th:list){
            if(th.getMaThuongHieu().equals(maThuongHieu)){
                return th;
            }
        }
        return null;
    }
    public String getNextID() {
        //throw new UnsupportedOperationException("Not supported yet.");
        try {
            String temp = list.get(list.size() - 1).getMaThuongHieu().replaceAll("TH", "");
            return "TH" + String.valueOf(Integer.parseInt(temp)+1);
        } catch (Exception e) {
            return "TH0001";
        }
    }//To change body of generated methods, choose Tools | Templates.j
    public ArrayList<ThuongHieuDTO> search(String value, String type) {
       ArrayList<ThuongHieuDTO> result = new ArrayList<>();
       
       list.forEach((th) ->{
           if(type.equals("Tất cả")){
               if(th.getMaThuongHieu().toLowerCase().contains(value.toLowerCase())
                       || th.getTenThuongHieu().toLowerCase().contains(value.toLowerCase())
                       || Tool.removeAccent(th.getTenThuongHieu().toLowerCase()).contains(value.toLowerCase())
                       || th.getMoTa().toLowerCase().contains(value.toLowerCase())
                       || Tool.removeAccent(th.getMoTa().toLowerCase()).contains(value.toLowerCase())){
                   result.add(th);
               }
           }else{
               switch(type) {
                   case "Mã Thương hiệu": {
                       if(th.getMaThuongHieu().toLowerCase().contains(value.toLowerCase())){
                           result.add(th);
                       }
                       break;
                   }
                   case "Tên Thương hiệu": {
                       if(th.getTenThuongHieu().toLowerCase().contains(value.toLowerCase())){
                           result.add(th);
                       }
                       break;
                   }
                   case "Mô tả": {
                       if(th.getMoTa().toLowerCase().contains(value.toLowerCase())){
                           result.add(th);
                       }
                       break;
                   }
               }
           }
       });
        return result;
   }
}
