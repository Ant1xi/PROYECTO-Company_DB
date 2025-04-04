package miVista;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AltaEmpleadoVista extends JFrame {

	private JTextField txtFirstName, txtLastName, txtEmail, txtPhone, txtJobTitle;
	private JComboBox<String> cmbManager;
	private JButton btnGuardar;

	public AltaEmpleadoVista() {
		setTitle("Alta de Nuevo Empleado"); // Título de la ventana
		setSize(500, 300); // Establecer tamaño de la ventana
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Acción al cerrar
		setLocationRelativeTo(null); // Centrar la ventana en la pantalla
		setLayout(new GridLayout(7, 2)); // 7 Filas para los campos y el botón

		// Creear los componentes para que el usuario escriba
		txtFirstName = new JTextField();
		txtLastName = new JTextField();
		txtEmail = new JTextField();
		txtPhone = new JTextField();
		txtJobTitle = new JTextField();
		cmbManager = new JComboBox<>(); // Aquí después cargaremos los jefes
		btnGuardar = new JButton("Guardar");
		
        // Crear las etiquetas para los campos
        JLabel lblFirstName = new JLabel("Nombre:");
        JLabel lblLastName = new JLabel("Apellido:");
        JLabel lblEmail = new JLabel("Correo electrónico:");
        JLabel lblPhone = new JLabel("Teléfono:");
        JLabel lblJobTitle = new JLabel("Cargo:");
        JLabel lblManager = new JLabel("Jefe:");
        
        // Añadir las etiquetas y campos al layout
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
        add(btnGuardar);

        setVisible(true); // Mostrar la ventana
	}

}
