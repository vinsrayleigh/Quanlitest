
package DAO;

import DTO.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class LoaiSPDAO {
    
    public static boolean insertMaSP(LoaiSPDTO sp){
        boolean result = false;
        try{
            Connection conn = MySQLConnUtils.getMySQLConnection();
            Statement st = conn.createStatement();
            String Sql = "INSERT into tblloaisanpham (maloaisp,tenloai)"
                    +"VALUES ('"+sp.getMaLoaiSP()+"',"
                    +"'"+sp.getTenLoaiSP()+"')";
            int row = st.executeUpdate(Sql);
            if(row>0) result = true;
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public static ArrayList<LoaiSPDTO> getMaSP(){
        ArrayList<LoaiSPDTO> list = new ArrayList<>();
        try{
            Connection conn = MySQLConnUtils.getMySQLConnection();
            Statement st = conn.createStatement();
            String Sql ="SELECT * FROM tblloaisanpham";
            ResultSet rs = st.executeQuery(Sql);
            while(rs.next()){
                LoaiSPDTO sp = new LoaiSPDTO(rs.getString(1), rs.getString(2));
                list.add(sp);
            }
            conn.close();
        }catch(Exception e){
            
        }
        return list;
    }
    public static boolean updateMaSP(LoaiSPDTO sp){
        boolean result= false;
        try {
            Connection conn = MySQLConnUtils.getMySQLConnection();
            Statement st = conn.createStatement();
            String Sql = "UPDATE tblloaisanpham "
                    +"SET tenloai = '"+sp.getTenLoaiSP()+"',"
                    +"WHERE maloaisp = '"+sp.getMaLoaiSP()+"';";
            int row = st.executeUpdate(Sql);
            if(row>0) result =true;
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
        
    }
    
    public static boolean DeleteMaSP(LoaiSPDTO sp){
        boolean result= false;
        try {
            Connection conn = MySQLConnUtils.getMySQLConnection();
            Statement st = conn.createStatement();
            String Sql = "DELETE FROM tblloaisanpham "
                    +"WHERE maloaisp = '"+sp.getMaLoaiSP()+"';";
            int row = st.executeUpdate(Sql);
            if(row>0) result =true;
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
        
    }
}
