/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import static BUS.PhieuNhapBUS.list;
import DAO.HoaDonDAO;
import DTO.HoaDonDTO;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author phuon
 */
public class HoaDonBUS {

    public static ArrayList<HoaDonDTO> list;

    public HoaDonBUS() {
        getData();
    }
    public String bigNum(){
        int[] a = new int[list.size()];
        for(int i=0 ;i<list.size();i++){
            String temp = list.get(i).getMaHoaDon().replaceAll("HD","");
            try {
                a[i]=Integer.parseInt(temp);
            } catch (Exception e) {
                a[i] = 0;
            }
            
        }
        Arrays.sort(a);
        return String.valueOf(a[a.length-1]+1);
    }
    public String getNextMaHD() {
        try {
            return "HD" + bigNum();
        } catch (Exception e) {
            return "HD1";
        }
    }


    public void getData() {
        list = HoaDonDAO.getHoaDon();
    }
}
