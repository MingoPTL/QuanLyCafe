package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.doanhThuThongKe;

public class thaoKe_dao {
    private ArrayList<doanhThuThongKe> dsDoanhThuSanPham = new ArrayList<>();
    private int soHoaDon = 0;

    public List<doanhThuThongKe> getAllThongKe() {
        Connection con = ConnectDB.getConnection();

        try {
            String sqlThongKe = 
                    "SELECT sp.MaSP, sp.TenSP, " +
                    "SUM(ct.SoLuong) AS tongSoLuong, " +
                    "SUM(ct.TongTien) AS tongTien " +
                    "FROM ChiTietDonHang ct " +
                    "JOIN SanPham sp ON ct.MaSP = sp.MaSP " +
                    "GROUP BY sp.MaSP, sp.TenSP";

            Statement state1 = con.createStatement();
            ResultSet res = state1.executeQuery(sqlThongKe);

            dsDoanhThuSanPham.clear();

            while (res.next()) {
                String maSP = res.getString("MaSP");
                String tenSP = res.getString("TenSP");
                int tongSoLuong = res.getInt("tongSoLuong");
                double tongTien = res.getDouble("tongTien");

                doanhThuThongKe sp = new doanhThuThongKe(maSP, tongSoLuong, tongTien,tenSP);
                dsDoanhThuSanPham.add(sp);
            }

            // Dùng statement riêng để đếm hóa đơn
            String sqlCount = "SELECT COUNT(*) AS soHoaDon FROM DonHang";
            Statement state2 = con.createStatement();
            ResultSet rsCount = state2.executeQuery(sqlCount);
            if (rsCount.next()) {
                soHoaDon = rsCount.getInt("soHoaDon");
            }

            res.close();
            rsCount.close();
            state1.close();
            state2.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsDoanhThuSanPham;
    }

    public double tongDoanhThu() {
        double tong = 0;
        for (doanhThuThongKe sp : dsDoanhThuSanPham) {
            tong += sp.getTongTien();
        }
        return tong;
    }

    public int getSoHoaDon() {
        return soHoaDon;
    }

    public void setSoHoaDon(int soHoaDon) {
        this.soHoaDon = soHoaDon;
    }

    public String sanPhamBanChay() {
        double max = 0;
        String spTam = "Chua co san pham ban chay";
        for (doanhThuThongKe sp : dsDoanhThuSanPham) {
            if (sp.getTongSL() > max) {
                max = sp.getTongSL();
                spTam = sp.getTenSP();
            }
        }
        return spTam;
    }
}
