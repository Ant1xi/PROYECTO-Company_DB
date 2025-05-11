package tablas;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Exceptions.EmployeeDataException;

class EmployeeTest {

    private Employee emp;
    private Date fechaContratacion;

 // Curiosidad que descubrí haciendo este test:
 // Cuando pones new Date(0), te da la fecha 1 de enero de 1970.
 // Al parecer esa es como la "fecha cero" que usan los ordenadores para contar el tiempo.
 // Me pareció curioso porque no lo sabía, y se usa bastante en pruebas porque siempre es la misma.

    @BeforeEach
    void setUp() throws EmployeeDataException {
        // Creamos una fecha específica para poder compararla luego en el toString
        fechaContratacion = new Date();

        // Creamos un empleado válido con todos los campos obligatorios
        emp = new Employee(101, "Lucía", "Gómez", "lucia.gomez@gmail.com", "600123456", fechaContratacion, 200, "Analista");
    }

    @Test
    void testConstructorYGetters() {
        // Verificamos que el constructor haya asignado correctamente todos los atributos
        assertEquals(101, emp.getEmployeeId());
        assertEquals("Lucía", emp.getFirstName());
        assertEquals("Gómez", emp.getLastName());
        assertEquals("lucia.gomez@gmail.com", emp.getEmail());
        assertEquals("600123456", emp.getPhone());
        assertEquals(fechaContratacion, emp.getHireDate());
        assertEquals(200, emp.getManagerId());
        assertEquals("Analista", emp.getJobTitle());
    }

    @Test
    void testSetters() {
        // Cambiamos todos los valores para comprobar que los setters funcionan correctamente
        emp.setEmployeeId(102);
        emp.setFirstName("Carlos");
        emp.setLastName("Ramírez");
        emp.setEmail("c.ramirez@gmail.com");
        emp.setPhone("655987321");

        // Usamos una fecha fija para que sea fácil de comparar
        emp.setHireDate(new Date(0));
        emp.setManagerId(201);
        emp.setJobTitle("Desarrollador");

        assertEquals(102, emp.getEmployeeId());
        assertEquals("Carlos", emp.getFirstName());
        assertEquals("Ramírez", emp.getLastName());
        assertEquals("c.ramirez@gmail.com", emp.getEmail());
        assertEquals("655987321", emp.getPhone());
        assertEquals(new Date(0), emp.getHireDate());
        assertEquals(201, emp.getManagerId());
        assertEquals("Desarrollador", emp.getJobTitle());
    }

    @Test
    void testConstructorLanzaExcepcionSiHayCamposNulos() {
        // Cada uno de estos debe lanzar la excepción porque falta un campo obligatorio
        assertThrows(EmployeeDataException.class, () -> {
            new Employee(103, null, "Martínez", "m.martinez@gmail.com", "600000000", new Date(), 300, "Gerente");
        });

        assertThrows(EmployeeDataException.class, () -> {
            new Employee(104, "Pedro", "Martínez", null, "600000000", new Date(), 300, "Gerente");
        });

        assertThrows(EmployeeDataException.class, () -> {
            new Employee(105, "Pedro", "Martínez", "pedro.martinez@gmail.com", "600000000", new Date(), 300, null);
        });
    }

    @Test
    void testHireDatePorDefectoSiEsNula() throws EmployeeDataException {
        // Si no se pasa fecha, debe asignarse la fecha actual automáticamente
        Employee e = new Employee("Ana", "López", "ana.lopez@gmail.com", "677112233", null, null, "Diseñadora");

        // No importa la fecha exacta, solo hay que comprobar que no sea null
        assertNotNull(e.getHireDate());
    }

    @Test
    void testToString() {
        // El texto esperado debe tener exactamente el mismo formato que el toString original
        String textoEsperado = "Employee [employeeId=101, firstName=Lucía, lastName=Gómez, email=lucia.gomez@gmail.com, phone=600123456, hireDate="
                + fechaContratacion + ", managerId=200, jobTitle=Analista]";

        assertEquals(textoEsperado, emp.toString());
    }
}
