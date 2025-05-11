package miVista;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

import Controladores.AlmacenControllerEjercicio2;
import dto.OpcionComboDTO;
import dto.OpcionPaisesComboDTO2;

public class AltaAlmacenVista extends JFrame {

    private JComboBox<OpcionComboDTO> cbRegiones;
    private JComboBox<OpcionPaisesComboDTO2> cbPaises;
    private JComboBox<OpcionComboDTO> cbUbicaciones;
    private JTextField txtNombreAlmacen;
    private JButton btnCrear;

    private AlmacenControllerEjercicio2 controller;

    public AltaAlmacenVista() {
        super("Alta de Almacén");

        controller = new AlmacenControllerEjercicio2();

        cbRegiones = new JComboBox<>();
        cbPaises = new JComboBox<>();
        cbUbicaciones = new JComboBox<>();
        txtNombreAlmacen = new JTextField();
        btnCrear = new JButton("Crear nuevo almacén");

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.add(new JLabel("Regiones:"));
        panel.add(cbRegiones);
        panel.add(new JLabel("Países:"));
        panel.add(cbPaises);
        panel.add(new JLabel("Ubicaciones:"));
        panel.add(cbUbicaciones);
        panel.add(new JLabel("Nombre del almacén:"));
        panel.add(txtNombreAlmacen);
        panel.add(new JLabel());
        panel.add(btnCrear);

        add(panel, BorderLayout.CENTER);

        cargarRegiones();

        cbRegiones.addActionListener(e -> cargarPaises());
        cbPaises.addActionListener(e -> cargarUbicaciones());

        btnCrear.addActionListener(e -> crearAlmacen());

        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void cargarRegiones() {
        try {
            List<OpcionComboDTO> regiones = controller.obtenerRegiones();
            cbRegiones.removeAllItems();
            for (OpcionComboDTO region : regiones) {
                cbRegiones.addItem(region);
            }
        } catch (Exception e) {
            mostrarError("Error al cargar regiones");
        }
    }

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
                mostrarError("Error al cargar países");
            }
        }
    }

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
                mostrarError("Error al cargar ubicaciones");
            }
        }
    }

    private void crearAlmacen() {
        String nombre = txtNombreAlmacen.getText().trim();
        OpcionComboDTO ubicacion = (OpcionComboDTO) cbUbicaciones.getSelectedItem();

        if (nombre.isEmpty() || ubicacion == null) {
            mostrarError("Debes completar todos los campos.");
            return;
        }

        try {
            controller.insertarAlmacen(nombre, ubicacion.getId());
            JOptionPane.showMessageDialog(this, "Almacén creado correctamente.");
            dispose(); // cierra la ventana
        } catch (Exception e) {
            e.printStackTrace();
            mostrarError("Error al insertar el almacén.");
        }
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
