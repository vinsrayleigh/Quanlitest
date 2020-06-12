
package DAO;

import DTO.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ThuongHieuDAO {
    
    public static boolean insertThuongHieu(ThuongHieuDTO sp){
        boolean result = false;
        try{
            Connection conn = MySQLConnUtils.getMySQLConnection();
            Statement st = conn.createStatement();
            String Sql = "INSERT into tblthuonghieu (mathuonghieu,tenthuonghieu,mota)"
                    +"VALUES ('"+sp.getMaThuongHieu()+"',"
                    +"'"+sp.getTenThuongHieu()+"',"
                    +"'"+sp.getMoTa()+"')";
            int row = st.executeUpdate(Sql);
            if(row>0) result = true;
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public static ArrayList<ThuongHieuDTO> getThuongHieu(){
        ArrayList<ThuongHieuDTO> list = new ArrayList<>();
        try{
            Connection conn = MySQLConnUtils.getMySQLConnection();
            Statement st = conn.createStatement();
            String Sql ="SELECT * FROM tblthuonghieu";
            ResultSet rs = st.executeQuery(Sql);
            while(rs.next()){
                ThuongHieuDTO sp = new ThuongHieuDTO(rs.getString(1), rs.getString(2), rs.getString(3));
                list.add(sp);
            }
            conn.close();
        }catch(Exception e){
            
        }
        return list;
    }
    public static boolean updateThuongHieu(ThuongHieuDTO sp){
        boolean result= false;
        try {
            Connection conn = MySQLConnUtils.getMySQLConnection();
            Statement st = conn.createStatement();
            String Sql = "UPDATE tblthuonghieu "
                    +"SET tenthuonghieu = '"+sp.getTenThuongHieu()+"',"
                    +" mota = '"+sp.getMoTa()+"' "
                    +"WHERE mathuonghieu = '"+sp.getMaThuongHieu()+"';";
            int row = st.executeUpdate(Sql);
            if(row>0) result =true;
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
        
    }
    
    public static boolean DeleteThuongHieu(ThuongHieuDTO sp){
        boolean result= false;
        try {
            Connection conn = MySQLConnUtils.getMySQLConnection();
            Statement st = conn.createStatement();
            String Sql = "DELETE FROM tblthuonghieu "
                    +"WHERE mathuonghieu = '"+sp.getMaThuongHieu()+"';";
            int row = st.executeUpdate(Sql);
            if(row>0) result =true;
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
        
    }
}
