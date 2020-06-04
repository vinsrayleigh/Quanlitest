/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.HienThi;
import BUS.KhuyenMaiBUS;
import BUS.NhanVienBUS;
import BUS.QuyenBUS;
import BUS.Tool;
import DAO.NhanVienDAO;
import DTO.KhuyenMaiDTO;
import DTO.NhanVienDTO;
import GUI.Button.DateButton;
import GUI.Button.RefreshButton;
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
/**
 *
 * @author phuon
 */
public class HienThiKhuyenMai extends FormHienThi{
    KhuyenMaiBUS qlKhuyenMai = new KhuyenMaiBUS();
    JTextField txTim = new JTextField(15);
    JComboBox<String> cbTypeSearch;
    RefreshButton btnrefresh = new RefreshButton();
    JTextField txKhoangNgay1 = new JTextField(8);
    JTextField txKhoangNgay2 = new JTextField(8);
    DatePicker dPicker1;
    DatePicker dPicker2;
    public HienThiKhuyenMai(){
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
        mtb = new MyTable();
        mtb.setPreferredSize(new Dimension(1200 - 250, 600));
        mtb.setHeaders(new String[]{"STT", "Mã khuyến mại","Tên khuyến mại", "Mã sản phẩm", "Giảm giá", "Ngày bắt đầu", "Ngày kết thúc","Chi tiết"});
        mtb.setColumnsWidth(new double[]{.5, 1.5, 2.5, 1.3, 3, 1.5, 1,1,});
        mtb.setAlignment(0, JLabel.CENTER);
        mtb.setupSort();
        
        setDataToTable(qlKhuyenMai.list, mtb);
        cbTypeSearch = new JComboBox<>(new String[]{"Tất cả", "Mã khuyến mại","Tên khuyến mại", "Mã sản phẩm"});

        JPanel plHeader = new JPanel();
        JPanel plTim = new JPanel();
        plTim.setBorder(BorderFactory.createTitledBorder("Tìm kiếm"));
        txTim.setBorder(BorderFactory.createTitledBorder("Tất cả")); // tạo border rỗng
        plTim.add(cbTypeSearch);
        plTim.add(txTim);
        plHeader.add(plTim);
        plHeader.add(btnrefresh);

        cbTypeSearch.addActionListener((ActionEvent e) -> {
            txTim.setBorder(BorderFactory.createTitledBorder(cbTypeSearch.getSelectedItem().toString()));
            txTim.requestFocus();
            if (!txTim.getText().equals("")) {
                txSearchOnChange();
            }
        });

        btnrefresh.addActionListener((ae) -> {
            refresh();
        });
        // https://stackoverflow.com/questions/3953208/value-change-listener-to-jtextfield
        addDocumentListener(txTim);
        addDocumentListener(txKhoangNgay1);
        addDocumentListener(txKhoangNgay2);

        //=========== add all to this jpanel ===========
        this.add(plHeader, BorderLayout.NORTH);
        this.add(mtb, BorderLayout.CENTER);
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
    private void setDataToTable(ArrayList<KhuyenMaiDTO> list, MyTable mtb) {
        int stt=1;
        mtb.clear();
        for(KhuyenMaiDTO km:list){
            mtb.addRow(new String[]{
                stt+++"",
                km.getMakhuyenmai(),
                km.getTenkhuyenmai(),
                km.getMaSanPham(),
                km.getGiamgia()+"",
                km.getNgaybatdau().toString(),
                km.getNgayketthuc().toString(),
                km.getChitiet()
            });
        }
    }
//String makhuyenmai, String tenkhuyenmai, String maSanPham, String giamgia, Date ngaybatdau, Date ngayketthuc, String chitiet) {
    private void txSearchOnChange() {
        setDataToTable(qlKhuyenMai.search(txTim.getText(), cbTypeSearch.getSelectedItem().toString()), mtb);
   
    }

    private void refresh() {
        qlKhuyenMai.getData();
        setDataToTable(qlKhuyenMai.list, mtb);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
