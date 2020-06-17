/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Excel;

import BUS.*;
import DAO.*;
import DTO.*;
import GUI.*;
import java.awt.FileDialog;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Iterator;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author Admin
 */
public class DocExcel {

    FileDialog fd = new FileDialog(new JFrame(), "Đọc excel", FileDialog.LOAD);

    public DocExcel() {

    }

    private String getFile() {
        fd.setFile("*.xls");
        fd.setVisible(true);
        String url = fd.getDirectory() + fd.getFile();
        if (url.equals("nullnull")) {
            return null;
        }
        return url;
    }
public void docFileExcelSanPham() {
        fd.setTitle("Nhập dữ liệu sản phẩm từ excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(url));

            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
            HSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            Row row1 = rowIterator.next();
            String hanhDongKhiTrung = "";
            int countThem = 0;
            int countGhiDe = 0;
            int countBoQua = 0;

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {

                    int stt = (int) cellIterator.next().getNumericCellValue();
                    String ma = cellIterator.next().getStringCellValue();
                    String ten = cellIterator.next().getStringCellValue();
                    String maLoaiSP = cellIterator.next().getStringCellValue();
                    LocalDate NamSX = LocalDate.parse(cellIterator.next().getStringCellValue());
                    String MaNCC = cellIterator.next().getStringCellValue();
                    String MaTH = cellIterator.next().getStringCellValue();
                    int dongia = (int) cellIterator.next().getNumericCellValue();
                    int soLuong = (int) cellIterator.next().getNumericCellValue();
                    String mota = cellIterator.next().getStringCellValue();
                    String image = cellIterator.next().getStringCellValue();
                    

                    SanPhamBUS qlspBUS = new SanPhamBUS();
                    SanPhamDTO lspOld = qlspBUS.getSanPham(ma);

                    if (lspOld != null) {
                        if (!hanhDongKhiTrung.contains("tất cả")) {
                            MyTable mtb = new MyTable();
                            mtb.setHeaders(new String[]{"", "Mã", "Tên", "Mã loại sản phẩm", "Năm Sản Xuất", "Mã Nhà Cung Cấp", "Mã Thương Hiệu", "Đơn giá", "Số Lượng", "Mô tả", "image"});
                            mtb.addRow(new String[]{
                                "Cũ:", lspOld.getMaSanPham(),
                                lspOld.getTenSanPham(),
                                lspOld.getMaLoaiSP(),
                                String.valueOf(lspOld.getNamSx()),
                                lspOld.getMaNCC(),
                                lspOld.getMaThuongHieu(),
                                String.valueOf(lspOld.getDongia()),
                                String.valueOf(lspOld.getSoLuong()),
                                lspOld.getMota(),
                                lspOld.getImage()});
                                
                            
                            mtb.addRow(new String[]{
                                "Mới:",ma, ten, maLoaiSP, String.valueOf(NamSX), MaNCC, MaTH, String.valueOf(dongia), String.valueOf(soLuong), mota, image
                            });

                            MyJOptionPane mop = new MyJOptionPane(mtb, hanhDongKhiTrung);
                            hanhDongKhiTrung = mop.getAnswer();
                        }
                        if (hanhDongKhiTrung.contains("Ghi đè")) {
                            SanPhamDTO sp = new SanPhamDTO(ma, ten, (int) dongia, (int) soLuong, Date.valueOf(NamSX), MaNCC, maLoaiSP, MaTH, image);
                            sp.setMota(mota);
                            SanPhamDAO.updateSanPham(sp);
                            countGhiDe++;
                        } else {
                            countBoQua++;
                        }
                    } else {
                            SanPhamDTO sp = new SanPhamDTO(ma, ten, (int) dongia, (int) soLuong, Date.valueOf(NamSX), MaNCC, maLoaiSP, MaTH, image);
                            sp.setMota(mota);
                            SanPhamDAO.insertSanPham(sp);
                            countThem++;
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Đọc thành công, "
                    + "Thêm " + countThem
                    + "; Ghi đè " + countGhiDe
                    + "; Bỏ qua " + countBoQua
                    + ". Vui lòng làm mới để thấy kết quả");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Lỗi khi nhập dữ liệu từ file: " + ex.getMessage());
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi khi đóng inputstream: " + ex.getMessage());
            }
        }
    }
    //Đọc file excel Nhà cung cấp
    public void docFileExcelNhaCungCap() {
        fd.setTitle("Nhập dữ liệu nhà cung cấp từ excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(url));

            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
            HSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            Row row1 = rowIterator.next();

            String hanhDongKhiTrung = "";
            int countThem = 0;
            int countGhiDe = 0;
            int countBoQua = 0;

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {

                    int stt = (int) cellIterator.next().getNumericCellValue();
                    String ma = cellIterator.next().getStringCellValue();
                    String ten = cellIterator.next().getStringCellValue();
                    String diachi = cellIterator.next().getStringCellValue();
                    String email = cellIterator.next().getStringCellValue();

                    NhaCungCapBUS qlnccBUS = new NhaCungCapBUS();

                    NhaCungCapDTO nccOld = qlnccBUS.getNCC(ma);
                    if (nccOld != null) {
                        if (!hanhDongKhiTrung.contains("tất cả")) {
                            MyTable mtb = new MyTable();
                            mtb.setHeaders(new String[]{"", "Mã nhà cung cấp", "Tên nhà cung cấp", "Địa chỉ", "Email"});
                            mtb.addRow(new String[]{
                                "Cũ:", nccOld.getMaNCC(),
                                nccOld.getTenNCC(),
                                nccOld.getDiaChi(),
                                nccOld.getEmail()
                            });
                            mtb.addRow(new String[]{
                                "Mới:", ma, ten, diachi, email
                            });

                            MyJOptionPane mop = new MyJOptionPane(mtb, hanhDongKhiTrung);
                            hanhDongKhiTrung = mop.getAnswer();
                        }
                        if (hanhDongKhiTrung.contains("Ghi đè")) {
                            NhaCungCapDTO ncc = new NhaCungCapDTO(ma, ten, diachi, email);
                            NhaCungCapDAO.updateNhaCungCap(ncc);
                            countGhiDe++;
                        } else {
                            countBoQua++;
                        }
                    } else {
                        NhaCungCapDTO ncc = new NhaCungCapDTO(ma, ten, diachi, email);
                        NhaCungCapDAO.insertNhaCungCap(ncc);
                        countThem++;
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Đọc thành công, "
                    + "Thêm " + countThem
                    + "; Ghi đè " + countGhiDe
                    + "; Bỏ qua " + countBoQua
                    + ". Vui lòng làm mới để thấy kết quả");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Lỗi khi nhập dữ liệu từ file: " + ex.getMessage());
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi khi đóng inputstream: " + ex.getMessage());
            }
        }
    }
//
//    // Đọc file excel quyền

