package vista;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.*;

import modelo.BaseDatos;

public class Lamina extends JPanel{

	private String [] campos;
	
	private String nombreBaseDatos;
	
	private BaseDatos baseDatos;
	
	private Object [][] objetos;
	
	private JLabel jLabel2;
	
	private JTable jTable;
	
	private JPanel jPanelCenter;
	
	private JScrollPane jScrollTabla, jScrollRegistro;
	
	private JTextField jTextField1, jTextField2;
	
	private JTextArea jTextArea;
	
	private JToolBar jToolBar;
	
	private JComboBox <String> jComboBox1, jComboBox2;
	
	private ArrayList <String> tablas;
	
	private TableModel tableModel;
	
	public Lamina() {	
				
		this.baseDatos = new BaseDatos();
	
		setLayout(new BorderLayout());
		
		JPanel jPanelTop = new JPanel();	
		
		JPanel jPanelRight = new JPanel();
		
		JPanel jPanelLeft = new JPanel();
		
		JPanel jPanelRTop = new JPanel();
		
		JPanel jPanelRBot = new JPanel();		
		
		JPanel jPanelLCenter = new JPanel();	
		
		jPanelCenter = new JPanel();
		
		JPanel jPanelBot = new JPanel();
		
		JLabel jLabel = new JLabel("Base de datos: ");
		
		JLabel jLabel3 = new JLabel("Atributos");
		
		jLabel2 = new JLabel("Columna");	
		
		JButton jButton1 = new JButton("Aceptar");
		
		JButton jButton2 = new JButton("Buscar");
		
		JButton jButton3 = new JButton("Aceptar");
		
		JButton jButton5 = new JButton(new ImageIcon("src/multimedia/add.png"));
		
		JButton jButton6 = new JButton(new ImageIcon("src/multimedia/remove.png"));	
		
		JButton jButton7 = new JButton(new ImageIcon("src/multimedia/update.png"));	
		
		jTextField1 = new JTextField("Campos");		
		
		jTextField2 = new JTextField(10);
		
		jTextArea = new JTextArea(5,50);
		
		jComboBox1 = new JComboBox<String>();	
		
		jComboBox2 = new JComboBox<String>();
		
		jToolBar = new JToolBar();
		
		jTable = new JTable();
		
		jScrollTabla = new JScrollPane();	
		
		jScrollRegistro = new JScrollPane();					
		
		jButton1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				String textoBusqueda = jComboBox1.getSelectedItem().toString();
				
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
		
		jButton2.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						
						try {
							
							crearVista(jComboBox2.getSelectedItem().toString(), nombreBaseDatos);
							
							jTextField1.setText(jComboBox2.getSelectedItem().toString());
														
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}
				});
		
		
		jButton5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub		
				
				String [] nombreColumnas = new String [jTable.getColumnCount()];
				
				for (int i = 0; i < jTable.getColumnCount(); i++) {
					
					nombreColumnas[i] = jTable.getColumnName(i);
					
				}		
				
				jButton3.setEnabled(true);
				
				jScrollRegistro.getViewport().add(new LaminaInsercion(nombreColumnas));
				
				
		
			}
		});
		
		jComboBox1.setPreferredSize(new Dimension(150, 20));
		
		jComboBox2.setPreferredSize(new Dimension(150, 20));	
		
		jTextField1.setFont(new Font(jTextField1.getFont().getName(),Font.BOLD,jTextField1.getFont().getSize()));
		
		jTextField1.setHorizontalAlignment(0);
		
		jTextField1.setFocusable(false);
		
		jTextField2.setFocusable(false);	
		
		jButton3.setEnabled(false);
		
		jComboBox2.addItem("Tablas");		
		
		jComboBox2.setAlignmentY(TOP_ALIGNMENT);
		
		jToolBar.add(jButton5);
		
		jToolBar.add(jButton6);		
		
		jToolBar.add(jButton7);	
		
		jToolBar.setOrientation(0);
		
		jPanelRTop.setPreferredSize(new Dimension(250, 20));

		jPanelTop.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		jPanelTop.add(jLabel);
		
		jPanelTop.add(jComboBox1);
		
		jPanelTop.add(jButton1);
		
		jPanelTop.add(jToolBar);
		
		jPanelRTop.add(jLabel3);						
		
		jPanelRBot.add(jButton3);			
		
		jPanelRight.setLayout(new BorderLayout());	
		
		jPanelRight.add(jPanelRTop, BorderLayout.NORTH);
		
		jPanelRight.add(jScrollRegistro, BorderLayout.CENTER);
		
		jPanelRight.add(jPanelRBot, BorderLayout.SOUTH);
		
		jPanelLeft.setPreferredSize(new Dimension(150, 200));
		
		jPanelLeft.setLayout(new BorderLayout());
		
		jPanelLeft.add(jComboBox2, BorderLayout.NORTH);
		
		jPanelLCenter.add(jButton2);
		
		jPanelLeft.add(jPanelLCenter, BorderLayout.CENTER);
		
		jPanelCenter.setLayout(new BorderLayout());
		
		jPanelCenter.setBackground(Color.WHITE);
		
		jPanelCenter.add(jTextField1, BorderLayout.NORTH);	
		
		jPanelCenter.add(jScrollTabla, BorderLayout.CENTER);
		
		jPanelBot.add(jTextArea);		
				
		add(jPanelRight, BorderLayout.EAST);
							
		add(jPanelTop, BorderLayout.NORTH);

		add(jPanelCenter, BorderLayout.CENTER);
		
		add(jPanelBot, BorderLayout.SOUTH);	
		
		add(jPanelLeft, BorderLayout.WEST);
		
		setVisible(true);					
		
	}
	
	/*
	 * Establece la conexión a la base de datos seleccionada.
	 */
	
	public void conexion(String nombreDB) {
		
		this.baseDatos = new BaseDatos();
		
		baseDatos.conecta(nombreDB);
		
	}
	
	/*
	 * Actualiza el JComboBox del JPanel de la izquierda para que muestre las tablas disponibles en la base de datos indicada.
	 */
	
	public void rellenaComboBox(ArrayList<String> tablas) {
		
		jComboBox2.removeAllItems();

		for (String tabla : tablas) {
			
			jComboBox2.addItem(tabla);
		
		}
		
	}
	
	/*
	 * Crea la vista de la tabla en el jPaneCenter y añade evento a la tabla
	 */
	
	public void crearVista(String tabla, String nombreDB) throws SQLException {
				
		campos = baseDatos.nombreColumnas(tabla, nombreDB);
		
		objetos = baseDatos.arrayConsulta(tabla, nombreDB);		

		tableModel = new DefaultTableModel(objetos, campos);
		
		jTable.setModel(tableModel);
		
		jTable.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				
				 int row = jTable.rowAtPoint(e.getPoint());
				 int col = jTable.columnAtPoint(e.getPoint());
				 
				 Object valor = jTable.getValueAt(row, col);
				 
				 jLabel2.setText(tableModel.getColumnName(col));

				 if(valor!=null) {
					 jTextField2.setText(jTable.getValueAt(row, col).toString());
				 }	
			}
			
		});		
		
		jScrollTabla.getViewport().add(jTable);				
		
	}		
	
	public BaseDatos getBaseDatos() {
		return baseDatos;
	}
	
	public JComboBox <String> getjComboBox() {
		return jComboBox1;
	}

}
