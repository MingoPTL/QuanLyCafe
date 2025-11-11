package entity;

public class Ban {
	
	// Thông tin bàn cà phê
	private String maBan;
	private int soBan;
	private String trangThai; // Bàn có được đặt trước hay chưa
	
	// Constructor
	public Ban() {
		super();
	}

	public Ban(String maBan, int soBan, String trangThai) {
		super();
		this.maBan = maBan;
		this.soBan = soBan;
		this.trangThai = trangThai;
	}

	// Getter, setter
	public String getMaBan() {
		return maBan;
	}

	public void setMaBan(String maBan) {
		this.maBan = maBan;
	}

	public int getSoBan() {
		return soBan;
	}

	public void setSoBan(int soBan) {
		this.soBan = soBan;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

	// Ghi đè
	@Override
	public String toString() {
		return "Ban [maBan=" + maBan + ", soBan=" + soBan + ", trangThai=" + trangThai + "]";
	}
	
}
