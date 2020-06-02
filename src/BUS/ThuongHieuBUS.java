/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.ThuongHieuDAO;
import java.util.ArrayList;
import DTO.*;
/**
 *
 * @author phuon
 */
public class ThuongHieuBUS {
     ArrayList<ThuongHieuDTO> list;
    public ThuongHieuBUS(){
        getData();
    }
    public void getData(){
        list = ThuongHieuDAO.getThuongHieu();
    }
    public ThuongHieuDTO getTH(String math){
        for(ThuongHieuDTO th:list){
            if(th.getMaThuongHieu().equals(math))
                return th;
        }
        return null;
    }
}
