package BUS;

import DTO.*;
import DAO.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
public class NhanVienBUS {

    public ArrayList<NhanVienDTO> list;

    public NhanVienBUS() {
        getData();
    }
    //"STT", "Mã nhân viên","Họ nhân viên", "Tên nhân viên", "Ngày sinh", "Giới tính", "Số điện thoại","Quyền", "Lương","Trạng thái"});
    public static boolean equals(NhanVienDTO nv1,NhanVienDTO nv2){
        if(!nv1.getMaNhanVien().equals(nv2.getMaNhanVien())){ 
            return false;
        }
        if(!nv1.getHoNhanVien().equalsIgnoreCase(nv2.getHoNhanVien())){ 
            return false;
        }
        if(!nv1.getTenNhanVien().equals(nv2.getTenNhanVien())){ 
            return false;
        }
        if(!nv1.getNgaySinh().equals(nv2.getNgaySinh())){ 
            return false;
        }
        if(!nv1.getSdt().equals(nv2.getSdt())){ 
            return false;
        }
        if(!nv1.getMaQuyen().equals(nv2.getMaQuyen())){ 
            return false;
        }
        if(nv1.getLuong() != nv2.getLuong()) { 
            return false;
        }
        if(!nv1.getGioiTinh().equals(nv2.getGioiTinh())) { 
            return false;
        }
        if(nv1.getTrangThai()!=nv2.getTrangThai()) { 
            return false;
        }
        return true;
    }
    public void getData() {
        list = NhanVienDAO.getNhanVien();
    }

    public void add(NhanVienDTO nv) {
        list.add(nv);
        NhanVienDAO.insertNhanVien(nv);
    }

    public NhanVienDTO getNV(String maNV) {
        for (NhanVienDTO nv : list) {
            if (nv.getMaNhanVien().equals(maNV)) {
                return nv;
            }
        }
        return null;
    }

    public String bigNum(){
        int[] a = new int[list.size()];
        for(int i=0 ;i<list.size();i++){
            String temp = list.get(i).getMaNhanVien().replaceAll("NV","");
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
            return "NV" + bigNum();
        } catch (Exception e) {
            return "NV1";
        }
    }

    public void xoa(String maString) {
        NhanVienDTO a = getNV(maString);
        list.remove(a);
        NhanVienDAO.DeleteNhanVien(a);
    }

    public void updateTrangThai(String maNV, int status) {
        NhanVienDTO nv = getNV(maNV);
        nv.setTrangThai(status);
        NhanVienDAO.updateNhanVien(nv);
    }

    public ArrayList<NhanVienDTO> getDsnv() {
        return list;
    }

    public static void main(String[] args) {
        NhanVienBUS a = new NhanVienBUS();

    }

    public ArrayList<NhanVienDTO> search(String value, String type, LocalDate _ngay1, LocalDate _ngay2) {
        ArrayList<NhanVienDTO> result = new ArrayList<>();

        list.forEach((nv) -> {
            if (type.equals("Tất cả")) {
                if (nv.getMaNhanVien().toLowerCase().contains(value.toLowerCase())
                        || nv.getHoNhanVien().toLowerCase().contains(value.toLowerCase())
                        || nv.getNgaySinh().toString().toLowerCase().contains(value.toLowerCase())
                        || nv.getTenNhanVien().toString().toLowerCase().contains(value.toLowerCase())
                        || nv.getGioiTinh().toLowerCase().contains(value.toLowerCase())
                        || nv.getSdt().toLowerCase().contains(value.toLowerCase())
                        || nv.getQuyen().toLowerCase().contains(value.toLowerCase())
                        || String.valueOf(nv.getTrangThai() == 1 ? "Ẩn" : "Hiện").toLowerCase().contains(value.toLowerCase())
                        || Tool.removeAccent(nv.getMaNhanVien()).contains(Tool.removeAccent(value))
                        || Tool.removeAccent(nv.getHoNhanVien()).contains(Tool.removeAccent(value))
                        || Tool.removeAccent(nv.getTenNhanVien()).contains(Tool.removeAccent(value))
                        || Tool.removeAccent(nv.getQuyen()).contains(Tool.removeAccent(value))) {
                    result.add(nv);
                }
            } else {
                switch (type) {
                    case "Mã nhân viên":
                        if (nv.getMaNhanVien().toLowerCase().contains(value.toLowerCase())) {
                            result.add(nv);
                        }
                        break;
                    case "Tên nhân viên":
                        if (nv.getTenNhanVien().toLowerCase().contains(value.toLowerCase())) {
                            result.add(nv);
                        }
                        break;

                    case "Họ nhân viên":
                        if (nv.getHoNhanVien().toLowerCase().contains(value.toLowerCase())) {
                            result.add(nv);
                        }
                        break;
                    case "Ngày sinh":
                        if (nv.getNgaySinh().toString().toLowerCase().contains(value.toLowerCase())) {
                            result.add(nv);
                        }
                        break;
                    case "Giới tính":
                        if (nv.getGioiTinh().toLowerCase().contains(value.toLowerCase())) {
                            result.add(nv);
                        }
                        break;
                    case "Số điện thoại":
                        if (nv.getSdt().toLowerCase().contains(value.toLowerCase())) {
                            result.add(nv);
                        }
                        break;
                    case "Trạng thái":
                        if (String.valueOf(nv.getTrangThai() == 0 ? "Hiện" : "Ẩn").toLowerCase().contains(value.toLowerCase())) {
                            result.add(nv);
                        }
                        break;
                    case "Quyền": {
                        
                        if (String.valueOf(nv.getQuyen()).toLowerCase().contains(value.toLowerCase())) {
                            result.add(nv);
                        }
                        break;
                    }
                    case "Lương":
                        if (String.valueOf(nv.getLuong()).toLowerCase().contains(value.toLowerCase())) {
                            result.add(nv);
                        }
                        break;
                }
            }
        });

        //Ngay sinh
        for (int i = result.size() - 1; i >= 0; i--) {
            NhanVienDTO nv = result.get(i);
            LocalDate ngaysinh = nv.getNgaySinh().toLocalDate();

            Boolean ngayKhongThoa = (_ngay1 != null && ngaysinh.isBefore(_ngay1)) || (_ngay2 != null && ngaysinh.isAfter(_ngay2));

            if (ngayKhongThoa) {
                result.remove(nv);
            }
        }

        return result;
    }
}
