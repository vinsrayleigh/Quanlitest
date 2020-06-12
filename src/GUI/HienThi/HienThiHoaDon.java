
package GUI.HienThi;

import BUS.HoaDonBUS;
import BUS.KhachHangBUS;
import BUS.NhanVienBUS;
import BUS.QuyenBUS;
import BUS.Tool;
import DAO.KhachHangDAO;
import DTO.HoaDonDTO;
import DTO.KhachHangDTO;
import GUI.Button.DateButton;
import GUI.Button.ExportExcelButton;
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

public class HienThiHoaDon extends FormHienThi{
    HoaDonBUS qlHoaDon = new HoaDonBUS();
    NhanVienBUS qlNhanVien = new NhanVienBUS();
    KhachHangBUS qlkhachhang = new KhachHangBUS();
    JTextField txTim = new JTextField(15);
    JComboBox<String> cbTypeSearch;
    JButton btnRefresh = new JButton("Làm mới");
    KhachHangDTO nvSua = new KhachHangDTO();
    JTextField txKhoangNgay1 = new JTextField(8);
    JTextField txKhoangNgay2 = new JTextField(8);
    DatePicker dPicker1;
    DatePicker dPicker2;
    JTextField txKhoangGia1 = new JTextField(8);
    JTextField txKhoangGia2 = new JTextField(8);
    HienThiCTHoaDon target;
    ExportExcelButton btnXuatExcel = new ExportExcelButton();
    public HienThiHoaDon(){
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
        txKhoangGia1.setBorder(BorderFactory.createTitledBorder("Từ:"));
        txKhoangGia2.setBorder(BorderFactory.createTitledBorder("Đến:"));
        //String maHoaDon, String maNhanVien, String maKhachHang, int tongTien, Date ngayLap, String maKM, double giamGia) {
        mtb = new MyTable();
        mtb.setPreferredSize(new Dimension(1200 - 250, 600));
        mtb.setHeaders(new String[]{"STT", "Mã hóa đơn", "Tên nhân viên", "Tên Khách Hàng", "Tổng tiền", "Ngày lập", "Mã khuyến mãi", "Giảm giá"});
        mtb.setColumnsWidth(new double[]{.5, 1.5, 2.5, 1.5, 3, 2.5, 1.5, 1});
        mtb.setAlignment(0, JLabel.CENTER);
        mtb.setupSort();
        setDataToTable(qlHoaDon.list, mtb);
        cbTypeSearch = new JComboBox<>(new String[]{"Tất cả", "Mã hóa đơn", "Nhân viên", "Khách hàng", "Khuyến mãi"});
        
        JPanel plHeader = new JPanel();
        JPanel plTim = new JPanel();
        plTim.setBorder(BorderFactory.createTitledBorder("Tìm kiếm"));
        txTim.setBorder(BorderFactory.createTitledBorder("Tất cả")); // tạo border rỗng
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
        JPanel plTimKiemKhoangGia = new JPanel();
        plTimKiemKhoangGia.setBorder(BorderFactory.createTitledBorder("Khoảng giá (đơn vị nghìn đồng: 000vnđ):"));
        plTimKiemKhoangGia.add(txKhoangGia1);
        plTimKiemKhoangGia.add(txKhoangGia2);
        plTimKiemKhoangGia.setPreferredSize(new Dimension(270, 80));
        plHeader.add(plTimKiemKhoangGia);
        btnRefresh.setIcon(new ImageIcon("src/Image/sync_50px.png"));
        plHeader.add(btnRefresh);
        plHeader.add(btnXuatExcel);

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
        addDocumentListener(txKhoangGia1);
        addDocumentListener(txKhoangGia2);

        //=========== add all to this jpanel ===========
        this.add(plHeader, BorderLayout.NORTH);
        this.add(mtb, BorderLayout.CENTER);
        //lay nv khi select;
        mtb.getTable().getSelectionModel().addListSelectionListener((e) -> {
            String manv = this.getSelectedRow(1);
            nvSua = qlkhachhang.getKH(manv);
        });
        //thay đổi cell's data;
        
    }
    private void setDataToTable(ArrayList<HoaDonDTO> data, MyTable table) {
        table.clear();
        int stt = 1; // lưu số thứ tự dòng hiện tại
        //Boolean hienKhachHangAn = true;
        Boolean hienKhachHangAn = true;
        for (HoaDonDTO hd : data) {
//            "Mã hóa đơn", "Tên nhân viên", "Tên Khách Hàng", "Tổng tiền", "Ngày lập", "Mã khuyến mãi", "Giảm giá"});
            if (hienKhachHangAn ) {
                table.addRow(new String[]{
                    String.valueOf(stt),
                    hd.getMaHoaDon(),
                    qlNhanVien.getNV(hd.getMaNhanVien()).getFullFame(),
                    qlkhachhang.getKH(hd.getMaKhachHang()).getFullName(),
                    Tool.getMonney(hd.getTongTien())+",000",
                    hd.getNgayLap().toString(),
                    hd.getMaKM(),
                    hd.getGiamGia()+""
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
        setDataToTable(qlHoaDon.Search(txTim.getText(), cbTypeSearch.getSelectedItem().toString(), ngay1, ngay2,Tool.getInt(txKhoangGia1.getText()),Tool.getInt(txKhoangGia2.getText())), mtb);
    }
    public void refresh() {
        qlkhachhang.getData();
        setDataToTable(qlHoaDon.list, mtb);
        txTim.setText("");
        txKhoangNgay1.setText("");
        txKhoangNgay2.setText("");
        txKhoangGia1.setText("");
        txKhoangGia2.setText("");
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