    public void docFileExcelQuyen() {
        fd.setTitle("Nhập dữ liệu quyền từ excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(url));

            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
            HSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            Row row1 = rowIterator.next();

            String hanhDongKhiTrung = "";
            int countThem = 0;
            int countGhiDe = 0;
            int countBoQua = 0;

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {

                    int stt = (int) cellIterator.next().getNumericCellValue();
                    String ma = cellIterator.next().getStringCellValue();
                    String ten = cellIterator.next().getStringCellValue();
                    String chitiet = cellIterator.next().getStringCellValue();

                    QuyenBUS qlqBUS = new QuyenBUS();

                    QuyenDTO qOld = qlqBUS.getQuyen(ma);
                    if (qOld != null) {
                        if (!hanhDongKhiTrung.contains("tất cả")) {
                            MyTable mtb = new MyTable();
                            mtb.setHeaders(new String[]{"", "Mã", "Tên", "Chi tiết quyển"});
                            mtb.addRow(new String[]{
                                "Cũ:", qOld.getMaQuyen(),
                                qOld.getTenQuyen(),
                                qOld.getChitiet()
                            });
                            mtb.addRow(new String[]{
                                "Mới:", ma, ten, chitiet
                            });

                            MyJOptionPane mop = new MyJOptionPane(mtb, hanhDongKhiTrung);
                            hanhDongKhiTrung = mop.getAnswer();
                        }
                        if (hanhDongKhiTrung.contains("Ghi đè")) {
                            QuyenDTO q = new QuyenDTO(ma, ten, chitiet);
                            if (QuyenDAO.updateQuyen(q)) {
                                countGhiDe++;
                            }
                        } else {
                            countBoQua++;
                        }
                    } else {
                        QuyenDTO q = new QuyenDTO(ma, ten, chitiet);
                        QuyenDAO.insertQuyen(q);
                        countThem++;
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Đọc thành công, "
                    + "Thêm " + countThem
                    + "; Ghi đè " + countGhiDe
                    + "; Bỏ qua " + countBoQua
                    + ". Vui lòng làm mới để thấy kết quả");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Lỗi khi nhập dữ liệu từ file: " + ex.getMessage());
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi khi đóng inputstream: " + ex.getMessage());
            }
        }
    }
    public void docFileExcelThuongHieu() {
        fd.setTitle("Nhập dữ liệu thương hiệu từ excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(url));

            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
            HSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            Row row1 = rowIterator.next();

            String hanhDongKhiTrung = "";
            int countThem = 0;
            int countGhiDe = 0;
            int countBoQua = 0;

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {

                    int stt = (int) cellIterator.next().getNumericCellValue();
                    String ma = cellIterator.next().getStringCellValue();
                    String ten = cellIterator.next().getStringCellValue();
                    String chitiet = cellIterator.next().getStringCellValue();

                    ThuongHieuBUS qlTH = new ThuongHieuBUS();
                    //"Mã thương hiệu", "Tên thương hiệu", "Mô tả"
                    ThuongHieuDTO THOld = qlTH.getTH(ma);
                    if (THOld != null) {
                        if (!hanhDongKhiTrung.contains("tất cả")) {
                            MyTable mtb = new MyTable();
                            mtb.setHeaders(new String[]{"", "Mã thương hiệu", "Tên thương hiệu", "Mô tả"});
                            mtb.addRow(new String[]{
                                "Cũ:", THOld.getMaThuongHieu(),
                                THOld.getTenThuongHieu(),
                                THOld.getMoTa()
                            });
                            mtb.addRow(new String[]{
                                "Mới:", ma, ten, chitiet
                            });

                            MyJOptionPane mop = new MyJOptionPane(mtb, hanhDongKhiTrung);
                            hanhDongKhiTrung = mop.getAnswer();
                        }
                        if (hanhDongKhiTrung.contains("Ghi đè")) {
                            ThuongHieuDTO th = new ThuongHieuDTO(ma, ten, chitiet);
                            if (ThuongHieuDAO.updateThuongHieu(th)) {
                                countGhiDe++;
                            }
                        } else {
                            countBoQua++;
                        }
                    } else {
                        ThuongHieuDTO th = new ThuongHieuDTO(ma, ten, chitiet);
                            if (ThuongHieuDAO.insertThuongHieu(th))
                             
                        countThem++;
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Đọc thành công, "
                    + "Thêm " + countThem
                    + "; Ghi đè " + countGhiDe
                    + "; Bỏ qua " + countBoQua
                    + ". Vui lòng làm mới để thấy kết quả");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Lỗi khi nhập dữ liệu từ file: " + ex.getMessage());
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi khi đóng inputstream: " + ex.getMessage());
            }
        }
    }
//
//    //Đọc file excel Tài khoản
//    public void docFileExcelTaiKhoan() {
//        fd.setTitle("Nhập dữ liệu tài khoản từ excel");
//        String url = getFile();
//        if (url == null) {
//            return;
//        }
//
//        FileInputStream inputStream = null;
//        try {
//            inputStream = new FileInputStream(new File(url));
//
//            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
//            HSSFSheet sheet = workbook.getSheetAt(0);
//            Iterator<Row> rowIterator = sheet.iterator();
//            Row row1 = rowIterator.next();
//
//            String hanhDongKhiTrung = "";
//            int countThem = 0;
//            int countGhiDe = 0;
//            int countBoQua = 0;
//
//            while (rowIterator.hasNext()) {
//                Row row = rowIterator.next();
//                Iterator<Cell> cellIterator = row.cellIterator();
//
//                while (cellIterator.hasNext()) {
//
//                    int stt = (int) cellIterator.next().getNumericCellValue();
//                    String taikhoan = cellIterator.next().getStringCellValue();
//                    String matkhau = cellIterator.next().getStringCellValue();
//                    String manv = cellIterator.next().getStringCellValue().split(" - ")[0];
//                    String maquyen = cellIterator.next().getStringCellValue();
//
//                    QuanLyTaiKhoanBUS qltkBUS = new QuanLyTaiKhoanBUS();
//                    TaiKhoan tkOld = qltkBUS.getTaiKhoan(manv);
//
//                    if (tkOld != null) {
//                        if (!hanhDongKhiTrung.contains("tất cả")) {
//                            MyTable mtb = new MyTable();
//                            mtb.setHeaders(new String[]{"", "Tên tài khoản", "Mật khẩu", "Mã nhân viên", "Mã quyền"});
//                            mtb.addRow(new String[]{
//                                "Cũ:", tkOld.getUsername(),
//                                tkOld.getPassword(),
//                                tkOld.getMaNV(),
//                                tkOld.getMaQuyen(),});
//                            mtb.addRow(new String[]{
//                                "Mới:", taikhoan, matkhau, manv, maquyen
//                            });
//
//                            MyJOptionPane mop = new MyJOptionPane(mtb, hanhDongKhiTrung);
//                            hanhDongKhiTrung = mop.getAnswer();
//                        }
//                        if (hanhDongKhiTrung.contains("Ghi đè")) {
//                            qltkBUS.update(taikhoan, matkhau, manv, maquyen);
//                            countGhiDe++;
//                        } else {
//                            countBoQua++;
//                        }
//                    } else {
//                        TaiKhoan tk = new TaiKhoan(taikhoan, matkhau, manv, maquyen);
//                        qltkBUS.add(tk);
//                        countThem++;
//                    }
//                }
//            }
//            JOptionPane.showMessageDialog(null, "Đọc thành công, "
//                    + "Thêm " + countThem
//                    + "; Ghi đè " + countGhiDe
//                    + "; Bỏ qua " + countBoQua
//                    + ". Vui lòng làm mới để thấy kết quả");
//
//        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(null, "Lỗi khi nhập dữ liệu từ file: " + ex.getMessage());
//        } finally {
//            try {
//                if (inputStream != null) {
//                    inputStream.close();
//                }
//            } catch (IOException ex) {
//                JOptionPane.showMessageDialog(null, "Lỗi khi đóng inputstream: " + ex.getMessage());
//            }
//        }
//    }
//
//    //Đọc file excel Khách hàng

