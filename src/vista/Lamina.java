package vista;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;

import modelo.BaseDatos;

public class Lamina extends JPanel{
	
	private String [] campos;
	
	private BaseDatos baseDatos;
	
	private Object [][] objetos;
	
	private JTable jTable;
	
	private JComboBox jComboBox;
	
	private ArrayList <String> tablas;
	
	public Lamina() {
		
		conexion();
		
		try {
			
			 campos = baseDatos.nombreColumnas();
						
			 objetos = baseDatos.arrayConsulta();				 
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setLayout(new BorderLayout());
		
		JPanel jPanelTop = new JPanel();
		
		JTextField jTextField = new JTextField("Introduce base de datos", 10);	
		
		jTextField.setSize(120, 20);
		
		JButton jButton = new JButton("Aceptar");
		
		jButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				String textoBusqueda = jTextField.getText();
				
				try {				
					
					tablas = baseDatos.obtenerTablas(textoBusqueda);
					
					rellenaComboBox(tablas);
										
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		jPanelTop.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		jPanelTop.add(jTextField);
		
		jPanelTop.add(jButton);
		
		add(jPanelTop, BorderLayout.NORTH);
		
		JPanel jPanelCenter = new JPanel();
		
		JTextField jTextField2 = new JTextField("Campos");
		
		jTextField2.setHorizontalAlignment(jTextField2.CENTER);
		
		jTextField2.setFocusable(false);
		
		JTextField jTextField3 = new JTextField("Registrar");
		
		jComboBox = new JComboBox();
		
		jComboBox.addItem("Tablas");		
		
		jComboBox.setAlignmentY(TOP_ALIGNMENT);
		
		JPanel jPanelComboBox = new JPanel();
		
		jPanelComboBox.add(jComboBox);
		
		jTable = new JTable(objetos, campos);		
		
		jTextField3.setFocusable(false);
						
		jPanelCenter.setLayout(new BorderLayout());
		
		jPanelCenter.setBackground(Color.WHITE);	
		
		jPanelCenter.add(jTextField2, BorderLayout.NORTH);
		
		jPanelCenter.add(jPanelComboBox, BorderLayout.WEST);
		
		jPanelCenter.add(new JScrollPane(jTable), BorderLayout.CENTER);

		add(jPanelCenter, BorderLayout.CENTER);
		
		JPanel jPanelBot = new JPanel();
		
		JButton jButton2 = new JButton("Buscar");
		
		jButton2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				
				
			}
		});
		
		jPanelBot.add(jButton2);
		
		add(jPanelBot, BorderLayout.SOUTH);	
	
		setVisible(true);				
		
	}
	
	public void conexion() {
		
		this.baseDatos = new BaseDatos();
		
		baseDatos.conecta();
		
	}
	
	public void rellenaComboBox(ArrayList<String> tablas) {
		
		jComboBox.removeAllItems();

		for (String tabla : tablas) {
			
			jComboBox.addItem(tabla);
		
		}
		
	}
	
	public void crearVista() {
		
		
		
		
	}

}
