package gui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import connectDB.ConnectDB;
import dao.ban_dao;
import entity.Ban;


public class frmTrangChu extends JFrame implements ActionListener {
    JButton btnTrangchu, btnBanhang, btnHoadon, btnSanpham, btnThongke, btnNhanvien,btnGiaoca,btnDangxuat;
    CardLayout card;
    JPanel pcenter; // nơi chứa các màn hình con
    
    private frmHoaDon hoaDonPanel;
    private frmSanPham sanphamPanel;
    private frmBanHang banhangPanel;
    private frmThongKe thongkePanel;
    private frmNhanVien nhanvienPanel;
    private JPanel createHomePanel() {
        Color colorMain = new Color(245, 222, 179);

        JPanel homePanel = new JPanel(new BorderLayout());
        homePanel.setBackground(colorMain);

        // PANEL TRUNG TÂM CHỨA BÀN
        JPanel center = new JPanel(new GridLayout(0, 4, 20, 20));
        center.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        center.setBackground(colorMain);
        JScrollPane scroll = new JScrollPane(center);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16);

        // Icon bàn
        ImageIcon iconBanTrong = new ImageIcon(getClass().getResource("/pic/banGhe/banTron.png"));
        Image scaledTrong = iconBanTrong.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        ImageIcon banIconTrong = new ImageIcon(scaledTrong);

        ImageIcon iconBanDo = new ImageIcon(getClass().getResource("/pic/banGhe/banDo.png"));
        Image scaledDo = iconBanDo.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        ImageIcon banIconDo = new ImageIcon(scaledDo);

        // DAO lấy danh sách bàn
        ban_dao dao = new ban_dao();
        ArrayList<Ban> dsBan = dao.getAllBan();

        for (Ban b : dsBan) {
            Ban banTemp = b; // fix lỗi final variable
            ImageIcon icon = banTemp.getTrangThai().equals("Dang phuc vu") ? banIconDo : banIconTrong;

            JButton btnBan = new JButton(banTemp.getViTri(), icon);
            btnBan.setHorizontalTextPosition(SwingConstants.CENTER); // chữ ở giữa dưới icon
            btnBan.setVerticalTextPosition(SwingConstants.BOTTOM);
            btnBan.setOpaque(false);      // cho trong suốt background
            btnBan.setContentAreaFilled(false); // tránh viền màu mặc định
            btnBan.setFocusPainted(false);
            btnBan.setBorderPainted(false);
            btnBan.setFont(new Font("Segoe UI", Font.BOLD, 14));


            // Popup menu chuột phải
            JPopupMenu popup = new JPopupMenu();
            JMenuItem delete = new JMenuItem("Xóa bàn");
            delete.addActionListener(e -> {
                int res = JOptionPane.showConfirmDialog(null,
                        "Bạn có chắc chắn muốn xóa " + banTemp.getMaBan() + "?",
                        "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
                if (res == JOptionPane.YES_OPTION) {
                    dao.xoaBan(banTemp.getMaBan());
                    center.remove(btnBan);
                    center.revalidate();
                    center.repaint();
                }
            });
            popup.add(delete);

            // Sự kiện chuột
            btnBan.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (SwingUtilities.isRightMouseButton(e)) {
                        popup.show(e.getComponent(), e.getX(), e.getY());
                    } else if (SwingUtilities.isLeftMouseButton(e)) {
                        if (banTemp.getTrangThai().equals("Trong")) {
                            if (JOptionPane.showConfirmDialog(null,
                                    "Bàn " + banTemp.getMaBan() + " đang trống.\nChuyển sang ĐANG PHỤC VỤ?",
                                    "Xác nhận",
                                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                                dao.setTrangThaiDangPhucVu(banTemp.getMaBan());
                                banTemp.setTrangThai("Dang phuc vu");
                                btnBan.setIcon(banIconDo);
                            }
                        } else if (banTemp.getTrangThai().equals("Dang phuc vu")) {
                            if (JOptionPane.showConfirmDialog(null,
                                    "Bàn " + banTemp.getMaBan() + " đang phục vụ.\nChuyển về TRỐNG?",
                                    "Xác nhận",
                                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                                dao.setTrangThaiTrong(banTemp.getMaBan());
                                banTemp.setTrangThai("Trong");
                                btnBan.setIcon(banIconTrong);
                            }
                        }
                    }
                }
            });

            center.add(btnBan);
        }

        homePanel.add(scroll, BorderLayout.CENTER);

        // NÚT THÊM BÀN
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        southPanel.setBackground(colorMain);

