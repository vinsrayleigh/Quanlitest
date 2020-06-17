/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Excel;

import BUS.*;
import DTO.*;
import DAO.*;
import java.awt.FileDialog;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.omg.CORBA.TCKind;

/**
 *
 * @author Admin
 */
public class XuatExcel {

    public static JFrame parent;
    FileDialog fd = new FileDialog(new JFrame(), "Xuất excel", FileDialog.SAVE);

    public static void setParent(JFrame frame) {
        parent = frame;
    }

    private String getFile() {
        fd.setFile("untitla.xls");
        fd.setVisible(true);
        String url = fd.getDirectory() + fd.getFile();
        if (url.equals("nullnull")) {
            return null;
        } else {
            return url;
        }
    }
public void XuatfileExcelSanpham(){
        
        fd.setTitle("Xuất dữ liệu sản phẩm ra excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileOutputStream outFile = null;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Tài khoản");

            SanPhamBUS qlSP = new SanPhamBUS();
            ArrayList<SanPhamDTO> list = qlSP.list;

            int rownum = 0;
            Row row = sheet.createRow(rownum);
            //"STT", "Mã thương hiệu", "Tên thương hiệu", "Mô tả"
            row.createCell(0, CellType.NUMERIC).setCellValue("STT");
            row.createCell(1, CellType.STRING).setCellValue("Mã Sản Phẩm");
            row.createCell(2, CellType.STRING).setCellValue("Tên Sản Phẩm");
            row.createCell(3, CellType.STRING).setCellValue("Mã Loại Sản Phẩm");
            row.createCell(4, CellType.STRING).setCellValue("Năm Sản Xuất");
            row.createCell(5, CellType.STRING).setCellValue("Mã Nhà Cung Cấp");
            row.createCell(6, CellType.STRING).setCellValue("Mã Thương Hiệu");
            row.createCell(7, CellType.NUMERIC).setCellValue("Đơn Giá");
            row.createCell(8, CellType.NUMERIC).setCellValue("Số Lượng");
            row.createCell(9, CellType.STRING).setCellValue("Mô tả");
            row.createCell(10, CellType.STRING).setCellValue("Image");

            for (SanPhamDTO th : list) {
                rownum++;
                row = sheet.createRow(rownum);

                String masp = th.getMaSanPham();
                String ten = th.getTenSanPham();
                String maloaisp = th.getMaLoaiSP();
                LocalDate namSX = th.getNamSx().toLocalDate();
                String tenNCC = th.getMaNCC();
                String tenthuonghieu = th.getMaThuongHieu();
                int dongia = th.getDongia();
                int soluong = th.getSoLuong();
                String mota = th.getMota();
                String image = th.getImage();

                row.createCell(0, CellType.NUMERIC).setCellValue(rownum);
                row.createCell(1, CellType.STRING).setCellValue(masp);
                row.createCell(2, CellType.STRING).setCellValue(ten);
                row.createCell(3, CellType.STRING).setCellValue(maloaisp);
                row.createCell(4, CellType.STRING).setCellValue(namSX.toString());
                row.createCell(5, CellType.STRING).setCellValue(tenNCC);
                row.createCell(6, CellType.STRING).setCellValue(tenthuonghieu);
                row.createCell(7, CellType.NUMERIC).setCellValue(dongia);
                row.createCell(8, CellType.NUMERIC).setCellValue(soluong);
                row.createCell(9, CellType.STRING).setCellValue(mota);
                row.createCell(10, CellType.STRING).setCellValue(image);
            }
            for (int i = 0; i < rownum; i++) {
                sheet.autoSizeColumn(i);
            }

            File file = new File(url);
            file.getParentFile().mkdirs();
            outFile = new FileOutputStream(file);
            workbook.write(outFile);

            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (outFile != null) {
                    outFile.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    // Xuất file Excel Nhà cung cấp    
    public void xuatFileExcelNhaCungCap() {
        fd.setTitle("Xuất dữ liệu nhà cung cấp ra excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileOutputStream outFile = null;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Nhà cung cấp");

            NhaCungCapBUS qlnccBUS = new NhaCungCapBUS();
            ArrayList<NhaCungCapDTO> list = qlnccBUS.list;

            int rownum = 0;
            Row row = sheet.createRow(rownum);

            row.createCell(0, CellType.NUMERIC).setCellValue("STT");
            row.createCell(1, CellType.STRING).setCellValue("Mã nhà cung cấp");
            row.createCell(2, CellType.STRING).setCellValue("Tên nhà cung cấp");
            row.createCell(3, CellType.STRING).setCellValue("Địa chỉ");
            row.createCell(4, CellType.STRING).setCellValue("Email");
            for (NhaCungCapDTO ncc : list) {
                rownum++;
                row = sheet.createRow(rownum);

                row.createCell(0, CellType.NUMERIC).setCellValue(rownum);
                row.createCell(1, CellType.STRING).setCellValue(ncc.getMaNCC());
                row.createCell(2, CellType.STRING).setCellValue(ncc.getTenNCC());
                row.createCell(3, CellType.STRING).setCellValue(ncc.getDiaChi());
                row.createCell(4, CellType.STRING).setCellValue(ncc.getEmail());
            }
            for (int i = 0; i < rownum; i++) {
                sheet.autoSizeColumn(i);
            }

            File file = new File(url);
            file.getParentFile().mkdirs();
            outFile = new FileOutputStream(file);
            workbook.write(outFile);

            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (outFile != null) {
                    outFile.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // Xuất file Excel Nhân viên
    public void xuatFileExcelNhanVien() {
        fd.setTitle("Xuất dữ liệu nhân viên ra excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileOutputStream outFile = null;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Nhân viên");

            NhanVienBUS qlnvBUS = new NhanVienBUS();
            ArrayList<NhanVienDTO> list = qlnvBUS.getDsnv();

            int rownum = 0;
            Row row = sheet.createRow(rownum);

            row.createCell(0, CellType.NUMERIC).setCellValue("STT");
            row.createCell(1, CellType.STRING).setCellValue("Mã nhân viên");
            row.createCell(2, CellType.STRING).setCellValue("Họ nhân viên");
            row.createCell(3, CellType.STRING).setCellValue("Tên nhân viên");
            row.createCell(4, CellType.STRING).setCellValue("Ngày sinh");
            row.createCell(5, CellType.STRING).setCellValue("Giới tính");
            row.createCell(6, CellType.STRING).setCellValue("Số điện thoại");
            row.createCell(7, CellType.STRING).setCellValue("Mã quyền");
            row.createCell(8, CellType.STRING).setCellValue("Lương");
            row.createCell(9, CellType.STRING).setCellValue("Trạng thái");
//String maNhanVien, String tenNhanVien, String hoNhanVien, java.sql.Date ngaySinh, String gioiTinh, String sdt, String maQuyen, double luong, int trangThai) {
            for (NhanVienDTO nv : list) {
                rownum++;
                row = sheet.createRow(rownum);

                row.createCell(0, CellType.NUMERIC).setCellValue(rownum);
                row.createCell(1, CellType.STRING).setCellValue(nv.getMaNhanVien());
                row.createCell(2, CellType.STRING).setCellValue(nv.getHoNhanVien());
                row.createCell(3, CellType.STRING).setCellValue(nv.getTenNhanVien());
                row.createCell(4, CellType.STRING).setCellValue(nv.getNgaySinh().toString());
                row.createCell(5, CellType.STRING).setCellValue(nv.getGioiTinh());
                row.createCell(6, CellType.STRING).setCellValue(nv.getSdt());
                row.createCell(7, CellType.STRING).setCellValue(nv.getMaQuyen());
                row.createCell(8, CellType.STRING).setCellValue(nv.getLuong());
                row.createCell(9, CellType.STRING).setCellValue((nv.getTrangThai() == 0 ? "Ẩn" : "Hiện"));
            }
            for (int i = 0; i < rownum; i++) {
                sheet.autoSizeColumn(i);
            }

            File file = new File(url);
            file.getParentFile().mkdirs();
            outFile = new FileOutputStream(file);
            workbook.write(outFile);

            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (outFile != null) {
                    outFile.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // Xuất file Excel Khách hàng
    public void xuatFileExcelKhachHang() {
        fd.setTitle("Xuất dữ liệu khách hàng ra excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileOutputStream outFile = null;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Khách hàng");

            KhachHangBUS qlkhBUS = new KhachHangBUS();
            ArrayList<KhachHangDTO> list = qlkhBUS.list;

            int rownum = 0;
            Row row = sheet.createRow(rownum);
//"Mã khách hàng", "Họ khách hàng", "Tên khách hàng", "Ngày sinh", "Số điện thoại", "Loại Khách Hàng", "Tích Lũy"
            row.createCell(0, CellType.NUMERIC).setCellValue("STT");
            row.createCell(1, CellType.STRING).setCellValue("Mã khách hàng");
            row.createCell(2, CellType.STRING).setCellValue("Họ khách hàng");
            row.createCell(3, CellType.STRING).setCellValue("Tên khách hàng");
            row.createCell(4, CellType.STRING).setCellValue("Ngày sinh");
            row.createCell(5, CellType.STRING).setCellValue("Số điện thoại");
            row.createCell(6, CellType.STRING).setCellValue("Loại khách hàng");
            row.createCell(7, CellType.STRING).setCellValue("Tích lũy");

            for (KhachHangDTO kh : list) {
                rownum++;
                row = sheet.createRow(rownum);

                row.createCell(0, CellType.NUMERIC).setCellValue(rownum);
                row.createCell(1, CellType.STRING).setCellValue(kh.getMaKhachHang());
                row.createCell(2, CellType.STRING).setCellValue(kh.getHoKhachHang());
                row.createCell(3, CellType.STRING).setCellValue(kh.getTenKhachHang());
                row.createCell(4, CellType.STRING).setCellValue(kh.getNgaySinh().toLocalDate().toString());
                row.createCell(5, CellType.STRING).setCellValue(kh.getSdt());
                row.createCell(6, CellType.STRING).setCellValue(kh.getLoaiKhachHang());
                row.createCell(7, CellType.NUMERIC).setCellValue(kh.getTichLuy());
            }
            for (int i = 0; i < rownum; i++) {
                sheet.autoSizeColumn(i);
            }

            File file = new File(url);
            file.getParentFile().mkdirs();
            outFile = new FileOutputStream(file);
            workbook.write(outFile);

            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (outFile != null) {
                    outFile.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
//
     public void xuatFileExcelBaoHanh(){
        fd.setTitle("xuất dữ liệu ra file excel");
        String url=getFile();
        if(url==null){
            return;
            }
           FileOutputStream outFile = null;
           try{
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Bảo Hành");
        HoaDonBUS qlHD = new HoaDonBUS();
        KhachHangBUS qlKH = new KhachHangBUS();
        SanPhamBUS qlSanPham = new SanPhamBUS();
        LoaiSPBUS qlL = new LoaiSPBUS();
        BaoHanhBUS qlbhBUS =new BaoHanhBUS();
        ArrayList<BaoHanhDTO> list = qlbhBUS.list;
        int rownum = 0;
        Row row = sheet.createRow(rownum);
        //"Mã khách hàng", "H? khách hàng", "Tên khách hàng", "Ngày sinh", "S? di?n tho?i", "Lo?i Khách Hàng", "Tích Luy"
        row.createCell(0, CellType.NUMERIC).setCellValue("STT");
        row.createCell(1, CellType.STRING).setCellValue("Mã hóa don");
        row.createCell(2, CellType.STRING).setCellValue("Tên khách hàng");
        row.createCell(3, CellType.STRING).setCellValue("Tên sản phẩm");
        row.createCell(4, CellType.STRING).setCellValue("Loại sản phẩm");
        row.createCell(5, CellType.STRING).setCellValue("Ngày lập");
        row.createCell(6, CellType.STRING).setCellValue("Thời hạn");
        for (BaoHanhDTO bh : list) {
            rownum++;
            row = sheet.createRow(rownum);
            HoaDonDTO hd = qlHD.getHD(bh.getMaHoaDon());
            KhachHangDTO kh = qlKH.getKH(hd.getMaKhachHang());
            SanPhamDTO sp = qlSanPham.getSanPham(bh.getMaSanPham());
            LoaiSPDTO lsp = qlL.getLoaiSPDTO(sp.getMaLoaiSP());
            java.sql.Date a = (java.sql.Date) bh.getNgayLap().clone();
            a.setYear(a.getYear()+2);
            row.createCell(0, CellType.NUMERIC).setCellValue(rownum);
            row.createCell(1, CellType.STRING).setCellValue(bh.getMaHoaDon());
            row.createCell(2, CellType.STRING).setCellValue(kh.getFullName());
            row.createCell(3, CellType.STRING).setCellValue( sp.getTenSanPham());
            row.createCell(4, CellType.STRING).setCellValue( lsp.getTenLoaiSP());
            row.createCell(5, CellType.STRING).setCellValue( bh.getNgayLap().toLocalDate().toString());
            row.createCell(6, CellType.STRING).setCellValue(a.toLocalDate().toString());
            
        }
        for (int i = 0; i < rownum; i++) {
            sheet.autoSizeColumn(i);
        }
        File file = new File(url);
        file.getParentFile().mkdirs();
        outFile = new FileOutputStream(file);
        workbook.write(outFile);
        JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());
        
    }catch (FileNotFoundException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (outFile != null) {
                    outFile.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
//    // Xuất file Excel Tài khoản
public void xuatFileExcelHoaDon() {
        fd.setTitle("Xuất hóa đơn ra excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileOutputStream outFile = null;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Hóa đơn");

            HoaDonBUS qlhdBUS = new HoaDonBUS();
            ArrayList<HoaDonDTO> list = qlhdBUS.list;
// khai báo khách hàng, nhân viên BUS
            KhachHangBUS qlkh = new KhachHangBUS();
            NhanVienBUS qlnv = new NhanVienBUS();
            int rownum = 0;
            Row row = sheet.createRow(rownum);

            row.createCell(0, CellType.NUMERIC).setCellValue("STT");
            row.createCell(1, CellType.STRING).setCellValue("Mã hóa đơn");
            row.createCell(2, CellType.STRING).setCellValue("Tên nhân viên");
            row.createCell(3, CellType.STRING).setCellValue("Tên khách hàng");
            row.createCell(4, CellType.STRING).setCellValue("Tổng tiền");
            row.createCell(5, CellType.STRING).setCellValue("Ngày lập");
            row.createCell(6, CellType.STRING).setCellValue("Mã Khuyến mãi");
            row.createCell(7, CellType.STRING).setCellValue("Giảm giá");
//String maNhanVien, String tenNhanVien, String hoNhanVien, java.sql.Date ngaySinh, String gioiTinh, String sdt, String maQuyen, double luong, int trangThai) {
            for (HoaDonDTO hd : list) {
                rownum++;
                row = sheet.createRow(rownum);

                row.createCell(0, CellType.NUMERIC).setCellValue(rownum);
                row.createCell(1, CellType.STRING).setCellValue(hd.getMaHoaDon());
                row.createCell(2, CellType.STRING).setCellValue(qlnv.getNV(hd.getMaNhanVien()).getFullFame());
                row.createCell(3, CellType.STRING).setCellValue(qlkh.getKH(hd.getMaKhachHang()).getFullName());
                row.createCell(4, CellType.NUMERIC).setCellValue(hd.getTongTien());
                row.createCell(5, CellType.STRING).setCellValue(hd.getNgayLap().toString());
                row.createCell(6, CellType.STRING).setCellValue(hd.getMaKM());
                row.createCell(7, CellType.NUMERIC).setCellValue(hd.getGiamGia());
            }
            for (int i = 0; i < rownum; i++) {
                sheet.autoSizeColumn(i);
            }

            File file = new File(url);
            file.getParentFile().mkdirs();
            outFile = new FileOutputStream(file);
            workbook.write(outFile);

            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (outFile != null) {
                    outFile.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void xuatFileExcelThuongHieu() {
        fd.setTitle("Xuất dữ liệu thương hiệu ra excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileOutputStream outFile = null;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Tài khoản");

            ThuongHieuBUS qlTH = new ThuongHieuBUS();
            ArrayList<ThuongHieuDTO> list = qlTH.getTH();

            int rownum = 0;
            Row row = sheet.createRow(rownum);
            //"STT", "Mã thương hiệu", "Tên thương hiệu", "Mô tả"
            row.createCell(0, CellType.NUMERIC).setCellValue("STT");
            row.createCell(1, CellType.STRING).setCellValue("Mã thương hiệu");
            row.createCell(2, CellType.STRING).setCellValue("Tên thương hiệu");
            row.createCell(3, CellType.STRING).setCellValue("Mô tả");

            for (ThuongHieuDTO th : list) {
                rownum++;
                row = sheet.createRow(rownum);

                String math = th.getMaThuongHieu();
                String ten = th.getTenThuongHieu();
                String mota = th.getMoTa();

                row.createCell(0, CellType.NUMERIC).setCellValue(rownum);
                row.createCell(1, CellType.STRING).setCellValue(math);
                row.createCell(2, CellType.STRING).setCellValue(ten);
                row.createCell(3, CellType.STRING).setCellValue(mota);
            }
            for (int i = 0; i < rownum; i++) {
                sheet.autoSizeColumn(i);
            }

            File file = new File(url);
            file.getParentFile().mkdirs();
            outFile = new FileOutputStream(file);
            workbook.write(outFile);

            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (outFile != null) {
                    outFile.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
//
//    // Xuất file Excel Khuyến mãi
//    public void xuatFileExcelKhuyenMai() {
//        fd.setTitle("Xuất dữ liệu khuyến mãi ra excel");
//        String url = getFile();
//        if (url == null) {
//            return;
//        }
//
//        FileOutputStream outFile = null;
//        try {
//            HSSFWorkbook workbook = new HSSFWorkbook();
//            HSSFSheet sheet = workbook.createSheet("Khuyến mãi");
//
//            QuanLyKhuyenMaiBUS qlkmBUS = new QuanLyKhuyenMaiBUS();
//            ArrayList<KhuyenMai> list = qlkmBUS.getDskm();
//
//            int rownum = 0;
//            Row row = sheet.createRow(rownum);
//
//            row.createCell(0, CellType.NUMERIC).setCellValue("STT");
//            row.createCell(1, CellType.STRING).setCellValue("Mã khuyến mãi");
//            row.createCell(2, CellType.STRING).setCellValue("Tên khuyến mãi");
//            row.createCell(3, CellType.NUMERIC).setCellValue("Điều kiện");
//            row.createCell(4, CellType.NUMERIC).setCellValue("Phần trăm");
//            row.createCell(5, CellType.STRING).setCellValue("Ngày bắt đầu");
//            row.createCell(6, CellType.STRING).setCellValue("Ngày kết thúc");
//
//            for (KhuyenMai km : list) {
//                rownum++;
//                row = sheet.createRow(rownum);
//
//                row.createCell(0, CellType.NUMERIC).setCellValue(rownum);
//                row.createCell(1, CellType.STRING).setCellValue(km.getMaKM());
//                row.createCell(2, CellType.STRING).setCellValue(km.getTenKM());
//                row.createCell(3, CellType.NUMERIC).setCellValue(km.getDieuKienKM());
//                row.createCell(4, CellType.NUMERIC).setCellValue(km.getPhanTramKM());
//                row.createCell(5, CellType.STRING).setCellValue(String.valueOf(km.getNgayBD()));
//                row.createCell(6, CellType.STRING).setCellValue(String.valueOf(km.getNgayKT()));
//            }
//            for (int i = 0; i < rownum; i++) {
//                sheet.autoSizeColumn(i);
//            }
//
//            File file = new File(url);
//            file.getParentFile().mkdirs();
//            outFile = new FileOutputStream(file);
//            workbook.write(outFile);
//
//            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());
//
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            try {
//                if (outFile != null) {
//                    outFile.close();
//                }
//            } catch (IOException ex) {
//                Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }
//

    public void xuatFileExcelSanPham() {
        fd.setTitle("Xuất dữ liệu sản phẩm ra excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileOutputStream outFile = null;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Sản phẩm");

            SanPhamBUS qlspBUS = new SanPhamBUS();
            LoaiSPBUS qllsp = new LoaiSPBUS();
            ArrayList<SanPhamDTO> list = qlspBUS.list;

            int rownum = 0;
            Row row = sheet.createRow(rownum);
//            "Mã sản phẩm", "Tên sản phẩm", "Loại sản phẩm", "Năm sx", "Nhà cung cấp", "Thương hiệu", "Đơn giá", "Số lượng","Mô tả", "Ảnh"});
            row.createCell(0, CellType.NUMERIC).setCellValue("STT");
            row.createCell(1, CellType.STRING).setCellValue("Mã sản phẩm");
            row.createCell(2, CellType.STRING).setCellValue("Tên sản phẩm");
            row.createCell(3, CellType.STRING).setCellValue("Loại sản phẩm");
            row.createCell(4, CellType.STRING).setCellValue("Nhà cung cấp");
            row.createCell(5, CellType.STRING).setCellValue("Thương hiệu");
            row.createCell(6, CellType.NUMERIC).setCellValue("Đơn giá");
            row.createCell(7, CellType.NUMERIC).setCellValue("Số lượng");
            row.createCell(8, CellType.STRING).setCellValue("Mô tả");
            row.createCell(9, CellType.STRING).setCellValue("Ảnh");

            for (SanPhamDTO sp : list) {
                rownum++;
                row = sheet.createRow(rownum);
                row.createCell(0, CellType.NUMERIC).setCellValue(rownum);
                row.createCell(1, CellType.STRING).setCellValue(sp.getMaSanPham());
                row.createCell(2, CellType.STRING).setCellValue(sp.getTenSanPham());
                row.createCell(3, CellType.STRING).setCellValue(sp.getTenLoaiSP() + "-" + sp.getMaLoaiSP());
                row.createCell(4, CellType.STRING).setCellValue(sp.getTenNCC() + "-" + sp.getMaNCC());
                row.createCell(5, CellType.STRING).setCellValue(sp.getTenThuongHieu() + "-" + sp.getMaThuongHieu());
                row.createCell(6, CellType.NUMERIC).setCellValue(sp.getDongia());
                row.createCell(7, CellType.NUMERIC).setCellValue(sp.getSoLuong());
                row.createCell(8, CellType.STRING).setCellValue(sp.getMota());
                row.createCell(9, CellType.STRING).setCellValue(sp.getImage());

            }
            for (int i = 0; i < rownum; i++) {
                sheet.autoSizeColumn(i);
            }

            File file = new File(url);
            file.getParentFile().mkdirs();
            outFile = new FileOutputStream(file);
            workbook.write(outFile);

            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (outFile != null) {
                    outFile.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

//    // Xuất file Excel Loại sản phẩm
    public void xuatFileExcelLoaiSanPham() {
        fd.setTitle("Xuất dữ liệu loại sản phẩm ra excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileOutputStream outFile = null;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Loại sản phẩm");

            LoaiSPBUS qllspBUS = new LoaiSPBUS();
            ArrayList<LoaiSPDTO> list = qllspBUS.getds();

            int rownum = 0;
            Row row = sheet.createRow(rownum);

            row.createCell(0, CellType.NUMERIC).setCellValue("STT");
            row.createCell(1, CellType.STRING).setCellValue("Mã Loại");
            row.createCell(2, CellType.STRING).setCellValue("Tên loại");

            for (LoaiSPDTO lsp : list) {
                rownum++;
                row = sheet.createRow(rownum);

                row.createCell(0, CellType.NUMERIC).setCellValue(rownum);
                row.createCell(1, CellType.STRING).setCellValue(lsp.getMaLoaiSP());
                row.createCell(2, CellType.STRING).setCellValue(lsp.getTenLoaiSP());
            }
            for (int i = 0; i < rownum; i++) {
                sheet.autoSizeColumn(i);
            }

            File file = new File(url);
            file.getParentFile().mkdirs();
            outFile = new FileOutputStream(file);
            workbook.write(outFile);

            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (outFile != null) {
                    outFile.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
//xuất file excel Phiếu nhập

    public void xuatFileExcelQuyen() {
        fd.setTitle("Xuất dữ liệu quyền ra excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileOutputStream outFile = null;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Quyền");

            QuyenBUS qlqBUS = new QuyenBUS();
            ArrayList<QuyenDTO> list = qlqBUS.list;

            int rownum = 0;
            Row row = sheet.createRow(rownum);

            row.createCell(0, CellType.NUMERIC).setCellValue("STT");
            row.createCell(1, CellType.STRING).setCellValue("Mã quyền");
            row.createCell(2, CellType.STRING).setCellValue("Tên quyền");
            row.createCell(3, CellType.STRING).setCellValue("Chi tiết quyền");

            for (QuyenDTO q : list) {
                rownum++;
                row = sheet.createRow(rownum);

                row.createCell(0, CellType.NUMERIC).setCellValue(rownum);
                row.createCell(1, CellType.STRING).setCellValue(q.getMaQuyen());
                row.createCell(2, CellType.STRING).setCellValue(q.getTenQuyen());
                row.createCell(3, CellType.STRING).setCellValue(q.getChitiet());
            }
            for (int i = 0; i < rownum; i++) {
                sheet.autoSizeColumn(i);
            }

            File file = new File(url);
            file.getParentFile().mkdirs();
            outFile = new FileOutputStream(file);
            workbook.write(outFile);

            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (outFile != null) {
                    outFile.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // Xuất file Excel Quyền
    public void xuatFileExcelPhieuNhap() {
        fd.setTitle("Xuất dữ liệu quyền ra excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileOutputStream outFile = null;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Quyền");

            PhieuNhapBUS qlPhieuNhap = new PhieuNhapBUS();
            NhanVienBUS qlNhanVien = new NhanVienBUS();
            //H
            //KhachHangBus qlKH  = new KhachHangBUS();
            //qlKH.getKh()
            NhaCungCapBUS qlNCC = new NhaCungCapBUS();
            ArrayList<PhieuNhapDTO> list = qlPhieuNhap.list;
//mtb.setHeaders(new String[]{"STT", "Mã Phiếu nhập", "Tên nhân viên"," Nhà Cung Cấp", "Tổng tiền", "Ngày lập"});
            int rownum = 0;
            Row row = sheet.createRow(rownum);

            row.createCell(0, CellType.NUMERIC).setCellValue("STT");
            row.createCell(1, CellType.STRING).setCellValue("Mã phiếu nhập");
            row.createCell(2, CellType.STRING).setCellValue("tên nhân viên");
            row.createCell(3, CellType.STRING).setCellValue("Chi nhà cung cấp");
            row.createCell(4, CellType.STRING).setCellValue("Tổng tiền");
            row.createCell(5, CellType.STRING).setCellValue("Ngày lập");
//hd.getMaPhieuNhap(),
//                    qlNhanVien.getNV(hd.getMaNhanVien()).getFullFame(),
//                    qlNCC.getNCC(hd.getMaNCC()).getTenNCC(),
//                    Tool.getMonney((int) hd.getTongTien())+",000",
//                    hd.getNgayLap().toString()
            for (PhieuNhapDTO q : list) {
                rownum++;
                row = sheet.createRow(rownum);

                row.createCell(0, CellType.NUMERIC).setCellValue(rownum);
                row.createCell(1, CellType.STRING).setCellValue(q.getMaPhieuNhap());
                row.createCell(2, CellType.STRING).setCellValue(qlNhanVien.getNV(q.getMaNhanVien()).getFullFame());
                row.createCell(3, CellType.STRING).setCellValue(qlNCC.getNCC(q.getMaNCC()).getTenNCC());
                row.createCell(4, CellType.NUMERIC).setCellValue(Tool.getMonney((int) q.getTongTien()) + ",000");
                row.createCell(5, CellType.STRING).setCellValue(q.getNgayLap().toString());
            }
            for (int i = 0; i < rownum; i++) {
                sheet.autoSizeColumn(i);
            }

            File file = new File(url);
            file.getParentFile().mkdirs();
            outFile = new FileOutputStream(file);
            workbook.write(outFile);

            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (outFile != null) {
                    outFile.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

//    // Xuất file Excel Hóa đơn
//    public void xuatFileExcelHoaDon() {
//        fd.setTitle("Xuất dữ liệu hóa đơn ra excel");
//        String url = getFile();
//        if (url == null) {
//            return;
//        }
//
//        FileOutputStream outFile = null;
//        try {
//            HSSFWorkbook workbook = new HSSFWorkbook();
//            HSSFSheet sheet = workbook.createSheet("Hóa đơn");
//
//            QuanLyHoaDonBUS qlhdBUS = new QuanLyHoaDonBUS();
//            QuanLyChiTietHoaDonBUS qlcthdBUS = new QuanLyChiTietHoaDonBUS();
//            QuanLyNhanVienBUS qlnvBUS = new QuanLyNhanVienBUS();
//            QuanLyKhachHangBUS qlkhBUS = new QuanLyKhachHangBUS();
//            QuanLyKhuyenMaiBUS qlkmBUS = new QuanLyKhuyenMaiBUS();
//            QuanLySanPhamBUS qlspBUS = new QuanLySanPhamBUS();
//            ArrayList<HoaDon> list = qlhdBUS.getDshd();
//
//            int rownum = 0;
//            int sttHoaDon = 0;
//            Row row = sheet.createRow(rownum);
//
//            row.createCell(0, CellType.NUMERIC).setCellValue("STT");
//            row.createCell(1, CellType.STRING).setCellValue("Mã hóa đơn");
//            row.createCell(2, CellType.STRING).setCellValue("Mã nhân viên");
//            row.createCell(3, CellType.STRING).setCellValue("Mã khách hàng");
//            row.createCell(4, CellType.STRING).setCellValue("Mã khuyến mãi");
//            row.createCell(5, CellType.STRING).setCellValue("Ngày lập");
//            row.createCell(6, CellType.STRING).setCellValue("Giờ lập");
//            row.createCell(7, CellType.STRING).setCellValue("Tổng tiền");
//
//            row.createCell(8, CellType.STRING).setCellValue("Sản phẩm");
//            row.createCell(9, CellType.STRING).setCellValue("Số lượng");
//            row.createCell(10, CellType.STRING).setCellValue("Đơn giá");
//            row.createCell(11, CellType.STRING).setCellValue("Thành tiền");
//
//            for (HoaDon hd : list) {
//                rownum++;
//                sttHoaDon++;
//                row = sheet.createRow(rownum);
//
//                row.createCell(0, CellType.NUMERIC).setCellValue(sttHoaDon);
//                row.createCell(1, CellType.STRING).setCellValue(hd.getMaHoaDon());
//                row.createCell(2, CellType.STRING).setCellValue(hd.getMaNhanVien() + " - " + qlnvBUS.getNhanVien(hd.getMaNhanVien()).getTenNV());
//                row.createCell(3, CellType.STRING).setCellValue(hd.getMaKhachHang() + " - " + qlkhBUS.getKhachHang(hd.getMaKhachHang()).getTenKH());
//                row.createCell(4, CellType.STRING).setCellValue(hd.getMaKhuyenMai() + " - " + qlkmBUS.getKhuyenMai(hd.getMaKhuyenMai()).getTenKM());
//                row.createCell(5, CellType.STRING).setCellValue(String.valueOf(hd.getNgayLap()));
//                row.createCell(6, CellType.STRING).setCellValue(String.valueOf(hd.getGioLap()));
//                row.createCell(7, CellType.NUMERIC).setCellValue(hd.getTongTien());
//
//                for (ChiTietHoaDon cthd : qlcthdBUS.getAllChiTiet(hd.getMaHoaDon())) {
//                    rownum++;
//                    row = sheet.createRow(rownum);
//
//                    String masp = cthd.getMaSanPham();
//
//                    row.createCell(8, CellType.STRING).setCellValue(masp + " - " + qlspBUS.getSanPham(masp).getTenSP());
//                    row.createCell(9, CellType.NUMERIC).setCellValue(cthd.getSoLuong());
//                    row.createCell(10, CellType.NUMERIC).setCellValue(cthd.getDonGia());
//                    row.createCell(11, CellType.NUMERIC).setCellValue(cthd.getDonGia() * cthd.getSoLuong());
//                }
//            }
//            for (int i = 0; i < rownum; i++) {
//                sheet.autoSizeColumn(i);
//            }
//
//            File file = new File(url);
//            file.getParentFile().mkdirs();
//            outFile = new FileOutputStream(file);
//            workbook.write(outFile);
//
//            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());
//
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            try {
//                if (outFile != null) {
//                    outFile.close();
//                }
//            } catch (IOException ex) {
//                Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }
//
//    // Xuất file Excel Phiếu nhập
//    public void xuatFileExcelPhieuNhap() {
//        fd.setTitle("Xuất dữ liệu phiếu nhập ra excel");
//        String url = getFile();
//        if (url == null) {
//            return;
//        }
//
//        FileOutputStream outFile = null;
//        try {
//            HSSFWorkbook workbook = new HSSFWorkbook();
//            HSSFSheet sheet = workbook.createSheet("Hóa đơn");
//
//            QuanLyPhieuNhapBUS qlpnBUS = new QuanLyPhieuNhapBUS();
//            QuanLyChiTietPhieuNhapBUS qlctpnBUS = new QuanLyChiTietPhieuNhapBUS();
//            QuanLySanPhamBUS qlspBUS = new QuanLySanPhamBUS();
//            QuanLyNhanVienBUS qlnvBUS = new QuanLyNhanVienBUS();
//            QuanLyNhaCungCapBUS qlnccBUS = new QuanLyNhaCungCapBUS();
//            ArrayList<PhieuNhap> list = qlpnBUS.getDspn();
//
//            int rownum = 0;
//            Row row = sheet.createRow(rownum);
//
//            row.createCell(0, CellType.NUMERIC).setCellValue("STT");
//            row.createCell(1, CellType.STRING).setCellValue("Mã hóa đơn");
//            row.createCell(2, CellType.STRING).setCellValue("Mã nhà cung cấp");
//            row.createCell(3, CellType.STRING).setCellValue("Mã nhân viên");
//            row.createCell(4, CellType.STRING).setCellValue("Ngày lập");
//            row.createCell(5, CellType.STRING).setCellValue("Giờ lập");
//            row.createCell(6, CellType.STRING).setCellValue("Tổng tiền");
//            row.createCell(7, CellType.STRING).setCellValue("Sản phẩm");
//            row.createCell(8, CellType.STRING).setCellValue("Số lượng");
//            row.createCell(9, CellType.STRING).setCellValue("Đơn giá");
//            row.createCell(10, CellType.STRING).setCellValue("Thành tiền");
//
//            for (PhieuNhap pn : list) {
//                rownum++;
//                row = sheet.createRow(rownum);
//
//                row.createCell(0, CellType.NUMERIC).setCellValue(rownum);
//                row.createCell(1, CellType.STRING).setCellValue(pn.getMaPN());
//                row.createCell(2, CellType.STRING).setCellValue(pn.getMaNCC() + " - " + qlnccBUS.getNhaCungCap(pn.getMaNCC()).getTenNCC());
//                row.createCell(3, CellType.STRING).setCellValue(pn.getMaNV() + " - " + qlnvBUS.getNhanVien(pn.getMaNV()).getTenNV());
//                row.createCell(4, CellType.STRING).setCellValue(String.valueOf(pn.getNgayNhap()));
//                row.createCell(5, CellType.STRING).setCellValue(String.valueOf(pn.getGioNhap()));
//                row.createCell(6, CellType.NUMERIC).setCellValue(pn.getTongTien());
//
//                for (ChiTietPhieuNhap ctpn : qlctpnBUS.getAllChiTiet(pn.getMaPN())) {
//                    rownum++;
//                    row = sheet.createRow(rownum);
//
//                    String masp = ctpn.getMaSP();
//
//                    row.createCell(7, CellType.STRING).setCellValue(masp + " - " + qlspBUS.getSanPham(masp).getTenSP());
//                    row.createCell(8, CellType.NUMERIC).setCellValue(ctpn.getSoLuong());
//                    row.createCell(9, CellType.NUMERIC).setCellValue(ctpn.getDonGia());
//                    row.createCell(10, CellType.NUMERIC).setCellValue(ctpn.getDonGia() * ctpn.getSoLuong());
//                }
//            }
//            for (int i = 0; i < rownum; i++) {
//                sheet.autoSizeColumn(i);
//            }
//
//            File file = new File(url);
//            file.getParentFile().mkdirs();
//            outFile = new FileOutputStream(file);
//            workbook.write(outFile);
//
//            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());
//
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            try {
//                if (outFile != null) {
//                    outFile.close();
//                }
//            } catch (IOException ex) {
//                Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }
    private String getTime() {
        return new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
    }
}
