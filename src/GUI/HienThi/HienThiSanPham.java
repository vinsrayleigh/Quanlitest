/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.HienThi;

import BUS.SanPhamBUS;
import DTO.NhanVienDTO;
import DTO.SanPhamDTO;
import GUI.MyTable;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author phuon
 */
public class HienThiSanPham extends FormHienThi {

    SanPhamBUS qlSanPham = new SanPhamBUS();
    JTextField txTim = new JTextField(15);
    JComboBox<String> cbTypeSearch;
    JButton btnRefresh = new JButton("Làm mới");
    NhanVienDTO nvSua = new NhanVienDTO();
    JTextField txGia1 = new JTextField(10);
    JTextField txGia2 = new JTextField(10);
    public HienThiSanPham() {
        mtb = new MyTable();
        //String maSanPham, String tenSanPham, double dongia, int soLuong, Date namSx, String maNCC, String maLoaiSP, String maThuongHieu, String image) {
        mtb.setHeaders(new String[]{"STT", "Mã sản phẩm", "Tên sản phẩm", "Loại sản phẩm", "Năm sx", "Nhà cung cấp", "Thương hiệu", "Đơn giá", "Số lượng","Mô tả", "Ảnh"});
        mtb.setColumnsWidth(new double[]{.5, 1.5, 2.5, 1.3, 3, 1.5, 1, 1, 1,1,1});
        mtb.setAlignment(0, JLabel.CENTER);
        mtb.setupSort();
        setDataToTable(qlSanPham.list, mtb);
        cbTypeSearch = new JComboBox<>(new String[]{"Tất cả", "Mã sản phẩm", "Tên sản phẩm", "Loại sản phẩm", "Năm sx", "Nhà cung cấp", "Thương hiệu"});
        JPanel plHeader = new JPanel();
        JPanel plTim = new JPanel();
        plTim.setBorder(BorderFactory.createTitledBorder("Tìm kiếm"));
        txTim.setBorder(BorderFactory.createTitledBorder("Tất cả"));
        plTim.add(cbTypeSearch);
        plTim.add(txTim);
        plHeader.add(plTim);
        JPanel plTimKiemKhoangGia = new JPanel();
        plTimKiemKhoangGia.setBorder(BorderFactory.createTitledBorder("Khoảng Giá"));
        txGia1.setBorder(BorderFactory.createTitledBorder("Từ"));
        txGia2.setBorder(BorderFactory.createTitledBorder("Đến"));
        plTimKiemKhoangGia.add(txGia1);
        plTimKiemKhoangGia.add(txGia2);
        plHeader.add(plTimKiemKhoangGia);
        btnRefresh.setIcon(new ImageIcon("src/Image/sync_50px.png"));
        plHeader.add(btnRefresh);
        cbTypeSearch.addActionListener((ActionEvent e) -> {
            txTim.setBorder(BorderFactory.createTitledBorder(cbTypeSearch.getSelectedItem().toString()));
            txTim.requestFocus();
            if (!txTim.getText().equals("")) {
                txSearchOnChange();
            }
        });
        btnRefresh.addActionListener((e) -> {
            refresh();
            txTim.setText("");
        });
        addDocumentListener(txTim);
        addDocumentListener(txGia1);
        addDocumentListener(txGia2);
        this.add(plHeader,BorderLayout.NORTH);
        this.add(mtb,BorderLayout.CENTER);
    }
    
    private void setDataToTable(ArrayList<SanPhamDTO> list, MyTable table) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        table.clear();
        int stt = 1; // lưu số thứ tự dòng hiện tại
//        "STT", "Mã sản phẩm", "Tên sản phẩm", "Loại sản phẩm", "Năm sx", "Nhà cung cấp", "Thương hiệu", "Đơn giá", "Số lượng","Mô tả", "Ảnh"});
        for (SanPhamDTO sp : list) {
            table.addRow(new String[]{
                String.valueOf(stt),
                sp.getMaSanPham(),
                sp.getTenSanPham(),
                sp.getTenLoaiSP(),
                sp.getNamSx().toString(),
                sp.getTenNCC(),
            sp.getTenThuongHieu(),
            sp.getDongia()+"000",
            sp.getSoLuong()+"",
            sp.getMota(),sp.getImage()});
            stt++;
        }
    }

    private void txSearchOnChange() {
        int gia1=0,gia2=0;
        try {
            gia1 = Integer.parseInt(txGia1.getText());
            txGia1.setForeground(Color.black);
        } catch (NumberFormatException e) {
            txGia1.setForeground(Color.red);
        }try {
            gia2 = Integer.parseInt(txGia2.getText());
            txGia2.setForeground(Color.black);
        } catch (NumberFormatException e) {
            txGia2.setForeground(Color.red);
        }
        setDataToTable(qlSanPham.search(txTim.getText().toLowerCase(), cbTypeSearch.getSelectedItem().toString(), 0, Integer.MAX_VALUE,gia1, gia2), mtb);
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
    private void refresh() {
        qlSanPham.getData();
        mtb.clear();
        setDataToTable(qlSanPham.list, mtb);
    }
}
