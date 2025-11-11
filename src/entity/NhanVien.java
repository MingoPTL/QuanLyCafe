package entity;

import java.time.LocalDate;

public class NhanVien {
	
	// Thông tin nhân viên
	private String maNhanVien;
	private String tenNhanVien;
	private LocalDate sinhNhat;
	private String gioiTinh;
	private String soDienThoai;
	private String email;
	private String chucVu;
	private String diaChi;
	
	// Thông tin tài khoản (dành cho nhân viên)
	private String taiKhoan;
	private String matKhau;
	private String vaiTro; // Phân biệt giữa quản lý và nhân viên
	
	// Constructor
	public NhanVien() {
		super();
	}

	public NhanVien(String maNhanVien, String tenNhanVien, LocalDate sinhNhat, String gioiTinh, String soDienThoai,
			String email, String chucVu, String taiKhoan, String matKhau, String vaiTro, String diaChi) {
		super();
		this.maNhanVien = maNhanVien;
		this.tenNhanVien = tenNhanVien;
		this.sinhNhat = sinhNhat;
		this.gioiTinh = gioiTinh;
		this.soDienThoai = soDienThoai;
		this.email = email;
		this.chucVu = chucVu;
		this.taiKhoan = taiKhoan;
		this.matKhau = matKhau;
		this.vaiTro = vaiTro;
		this.diaChi = diaChi;
	}
	
	// Getter, setter
	public String getMaNhanVien() {
		return maNhanVien;
	}

	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}

	public String getTenNhanVien() {
		return tenNhanVien;
	}

	public void setTenNhanVien(String tenNhanVien) {
		this.tenNhanVien = tenNhanVien;
	}

	public LocalDate getSinhNhat() {
		return sinhNhat;
	}

	public void setSinhNhat(LocalDate sinhNhat) {
		this.sinhNhat = sinhNhat;
	}

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getChucVu() {
		return chucVu;
	}

	public void setChucVu(String chucVu) {
		this.chucVu = chucVu;
	}

	public String getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(String taiKhoan) {
		this.taiKhoan = taiKhoan;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public String getVaiTro() {
		return vaiTro;
	}

	public void setVaiTro(String vaiTro) {
		this.vaiTro = vaiTro;
	}
	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	// Ghi đè
	@Override
	public String toString() {
		return "NhanVien [maNhanVien=" + maNhanVien + ", tenNhanVien=" + tenNhanVien + ", sinhNhat=" + sinhNhat
				+ ", gioiTinh=" + gioiTinh + ", soDienThoai=" + soDienThoai + ", email=" + email + ", chucVu=" + chucVu
				+ ", diaChi=" + diaChi + ", taiKhoan=" + taiKhoan + ", matKhau=" + matKhau + ", vaiTro=" + vaiTro + "]";
	}

	

	
	
	
}
