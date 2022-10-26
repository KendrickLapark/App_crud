package controlador;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JComboBox;

import modelo.BaseDatos;

public class CargaBD extends WindowAdapter{
	
	private BaseDatos baseDatos;
	
	private ArrayList <String> bdDisponibles;
	
	private JComboBox jComboBox;
	
	public CargaBD(BaseDatos baseDatos, ArrayList <String> bdDisponibles, JComboBox jComboBox) {
		
		this.baseDatos = baseDatos;
		
		this.bdDisponibles = bdDisponibles;
		
		this.jComboBox = jComboBox;
		
	}

	
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		super.windowOpened(e);
		
		for (String bd : bdDisponibles) {
			jComboBox.addItem(bd);
		}
		
	}
	
}
