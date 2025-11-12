package entity;

public class Ban {
	
	private String maBan;
    private String trangThai; // đổi tên cho giống SQL
    private int kichThuoc;
    private String viTri;

    public Ban() {
        super();
    }

    public Ban(String maBan) {
        super();
        this.maBan = maBan;
    }

    public Ban(String maBan, String trangThai, int kichThuoc, String viTri) {
        super();
        this.maBan = maBan;
        this.trangThai = trangThai;
        this.kichThuoc = kichThuoc;
        this.viTri = viTri;
    }

    public String getMaBan() {
        return maBan;
    }

    public void setMaBan(String maBan) {
        this.maBan = maBan;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public int getKichThuoc() {
        return kichThuoc;
    }

    public void setKichThuoc(int kichThuoc) {
        this.kichThuoc = kichThuoc;
    }

    public String getViTri() {
        return viTri;
    }

    public void setViTri(String viTri) {
        this.viTri = viTri;
    }

    @Override
    public String toString() {
        return "Ban [maBan=" + maBan + ", trangThai=" + trangThai + ", kichThuoc=" + kichThuoc + ", viTri=" + viTri
                + "]";
    }
	
	
	
}
