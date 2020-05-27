/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;
import DTO.*;
import DAO.*;
import java.util.ArrayList;
/**
 *
 * @author phuon
 */
public class QuyenBUS {
    public static ArrayList<QuyenDTO> list = new ArrayList<>();

    public QuyenBUS() {
        getData();
    }
    public static void getData(){
        list=QuyenDAO.getQuyen();
    }
    public QuyenDTO getQuyen(String maQ){
        for(QuyenDTO q : list){
            if(q.getMaQuyen().equals(maQ)){
                return q;
            }
        }
        return null;
    }
}
