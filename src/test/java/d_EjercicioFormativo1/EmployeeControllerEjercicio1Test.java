package d_EjercicioFormativo1;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

import java.sql.Connection;
import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.daw.Company_DB_App.DataBaseConector;

import dao.EmployeeDAOImpl;

class EmployeeControllerEjercicio1Test {

	@Mock
	private EmployeeDAOImpl mockDAO;

	@Mock
	private static Connection mockConn;

	@InjectMocks
	private EmployeeControllerEjercicio1 controller;

	@BeforeAll
	static void sutUp() {
		Mockito.mockStatic(DataBaseConector.class).when(DataBaseConector::getConnection).thenReturn(mockConn);
	}

	@Test
	void testGuardarEmpleado() throws Exception {
		// Creamos los datos para poblar el constructor
		String firstName = "Antonio";
		String lastName = "Castro";
		String email = "antcastro@gmail.com";
		String phone = "687093970";
		Date hireDate = new Date();
		Integer managerId = 1;
		String jobTitle = "Informatico";

		// Introducimos los datos creados en el constructor
		controller.guardarEmpleado(firstName, lastName, email, phone, hireDate, managerId, jobTitle);
		

		// ArgumentCaptor se encargará de capturar datos de la clase Employee
		ArgumentCaptor<tablas.Employee> empCaptor = ArgumentCaptor.forClass(tablas.Employee.class);

		// Tenemos que verificar que el create se ejecuta bien, esto es lo mismo que:
		// EmployeeDAOImpl.create(conn, Employee e);
		verify(mockDAO).create(mockConn, empCaptor.capture());

		// Si todo va bien recogemos el valor del Empleado
		tablas.Employee emp = empCaptor.getValue();

		// Finalmente comprobamos lo valores recogidos comparandolos con los datos
		// iniciales
		assertEquals(firstName, emp.getFirstName());
		assertEquals(lastName, emp.getLastName());
		assertEquals(email, emp.getEmail());
		assertEquals(phone, emp.getPhone());
		assertEquals(managerId, emp.getManagerId());
		assertEquals(jobTitle, emp.getJobTitle());
	}

}
