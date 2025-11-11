package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HoaDon {
	
	// Thông tin hóa đơn
	private String maHoaDon;
	private KhachHang khachHang;
	private Ban ban;
	private NhanVien nhanVienPhuThu;
	private LocalDateTime thoiGianLap = LocalDateTime.now();
	private List<ChiTietHoaDon> dsHoaDon = new ArrayList<>();	
	
}
