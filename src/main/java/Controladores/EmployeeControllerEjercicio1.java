package Controladores;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.daw.Company_DB_App.DataBaseConector;

import Exceptions.CompanyException;
import Exceptions.EmployeeDataException;
import Exceptions.IncorrectDataException;
import dao.EmployeeDAOImpl;
import dto.OpcionComboDTO;
import miVista.AltaEmpleadoVista;
import tablas.Employee;

public class EmployeeControllerEjercicio1 {

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

	public void cargaVistaAltaEmpleado() throws SQLException, CompanyException {
		List<OpcionComboDTO> listaManagers = new ArrayList<>();

		try (Connection conn = DataBaseConector.getConnection();) {

			EmployeeDAOImpl e1 = new EmployeeDAOImpl();
			List<Employee> listaEmpleado = e1.getAll(conn);
			for (var emp : listaEmpleado) {
				listaManagers
						.add(new OpcionComboDTO(emp.getEmployeeId(), emp.getFirstName() + " " + emp.getLastName()));

			}

		} catch (EmployeeDataException e) {
			e.printStackTrace();
		}

		AltaEmpleadoVista aev = new AltaEmpleadoVista(listaManagers);
	}

	public void guardarEmpleado(Employee empleado) throws IncorrectDataException, EmployeeDataException {
		try (Connection conn = DataBaseConector.getConnection();) {
			EmployeeDAOImpl EmployeeDAO = new EmployeeDAOImpl();
			EmployeeDAO.create(conn, empleado);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
