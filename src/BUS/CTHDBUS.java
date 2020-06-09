/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.CTHoaDonDAO;
import DTO.CTHoaDonDTO;
import java.util.ArrayList;

/**
 *
 * @author phuon
 */
public class CTHDBUS {
    public ArrayList<CTHoaDonDTO> list;

    public CTHDBUS() {
        getData();
    }
    public void getData(){
        list = CTHoaDonDAO.getCTHoaDon();
    }
    public ArrayList<CTHoaDonDTO> Seacrh(String maHD){
        ArrayList<CTHoaDonDTO> result= new ArrayList<>();
        for(CTHoaDonDTO ct:list){
            if(ct.getMaHoaDon().equals(maHD)){
                result.add(ct);
            }
        }
        return result;
    }
}
