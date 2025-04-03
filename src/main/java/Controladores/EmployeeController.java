package Controladores;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import com.daw.Company_DB_App.DataBaseConector;

import Exceptions.EmployeeDataException;
import Exceptions.IncorrectDataException;
import dao.EmployeeDAOImpl;
import tablas.Employee;

public class EmployeeController {

	public void createNewEmployee(String firstName, String lastName, String email, String phone, Date hireDate,
			Integer managerId, String jobTitle) throws SQLException {

		try (Connection conn = DataBaseConector.getConnection();) {
			Employee e1 = new Employee(firstName, lastName, email, phone, hireDate, managerId, jobTitle);
			EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();

			employeeDAO.create(conn, e1); // Insertar en la BD
			System.out.println("Empleado insertado correctamente.");

		} catch (IncorrectDataException e) {
			e.printStackTrace();
		} catch (EmployeeDataException e) {
			e.printStackTrace();
		} 
	}
}
