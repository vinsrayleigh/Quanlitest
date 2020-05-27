
package DAO;

import DTO.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class BaoHanhDAO {
    
    public static boolean insertBaoHanh(BaoHanhDTO sp){
        boolean result = false;
        try{
            Connection conn = MySQLConnUtils.getMySQLConnection();
            Statement st = conn.createStatement();
            String Sql = "INSERT into tblbaohanh (mahoadon,masanpham,ngaylap,thoihan)"
                    +"VALUES ('"+sp.getMaHoaDon()+"',"
                    +"'"+sp.getMaSanPham()+"',"
                    +"'"+sp.getNgayLap()+"',"
                    +"'"+sp.getThoiHan()+"')";
            int row = st.executeUpdate(Sql);
            if(row>0) result = true;
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public static ArrayList<BaoHanhDTO> getBaoHanh(){
        ArrayList<BaoHanhDTO> list = new ArrayList<>();
        try{
            Connection conn = MySQLConnUtils.getMySQLConnection();
            Statement st = conn.createStatement();
            String Sql ="SELECT * FROM tblbaohanh";
            ResultSet rs = st.executeQuery(Sql);
            while(rs.next()){
                BaoHanhDTO sp = new BaoHanhDTO(rs.getString(1), rs.getString(2), rs.getDate(3), rs.getInt(4));
                list.add(sp);
            }
            conn.close();
        }catch(Exception e){
            
        }
        return list;
    }
    public static boolean updateBaoHanh(BaoHanhDTO sp){
        boolean result= false;
        try {
            Connection conn = MySQLConnUtils.getMySQLConnection();
            Statement st = conn.createStatement();
            String Sql = "UPDATE tblbaohanh "
                    +"SET ngaylap = '"+sp.getNgayLap()+"',"
                    +" thoihan = '"+sp.getThoiHan()+"',"
                    +" mahoadon = '"+sp.getMaHoaDon()+"',"
                    +"WHERE masanpham = '"+sp.getMaSanPham()+"';";
            int row = st.executeUpdate(Sql);
            if(row>0) result =true;
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
        
    }
}
