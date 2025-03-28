package miVista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.daw.Company_DB_App.DataBaseConector;

import dao.EmployeeDAOImpl;
import tablas.Employee;

import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Color;
import java.awt.Button;
import javax.swing.JTable;

public class MiVentana {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MiVentana window = new MiVentana();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MiVentana() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("MOSTRAR EMPLEADOS");
		btnNewButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            Connection conn = DataBaseConector.getConnection();
		            if (conn != null) {
		                EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
		                List<Employee> employees = employeeDAO.getAll(conn);
		                
		                // Ejemplo: Mostrar en consola (deberías usar JTable o similar)
		                for (Employee emp : employees) {
		                    System.out.println(emp);
		                }
		            } else {
		                JOptionPane.showMessageDialog(null, "Error: No se pudo conectar a la base de datos.");
		            }
		        } catch (Exception ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(null, "Error al obtener empleados: " + ex.getMessage());
		        }
		    }
		});

		btnNewButton.setBounds(107, 39, 210, 23);
		panel.add(btnNewButton);
		
		JLabel lblNewJgoodiesTitle = DefaultComponentFactory.getInstance().createTitle("Company App");
		lblNewJgoodiesTitle.setBackground(new Color(255, 255, 255));
		lblNewJgoodiesTitle.setBounds(174, 11, 73, 14);
		panel.add(lblNewJgoodiesTitle);
	}
}
