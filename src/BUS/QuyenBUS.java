/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;
import DTO.*;
import DAO.*;
import java.util.ArrayList;
import java.util.Arrays;
/**
 *
 * @author phuon
 */
public class QuyenBUS {
    public  ArrayList<QuyenDTO> list = new ArrayList<>();

    public QuyenBUS() {
        getData();
    } 
    public void getData(){
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
    public QuyenDTO getQuyenfromTen(String maQ){
        for(QuyenDTO q : list){
            if(q.getTenQuyen().equals(maQ)){
                return q;
            }
        }
        return null;
    }
    public String bigNum(){
        int[] a = new int[list.size()];
        for(int i=0 ;i<list.size();i++){
            String temp = list.get(i).getMaQuyen().replaceAll("Q","");
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
            return "Q" + bigNum();
        } catch (Exception e) {
            return "Q1";
        }
    }
}
