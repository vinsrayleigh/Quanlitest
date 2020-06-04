
package DAO;

import DTO.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class KhuyenMaiDAO {
    
    public static boolean insertKhuyenMai(KhuyenMaiDTO sp){
        boolean result = false;
        try{
            Connection conn = MySQLConnUtils.getMySQLConnection();
            Statement st = conn.createStatement();
            String Sql = "INSERT into tblkhuyenmai (makhuyenmai,tenkhuyenmai,masanpham,giamgia,thoigianbatdau,thoigianketthuc,chitiet)"
                    +"VALUES ('"+sp.getMakhuyenmai()+"',"
                    +"'"+sp.getTenkhuyenmai()+"',"
                    +"'"+sp.getMaSanPham()+"',"
                    +"'"+sp.getGiamgia()+"',"
                    +"'"+sp.getNgaybatdau()+"',"
                    +"'"+sp.getNgayketthuc()+"',"
                    +"'"+sp.getChitiet()+"')";
            int row = st.executeUpdate(Sql);
            if(row>0) result = true;
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public static ArrayList<KhuyenMaiDTO> getKhuyenMai(){
        ArrayList<KhuyenMaiDTO> list = new ArrayList<>();
        try{
            Connection conn = MySQLConnUtils.getMySQLConnection();
            Statement st = conn.createStatement();
            String Sql ="SELECT * FROM tblkhuyenmai";
            ResultSet rs = st.executeQuery(Sql);
            while(rs.next()){
                KhuyenMaiDTO sp = new KhuyenMaiDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getDate(5), rs.getDate(6), rs.getString(7));
                list.add(sp);
            }
            conn.close();
        }catch(Exception e){
            
        }
        return list;
    }
    public static boolean updateKhuyenMai(KhuyenMaiDTO sp){
        boolean result= false;
        try {
            Connection conn = MySQLConnUtils.getMySQLConnection();
            Statement st = conn.createStatement();
            String Sql = "UPDATE tblkhuyenmai "
                    +"SET tenkhuyenmai = '"+sp.getTenkhuyenmai()+"',"
                    +" thoigianbatdau = '"+sp.getNgaybatdau()+"',"
                    +" thoigianketthuc = '"+sp.getNgayketthuc()+"',"
                    +" chitiet = '"+sp.getChitiet()+"',"
                    +"WHERE makhuyenmai = '"+sp.getMakhuyenmai()+"';";
            int row = st.executeUpdate(Sql);
            if(row>0) result =true;
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
        
    }
    
    public static boolean DeleteKhuyenMai(KhuyenMaiDTO sp){
        boolean result= false;
        try {
            Connection conn = MySQLConnUtils.getMySQLConnection();
            Statement st = conn.createStatement();
            String Sql = "DELETE FROM tblkhuyenmai "
                    +"WHERE makhuyenmai = '"+sp.getMakhuyenmai()+"';";
            int row = st.executeUpdate(Sql);
            if(row>0) result =true;
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
        
    }
}
