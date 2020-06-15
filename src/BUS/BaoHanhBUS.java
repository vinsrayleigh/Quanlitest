/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.BaoHanhDAO;
import DTO.BaoHanhDTO;
import DTO.HoaDonDTO;
import DTO.KhachHangDTO;
import DTO.LoaiSPDTO;
import DTO.SanPhamDTO;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author phuon
 */
public class BaoHanhBUS {

    ArrayList<BaoHanhDTO> list = new ArrayList<>();

    public BaoHanhBUS() {
        getData();
    }

    public void getData() {
        list = BaoHanhDAO.getBaoHanh();
    }

    public ArrayList<BaoHanhDTO> getds() {
        return list;
    }

    public ArrayList<BaoHanhDTO> search(String value, String Type, LocalDate ngay_1, LocalDate ngay_2) {
        HoaDonBUS qlHD = new HoaDonBUS();
        KhachHangBUS qlKH = new KhachHangBUS();
        SanPhamBUS qlSanPham = new SanPhamBUS();
        LoaiSPBUS qlL = new LoaiSPBUS();
        ArrayList<BaoHanhDTO> result = new ArrayList<>();
        list.forEach((bh) -> {
            HoaDonDTO hd = qlHD.getHD(bh.getMaHoaDon());
            KhachHangDTO kh = qlKH.getKH(hd.getMaKhachHang());
            SanPhamDTO sp = qlSanPham.getSanPham(bh.getMaSanPham());
            LoaiSPDTO lsp = qlL.getLoaiSPDTO(sp.getMaLoaiSP());
            Date a = (Date) bh.getNgayLap().clone();
            a.setYear(a.getYear() + 2);
            if (Type.equals("Tất cả")) {
                if (bh.getMaHoaDon().toLowerCase().contains(value.toLowerCase())
                        ||Tool.removeAccent(kh.getFullName()).toLowerCase().contains(value.toLowerCase())
                        ||kh.getMaKhachHang().toLowerCase().contains(value.toLowerCase())
                        || bh.getMaSanPham().toLowerCase().contains(value.toLowerCase())
                        || Tool.removeAccent(sp.getTenSanPham()).toLowerCase().contains(value.toLowerCase())
                        || bh.getNgayLap().toString().toLowerCase().contains(value.toLowerCase())
                        || String.valueOf(bh.getThoiHan()).toLowerCase().contains(value.toLowerCase())) {
                    result.add(bh);
                }
            } else {
                switch (Type) {
                    case "Mã Hóa Đơn": {
                        if (bh.getMaHoaDon().toLowerCase().contains(value.toLowerCase())) {
                            result.add(bh);
                        }
                        break;
                    }
                    case "Sản phẩm": {
                        if (bh.getMaSanPham().toLowerCase().contains(value.toLowerCase())) {
                            result.add(bh);
                        }
                        break;
                    }
                    case "Khách hàng": {
                        if (bh.getNgayLap().toString().toLowerCase().contains(value.toLowerCase())) {
                            result.add(bh);
                        }
                        break;
                    }
                }
            }
        });

        for (int i = result.size() - 1; i >= 0; i--) {
            BaoHanhDTO bh = result.get(i);
            LocalDate ngaylap = bh.getNgayLap().toLocalDate();
            Boolean ngaykhongthoa = (ngay_1 != null && ngaylap.isBefore(ngay_1) || ngay_2 != null && ngaylap.isAfter(ngay_2));

            if (ngaykhongthoa) {
                result.remove(i);
            }
        }

        return result;
    }
}
