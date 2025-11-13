package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class frmDangNhap extends JFrame implements ActionListener {
	JTextField txtDn;
	JPasswordField txtPw;
	JButton btnDn,btnDk;
	public frmDangNhap() {
		//porth
		
		JPanel pnorth = new JPanel();
		JLabel lbtittle = new JLabel("Quản Lý Cửa Hàng");
		pnorth.add(lbtittle);
		add(pnorth,BorderLayout.NORTH);
		
		//south
		JPanel psouth = new JPanel();
		JLabel lbcontent = new JLabel("HotLine liên hệ admin");
		psouth.setBorder(BorderFactory.createLineBorder(Color.blue));
		psouth.add(lbcontent);
		add(psouth,BorderLayout.SOUTH);
		
		//center
		JPanel pcenter = new JPanel();
		pcenter.setBorder(BorderFactory.createLineBorder(Color.blue));
		JLabel lbdangnhap = new JLabel("Tên đăng nhập");
		JLabel lbpassword = new JLabel("Password");
		pcenter.setLayout(new GridBagLayout());
		GridBagConstraints gb = new GridBagConstraints();
		gb.insets = new Insets(5, 5, 5, 5);
		gb.anchor = GridBagConstraints.WEST;
		gb.fill = GridBagConstraints.NONE;
		//ten dang nhap
		gb.gridx = 0;
		gb.gridy = 0;
		pcenter.add(new JLabel("Tên đăng nhập"),gb);
		gb.gridx = 1;
		gb.gridy = 0;
		txtDn = new JTextField();
		gb.gridwidth = 5;
		txtDn.setPreferredSize(new Dimension(200,20));
		pcenter.add(txtDn,gb);
		gb.gridwidth = 1;
		
		//pass
		gb.gridx = 0;
		gb.gridy = 1;
		pcenter.add(new JLabel("Password"),gb);
		gb.gridx = 1;
		gb.gridy = 1;
		txtPw = new JPasswordField();
		gb.gridwidth = 5;
		txtPw.setPreferredSize(new Dimension(200,20));
		pcenter.add(txtPw,gb);
		gb.gridwidth = 1;
		
		//btn
		gb.gridx = 2;
		gb.gridy = 2;
		pcenter.add(btnDn = new JButton("Đăng nhập"),gb);
		
		gb.gridx = 3;
		gb.gridy = 2;
		pcenter.add(btnDk = new JButton("Đăng ký"),gb);
		pnorth.setBackground(new Color(245,222,179));
		psouth.setBackground(new Color(245,222,179));
		
		pcenter.setBackground(new Color(245,222,179));
		add(pcenter,BorderLayout.CENTER);
		
		btnDn.addActionListener(this);
		
		
		setSize(400,300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Đăng Nhập");
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new frmDangNhap();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		if (obj == btnDn) {
		    String dnhap = txtDn.getText().trim();
		    String pass = txtPw.getText().trim();

		    String regexQuanLy = "quanlycafe";
		    String regexNhanVien = "^NV10[0-9]$";
		    String regexPass = "123456";

		    if (dnhap.equals("") || pass.equals("")) {
		        JOptionPane.showMessageDialog(this, "Nhập đủ thông tin");
		    } 
		    else if (
		        (!dnhap.equals(regexQuanLy) && !dnhap.matches(regexNhanVien)) // nếu không phải quản lý hoặc nhân viên hợp lệ
		        || !pass.equals(regexPass)
		    ) {
		        JOptionPane.showMessageDialog(this, "Tài khoản hoặc mật khẩu không đúng");
		    } 
		    else {
		        this.dispose(); 
		        new frmTrangChu(); // mở form chính
		    }
		}

	}
}
