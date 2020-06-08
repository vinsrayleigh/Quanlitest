/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.ThuongHieuDAO;
import java.util.ArrayList;
import DTO.*;
import java.util.Arrays;
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
    public String bigNum(){
        int[] a = new int[list.size()];
        for(int i=0 ;i<list.size();i++){
            String temp = list.get(i).getMaThuongHieu().replaceAll("TH","");
            try {
                a[i]=Integer.parseInt(temp);
            } catch (Exception e) {
                a[i] = 0;
            }
            
        }
        Arrays.sort(a);
        System.out.println(a[a.length-1]);
        return String.valueOf(a[a.length-1]);
    }
    public String getNextID() {
        try {
            return "Q" + bigNum()+1;
        } catch (Exception e) {
            return "Q1";
        }
    }
}
