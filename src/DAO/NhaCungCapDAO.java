
package DAO;
import DTO.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class NhaCungCapDAO {
    public static boolean insertNhaCungCap(NhaCungCapDTO ncc){
        boolean result = false;
        try{
            Connection conn = MySQLConnUtils.getMySQLConnection();
            Statement st = conn.createStatement();
            String Sql = "INSERT into tblnhacungcap (mancc,tenncc,diachi,email)"
                    +"VALUES ('"+ncc.getMaNCC()+"',"
                    +"'"+ncc.getTenNCC()+"',"
                    +"'"+ncc.getDiaChi()+"',"
                    +"'"+ncc.getEmail()+"')";
            int row = st.executeUpdate(Sql);
            if(row>0) result = true;
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public static ArrayList<NhaCungCapDTO> getNhaCungCap(){
        ArrayList<NhaCungCapDTO> list = new ArrayList<>();
        try{
            Connection conn = MySQLConnUtils.getMySQLConnection();
            Statement st = conn.createStatement();
            String Sql ="SELECT * FROM tblnhacungcap";
            ResultSet rs = st.executeQuery(Sql);
            while(rs.next()){
                NhaCungCapDTO ncc = new NhaCungCapDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
                list.add(ncc);
            }
            conn.close();
        }catch(Exception e){
            
        }
        return list;
    }
    public static boolean updateNhaCungCap(NhaCungCapDTO ncc){
        boolean result= false;
        try {
            Connection conn = MySQLConnUtils.getMySQLConnection();
            Statement st = conn.createStatement();
            String Sql = "UPDATE tblnhacungcap "
                    +"SET tenncc = '"+ncc.getTenNCC()+"',"
                    +" diachi = '"+ncc.getDiaChi()+"',"
                    +" email = '"+ncc.getEmail()+"' "
                    +"WHERE mancc = '"+ncc.getMaNCC()+"';";
            int row = st.executeUpdate(Sql);
            if(row>0) result =true;
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
        
    }
    
    public static boolean DeleteNhaCungCap(NhaCungCapDTO ncc){
        boolean result= false;
        try {
            Connection conn = MySQLConnUtils.getMySQLConnection();
            Statement st = conn.createStatement();
            String Sql = "DELETE FROM tblnhacungcap "
                    +"WHERE mancc = '"+ncc.getMaNCC()+"';";
            int row = st.executeUpdate(Sql);
            if(row>0) result =true;
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
        
    }
    
    public static void main(String[] args) {
        NhaCungCapDTO ncc = new NhaCungCapDTO("ma","ten3","diachi","Email");
        NhaCungCapDAO.updateNhaCungCap(ncc);
    }
}
