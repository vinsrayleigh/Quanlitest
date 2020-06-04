
package DAO;

import DTO.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
public class HoaDonDAO {
    
    public static boolean insertHoaDon(HoaDonDTO sp){
        boolean result = false;
        try{
            Connection conn = MySQLConnUtils.getMySQLConnection();
            Statement st = conn.createStatement();
            String Sql = "INSERT into tblhoadon (mahoadon,manhanvien,makhachhang,tongtien,ngaylap,maKM,giamgia)"
                    +"VALUES ('"+sp.getMaHoaDon()+"',"
                    +"'"+sp.getMaNhanVien()+"',"
                    +"'"+sp.getMaKhachHang()+"',"
                    +"'"+sp.getTongTien()+"',"
                    +"'"+sp.getNgayLap()+"',"
                    +"'"+sp.getMaKM()+"',"
                    +"'"+sp.getGiamGia()+"')";
            int row = st.executeUpdate(Sql);
            if(row>0) result = true;
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public static ArrayList<HoaDonDTO> getHoaDon(){
        ArrayList<HoaDonDTO> list = new ArrayList<>();
        try{
            Connection conn = MySQLConnUtils.getMySQLConnection();
            Statement st = conn.createStatement();
            String Sql ="SELECT * FROM tblhoadon";
            ResultSet rs = st.executeQuery(Sql);
            while(rs.next()){
                HoaDonDTO sp = new HoaDonDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getDate(5), rs.getString(6), rs.getDouble(7));
                list.add(sp);
            }
            conn.close();
        }catch(Exception e){
            
        }
        return list;
    }
    public static boolean updateHoaDon(HoaDonDTO sp){
        boolean result= false;
        try {
            Connection conn = MySQLConnUtils.getMySQLConnection();
            Statement st = conn.createStatement();
            String Sql = "UPDATE tblhoadon "
                    +"SET manhanvien = '"+sp.getMaNhanVien()+"',"
                    +" makhachhang = '"+sp.getMaKhachHang()+"',"
                    +" tongtien = '"+sp.getTongTien()+"',"
                    +" ngaylap = '"+sp.getNgayLap()+"',"
                    +" makhuyenmai = '"+sp.getMaKM()+"',"
                    +" giamgia = '"+sp.getGiamGia()+"',"
                    +"WHERE mahoadon = '"+sp.getMaHoaDon()+"';";
            int row = st.executeUpdate(Sql);
            if(row>0) result =true;
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
        
    }
    
    public static boolean DeleteHoaDon(HoaDonDTO sp){
        boolean result= false;
        try {
            Connection conn = MySQLConnUtils.getMySQLConnection();
            Statement st = conn.createStatement();
            String Sql = "DELETE FROM tblhoadon "
                    +"WHERE mahoadon = '"+sp.getMaHoaDon()+"';";
            int row = st.executeUpdate(Sql);
            if(row>0) result =true;
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
        
    }
}
