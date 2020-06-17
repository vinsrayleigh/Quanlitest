/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.LoaiSPDAO;
import DTO.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author phuon
 */
public class LoaiSPBUS {

    public ArrayList<LoaiSPDTO> list = new ArrayList<>();

    public LoaiSPBUS() {
        getData();
    }

    public void getData() {
        list = LoaiSPDAO.getMaSP();
    }

    public static LoaiSPDTO getLoaiSP(String masp) {
        return new LoaiSPBUS().getLoaiSPDTO(masp);
    }

    public LoaiSPDTO getLoaiSPDTO(String malsp) {
        for (LoaiSPDTO lsp : list) {
            if (lsp.getMaLoaiSP().equals(malsp)) {
                return lsp;
            }
        }
        return null;
    }

    public ArrayList<LoaiSPDTO> getds() {
        return list;
    }

    public String bigNum() {
        int[] a = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            String temp = list.get(i).getMaLoaiSP().replaceAll("LSP", "");
            try {
                a[i] = Integer.parseInt(temp);
            } catch (Exception e) {
                a[i] = 0;
            }

        }
        Arrays.sort(a);
        return String.valueOf(a[a.length - 1] + 1);
    }

    public String getNextID() {
        try {
            return "LSP" + bigNum();
        } catch (Exception e) {
            return "LSP1";
        }
    }

    public ArrayList<LoaiSPDTO> search(String value, String type) {
        ArrayList<LoaiSPDTO> result = new ArrayList<>();
        list.forEach((th) -> {
            if (type.equals("Tất cả")) {
                if (th.getMaLoaiSP().toLowerCase().contains(value.toLowerCase())
                        || th.getTenLoaiSP().toLowerCase().contains(value.toLowerCase())
                        || Tool.removeAccent(th.getTenLoaiSP().toLowerCase()).contains(value.toLowerCase())) {
                    result.add(th);
                }
            } else {
                switch (type) {
                    case "Mã Loại sản phẩm": {
                        if (th.getMaLoaiSP().toLowerCase().contains(value.toLowerCase())) {
                            result.add(th);
                        }
                        break;
                    }
                    case "Tên Loại sản phẩm": {
                        if (th.getTenLoaiSP().toLowerCase().contains(value.toLowerCase())
                                || Tool.removeAccent(th.getTenLoaiSP().toLowerCase()).contains(value.toLowerCase())) {
                            result.add(th);
                        }
                        break;
                    }
                }
            }
        });
        return result;
    }
}
