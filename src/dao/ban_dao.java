package dao;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.Ban;

public class ban_dao {
	public ArrayList<Ban> getAllBan(){
		ArrayList <Ban> ds = new ArrayList<>();
		try{
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String url = "select * from Ban";
			Statement state = con.createStatement();
			ResultSet rs = state.executeQuery(url);
			while(rs.next()) {
				String ma = rs.getString("MaBan");
				String trangThai = rs.getString("TrangThai");
				int kichThuoc = rs.getInt("KichThuoc");
				String viTri = rs.getString("ViTri");
				ds.add(new Ban(ma,trangThai,kichThuoc,viTri));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return ds;
	}
	public boolean UpdateTrangThai(String maBan,String trangThai) {
		int n = 0;
		try {
			ConnectDB.getInstance();
			Connection con =  ConnectDB.getConnection();
			String url = "UPDATE Ban Set TrangThai = ? where MaBan =?";
			java.sql.PreparedStatement pstmt = con.prepareStatement(url);
			pstmt.setString(1, trangThai);
			pstmt.setString(2, maBan);
			n = pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return n>0;
	}
	public boolean setTrangThaiTrong(String ma) {
		return UpdateTrangThai(ma, "Trong");
	}
	public boolean setTrangThaiDangPhucVu(String ma) {
		return UpdateTrangThai(ma, "Dang phuc vu");
	}
	public boolean themBan(Ban b) {
		int n= 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String url = "INSERT INTO Ban( TrangThai, KichThuoc, ViTri) VALUES ( ?, ?, ?)";
			PreparedStatement pstmt = con.prepareStatement(url);
		
	        pstmt.setString(1, b.getTrangThai());
	        pstmt.setInt(2, b.getKichThuoc());
	        pstmt.setString(3, b.getViTri());
	        n = pstmt.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return n>0;
	}
	public boolean xoaBan(String maBan) {
	    int n = 0;
	    try {
	        ConnectDB.getInstance();
	        Connection con = ConnectDB.getConnection();

	        String sql = "DELETE FROM Ban WHERE MaBan = ?";
	        java.sql.PreparedStatement pstmt = con.prepareStatement(sql);

	        pstmt.setString(1, maBan);

	        n = pstmt.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return n > 0;
	}

}
