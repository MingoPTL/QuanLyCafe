package dao;

import java.sql.*;
import java.util.Random;
import connectDB.ConnectDB;
import entity.KhachHang;

public class KhachHang_dao {

    public KhachHang taoKhachHangNgauNhien() {
        Random rand = new Random();
        String maKhach = "KH" + (1000 + rand.nextInt(9000)); // ví dụ: KH1234
        String tenKhach = "Khách " + (char) (65 + rand.nextInt(26)); // Khách A-Z
        String sdt = "09" + (10000000 + rand.nextInt(90000000));     // SDT ngẫu nhiên
        String email = "khach" + rand.nextInt(1000) + "@mail.com";

        KhachHang kh = new KhachHang(maKhach, tenKhach, sdt, email);
        themKhachHang(kh);
        return kh;
    }

    public boolean themKhachHang(KhachHang kh) {
        int n = 0;
        try {
            Connection con = ConnectDB.getInstance().getConnection();
            String sql = "INSERT INTO KhachHang (MaKhach, TenKhach, SoDienThoai, Email) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, kh.getMaKhach());
            ps.setString(2, kh.getTenKhach());
            ps.setString(3, kh.getSoDienThoai());
            ps.setString(4, kh.getEmail());
            n = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n > 0;
    }
}
