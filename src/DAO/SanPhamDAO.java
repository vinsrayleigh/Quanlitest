
package DAO;

import DTO.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class SanPhamDAO {
    
    public static boolean insertSanPham(SanPhamDTO sp){
        boolean result = false;
        try{
            Connection conn = MySQLConnUtils.getMySQLConnection();
            Statement st = conn.createStatement();
            String Sql = "INSERT into tblsanpham (masanpham,tensanpham,dongia,soluong,namsx,mota,mancc,maloaisp,mathuonghieu,image)"
                    +"VALUES ('"+sp.getMaSanPham()+"',"
                    +"'"+sp.getTenSanPham()+"',"
                    +"'"+sp.getDongia()+"',"
                    +"'"+sp.getSoLuong()+"',"
                    +"'"+sp.getNamSx()+"',"
                    +"'"+sp.getMota()+"',"
                    +"'"+sp.getMaNCC()+"',"
                    +"'"+sp.getMaLoaiSP()+"',"
                    +"'"+sp.getMaThuongHieu()+"',"
                    +"'"+sp.getImage()+"')";
            int row = st.executeUpdate(Sql);
            if(row>0) result = true;
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public static ArrayList<SanPhamDTO> getSanPham(){
        ArrayList<SanPhamDTO> list = new ArrayList<>();
        try{
            Connection conn = MySQLConnUtils.getMySQLConnection();
            Statement st = conn.createStatement();
            String Sql ="SELECT * FROM tblsanpham";
            ResultSet rs = st.executeQuery(Sql);
            while(rs.next()){
                SanPhamDTO sp = new SanPhamDTO(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getDate(5), rs.getString(7), rs.getString(8), rs.getString(9),rs.getString(10));
                sp.setMota(rs.getString(6));
                list.add(sp);
            }
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    public static boolean updateSanPham(SanPhamDTO sp){
        boolean result= false;
        try {
            Connection conn = MySQLConnUtils.getMySQLConnection();
            Statement st = conn.createStatement();
            String Sql = "UPDATE tblsanpham "
                    +"SET tensanpham = '"+sp.getTenSanPham()+"',"
                    +" dongia = '"+sp.getDongia()+"',"
                    +" soluong = '"+sp.getSoLuong()+"',"
                    +" namsx = '"+sp.getNamSx()+"',"
                    +" mota = '"+sp.getMota()+"',"
                    +" mancc = '"+sp.getMaNCC()+"',"
                    +" maloaisp = '"+sp.getMaLoaiSP()+"',"
                    +" mathuonghieu = '"+sp.getMaThuongHieu()+"',"
                    +" image = '"+sp.getImage()+"' "
                    +"WHERE masanpham = '"+sp.getMaSanPham()+"';";
            int row = st.executeUpdate(Sql);
            if(row>0) result =true;
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
        
    }
    
    public static boolean DeleteSanPham(SanPhamDTO sp){
        boolean result= false;
        try {
            Connection conn = MySQLConnUtils.getMySQLConnection();
            Statement st = conn.createStatement();
            String Sql = "DELETE FROM tblsanpham "
                    +"WHERE masanpham = '"+sp.getMaSanPham()+"';";
            int row = st.executeUpdate(Sql);
            if(row>0) result =true;
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
        
    }
    
   /* public static void main(String[] args) {
        sanPhamDTO sp = new sanPhamDTO("ma","ten","dongia","soluong","namsx","mancc","maloaisp","mathuonghieu");
        NhaCungCapDAO.updateSanPham(sp);
    }*/
}
