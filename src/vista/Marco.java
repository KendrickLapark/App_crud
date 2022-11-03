package vista;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;

import controlador.CargaBD;
import modelo.BaseDatos;

public class Marco extends JFrame{
	
	private ArrayList<String> bdDisponibles;
	
	private int x = 600;
	
	private int y = 200;
	
	private int width = 1080;
	
	private int height = 720;

	public Marco() {
		
		setBounds(x, y, width, height);
		
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
