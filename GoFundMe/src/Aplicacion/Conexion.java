package Aplicacion;



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
	
	public void ejecutarConsulta(String consultaSQL, Boolean update) {
		Statement statement = null;
		try {
			statement = connection.createStatement();
                        
                        if (update){
                            int filasAfectadas = statement.executeUpdate(consultaSQL);
                             System.out.println("Filas afectadas: " + filasAfectadas);
                        } else {
                            ResultSet resultSet;
                            resultSet = statement.executeQuery(consultaSQL);
                            
                        
                        procesarResultados(resultSet);}
		} catch (SQLException e) {
			System.out.println("Error al ejecutar la consulta.");
			e.printStackTrace();
		}
	}
	
	public void procesarResultados(ResultSet resultSet) {
		try {
			while (resultSet.next()) {
                            int columnCount = resultSet.getMetaData().getColumnCount();

                for (int i = 1; i <= columnCount; i++) {
                    String columnName = resultSet.getMetaData().getColumnName(i);
                    Object value = resultSet.getObject(i);
                    System.out.println(columnName +": "+ value);
                }
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
