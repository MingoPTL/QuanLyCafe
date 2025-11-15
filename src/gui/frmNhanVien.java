package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import dao.NhanVien_dao;
import entity.NhanVien;

public class frmNhanVien extends JPanel implements ActionListener,MouseListener {
    private JTextField txtMaNV, txtTenNV, txtSoDT, txtEmail, txtDiaChi, txtNgaySinh;
    private JComboBox<String> cboGioiTinh, cboChucVu;
    private JButton btnThem, btnCapNhat, btnTim,btnXoa;
    private JTable table;
    private DefaultTableModel modelNV;
    private NhanVien_dao nvDao;
    JTextField txtTimTen;

    public frmNhanVien() {

        nvDao = new NhanVien_dao();
        setLayout(new BorderLayout());
        setBackground(new Color(245, 222, 179)); // màu nền giống app

        // ===== PANEL THÔNG TIN =====
        JPanel pnlThongTin = new JPanel();
        pnlThongTin.setBackground(new Color(255, 250, 240));
        pnlThongTin.setLayout(null);
        pnlThongTin.setPreferredSize(new Dimension(350, 0));

        JLabel lblTitle = new JLabel("Thông tin nhân viên");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblTitle.setBounds(10, 10, 200, 20);
        pnlThongTin.add(lblTitle);

        JLabel lblMaNV = new JLabel("Mã NV:");
        lblMaNV.setBounds(10, 40, 100, 20);
        pnlThongTin.add(lblMaNV);

        txtMaNV = new JTextField();
        txtMaNV.setBounds(120, 40, 200, 20);
        pnlThongTin.add(txtMaNV);

        JLabel lblTenNV = new JLabel("Tên NV:");
        lblTenNV.setBounds(10, 70, 100, 20);
        pnlThongTin.add(lblTenNV);

        txtTenNV = new JTextField();
        txtTenNV.setBounds(120, 70, 200, 20);
        pnlThongTin.add(txtTenNV);

        JLabel lblGioiTinh = new JLabel("Giới tính:");
        lblGioiTinh.setBounds(10, 100, 100, 20);
        pnlThongTin.add(lblGioiTinh);

        cboGioiTinh = new JComboBox<>(new String[]{"Nam", "Nữ"});
        cboGioiTinh.setBounds(120, 100, 200, 20);
        pnlThongTin.add(cboGioiTinh);

        JLabel lblNgaySinh = new JLabel("Ngày sinh:");
        lblNgaySinh.setBounds(10, 130, 100, 20);
        pnlThongTin.add(lblNgaySinh);

        txtNgaySinh = new JTextField();
        txtNgaySinh.setBounds(120, 130, 200, 20);
        txtNgaySinh.setToolTipText("Nhập theo định dạng yyyy-MM-dd");
        pnlThongTin.add(txtNgaySinh);

        JLabel lblSoDT = new JLabel("Số ĐT:");
        lblSoDT.setBounds(10, 160, 100, 20);
        pnlThongTin.add(lblSoDT);

        txtSoDT = new JTextField();
        txtSoDT.setBounds(120, 160, 200, 20);
        pnlThongTin.add(txtSoDT);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(10, 190, 100, 20);
        pnlThongTin.add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(120, 190, 200, 20);
        pnlThongTin.add(txtEmail);

        JLabel lblDiaChi = new JLabel("Địa chỉ:");
        lblDiaChi.setBounds(10, 220, 100, 20);
        pnlThongTin.add(lblDiaChi);

        txtDiaChi = new JTextField();
        txtDiaChi.setBounds(120, 220, 200, 20);
        pnlThongTin.add(txtDiaChi);

        JLabel lblChucVu = new JLabel("Chức vụ:");
        lblChucVu.setBounds(10, 250, 100, 20);
        pnlThongTin.add(lblChucVu);

        cboChucVu = new JComboBox<>(new String[]{"Phục Vụ", "Quản lý", "Pha Chế", "Thu Ngân"});
        cboChucVu.setBounds(120, 250, 200, 20);
        pnlThongTin.add(cboChucVu);

        // ===== NÚT =====
        btnThem = new JButton("Thêm");
        btnThem.setBounds(50, 290, 100, 25);
        pnlThongTin.add(btnThem);

        btnCapNhat = new JButton("Cập nhật");
        btnCapNhat.setBounds(180, 290, 100, 25);
        pnlThongTin.add(btnCapNhat);

        add(pnlThongTin, BorderLayout.WEST);

        // ===== PANEL PHẢI =====
        JPanel pnlRight = new JPanel(new BorderLayout());

        // Tìm kiếm
        JPanel pnlTimKiem = new JPanel();
        pnlTimKiem.setBorder(BorderFactory.createTitledBorder("Tìm kiếm nhân viên"));
        pnlTimKiem.add(new JLabel("Mã NV:"));
        txtTimTen = new JTextField(15);
        pnlTimKiem.add(txtTimTen);
        pnlTimKiem.add(new JLabel("Chức vụ:"));
        JComboBox<String> cboTimChucVu = new JComboBox<>(new String[]{"Tất cả", "Phục Vụ", "Quản Lý", "Pha Chế", "Thu Ngân"});
        pnlTimKiem.add(cboTimChucVu);
        btnTim = new JButton("Tìm");
        pnlTimKiem.add(btnTim);
        
        btnXoa = new JButton("Xóa");
        pnlTimKiem.add(btnXoa);

        pnlRight.add(pnlTimKiem, BorderLayout.NORTH);

        // ===== BẢNG NHÂN VIÊN =====
        modelNV = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Mã NV", "Tên NV", "Giới tính", "Ngày sinh", "SĐT", "Email", "Địa chỉ", "Chức vụ"}
        );
        table = new JTable(modelNV);
        JScrollPane scroll = new JScrollPane(table);
        pnlRight.add(scroll, BorderLayout.CENTER);

