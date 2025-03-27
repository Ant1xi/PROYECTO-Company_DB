package com.daw.Company_DB_App;

import java.sql.*;

public class DataBaseConector {
	private static final String URL = "jdbc:oracle:thin:@//localhost:1521/XE";
	private static final String User = "C##company";
	private static final String Password = "company";

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, User, Password);
	}

	public static void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}
}
