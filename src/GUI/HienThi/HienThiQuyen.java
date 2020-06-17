/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.HienThi;

import BUS.QuyenBUS;
import DTO.QuyenDTO;
import GUI.Button.RefreshButton;
import GUI.Button.SuaButton;
import GUI.Button.ThemButton;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.tree.DefaultTreeCellEditor;

/**
 *
 * @author phuon
 */
public class HienThiQuyen extends FormHienThi {

    QuyenBUS qlQuyen = new QuyenBUS();
    JTextField txTim = new JTextField(15);
    JComboBox<String> cbTypeSearch;
    RefreshButton btnRefresh = new RefreshButton();
    ChiTietQuyen ctQuyen = new ChiTietQuyen("xem");
    public HienThiQuyen() {
        setLayout(new BorderLayout());
        this.removeAll();
        JPanel plHeader = new JPanel();
        JPanel plTim = new JPanel();
        plTim.setBorder(BorderFactory.createTitledBorder("Tìm kiếm"));
        txTim.setBorder(BorderFactory.createTitledBorder("Tất cả")); // tạo border rỗng
        cbTypeSearch = new JComboBox<String>(new String[]{"Tất cả","Mã quyền","Tên quyền"});
        plTim.add(cbTypeSearch);
        plTim.add(txTim);
        plHeader.add(plTim);
        plHeader.add(btnRefresh);
        add(plHeader, BorderLayout.NORTH);
        mtb.setHeaders(new String[]{"STT", "Mã quyền", "Tên quyền"});
        mtb.setColumnsWidth(new double[]{.5, 1.5, 2.5});
        mtb.setAlignment(0, JLabel.CENTER);
        mtb.setupSort();
        setDataToTable(qlQuyen.list);
        JPanel plLeft = new JPanel(new BorderLayout());
        plLeft.add(mtb,BorderLayout.EAST);
        JPanel margin = new JPanel();
        margin.setPreferredSize(new Dimension(200, 0));
        plLeft.add(margin,BorderLayout.WEST);
        add(plLeft, BorderLayout.WEST);
        add(ctQuyen,BorderLayout.EAST);
        ctQuyen.setPreferredSize(new Dimension(800, 0));
        mtb.getTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = mtb.getTable().getSelectedRow();
                if(row>=0)
                ctQuyen.setData(qlQuyen.getQuyen((String) mtb.getTable().getValueAt(row, 1)));
            }
       });
        mtb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(getSelectedRow(2));
                int row = mtb.getTable().getSelectedRow();
                if(row>=0)
                ctQuyen.setData(qlQuyen.getQuyen(getSelectedRow(1)));
            }
       });
        btnRefresh.addActionListener((e) -> {
            setDataToTable(qlQuyen.list);
        });
    }
    public void refresh(){
        setDataToTable(qlQuyen.list);
    }
    public void setDataToTable(ArrayList<QuyenDTO> list) {
        int stt = 1;
        mtb.clear();
        for (QuyenDTO q : list) {
            mtb.addRow(new String[]{
                stt + "",
                q.getMaQuyen(),
                q.getTenQuyen()
            });
            stt++;
        }
    }
}
