
package DAO;

import DTO.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class NhanVienDAO {
    
    public static boolean insertNhanVien(NhanVienDTO sp){
        boolean result = false;
        try{
            Connection conn = MySQLConnUtils.getMySQLConnection();
            Statement st = conn.createStatement();
            String Sql = "INSERT into tblnhanvien (manhanvien, ten, ho, ngaysinh, gioitinh, sdt, maQuyen, luong, trangthai ) "
                    +"VALUES ('"+sp.getMaNhanVien()+"', "
                    +"'"+sp.getTenNhanVien()+"', "
                    +"'"+sp.getHoNhanVien()+"', "
                    +"'"+sp.getNgaySinh()+"', "
                    +"'"+sp.getGioiTinh()+"', "
                    +"'"+sp.getSdt()+"',"
                    +"'"+sp.getMaQuyen()+"', "
                    +"'"+sp.getLuong()+"', "
                    +"'"+sp.getTrangThai()+"')";
            int row = st.executeUpdate(Sql);
            if(row>0) result = true;
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public static ArrayList<NhanVienDTO> getNhanVien(){
        ArrayList<NhanVienDTO> list = new ArrayList<>();
        try{
            Connection conn = MySQLConnUtils.getMySQLConnection();
            Statement st = conn.createStatement();
            String Sql ="SELECT * FROM tblnhanvien";
            ResultSet rs = st.executeQuery(Sql);
            while(rs.next()){
                NhanVienDTO sp = new NhanVienDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getString(5), rs.getString(6),rs.getString(7), rs.getInt(8),rs.getInt(9));
                list.add(sp);
            }
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    public static boolean updateNhanVien(NhanVienDTO sp){
        boolean result= false;
        try {
            Connection conn = MySQLConnUtils.getMySQLConnection();
            Statement st = conn.createStatement();
            String Sql = "UPDATE tblnhanvien "
                    +"SET ho = '"+sp.getHoNhanVien()+"',"
                    +" ten = '"+sp.getTenNhanVien()+"',"
                    +" ngaysinh = '"+sp.getNgaySinh()+"',"
                    +" gioitinh = '"+sp.getGioiTinh()+"',"
                    +" sdt = '"+sp.getSdt()+"',"
                    +" maquyen = '"+sp.getMaQuyen()+"',"
                    +" luong = '"+sp.getLuong()+"',"
                    +" trangthai = '"+sp.getTrangThai()+"'"
                    +"WHERE manhanvien = '"+sp.getMaNhanVien()+"' ;";
            int row = st.executeUpdate(Sql);
            if(row>0) result =true;
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
        
    }
    
    public static boolean DeleteNhanVien(NhanVienDTO sp){
        boolean result= false;
        try {
            Connection conn = MySQLConnUtils.getMySQLConnection();
            Statement st = conn.createStatement();
            String Sql = "DELETE FROM tblnhanvien "
                    +"WHERE manhanvien = '"+sp.getMaNhanVien()+"';";
            int row = st.executeUpdate(Sql);
            if(row>0) result =true;
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
        
    }
    public static void main(String[] args) {
        NhanVienDTO nv  = new NhanVienDTO("2001", "Phuong Tay", "Le", new Date(0), "Nam","0393203261", "admin", 0,1);
        NhanVienDAO.insertNhanVien(nv);
    }
}
