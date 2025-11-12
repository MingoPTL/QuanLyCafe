package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.NhanVien;

public class NhanVien_dao {
	public List<NhanVien> getAllNhanVien(){
		List<NhanVien> dsnv = new ArrayList<>();
		Connection con = ConnectDB.getConnection();
		
		try {
			String url = "Select * from NhanVien";
			Statement state = con.createStatement();
			ResultSet rs = state.executeQuery(url);
			
			while(rs.next()) {
				String ma = rs.getString("MaNV");
				String ten = rs.getString("TenNV");
				LocalDate ngaysinh = rs.getDate("NgaySinh").toLocalDate();
				String gioitinh = rs.getString("GioiTinh");
				String sdt = rs.getString("SoDienThoai");
				String email = rs.getString("Email");
				String chucvu = rs.getString("ChucVu");
				String diachi = rs.getString("DiaChi");
				
				NhanVien nv = new NhanVien(ma,ten,ngaysinh,gioitinh,sdt,email,chucvu,diachi);
				dsnv.add(nv);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return dsnv;
	}
	
	public boolean ThemNhanVien(NhanVien nv) {
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		
		try {
			String url = "INSERT INTO NhanVien (MaNV, TenNV, NgaySinh, GioiTinh, SoDienThoai, Email, ChucVu, DiaChi) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			stmt = con.prepareStatement(url);
			stmt.setString(1, nv.getMaNV());
            stmt.setString(2, nv.getTenNV());
            stmt.setDate(3, Date.valueOf(nv.getNgaySinh()));
            stmt.setString(4, nv.getGioiTinh());
            stmt.setString(5, nv.getSoDienThoai());
            stmt.setString(6, nv.getEmail());
            stmt.setString(7, nv.getChucVu());
            stmt.setString(8, nv.getDiaChi());
            
            n = stmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	
	
	public boolean XoaNhanVien(String Manv) {
		Connection con = ConnectDB.getConnection();
		PreparedStatement ps = null;
		int n = 0;
		
		try {
			String sql = "DELETE FROM NhanVien WHERE MaNV=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, Manv);
			n = ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public boolean capNhatNhanVien(NhanVien nv) {
        Connection con = ConnectDB.getInstance().getConnection();
        int n = 0;
        try {
            String sql = "UPDATE NhanVien SET TenNV=?, NgaySinh=?, GioiTinh=?, SoDienThoai=?, "
                       + "Email=?, ChucVu=?, DiaChi=? WHERE MaNV=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nv.getTenNV());
            ps.setDate(2, Date.valueOf(nv.getNgaySinh()));
            ps.setString(3, nv.getGioiTinh());
            ps.setString(4, nv.getSoDienThoai());
            ps.setString(5, nv.getEmail());
            ps.setString(6, nv.getChucVu());
            ps.setString(7, nv.getDiaChi());
            ps.setString(8, nv.getMaNV());

            n = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }
	
	public NhanVien getNhanVienTheoMa(String maNV) {
        Connection con = ConnectDB.getInstance().getConnection();
        NhanVien nv = null;

        try {
            String sql = "SELECT * FROM NhanVien WHERE MaNV = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maNV);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                nv = new NhanVien(
                    rs.getString("MaNV"),
                    rs.getString("TenNV"),
                    rs.getDate("NgaySinh").toLocalDate(),
                    rs.getString("GioiTinh"),
                    rs.getString("SoDienThoai"),
                    rs.getString("Email"),
                    rs.getString("ChucVu"),
                    rs.getString("DiaChi")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nv;
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
