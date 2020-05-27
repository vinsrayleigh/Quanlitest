/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;
import DTO.*;
import DAO.*;
import java.util.ArrayList;
/**
 *
 * @author phuon
 */
public class TaiKhoanBUS {
    public static ArrayList<TaiKhoanDTO> list;
    public static String role="nhanvien";

    public TaiKhoanBUS() {
        role = "nhanVien";
        list = new ArrayList<>();
        getData();
    }
    
    public static boolean check(String TK,String password){
        TaiKhoanDTO taiKhoan = isExit(TK);
        if(taiKhoan==null){
            return false;
        }else if (password.equals(taiKhoan.getPassword())){
            getRole(TK);
            return true;
        }
        return false;
    }
    public TaiKhoanDTO  getTK(String tentk){
        for(TaiKhoanDTO tk: list){
            if(tk.getMaNhanVien().equals(tentk)){
                return tk;
            }
        }
        return null;
    }
    public static void getData(){
        list = TaiKhoanDAO.getTaiKhoan();
    }
    public static TaiKhoanDTO isExit(String TK){
        for(TaiKhoanDTO x : list){
            if(TK.equals(x.getMaNhanVien()))
                return x;
        }
        return null;
    }
    public static boolean getRole(String TK){
        NhanVienBUS ql = new NhanVienBUS();
            for(NhanVienDTO nv : ql.list){
                if(nv.getMaNhanVien().equals(TK)){
                    role=nv.getQuyen();
                    return true;
                }
            }
            return false;
    }
    public boolean update(TaiKhoanDTO taik){
        for(TaiKhoanDTO tk: list){
            if(tk.getMaNhanVien().equals(taik.getMaNhanVien())){
                tk=taik;
               return TaiKhoanDAO.updateTaiKhoan(tk);
                
            }
        }
        return false;
    }
}
