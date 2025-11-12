package gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

public class frmThongKe extends JPanel {

    private JLabel lblDoanhThu, lblHoaDon, lblBanChay;
    private JTable tblThongKe;
    private DefaultTableModel model;

    public frmThongKe() {
        // Thiết lập layout tổng: BoxLayout theo chiều dọc
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(245, 222, 179)); // Màu be chủ đạo

        // ======== TIÊU ĐỀ ========
        JLabel lblTitle = new JLabel("Trang thống kê", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitle.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(lblTitle);

        // ======== KHỐI THẺ THỐNG KÊ ========
        JPanel pnlCards = new JPanel();
        pnlCards.setLayout(new BoxLayout(pnlCards, BoxLayout.X_AXIS));
        pnlCards.setBackground(new Color(245, 222, 179));
        pnlCards.setBorder(BorderFactory.createEmptyBorder(10, 30, 20, 30));

        lblDoanhThu = createStatCard("Tổng doanh thu", "Đang cập nhật...");
        lblHoaDon = createStatCard("Số hóa đơn", "Đang cập nhật...");
        lblBanChay = createStatCard("Sản phẩm bán chạy", "Đang tải...");

        pnlCards.add(lblDoanhThu);
        pnlCards.add(Box.createHorizontalStrut(20)); // khoảng cách
        pnlCards.add(lblHoaDon);
        pnlCards.add(Box.createHorizontalStrut(20));
        pnlCards.add(lblBanChay);

        add(pnlCards);

        // ======== BẢNG THỐNG KÊ SẢN PHẨM ========
        JPanel pnlTable = new JPanel();
        pnlTable.setLayout(new BoxLayout(pnlTable, BoxLayout.Y_AXIS));
        pnlTable.setBackground(new Color(245, 222, 179));
        pnlTable.setBorder(BorderFactory.createTitledBorder(
                new LineBorder(new Color(205, 133, 63), 2, true),
                "Thống kê sản phẩm",
                TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 16),
                new Color(90, 50, 30)
        ));

        String[] columns = {"Sản phẩm", "Số lượng bán", "Doanh thu (VNĐ)"};
        model = new DefaultTableModel(columns, 0);
        tblThongKe = new JTable(model);
        tblThongKe.setRowHeight(28);
        tblThongKe.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tblThongKe.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tblThongKe.getTableHeader().setBackground(new Color(210, 180, 140));
        tblThongKe.getTableHeader().setReorderingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(tblThongKe);
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        scrollPane.setPreferredSize(new Dimension(800, 300));
        pnlTable.add(scrollPane);

        add(pnlTable);

        // ======== NÚT LÀM MỚI ========
        JButton btnRefresh = new JButton("🔄 Làm mới dữ liệu");
        btnRefresh.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnRefresh.setBackground(new Color(160, 82, 45));
        btnRefresh.setForeground(Color.WHITE);
        btnRefresh.setFocusPainted(false);
        btnRefresh.setBorder(new EmptyBorder(10, 15, 10, 15));
        btnRefresh.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnRefresh.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Placeholder action
        btnRefresh.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Tính năng cập nhật dữ liệu sẽ được kết nối DAO sau!");
        });

        add(Box.createVerticalStrut(15));
        add(btnRefresh);
        add(Box.createVerticalStrut(15));
    }

    // ======== HÀM HỖ TRỢ TẠO THẺ ========
    private JLabel createStatCard(String title, String value) {
        JLabel lbl = new JLabel("<html><center><b>" + title + "</b><br>" + value + "</center></html>");
        lbl.setOpaque(true);
        lbl.setBackground(new Color(205, 133, 63)); // nâu đất
        lbl.setForeground(Color.WHITE);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        lbl.setVerticalAlignment(SwingConstants.CENTER);
        lbl.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(160, 82, 45), 2, true),
                new EmptyBorder(20, 10, 20, 10)
        ));
        lbl.setPreferredSize(new Dimension(220, 80));
        return lbl;
    }

    // ======== HÀM TEST GUI RIÊNG LẺ ========
    public static void main(String[] args) {
        JFrame f = new JFrame("Thống kê");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(900, 600);
        f.setLocationRelativeTo(null);
        f.setContentPane(new frmThongKe());
        f.setVisible(true);
    }
}
