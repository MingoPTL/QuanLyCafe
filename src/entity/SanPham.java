package entity;

public class SanPham {

	// Thông tin sản phẩm
	private String maSanPham;
	private String tenSanPham;
	private double donGiaSanPham;
	private NhaCungCap nhaCungCap;
	private String moTaSanPham;
	private LoaiSanPham loaiSanpham;
	public String getMaSanPham() {
		return maSanPham;
	}
	public void setMaSanPham(String maSanPham) {
		this.maSanPham = maSanPham;
	}
	public String getTenSanPham() {
		return tenSanPham;
	}
	public void setTenSanPham(String tenSanPham) {
		this.tenSanPham = tenSanPham;
	}
	public double getDonGiaSanPham() {
		return donGiaSanPham;
	}
	public void setDonGiaSanPham(double donGiaSanPham) {
		this.donGiaSanPham = donGiaSanPham;
	}
	public NhaCungCap getNhaCungCap() {
		return nhaCungCap;
	}
	public void setNhaCungCap(NhaCungCap nhaCungCap) {
		this.nhaCungCap = nhaCungCap;
	}
	public String getMoTaSanPham() {
		return moTaSanPham;
	}
	public void setMoTaSanPham(String moTaSanPham) {
		this.moTaSanPham = moTaSanPham;
	}
	public LoaiSanPham getLoaiSanpham() {
		return loaiSanpham;
	}
	public void setLoaiSanpham(LoaiSanPham loaiSanpham) {
		this.loaiSanpham = loaiSanpham;
	}
	public SanPham(String maSanPham, String tenSanPham, double donGiaSanPham, NhaCungCap nhaCungCap, String moTaSanPham,
			LoaiSanPham loaiSanpham) {
		super();
		this.maSanPham = maSanPham;
		this.tenSanPham = tenSanPham;
		this.donGiaSanPham = donGiaSanPham;
		this.nhaCungCap = nhaCungCap;
		this.moTaSanPham = moTaSanPham;
		this.loaiSanpham = loaiSanpham;
	}
	public SanPham() {
		super();
	}
	@Override
	public String toString() {
		return "SanPham [maSanPham=" + maSanPham + ", tenSanPham=" + tenSanPham + ", donGiaSanPham=" + donGiaSanPham
				+ ", nhaCungCap=" + nhaCungCap + ", moTaSanPham=" + moTaSanPham + ", loaiSanpham=" + loaiSanpham + "]";
	}
	
	
	
}