        JButton btnThemBan = new JButton("Thêm bàn");
        btnThemBan.setBackground(new Color(100, 149, 237));
        btnThemBan.setForeground(Color.WHITE);
        btnThemBan.setFocusPainted(false);
        btnThemBan.setFont(new Font("Segoe UI", Font.BOLD, 14));

        btnThemBan.addActionListener(e -> {
            new FrmThemBan(pcenter, () -> {
                pcenter.removeAll();
                pcenter.add(createHomePanel(), "Trang chủ");
                card.show(pcenter, "Trang chủ");
                pcenter.revalidate();
                pcenter.repaint();
            });
        });

        southPanel.add(btnThemBan);
        homePanel.add(southPanel, BorderLayout.SOUTH);

        return homePanel;
    }


    public frmTrangChu() {
        setTitle("Trang Chủ");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1500, 1000);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        ConnectDB.getInstance().connect();


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

        btnGiaoca = new JButton("Giao ca");
        btnDangxuat = new JButton("Đăng xuất");

        Color btnColor = new Color(245, 222, 179);
        for (JButton b : new JButton[]{btnGiaoca, btnDangxuat}) {
            b.setBackground(btnColor);
            b.setFocusPainted(false);
            b.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        }

        pnNorthLeft.add(btnGiaoca);
        pnNorthLeft.add(btnDangxuat);

        JPanel pnNorthRight = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 5));
        pnNorthRight.setOpaque(false);
        JLabel lblUser = new JLabel("Lâm Văn Kỳ - Quản lí");
        lblUser.setForeground(Color.WHITE);
        JLabel lblTime = new JLabel("");
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
        ImageIcon logo = new ImageIcon("src/pic/sanpham/logo.jpeg");
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

     // ===== Home Panel =====
        JPanel homePanel = createHomePanel();

     // Thêm homePanel vào pcenter CardLayout
  
        pcenter.add(homePanel, "Trang chủ");

        // Thêm frmHoaDon (JPanel)
        hoaDonPanel = new frmHoaDon();

        pcenter.add(homePanel, "Trang chủ");
        pcenter.add(hoaDonPanel, "Hóa đơn");
        
        // Them frmSanpham
        sanphamPanel = new frmSanPham();
        
        pcenter.add(homePanel,"Trang chủ");
        pcenter.add(sanphamPanel,"Sản phẩm");
        
        // Them frmBanHang
        banhangPanel = new frmBanHang();
        
        pcenter.add(homePanel,"Trang chủ");
        pcenter.add(banhangPanel,"Bán Hàng");
        
        //Them frmThongke
        thongkePanel = new frmThongKe();
        
        pcenter.add(homePanel,"Trang chủ");
        pcenter.add(thongkePanel,"Thống Kê");
        
        //Them frmNhanvien
        nhanvienPanel = new frmNhanVien();
        
        pcenter.add(homePanel,"Trang chủ");
        pcenter.add(nhanvienPanel,"Nhân Viên");

        // === GÁN ACTION ===
        btnTrangchu.addActionListener(this);
        btnHoadon.addActionListener(this);
        btnSanpham.addActionListener(this);
        btnBanhang.addActionListener(this);
        btnThongke.addActionListener(this);
        btnNhanvien.addActionListener(this);
        btnDangxuat.addActionListener(this);
        btnGiaoca.addActionListener(this);

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
            hoaDonPanel.reloadHoaDonTable();
            card.show(pcenter, "Hóa đơn");
        } else if(obj == btnSanpham) {
        	card.show(pcenter, "Sản phẩm");
        } else if(obj == btnBanhang) {
        	card.show(pcenter, "Bán Hàng");
        } else if(obj == btnThongke) {
        	card.show(pcenter, "Thống Kê");
        } else if(obj == btnNhanvien) {
        	card.show(pcenter, "Nhân Viên");
        } else if(obj == btnDangxuat) {
        	int result = JOptionPane.showConfirmDialog(
        		    this,
        		    "Bạn chắc chắn muốn đăng xuất chứ?",
        		    "Xác nhận đăng xuất",
        		    JOptionPane.YES_NO_OPTION,
        		    JOptionPane.QUESTION_MESSAGE
        		);

        		if (result == JOptionPane.YES_OPTION) {
        		    this.dispose();
        		    new frmDangNhap();
        		}

        } else if(obj == btnGiaoca) {
        	new frmGiaoCa(this);
        }
    }

    public static void main(String[] args) {
        new frmTrangChu(); 
    }
}
