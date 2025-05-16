package d_EjercicioFormativo2;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

import d_EjercicioFormativo1.OpcionComboDTO;

public class AltaAlmacenVista extends JFrame {

	// Combos para regiones, países y ubicaciones
	private JComboBox<OpcionComboDTO> cbRegiones;
	private JComboBox<OpcionPaisesComboDTO2> cbPaises;
	private JComboBox<OpcionComboDTO> cbUbicaciones;

	// Campo para escribir el nombre del almacén
	private JTextField txtNombreAlmacen;

	// Botón para crear el nuevo almacén
	private JButton btnCrear;

	// Controlador que se encarga de la lógica
	private AlmacenControllerEjercicio2 controller = new AlmacenControllerEjercicio2();

	public AltaAlmacenVista() {

		// Configuraciones finales de la ventana
		setTitle("Alta de Almacen");
		setSize(400, 300);
		setLocationRelativeTo(null); // para que aparezca centrada
		setVisible(true);
		setLayout(new GridLayout(5, 2, 10, 10));

		// Inicializo los componentes
		cbRegiones = new JComboBox<>();
		cbPaises = new JComboBox<>();
		cbUbicaciones = new JComboBox<>();
		txtNombreAlmacen = new JTextField();
		btnCrear = new JButton("Crear nuevo almacén");

		add(new JLabel("Regiones:"));
		add(cbRegiones);
		add(new JLabel("Países:"));
		add(cbPaises);
		add(new JLabel("Ubicaciones:"));
		add(cbUbicaciones);
		add(new JLabel("Nombre del almacén:"));
		add(txtNombreAlmacen);
		add(new JLabel()); // celda vacía para que el boton de crear este alineado con los comboBox
		add(btnCrear);

		// Al abrir la ventana, se cargan las regiones
		cargarRegiones();

		// Listeners para ir cargando países y ubicaciones según selección

		// Cuando el usuario haga algo (como elegir una región o hacer clic), ejecuta
		// este código

		/*
		 * Una clase anónima es como una clase rápida que creo ahí mismo, sin tener que
		 * escribirla aparte ni darle un nombre. Solo la uso para algo puntual, como
		 * decirle a un botón qué tiene que hacer cuando se pulsa. En vez de crear una
		 * clase aparte llamada, por ejemplo, ClaseParaListeners, lo escribo
		 * directamente dentro del addActionListener(). Ahí dentro, pongo new
		 * ActionListener(), que es una interfaz de Java, y sobrescribo su único método,
		 * actionPerformed(), donde ya puedo programar lo que quiero que pase cuando se
		 * hace clic.
		 */
	

		cbRegiones.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cargarPaises();
			}
		});
		
		cbRegiones.addActionListener(e -> cargarPaises());

		cbPaises.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cargarUbicaciones();
			}
		});

		// Cuando se pulsa el botón, se intenta crear el almacén

		btnCrear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				crearAlmacen();
			}
		});

		//btnCrear.addActionListener(e -> crearAlmacen()); Esta es la syntaxis lambda
		// es más sencilla, basicamente java dice “Toma el parámetro e (el ActionEvent)
		// y pásalo al método que quiero ejecutar” en este caso crearAlmacen().

		// Java permite usar lambdas cuando la interfaz solo tiene un método.
		// ActionListener cumple con eso, así que podemos escribirlo de forma más corta.
		// El código hace lo mismo, pero es más limpio y fácil de leer.

	}

	// Este método llama al controlador para cargar las regiones disponibles
	private void cargarRegiones() {
		try {
			List<OpcionComboDTO> regiones = controller.obtenerRegiones();
			cbRegiones.removeAllItems(); // limpiamos el combo por si acaso
			for (OpcionComboDTO region : regiones) {
				cbRegiones.addItem(region);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error al cargar regiones", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	// Al seleccionar una región, se cargan los países que pertenecen a esa región
	private void cargarPaises() {
		cbPaises.removeAllItems();
		OpcionComboDTO regionSeleccionada = (OpcionComboDTO) cbRegiones.getSelectedItem();
		if (regionSeleccionada != null) {
			try {
				List<OpcionPaisesComboDTO2> paises = controller.obtenerPaisesPorRegion(regionSeleccionada.getId());
				for (OpcionPaisesComboDTO2 pais : paises) {
					cbPaises.addItem(pais);
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Error al cargar países", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	// Al seleccionar un país, se cargan las ubicaciones disponibles en ese país
	private void cargarUbicaciones() {
		cbUbicaciones.removeAllItems();
		OpcionPaisesComboDTO2 paisSeleccionado = (OpcionPaisesComboDTO2) cbPaises.getSelectedItem();
		if (paisSeleccionado != null) {
			try {
				List<OpcionComboDTO> ubicaciones = controller.obtenerUbicacionesPorPais(paisSeleccionado.getId());
				for (OpcionComboDTO ubicacion : ubicaciones) {
					cbUbicaciones.addItem(ubicacion);
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Error al cargar ubicaciones", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	// Este método recoge los datos del formulario y crea el almacén si todo está
	// bien
	private void crearAlmacen() {
		String nombre = txtNombreAlmacen.getText().trim();
		OpcionComboDTO ubicacion = (OpcionComboDTO) cbUbicaciones.getSelectedItem();

		// Validamos que el usuario haya rellenado los datos
		if (nombre.isEmpty() || ubicacion == null) {
			JOptionPane.showMessageDialog(this, "Debes completar todos los campos.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		try {
			controller.insertarAlmacen(nombre, ubicacion.getId());
			JOptionPane.showMessageDialog(this, "Almacén creado correctamente.");
			dispose(); // Cierra la ventana al finalizar
		} catch (Exception e) {
			e.printStackTrace(); // Lo dejo para depurar si falla
			JOptionPane.showMessageDialog(this, "Error al insertar el almacén.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
