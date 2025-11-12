package entity;

public class KhachHang {
	
	private String maKhach;
    private String tenKhach;
    private String soDienThoai;
    private String email;

    public KhachHang() {
    }

    public KhachHang(String maKhach) {
        this.maKhach = maKhach;
    }

    public KhachHang(String maKhach, String tenKhach, String soDienThoai, String email) {
        this.maKhach = maKhach;
        this.tenKhach = tenKhach;
        this.soDienThoai = soDienThoai;
        this.email = email;
    }

    public String getMaKhach() {
        return maKhach;
    }

    public void setMaKhach(String maKhach) {
        this.maKhach = maKhach;
    }

    public String getTenKhach() {
        return tenKhach;
    }

    public void setTenKhach(String tenKhach) {
        this.tenKhach = tenKhach;
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

    @Override
    public String toString() {
        return "KhachHang [maKhach=" + maKhach + ", tenKhach=" + tenKhach +
                ", soDienThoai=" + soDienThoai + ", email=" + email + "]";
    }

}
