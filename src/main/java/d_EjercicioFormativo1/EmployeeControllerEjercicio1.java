package d_EjercicioFormativo1;

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
import tablas.Employee;

public class EmployeeControllerEjercicio1 {

	public void cargaVistaAltaEmpleado() throws SQLException, CompanyException {
		List<OpcionComboDTO> listaManagers = new ArrayList<>();

		try (Connection conn = DataBaseConector.getConnection();) {

			EmployeeDAOImpl e1 = new EmployeeDAOImpl();
			List<Employee> listaEmpleado = e1.getAll(conn);
			for (Employee emp : listaEmpleado) {
				listaManagers
						.add(new OpcionComboDTO(emp.getEmployeeId(), emp.getFirstName() + " " + emp.getLastName()));

			}

		} catch (EmployeeDataException e) {
			e.printStackTrace();
		}

		AltaEmpleadoVista aev = new AltaEmpleadoVista(listaManagers);
	}

	public void guardarEmpleado(String firstName, String lastName, String email, String phone, Date fecha,
			Integer managerId, String jobTitle) throws IncorrectDataException, EmployeeDataException {
		// Una vez validado todo y obtenido la id, ya se puede crear al nuevo empleado
		Employee empleado = new Employee(firstName, lastName, email, phone, null, managerId, jobTitle);

		try (Connection conn = DataBaseConector.getConnection();) {
			EmployeeDAOImpl EmployeeDAO = new EmployeeDAOImpl();
			EmployeeDAO.create(conn, empleado);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
