package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import connectDB.ConnectDB;
import entity.HoaDon;

public class HoaDon_dao {
    public boolean themHoaDon(HoaDon hd) {
        Connection con = ConnectDB.getInstance().getConnection();
        PreparedStatement stmt = null;
        try {
            // ✅ Debug xem có bị null không
            System.out.println("====== THÊM HÓA ĐƠN ======");
            System.out.println("MaHoaDon: " + hd.getMaHoaDon());
            System.out.println("MaDonHang: " + hd.getMaDonHang());
            System.out.println("NgayXuat: " + hd.getNgayXuat());
            System.out.println("TongGia: " + hd.getTongGia());
            System.out.println("==========================");

            String sql = "INSERT INTO HoaDon (MaHoaDon, MaDonHang, NgayXuat, TongGia) VALUES (?, ?, ?, ?)";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, hd.getMaHoaDon());
            stmt.setString(2, hd.getMaDonHang());
            stmt.setDate(3, java.sql.Date.valueOf(hd.getNgayXuat()));
            stmt.setDouble(4, hd.getTongGia());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) { e.printStackTrace(); }
        }
    }
    
    public double getTongTienAll() {
        String sql = "SELECT SUM(TongGia) AS Tong FROM HoaDon";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConnectDB.getInstance().getConnection();
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            if (rs.next()) {
                double tong = rs.getDouble("Tong");
                return tong;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            // Không đóng connection nếu ConnectDB quản lý connection pool; nếu không thì đóng.
        }
        return 0;
    }
}
