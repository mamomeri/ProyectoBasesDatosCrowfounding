

import java.sql.*;

public class Conexion {
	
	private String url;
	private String usuario;
	private String contrasena;
	private Connection connection;
	
	public Conexion(String url,String usuario,String contrasena){
		this.url = url;
		this.usuario = usuario;
		this.contrasena = contrasena;
	}
	
	public void cargarControlador() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Error al cargar el controlador JDBC.");
			e.printStackTrace();
		}
	}
	
	public void conectar() {
		try {
			connection = DriverManager.getConnection(url, usuario, contrasena);
		} catch (SQLException e) {
			System.out.println("Error al conectar a la base de datos.");
			e.printStackTrace();
		}
	}
	
	public void ejecutarConsulta(String consultaSQL) {
		Statement statement = null;
		try {
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(consultaSQL);
			procesarResultados(resultSet);
		} catch (SQLException e) {
			System.out.println("Error al ejecutar la consulta.");
			e.printStackTrace();
		}
	}
	
	public void procesarResultados(ResultSet resultSet) {
		try {
			while (resultSet.next()) {
				int id = resultSet.getInt("id_player");
				String nombre = resultSet.getString("first_name");
				System.out.println("ID: " + id + ", Nombre: " + nombre);
			}
		} catch (SQLException e) {
			System.out.println("Error al procesar los resultados.");
			e.printStackTrace();
		}
	}
	
	public void cerrarConexion() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("Error al cerrar la conexión.");
				e.printStackTrace();
			}
		}
	}
}
