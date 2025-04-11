package miVista;

import javax.swing.*;

import Controladores.AlmacenControllerEjercicio2;
import Controladores.EmployeeControllerEjercicio1;
import Exceptions.CompanyException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class VentanaPrincipal extends JFrame {
	public VentanaPrincipal() {
		setTitle("Menú Principal");
		setSize(400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(5, 2, 10, 10)); // 5 filas, 2 columnas, separación

		// Botón para abrir la ventana de Alta de Empleado
		JButton btnAltaEmpleado = new JButton("Alta de Empleado");
		btnAltaEmpleado.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EmployeeControllerEjercicio1 ec = new EmployeeControllerEjercicio1();
				try {
					ec.cargaVistaAltaEmpleado(); // Abre la ventana de AltaEmpleadoVista
				} catch (SQLException | CompanyException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error al cargar alta de empleado", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		// Botón para abrir la ventana de Alta de Almacén
		JButton btnAltaAlmacen = new JButton("Alta de Almacén");
		btnAltaAlmacen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AlmacenControllerEjercicio2 ac = new AlmacenControllerEjercicio2();
				try {
					ac.cargaVistaAltaAlmacen();// Método correcto para abrir la vista de alta de almacén
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error al cargar alta de almacén", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		// Agregar los botones al menú principal
		add(btnAltaEmpleado);
		add(btnAltaAlmacen);

		// Mostrar la ventana
		setVisible(true);
	}

	public static void main(String[] args) {
		new VentanaPrincipal();
	}

}