    public void docFileExcelKhachhang() {
        fd.setTitle("Nhập dữ liệu khách hàng từ excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(url));

            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
            HSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            Row row1 = rowIterator.next();

            String hanhDongKhiTrung = "";
            int countThem = 0;
            int countGhiDe = 0;
            int countBoQua = 0;

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {

                    int stt = (int) cellIterator.next().getNumericCellValue();
                    String ma = cellIterator.next().getStringCellValue();
                    String ho = cellIterator.next().getStringCellValue();
                    String ten = cellIterator.next().getStringCellValue();
                    LocalDate ngaysinh = LocalDate.parse(cellIterator.next().getStringCellValue());
                    String sdt = cellIterator.next().getStringCellValue();
                    String loaiKH = cellIterator.next().getStringCellValue();
                    int tichLuy = (int) cellIterator.next().getNumericCellValue();
                    KhachHangBUS qlkhBUS = new KhachHangBUS();
                    KhachHangDTO khOLD = qlkhBUS.getKH(ma);
//                    {"STT", "Mã Khách Hàng", "Họ Khách Hàng", "Tên Khách Hàng", "Ngày Sinh", "SĐT", "Loại Khách Hàng", "Tích Lũy"});
                    if (khOLD != null) {
                        if (!hanhDongKhiTrung.contains("tất cả")) {
                            MyTable mtb = new MyTable();
                            mtb.setHeaders(new String[]{"", "Mã Khách Hàng", "Họ Khách Hàng", "Tên Khách Hàng", "Ngày Sinh", "SĐT", "Loại Khách Hàng", "Tích Lũy"});
                            mtb.addRow(new String[]{
                                "Cũ:", khOLD.getMaKhachHang(),
                                khOLD.getHoKhachHang(),
                                khOLD.getTenKhachHang(),
                                String.valueOf(khOLD.getNgaySinh()),
                                khOLD.getSdt(),
                                khOLD.getLoaiKhachHang(),
                                String.valueOf(khOLD.getTichLuy())
                            });
                            mtb.addRow(new String[]{
                                "Mới:", ma, ho, ten, ngaysinh.toString(), sdt, loaiKH, tichLuy + ""
                            });

                            MyJOptionPane mop = new MyJOptionPane(mtb, hanhDongKhiTrung);
                            hanhDongKhiTrung = mop.getAnswer();
                        }
                        if (hanhDongKhiTrung.contains("Ghi đè")) {
                            KhachHangDTO kh = new KhachHangDTO(ma, ten, ho, Date.valueOf(ngaysinh), sdt, loaiKH, tichLuy);
                            KhachHangDAO.updateKhachHang(kh);
                            countGhiDe++;
                        } else {
                            countBoQua++;
                        }
                    } else {
                        KhachHangDTO kh = new KhachHangDTO(ma, ten, ho, Date.valueOf(ngaysinh), sdt, loaiKH, tichLuy);
                        KhachHangDAO.insertKhachHang(kh);
                        countThem++;
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Đọc thành công, "
                    + "Thêm " + countThem
                    + "; Ghi đè " + countGhiDe
                    + "; Bỏ qua " + countBoQua
                    + ". Vui lòng làm mới để thấy kết quả");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Lỗi khi nhập dữ liệu từ file: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi khi đóng inputstream: " + ex.getMessage());
            }
        }
    }
//
//    //Đọc file excel Nhân viên

