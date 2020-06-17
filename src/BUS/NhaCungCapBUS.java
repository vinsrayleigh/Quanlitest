/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.NhaCungCapDAO;
import DTO.NhaCungCapDTO;
import java.util.ArrayList;
import java.util.Arrays;

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
    public ArrayList<NhaCungCapDTO> getList(){
        return list;
    }
    public String bigNum(){
        int[] a = new int[list.size()];
        for(int i=0 ;i<list.size();i++){
            String temp = list.get(i).getMaNCC().replaceAll("NCC","");
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
            return "NCC" + bigNum();
        } catch (Exception e) {
            return "NCC1";
        }
    }
    public ArrayList<NhaCungCapDTO> search(String value, String type) {
        ArrayList<NhaCungCapDTO> result = new ArrayList<>();

        list.forEach((NhaCungCap) -> {
            if (type.equals("Tất cả")) {
                if (NhaCungCap.getMaNCC().toLowerCase().contains(value.toLowerCase())
                        || NhaCungCap.getMaNCC().toLowerCase().contains(value.toLowerCase())
                        || NhaCungCap.getTenNCC().toString().toLowerCase().contains(value.toLowerCase())
                        || Tool.removeAccent(NhaCungCap.getTenNCC().toString().toLowerCase()).contains(value.toLowerCase())
                        || NhaCungCap.getDiaChi().toString().toLowerCase().contains(value.toLowerCase())
                        || Tool.removeAccent(NhaCungCap.getDiaChi().toString().toLowerCase()).contains(value.toLowerCase())
                        || NhaCungCap.getEmail().toLowerCase().contains(value.toLowerCase()))
                        {
                    result.add(NhaCungCap);
                }
            } else {
                switch (type) {
                    case "Mã nhà cung cấp":
                        if (NhaCungCap.getMaNCC().toLowerCase().contains(value.toLowerCase())) {
                            result.add(NhaCungCap);
                        }
                        break;
                    case "Tên nhà cung cấp":
                        if (NhaCungCap.getTenNCC().toLowerCase().contains(value.toLowerCase())) {
                            result.add(NhaCungCap);
                        }
                        break;

                    case "Địa Chỉ":
                        if (NhaCungCap.getDiaChi().toLowerCase().contains(value.toLowerCase())) {
                            result.add(NhaCungCap);
                        }
                        break;
                    case "Email":
                        if (NhaCungCap.getEmail().toLowerCase().contains(value.toLowerCase())) {
                            result.add(NhaCungCap);
                        }
                        break;
                    
                  }
            }
        });
    return result;
    }
}
