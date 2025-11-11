package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class frmTrangChu extends JFrame implements ActionListener {
    JButton btnTrangchu, btnBanhang, btnHoadon, btnSanpham, btnThongke, btnNhanvien;
    CardLayout card;
    JPanel pcenter; // nơi chứa các màn hình con

    public frmTrangChu() {
        setTitle("Trang Chủ");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // === MÀU CHỦ ĐẠO ===
        Color colorSidebar = new Color(150, 111, 91);
        Color colorHover = new Color(180, 135, 105);
        Color colorMain = new Color(245, 222, 179);

        // === NORTH (Thanh trên cùng) ===
        JPanel pnNorth = new JPanel(new BorderLayout());
        pnNorth.setBackground(new Color(143, 101, 82));
        pnNorth.setPreferredSize(new Dimension(0, 40));

        JPanel pnNorthLeft = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        pnNorthLeft.setOpaque(false);

        JButton btnGiaoca = new JButton("Giao ca");
        JButton btnDoimatkhau = new JButton("Đổi mật khẩu");
        JButton btnDangxuat = new JButton("Đăng xuất");

        Color btnColor = new Color(245, 222, 179);
        for (JButton b : new JButton[]{btnGiaoca, btnDoimatkhau, btnDangxuat}) {
            b.setBackground(btnColor);
            b.setFocusPainted(false);
            b.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        }

        pnNorthLeft.add(btnGiaoca);
        pnNorthLeft.add(btnDoimatkhau);
        pnNorthLeft.add(btnDangxuat);

        JPanel pnNorthRight = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 5));
        pnNorthRight.setOpaque(false);
        JLabel lblUser = new JLabel("Lâm Văn Kỳ - Quản lí");
        lblUser.setForeground(Color.WHITE);
        JLabel lblTime = new JLabel("11:30 AM");
        lblTime.setForeground(Color.WHITE);

        pnNorthRight.add(lblUser);
        pnNorthRight.add(lblTime);

        pnNorth.add(pnNorthLeft, BorderLayout.WEST);
        pnNorth.add(pnNorthRight, BorderLayout.EAST);

        // === WEST SIDEBAR ===
        JPanel pwest = new JPanel(new GridBagLayout());
        pwest.setBackground(colorSidebar);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;

        // Logo nhỏ góc trái
        ImageIcon logo = new ImageIcon("src/pic/logo.jpeg");
        Image scaled = logo.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        JLabel lblLogo = new JLabel(new ImageIcon(scaled));
        JPanel plogo = new JPanel();
        plogo.setBackground(colorSidebar);
        plogo.add(lblLogo);

        gbc.gridy = 0;
        pwest.add(plogo, gbc);

        Font f = new Font("Segoe UI", Font.PLAIN, 14);
        Dimension btnSize = new Dimension(180, 40);

        // Các nút menu
        btnTrangchu = createFlatButton("Trang chủ", f, colorSidebar, colorHover, btnSize);
        btnBanhang = createFlatButton("Bán hàng", f, colorSidebar, colorHover, btnSize);
        btnHoadon = createFlatButton("Hóa đơn", f, colorSidebar, colorHover, btnSize);
        btnSanpham = createFlatButton("Sản phẩm", f, colorSidebar, colorHover, btnSize);
        btnThongke = createFlatButton("Thống kê", f, colorSidebar, colorHover, btnSize);
        btnNhanvien = createFlatButton("Nhân viên", f, colorSidebar, colorHover, btnSize);

        gbc.gridy = 1; pwest.add(btnTrangchu, gbc);
        gbc.gridy = 2; pwest.add(btnBanhang, gbc);
        gbc.gridy = 3; pwest.add(btnHoadon, gbc);
        gbc.gridy = 4; pwest.add(btnSanpham, gbc);
        gbc.gridy = 5; pwest.add(btnThongke, gbc);
        gbc.gridy = 6; pwest.add(btnNhanvien, gbc);

        // === CENTER (NỘI DUNG CHÍNH) ===
        card = new CardLayout();
        pcenter = new JPanel(card);

        // Trang chủ mặc định
        JPanel homePanel = new JPanel(new BorderLayout());
        homePanel.setBackground(colorMain);
        JLabel lblHome = new JLabel("Trang chủ", SwingConstants.CENTER);
        lblHome.setFont(new Font("Segoe UI", Font.BOLD, 22));
        homePanel.add(lblHome, BorderLayout.CENTER);

        // Thêm frmHoaDon (JPanel)
        frmHoaDon hoaDonPanel = new frmHoaDon();

        pcenter.add(homePanel, "Trang chủ");
        pcenter.add(hoaDonPanel, "Hóa đơn");

        // === GÁN ACTION ===
        btnTrangchu.addActionListener(this);
        btnHoadon.addActionListener(this);

        // === ADD TO FRAME ===
        add(pnNorth, BorderLayout.NORTH);
        add(pwest, BorderLayout.WEST);
        add(pcenter, BorderLayout.CENTER);

        setVisible(true);
    }

    // === NÚT MENU PHẲNG ===
    private JButton createFlatButton(String text, Font font, Color bg, Color hover, Dimension size) {
        JButton btn = new JButton(text);
        btn.setFont(font);
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        btn.setPreferredSize(size);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) { btn.setBackground(hover); }
            @Override
            public void mouseExited(MouseEvent e) { btn.setBackground(bg); }
        });
        return btn;
    }

    // === SỰ KIỆN CHUYỂN MÀN HÌNH ===
    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == btnTrangchu) {
            card.show(pcenter, "Trang chủ");
        } else if (obj == btnHoadon) {
            card.show(pcenter, "Hóa đơn");
        }
    }

    public static void main(String[] args) {
        new frmTrangChu();
    }
}
