package entity;

public class SanPham {

	// Thông tin sản phẩm
	private String MaSP;
	private String TenSP;
	private double Gia;
	private NhaCungCap MaNhaCungCap;
	private String MoTa;
	private LoaiSanPham LoaiSP;
	public String getMaSanPham() {
		return MaSP;
	}
	public void setMaSanPham(String maSanPham) {
		this.MaSP = maSanPham;
	}
	public String getTenSanPham() {
		return TenSP;
	}
	public void setTenSanPham(String tenSanPham) {
		this.TenSP = tenSanPham;
	}
	public double getDonGiaSanPham() {
		return Gia;
	}
	public void setDonGiaSanPham(double donGiaSanPham) {
		this.Gia = donGiaSanPham;
	}
	public NhaCungCap getNhaCungCap() {
		return MaNhaCungCap;
	}
	public void setNhaCungCap(NhaCungCap nhaCungCap) {
		this.MaNhaCungCap = nhaCungCap;
	}
	public String getMoTaSanPham() {
		return MoTa;
	}
	public void setMoTaSanPham(String moTaSanPham) {
		this.MoTa = moTaSanPham;
	}
	public LoaiSanPham getLoaiSanpham() {
		return LoaiSP;
	}
	public void setLoaiSanpham(LoaiSanPham loaiSanpham) {
		this.LoaiSP = loaiSanpham;
	}
	public SanPham(String maSanPham, String tenSanPham, double donGiaSanPham, NhaCungCap nhaCungCap, String moTaSanPham,
			LoaiSanPham loaiSanpham) {
		super();
		this.MaSP = maSanPham;
		this.TenSP = tenSanPham;
		this.Gia = donGiaSanPham;
		this.MaNhaCungCap = nhaCungCap;
		this.MoTa = moTaSanPham;
		this.LoaiSP = loaiSanpham;
	}
	public SanPham() {
		super();
	}
	@Override
	public String toString() {
		return "SanPham [MaSP=" + MaSP + ", TenSP=" + TenSP + ", Gia=" + Gia + ", MaNhaCungCap=" + MaNhaCungCap
				+ ", MoTa=" + MoTa + ", LoaiSP=" + LoaiSP + "]";
	}
	
	
	
	
}
