package Controladores;

import java.sql.Connection;
import java.util.List;

import javax.swing.JTable;
import javax.swing.JOptionPane;

import com.daw.Company_DB_App.DataBaseConector;

import dao.ContactDAOImpl;
import dao.CustomerDAOImpl;
import dto.ClienteModificarDTO;
import miVista.FormularioModificarClienteVista;
import miVista.TablaClienteVista;
import tablas.Contact;
import tablas.Customer;

public class ModificarCustomerControllerEjercicio3 {

	public void cargarVistaModificarCustomers() {

		List<Customer> listaClientes;

		try (Connection conn = DataBaseConector.getConnection()) {

			CustomerDAOImpl customerDAO = new CustomerDAOImpl();
			listaClientes = customerDAO.getAll(conn); 

			TablaClienteVista tcv = new TablaClienteVista(listaClientes);

			// Acción del botón "Modificar cliente"
			tcv.getBtnModificarCliente().addActionListener(e -> {
				try {
					//Llenamos el JTable
					JTable tabla = tcv.getTablaCustomers();
					
					int filaSeleccionada = tabla.getSelectedRow();

					if (filaSeleccionada == -1)
						return; // Si no hay selección, salgo

					// Recupero el cliente que se ha seleccionado
					Customer cliente = listaClientes.get(filaSeleccionada);
					int customerId = cliente.getCustomerId();

					// Me conecto otra vez para sacar su contacto
					try (Connection conn2 = DataBaseConector.getConnection()) {
						ContactDAOImpl contactDAO = new ContactDAOImpl();
						Contact contacto = contactDAO.getByCustomerId(conn2, customerId);

						// Creo el DTO que contiene todos los datos a modificar
						ClienteModificarDTO dto = new ClienteModificarDTO(cliente.getName(), cliente.getAddres(), null,

								cliente.getWebsite(), String.valueOf(cliente.getCreditLimit()), contacto.getFirstName(),
								contacto.getLastName(), contacto.getEmail());

						dto.setCustomerId(customerId);
						dto.setContactId(contacto.getContactId());
						dto.setPhone(contacto.getPhone());

						// Abro la ventana para editar los datos
						new FormularioModificarClienteVista(dto);
					}

				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error al abrir el formulario.");
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error al cargar los clientes.");
		}
	}
}
