package vista;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import modelo.BaseDatos;

public class Lamina extends JPanel{
	
	private String [] campos;
	
	private String nombreBaseDatos;
	
	private BaseDatos baseDatos;
	
	private Object [][] objetos;
	
	private JTable jTable;
	
	private JPanel jPanelCenter;
	
	private JScrollPane jScrollTabla;
	
	private JTextField jTextField2;
	
	private JComboBox jComboBox, jComboBox2;
	
	private ArrayList <String> tablas;
	
	public Lamina() {	
		
		
		this.baseDatos = new BaseDatos();
	
		setLayout(new BorderLayout());
		
		JPanel jPanelTop = new JPanel();
		
		JLabel jLabel = new JLabel("Base de datos: ");
		
		jComboBox = new JComboBox();		
		
		JButton jButton = new JButton("Aceptar");
		
		jButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				String textoBusqueda = jComboBox.getSelectedItem().toString();
				
				try {				
					
					tablas = baseDatos.obtenerTablas(textoBusqueda);
					
					nombreBaseDatos = textoBusqueda;
					
					rellenaComboBox(tablas);
										
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		jPanelTop.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		jPanelTop.add(jLabel);
		
		jPanelTop.add(jComboBox);
		
		jPanelTop.add(jButton);
					
		add(jPanelTop, BorderLayout.NORTH);
		
		jPanelCenter = new JPanel();
		
		jTextField2 = new JTextField("Campos");
		
		jTextField2.setFont(new Font(jTextField2.getFont().getName(),Font.BOLD,jTextField2.getFont().getSize()));
		
		jTextField2.setHorizontalAlignment(jTextField2.CENTER);
		
		jTextField2.setFocusable(false);
		
		JTextField jTextField3 = new JTextField("Registrar");
		
		jComboBox2 = new JComboBox();
		
		jComboBox2.addItem("Tablas");		
		
		jComboBox2.setAlignmentY(TOP_ALIGNMENT);
		
		JPanel jPanelComboBox = new JPanel();
		
		jPanelComboBox.add(jComboBox2);
		
		jTable = new JTable();
		
		jScrollTabla = new JScrollPane();
		
		jTextField3.setFocusable(false);
						
		jPanelCenter.setLayout(new BorderLayout());
		
		jPanelCenter.setBackground(Color.WHITE);	
		
		jPanelCenter.add(jTextField2, BorderLayout.NORTH);
		
		jPanelCenter.add(jPanelComboBox, BorderLayout.WEST);
		
		jPanelCenter.add(jScrollTabla, BorderLayout.CENTER);

		add(jPanelCenter, BorderLayout.CENTER);
		
		JPanel jPanelBot = new JPanel();
		
		JButton jButton2 = new JButton("Buscar");
		
		jButton2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				try {
					
					crearVista(jComboBox2.getSelectedItem().toString(), nombreBaseDatos);
					
					jTextField2.setText(jComboBox2.getSelectedItem().toString());
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		jPanelBot.add(jButton2);
		
		add(jPanelBot, BorderLayout.SOUTH);			
	
		setVisible(true);				
		
	}
	
	public void conexion(String nombreDB) {
		
		this.baseDatos = new BaseDatos();
		
		baseDatos.conecta(nombreDB);
		
	}
	
	public void rellenaComboBox(ArrayList<String> tablas) {
		
		jComboBox2.removeAllItems();

		for (String tabla : tablas) {
			
			jComboBox2.addItem(tabla);
		
		}
		
	}
	
	public void crearVista(String tabla, String nombreDB) throws SQLException {
				
		campos = baseDatos.nombreColumnas(tabla, nombreDB);
		
		objetos = baseDatos.arrayConsulta(tabla, nombreDB);		

		TableModel tableModel = new DefaultTableModel(objetos, campos);
		
		jTable.setModel(tableModel);
		
		jScrollTabla.getViewport().add(jTable);				
		
	}		
	
	public BaseDatos getBaseDatos() {
		return baseDatos;
	}
	
	public JComboBox getjComboBox() {
		return jComboBox;
	}

}
