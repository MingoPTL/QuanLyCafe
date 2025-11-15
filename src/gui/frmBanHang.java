package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dao.ChiTietDonHang_dao;
import dao.DonHang_dao;
import dao.HoaDon_dao;
import entity.ChiTietDonHang;
import entity.DonHang;
import entity.HoaDon;

public class frmBanHang extends JPanel implements ActionListener {
    private JTable tblSanPhamDaChon;
    private JTextField txtSDT, txtHoTen, txtTongTienSP, txtTongTienHD, txtTienKhachTra, txtTienThua;
    private JComboBox<String> cboLoaiTT, cboTrangThai;
    DefaultTableModel model;
    JButton btnTimKH, btnThemKH;

    public frmBanHang() {
        setLayout(new BorderLayout());
        setBackground(new Color(245, 222, 179));

        JPanel pnlMain = new JPanel(new GridLayout(1, 2, 10, 0));
        pnlMain.setOpaque(false);
        add(pnlMain, BorderLayout.CENTER);

        // ===== BÊN TRÁI =====
        JPanel pnlLeft = new JPanel(new BorderLayout(5, 5));
        pnlLeft.setOpaque(false);

        JPanel pnlTim = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlTim.setOpaque(false);
        JTextField txtTim = new JTextField(15);
        JButton btnTim = new JButton("Tìm");
        JComboBox<String> cboLoai = new JComboBox<>(new String[]{"Tất cả", "Cà phê", "Trà", "Khác"});
        pnlTim.add(new JLabel("Tên sản phẩm:"));
        pnlTim.add(txtTim);
        pnlTim.add(btnTim);
        pnlTim.add(new JLabel("Loại:"));
        pnlTim.add(cboLoai);
        pnlLeft.add(pnlTim, BorderLayout.NORTH);

        JPanel pnlSP = new JPanel(new GridLayout(0, 3, 10, 10));
        pnlSP.setOpaque(false);
        JScrollPane scrollSP = new JScrollPane(pnlSP);
        scrollSP.setBorder(new TitledBorder("Danh sách sản phẩm"));
        pnlLeft.add(scrollSP, BorderLayout.CENTER);

        // Danh sách sản phẩm
        String[][] dsSP = {
                {"CF100", "Cà Phê Đen Đá", "src/pic/sanpham/capheden.jpg", "15000"},
                {"CF101", "Cà Phê Sữa Đá", "src/pic/sanpham/caphesua.png", "20000"},
                {"CF102", "Bạc Xỉu", "src/pic/sanpham/bacxiu.jpg", "25000"},
                {"CF103", "Cà Phê Muối", "src/pic/sanpham/caphemuoi.jpg", "30000"},
                {"CC100", "CaCao Nóng", "src/pic/sanpham/cacaonong.jpg", "35000"},
                {"TS100", "Trà Sữa TT", "src/pic/sanpham/trasuatruyenthong.jpg", "20000"},
                {"TS101", "Trà Sữa TT Đường Đen", "src/pic/sanpham/trasuatrantrauduongden.jpg", "30000"},
                {"TS102", "Trà Sữa Bạc Hà", "src/pic/sanpham/trasuabacha.jpg", "25000"},
                {"TS103", "Trà Sữa Thái Xanh", "src/pic/sanpham/trasuathaixanh.jpg", "30000"},
                {"NE100", "Nước cam", "src/pic/sanpham/nuocepcam.jpg", "15000"},
                {"TS105", "Trà Đào", "src/pic/sanpham/tradao.png", "20000"},
                {"TS106", "Trà Chanh", "src/pic/sanpham/trachanh.jpg", "20000"},
                {"ST100", "Sinh Tố Sapoche", "src/pic/sanpham/sinhtosapo.jpg", "20000"},
                {"ST100", "Sinh Tố Bơ", "src/pic/sanpham/sinhtobo.png", "25000"},
                {"ĐA100", "Bánh Khoai Tây", "src/pic/sanpham/bimbim.jpg", "10000"},
                {"ĐA101", "Bánh Bông Lan", "src/pic/sanpham/banhbonglan.jpg", "20000"},
                {"ĐA102", "Bánh Mì", "src/pic/sanpham/banhmi.jpg", "25000"},
                {"ĐA103", "Bánh Xu Kem", "src/pic/sanpham/banhxukem.jpg", "30000"},
        };

        for (String[] sp : dsSP) {
            JPanel card = new JPanel(new BorderLayout(0, 5));
            card.setPreferredSize(new Dimension(150, 180));
            card.setBackground(Color.WHITE);
            card.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180), 1));

            ImageIcon icon = new ImageIcon(sp[2]);
            Image img = icon.getImage().getScaledInstance(120, 100, Image.SCALE_SMOOTH);
            JLabel lblImage = new JLabel(new ImageIcon(img));
            lblImage.setHorizontalAlignment(SwingConstants.CENTER);
            card.add(lblImage, BorderLayout.CENTER);

            JLabel lblTen = new JLabel("<html><center>" + sp[1] + "<br><b>" + sp[3] + "₫</b></center></html>");
            lblTen.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            lblTen.setHorizontalAlignment(SwingConstants.CENTER);
            card.add(lblTen, BorderLayout.SOUTH);

            card.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                	model.addRow(new Object[]{sp[0], sp[1], sp[3] + "₫", 1, sp[3] + "₫", "", "X"});
                	capNhatTongTien();

                }
            });

            pnlSP.add(card);
        }

        pnlMain.add(pnlLeft);

        // ===== BÊN PHẢI =====
        JPanel pnlRight = new JPanel(new BorderLayout(5, 5));
        pnlRight.setOpaque(false);

        // BẢNG SẢN PHẨM
        String[] cols = {"Mã SP", "Tên SP", "Đơn giá", "Số lượng", "Thành tiền", "Ghi chú", ""};
        model = new DefaultTableModel(cols, 0);
        tblSanPhamDaChon = new JTable(model);
        JScrollPane scrollHD = new JScrollPane(tblSanPhamDaChon);
        scrollHD.setBorder(new TitledBorder("Danh sách sản phẩm đã chọn"));

        // FORM THÔNG TIN KHÁCH HÀNG
        JPanel pnlInfo = new JPanel(new GridLayout(0, 2, 5, 5));
        pnlInfo.setBorder(BorderFactory.createTitledBorder("Thông tin hóa đơn"));
        pnlInfo.setOpaque(false);

        pnlInfo.add(new JLabel("SĐT:"));
        JPanel pnlSDT = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        pnlSDT.setOpaque(false);
        txtSDT = new JTextField(12);
        btnTimKH = new JButton("🔍");
        btnTimKH.setPreferredSize(new Dimension(40, 24));
        btnTimKH.addActionListener(this);
        pnlSDT.add(txtSDT);
        pnlSDT.add(btnTimKH);
        pnlInfo.add(pnlSDT);

        pnlInfo.add(new JLabel("Họ tên:"));
        JPanel pnlHoTen = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        pnlHoTen.setOpaque(false);
        txtHoTen = new JTextField(12);
        btnThemKH = new JButton("+");
        btnThemKH.setPreferredSize(new Dimension(40, 24));
        btnThemKH.addActionListener(this);
        pnlHoTen.add(txtHoTen);
        pnlHoTen.add(btnThemKH);
        pnlInfo.add(pnlHoTen);

        pnlInfo.add(new JLabel("Tổng tiền SP:"));
        txtTongTienSP = new JTextField("0₫");
        txtTongTienSP.setEditable(false);
        pnlInfo.add(txtTongTienSP);

        pnlInfo.add(new JLabel("Tổng tiền hóa đơn:"));
        txtTongTienHD = new JTextField("0₫");
        txtTongTienHD.setEditable(false);
        pnlInfo.add(txtTongTienHD);

        pnlInfo.add(new JLabel("Tiền khách trả:"));
        txtTienKhachTra = new JTextField();
        pnlInfo.add(txtTienKhachTra);

        pnlInfo.add(new JLabel("Tiền thừa:"));
        txtTienThua = new JTextField("0₫");
        txtTienThua.setEditable(false);
        pnlInfo.add(txtTienThua);

        pnlInfo.add(new JLabel("Loại TT:"));
        cboLoaiTT = new JComboBox<>(new String[]{"Tiền mặt", "Chuyển khoản"});
        pnlInfo.add(cboLoaiTT);

        pnlInfo.add(new JLabel("Trạng thái:"));
        cboTrangThai = new JComboBox<>(new String[]{"Chờ order", "Đã thanh toán"});
        pnlInfo.add(cboTrangThai);

        // GHÉP LẠI BẰNG JSplitPane (KHÔNG CHE)
        JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, scrollHD, pnlInfo);
        split.setResizeWeight(0.7);  // 70% bảng, 30% form
        split.setDividerSize(5);
        pnlRight.add(split, BorderLayout.CENTER);

        // ===== NÚT =====
        JPanel pnlButton = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        pnlButton.setOpaque(false);
        JButton btnHuy = new JButton("Hủy");
        JButton btnThanhToan = new JButton("Thanh toán");
        pnlButton.add(btnHuy);
        pnlButton.add(btnThanhToan);
        pnlRight.add(pnlButton, BorderLayout.SOUTH);

        // ===== XỬ LÝ THANH TOÁN =====
        btnThanhToan.addActionListener(ev -> {
            double tong = parseMoney(txtTongTienHD.getText());
            double tra = parseMoney(txtTienKhachTra.getText());
            double thua = tra - tong;
            if (thua < 0) thua = 0;
            txtTienThua.setText(String.format("%,.0f₫", thua));
            cboTrangThai.setSelectedItem("Đã thanh toán");

            try {
                // ===== 1️⃣ TẠO ĐƠN HÀNG =====
                DonHang_dao donHang_dao = new DonHang_dao();
                DonHang dh = new DonHang();

                dh.setTongTien(tong);
                dh.setPhuongThucThanhToan(cboLoaiTT.getSelectedItem().toString());
                dh.setMoTa("Thanh toán tại quầy");
                dh.setTrangThai("Đã thanh toán");

                // gán mã NV ngẫu nhiên
                String[] maNVs = {"NV100", "NV101"};
                dh.setMaNhanVien(maNVs[(int) (Math.random() * maNVs.length)]);
                dh.setMaBan(1);

                String maDonHang = donHang_dao.themDonHangVaTraVeMa(dh); // 🟢 TRẢ VỀ MÃ ĐƠN HÀNG

                if (maDonHang == null) {
                    JOptionPane.showMessageDialog(this, "❌ Lưu đơn hàng thất bại!");
                    return;
                }

                // ===== 2️⃣ TẠO HÓA ĐƠN =====
                HoaDon_dao hoaDon_dao = new HoaDon_dao();
                HoaDon hd = new HoaDon();
                hd.setMaDonHang(maDonHang);
                hd.setTongGia(tong);
                hd.setNgayXuat(java.time.LocalDate.now());
                if (hd.getMaHoaDon() == null || hd.getMaHoaDon().isEmpty()) {
                    String maHoaDon = "HD" + System.currentTimeMillis();
                    hd.setMaHoaDon(maHoaDon);
                }
                hoaDon_dao.themHoaDon(hd);

                // ===== 3️⃣ LƯU CHI TIẾT ĐƠN HÀNG =====
                ChiTietDonHang_dao ctdh_dao = new ChiTietDonHang_dao();

                for (int i = 0; i < model.getRowCount(); i++) {
                    ChiTietDonHang ct = new ChiTietDonHang();
                    ct.setMaDonHang(maDonHang);
                    ct.setMaSanPham(model.getValueAt(i, 0).toString()); // mã SP
                    ct.setSoLuong(Integer.parseInt(model.getValueAt(i, 3).toString())); // cột SL
                    ct.setGiaGoc(parseMoney(model.getValueAt(i, 2).toString()));
                    ct.setTongTienDonHang(ct.getGiaGoc() * ct.getSoLuong());
                    ctdh_dao.themChiTietDonHang(ct);
                }

                // ===== 4️⃣ HOÀN TẤT =====
                JOptionPane.showMessageDialog(this,
                        "✅ Thanh toán thành công!\nĐơn hàng: " + maDonHang,
                        "Hoàn tất", JOptionPane.INFORMATION_MESSAGE);

                // reset form
                model.setRowCount(0);
                txtTienKhachTra.setText("");
                txtTienThua.setText("0₫");
                txtTongTienSP.setText("0₫");
                txtTongTienHD.setText("0₫");

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "⚠️ Lỗi khi lưu dữ liệu: " + ex.getMessage());
            }
        });


        btnHuy.addActionListener(ev -> {
            model.setRowCount(0);
            txtSDT.setText("");
            txtHoTen.setText("");
            txtTienKhachTra.setText("");
            txtTienThua.setText("0₫");
            txtTongTienSP.setText("0₫");
            txtTongTienHD.setText("0₫");
            cboTrangThai.setSelectedItem("Chờ order");
        });
        
     // AUTO UPDATE THÀNH TIỀN KHI SỬA SỐ LƯỢNG
        tblSanPhamDaChon.addPropertyChangeListener(evt -> {
            if ("tableCellEditor".equals(evt.getPropertyName()) && !tblSanPhamDaChon.isEditing()) {
                int row = tblSanPhamDaChon.getSelectedRow();
                if (row >= 0) {
                    try {
                        double donGia = parseMoney(model.getValueAt(row, 2).toString());
                        int sl = Integer.parseInt(model.getValueAt(row, 3).toString());
                        double thanhTien = donGia * sl;

                        model.setValueAt(String.format("%,.0f₫", thanhTien), row, 4);
                        capNhatTongTien();
                    } catch (Exception ex) {
                        // Nếu nhập sai số → reset SL = 1
                        model.setValueAt(1, row, 3);
                    }
                }
            }
        });
        
     // TỰ ĐỘNG CẬP NHẬT TIỀN THỪA KHI NHẬP TIỀN KHÁCH TRẢ
        txtTienKhachTra.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {

            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                capNhatTienThoi();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                capNhatTienThoi();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                capNhatTienThoi();
            }
        });



        pnlMain.add(pnlRight);
    }

    private double parseMoney(String str) {
        if (str == null || str.isEmpty()) return 0;
        str = str.replace("₫", "").replace(".", "").replace(",", "").trim();
        try {
            return Double.parseDouble(str);
        } catch (Exception e) {
            return 0;
        }
    }


    private void capNhatTongTien() {
        double tong = 0;

        for (int i = 0; i < model.getRowCount(); i++) {
            String tt = model.getValueAt(i, 4).toString();
            tong += parseMoney(tt);
        }

        txtTongTienSP.setText(String.format("%,.0f₫", tong));
        txtTongTienHD.setText(String.format("%,.0f₫", tong));
    }
    
    private void capNhatTienThoi() {
        double tongHD = parseMoney(txtTongTienHD.getText());
        double tienKhach = parseMoney(txtTienKhachTra.getText());

        double tienThoi = tienKhach - tongHD;
        if (tienThoi < 0) tienThoi = 0;

        txtTienThua.setText(String.format("%,.0f₫", tienThoi));
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == btnTimKH) {
            String sdt = txtSDT.getText().trim();
            if (sdt.endsWith("0") || sdt.endsWith("2") || sdt.endsWith("4")) {
                txtHoTen.setText("Nguyễn Văn A");
            } else {
                txtHoTen.setText("Trần Thị B");
            }
            JOptionPane.showMessageDialog(this, "Tìm thấy khách hàng: " + txtHoTen.getText());
        } else if (src == btnThemKH) {
            String newName = JOptionPane.showInputDialog(this, "Nhập tên khách hàng mới:");
            if (newName != null && !newName.trim().isEmpty()) {
                txtHoTen.setText(newName.trim());
                JOptionPane.showMessageDialog(this, "Đã thêm khách: " + newName);
            }
        }
    }
}
