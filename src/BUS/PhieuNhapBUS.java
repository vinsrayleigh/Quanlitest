/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.HoaDonDAO;
import DAO.PhieuNhapDAO;
import DTO.HoaDonDTO;
import DTO.PhieuNhapDTO;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author phuon
 */
public class PhieuNhapBUS {

    public static ArrayList<PhieuNhapDTO> list;

    public PhieuNhapBUS() {
        getData();
    }
    public String bigNum(){
        int[] a = new int[list.size()];
        for(int i=0 ;i<list.size();i++){
            String temp = list.get(i).getMaPhieuNhap().replaceAll("PN","");
            try {
                a[i]=Integer.parseInt(temp);
            } catch (Exception e) {
                a[i] = 0;
            }
            
        }
        Arrays.sort(a);
        return String.valueOf(a[a.length-1]+1);
    }
    public String getNextMaPN() {
        try {
            return "PN" + bigNum();
        } catch (Exception e) {
            return "PN1";
        }
    }

    public void getData() {
        list = PhieuNhapDAO.getPhieuNhap();
    }
}
