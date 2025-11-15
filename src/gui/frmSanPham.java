package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import dao.SanPham_dao;
import entity.LoaiSanPham;
import entity.NhaCungCap;
import entity.SanPham;

public class frmSanPham extends JPanel implements ActionListener, MouseListener {
	JTextField txtMasp,txtTensp,txtDongia,txtMota,txtTimTen,txtNcc;
	JComboBox<String> cboloaisp,cboTimloai;
	DefaultTableModel model;
	JTable table;
	JButton btnThem,btnCapnhap,btnTim,btnXoa;
	SanPham_dao sanPhamdao;
	
	
	
	public frmSanPham() {
		ConnectDB.getInstance().connect();
		sanPhamdao = new SanPham_dao();
		// Đây là panel nội dung chính của Hóa đơn (khi được show trong CardLayout)
        setLayout(new BorderLayout());
        setBackground(new Color(245, 222, 179)); // màu nền giống app

        //panel trai
        JPanel pnlLeft = new JPanel();
        pnlLeft.setPreferredSize(new Dimension(350, 0));
        pnlLeft.setLayout(null);
        pnlLeft.setBackground(new Color(250, 240, 215));
        pnlLeft.setBorder(BorderFactory.createTitledBorder("Thông tin sản phẩm"));

        JLabel lblMaSP = new JLabel("Mã sản phẩm:");
        lblMaSP.setBounds(30, 40, 100, 25);
        pnlLeft.add(lblMaSP);
        txtMasp = new JTextField();
        txtMasp.setBounds(140, 40, 160, 25);
        pnlLeft.add(txtMasp);

        JLabel lblTenSP = new JLabel("Tên sản phẩm:");
        lblTenSP.setBounds(30, 80, 100, 25);
        pnlLeft.add(lblTenSP);
        txtTensp = new JTextField();
        txtTensp.setBounds(140, 80, 160, 25);
        pnlLeft.add(txtTensp);

        JLabel lblLoaiSP = new JLabel("Loại sản phẩm:");
        lblLoaiSP.setBounds(30, 120, 100, 25);
        pnlLeft.add(lblLoaiSP);
        cboloaisp = new JComboBox<>();
        cboloaisp.setBounds(140, 120, 160, 25);
        cboloaisp.addItem("Trà sữa");
        cboloaisp.addItem("Cà phê");
        cboloaisp.addItem("Đồ ăn");
        pnlLeft.add(cboloaisp);

        JLabel lblDonGia = new JLabel("Đơn giá:");
        lblDonGia.setBounds(30, 160, 100, 25);
        pnlLeft.add(lblDonGia);
        txtDongia = new JTextField();
        txtDongia.setBounds(140, 160, 160, 25);
        pnlLeft.add(txtDongia);

        JLabel lblMoTa = new JLabel("Mô tả:");
        lblMoTa.setBounds(30, 200, 100, 25);
        pnlLeft.add(lblMoTa);
        txtMota = new JTextField();
        txtMota.setBounds(140, 200, 160, 60);
        pnlLeft.add(txtMota);
        
        JLabel lblNCC = new JLabel("Nhà cung cấp:");
        lblNCC.setBounds(30, 270, 100, 25);
        pnlLeft.add(lblNCC);

        txtNcc = new JTextField();
        txtNcc.setBounds(140, 270, 160, 25);
        pnlLeft.add(txtNcc);
        
        btnThem = new JButton("Thêm");
        btnThem.setBounds(30, 320, 120, 30);   // ↓ Lùi xuống một đoạn
        pnlLeft.add(btnThem);

        btnCapnhap = new JButton("Cập nhật");
        btnCapnhap.setBounds(170, 320, 120, 30); // ↓ Lùi xuống theo
        pnlLeft.add(btnCapnhap);

        
        add(pnlLeft, BorderLayout.WEST);
        
        //phai
        JPanel pnlRight = new JPanel(new BorderLayout());
        pnlRight.setOpaque(false);

        // --- Thanh tìm kiếm ---
        JPanel pnlSearch = new JPanel(new GridBagLayout());
        pnlSearch.setBackground(new Color(255, 248, 230));
        pnlSearch.setBorder(BorderFactory.createTitledBorder("Tìm kiếm sản phẩm"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0; gbc.gridy = 0;
        pnlSearch.add(new JLabel("Tên sản phẩm:"), gbc);

        gbc.gridx = 1; gbc.gridy = 0;
        txtTimTen = new JTextField(15);
        pnlSearch.add(txtTimTen, gbc);

        gbc.gridx = 0; gbc.gridy =1;
        pnlSearch.add(new JLabel("Loại sản phẩm:"), gbc);

        gbc.gridx = 1; gbc.gridy = 1;
        cboTimloai = new JComboBox<>(new String[]{"Tất cả", "Cà Phê", "Trà Sữa", "Đồ ăn"});
        cboTimloai.setPreferredSize(new Dimension(140, 25));
        pnlSearch.add(cboTimloai, gbc);

        gbc.gridx = 2; gbc.gridy = 0;
        btnTim = new JButton("Tìm");
        pnlSearch.add(btnTim, gbc);
        
        gbc.gridx = 3; gbc.gridy = 0;
        btnXoa = new JButton("Xoa");
        pnlSearch.add(btnXoa, gbc);

        pnlRight.add(pnlSearch, BorderLayout.NORTH);

        // --- Bảng sản phẩm ---
        String[] colHeader = {"Mã SP", "Tên SP", "Loại SP", "Đơn giá"};
        model = new DefaultTableModel(colHeader, 0);
        table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);
        pnlRight.add(scroll, BorderLayout.CENTER);
        
        loadTable();

        add(pnlRight, BorderLayout.CENTER);
        
        btnThem.addActionListener(this);
        btnCapnhap.addActionListener(this);
        btnTim.addActionListener(this);
        btnXoa.addActionListener(this);
	}
	
	
	private void loadTable() {
	    model.setRowCount(0); // Xóa dữ liệu cũ trong bảng

	    List<SanPham> list = sanPhamdao.getAllSanPham();
	    for (SanPham sp : list) {
	        model.addRow(new Object[] {
	            sp.getMaSanPham(),
	            sp.getTenSanPham(),
	            sp.getLoaiSanpham().getTenloai(),
	            sp.getDonGiaSanPham()
	        });
	    }
	}




	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		if(obj == btnThem) {
			String ma = txtMasp.getText().trim();
	        String ten = txtTensp.getText().trim();
	        String giaStr = txtDongia.getText().trim();
	        String ncc = txtNcc.getText().trim();
	        String mota = txtMota.getText().trim();
	        String loai = (String) cboloaisp.getSelectedItem();

	        // --- Kiểm tra nhập đầy đủ ---
	        if (ma.isEmpty() || ten.isEmpty() || giaStr.isEmpty() || ncc.isEmpty() || mota.isEmpty() || loai == null) {
	            JOptionPane.showMessageDialog(this, "⚠ Vui lòng nhập đầy đủ thông tin sản phẩm!");
	            return;
	        }

	        // --- Kiểm tra giá có phải số không ---
	        double gia;
	        try {
	            gia = Double.parseDouble(giaStr);
	            if (gia < 0) {
	                JOptionPane.showMessageDialog(this, "⚠ Đơn giá phải lớn hơn 0!");
	                return;
	            }
	        } catch (NumberFormatException ex2) {
	            JOptionPane.showMessageDialog(this, "⚠ Đơn giá phải là số hợp lệ!");
	            return;
	        }
	        
	        String maLoai = "";
	        switch (loai) {
	            case "Trà sữa": maLoai = "TS"; break;
	            case "Cà phê": maLoai = "CF"; break;
	            case "Đồ ăn":  maLoai = "ĐA"; break;
	        }

	        // --- Tạo đối tượng sản phẩm ---
	     // Nếu txtNcc là nhập mã nhà cung cấp
	        NhaCungCap nhaCungCap = new NhaCungCap();
	        nhaCungCap.setMaNhaCungCap(ncc);

	        LoaiSanPham loaiSP = new LoaiSanPham(maLoai, loai);
	        SanPham sp = new SanPham(ma, ten, gia, nhaCungCap, mota, loaiSP);


	        // --- Gọi DAO để thêm ---
	        if (sanPhamdao.themSanPham(sp)) {
	            JOptionPane.showMessageDialog(this, "✅ Thêm sản phẩm thành công!");
	            loadTable(); // tải lại bảng
	        } else {
	            JOptionPane.showMessageDialog(this, "❌ Thêm sản phẩm thất bại!");
	        }
		}
		else if (obj.equals(btnCapnhap)) {
		    String maSP = txtMasp.getText().trim();
		    String tenSP = txtTensp.getText().trim();
		    double gia = Double.parseDouble(txtDongia.getText().trim());
		    String moTa = txtMota.getText().trim();
		    String tenNCC = txtNcc.getText().trim();
		    String tenLoai = (String) cboloaisp.getSelectedItem();

		    // --- Xác định mã loại từ tên loại ---
		    String maLoai = "";
		    switch (tenLoai) {
		        case "Trà sữa": 
		            maLoai = "TS"; 
		            break;
		        case "Cà phê": 
		            maLoai = "CF"; 
		            break;
		        case "Đồ ăn": 
		            maLoai = "ĐA"; 
		            break;
		        default:
		            JOptionPane.showMessageDialog(this, "⚠ Không xác định được mã loại!");
		            return;
		    }

		    // --- Tạo đối tượng con ---
		    NhaCungCap ncc = new NhaCungCap(tenNCC);
		    LoaiSanPham loai = new LoaiSanPham(maLoai, tenLoai);

		    // --- Tạo sản phẩm ---
		    SanPham sp = new SanPham(maSP, tenSP, gia, ncc, moTa, loai);

		    // --- Cập nhật ---
		    SanPham_dao dao = new SanPham_dao();
		    if (dao.capNhatSanPham(sp)) {
		        JOptionPane.showMessageDialog(this, "✅ Cập nhật sản phẩm thành công!");
		        loadTable();
		    } else {
		        JOptionPane.showMessageDialog(this, "❌ Cập nhật thất bại!");
		    }
		}
		else if (obj.equals(btnTim)) {
		    String keyword = txtTimTen.getText().trim();
		    if (keyword.isEmpty()) {
		        JOptionPane.showMessageDialog(this, "Vui lòng nhập từ khóa cần tìm!");
		        return;
		    }

		    SanPham_dao dao = new SanPham_dao();
		    List<SanPham> ds = dao.timSanPham(keyword);

		    // Xóa hết dữ liệu cũ trên bảng
		    DefaultTableModel model = (DefaultTableModel) table.getModel();
		    model.setRowCount(0);

		    // Hiển thị kết quả tìm được
		    for (SanPham sp : ds) {
		        model.addRow(new Object[] {
		            sp.getMaSanPham(),
		            sp.getTenSanPham(),
		            sp.getDonGiaSanPham(),
		            sp.getMoTaSanPham(),
		            sp.getNhaCungCap(),
		            sp.getLoaiSanpham()
		        });
		    }

		    if (ds.isEmpty()) {
		        JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm phù hợp!");
		    }
		}else if (obj.equals(btnXoa)) {
		    int row = table.getSelectedRow();
		    if (row == -1) {
		        JOptionPane.showMessageDialog(this, "⚠ Vui lòng chọn sản phẩm cần xóa trong bảng!");
		        return;
		    }

		    String maSP = table.getValueAt(row, 0).toString();

		    int confirm = JOptionPane.showConfirmDialog(this,
		        "Bạn có chắc muốn xóa sản phẩm có mã: " + maSP + " ?",
		        "Xác nhận xóa", JOptionPane.YES_NO_OPTION);

		    if (confirm == JOptionPane.YES_OPTION) {
		        SanPham_dao dao = new SanPham_dao();
		        if (dao.xoaSanPham(maSP)) {
		            JOptionPane.showMessageDialog(this, "🗑️ Xóa sản phẩm thành công!");
		            loadTable();
		        } else {
		            JOptionPane.showMessageDialog(this, "❌ Xóa thất bại! Kiểm tra lại mã sản phẩm.");
		        }
		    }
		}
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
        if (row >= 0) {
            txtMasp.setText(table.getValueAt(row, 0).toString());
            txtTensp.setText(table.getValueAt(row, 1).toString());
            cboloaisp.setSelectedItem(table.getValueAt(row, 2).toString());
            txtDongia.setText(table.getValueAt(row, 3).toString());

            // ⚠ Nếu bảng của bạn có thêm cột Mô tả, Nhà cung cấp:
            // thì thêm các dòng dưới đây cho đủ
            if (table.getColumnCount() > 4) {
                txtNcc.setText(table.getValueAt(row, 4).toString());
            }
            if (table.getColumnCount() > 5) {
                txtMota.setText(table.getValueAt(row, 5).toString());
            }
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
}
