package BUS;
import DTO.*;
import DAO.*;
import java.time.LocalDate;
import java.util.ArrayList;
public class NhanVienBUS {
    public ArrayList<NhanVienDTO> list;

    public NhanVienBUS() {
        getData();
    }
    public void getData(){
        list =  NhanVienDAO.getNhanVien();
    }
    public void add(NhanVienDTO nv ){
        list.add(nv);
        NhanVienDAO.insertNhanVien(nv);
    }
    public NhanVienDTO getNV(String maNV){
        for(NhanVienDTO nv :list){
            if(nv.getMaNhanVien().equals(maNV)){
                return nv;
            }
        }
        return null;
    }
    public String getNextID(){
        long ma;
        try {
            ma= Integer.parseInt(list.get(list.size()-2).getMaNhanVien());
            System.out.println(ma);
            ma++;
        } catch (Exception e) {
            ma = 2000;
        }
        return ""+ma;
    }
    public void xoa(String maString){
        NhanVienDTO a = getNV(maString);
        list.remove(a);
        NhanVienDAO.DeleteNhanVien(a);
    }
    public void updateTrangThai(String maNV,int status){
        NhanVienDTO nv  = getNV(maNV);
        nv.setTrangThai(status);
        NhanVienDAO.updateNhanVien(nv);
    }
    public ArrayList<NhanVienDTO> getDsnv(){
        return list;
    }
    public static void main(String[] args) {
        NhanVienBUS a = new NhanVienBUS();
        
    }public ArrayList<NhanVienDTO> search(String value, String type, LocalDate _ngay1, LocalDate _ngay2) {
        ArrayList<NhanVienDTO> result = new ArrayList<>();

        list.forEach((nv) -> {
            if (type.equals("Tất cả")) {
                if (nv.getMaNhanVien().toLowerCase().contains(value.toLowerCase())
                        || nv.getHoNhanVien().toLowerCase().contains(value.toLowerCase())
                        || nv.getNgaySinh().toString().toLowerCase().contains(value.toLowerCase())
                        || nv.getTenNhanVien().toString().toLowerCase().contains(value.toLowerCase())
                        || nv.getGioiTinh().toLowerCase().contains(value.toLowerCase())
                        || nv.getSdt().toLowerCase().contains(value.toLowerCase())
                        || String.valueOf(nv.getTrangThai() == 1 ? "Ẩn" : "Hiện").toLowerCase().contains(value.toLowerCase())) {
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
