/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.SanPhamDAO;
import DTO.*;
import java.util.ArrayList;

/**
 *
 * @author phuon
 */
public class SanPhamBUS {
    public ArrayList<SanPhamDTO> list = new ArrayList<>();
    public SanPhamBUS(){
        getData();
    }
    public SanPhamDTO getSanPham(String masp){
        for(SanPhamDTO sp: list){
            if(sp.getMaSanPham().equals(masp)){
                System.out.println("found");
                return sp;
            }
        }
        return null;
    }
    public void getData(){
        list = SanPhamDAO.getSanPham();
    };
}
