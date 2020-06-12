
package DAO;

import DTO.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class KhachHangDAO {
    
    public static boolean insertKhachHang(KhachHangDTO sp){
        boolean result = false;
        try{
            Connection conn = MySQLConnUtils.getMySQLConnection();
            Statement st = conn.createStatement();
            String Sql = "INSERT into tblkhachhang (makhachhang, hokhachhang, tenkhachhang, ngaysinh, sodienthoai, loaikhachhang, tichluy)"
                    +"VALUES ('"+sp.getMaKhachHang()+"',"
                    +"'"+sp.getHoKhachHang()+"',"
                    +"'"+sp.getTenKhachHang()+"',"
                    +"'"+sp.getNgaySinh()+"',"
                    +"'"+sp.getSdt()+"',"
                    +"'"+sp.getLoaiKhachHang()+"',"
                    +"'"+sp.getTichLuy()+"')";
            int row = st.executeUpdate(Sql);
            if(row>0) result = true;
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public static ArrayList<KhachHangDTO> getKhachHang(){
        ArrayList<KhachHangDTO> list = new ArrayList<>();
        try{
            Connection conn = MySQLConnUtils.getMySQLConnection();
            Statement st = conn.createStatement();
            String Sql ="SELECT * FROM tblkhachhang";
            ResultSet rs = st.executeQuery(Sql);
            while(rs.next()){
                KhachHangDTO sp = new KhachHangDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getString(5), rs.getString(6), rs.getInt(7));
                list.add(sp);
            }
            conn.close();
        }catch(Exception e){
            
        }
        return list;
    }
    public static boolean updateKhachHang(KhachHangDTO sp){
        boolean result= false;
        try {
            Connection conn = MySQLConnUtils.getMySQLConnection();
            Statement st = conn.createStatement();
            String Sql = "UPDATE tblkhachhang "
                    +"SET hokhachang = '"+sp.getHoKhachHang()+"',"
                    +" tenkhachhang = '"+sp.getTenKhachHang()+"',"
                    +" ngaysinh = '"+sp.getNgaySinh()+"',"
                    +" sodienthoai = '"+sp.getSdt()+"',"
                    +" loaikhachhang = '"+sp.getLoaiKhachHang()+"',"
                    +" tichluy = '"+sp.getTichLuy()+"',"
                    +"WHERE makhachhang = '"+sp.getMaKhachHang()+"';";
            int row = st.executeUpdate(Sql);
            if(row>0) result =true;
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
        
    }
    
    public static boolean DeleteKhachHang(KhachHangDTO sp){
        boolean result= false;
        try {
            Connection conn = MySQLConnUtils.getMySQLConnection();
            Statement st = conn.createStatement();
            String Sql = "DELETE FROM tblkhachhang "
                    +"WHERE makhachhang = '"+sp.getMaKhachHang()+"';";
            int row = st.executeUpdate(Sql);
            if(row>0) result =true;
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
        
    }
}
