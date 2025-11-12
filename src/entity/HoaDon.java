package entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HoaDon {
	
	private String maHoaDon;
    private String maDonHang;
    private LocalDate ngayXuat;
    private double tongGia;

    public HoaDon() {
    }

    public HoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public HoaDon(String maHoaDon, String maDonHang, LocalDate ngayXuat, double tongGia) {
        this.maHoaDon = maHoaDon;
        this.maDonHang = maDonHang;
        this.ngayXuat = ngayXuat;
        this.tongGia = tongGia;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getMaDonHang() {
        return maDonHang;
    }

    public void setMaDonHang(String maDonHang) {
        this.maDonHang = maDonHang;
    }

    public LocalDate getNgayXuat() {
        return ngayXuat;
    }

    public void setNgayXuat(LocalDate ngayXuat) {
        this.ngayXuat = ngayXuat;
    }

    public double getTongGia() {
        return tongGia;
    }

    public void setTongGia(double tongGia) {
        this.tongGia = tongGia;
    }

    @Override
    public String toString() {
        return "HoaDon [maHoaDon=" + maHoaDon + ", maDonHang=" + maDonHang +
                ", ngayXuat=" + ngayXuat + ", tongGia=" + tongGia + "]";
    }

}
