package vista;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;

import controlador.CargaBD;
import modelo.BaseDatos;

public class Marco extends JFrame{
	
	private ArrayList<String> bdDisponibles;

	public Marco() {
		
		setBounds(600, 200, 700, 700);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setTitle("App Crud");
		
		Lamina lamina = new Lamina();
		
		add(lamina);
		
		BaseDatos baseDatos = lamina.getBaseDatos();

		try {
			
			bdDisponibles = baseDatos.baseDatos_disponibles();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		addWindowListener(new CargaBD(baseDatos, bdDisponibles, lamina.getjComboBox()));
		
		setVisible(true);
		
	}
	
}
