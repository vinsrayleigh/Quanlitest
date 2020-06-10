/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import BUS.Tool;
import DTO.CTHoaDonDTO;
import DTO.TaiKhoanDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author phuon
 */
public class ThongKeDAO {
    public static int getThuNhapNgay(String date1,String date2){
        int tong =0;
        try{
            Connection conn = MySQLConnUtils.getMySQLConnection();
            Statement st = conn.createStatement();
            String Sql ="SELECT SUM(tongtien) FROM tblhoadon WHERE ngaylap >='"+date1 +"' AND ngaylap <='"+date2+"'";
            ResultSet rs = st.executeQuery(Sql);
            while(rs.next()){
                System.out.println(rs.getInt(1));
            }
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return tong;
    }
    public static int getThuNhapThang(String date1,String date2,String nam){
        date1=nam+"-"+date1+"-1";
        date2=nam+"-"+date2+"-1";
        int tong =0;
        try{
            Connection conn = MySQLConnUtils.getMySQLConnection();
            Statement st = conn.createStatement();
            String Sql ="SELECT SUM(tongtien) FROM tblhoadon WHERE ngaylap >='"+date1 +"' AND ngaylap <'"+date2+"'";
            ResultSet rs = st.executeQuery(Sql);
            while(rs.next()){
                tong=rs.getInt(1);
            }
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return tong;
    }
    public static int getThuNhapNam(String nam){
        String date1=nam +"-01-01";
        String date2=nam+"-12-31";
        int tong =0;
        try{
            Connection conn = MySQLConnUtils.getMySQLConnection();
            Statement st = conn.createStatement();
            String Sql ="SELECT SUM(tongtien) FROM tblhoadon WHERE ngaylap >='"+date1 +"' AND ngaylap <'"+date2+"'";
            ResultSet rs = st.executeQuery(Sql);
            while(rs.next()){
                tong=rs.getInt(1);
            }
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return tong;
    }
    public static int getChiThang(String date1,String date2,String nam){
        date1=nam+"-"+date1+"-1";
        date2=nam+"-"+date2+"-1";
        int tong =0;
        try{
            Connection conn = MySQLConnUtils.getMySQLConnection();
            Statement st = conn.createStatement();
            String Sql ="SELECT SUM(tongtien) FROM tblphieunhap WHERE ngaylap >='"+date1 +"' AND ngaylap <='"+date2+"'";
            ResultSet rs = st.executeQuery(Sql);
            while(rs.next()){
                tong=rs.getInt(1);
            }
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return tong;
    }
    public static int getChiNam(String nam){
        String date1=nam +"-01-01";
        String date2=nam+"-12-31";
        int tong =0;
        try{
            Connection conn = MySQLConnUtils.getMySQLConnection();
            Statement st = conn.createStatement();
            String Sql ="SELECT SUM(tongtien) FROM tblphieunhap WHERE ngaylap >='"+date1 +"' AND ngaylap <='"+date2+"'";
            ResultSet rs = st.executeQuery(Sql);
            while(rs.next()){
                tong=rs.getInt(1);
            }
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return tong;
    }
    public static void main(String[] args) {
        ThongKeDAO.getThuNhapNgay("2020-06-08","2020-07-09");
        System.out.println(ThongKeDAO.getThuNhapThang(6+"", 6+1+"",2020+""));
    }
    public static ArrayList<CTHoaDonDTO> getCTHOADON(LocalDate date1,LocalDate date2,String sp,String nv,String KH){
        ArrayList<CTHoaDonDTO> result = new ArrayList<>();
        try {
            Connection conn = MySQLConnUtils.getMySQLConnection();
            Statement st = conn.createStatement();
            String Sql ="SELECT tblcthoadon.mahoadon, tblcthoadon.masanpham, tblcthoadon.soluong, tblcthoadon.thanhtien, tblcthoadon.dongia  FROM `tblcthoadon` , `tblhoadon` \n WHERE";
            int i=0;
            if(date1!=null&&!date1.toString().equals("")){
                Sql+=" tblhoadon.ngaylap >= '"+date1.toString()+"' \n";
                i++;
            }
            if(date2!=null&&!date2.toString().equals("")){
                if(i!=0) Sql+=" AND ";
                Sql+=" tblhoadon.ngaylap <= '"+date2.toString()+"' \n";
                i++;
            }
            if(!KH.equals("")){
                if(i!=0) Sql+=" AND ";
                Sql+=" tblhoadon.makhachhang CONTAIN " + KH;
                i++;
            }
            if(!sp.equals("")){
                if(i!=0) Sql+=" AND ";
                Sql+=" tblcthoadon.masanpham CONTAIN " + sp;
                i++;
            }
            if(!nv.equals("")){
                if(i!=0) Sql+=" AND ";
                Sql+=" tblhoadon.manhanvien CONTAIN" + nv;
                i++;
            }
            System.out.println(Sql);
            ResultSet rs = st.executeQuery(Sql);
            while(rs.next()){
                
                CTHoaDonDTO ctHD = new CTHoaDonDTO(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5));
                result.add(ctHD);
            }
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        if(result.size()>1){
            for(int i=result.size()-1;i>=1;i--){
                boolean k=true;
                for(int j=i-1;j>=0&&k;j--){
                    if(result.get(i).equal(result.get(j))){
                        result.remove(i);
                        i--;
                    }
                }
            }
        }
        return result;
    }
    //1654480
}
