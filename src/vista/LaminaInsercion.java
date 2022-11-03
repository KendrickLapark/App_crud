package vista;

import java.awt.GridLayout;

import javax.swing.*;

public class LaminaInsercion extends JPanel{
	
	private JLabel jLabel;
	
	private JTextField jTextField;

	public LaminaInsercion(String [] nombreColumnas) {
		
		JPanel jPanel = new JPanel();
		
		jPanel.setLayout(new GridLayout(nombreColumnas.length, 2));
		
		for (int i = 0; i < nombreColumnas.length; i++) {
			
			jLabel = new JLabel(nombreColumnas[i]);
			
			jTextField = new JTextField(10);			
			
			jPanel.add(jLabel);
			
			jPanel.add(jTextField);						
			
		}
		
		add(jPanel);
		
	}
	
}
