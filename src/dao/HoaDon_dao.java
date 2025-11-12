package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import connectDB.ConnectDB;
import entity.HoaDon;

public class HoaDon_dao {
    public boolean themHoaDon(HoaDon hd) {
        Connection con = ConnectDB.getInstance().getConnection();
        PreparedStatement stmt = null;
        try {
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
}
