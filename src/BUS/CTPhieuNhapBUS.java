/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.CTPhieuNhapDAO;
import DTO.CTHoaDonDTO;
import DTO.CTPhieuNhapDTO;
import java.util.ArrayList;

/**
 *
 * @author phuon
 */
public class CTPhieuNhapBUS {
    public ArrayList<CTPhieuNhapDTO> list = new ArrayList<>();
    public CTPhieuNhapBUS(){
        getData();
    }
    public void getData(){
        list=CTPhieuNhapDAO.getCTPhieuNhap();
    }
    public ArrayList<CTPhieuNhapDTO> Seacrh(String maPN){
        ArrayList<CTPhieuNhapDTO> result= new ArrayList<>();
        for(CTPhieuNhapDTO ct:list){
            if(ct.getMaPhieuNhap().equals(maPN)){
                result.add(ct);
            }
        }
        return result;
    }
}
