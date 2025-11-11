package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class frmSanPham extends JPanel {
	public frmSanPham() {
		// Đây là panel nội dung chính của Hóa đơn (khi được show trong CardLayout)
        setLayout(new BorderLayout());
        setBackground(new Color(245, 222, 179)); // màu nền giống app

        // Header / title của trang Hóa đơn
        JLabel lblTitle = new JLabel("Trang Sản Phẩm", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(lblTitle, BorderLayout.NORTH);

        // Nội dung mẫu: ở giữa để bạn thay bằng bảng, form, ...
        JPanel content = new JPanel();
        content.setOpaque(false); // transparent để thấy nền
        content.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        // Ví dụ placeholder: bạn thay bằng JTable hoặc form nhập hóa đơn
        JLabel placeholder = new JLabel(new ImageIcon("src/pic/cup.png")); // nếu có ảnh
        placeholder.setText("Pắc Kỳ chó");
        placeholder.setHorizontalTextPosition(SwingConstants.CENTER);
        placeholder.setVerticalTextPosition(SwingConstants.BOTTOM);
        content.add(placeholder);

        add(content, BorderLayout.CENTER);
	}
}
