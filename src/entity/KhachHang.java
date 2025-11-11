package entity;

public class KhachHang {
	
	// Thông tin khách hàng
	private String maKhachHang; 
	private String tenKhachHang;
	
	// Constructor
	public KhachHang() {
		super();
	}

	public KhachHang(String maKhachHang, String tenKhachHang) {
		super();
		this.maKhachHang = maKhachHang;
		this.tenKhachHang = tenKhachHang;
	}

	// Getter, setter
	public String getMaKhachHang() {
		return maKhachHang;
	}

	public void setMaKhachHang(String maKhachHang) {
		this.maKhachHang = maKhachHang;
	}

	public String getTenKhachHang() {
		return tenKhachHang;
	}

	public void setTenKhachHang(String tenKhachHang) {
		this.tenKhachHang = tenKhachHang;
	}

	//Ghi đè
	@Override
	public String toString() {
		return "KhachHang [maKhachHang=" + maKhachHang + ", tenKhachHang=" + tenKhachHang + "]";
	}
		
}
