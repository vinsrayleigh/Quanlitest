package GUI.NavBar;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class NavBarButton extends NavBarItem {

    protected Color bgHover, colorHover;
    protected Boolean actived = false;
    protected JLabel lbIcon;

    public NavBarButton(Rectangle rec, String text, String iconUrl) {
        super(rec, text);
        this.bgHover = new Color(49, 49, 49);
        this.colorHover = new Color(255, 255, 255);

        // icon
        setIconFromString(iconUrl);

        // mouse event
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                setHovered(true);
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent me) {
                setHovered(false);
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            } 
        });
    }

    public void setHovered(Boolean hovering) {
        if (!actived) {
            if (hovering) {
                setBackground(bgHover);
                lbLabel.setForeground(colorHover);
            } else {
                setBackground(bgDefault);
                lbLabel.setForeground(colorDefault);
            }
        }
    }
    
    public void setBgHover(Color bgHover) {
        this.bgHover = bgHover;
    }

    public void setColorHover(Color colorHover) {
        this.colorHover = colorHover;
    }

    // ======== setup Icon ==========
    public void setIconFromString(String iconUrl) {
        lbIcon = new JLabel();
        lbIcon.setSize(this.getHeight()-20,this.getHeight()-20);
        //lbIcon.setIcon(new ImageIcon(getClass().getResource("/Quanli/Image/" + iconUrl)));
        setPicture(lbIcon,"src/Image/"+ iconUrl);
        //lbIcon.setIcon(new ImageIcon("src/Image/"+iconUrl));
        add(lbIcon);
    }
    public void setPicture(JLabel label, String filename) {
        try {
            BufferedImage image = ImageIO.read(new File(filename));
            int x = label.getSize().width;
            int y = label.getSize().height;
            int ix = image.getWidth();
            int iy = image.getHeight();
            int dx = 0;
            int dy = 0;
            if (x / y > ix / iy) {
                dy = y;
                dx = dy * ix / iy;
            } else {
                dx = x;
                dy = dx * iy / ix;
            }
            ImageIcon icon = new ImageIcon(image.getScaledInstance(dx, dy, BufferedImage.SCALE_SMOOTH));
            label.setIcon(icon);
        } catch (IOException ex) {
        }

    }
    public void setIconLocation(Rectangle rec) {
        lbIcon.setBounds(rec);
    }

    // ===== calculate position of icon and label ======
    public void relocate() {
        Rectangle rec = getBounds();
        lbIcon.setBounds(rec.width / 10, rec.height / 4, rec.width / 4, (int) (rec.height / 1.75));
        lbLabel.setBounds(rec.width / 4, rec.height / 4, rec.width * 3 / 4, (int) (rec.height / 1.75));
    }
    
    // dùng cho nút logout
    public void relocate2() {
        Rectangle rec = getBounds();
        lbIcon.setBounds(5, rec.height / 4, rec.width / 4, (int) (rec.height / 1.75));
        lbLabel.setBounds(rec.width / 4, rec.height / 4, rec.width * 3 / 4, (int) (rec.height / 1.75));
    }

    // ======== active ==========
    public void setActive(Boolean stage) {
        this.actived = stage;
        if (this.actived) {
            this.setBackground(new Color(20, 20, 20));
            lbLabel.setForeground(Color.white);
        } else {
            this.setBackground(bgDefault);
            lbLabel.setForeground(colorDefault);
        }
    }

    public Boolean getActive() {
        return this.actived;
    }
    
    public void switchActive() {
        this.actived = !this.actived;
        setActive(actived);
    }
}
