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
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author phuon
 */
public class HienThiSanPham  extends FormHienThi{
    SanPhamBUS qlSanPham = new SanPhamBUS();
    JTextField txTim = new JTextField(15);
    JComboBox<String> cbTypeSearch;
    JButton btnRefresh = new JButton("Làm mới");
    NhanVienDTO nvSua = new NhanVienDTO();
    public HienThiSanPham(){
                mtb = new MyTable();
        mtb.setPreferredSize(new Dimension(1200 - 250, 600));
        //String maSanPham, String tenSanPham, double dongia, int soLuong, Date namSx, String maNCC, String maLoaiSP, String maThuongHieu, String image) {
        mtb.setHeaders(new String[]{"STT", "Mã sản phẩm","Tên sản phẩm", "Đ", "Ngày sinh", "Giới tính", "Số điện thoại","Quyền", "Lương","Trạng thái"});
        mtb.setColumnsWidth(new double[]{.5, 1.5, 2.5, 1.3, 3, 1.5, 1,1,1,1});
        mtb.setAlignment(0, JLabel.CENTER);
        mtb.setupSort();
        setDataToTable(qlSanPham.list, mtb);
        cbTypeSearch = new JComboBox<>(new String[]{"Tất cả", "Mã nhân viên","Họ nhân viên", "Tên nhân viên", "Ngày sinh", "Giới tính", "Số điện thoại","Quyền","Lương", "Trạng thái"});

        JPanel plHeader = new JPanel();
        JPanel plTim = new JPanel();
        plTim.setBorder(BorderFactory.createTitledBorder("Tìm kiếm"));
        txTim.setBorder(BorderFactory.createTitledBorder("Tất cả")); // tạo border rỗng
        plTim.add(cbTypeSearch);
        plTim.add(txTim);
        plHeader.add(plTim);
    }

    private void setDataToTable(ArrayList<SanPhamDTO> list, MyTable table) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        table.clear();
        int stt = 1; // lưu số thứ tự dòng hiện tại
        Boolean hienNhanVienAn = true;
        for (SanPhamDTO sp : list) {
                table.addRow(new String[]{
                    String.valueOf(stt),
                    sp.getMaSanPham(),
                    
                });
                stt++;
    }
    }
}
