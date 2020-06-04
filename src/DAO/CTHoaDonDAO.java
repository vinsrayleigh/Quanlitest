
package DAO;

import DTO.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class CTHoaDonDAO {
    
    public static boolean insertCTHD(CTHoaDonDTO sp){
        boolean result = false;
        try{
            Connection conn = MySQLConnUtils.getMySQLConnection();
            Statement st = conn.createStatement();
            String Sql = "INSERT into tblcthoadon (mahoadon,masanpham,soluong,thanhtien,dongia)"
                    +"VALUES ('"+sp.getMaHoaDon()+"',"
                    +"'"+sp.getMaSanPham()+"',"
                    +"'"+sp.getSoLuong()+"',"
                    +"'"+sp.getThanhTien()+"',"
                    +"'"+sp.getDonGia()+"')";
            int row = st.executeUpdate(Sql);
            if(row>0) result = true;
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public static ArrayList<CTHoaDonDTO> getCTHoaDon(){
        ArrayList<CTHoaDonDTO> list = new ArrayList<>();
        try{
            Connection conn = MySQLConnUtils.getMySQLConnection();
            Statement st = conn.createStatement();
            String Sql ="SELECT * FROM tblcthoadon";
            ResultSet rs = st.executeQuery(Sql);
            while(rs.next()){
                CTHoaDonDTO sp = new CTHoaDonDTO(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5));
                list.add(sp);
            }
            conn.close();
        }catch(Exception e){
            
        }
        return list;
    }
    public static boolean updateCTHoaDon(CTHoaDonDTO sp){
        boolean result= false;
        try {
            Connection conn = MySQLConnUtils.getMySQLConnection();
            Statement st = conn.createStatement();
            String Sql = "UPDATE tblcthoadon "
                    +"SET masanpham = '"+sp.getMaSanPham()+"',"
                    +" soluong = '"+sp.getSoLuong()+"',"
                    +" thanhtien = '"+sp.getThanhTien()+"',"
                    +" dongia = '"+sp.getDonGia()+"',"
                    +"WHERE mahoadon = '"+sp.getMaHoaDon()+"';";
            int row = st.executeUpdate(Sql);
            if(row>0) result =true;
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
        
    }
    
    public static boolean DeleteCTHoaDon(CTHoaDonDTO sp){
        boolean result= false;
        try {
            Connection conn = MySQLConnUtils.getMySQLConnection();
            Statement st = conn.createStatement();
            String Sql = "DELETE FROM tblcthoadon "+"WHERE mahoadon = '"+sp.getMaHoaDon()+"';";
            int row = st.executeUpdate(Sql);
            if(row>0) result =true;
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
        
    }
}
