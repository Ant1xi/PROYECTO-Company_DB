package d_EjercicioFormativo4;

import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.daw.Company_DB_App.DataBaseConector;

import dao.CustomerDAOImpl;
import dao.OrderDAOImpl;
import tablas.Customer;
import tablas.Order;

public class BuscarClienteYDetallesPedidoControllerEjercicio4 {

	public void cargarBuscarPedidosClienteVista() {

		
		try (Connection conn = DataBaseConector.getConnection()) {

			// Al abrir la ventana, la tabla empieza vacía (sin pedidos aún)
			List<Order> pedidosIniciales = new ArrayList<>();
			BuscarPedidosClienteVista vista = new BuscarPedidosClienteVista(pedidosIniciales);
			vista.setVisible(true);

			// Este listener se ejecuta cuando el usuario pulsa "Buscar cliente"
			vista.getBtnBuscarCliente().addActionListener((ActionEvent e) -> {

				String idRecogida = vista.getTxtCustomerId().getText().trim();
				String nombre = vista.getTxtCustomerName().getText().trim();

				// Truco que nos decía Quique muchas veces: darle un valor inicial a "id"
				// así evitamos que Java se queje porque puede no tener valor si no rellenamos
				// nada
				int id = -1;

				try {
					if (!idRecogida.isEmpty()) {
						// Convertimos el texto del campo ID a un número (int)
						// Esto es necesario porque si no lo haces, no puedes buscar por ID
						id = Integer.parseInt(idRecogida);
					}
				} catch (NumberFormatException excepcionDeNumeros) {
					// Si el usuario pone letras en vez de un número, mostramos este aviso
					JOptionPane.showMessageDialog(null, "El ID debe ser un número.");
					return;
				}

				// Aquí viene una parte importante:
				// Aunque ya habíamos hecho una conexión antes para abrir la vista,
				// abrimos otra (conexionExtra) para trabajar con los DAOs.
				//
				// Lo hago así porque si usamos la misma conexión anterior y algo falla o se
				// cierra,
				// la ventana también podría dejar de funcionar.

				try (Connection conexionExtra = DataBaseConector.getConnection()) {

					CustomerDAOImpl customerDAO = new CustomerDAOImpl();
					List<Customer> listaClientes = customerDAO.getByIdOrNombre( conexionExtra, id, nombre);

					if (listaClientes.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
						return;
					}

					int customerId = listaClientes.get(0).getCustomerId();
					System.out.println("ID del cliente encontrado: " + customerId);

					// Ahora que ya tenemos el ID, usamos otro DAO para obtener los pedidos
					OrderDAOImpl orderDAO = new OrderDAOImpl();
					List<Order> pedidos = orderDAO.getByCustomerId(conexionExtra, customerId);

					// Mostramos los pedidos en el JTable
					vista.getTablaOrders().setModel(new OrderTableModel(pedidos));

					// Si el cliente no tiene pedidos, también avisamos
					if (pedidos.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Este cliente no tiene pedidos.");
					}

				} catch (Exception ex) {
					// Por si algo falla al hacer la consulta de pedidos
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error al buscar pedidos.");
				}
			});

		} catch (SQLException e) {
			// Por si algo falla al cargar la ventana al principio
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error al cargar la vista.");
		}
	}
}