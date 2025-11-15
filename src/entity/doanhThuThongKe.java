package entity;

public class doanhThuThongKe {
	private String maSP;
	private int tongSL;
	private double tongTien;
	private String tenSP;
	public doanhThuThongKe(String maSP, int tongSL, double tongTien, String tenSP) {
		super();
		this.maSP = maSP;
		this.tongSL = tongSL;
		this.tongTien = tongTien;
		this.tenSP = tenSP;
	}
	public String getTenSP() {
		return tenSP;
	}
	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}
	public String getMaSP() {
		return maSP;
	}
	public void setMaSP(String maSP) {
		this.maSP = maSP;
	}
	public int getTongSL() {
		return tongSL;
	}
	public void setTongSL(int tongSL) {
		this.tongSL = tongSL;
	}
	public double getTongTien() {
		return tongTien;
	}
	public void setTongTien(double tongTien) {
		this.tongTien = tongTien;
	}
	public doanhThuThongKe(String maSP, int tongSL, double tongTien) {
		super();
		this.maSP = maSP;
		this.tongSL = tongSL;
		this.tongTien = tongTien;
	}
	public doanhThuThongKe() {
		super();
	}
	
}
