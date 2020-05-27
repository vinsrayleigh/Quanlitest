
package DAO;

import DTO.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class CTKhuyenMaiDAO {
    
    public static boolean insertCTKhuyenMai(CTKhuyenMaiDTO sp){
        boolean result = false;
        try{
            Connection conn = MySQLConnUtils.getMySQLConnection();
            Statement st = conn.createStatement();
            String Sql = "INSERT into tblctkhuyenmai (makhuyenmai,masanpham,giamgia)"
                    +"VALUES ('"+sp.getMaKhuyenMai()+"',"
                    +"'"+sp.getMaSanPham()+"',"
                    +"'"+sp.getGiamGia()+"')";
            int row = st.executeUpdate(Sql);
            if(row>0) result = true;
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public static ArrayList<CTKhuyenMaiDTO> getCTKhuyenMai(){
        ArrayList<CTKhuyenMaiDTO> list = new ArrayList<>();
        try{
            Connection conn = MySQLConnUtils.getMySQLConnection();
            Statement st = conn.createStatement();
            String Sql ="SELECT * FROM tblctkhuyenmai";
            ResultSet rs = st.executeQuery(Sql);
            while(rs.next()){
                CTKhuyenMaiDTO sp = new CTKhuyenMaiDTO(rs.getString(1), rs.getString(2), rs.getInt(3));
                list.add(sp);
            }
            conn.close();
        }catch(Exception e){
            
        }
        return list;
    }
    public static boolean updateCTKhuyenMai(CTKhuyenMaiDTO sp){
        boolean result= false;
        try {
            Connection conn = MySQLConnUtils.getMySQLConnection();
            Statement st = conn.createStatement();
            String Sql = "UPDATE tblctkhuyenmai "
                    +"SET giamgia = '"+sp.getGiamGia()+"',"
                    +" masanpham = '"+sp.getMaSanPham()+"',"
                    +"WHERE makhuyenmai = '"+sp.getMaKhuyenMai()+"';";
            int row = st.executeUpdate(Sql);
            if(row>0) result =true;
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
        
    }
    
    public static boolean DeleteCTKhuyenMai(CTKhuyenMaiDTO sp){
        boolean result= false;
        try {
            Connection conn = MySQLConnUtils.getMySQLConnection();
            Statement st = conn.createStatement();
            String Sql = "DELETE FROM tblctkhuyenmai "
                    +"WHERE makhuyenmai = '"+sp.getMaKhuyenMai()+"';";
            int row = st.executeUpdate(Sql);
            if(row>0) result =true;
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
        
    }
}
