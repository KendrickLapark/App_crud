package modelo;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

public class BaseDatos {
	
	private Connection connection;
	
	private Statement statement;
	
	public void conecta(String bDCadena) {
		
		try {			
			
			BufferedReader bufferedReader = new BufferedReader(new FileReader("C:/Users/pexpo/desktop/bdapp.txt"));
				
			String[] ruta = getRoot(bufferedReader);
			
			connection = DriverManager.getConnection(ruta[0]+bDCadena, ruta[1], ruta[2]);
			
			statement = connection.createStatement();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/*
	 * Obtiene la url de la base de datos de un archivo ".txt"
	 */
	
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
	 * Devuelve el número de filas que tiene la tabla a consultar
	 */
	
	public int cuentaFilas(String tabla) throws SQLException {
				
		int numeroFilas = 0;
		
		String filas = "SELECT COUNT(*) FROM "+tabla+";";
		
		ResultSet resultSet = statement.executeQuery(filas);

		if(resultSet.next()) {
			numeroFilas = resultSet.getInt(1);				
			
		}
		
		return numeroFilas;
		
	}
	
	/*
	 * Devuelve el número de campos que tiene la tabla a consultar
	 */
	
	public int cuentaColumnas(String tabla) throws SQLException {
		
		int numeroColumnas = 0;		
		
		String consulta = "SELECT * FROM "+tabla+";";
		
		ResultSet resultSet2 = statement.executeQuery(consulta);
		
		ResultSetMetaData resultSetMetaData = resultSet2.getMetaData();
		
		numeroColumnas = resultSetMetaData.getColumnCount();
		
		return numeroColumnas;
		
	}
	
	/*
	 * Devuelve el nombre de las columnas de la tabla;
	 */
	
	public String[] nombreColumnas(String tabla, String nombreBD) throws SQLException {
		
		conecta(nombreBD);	
		
		String consulta = "SELECT * FROM "+tabla+";";
		
		ResultSet resultSet = statement.executeQuery(consulta);
		
		ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
		
		String [] nombreColumnas = new String[resultSetMetaData.getColumnCount()];
		
		ResultSet resultSet3 = statement.executeQuery(consulta);
		
		ResultSetMetaData resultSetMetaData2 = resultSet3.getMetaData();
		
		for (int i = 1; i < nombreColumnas.length+1; i++) {
			nombreColumnas[i-1]=resultSetMetaData2.getColumnLabel(i);			
			
		}		
		
		return nombreColumnas;
	}
	
	/*
	 * Devuelve una matriz de objetos para crear la table ; return Object[][]
	 */
	
	public Object[][] arrayConsulta(String tabla, String nombreDB) throws SQLException {
				
		System.out.println(" ");
		
		int nFilas = cuentaFilas(tabla);
		
		int nColumnas = cuentaColumnas(tabla);
	
		Object [][] objetos = new Object[nFilas][nColumnas];
		
		String todo = "SELECT * FROM "+tabla+";";
		
		ResultSet resultSet = statement.executeQuery(todo);
		
		String [] nombreColumnas = nombreColumnas(tabla, nombreDB);
		
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
		
		conecta(nombreBD);	
		
		baseDatos_disponibles();
		
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
	
	public ArrayList<String> baseDatos_disponibles() throws SQLException {
		
		conecta("");
		
		ResultSet resultSet = connection.getMetaData().getCatalogs();
		
		ArrayList <String> bases_disponibles = new ArrayList<>();
		
		while(resultSet.next()) {
			
			if(!resultSet.getString("TABLE_CAT").equals("mysql") &&
					!resultSet.getString("TABLE_CAT").equals("performance_schema") &&
					!resultSet.getString("TABLE_CAT").equals("information_schema") &&
					!resultSet.getString("TABLE_CAT").equals("sys")){
				bases_disponibles.add(resultSet.getString("TABLE_CAT"));
			}			
			
		}
		
		return bases_disponibles;
		
	}

}