    public void docFileExcelNhanVien() {
        fd.setTitle("Nhập dữ liệu nhân viên từ excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(url));

            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
            HSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            Row row1 = rowIterator.next();

            String hanhDongKhiTrung = "";
            int countThem = 0;
            int countGhiDe = 0;
            int countBoQua = 0;

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {

                    int stt = (int) cellIterator.next().getNumericCellValue();
                    String ma = cellIterator.next().getStringCellValue();
                    String ho = cellIterator.next().getStringCellValue();
                    String ten = cellIterator.next().getStringCellValue();
                    LocalDate ngaysinh = LocalDate.parse(cellIterator.next().getStringCellValue());
                    String gioitinh = cellIterator.next().getStringCellValue();
                    String sdt = cellIterator.next().getStringCellValue();
                    String quyen = cellIterator.next().getStringCellValue();
                    int luong = (int) cellIterator.next().getNumericCellValue();
                    int trangthai = (cellIterator.next().getStringCellValue().equals("Ẩn") ? 0 : 1);

                    NhanVienBUS qlnvBUS = new NhanVienBUS();
                    NhanVienDTO nvOld = qlnvBUS.getNV(ma);

                    if (nvOld != null) {
                        if (!hanhDongKhiTrung.contains("tất cả")) {
                            MyTable mtb = new MyTable();
                            mtb.setHeaders(new String[]{"", "Mã", "Họ", "Tên", "Ngày sinh", "Giới tính", "SDT", "Quyền", "Lương", "Trạng thái"});
//                          "STT", "Mã nhân viên","Họ nhân viên", "Tên nhân viên", "Ngày sinh", "Giới tính", "Số điện thoại","Quyền", "Lương","Trạng thái"});
                            mtb.addRow(new String[]{
                                "Cũ:", nvOld.getMaNhanVien(),
                                nvOld.getHoNhanVien(),
                                nvOld.getTenNhanVien(),
                                String.valueOf(nvOld.getNgaySinh()),
                                nvOld.getGioiTinh(),
                                nvOld.getSdt(),
                                nvOld.getQuyen(),
                                nvOld.getLuong() + "",
                                String.valueOf(nvOld.getTrangThai())
                            });
                            mtb.addRow(new String[]{
                                "Mới:", ma, ho, ten, String.valueOf(ngaysinh), gioitinh, sdt, quyen, luong + "", String.valueOf(trangthai)
                            });

                            MyJOptionPane mop = new MyJOptionPane(mtb, hanhDongKhiTrung);
                            hanhDongKhiTrung = mop.getAnswer();
                        }
                        if (hanhDongKhiTrung.contains("Ghi đè")) {
                            NhanVienDTO nv = new NhanVienDTO(ma, ten, ho, Date.valueOf(ngaysinh), gioitinh, sdt, quyen, luong, trangthai);
                            NhanVienDAO.updateNhanVien(nv);
                            countGhiDe++;
                        } else {
                            countBoQua++;
                        }
                    } else {
                        NhanVienDTO nv = new NhanVienDTO(ma, ten, ho, Date.valueOf(ngaysinh), gioitinh, sdt, quyen, luong, trangthai);
                        NhanVienDAO.insertNhanVien(nv);
                        qlnvBUS.add(nv);
                        countThem++;
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Đọc thành công, "
                    + "Thêm " + countThem
                    + "; Ghi đè " + countGhiDe
                    + "; Bỏ qua " + countBoQua
                    + ". Vui lòng làm mới để thấy kết quả");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Lỗi khi nhập dữ liệu từ file: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi khi đóng inputstream: " + ex.getMessage());
            }
        }
    }

//    //Đọc file excel Khuyến mãi
    public void docFileExcelKhuyenMai() {
        fd.setTitle("Nhập dữ liệu khuyến mãi từ excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(url));

            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
            HSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            Row row1 = rowIterator.next();

            String hanhDongKhiTrung = "";
            int countThem = 0;
            int countGhiDe = 0;
            int countBoQua = 0;

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {

                    int stt = (int) cellIterator.next().getNumericCellValue();
                    String ma = cellIterator.next().getStringCellValue();
                    String ten = cellIterator.next().getStringCellValue();
                    String maSp = cellIterator.next().getStringCellValue();
                    int giamGia = (int) cellIterator.next().getNumericCellValue();
                    LocalDate ngaybatdau = LocalDate.parse(cellIterator.next().getStringCellValue());
                    LocalDate ngayketthuc = LocalDate.parse(cellIterator.next().getStringCellValue());
                    String chitiet = cellIterator.next().getStringCellValue();

                    KhuyenMaiBUS qlkmBUS = new KhuyenMaiBUS();
                    KhuyenMaiDTO kmOld = qlkmBUS.getKM(ma);

                    if (kmOld != null) {
                        if (!hanhDongKhiTrung.contains("tất cả")) {
                            MyTable mtb = new MyTable();
                            mtb.setHeaders(new String[]{"", "Mã khuyến mại", "Tên khuyến mại", "Mã sản phẩm", "Giảm giá", "Ngày bắt đầu", "Ngày kết thúc", "Chi tiết"});
                            mtb.addRow(new String[]{
                                "Cũ:", kmOld.getMakhuyenmai(),
                                kmOld.getTenkhuyenmai(),
                                kmOld.getMaSanPham(),
                                kmOld.getGiamgia() + "",
                                String.valueOf(kmOld.getNgaybatdau()),
                                String.valueOf(kmOld.getNgayketthuc()),
                                kmOld.getChitiet()
                            });
                            mtb.addRow(new String[]{
                                "Mới:", ma, ten,
                                maSp, giamGia + "",
                                String.valueOf(ngaybatdau),
                                String.valueOf(ngayketthuc),
                                chitiet
                            });

                            MyJOptionPane mop = new MyJOptionPane(mtb, hanhDongKhiTrung);
                            hanhDongKhiTrung = mop.getAnswer();
                        }
                        if (hanhDongKhiTrung.contains("Ghi đè")) {
                            KhuyenMaiDTO km = new KhuyenMaiDTO(maSp, ten, maSp, giamGia, Date.valueOf(ngaybatdau), Date.valueOf(ngayketthuc), chitiet);
                            KhuyenMaiDAO.updateKhuyenMai(km);
                            countGhiDe++;
                        } else {
                            countBoQua++;
                        }
                    } else {
                        KhuyenMaiDTO km = new KhuyenMaiDTO(maSp, ten, maSp, giamGia, Date.valueOf(ngaybatdau), Date.valueOf(ngayketthuc), chitiet);
                        KhuyenMaiDAO.insertKhuyenMai(km);
                        countThem++;
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Đọc thành công, "
                    + "Thêm " + countThem
                    + "; Ghi đè " + countGhiDe
                    + "; Bỏ qua " + countBoQua
                    + ". Vui lòng làm mới để thấy kết quả");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Lỗi khi nhập dữ liệu từ file: " + ex.getMessage());
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi khi đóng inputstream: " + ex.getMessage());
            }
        }
    }
//
//    //Đọc file excel Sản phẩm
//    public void docFileExcelSanPham() {
//        fd.setTitle("Nhập dữ liệu sản phẩm từ excel");
//        String url = getFile();
//        if (url == null) {
//            return;
//        }
//
//        FileInputStream inputStream = null;
//        try {
//            inputStream = new FileInputStream(new File(url));
//
//            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
//            HSSFSheet sheet = workbook.getSheetAt(0);
//            Iterator<Row> rowIterator = sheet.iterator();
//            Row row1 = rowIterator.next();
//            String hanhDongKhiTrung = "";
//            int countThem = 0;
//            int countGhiDe = 0;
//            int countBoQua = 0;
//
//            while (rowIterator.hasNext()) {
//                Row row = rowIterator.next();
//                Iterator<Cell> cellIterator = row.cellIterator();
//
//                while (cellIterator.hasNext()) {
//
//                    int stt = (int) cellIterator.next().getNumericCellValue();
//                    String masp = cellIterator.next().getStringCellValue();
//                    String maloai = cellIterator.next().getStringCellValue().split(" - ")[0];
//                    String tensp = cellIterator.next().getStringCellValue();
//                    float dongia = (float) cellIterator.next().getNumericCellValue();
//                    int soluong = (int) cellIterator.next().getNumericCellValue();
//                    String hinhanh = cellIterator.next().getStringCellValue();
//                    int trangthai = (cellIterator.next().getStringCellValue().equals("Ẩn") ? 1 : 0);
//
//                    QuanLySanPhamBUS qlspBUS = new QuanLySanPhamBUS();
//                    SanPham spOld = qlspBUS.getSanPham(masp);
//                    if (spOld != null) {
//                        if (!hanhDongKhiTrung.contains("tất cả")) {
//                            MyTable mtb = new MyTable();
//                            mtb.setHeaders(new String[]{"", "Mã SP", "Mã LSP", "Tên SP", "Đơn giá", "Số lượng", "Hình ảnh", "Trạng thái"});
//                            mtb.addRow(new String[]{
//                                "Cũ:", spOld.getMaSP(),
//                                spOld.getMaLSP(),
//                                spOld.getTenSP(),
//                                String.valueOf(spOld.getDonGia()),
//                                String.valueOf(spOld.getSoLuong()),
//                                spOld.getFileNameHinhAnh(),
//                                String.valueOf(spOld.getTrangThai())
//                            });
//                            mtb.addRow(new String[]{
//                                "Mới:", masp, maloai, tensp, String.valueOf(dongia), String.valueOf(soluong), hinhanh, String.valueOf(trangthai)
//                            });
//
//                            MyJOptionPane mop = new MyJOptionPane(mtb, hanhDongKhiTrung);
//                            hanhDongKhiTrung = mop.getAnswer();
//                        }
//                        if (hanhDongKhiTrung.contains("Ghi đè")) {
//                            qlspBUS.update(masp, maloai, tensp, dongia, soluong, hinhanh, trangthai);
//                            countGhiDe++;
//                        } else {
//                            countBoQua++;
//                        }
//                    } else {
//                        SanPham sp = new SanPham(masp, maloai, tensp, dongia, soluong, hinhanh, trangthai);
//                        qlspBUS.add(sp);
//                        countThem++;
//                    }
//                }
//            }
//            JOptionPane.showMessageDialog(null, "Đọc thành công, "
//                    + "Thêm " + countThem
//                    + "; Ghi đè " + countGhiDe
//                    + "; Bỏ qua " + countBoQua
//                    + ". Vui lòng làm mới để thấy kết quả");
//
//        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(null, "Lỗi khi nhập dữ liệu từ file: " + ex.getMessage());
//        } finally {
//            try {
//                if (inputStream != null) {
//                    inputStream.close();
//                }
//            } catch (IOException ex) {
//                JOptionPane.showMessageDialog(null, "Lỗi khi đóng inputstream: " + ex.getMessage());
//            }
//        }
//    }
//
//    //Đọc file excel Loại sản phẩm
    public void docFileExcelLoaiSanPham() {
        fd.setTitle("Nhập dữ liệu loại sản phẩm từ excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(url));

            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
            HSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            Row row1 = rowIterator.next();
            String hanhDongKhiTrung = "";
            int countThem = 0;
            int countGhiDe = 0;
            int countBoQua = 0;

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {

                    int stt = (int) cellIterator.next().getNumericCellValue();
                    String ma = cellIterator.next().getStringCellValue();
                    String ten = cellIterator.next().getStringCellValue();

                    LoaiSPBUS qllspBUS = new LoaiSPBUS();
                    LoaiSPDTO lspOld = qllspBUS.getLoaiSPDTO(ma);

                    if (lspOld != null) {
                        if (!hanhDongKhiTrung.contains("tất cả")) {
                            MyTable mtb = new MyTable();
                            mtb.setHeaders(new String[]{"", "Mã", "Tên", "Mô tả"});
                            mtb.addRow(new String[]{
                                "Cũ:", lspOld.getMaLoaiSP(),
                                lspOld.getTenLoaiSP()});
                            
                            mtb.addRow(new String[]{
                                "Mới:", ma, ten
                            });

                            MyJOptionPane mop = new MyJOptionPane(mtb, hanhDongKhiTrung);
                            hanhDongKhiTrung = mop.getAnswer();
                        }
                        if (hanhDongKhiTrung.contains("Ghi đè")) {
                            LoaiSPDTO lsp = new LoaiSPDTO(ma, ten);
                            LoaiSPDAO.updateMaSP(lsp);
                            countGhiDe++;
                        } else {
                            countBoQua++;
                        }
                    } else {
                        LoaiSPDTO lsp = new LoaiSPDTO(ma, ten);
                            LoaiSPDAO.insertMaSP(lsp);
                        countThem++;
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Đọc thành công, "
                    + "Thêm " + countThem
                    + "; Ghi đè " + countGhiDe
                    + "; Bỏ qua " + countBoQua
                    + ". Vui lòng làm mới để thấy kết quả");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Lỗi khi nhập dữ liệu từ file: " + ex.getMessage());
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi khi đóng inputstream: " + ex.getMessage());
            }
        }
    }
}
