/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.HienThi;

import BUS.BaoHanhBUS;
import BUS.HoaDonBUS;
import BUS.KhachHangBUS;
import BUS.LoaiSPBUS;
import BUS.SanPhamBUS;
import BUS.Tool;
import DTO.*;
import GUI.Button.DateButton;
import GUI.Button.ExportExcelButton;
import GUI.Excel.XuatExcel;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author phuon
 */
public class HienThiBaoHanh extends FormHienThi{
    HoaDonBUS qlHD = new HoaDonBUS();
    KhachHangBUS qlKH = new KhachHangBUS();
    SanPhamBUS qlSanPham = new SanPhamBUS();
    LoaiSPBUS qlL = new LoaiSPBUS();
    BaoHanhBUS qlBH = new BaoHanhBUS();
    JTextField txTim = new JTextField(15);
    JComboBox<String> cbTypeSearch;
    JButton btnRefresh = new JButton("Làm mới");
    JTextField txKhoangNgay1 = new JTextField(8);
    JTextField txKhoangNgay2 = new JTextField(8);
    DatePicker dPicker1;
    DatePicker dPicker2;
    ExportExcelButton btnXuatExcel = new ExportExcelButton();
    public HienThiBaoHanh(){
        DatePickerSettings pickerSettings = new DatePickerSettings();
        pickerSettings.setVisibleDateTextField(false);
        dPicker1 = new DatePicker(pickerSettings);
        dPicker2 = new DatePicker(pickerSettings.copySettings());
        dPicker1.setDateToToday();
        dPicker2.setDateToToday();
        //button
        DateButton db = new DateButton(dPicker1);
        DateButton db2 = new DateButton(dPicker2);
        txKhoangNgay1.setBorder(BorderFactory.createTitledBorder("Từ:"));
        txKhoangNgay2.setBorder(BorderFactory.createTitledBorder("Đến:"));
        JPanel plHeader = new JPanel();
        JPanel plTim = new JPanel();
        plTim.setBorder(BorderFactory.createTitledBorder("Tìm kiếm"));
        txTim.setBorder(BorderFactory.createTitledBorder("Tất cả")); // tạo border rỗng
        cbTypeSearch = new JComboBox<>(new String[]{"Tất cả", "Mã hóa đơn", "Sản phẩm", "Khách hàng"});
        plTim.add(cbTypeSearch);
        plTim.add(txTim);
        plHeader.add(plTim);

        // pl tim khoang ngay
        JPanel plTimKiemKhoangNgay = new JPanel();
        plTimKiemKhoangNgay.setBorder(BorderFactory.createTitledBorder("Ngày lập:"));
        plTimKiemKhoangNgay.add(txKhoangNgay1);
        plTimKiemKhoangNgay.add(dPicker1);
        plTimKiemKhoangNgay.add(txKhoangNgay2);
        plTimKiemKhoangNgay.add(dPicker2);
        plHeader.add(plTimKiemKhoangNgay);
        btnRefresh.setIcon(new ImageIcon("src/Image/sync_50px.png"));
        plHeader.add(btnRefresh);
        plHeader.add(btnXuatExcel);
        String[] header  = new String[]{
          "STT",
            "Mã hóa đơn",
            "Tên khách hàng",
            "Tên sản phẩm",
            "Loại sản phẩm",
            "Ngày lập",
            "Thời hạn"
        };
        mtb.setHeaders(header);
        setDataToTable(qlBH.getds());
        this.add(plHeader,BorderLayout.NORTH);
        addDocumentListener(txTim);
        addDocumentListener(txKhoangNgay1);
        addDocumentListener(txKhoangNgay2);
        btnRefresh.addActionListener((e) -> {
            txTim.setText("");
            txKhoangNgay1.setText("");
            txKhoangNgay2.setText("");
            refresh();
        });
        dPicker1.addDateChangeListener((dce) -> {
            txKhoangNgay1.setText(dPicker1.getDateStringOrEmptyString());
        });
        dPicker2.addDateChangeListener((dce) -> {
            txKhoangNgay2.setText(dPicker2.getDateStringOrEmptyString());
        });
        btnXuatExcel.addActionListener((e) -> {
           new XuatExcel().xuatFileExcelBaoHanh();
        });
    }
    public void refresh(){
        setDataToTable(qlBH.getds());
    }
    private void addDocumentListener(JTextField txField) {
        txField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                txSearchOnChange();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                txSearchOnChange();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                txSearchOnChange();
            }
        });
    }
    private void txSearchOnChange(){
        LocalDate ngay1 = null, ngay2 = null;
        try {
            ngay1 = java.time.LocalDate.parse(txKhoangNgay1.getText());
            txKhoangNgay1.setForeground(Color.black);
        } catch (DateTimeParseException e) {
            txKhoangNgay1.setForeground(Color.red);
        }
        try {
            ngay2 = java.time.LocalDate.parse(txKhoangNgay2.getText());
            txKhoangNgay2.setForeground(Color.black);
        } catch (DateTimeParseException e) {
            txKhoangNgay2.setForeground(Color.red);
        }
        setDataToTable(qlBH.search(txTim.getText(), cbTypeSearch.getSelectedItem().toString(), ngay1, ngay2));
    }
    public void setDataToTable(ArrayList<BaoHanhDTO> list){
        int stt=1;
        mtb.clear();
        for(BaoHanhDTO bh :list){           
            HoaDonDTO hd = qlHD.getHD(bh.getMaHoaDon());
            KhachHangDTO kh = qlKH.getKH(hd.getMaKhachHang());
            SanPhamDTO sp = qlSanPham.getSanPham(bh.getMaSanPham());
            LoaiSPDTO lsp = qlL.getLoaiSPDTO(sp.getMaLoaiSP());
            Date a = (Date) bh.getNgayLap().clone();
            a.setYear(a.getYear()+2);
            mtb.addRow(new String[]{
                stt+"",
                hd.getMaHoaDon(),
                kh.getFullName(),
                sp.getTenSanPham(),
                lsp.getTenLoaiSP(),
                bh.getNgayLap().toLocalDate().toString(),
                a.toLocalDate().toString()
            });
            stt++;
        }
    }
    
}
