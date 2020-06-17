package GUI;

import DTO.NhanVienDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Comparator;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.event.RowSorterEvent;
import javax.swing.event.RowSorterListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class MyTable extends JPanel {

    JTable tb;
    DefaultTableModel tbModel;
    JScrollPane pane;

    public MyTable() {
        setLayout(new BorderLayout());

        tb = new JTable();
        tbModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int colum) {
                return false;
            }
        };
        pane = new JScrollPane(tb);
        pane.getVerticalScrollBar().setUnitIncrement(8);
        //editcell unennable
        tb.setFillsViewportHeight(true);
        tb.setFont(new Font("Segoe UI", 0, 16));
        tb.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));
        tb.setRowHeight(40);
        // color
        int bgColor = 235;
        int color = 0;
        tb.getTableHeader().setBackground(new Color(bgColor, bgColor, bgColor));
        tb.getTableHeader().setForeground(new Color(color, color, color));
        tb.setBackground(new Color(bgColor, bgColor, bgColor));
        tb.setForeground(new Color(color, color, color));
        tb.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(pane, BorderLayout.CENTER);
    }

    public void addRowNhanVien(NhanVienDTO nv) {
        addRow(new String[]{nv.getMaNhanVien(), nv.getHoNhanVien(), nv.getTenNhanVien(), nv.getNgaySinh().toString(), nv.getGioiTinh(), nv.getSdt(), nv.getMaQuyen(), nv.getLuong() + "", nv.getTrangThai() + ""});
    }

    public void setHeaders(String[] headers) {
        tbModel.setColumnIdentifiers(headers);
        tb.setModel(tbModel);
    }

    public void setHeaders(ArrayList<String> headers) {
        tbModel.setColumnIdentifiers(headers.toArray());
        tb.setModel(tbModel);
    }

    // https://stackoverflow.com/questions/7433602/how-to-center-in-jtable-cell-a-value
    public void setAlignment(int column, int align) {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(align);
        tb.getColumnModel().getColumn(column).setCellRenderer(centerRenderer);
    }

    // https://stackoverflow.com/questions/28823670/how-to-sort-jtable-in-shortest-way
    public void setupSort() {
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(tb.getModel());
        ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<>();
        for (int i = 0; i < tbModel.getColumnCount(); i++) {
            sortKeys.add(new RowSorter.SortKey(i, SortOrder.ASCENDING));
            sorter.setComparator(i, new Comparator<String>() {

                @Override
                public int compare(String name1, String name2) {
                    try {
                        int so1 = Integer.parseInt(name1);
                        int so2 = Integer.parseInt(name2);
                        if (so1 == so2) {
                            return 0;
                        }
                        if (so1 < so2) {
                            return -1;
                        }
                        if (so1 > so2) {
                            return 1;
                        }
                    } catch (NumberFormatException e) {
                        try {
                            name1 = name1.replace(",", "");
                            name2 = name2.replace(",", "");
                            int so1 = Integer.parseInt(name1);
                            int so2 = Integer.parseInt(name2);
                            if (so1 == so2) {
                                return 0;
                            }
                            if (so1 < so2) {
                                return -1;
                            }
                            if (so1 > so2) {
                                return 1;
                            }
                        } catch (Exception ex) {
                            try {
                                name1 = name1.substring(2);
                                name2 = name2.substring(2);
                                int so1 = Integer.parseInt(name1);
                                int so2 = Integer.parseInt(name2);
                                if (so1 == so2) {
                                    return 0;
                                }
                                if (so1 < so2) {
                                    return -1;
                                }
                                if (so1 > so2) {
                                    return 1;
                                }
                            } catch (Exception exx) {
                                try {
                                    name1 = name1.substring(3);
                                    name2 = name2.substring(3);
                                    int so1 = Integer.parseInt(name1);
                                    int so2 = Integer.parseInt(name2);
                                    if (so1 == so2) {
                                        return 0;
                                    }
                                    if (so1 < so2) {
                                        return -1;
                                    }
                                    if (so1 > so2) {
                                        return 1;
                                    }
                                } catch (Exception es) {
                                }
                            }
                            return name1.compareTo(name2);
                        }
                    }
                    return name1.compareTo(name2);
                }
            });
        }
        sorter.setSortKeys(sortKeys);
        tb.setRowSorter(sorter);

        sorter.addRowSorterListener(new RowSorterListener() {
            @Override
            public void sorterChanged(RowSorterEvent evt) {
            }
        });
    }

    public void addRow(String[] data) {
        tbModel.addRow(data);
    }

    public JTable getTable() {
        return tb;
    }

    public DefaultTableModel getModel() {
        return tbModel;
    }

    public void clear() {
        tbModel.setRowCount(0);
    }

    // https://www.codejava.net/java-se/swing/setting-column-width-and-row-height-for-jtable
    public void setColumnsWidth(double[] percentages) {

        double total = 0;
        for (int i = 0; i < tb.getColumnModel().getColumnCount(); i++) {
            total += percentages[i];
        }

        for (int i = 0; i < tb.getColumnModel().getColumnCount(); i++) {
            TableColumn column = tb.getColumnModel().getColumn(i);
            column.setPreferredWidth((int) (getPreferredSize().width * (percentages[i] / total)));
        }
    }

    //https://stackoverflow.com/questions/17627431/auto-resizing-the-jtable-column-widths
    public void resizeColumnWidth() {
        final TableColumnModel columnModel = tb.getColumnModel();
        for (int column = 0; column < tb.getColumnCount(); column++) {
            int width = 15; // Min width
            for (int row = 0; row < tb.getRowCount(); row++) {
                TableCellRenderer renderer = tb.getCellRenderer(row, column);
                Component comp = tb.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width + 1, width);
            }

            width += 15;
            columnModel.getColumn(column).setPreferredWidth(width);
        }
        tb.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    }
}

/**
 * A JTable that draws a zebra striped background.
 */
//http://nadeausoftware.com/articles/2008/01/java_tip_how_add_zebra_background_stripes_jtable

