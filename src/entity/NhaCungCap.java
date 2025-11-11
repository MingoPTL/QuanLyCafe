package entity;

public class NhaCungCap {
	
	// Thông tin nhà cung cấp
	private String maNhaCungCap;
	private String tenNhaCungCap;
	private String soDienThoaiNhaCungCap;
	private String emailNhaCungCap;
	private String diaChiNhaCungCap;
	
	//Constructor
	public NhaCungCap() {
		super();
	}

	public NhaCungCap(String maNhaCungCap, String tenNhaCungCap, String soDienThoaiNhaCungCap, String emailNhaCungCap,
			String diaChiNhaCungCap) {
		super();
		this.maNhaCungCap = maNhaCungCap;
		this.tenNhaCungCap = tenNhaCungCap;
		this.soDienThoaiNhaCungCap = soDienThoaiNhaCungCap;
		this.emailNhaCungCap = emailNhaCungCap;
		this.diaChiNhaCungCap = diaChiNhaCungCap;
	}

	// Getter, setter
	public String getMaNhaCungCap() {
		return maNhaCungCap;
	}

	public void setMaNhaCungCap(String maNhaCungCap) {
		this.maNhaCungCap = maNhaCungCap;
	}

	public String getTenNhaCungCap() {
		return tenNhaCungCap;
	}

	public void setTenNhaCungCap(String tenNhaCungCap) {
		this.tenNhaCungCap = tenNhaCungCap;
	}

	public String getSoDienThoaiNhaCungCap() {
		return soDienThoaiNhaCungCap;
	}

	public void setSoDienThoaiNhaCungCap(String soDienThoaiNhaCungCap) {
		this.soDienThoaiNhaCungCap = soDienThoaiNhaCungCap;
	}

	public String getEmailNhaCungCap() {
		return emailNhaCungCap;
	}

	public void setEmailNhaCungCap(String emailNhaCungCap) {
		this.emailNhaCungCap = emailNhaCungCap;
	}

	public String getDiaChiNhaCungCap() {
		return diaChiNhaCungCap;
	}

	public void setDiaChiNhaCungCap(String diaChiNhaCungCap) {
		this.diaChiNhaCungCap = diaChiNhaCungCap;
	}

	// Ghi đè
	@Override
	public String toString() {
		return "NhaCungCap [maNhaCungCap=" + maNhaCungCap + ", tenNhaCungCap=" + tenNhaCungCap
				+ ", soDienThoaiNhaCungCap=" + soDienThoaiNhaCungCap + ", emailNhaCungCap=" + emailNhaCungCap
				+ ", diaChiNhaCungCap=" + diaChiNhaCungCap + "]";
	}
	
	
}
