/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import DTO.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
/**
 *
 * @author phuon
 */
public class QuyenDAO {
        public static boolean insertQuyen(QuyenDTO q){
        boolean result = false;
        try{
            Connection conn = MySQLConnUtils.getMySQLConnection();
            Statement st = conn.createStatement();
            String Sql = "INSERT into tblquyen (maQuyen,tenQuyen,chitiet)"
                    +"VALUES ('"+q.getMaQuyen()+"',"
                    +"'"+q.getTenQuyen()+"',"
                    +"'"+q.getChitiet()+"')";
            int row = st.executeUpdate(Sql);
            if(row>0) result = true;
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public static ArrayList<QuyenDTO> getQuyen(){
        ArrayList<QuyenDTO> list = new ArrayList<>();
        try{
            Connection conn = MySQLConnUtils.getMySQLConnection();
            Statement st = conn.createStatement();
            String Sql ="SELECT * FROM tblquyen";
            ResultSet rs = st.executeQuery(Sql);
            while(rs.next()){
                QuyenDTO q = new QuyenDTO(rs.getString(1), rs.getString(2), rs.getString(3));
                list.add(q);
            }
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    public static boolean updateQuyen(QuyenDTO q){
        boolean result= false;
        try {
            Connection conn = MySQLConnUtils.getMySQLConnection();
            Statement st = conn.createStatement();
            String Sql = "UPDATE tblquyen "
                    +"SET tenquyen = '"+q.getTenQuyen()+"',"
                    +" chitiet = '"+q.getChitiet()+"',"
                    +"WHERE maquyen = '"+q.getMaQuyen()+"';";
            int row = st.executeUpdate(Sql);
            if(row>0) result =true;
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
        
    }
    
    public static boolean DeleteQuyen(QuyenDTO sp){
        boolean result= false;
        try {
            Connection conn = MySQLConnUtils.getMySQLConnection();
            Statement st = conn.createStatement();
            String Sql = "DELETE FROM tblphieunhap "
                    +"WHERE maphieunhap = '"+sp.getMaQuyen()+"';";
            int row = st.executeUpdate(Sql);
            if(row>0) result =true;
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
        
    }
}
