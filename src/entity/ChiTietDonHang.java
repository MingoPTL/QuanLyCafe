package entity;

public class ChiTietDonHang {

	private int id;
    private int soLuong;
    private double giaGoc;
    private double tongTienDonHang;
    private String maSanPham;
    private String maDonHang;

    public ChiTietDonHang() {
        super();
    }

    public ChiTietDonHang(int id) {
        super();
        this.id = id;
    }

    public ChiTietDonHang(int id, int soLuong, double giaGoc, double tongTienDonHang, String maSanPham,
            String maDonHang) {
        super();
        this.id = id;
        this.soLuong = soLuong;
        this.giaGoc = giaGoc;
        this.tongTienDonHang = tongTienDonHang;
        this.maSanPham = maSanPham;
        this.maDonHang = maDonHang;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getGiaGoc() {
        return giaGoc;
    }

    public void setGiaGoc(double giaGoc) {
        this.giaGoc = giaGoc;
    }

    public double getTongTienDonHang() {
        return tongTienDonHang;
    }

    public void setTongTienDonHang(double tongTienDonHang) {
        this.tongTienDonHang = tongTienDonHang;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getMaDonHang() {
        return maDonHang;
    }

    public void setMaDonHang(String maDonHang) {
        this.maDonHang = maDonHang;
    }

    @Override
    public String toString() {
        return "ChiTietDonHang [id=" + id + ", soLuong=" + soLuong + ", giaGoc=" + giaGoc + ", tongTienDonHang="
                + tongTienDonHang + ", maSanPham=" + maSanPham + ", maDonHang=" + maDonHang + "]";
    }
	
	
}
