package modelo;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

public class BaseDatos {
	
	private Connection connection;
	
	public void conecta() {
		
		try {			
			
			BufferedReader bufferedReader = new BufferedReader(new FileReader("C:/Users/pexpo/desktop/bdapp.txt"));
				
			String[] ruta = getRoot(bufferedReader);
			
			connection = DriverManager.getConnection(ruta[0], ruta[1], ruta[2]);
			
			consultaFilas();
			
			String [] nombreColumnas = nombreColumnas();
			
			for (String string : nombreColumnas) {
				System.out.print(string+" // ");
			}
			
			arrayConsulta();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String [] getRoot(BufferedReader bufferedReader) {
		
		String [] ruta = new String[3];
		
		String linea;
		
		int contador = 0;
		
		try {
			
			while((linea = bufferedReader.readLine()) != null) {
				ruta[contador]= linea;
				
				contador++;
			}						
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return ruta;
		
	}
	
	/*
	 * Este método obtiene el número de filas y de columnas de una tabla y el
	 * nombre de las columnas;
	 */
	
	public void consultaFilas() throws SQLException {	
					
			Statement statement = connection.createStatement();
			
			String filas = "SELECT COUNT(*) FROM USUARIOS;";
			
			String todo = "SELECT * FROM USUARIOS;";
			
			ResultSet resultSet = statement.executeQuery(filas);

			if(resultSet.next()) {
				int numeroFilas = resultSet.getInt(1);	
				
				System.out.println("El número de filas es: "+numeroFilas);
				
			}	

			ResultSet resultSet2 = statement.executeQuery(todo);
			
			ResultSetMetaData resultSetMetaData = resultSet2.getMetaData();
			
			int numeroColumnas = resultSetMetaData.getColumnCount();
			
			System.out.println("El número de columnas es: "+numeroColumnas);
			
			ResultSet resultSet3 = statement.executeQuery(todo);
			
			ResultSetMetaData resultSetMetaData2 = resultSet3.getMetaData();		
			
			String nombreColumnas = "";
			
			for (int i = 1; i < numeroColumnas; i++) {
				nombreColumnas=resultSetMetaData2.getColumnLabel(i);
				
				//System.out.println(nombreColumnas);
			}	
			
		
	}
	
	public int cuentaFilas() throws SQLException {
				
		int numeroFilas = 0;
		
		Statement statement = connection.createStatement();
		
		String filas = "SELECT COUNT(*) FROM USUARIOS;";
		
		ResultSet resultSet = statement.executeQuery(filas);

		if(resultSet.next()) {
			numeroFilas = resultSet.getInt(1);				
			
		}
		
		return numeroFilas;
		
	}
	
	public int cuentaColumnas() throws SQLException {
		
		int numeroColumnas = 0;
		
		String todo = "SELECT * FROM USUARIOS;";
		
		Statement statement = connection.createStatement();
		
		ResultSet resultSet2 = statement.executeQuery(todo);
		
		ResultSetMetaData resultSetMetaData = resultSet2.getMetaData();
		
		numeroColumnas = resultSetMetaData.getColumnCount();
		
		return numeroColumnas;
		
	}
	
	/*
	 * Devuelve el nombre de las columnas de la tabla;
	 */
	
	public String[] nombreColumnas() throws SQLException {
		
		Statement statement = connection.createStatement();
		
		String todo = "SELECT * FROM USUARIOS;";
		
		ResultSet resultSet = statement.executeQuery(todo);
		
		ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
		
		String [] nombreColumnas = new String[resultSetMetaData.getColumnCount()];
		
		ResultSet resultSet3 = statement.executeQuery(todo);
		
		ResultSetMetaData resultSetMetaData2 = resultSet3.getMetaData();
		
		for (int i = 1; i < nombreColumnas.length+1; i++) {
			nombreColumnas[i-1]=resultSetMetaData2.getColumnLabel(i);			
			
		}		
		
		return nombreColumnas;
	}
	
	/*
	 * Devuelve una matriz de objetos para crear la table ; return Object[][]
	 */
	
	public Object[][] arrayConsulta() throws SQLException {
		
		System.out.println(" ");
		
		int nFilas = cuentaFilas();
		
		int nColumnas = cuentaColumnas();
	
		Object [][] objetos = new Object[nFilas][nColumnas];
		
		Statement statement = connection.createStatement();
		
		String todo = "SELECT * FROM USUARIOS;";
		
		ResultSet resultSet = statement.executeQuery(todo);
		
		String [] nombreColumnas = nombreColumnas();
		
		int contador = 0;			
		
		if(resultSet.next()) {		
			
				do{			
			
					for (int j = 0; j < nombreColumnas.length; j++) {
						
						objetos[contador][j] = resultSet.getObject(nombreColumnas[j]);
											
					}
										
					contador++;
					
				}while(resultSet.next()); 
			
			}
		
				for (int i = 0; i < objetos.length; i++) {
					
					for (int j = 0; j < objetos[i].length; j++) {
						System.out.print(objetos[i][j]+" / ");
					}
					
					System.out.println("");
					
				}

				return objetos;
				
		}
	
	/*
	 * Método que devuelve un ArrayList con los nombres de las tablas de una determinada Base de datosM
	 */
	
	public ArrayList <String> obtenerTablas(String nombreBD) throws SQLException {
		
		ArrayList <String> nombreTablas = new ArrayList<>();
		
		Statement statement = connection.createStatement();
		
		String moldeConsulta = "show tables from ";
		
		String showTablas = moldeConsulta+nombreBD+";";		
		
		ResultSet resultSet = statement.executeQuery(showTablas);		
		
		if(resultSet.next()) {
			
			do {
			
				nombreTablas.add(resultSet.getString(1));				
				
			}while(resultSet.next());
			
		}
		
		for (String tabla : nombreTablas) {
			System.out.println("Nombre tabla: "+tabla);
		}
		
		return nombreTablas;
		
	}

}