        add(pnlRight, BorderLayout.CENTER);

        // ===== CUỐI CÙNG: LOAD DỮ LIỆU =====
        loadDataToTable();
        
        btnThem.addActionListener(this);
        btnCapNhat.addActionListener(this);
        btnTim.addActionListener(this);
        btnXoa.addActionListener(this);
    }

    private void loadDataToTable() {
        modelNV.setRowCount(0); // Xóa dữ liệu cũ

        List<NhanVien> listNV = nvDao.getAllNhanVien();
        for (NhanVien nv : listNV) {
            modelNV.addRow(new Object[]{
                    nv.getMaNV(),
                    nv.getTenNV(),
                    nv.getGioiTinh(),
                    nv.getNgaySinh(),
                    nv.getSoDienThoai(),
                    nv.getEmail(),
                    nv.getDiaChi(),
                    nv.getChucVu()
            });
        }
    }

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
	    if (row >= 0) {
	        txtMaNV.setText(modelNV.getValueAt(row, 0).toString());
	        txtTenNV.setText(modelNV.getValueAt(row, 1).toString());
	        cboGioiTinh.setSelectedItem(modelNV.getValueAt(row, 2).toString());
	        txtNgaySinh.setText(modelNV.getValueAt(row, 3).toString());
	        txtSoDT.setText(modelNV.getValueAt(row, 4).toString());
	        txtEmail.setText(modelNV.getValueAt(row, 5).toString());
	        txtDiaChi.setText(modelNV.getValueAt(row, 6).toString());
	        cboChucVu.setSelectedItem(modelNV.getValueAt(row, 7).toString());
	    }
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();

	    if (o.equals(btnThem)) {
	        themNhanVien();
	    } else if (o.equals(btnCapNhat)) {
	        capNhatNhanVien();
	    } else if (o.equals(btnXoa)) {
	        xoaNhanVien();
	    } else if (o.equals(btnTim)) {
	        timNhanVien();
	    }
	}
	
	private void clearForm() {
	    txtMaNV.setText("");
	    txtTenNV.setText("");
	    txtSoDT.setText("");
	    txtEmail.setText("");
	    txtDiaChi.setText("");
	    txtNgaySinh.setText("");
	    cboChucVu.setSelectedIndex(0);
	    cboGioiTinh.setSelectedIndex(0);
	}

	
	private void themNhanVien() {
	    try {
	        String ma = txtMaNV.getText().trim();
	        String ten = txtTenNV.getText().trim();
	        String gioiTinh = (String) cboGioiTinh.getSelectedItem();
	        String ngaySinhStr = txtNgaySinh.getText().trim();
	        String sdt = txtSoDT.getText().trim();
	        String email = txtEmail.getText().trim();
	        String diaChi = txtDiaChi.getText().trim();
	        String chucVu = (String) cboChucVu.getSelectedItem();

	        if (ma.isEmpty() || ten.isEmpty()) {
	            JOptionPane.showMessageDialog(this, "Mã và tên không được để trống!");
	            return;
	        }

	        java.time.LocalDate ngaySinh = java.time.LocalDate.parse(ngaySinhStr);

	        NhanVien nv = new NhanVien(ma, ten, ngaySinh, gioiTinh, sdt, email, chucVu, diaChi);

	        if (nvDao.ThemNhanVien(nv)) {
	            JOptionPane.showMessageDialog(this, "Thêm thành công!");
	            loadDataToTable();
	            clearForm();
	        } else {
	            JOptionPane.showMessageDialog(this, "Mã nhân viên đã tồn tại hoặc lỗi dữ liệu!");
	        }

	    } catch (Exception ex) {
	        JOptionPane.showMessageDialog(this, "Lỗi thêm nhân viên: " + ex.getMessage());
	    }
	}
	
	private void capNhatNhanVien() {
	    try {
	        String ma = txtMaNV.getText().trim();
	        String ten = txtTenNV.getText().trim();
	        String gioiTinh = (String) cboGioiTinh.getSelectedItem();
	        String ngaySinhStr = txtNgaySinh.getText().trim();
	        String sdt = txtSoDT.getText().trim();
	        String email = txtEmail.getText().trim();
	        String diaChi = txtDiaChi.getText().trim();
	        String chucVu = (String) cboChucVu.getSelectedItem();

	        if (ma.isEmpty()) {
	            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã nhân viên cần cập nhật!");
	            return;
	        }

	        java.time.LocalDate ngaySinh = java.time.LocalDate.parse(ngaySinhStr);

	        NhanVien nv = new NhanVien(ma, ten, ngaySinh, gioiTinh, sdt, email, chucVu, diaChi);

	        if (nvDao.capNhatNhanVien(nv)) {
	            JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
	            loadDataToTable();
	            clearForm();
	        } else {
	            JOptionPane.showMessageDialog(this, "Không tìm thấy nhân viên để cập nhật!");
	        }

	    } catch (Exception ex) {
	        JOptionPane.showMessageDialog(this, "Lỗi cập nhật: " + ex.getMessage());
	    }
	}
	
	private void xoaNhanVien() {
	    int row = table.getSelectedRow();
	    if (row == -1) {
	        JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên cần xóa!");
	        return;
	    }

	    String ma = modelNV.getValueAt(row, 0).toString();
	    int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa nhân viên " + ma + "?", "Xác nhận", JOptionPane.YES_NO_OPTION);

	    if (confirm == JOptionPane.YES_OPTION) {
	        if (nvDao.XoaNhanVien(ma)) {
	            JOptionPane.showMessageDialog(this, "Xóa thành công!");
	            loadDataToTable();
	            clearForm();
	        } else {
	            JOptionPane.showMessageDialog(this, "Xóa thất bại!");
	        }
	    }
	}
	
	private void timNhanVien() {
	    try {
	        String ma = txtTimTen.getText().trim();
	        if (ma == null || ma.trim().isEmpty()) 
	            return;

	        NhanVien nv = nvDao.getNhanVienTheoMa(ma.trim());
	        modelNV.setRowCount(0); // xóa dữ liệu cũ

	        if (nv != null) {
	            modelNV.addRow(new Object[]{
	                nv.getMaNV(),
	                nv.getTenNV(),
	                nv.getGioiTinh(),
	                nv.getNgaySinh(),
	                nv.getSoDienThoai(),
	                nv.getEmail(),
	                nv.getDiaChi(),
	                nv.getChucVu()
	            });
	        } else {
	            JOptionPane.showMessageDialog(this, "Không tìm thấy nhân viên có mã: " + ma);
	        }

	    } catch (Exception ex) {
	        JOptionPane.showMessageDialog(this, "Lỗi tìm nhân viên: " + ex.getMessage());
	    }
	}




}
