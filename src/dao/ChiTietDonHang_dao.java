package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import connectDB.ConnectDB;
import entity.ChiTietDonHang;

public class ChiTietDonHang_dao {

    // 🧩 Thêm chi tiết đơn hàng vào database
    public boolean themChiTietDonHang(ChiTietDonHang ctdh) {
        Connection con = ConnectDB.getInstance().getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            String sql = "INSERT INTO ChiTietDonHang (SoLuong, GiaGoc, TongTien, MaSP, MaDonHang) VALUES (?, ?, ?, ?, ?)";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, ctdh.getSoLuong());
            stmt.setDouble(2, ctdh.getGiaGoc());
            stmt.setDouble(3, ctdh.getTongTienDonHang()); // đúng với tên biến trong entity
            stmt.setString(4, ctdh.getMaSanPham());
            stmt.setString(5, ctdh.getMaDonHang());

            n = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return n > 0;
    }
}
