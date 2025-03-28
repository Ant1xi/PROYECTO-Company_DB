package dao;

import java.sql.*;
import java.util.*;
import java.util.Date;

import Exceptions.EmployeeDataException;
import Exceptions.IncorrectDataException;
import tablas.Employee;

public class EmployeeDAOImpl implements DAO<Employee>{

	@Override
	public List<Employee> getAll(Connection conn) throws EmployeeDataException, IncorrectDataException {
		String sqlQuery = "SELECT * FROM employees";
		
		List<Employee> employeesList = new ArrayList<>();
		
		try (
				PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
				ResultSet rs = pstmt.executeQuery();
			) {
			
			while (rs.next()) {
				Integer employeeId = rs.getInt("employee_id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String email = rs.getString("email");
				String phone = rs.getString("phone");
				Date hireDate = rs.getDate("hire_date");
				Integer managerId = rs.getInt("manager_id");
				String jobTitle = rs.getString("job_title");
				
				employeesList.add(new Employee(employeeId, firstName, lastName, email, phone, hireDate, managerId, jobTitle));
				
			}
			return employeesList;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new EmployeeDataException();
		}
		
		
	}

	@Override
	public Employee get(Connection conn, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(Connection conn, Employee t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Connection conn, Employee t, Object[] params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Connection conn, Employee t) {
		// TODO Auto-generated method stub
		
	}

}
