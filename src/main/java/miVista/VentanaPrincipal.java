package miVista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                new AltaEmpleadoVista(); // Abre la ventana de AltaEmpleadoVista
            }
        });

        // Agregar los botones al menú principal
        add(btnAltaEmpleado);
        // Aquí puedes agregar más botones para las otras funcionalidades...

        setVisible(true);
    }

    public static void main(String[] args) {
        new VentanaPrincipal();
    }
}

