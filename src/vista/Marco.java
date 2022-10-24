package vista;

import javax.swing.JFrame;

public class Marco extends JFrame{

	public Marco() {
		
		setBounds(600, 200, 700, 700);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setTitle("App Crud");
		
		add(new Lamina());
		
		setVisible(true);
		
	}
	
}
