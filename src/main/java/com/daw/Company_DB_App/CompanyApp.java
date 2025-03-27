package com.daw.Company_DB_App;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import Exceptions.CompanyException;
import dao.DAO;
import dao.EmployeeDAOImpl;
import tablas.Employee;

public class CompanyApp {
	public static void main(String[] args) {
		DAO<Employee> employeeDAO = new EmployeeDAOImpl();
		Connection conn = null;

		try {
			conn = DataBaseConector.getConnection();
			List<Employee> list = employeeDAO.getAll(conn);

			printList(list);
		} catch (SQLException e) {
			System.out.println("No se ha podido conectar a la base de datos: " + e.getMessage());
		} catch (CompanyException e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			if (conn != null) {
				DataBaseConector.closeConnection(conn);
			}
		}
	}

	private static void printList(List<Employee> list) {
		String str = "";
		for (Object element : list) {
			str += element.toString() + "\n";
		}
		System.out.println(str);
	}
}
