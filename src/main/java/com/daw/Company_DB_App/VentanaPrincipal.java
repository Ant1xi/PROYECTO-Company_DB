package com.daw.Company_DB_App;

import javax.swing.*;

import Exceptions.CompanyException;
import d_EjercicioFormativo1.EmployeeControllerEjercicio1;
import d_EjercicioFormativo2.AlmacenControllerEjercicio2;
import d_EjercicioFormativo3.ModificarCustomerControllerEjercicio3;
import d_EjercicioFormativo4.BuscarClienteYDetallesPedidoControllerEjercicio4;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class VentanaPrincipal extends JFrame {
	public VentanaPrincipal() {
		setTitle("Menú Principal");
		setSize(400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(6, 2, 10, 10)); // 6 filas, 2 columnas, separación

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
					ac.cargaVistaAltaAlmacen(); // Método correcto para abrir la vista de alta de almacén
				} catch (Exception e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error al cargar alta de almacén", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JButton btnModificarCliente = new JButton("Modificar datos de un cliente");
		btnModificarCliente.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ModificarCustomerControllerEjercicio3 c3 = new ModificarCustomerControllerEjercicio3();
				c3.cargarVistaModificarCustomers();
			}
		});

		JButton btnBuscarPedidosCliente = new JButton("Buscar pedidos de un cliente y vista detalle del pedido");
		btnBuscarPedidosCliente.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				BuscarClienteYDetallesPedidoControllerEjercicio4 c4 = new BuscarClienteYDetallesPedidoControllerEjercicio4();
				c4.cargarBuscarPedidosClienteVista();
			}
		});

		JButton btnEliminarEmpleado = new JButton("Eliminar empleado");
		btnEliminarEmpleado.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		JButton btnTraspasoYCierreAlmacen = new JButton("Traspaso y cierre de un almacén");
		btnTraspasoYCierreAlmacen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		// Agregar los botones al menú principal
		add(btnAltaEmpleado);
		add(btnAltaAlmacen);
		add(btnModificarCliente);
		add(btnBuscarPedidosCliente);
		add(btnEliminarEmpleado);
		add(btnTraspasoYCierreAlmacen);

		// Mostrar la ventana
		setVisible(true);
	}

	public static void main(String[] args) {
		new VentanaPrincipal();
	}

}
