/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.HienThi;

import BUS.*;
import DTO.*;
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

public class HienThiThuongHieu extends FormHienThi {

    ThuongHieuBUS qlThuongHieu = new ThuongHieuBUS();
    JTextField txTim = new JTextField(15);
    JComboBox<String> cbTypeSearch;
    JButton btnRefresh = new JButton("Làm mới");
    ThuongHieuDTO thsua = new ThuongHieuDTO();

    public HienThiThuongHieu() {
        setLayout(new BorderLayout());
        //khoang ngay

        //button
        btnRefresh.setIcon(new ImageIcon("src/Image/sync_50px.png"));
        mtb = new MyTable();
        mtb.setPreferredSize(new Dimension(1200 - 250, 600));
        mtb.setHeaders(new String[]{"STT", "Mã thương hiệu", "Tên thương hiệu", "Mô tả"});
        mtb.setColumnsWidth(new double[]{.5, 1.5, 2.5, 1.3, 3, 1.5, 1, 1, 1, 1});
        mtb.setAlignment(0, JLabel.CENTER);
        mtb.setupSort();
        setDataToTable(qlThuongHieu.getTH(), mtb);
        cbTypeSearch = new JComboBox<>(new String[]{"Tất cả", "Mã thương hiệu", "Tên thương hiệu"});
        JPanel plHeader = new JPanel();
        JPanel plTim = new JPanel();
        plTim.setBorder(BorderFactory.createTitledBorder("Tìm kiếm"));
        txTim.setBorder(BorderFactory.createTitledBorder("Tất cả")); // tạo border rỗng
        plTim.add(cbTypeSearch);
        plTim.add(txTim);
        plHeader.add(plTim);

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

        // https://stackoverflow.com/questions/3953208/value-change-listener-to-jtextfield
        addDocumentListener(txTim);

        //=========== add all to this jpanel ===========
        this.add(plHeader, BorderLayout.NORTH);
        this.add(mtb, BorderLayout.CENTER);
        //lay nv khi select;
        mtb.getTable().getSelectionModel().addListSelectionListener((e) -> {
            String maThuongHieu = this.getSelectedRow(1);
            thsua = qlThuongHieu.getThuongHieu(maThuongHieu);
        });
        //thay đổi cell's data;

    }

    private void setDataToTable(ArrayList<ThuongHieuDTO> data, MyTable table) {
        table.clear();
        int stt = 1; // lưu số thứ tự dòng hiện tại
        //mtb.setHeaders(new String[]{"STT", "Mã nhà cung cấp", "Tên nhà cung cấp", "Địa chỉ", "Email"});
        for (ThuongHieuDTO th : data) {

            table.addRow(new String[]{
                String.valueOf(stt),
                th.getMaThuongHieu(),
                th.getTenThuongHieu(),
                th.getMoTa(),});
            stt++;

        }
    }

    private void txSearchOnChange() {
        setDataToTable(qlThuongHieu.search(txTim.getText(), cbTypeSearch.getSelectedItem().toString()), mtb);
    }

    public void refresh() {
        qlThuongHieu.getData();
        setDataToTable(qlThuongHieu.getTH(), mtb);
        txTim.setText("");
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
