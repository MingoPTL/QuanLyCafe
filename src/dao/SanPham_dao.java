package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.LoaiSanPham;
import entity.NhaCungCap;
import entity.SanPham;

public class SanPham_dao {
	public List<SanPham> getAllSanPham(){
		List<SanPham> dssp = new ArrayList<>();
		Connection con = ConnectDB.getConnection();
		
		try {
			String url = "Select * from SanPham";
			Statement state = con.createStatement();
			ResultSet res = state.executeQuery(url);
			
			while(res.next()) {
				String ma = res.getString("MaSP");
                String ten = res.getString("TenSP");
                double gia = res.getDouble("Gia");
                String ncc = res.getString("MaNhaCungCap");
                String mota = res.getString("MoTa");
                String loai = res.getString("LoaiSP");

                SanPham sp = new SanPham(ma, ten, gia, new NhaCungCap(ncc), mota, new LoaiSanPham(loai));
                dssp.add(sp);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return dssp;
	}
	
	public boolean themSanPham(SanPham sp) {
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;

        try {
            String sql = "INSERT INTO SanPham(MaSP, TenSP, Gia, MaNhaCungCap, MoTa, LoaiSP) VALUES(?, ?, ?, ?, ?, ?)";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, sp.getMaSanPham());
            stmt.setString(2, sp.getTenSanPham());
            stmt.setDouble(3, sp.getDonGiaSanPham());
            stmt.setString(4, sp.getNhaCungCap().getTenNhaCungCap());
            stmt.setString(5, sp.getMoTaSanPham());
            stmt.setString(6, sp.getLoaiSanpham().getTenloai());

            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }
	
	private String getMaNhaCungCapTheoTen(String tenNCC) {
        Connection con = ConnectDB.getConnection();
        String maNCC = null;
        try {
            String sql = "SELECT MaNCC FROM NhaCungCap WHERE TenNCC = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, tenNCC);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                maNCC = rs.getString("MaNCC");
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maNCC;
    }

    // ✅ Cập nhật sản phẩm
	public boolean capNhatSanPham(SanPham sp) {
	    Connection conn = ConnectDB.getInstance().getConnection();
	    PreparedStatement stmt = null;
	    int n = 0;
	    try {
	        stmt = conn.prepareStatement(
	            "UPDATE SanPham SET TenSP=?, Gia=?, MoTa=?, MaNhaCungCap=?, LoaiSP=?, maLoai=? WHERE MaSP=?");

	        stmt.setString(1, sp.getTenSanPham());
	        stmt.setDouble(2, sp.getDonGiaSanPham());
	        stmt.setString(3, sp.getMoTaSanPham());
	        stmt.setString(4, getMaNhaCungCapTheoTen(sp.getNhaCungCap().getTenNhaCungCap()));
	        stmt.setString(5, sp.getLoaiSanpham().getTenloai());
	        stmt.setString(6, sp.getLoaiSanpham().getMaloai()); // ⚠ dùng mã loại (CF, TS, ĐA)
	        stmt.setString(7, sp.getMaSanPham());

	        n = stmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (stmt != null) stmt.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return n > 0;
	}



    // ✅ Xóa sản phẩm
    public boolean xoaSanPham(String maSP) {
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;

        try {
            String sql = "DELETE FROM SanPham WHERE MaSP=?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, maSP);
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }
    
    private String getMaLoaiTheoTen(String tenLoai) {
        Connection con = ConnectDB.getConnection();
        String maLoai = null;
        try {
            String sql = "SELECT MaLoai FROM LoaiSanPham WHERE LOWER(TenLoai) = LOWER(?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, tenLoai.trim());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                maLoai = rs.getString("MaLoai");
            } else {
                System.out.println("⚠ Không tìm thấy mã loại cho loại: " + tenLoai);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maLoai;
    }
    
    public List<SanPham> timSanPham(String keyword) {
        List<SanPham> ds = new ArrayList<>();
        Connection con = ConnectDB.getConnection();
        try {
            String sql = "SELECT * FROM SanPham WHERE MaSP LIKE ? OR TenSP LIKE ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, "%" + keyword + "%");
            stmt.setString(2, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // Tạo đối tượng NhaCungCap và LoaiSanPham từ dữ liệu trong bảng
                NhaCungCap ncc = new NhaCungCap();
                ncc.setMaNhaCungCap(rs.getString("MaNhaCungCap"));

                LoaiSanPham loai = new LoaiSanPham();
                loai.setTenloai(rs.getString("LoaiSP"));
                loai.setMaloai(rs.getString("MaLoai")); // nếu có cột này trong bảng

                // Tạo đối tượng SanPham
                SanPham sp = new SanPham(
                    rs.getString("MaSP"),
                    rs.getString("TenSP"),
                    rs.getDouble("Gia"),
                    ncc,
                    rs.getString("MoTa"),
                    loai
                );

                ds.add(sp);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ds;
    }



    


}
