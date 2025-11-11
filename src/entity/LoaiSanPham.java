package entity;

public class LoaiSanPham {
	private String maloai;
	private String tenloai;
	public String getMaloai() {
		return maloai;
	}
	public void setMaloai(String maloai) {
		this.maloai = maloai;
	}
	public String getTenloai() {
		return tenloai;
	}
	public void setTenloai(String tenloai) {
		this.tenloai = tenloai;
	}
	public LoaiSanPham(String maloai, String tenloai) {
		super();
		this.maloai = maloai;
		this.tenloai = tenloai;
	}
	public LoaiSanPham() {
		super();
	}
	
	
	public LoaiSanPham(String maloai) {
		super();
		this.maloai = maloai;
	}
	@Override
	public String toString() {
		return "LoaiSanPham [maloai=" + maloai + ", tenloai=" + tenloai + "]";
	}
	
	
}
