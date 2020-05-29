/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.MaSPDAO;
import DTO.*;
import java.util.ArrayList;

/**
 *
 * @author phuon
 */
public class LoaiSPBUS {
    private ArrayList<LoaiSPDTO> list = new ArrayList<>();
    public LoaiSPBUS(){
        getData();
    }
    public void getData(){
        list = MaSPDAO.getMaSP();
    }
    public static LoaiSPDTO getLoaiSP(String masp){
        return new LoaiSPBUS().getLoaiSPDTO(masp);
    }
    public LoaiSPDTO getLoaiSPDTO(String malsp){
        for(LoaiSPDTO lsp:list){
            if(lsp.getMaLoaiSP().equals(malsp)){
                return lsp;
            }
        }
        return null;
    }
}
