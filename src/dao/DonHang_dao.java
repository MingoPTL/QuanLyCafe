package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import connectDB.ConnectDB;
import entity.DonHang;
import entity.HoaDon;

public class DonHang_dao {

    public boolean themDonHangNgauNhien(DonHang dh) {
        Connection con = ConnectDB.getInstance().getConnection();
        PreparedStatement stmt = null;

        try {
            // 🔹 Gán mã đơn hàng ngẫu nhiên
            String maDon = "DH" + String.format("%03d", (int) (Math.random() * 1000));
            dh.setMaDonHang(maDon);

            // 🔹 Nếu ngày đặt chưa có -> tự lấy ngày hôm nay
            if (dh.getNgayDat() == null) {
                dh.setNgayDat(LocalDate.now());
            }

            String sql = "INSERT INTO DonHang (MaDonHang, NgayDat, TongTien, PhuongThucThanhToan, MoTa, TrangThai, MaKhach, MaNhanVien, MaBan) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            stmt = con.prepareStatement(sql);
            stmt.setString(1, dh.getMaDonHang());
            stmt.setDate(2, java.sql.Date.valueOf(dh.getNgayDat()));
            stmt.setDouble(3, dh.getTongTien());
            stmt.setString(4, dh.getPhuongThucThanhToan());
            stmt.setString(5, dh.getMoTa());
            stmt.setString(6, dh.getTrangThai());
            stmt.setString(7, dh.getMaKhach());
            stmt.setString(8, dh.getMaNhanVien());
            stmt.setInt(9, dh.getMaBan());

            int n = stmt.executeUpdate();

            // 🔹 Nếu thêm đơn hàng thành công -> thêm hóa đơn
            if (n > 0) {
                HoaDon_dao hdDao = new HoaDon_dao();
                HoaDon hd = new HoaDon();

                // Tạo mã hóa đơn ngẫu nhiên
                String maHoaDon = "HD" + String.format("%03d", (int) (Math.random() * 1000));

                hd.setMaHoaDon(maHoaDon);
                hd.setMaDonHang(dh.getMaDonHang());
                hd.setNgayXuat(LocalDate.now());
                hd.setTongGia(dh.getTongTien());

                hdDao.themHoaDon(hd);
            }

            return n > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
