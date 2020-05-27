
package DAO;

import DTO.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class TaiKhoanDAO {
    
    public static boolean insertTaiKhoan(TaiKhoanDTO sp){
        boolean result = false;
        try{
            Connection conn = MySQLConnUtils.getMySQLConnection();
            Statement st = conn.createStatement();
            String Sql = "INSERT into tbltaikhoan (manhanvien,password)"
                    +"VALUES ('"+sp.getMaNhanVien()+"',"
                    +"'"+sp.getPassword()+"')";
            int row = st.executeUpdate(Sql);
            if(row>0) result = true;
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public static ArrayList<TaiKhoanDTO> getTaiKhoan(){
        ArrayList<TaiKhoanDTO> list = new ArrayList<>();
        try{
            Connection conn = MySQLConnUtils.getMySQLConnection();
            Statement st = conn.createStatement();
            String Sql ="SELECT * FROM tbltaikhoan";
            ResultSet rs = st.executeQuery(Sql);
            while(rs.next()){
                TaiKhoanDTO sp = new TaiKhoanDTO(rs.getString(1), rs.getString(2));
                list.add(sp);
            }
            conn.close();
        }catch(Exception e){
            
        }
        return list;
    }
    public static boolean updateTaiKhoan(TaiKhoanDTO sp){
        boolean result= false;
        try {
            Connection conn = MySQLConnUtils.getMySQLConnection();
            Statement st = conn.createStatement();
            String Sql = "UPDATE tbltaikhoan "
                    +"SET password = '"+sp.getPassword()+"' "
                    +"WHERE manhanvien = '"+sp.getMaNhanVien()+"';";
            int row = st.executeUpdate(Sql);
            if(row>0) result =true;
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
        
    }
    
    public static boolean DeleteTaiKhoan(TaiKhoanDTO sp){
        boolean result= false;
        try {
            Connection conn = MySQLConnUtils.getMySQLConnection();
            Statement st = conn.createStatement();
            String Sql = "DELETE FROM tbltaikhoan "
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
        TaiKhoanDTO tk = new TaiKhoanDTO("admin", "123456");
        TaiKhoanDAO.insertTaiKhoan(tk);
    }
}
