package miVista;


import java.awt.BorderLayout;
import java.util.List;

import javax.swing.*;
import tablas.Customer;

public class TablaClienteVista extends JFrame {

	private JButton btnModificarCliente;
	private JTable tablaCustomers;
	private List<Customer> customerList;
	private CustomerTableModel ctm;

	public TablaClienteVista(List<Customer> customerList) {
		this.customerList = customerList;
		ctm = new CustomerTableModel(customerList); // Este es el modelo de tabla que creé

		setTitle("Modificar Cliente");
		setSize(700, 400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);

		// En vez de GridLayout, puse BorderLayout porque así podemos colocar
		// la tabla arriba (CENTER) y el botón abajo (SOUTH), y se ve mejor
		setLayout(new BorderLayout());

		// Aquí creamos la tabla y la metemos en un JScrollPane
		// Esto lo vi porque si no, la tabla no muestra bien todos los datos y no se
		// puede hacer scroll
		tablaCustomers = new JTable(ctm);
		JScrollPane scrollPane = new JScrollPane(tablaCustomers);

		// Creamos el botón que va abajo, en su propio panel
		btnModificarCliente = new JButton("Modificar cliente");
		btnModificarCliente.setEnabled(false); // Deshabilitado al inicio

		JPanel panelBoton = new JPanel(); // Un panel para poner el botón centrado
		panelBoton.add(btnModificarCliente);
		
		

		// Le digo a la tabla que solo se puede seleccionar UNA fila a la vez.
		// Esto es importante porque solo quiero que se modifique un cliente, no varios
		// a la vez.
		tablaCustomers.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// Añado un listener que se activa cada vez que el usuario selecciona o cambia
		// la selección de una fila.
		tablaCustomers.getSelectionModel().addListSelectionListener(e -> {

			// Esta condición sirve para evitar que el evento se haga dos veces o
			// mientras se está "ajustando" la selección.
			// Lo encontré buscando cómo evitar que se ejecute innecesariamente cuando haces
			// clic varias veces.
			if (!e.getValueIsAdjusting()) {

				// Si hay una fila seleccionada, habilito el botón "Modificar cliente".
				// Si no hay ninguna fila seleccionada (por ejemplo si haces clic fuera de la
				// tabla), lo desactivo.
				btnModificarCliente.setEnabled(tablaCustomers.getSelectedRow() != -1);
			}
		});
		
		

		// Añadimos todo a la ventana: la tabla arriba y el botón abajo
		add(scrollPane, BorderLayout.CENTER);
		add(panelBoton, BorderLayout.SOUTH);

		setVisible(true);
	}

	// Estos métodos son opcionales pero útiles si quieres acceder al botón o tabla
	// desde otro sitio
	public JButton getBtnModificarCliente() {
		return btnModificarCliente;
	}

	public JTable getTablaCustomers() {
		return tablaCustomers;
	}
}
