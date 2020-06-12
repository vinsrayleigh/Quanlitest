/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.LoaiSPDAO;
import DTO.*;
import java.util.ArrayList;
import java.util.Arrays;

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
        list = LoaiSPDAO.getMaSP();
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
    public String bigNum(){
        int[] a = new int[list.size()];
        for(int i=0 ;i<list.size();i++){
            String temp = list.get(i).getMaLoaiSP().replaceAll("LSP","");
            try {
                a[i]=Integer.parseInt(temp);
            } catch (Exception e) {
                a[i] = 0;
            }
            
        }
        Arrays.sort(a);
        return String.valueOf(a[a.length-1]+1);
    }
    public String getNextID() {
        try {
            return "LSP" + bigNum();
        } catch (Exception e) {
            return "LSP1";
        }
    }
}
