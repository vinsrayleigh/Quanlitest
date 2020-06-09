/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import BUS.Tool;
import DTO.TaiKhoanDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

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
    //1654480
}
