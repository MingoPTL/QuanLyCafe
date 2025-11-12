package entity;

import java.time.LocalDate;

public class DonHang {
	
	private String maDonHang;
    private LocalDate ngayDat;
    private double tongTien;
    private String phuongThucThanhToan; // "tienmat" hoặc "chuyenkhoan"
    private String moTa;
    private String trangThai; // "da nhan", "da lam", "da giao"
    private String maKhach;
    private String maNhanVien;
    private int maBan;

    public DonHang() {}

    public DonHang(String maDonHang) {
        this.maDonHang = maDonHang;
    }

    public DonHang(String maDonHang, LocalDate ngayDat, double tongTien, String phuongThucThanhToan,
                    String moTa, String trangThai, String maKhach, String maNhanVien, int maBan) {
        this.maDonHang = maDonHang;
        this.ngayDat = ngayDat;
        this.tongTien = tongTien;
        this.phuongThucThanhToan = phuongThucThanhToan;
        this.moTa = moTa;
        this.trangThai = trangThai;
        this.maKhach = maKhach;
        this.maNhanVien = maNhanVien;
        this.maBan = maBan;
    }

    public String getMaDonHang() { return maDonHang; }
    public void setMaDonHang(String maDonHang) { this.maDonHang = maDonHang; }

    public LocalDate getNgayDat() { return ngayDat; }
    public void setNgayDat(LocalDate ngayDat) { this.ngayDat = ngayDat; }

    public double getTongTien() { return tongTien; }
    public void setTongTien(double tongTien) { this.tongTien = tongTien; }

    public String getPhuongThucThanhToan() { return phuongThucThanhToan; }
    public void setPhuongThucThanhToan(String phuongThucThanhToan) { this.phuongThucThanhToan = phuongThucThanhToan; }

    public String getMoTa() { return moTa; }
    public void setMoTa(String moTa) { this.moTa = moTa; }

    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }

    public String getMaKhach() { return maKhach; }
    public void setMaKhach(String maKhach) { this.maKhach = maKhach; }

    public String getMaNhanVien() { return maNhanVien; }
    public void setMaNhanVien(String maNhanVien) { this.maNhanVien = maNhanVien; }

    public int getMaBan() { return maBan; }
    public void setMaBan(int maBan) { this.maBan = maBan; }

    @Override
    public String toString() {
        return "DonHang [maDonHang=" + maDonHang + ", ngayDat=" + ngayDat + ", tongTien=" + tongTien +
               ", phuongThucThanhToan=" + phuongThucThanhToan + ", moTa=" + moTa + ", trangThai=" + trangThai +
               ", maKhach=" + maKhach + ", maNhanVien=" + maNhanVien + ", maBan=" + maBan + "]";
    }
	
	
}
