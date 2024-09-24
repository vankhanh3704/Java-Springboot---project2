package com.javaweb.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// do bị lặp lại nhiều nên tạo 1 util để kết nối tới JDBC 
public class ConnectionJDBCUtil {
	static final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic?autoReconnect=true&useSSL=false";
	static final String username = "root";
	static final String password = "Hoangkhanhvan703";
	
	public static Connection getConnection() {
		// khai báo 1 Connection để có thể trả về conn
		try {
			Connection conn = DriverManager.getConnection(DB_URL, username, password);
			return conn;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}
}
