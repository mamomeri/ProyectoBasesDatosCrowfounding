import Conexion;

import java.util.Scanner;

public class InterfazUsuario {
	private Conexion conectorInterfaz;
	public InterfazUsuario(Conexion conectorInterfaz){
		this.conectorInterfaz = conectorInterfaz;
	}
    public void MostrarInterfaz(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenido al sistema de Crowdfunding");
        System.out.println("-------------------------------------");
        System.out.println("Por favor, escriba su consulta:");
        
        String query = scanner.nextLine();
		conectorInterfaz.cargarControlador();
		conectorInterfaz.conectar();
		conectorInterfaz.ejecutarConsulta(query);
		conectorInterfaz.cerrarConexion();
       
    }
}
