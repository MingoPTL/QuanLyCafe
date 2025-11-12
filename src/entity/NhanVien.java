package entity;

import java.time.LocalDate;

public class NhanVien {

    private String maNV;
    private String tenNV;
    private LocalDate ngaySinh;
    private String gioiTinh;
    private String soDienThoai;
    private String email;
    private String chucVu;
    private String diaChi;

    // Constructor mặc định
    public NhanVien() {
        super();
    }

    // Constructor đầy đủ
    public NhanVien(String maNV, String tenNV, LocalDate ngaySinh, String gioiTinh,
                    String soDienThoai, String email, String chucVu, String diaChi) {
        super();
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.chucVu = chucVu;
        this.diaChi = diaChi;
    }

    // Getter và Setter
    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public LocalDate getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    @Override
    public String toString() {
        return "NhanVien [maNV=" + maNV + ", tenNV=" + tenNV + ", ngaySinh=" + ngaySinh
                + ", gioiTinh=" + gioiTinh + ", soDienThoai=" + soDienThoai
                + ", email=" + email + ", chucVu=" + chucVu + ", diaChi=" + diaChi + "]";
    }
}
