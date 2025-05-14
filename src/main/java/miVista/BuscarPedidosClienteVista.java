package miVista;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import tablas.Order;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;

public class BuscarPedidosClienteVista extends JFrame {

	private JButton btnBuscarCliente;
	private JButton btnDetallePedido;
	private JTextField txtCustomerId, txtCustomerName;
	private JTable tablaOrders;

	public BuscarPedidosClienteVista(List<Order> orderList) {
		setTitle("Buscar pedidos de cliente");
		setSize(700, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// Usamos un GridLayout con 6 filas (3 zonas: campos, tabla, botones) y 2
		// columnas
		setLayout(new GridLayout(6, 2, 10, 10));

		// Campos de búsqueda
		JLabel lblCustomerId = new JLabel("Customer ID:");
		txtCustomerId = new JTextField();

		JLabel lblCustomerName = new JLabel("Nombre:");
		txtCustomerName = new JTextField();

		// Botón buscar
		btnBuscarCliente = new JButton("Buscar cliente");
		btnBuscarCliente.setEnabled(false); // desactivado al principio

		// Botón detalles
		btnDetallePedido = new JButton("Ver detalles del pedido");
		btnDetallePedido.setEnabled(false);

		// Tabla
		tablaOrders = new JTable(new OrderTableModel(orderList));
		tablaOrders.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scrollPane = new JScrollPane(tablaOrders);

		// Habilitar botón detalle si hay una fila seleccionada
		tablaOrders.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					btnDetallePedido.setEnabled(tablaOrders.getSelectedRow() != -1);
				}

			}
		});

		// Añadir los componentes al layout
		add(lblCustomerId);
		add(txtCustomerId);

		add(lblCustomerName);
		add(txtCustomerName);

		add(new JLabel()); // espacio vacío
		add(btnBuscarCliente);

		// Añadimos el scrollPane de la tabla a 2 columnas (lo "forzamos")
		add(new JLabel("Pedidos:"));
		add(new JLabel());
		add(scrollPane);
		add(new JLabel()); // para ajustar el espacio

		add(new JLabel()); // espacio vacío
		add(btnDetallePedido);

		txtCustomerId.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				String Id = txtCustomerId.getText().trim();

				if (!Id.isEmpty()) {
					btnBuscarCliente.setEnabled(true);
				} else {
					btnBuscarCliente.setEnabled(false);
				}

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});

		txtCustomerName.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				String Nombre = txtCustomerName.getText().trim();

				if (!Nombre.isEmpty()) {
					btnBuscarCliente.setEnabled(true);
				} else {
					btnBuscarCliente.setEnabled(false);
				}

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});

		boolean camposLlenos = isAlgoEscrito();

		if (camposLlenos == true) {
			btnBuscarCliente.setEnabled(true);
		} else {
			btnBuscarCliente.setEnabled(false);
		}
	}

	// Getters
	public JTextField getTxtCustomerId() {
		return txtCustomerId;
	}

	public JTextField getTxtCustomerName() {
		return txtCustomerName;
	}

	public JButton getBtnBuscarCliente() {
		return btnBuscarCliente;
	}

	public JButton getBtnDetallePedido() {
		return btnDetallePedido;
	}

	public JTable getTablaOrders() {
		return tablaOrders;
	}

	public boolean isAlgoEscrito() {

		boolean isAlgoEscrito;

		boolean isId = !txtCustomerId.getText().trim().isEmpty();
		boolean isNombre = !txtCustomerName.getText().trim().isEmpty();

		if (isId == true || isNombre == true) {
			isAlgoEscrito = true;
		} else {
			isAlgoEscrito = false;
		}

		return isAlgoEscrito;

	}
}
