package entity;

import java.time.LocalDate;

public class Kho {
	private String maSanPham;
	private int soLuongTonKho;
	private LocalDate ngayCapNhat;
	
	public Kho() {
		super();
	}

	public Kho(String maSanPham) {
		super();
		this.maSanPham = maSanPham;
	}

	public Kho(String maSanPham, int soLuongTonKho, LocalDate ngayCapNhat) {
		super();
		this.maSanPham = maSanPham;
		this.soLuongTonKho = soLuongTonKho;
		this.ngayCapNhat = ngayCapNhat;
	}

	public String getMaSanPham() {
		return maSanPham;
	}

	public void setMaSanPham(String maSanPham) {
		this.maSanPham = maSanPham;
	}

	public int getSoLuongTonKho() {
		return soLuongTonKho;
	}

	public void setSoLuongTonKho(int soLuongTonKho) {
		this.soLuongTonKho = soLuongTonKho;
	}

	public LocalDate getNgayCapNhat() {
		return ngayCapNhat;
	}

	public void setNgayCapNhat(LocalDate ngayCapNhat) {
		this.ngayCapNhat = ngayCapNhat;
	}

	@Override
	public String toString() {
		return "Kho [maSanPham=" + maSanPham + ", soLuongTonKho=" + soLuongTonKho + ", ngayCapNhat=" + ngayCapNhat
				+ "]";
	}
	
	
}
