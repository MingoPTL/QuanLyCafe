package connectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.microsoft.sqlserver.jdbc.ISQLServerConnection;

public class ConnectDB {
	public static Connection con = null;
	private static ConnectDB instance = new ConnectDB();
	public static ConnectDB getInstance() {
		return instance;
	}
	
	public void connect() {
		String url = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyCafe;encrypt=false;trustServerCertificate=true";
		String user = "sa";
		String password = "Mingo@1234";

		
		try {
			con = DriverManager.getConnection(url,user,password);
			System.out.println("Oke");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void disconnect() {
		if(con!=null) {
			try {
				con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static Connection getConnection() {
		return con;
	}
}
