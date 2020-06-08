package GUI.Quanli;

import javax.swing.JPanel;
import GUI.*;
import DAO.*;
import DTO.*;
import GUI.HienThi.ChonNhaCungCap;
import java.awt.BorderLayout;
public class NhapHangForm extends JPanel{
    ChonSanPham chonsp = new ChonSanPham();
    public PhieuNhap phieunhap = new PhieuNhap();
    public NhapHangForm(){
        setLayout(new BorderLayout());
        chonsp.setTaget(phieunhap,"PN");
        add(chonsp,BorderLayout.WEST);
        add(phieunhap,BorderLayout.EAST);
    }
}
