/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.NhaCungCapDAO;
import DTO.NhaCungCapDTO;
import java.util.ArrayList;

/**
 *
 * @author phuon
 */
public class NhaCungCapBUS {
    public ArrayList<NhaCungCapDTO> list;
    public NhaCungCapBUS(){
        getData();
    }
    public void getData(){
        list = NhaCungCapDAO.getNhaCungCap();
    }
    public NhaCungCapDTO getNCC(String mancc){
        for(NhaCungCapDTO ncc:list){
            if(ncc.getMaNCC().equals(mancc))
                return ncc;
        }
        return null;
    }
}
