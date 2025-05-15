package d_EjercicioFormativo3;

import dao.ContactDAOImpl;
import dao.CustomerDAOImpl;
import tablas.Contact;
import tablas.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import com.daw.Company_DB_App.DataBaseConector;

public class FormularioModificarClienteVista extends JFrame {

	// Campos de texto para cada dato editable (4 de customers y 4 de contacts)
	private JTextField txtNombre, txtDireccion, txtWeb, txtCredito, txtNombreContacto, txtApellidoContacto, txtEmail,
			txtTelefono;
	private JButton btnGuardar;

	public FormularioModificarClienteVista(ClienteModificarDTO dto) {
		setTitle("Modificar Cliente");
		setSize(500, 400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new GridLayout(9, 2, 10, 10)); // 9 filas, 2 columnas y la separacion

		// Campos del cliente tabla customers
		add(new JLabel("Nombre de la empresa:"));
		txtNombre = new JTextField(dto.getCustomerName());
		add(txtNombre);

		add(new JLabel("Dirección:"));
		txtDireccion = new JTextField(dto.getPhone());
		add(txtDireccion);

		add(new JLabel("Página web:"));
		txtWeb = new JTextField(dto.getWebsite());
		add(txtWeb);

		add(new JLabel("Límite de crédito:"));
		txtCredito = new JTextField(dto.getCreditLimit());
		add(txtCredito);

		// Campos del contacto de la tabla contacts
		add(new JLabel("Nombre de contacto:"));
		txtNombreContacto = new JTextField(dto.getFirstName());
		add(txtNombreContacto);

		add(new JLabel("Apellido de contacto:"));
		txtApellidoContacto = new JTextField(dto.getLastName());
		add(txtApellidoContacto);

		add(new JLabel("Email de contacto:"));
		txtEmail = new JTextField(dto.getEmail());
		add(txtEmail);

		add(new JLabel("Teléfono de contacto:"));
		txtTelefono = new JTextField(dto.getPhone());
		add(txtTelefono);

		// Botón de guardar
		btnGuardar = new JButton("Grabar cambios");
		add(new JLabel()); // espacio vacío para alinear el botón
		add(btnGuardar);

		// Acción del botón
		btnGuardar.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        try {
		        	
		        	//Comprobamos si los campos estan vacios con el método creado fuera del constructor
		            if (camposVacios()) {
		                JOptionPane.showMessageDialog(FormularioModificarClienteVista.this,
		                        "Por favor, completa todos los campos.");
		                return;
		            }

		            //Crear 
		            Customer customer = new Customer(dto.getCustomerId(), txtNombre.getText(),
		                    txtDireccion.getText(), txtWeb.getText(), Double.parseDouble(txtCredito.getText()));

		            Contact contact = new Contact(dto.getContactId(), txtNombreContacto.getText(),
		                    txtApellidoContacto.getText(), txtEmail.getText(), txtTelefono.getText(),
		                    dto.getCustomerId());

		            try (Connection conn = DataBaseConector.getConnection()) {
		                CustomerDAOImpl customerDAO = new CustomerDAOImpl();
		                ContactDAOImpl contactDAO = new ContactDAOImpl();

		                customerDAO.update(conn, customer);
		                contactDAO.update(conn, contact);

		                JOptionPane.showMessageDialog(FormularioModificarClienteVista.this,
		                        "Datos actualizados correctamente.");
		                dispose();
		            }

		        } catch (Exception ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(FormularioModificarClienteVista.this,
		                    "Ocurrió un error al actualizar los datos.");
		        }
		    }
		});


		setVisible(true);
	}

	// Método para validar si hay campos vacíos
	private boolean camposVacios() {
		return txtNombre.getText().isEmpty() || txtDireccion.getText().isEmpty() || txtWeb.getText().isEmpty()
				|| txtCredito.getText().isEmpty() || txtNombreContacto.getText().isEmpty()
				|| txtApellidoContacto.getText().isEmpty() || txtEmail.getText().isEmpty()
				|| txtTelefono.getText().isEmpty();
	}
}
