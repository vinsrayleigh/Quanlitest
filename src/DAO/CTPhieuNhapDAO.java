
package DAO;

import DTO.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class CTPhieuNhapDAO {
    
    public static boolean insertCTPhieuNhap(CTPhieuNhapDTO sp){
        boolean result = false;
        try{
            Connection conn = MySQLConnUtils.getMySQLConnection();
            Statement st = conn.createStatement();
            String Sql = "INSERT into tblctphieunhap (maphieunhap,masanpham,soluong,dongia)"
                    +"VALUES ('"+sp.getMaPhieuNhap()+"',"
                    +"'"+sp.getMaSanPham()+"',"
                    +"'"+sp.getSoLuong()+"',"
                    +"'"+sp.getDongia()+"')";
            int row = st.executeUpdate(Sql);
            if(row>0) result = true;
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public static ArrayList<CTPhieuNhapDTO> getCTPhieuNhap(){
        ArrayList<CTPhieuNhapDTO> list = new ArrayList<>();
        try{
            Connection conn = MySQLConnUtils.getMySQLConnection();
            Statement st = conn.createStatement();
            String Sql ="SELECT * FROM tblctphieunhap";
            ResultSet rs = st.executeQuery(Sql);
            while(rs.next()){
                CTPhieuNhapDTO sp = new CTPhieuNhapDTO(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
                list.add(sp);
            }
            conn.close();
        }catch(Exception e){
            
        }
        return list;
    }
    public static boolean updateCTPhieuNhap(CTPhieuNhapDTO sp){
        boolean result= false;
        try {
            Connection conn = MySQLConnUtils.getMySQLConnection();
            Statement st = conn.createStatement();
            String Sql = "UPDATE tblctphieunhap "
                    +"SET masanpham = '"+sp.getMaSanPham()+"',"
                    +" soluong = '"+sp.getSoLuong()+"',"
                    +" dongia = '"+sp.getDongia()+"',"
                    +"WHERE maphieunhap = '"+sp.getMaPhieuNhap()+"';";
            int row = st.executeUpdate(Sql);
            if(row>0) result =true;
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
        
    }
    
    public static boolean DeleteCTPhieuNhap(CTPhieuNhapDTO sp){
        boolean result= false;
        try {
            Connection conn = MySQLConnUtils.getMySQLConnection();
            Statement st = conn.createStatement();
            String Sql = "DELETE FROM tblctphieunhap "
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
