package miVista;

import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.daw.Company_DB_App.DataBaseConector;

import Controladores.EmployeeControllerEjercicio1;
import Exceptions.EmployeeDataException;
import Exceptions.IncorrectDataException;
import dao.EmployeeDAOImpl;
import dto.OpcionComboDTO;
import oracle.sql.OpaqueDescriptor;
import tablas.Employee;

@SuppressWarnings("serial")
public class AltaEmpleadoVista extends JFrame {

	// Campos de entrada de texto
	private JTextField txtFirstName, txtLastName, txtEmail, txtPhone, txtJobTitle;

	// ComboBox para seleccionar al jefe (manager)
	private JComboBox<OpcionComboDTO> cmbManager;

	// Botón para guardar el nuevo empleado
	private JButton btnGuardar;

	// Lista para guardar los managers cargados desde la base de datos
	private List<OpcionComboDTO> listaManagers;

	public AltaEmpleadoVista(List<OpcionComboDTO> listaManagers) {

		this.listaManagers = listaManagers;

		// Configuración básica de la ventana
		setTitle("Alta de Nuevo Empleado");
		setSize(500, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cerrar ventana pero no el programa
		setLocationRelativeTo(null); // Como es una ventana nueva no le podemos asignar la posicion donde se abre a
										// un Jtable por ejemplo
		setLayout(new GridLayout(7, 2)); // 7 filas, 2 columnas

		// Inicializar los campos de texto
		txtFirstName = new JTextField();
		txtLastName = new JTextField();
		txtEmail = new JTextField();
		txtPhone = new JTextField();
		txtJobTitle = new JTextField();

		// Inicializar ComboBox y botón
		cmbManager = new JComboBox<>();
		btnGuardar = new JButton("Guardar");

		// Crear etiquetas
		JLabel lblFirstName = new JLabel("Nombre:");
		JLabel lblLastName = new JLabel("Apellido:");
		JLabel lblEmail = new JLabel("Correo electrónico:");
		JLabel lblPhone = new JLabel("Teléfono:");
		JLabel lblJobTitle = new JLabel("Cargo:");
		JLabel lblManager = new JLabel("Jefe:");

		// Añadir etiquetas y campos al layout
		add(lblFirstName);
		add(txtFirstName);
		add(lblLastName);
		add(txtLastName);
		add(lblEmail);
		add(txtEmail);
		add(lblPhone);
		add(txtPhone);
		add(lblJobTitle);
		add(txtJobTitle);
		add(lblManager);
		add(cmbManager);

		add(new JLabel()); // Espacio vacío
		add(btnGuardar); // Botón de guardar

		// Mostrar ventana
		setVisible(true);

		// Cargar ComboBox con los managers desde la base de datos
		cargarComboBoxManagers();

		// Añadir acción al botón "Guardar"
		btnGuardar.addActionListener(e -> {
			try {
				try {
					guardarEmpleado();
				} catch (EmployeeDataException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} catch (IncorrectDataException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		limpiarCampos();
	}

	// Carga los nombres de los managers en el JComboBox

	private void cargarComboBoxManagers() {

		for (var opc : listaManagers) {
			cmbManager.addItem(opc);
		}

	}

	// Intenta guardar un nuevo empleado en la base de datos usando los datos
	// introducidos.

	private void guardarEmpleado() throws IncorrectDataException, EmployeeDataException {
		// Validar los datos proporcianados por el usuario en el JtextField
		if (txtFirstName.getText().isEmpty() || txtLastName.getText().isEmpty() || txtEmail.getText().isEmpty()
				|| txtPhone.getText().isEmpty() || txtJobTitle.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos.", "Campos incompletos",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		// Obtener los datos del usuario una vez validados
		String firstName = txtFirstName.getText().trim();
		String lastName = txtLastName.getText().trim();
		String email = txtEmail.getText().trim();
		String phone = txtPhone.getText().trim();
		String jobTitle = txtJobTitle.getText().trim();

		// Obtener el id del manager escogido entre todos los empleados
		OpcionComboDTO opcionElegida = (OpcionComboDTO) cmbManager.getSelectedItem();
		Integer managerId = null;

		if (opcionElegida != null) {
			managerId = opcionElegida.getId();
		}

		// Tenemos que guardar llamando al controlador y no al DAO
		try {
			EmployeeControllerEjercicio1 controller = new EmployeeControllerEjercicio1();
			controller.guardarEmpleado(firstName, lastName, email, phone, null, managerId, jobTitle); // El controlador
																										// se encarga de
																										// validación y
																										// DAO
			//Para que se muestre el Joption pane en esta ventana usamos: this
			JOptionPane.showMessageDialog(this, "Empleado guardado correctamente.", "Éxito",
					JOptionPane.INFORMATION_MESSAGE);
			limpiarCampos();
		} catch (IncorrectDataException ex) {
			JOptionPane.showMessageDialog(this, "Error de validación: " + ex.getMessage(), "Datos inválidos",
					JOptionPane.WARNING_MESSAGE);
			throw ex;
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "Error inesperado: " + ex.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace();
		}
	}

	// Limpia todos los campos del formulario y selecciona el primer manager.

	private void limpiarCampos() {
		txtFirstName.setText("");
		txtLastName.setText("");
		txtEmail.setText("");
		txtPhone.setText("");
		txtJobTitle.setText("");
	}
}
