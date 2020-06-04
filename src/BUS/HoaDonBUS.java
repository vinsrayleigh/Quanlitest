/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.HoaDonDAO;
import DTO.HoaDonDTO;
import java.util.ArrayList;

/**
 *
 * @author phuon
 */
public class HoaDonBUS {

    public static ArrayList<HoaDonDTO> list;

    public HoaDonBUS() {
        getData();
    }

    public String getNextMaHD() {
        try {
            String temp = list.get(list.size() - 1).getMaHoaDon().replaceAll("HD", "");
            return "HD" + String.valueOf(Integer.parseInt(temp));
        } catch (Exception e) {
            return "HD0001";
        }
    }

    public void getData() {
        list = HoaDonDAO.getHoaDon();
    }
}
