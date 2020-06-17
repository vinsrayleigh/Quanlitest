/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.HienThi;

import BUS.NhanVienBUS;
import BUS.QuyenBUS;
import BUS.Tool;
import DAO.NhanVienDAO;
import DTO.NhanVienDTO;
import GUI.Button.DateButton;
import GUI.MyTable;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author phuon
 */
public class HienThiNhanVien extends FormHienThi{
    NhanVienBUS qlNhanVien = new NhanVienBUS();
    JTextField txTim = new JTextField(15);
    JComboBox<String> cbTypeSearch;
    JButton btnRefresh = new JButton("Làm mới");
    NhanVienDTO nvSua = new NhanVienDTO();
    JTextField txKhoangNgay1 = new JTextField(8);
    JTextField txKhoangNgay2 = new JTextField(8);
    DatePicker dPicker1;
    DatePicker dPicker2;
    public HienThiNhanVien(){
        setLayout(new BorderLayout());
        //khoang ngay
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
        btnRefresh.setIcon(new ImageIcon("src/Image/sync_50px.png"));
        mtb = new MyTable();
        mtb.setPreferredSize(new Dimension(1200 - 250, 600));
        mtb.setHeaders(new String[]{"STT", "Mã nhân viên","Họ nhân viên", "Tên nhân viên", "Ngày sinh", "Giới tính", "Số điện thoại","Quyền", "Lương","Trạng thái"});
        mtb.setColumnsWidth(new double[]{.5, 1.5, 2.5, 1.3, 3, 1.5, 1,1,1,1});
        mtb.setAlignment(0, JLabel.CENTER);
        mtb.setupSort();
        setDataToTable(qlNhanVien.getDsnv(), mtb);
        cbTypeSearch = new JComboBox<>(new String[]{"Tất cả", "Mã nhân viên","Họ nhân viên", "Tên nhân viên", "Ngày sinh", "Giới tính", "Số điện thoại","Quyền","Lương", "Trạng thái"});

        JPanel plHeader = new JPanel();
        JPanel plTim = new JPanel();
        plTim.setBorder(BorderFactory.createTitledBorder("Tìm kiếm"));
        txTim.setBorder(BorderFactory.createTitledBorder("Tất cả")); // tạo border rỗng
        plTim.add(cbTypeSearch);
        plTim.add(txTim);
        plHeader.add(plTim);

        // pl tim khoang ngay
        JPanel plTimKiemKhoangNgay = new JPanel();
        plTimKiemKhoangNgay.setBorder(BorderFactory.createTitledBorder("Ngày sinh:"));
        plTimKiemKhoangNgay.add(txKhoangNgay1);
        plTimKiemKhoangNgay.add(dPicker1);
        plTimKiemKhoangNgay.add(txKhoangNgay2);
        plTimKiemKhoangNgay.add(dPicker2);
        plHeader.add(plTimKiemKhoangNgay);

        btnRefresh.setIcon(new ImageIcon("src/Image/sync_50px.png"));
        plHeader.add(btnRefresh);

        cbTypeSearch.addActionListener((ActionEvent e) -> {
            txTim.setBorder(BorderFactory.createTitledBorder(cbTypeSearch.getSelectedItem().toString()));
            txTim.requestFocus();
            if (!txTim.getText().equals("")) {
                txSearchOnChange();
            }
        });

        btnRefresh.addActionListener((ae) -> {
            refresh();
        });

        dPicker1.addDateChangeListener((dce) -> {
            txKhoangNgay1.setText(dPicker1.getDateStringOrEmptyString());
        });
        dPicker2.addDateChangeListener((dce) -> {
            txKhoangNgay2.setText(dPicker2.getDateStringOrEmptyString());
        });

        // https://stackoverflow.com/questions/3953208/value-change-listener-to-jtextfield
        addDocumentListener(txTim);
        addDocumentListener(txKhoangNgay1);
        addDocumentListener(txKhoangNgay2);

        //=========== add all to this jpanel ===========
        this.add(plHeader, BorderLayout.NORTH);
        this.add(mtb, BorderLayout.CENTER);
        //lay nv khi select;
        mtb.getTable().getSelectionModel().addListSelectionListener((e) -> {
            String manv = this.getSelectedRow(1);
            nvSua = qlNhanVien.getNV(manv);
        });
        //thay đổi cell's data;
        
    }
    private void setDataToTable(ArrayList<NhanVienDTO> data, MyTable table) {
        table.clear();
        int stt = 1; // lưu số thứ tự dòng hiện tại
        Boolean hienNhanVienAn = true;
        for (NhanVienDTO nv : data) {
            if(!nv.getMaNhanVien().equals("ADMIN"))
            if (hienNhanVienAn || nv.getTrangThai() == 0) {
                table.addRow(new String[]{
                    String.valueOf(stt),
                    nv.getMaNhanVien(),
                    nv.getHoNhanVien(),
                    nv.getTenNhanVien(),
                    nv.getNgaySinh().toString(),
                    nv.getGioiTinh(),
                    nv.getSdt(),
                    nv.getTenQuyen(),
                    nv.getLuong()+"",
                    nv.getTrangThai()==0?"Ẩn":"Hiện",
                });
                stt++;
            }

        }
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
        setDataToTable(qlNhanVien.search(txTim.getText(), cbTypeSearch.getSelectedItem().toString(), ngay1, ngay2), mtb);
    }
    public void refresh() {
        qlNhanVien.getData();
        setDataToTable(qlNhanVien.getDsnv(), mtb);
        txTim.setText("");
        txKhoangNgay1.setText("");
        txKhoangNgay2.setText("");
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
}
