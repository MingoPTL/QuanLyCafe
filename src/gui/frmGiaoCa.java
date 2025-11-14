package gui;

import dao.HoaDon_dao;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.text.NumberFormat;
import java.util.Locale;

public class frmGiaoCa extends JFrame {

    private JLabel lblThoiGianKetThuc;
    private JLabel lblTongTienHD;
    private JFrame parent;


    private HoaDon_dao hoaDonDao = new HoaDon_dao();
    private final NumberFormat moneyFormat = NumberFormat.getIntegerInstance(new Locale("vi", "VN"));

    public frmGiaoCa(frmTrangChu parent) {
        this.parent = parent;

        setTitle("Giao ca");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initUI();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        loadData();
    }

    private void initUI() {

        JPanel content = new JPanel(new BorderLayout(10, 10));
        content.setBorder(new EmptyBorder(12, 12, 12, 12));
        content.setBackground(new Color(252, 240, 236));
        setContentPane(content);

        JLabel title = new JLabel("GIAO CA", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 28));
        content.add(title, BorderLayout.NORTH);

        JPanel center = new JPanel(new GridBagLayout());
        center.setOpaque(false);
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(6, 6, 6, 6);
        g.anchor = GridBagConstraints.WEST;

        int row = 0;

        // ================= Thời gian kết thúc ca =================
        g.gridx = 0; g.gridy = row;
        center.add(new JLabel("Thời gian kết thúc:"), g);

        lblThoiGianKetThuc = new JLabel();
        lblThoiGianKetThuc.setFont(lblThoiGianKetThuc.getFont().deriveFont(Font.BOLD));

        g.gridx = 1;
        center.add(lblThoiGianKetThuc, g);
        row++;

        // ================= Tổng tiền hóa đơn =================
        g.gridx = 0; g.gridy = row;
        center.add(new JLabel("Tổng tiền hóa đơn:"), g);

        lblTongTienHD = new JLabel();
        lblTongTienHD.setFont(lblTongTienHD.getFont().deriveFont(Font.BOLD));

        g.gridx = 1;
        center.add(lblTongTienHD, g);
        row++;

        content.add(center, BorderLayout.CENTER);

        // ================= Buttons =================
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 8));
        btnPanel.setOpaque(false);

        JButton btnXacNhan = new JButton("Xác nhận");
        JButton btnHuy = new JButton("Hủy");

        btnPanel.add(btnXacNhan);
        btnPanel.add(btnHuy);

        content.add(btnPanel, BorderLayout.SOUTH);

        btnHuy.addActionListener(e -> dispose());

        btnXacNhan.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Bạn có chắc muốn giao ca?\n" +
                    "Thời gian: " + lblThoiGianKetThuc.getText() + "\n" +
                    "Tổng tiền: " + lblTongTienHD.getText(),
                    "Xác nhận",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
            	JOptionPane.showMessageDialog(this, "Giao ca thành công!");

                // Đóng frmGiaoCa
                this.dispose();

                // Đóng frmTrangChu
                if (parent != null) 
                    parent.dispose();

                // Mở lại frmDangNhap
                new frmDangNhap();
            }
        });
    }

    private void loadData() {
        // Thời gian thực
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy"));
        lblThoiGianKetThuc.setText(time);

        // Tổng tiền hóa đơn
        double tong = 0;
        try {
            tong = hoaDonDao.getTongTienAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        lblTongTienHD.setText(moneyFormat.format(tong) + " ₫");
    }
}
