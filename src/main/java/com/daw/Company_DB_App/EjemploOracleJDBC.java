package com.daw.Company_DB_App;

import java.sql.*;

public class EjemploOracleJDBC {
	// Datos de conexión
	private static final String URL = "jdbc:oracle:thin:@//localhost:1521/XE"; // O SERVICE_NAME
	private static final String USER = "C##Company";
	private static final String PASSWORD = "company";

	public static void main(String[] args) {
		// Intentar conectar y ejecutar consulta
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
			System.out.println("✅ Conexión exitosa a Oracle!");

			// Query de ejemplo
			String sql = "SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME FROM EMPLOYEES WHERE ROWNUM <= 100 ORDER BY EMPLOYEE_ID ASC";

			// Ejecutar la consulta
			try (PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

				// Recorrer los resultados
				while (rs.next()) {
					int id = rs.getInt("EMPLOYEE_ID");
					String firstName = rs.getString("FIRST_NAME");
					String lastName = rs.getString("LAST_NAME");

					System.out.println("👤 " + id + " | " + firstName + " " + lastName);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}