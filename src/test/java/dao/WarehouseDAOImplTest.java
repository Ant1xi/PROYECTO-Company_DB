package dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.jupiter.api.*;

import com.daw.Company_DB_App.DataBaseConector;

import Exceptions.CompanyException;
import Exceptions.WarehouseDataException;
import d_EjercicioFormativo2.WarehouseDAOImpl;
import tablas.Warehouse;

public class WarehouseDAOImplTest {

    private static Connection conn;
    private WarehouseDAOImpl dao;

    @BeforeAll
    public static void setupConnection() throws Exception {
        conn = DataBaseConector.getConnection();
    }

    @AfterAll
    public static void closeConnection() throws Exception {
        DataBaseConector.closeConnection(conn);
    }

    @BeforeEach
    public void init() {
        dao = new WarehouseDAOImpl();
    }

    @Test
    public void testCreateWarehouseSuccessfully() throws Exception {
        Warehouse warehouse = new Warehouse("TestWarehouseOracle", 1);

        dao.create(conn, warehouse);

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "SELECT * FROM warehouses WHERE warehouse_name = 'TestWarehouseOracle'")) {

            assertTrue(rs.next(), "El registro debe existir en la base de datos");
            assertEquals("TestWarehouseOracle", rs.getString("warehouse_name"));
            assertEquals(1, rs.getInt("location_id"));
        }
    }

    @Test
    public void testCreateWarehouseWithNullValuesThrowsException() {
        assertThrows(WarehouseDataException.class, () -> {
            new Warehouse(null, null);
        });
    }
}
