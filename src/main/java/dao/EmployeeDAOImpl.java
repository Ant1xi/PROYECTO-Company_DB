package dao;

import java.sql.*;
import java.util.*;
import java.util.Date;

import Exceptions.EmployeeDataException;
import Exceptions.IncorrectDataException;
import tablas.Employee;

public class EmployeeDAOImpl {

	public List<Employee> getAll(Connection conn) throws EmployeeDataException, IncorrectDataException {
		String sqlQuery = "SELECT * FROM employees";

		List<Employee> employeesList = new ArrayList<>();

		try (PreparedStatement pstmt = conn.prepareStatement(sqlQuery); ResultSet rs = pstmt.executeQuery();) {

			while (rs.next()) {
				Integer employeeId = rs.getInt("employee_id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String email = rs.getString("email");
				String phone = rs.getString("phone");
				Date hireDate = rs.getDate("hire_date");
				Integer managerId = rs.getInt("manager_id");
				String jobTitle = rs.getString("job_title");

				employeesList.add(
						new Employee(employeeId, firstName, lastName, email, phone, hireDate, managerId, jobTitle));

			}
			return employeesList;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new EmployeeDataException();
		}

	}

	public void create(Connection conn, Employee employee) throws EmployeeDataException {
		String sqlQuery = "INSERT INTO employees "
				+ "(first_name, last_name, email, phone, hire_date, manager_id, job_title) VALUES (?, ?, ?, ?, ?, ?, ?)";

		try (PreparedStatement pstmt = conn.prepareStatement(sqlQuery)) {
			pstmt.setString(1, employee.getFirstName());
			pstmt.setString(2, employee.getLastName());
			pstmt.setString(3, employee.getEmail());
			pstmt.setString(4, employee.getPhone());
			pstmt.setDate(5, new java.sql.Date(employee.getHireDate().getTime())); // Convertimos Date a java.sql.Date
			pstmt.setObject(6, employee.getManagerId(), Types.INTEGER); // Puede ser nulo
			pstmt.setString(7, employee.getJobTitle());

			pstmt.executeUpdate(); // Ejecutamos la consulta para insertar nuestro nuevo empleado

		} catch (SQLException e) {
			e.printStackTrace();
			throw new EmployeeDataException(); // Lanzamos una excepción personalizada si hay error
		}
	}

}
