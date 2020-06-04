
package BUS;

import DTO.*;
import DAO.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class KhachHangBUS {
    
    public ArrayList<KhachHangDTO> list;
    
    public KhachHangBUS(){
        getData();
    }
    //"STT","Mã khách hàng","Họ khách hàng","Tên khách hàng","Ngày sinh","Số điện thoại","Loại khách hàng","tích lũy"
    public static Boolean equals(KhachHangDTO kh1,KhachHangDTO kh2){
        if(!kh1.getMaKhachHang().equals(kh2.getMaKhachHang()))
        {
            System.out.println("1");
            return false;
        }
        if(!kh1.getHoKhachHang().equalsIgnoreCase(kh2.getHoKhachHang()))
        {
            System.out.println("2");
            return false;
        }
        if(!kh1.getTenKhachHang().equals(kh2.getTenKhachHang()))
        {
            System.out.println("3");
            return false;
        }
        if(!kh1.getNgaySinh().equals(kh2.getNgaySinh()))
        {
            System.out.println("4");
            return false;
        }
        if(!kh1.getSdt().equals(kh2.getSdt()))
        {
            System.out.println("5");
            return false;
        }
        if(!kh1.getLoaiKhachHang().equals(kh2.getLoaiKhachHang()))
        {
            System.out.println("6");
            return false;
        }
        if(kh1.getTichLuy() != kh2.getTichLuy()){
            System.out.println("7");
            return false;
        }
        return true;
    }
    
    public void getData(){
        
        list = KhachHangDAO.getKhachHang();
        
    }
    
    public void add(KhachHangDTO kh){
        list.add(kh);
        KhachHangDAO.insertKhachHang(kh);
    }
    
    public KhachHangDTO getKH(String MaKH){
        for(KhachHangDTO kh :list)
        {
            if(kh.getMaKhachHang().equals(MaKH))
                return kh; 
        }
        return null;
    }
   
    public String getNextID(){
        long ma;
        try {
            ma = Integer.parseInt(list.get(list.size() -2 ).getMaKhachHang());
            System.out.println(ma);
            ma++;
        } catch (Exception e) {
            ma = 200;
        }
        return "" + ma;
    }
    
    public void xoa(String maString){
        
        KhachHangDTO a = getKH(maString);
        list.remove(a);
        KhachHangDAO.DeleteKhachHang(a);
        
    }
    
    public ArrayList<KhachHangDTO> getDsKH(){
        return list;
    }
    
    public ArrayList<KhachHangDTO> search(String value, String type, LocalDate Ngay_1, LocalDate Ngay_2){
        
        ArrayList<KhachHangDTO> result = new ArrayList<>();
        
        list.forEach((kh) -> {
                if(type.equals("Tất cả")){
                    if(kh.getMaKhachHang().toLowerCase().contains(value.toLowerCase())
                            || kh.getHoKhachHang().toLowerCase().contains(value.toLowerCase())
                            || kh.getTenKhachHang().toLowerCase().contains(value.toLowerCase())
                            || kh.getNgaySinh().toString().toLowerCase().contains(value.toLowerCase())
                            || kh.getSdt().toLowerCase().contains(value.toLowerCase())
                            || kh.getLoaiKhachHang().toLowerCase().contains(value.toLowerCase())
                            || String.valueOf(kh.getTichLuy()).toLowerCase().contains(value.toLowerCase())){
                        result.add(kh);
                        }
                    }else {
                        switch(type)
                        {
                            case "Mã Khách Hàng":{
                                if(kh.getHoKhachHang().toLowerCase().contains(value.toLowerCase())){
                                    result.add(kh);
                                }
                                break;
                            }
                            case "Họ Khách Hàng":{
                                if(kh.getHoKhachHang().toLowerCase().contains(value.toLowerCase())){
                                    result.add(kh);
                                }
                                break;
                            }
                            case "Tên Khách Hàng":{
                                if(kh.getTenKhachHang().toLowerCase().contains(value.toLowerCase())){
                                    result.add(kh);
                                }
                                break;
                            }
                            case "Ngày Sinh":{
                                if(kh.getNgaySinh().toString().toLowerCase().contains(value.toLowerCase())){
                                    result.add(kh);
                                }
                                break;
                            }
                            case "Số điện thoại":{
                                if(kh.getSdt().toLowerCase().contains(value.toLowerCase())){
                                    result.add(kh);
                                }
                                break;
                            }
                            case "Loại Khách Hàng":{
                                if(String.valueOf(kh.getLoaiKhachHang()).toLowerCase().contains(value.toLowerCase())){
                                    result.add(kh);
                                }
                                break;
                            }
                            case "Tích lũy":{
                                if(String.valueOf(kh.getTichLuy()).toLowerCase().contains(value.toLowerCase())){
                                    result.add(kh);
                                }
                                break;
                            }
                        }
                    }
                });
                    
                    
        for(int i = result.size() - 1; i>=0; i --){
            KhachHangDTO kh = result.get(i);
            LocalDate ngaysinh = kh.getNgaySinh().toLocalDate();
            
            Boolean ngaykhongthoa = (Ngay_1 != null && ngaysinh.isBefore(Ngay_1)) || (Ngay_2 != null && ngaysinh.isAfter(Ngay_2));
            
            if(ngaykhongthoa){
                result.remove(kh);
            }
        }            
        return result;
        }
}
