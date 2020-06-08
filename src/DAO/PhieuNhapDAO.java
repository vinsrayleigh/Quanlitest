
package DAO;

import DTO.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class PhieuNhapDAO {
    
    public static boolean insertPhieuNhap(PhieuNhapDTO sp){
        boolean result = false;
        try{
            Connection conn = MySQLConnUtils.getMySQLConnection();
            Statement st = conn.createStatement();
            String Sql = "INSERT into tblphieunhap (maphieunhap,manhanvien,mancc,tongtien,ngaylap)"
                    +"VALUES ('"+sp.getMaPhieuNhap()+"',"
                    +"'"+sp.getMaNhanVien()+"',"
                    +"'"+sp.getMaNCC()+"',"
                    +"'"+sp.getTongTien()+"',"
                    +"'"+sp.getNgayLap()+"')";
            int row = st.executeUpdate(Sql);
            if(row>0) result = true;
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public static ArrayList<PhieuNhapDTO> getPhieuNhap(){
        ArrayList<PhieuNhapDTO> list = new ArrayList<>();
        try{
            Connection conn = MySQLConnUtils.getMySQLConnection();
            Statement st = conn.createStatement();
            String Sql ="SELECT * FROM tblphieunhap";
            ResultSet rs = st.executeQuery(Sql);
            while(rs.next()){
                PhieuNhapDTO sp = new PhieuNhapDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getDate(5));
                list.add(sp);
            }
            conn.close();
        }catch(Exception e){
            
        }
        return list;
    }
    public static boolean updatePhieuNhap(PhieuNhapDTO sp){
        boolean result= false;
        try {
            Connection conn = MySQLConnUtils.getMySQLConnection();
            Statement st = conn.createStatement();
            String Sql = "UPDATE tblphieunhap "
                    +"SET manhanvien = '"+sp.getMaNhanVien()+"',"
                    +" mancc = '"+sp.getMaNCC()+"',"
                    +" tongtien = '"+sp.getTongTien()+"',"
                    +" ngaylap = '"+sp.getNgayLap()+"',"
                    +"WHERE maphieunhap = '"+sp.getMaPhieuNhap()+"';";
            int row = st.executeUpdate(Sql);
            if(row>0) result =true;
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
        
    }
    
    public static boolean DeletePhieuNhap(PhieuNhapDTO sp){
        boolean result= false;
        try {
            Connection conn = MySQLConnUtils.getMySQLConnection();
            Statement st = conn.createStatement();
            String Sql = "DELETE FROM tblphieunhap "
                    +"WHERE maphieunhap = '"+sp.getMaPhieuNhap()+"';";
            int row = st.executeUpdate(Sql);
            if(row>0) result =true;
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
        
    }
}
