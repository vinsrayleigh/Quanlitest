
package GUI.HienThi;

import BUS.CTPhieuNhapBUS;
import DTO.CTPhieuNhapDTO;
import GUI.MyTable;
import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author minkuppj
 */
public class HienThiCTPhieuNhap extends JFrame{
    CTPhieuNhapBUS qlCTPN = new CTPhieuNhapBUS();
    MyTable tbCTPN = new MyTable();
    String maPN = "";
    public HienThiCTPhieuNhap(String maPN){
        setSize(700, 500);
        setLayout(new BorderLayout());
        this.maPN=maPN;
        String[] header = new String[]{
            "STT",
            "Mã phiếu nhập",
            "Mã sản phẩm",
            "Số lượng",
            "Đơn giá",
                "Thành tiền"
        };
        tbCTPN.setHeaders(header);
        setDataToTable(qlCTPN.Seacrh(maPN));
        add(tbCTPN,BorderLayout.CENTER);
    }
    public void setDataToTable(ArrayList<CTPhieuNhapDTO> list){
        tbCTPN.clear();
        int stt=1;
        for(CTPhieuNhapDTO ct: list){
            tbCTPN.addRow(new String[]{
                stt+"",
                ct.getMaPhieuNhap(),
                ct.getMaSanPham(),
                ct.getSoLuong()+"",
                ct.getDongia()+"",
                ct.getSoLuong()*ct.getDongia()+"",
            });
        }
    }
}
