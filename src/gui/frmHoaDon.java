package gui;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import connectDB.ConnectDB;

public class frmHoaDon extends JPanel {
    private JTable tblHoaDon, tblChiTiet;
    private DefaultTableModel modelHoaDon, modelChiTiet;
    private JTextField txtMaHD, txtKhach, txtSDT, txtTGtao, txtTGthanhtoan,
            txtLoaiTT, txtGhiChu, txtTrangThai, txtTongSP, txtChiPhiKhac, txtTongHD, txtDiaChi;

    private JButton btnXacNhan;

    public frmHoaDon() {
        setLayout(new BorderLayout(10,10));
        setBackground(new Color(245, 222, 179));

        // --- Tiêu đề ---
        JLabel lblTitle = new JLabel("Hóa đơn", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
        add(lblTitle, BorderLayout.NORTH);

        // --- Panel chứa bảng hóa đơn và chi tiết ---
        JPanel pnlMain = new JPanel(new GridLayout(1, 2, 10, 10));
        pnlMain.setOpaque(false);

        // --- Bảng Hóa đơn ---
        String[] colsHD = {"Mã HD", "Mã đơn hàng", "Ngày xuất", "Tổng giá"};
        modelHoaDon = new DefaultTableModel(colsHD, 0);
        tblHoaDon = new JTable(modelHoaDon);
        JScrollPane spHD = new JScrollPane(tblHoaDon);
        spHD.setBorder(new TitledBorder("Danh sách Hóa đơn"));
        pnlMain.add(spHD);

        // --- Panel chi tiết hóa đơn ---
        JPanel pnlChiTiet = new JPanel(new BorderLayout(5,5));
        pnlChiTiet.setOpaque(false);

        // Bảng chi tiết
        String[] colsCT = {"Tên SP", "Đơn giá", "Số lượng", "Thành tiền", "Ghi chú"};
        modelChiTiet = new DefaultTableModel(colsCT, 0);
        tblChiTiet = new JTable(modelChiTiet);
        JScrollPane spCT = new JScrollPane(tblChiTiet);
        spCT.setBorder(new TitledBorder("Hóa đơn chi tiết"));
        pnlChiTiet.add(spCT, BorderLayout.CENTER);

        // --- Form thông tin chi tiết ---
        JPanel pnlInfo = new JPanel(new GridLayout(0, 4, 10, 5));
        pnlInfo.setOpaque(false);
        pnlInfo.setBorder(BorderFactory.createTitledBorder("Thông tin hóa đơn"));

        pnlInfo.add(new JLabel("Mã HD:"));
        txtMaHD = new JTextField(); pnlInfo.add(txtMaHD);

        pnlInfo.add(new JLabel("TG tạo:"));
        txtTGtao = new JTextField(); pnlInfo.add(txtTGtao);

        pnlInfo.add(new JLabel("Loại thanh toán:"));
        txtLoaiTT = new JTextField(); pnlInfo.add(txtLoaiTT);

        pnlInfo.add(new JLabel("Trạng thái:"));
        txtTrangThai = new JTextField(); pnlInfo.add(txtTrangThai);

        pnlInfo.add(new JLabel("Tổng tiền HD:"));
        txtTongHD = new JTextField(); pnlInfo.add(txtTongHD);

        pnlInfo.add(new JLabel("Ghi chú:"));
        txtGhiChu = new JTextField(); pnlInfo.add(txtGhiChu);

        pnlChiTiet.add(pnlInfo, BorderLayout.SOUTH);
        pnlMain.add(pnlChiTiet);

        add(pnlMain, BorderLayout.CENTER);

        // --- Nút xác nhận ---
        btnXacNhan = new JButton("Xác nhận HĐ");
        btnXacNhan.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnXacNhan.setBackground(new Color(255, 218, 185));
        add(btnXacNhan, BorderLayout.SOUTH);

        // --- Load dữ liệu từ SQL ---
        loadDataHoaDon();

        // --- Sự kiện click hàng hóa đơn ---
        tblHoaDon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tblHoaDon.getSelectedRow();
                if (row >= 0) {
                    String maHD = modelHoaDon.getValueAt(row, 0).toString();
                    loadChiTietHoaDon(maHD);
                    fillThongTin(maHD);
                }
            }
        });
    }

    private void loadDataHoaDon() {
        try {
            Connection con = ConnectDB.getInstance().getConnection();
            String sql = "SELECT MaHoaDon, MaDonHang, NgayXuat, TongGia FROM HoaDon";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                modelHoaDon.addRow(new Object[]{
                        rs.getString("MaHoaDon"),
                        rs.getString("MaDonHang"),
                        rs.getDate("NgayXuat"),
                        rs.getDouble("TongGia")
                });
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void loadChiTietHoaDon(String maHD) {
        modelChiTiet.setRowCount(0);
        try {
            Connection con = ConnectDB.getInstance().getConnection();

            String sql = """
                SELECT sp.TenSP, ctdh.GiaGoc, ctdh.SoLuong,
                       (ctdh.SoLuong * ctdh.GiaGoc) AS ThanhTien,
                       dh.MoTa AS GhiChu
                FROM HoaDon hd
                JOIN DonHang dh ON hd.MaDonHang = dh.MaDonHang
                JOIN ChiTietDonHang ctdh ON dh.MaDonHang = ctdh.MaDonHang
                JOIN SanPham sp ON ctdh.MaSP = sp.MaSP
                WHERE hd.MaHoaDon = ?
            """;

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maHD);
            ResultSet rs = ps.executeQuery();

            boolean coDuLieu = false;
            while (rs.next()) {
                coDuLieu = true;
                modelChiTiet.addRow(new Object[]{
                        rs.getString("TenSP"),
                        rs.getDouble("GiaGoc"),
                        rs.getInt("SoLuong"),
                        rs.getDouble("ThanhTien"),
                        rs.getString("GhiChu")
                });
            }

            if (!coDuLieu) {
                System.out.println("Không có chi tiết nào cho mã hóa đơn: " + maHD);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



    private void fillThongTin(String maHD) {
        try {
            Connection con = ConnectDB.getInstance().getConnection();
            String sql = "SELECT hd.MaHoaDon, dh.MaDonHang, dh.PhuongThucThanhToan, dh.TrangThai, dh.MaNhanVien, dh.MoTa, hd.NgayXuat, hd.TongGia "
                    + "FROM HoaDon hd JOIN DonHang dh ON hd.MaDonHang = dh.MaDonHang WHERE hd.MaHoaDon = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maHD);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                txtMaHD.setText(rs.getString("MaHoaDon"));
                txtLoaiTT.setText(rs.getString("PhuongThucThanhToan"));
                txtTrangThai.setText(rs.getString("TrangThai"));
                txtGhiChu.setText(rs.getString("MoTa"));
                txtTGtao.setText(rs.getString("NgayXuat"));
                txtTongHD.setText(String.valueOf(rs.getDouble("TongGia")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void reloadHoaDonTable() {
        modelHoaDon.setRowCount(0);
        loadDataHoaDon();
        modelChiTiet.setRowCount(0); // Xóa chi tiết cũ
    }

}
