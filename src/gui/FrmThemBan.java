package gui;

import javax.swing.*;
import java.awt.*;
import dao.ban_dao;
import entity.Ban;

public class FrmThemBan extends JDialog {

    private JTextField txtMaBan, txtKichThuoc, txtViTri;
    private JComboBox<String> cbTrangThai;

    private Runnable reloadCallback;

    public FrmThemBan(JPanel pcenter, Runnable reloadCallback) {
        this.reloadCallback = reloadCallback;

        setTitle("Thêm bàn mới");
        setSize(300, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2, 10, 10));

        add(new JLabel("Mã bàn:"));
        txtMaBan = new JTextField();
        add(txtMaBan);

        add(new JLabel("Kích thước:"));
        txtKichThuoc = new JTextField();
        add(txtKichThuoc);

        add(new JLabel("Vị trí:"));
        txtViTri = new JTextField();
        add(txtViTri);

        add(new JLabel("Trạng thái:"));
        cbTrangThai = new JComboBox<>(new String[]{"Trong",  "Dang phuc vu"});
        add(cbTrangThai);

        JButton btnThem = new JButton("Thêm");
        btnThem.addActionListener(e -> themBan());
        add(new JLabel());
        add(btnThem);

        setModal(true);
        setVisible(true);
    }

    private void themBan() {
        String ma = txtMaBan.getText().trim();
        String vt = txtViTri.getText().trim();
        String tt = (String) cbTrangThai.getSelectedItem();

        if (ma.isEmpty() || vt.isEmpty() || txtKichThuoc.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
            return;
        }

        int kt;
        try {
            kt = Integer.parseInt(txtKichThuoc.getText().trim());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Kích thước phải là số!");
            return;
        }

        Ban b = new Ban(ma, tt, kt, vt);
        ban_dao dao = new ban_dao();

        if (dao.themBan(b)) {
            JOptionPane.showMessageDialog(this, "Thêm bàn thành công!");

            if (reloadCallback != null) {
                reloadCallback.run();
            }

            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Thêm bàn thất bại!");
        }
    }
}
