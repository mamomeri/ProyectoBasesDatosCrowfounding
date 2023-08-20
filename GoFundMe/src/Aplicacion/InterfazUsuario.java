package Aplicacion;

import Aplicacion.Conexion;
import Aplicacion.InputRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import Aplicacion.Modelo.*;
public class InterfazUsuario {
	private Conexion conectorInterfaz;
        
      
        List<InputRequest<?>> requests;
        List<Object> listaResultados;
        
	public InterfazUsuario(Conexion conectorInterfaz){
		this.conectorInterfaz = conectorInterfaz;
                
                requests = new ArrayList<>();
                listaResultados  = new ArrayList<>();
	}
        public InterfazUsuario(){   
                requests = new ArrayList<>();
                listaResultados  = new ArrayList<>();
	}
        
        public <T> void pedirParametro(Class<T> type, String message){
            requests.add(new InputRequest<>(type, message));       
        }
        
        public List<Object> pedirParametrosConsola(){
            this.listaResultados.clear();
            for (InputRequest<?> request : this.requests) {
                listaResultados.add(request.requestInput());
            }  
            this.requests.clear();
            return this.listaResultados;
        
        }
        
        
        public void MostrarInterfazBasura(){
            Scanner scanner = new Scanner(System.in);

            System.out.println("Bienvenido al sistema de Crowdfunding");
            System.out.println("-------------------------------------");
            System.out.println("Por favor, escriba su consulta:");

            String query = scanner.nextLine();
                    conectorInterfaz.cargarControlador();
                    conectorInterfaz.conectar();
                    conectorInterfaz.ejecutarConsulta(query,false);
                    conectorInterfaz.cerrarConexion();

        }
        public void MostrarInterfaz(){
            Scanner scanner = new Scanner(System.in);
            IConsultable tablaTrabajo;
            System.out.println("Bienvenido al sistema de Crowdfunding");
            System.out.println("-------------------------------------");
            System.out.println("Seleccione la tabla con la que desea trabajar:");
            System.out.println("1. Usuario");
            System.out.println("2. Tipo Usuario");
            System.out.println("3. Comentario");
            System.out.println("4. Campaña");
            System.out.println("5. Ubicación_Campaña");
            System.out.println("6. Asesor");
            System.out.println("7. Asesor_Campaña");
            System.out.println("8. Estado_Legal");
            System.out.println("9. Donación_Pago");
            System.out.println("-------------------------------------");

            int opcion = scanner.nextInt();
            scanner.nextLine();  // Consumir el siguiente '\n'

            String tabla = "";

            switch (opcion) {
                case 1:
                    tabla = "Usuario";
                    tablaTrabajo = new Usuario();
                    break;
                case 2:
                    tabla = "Tipo_Usuario";
                    tablaTrabajo = new Tipo_Usuario();
                    break;
                case 3:
                    tabla = "Comentario";
                    tablaTrabajo = new Comentario();
                    break;
                case 4:
                    tabla = "Campaña";
                    tablaTrabajo = new Campaña();
                    break;
                case 5:
                    tabla = "Ubicación_Campaña";
                    tablaTrabajo = new Ubicación_Campaña();
                    break;
                case 6:
                    tabla = "Asesor";
                    tablaTrabajo = new Asesor();
                    break;
                case 7:
                    tabla = "Asesor_Campaña";
                    tablaTrabajo = new Asesor_Campaña();
                    break;
                case 8:
                    tabla = "Estado_Legal";
                    tablaTrabajo = new Estado_Legal();
                    break;
                case 9:
                    tabla = "Donación_Pago";
                    tablaTrabajo = new Donación_Pago();
                    break;
                default:
                    System.out.println("Opción no válida.");
                    return;
            }

            System.out.println("Has seleccionado la tabla: " + tabla);

            System.out.println("Ahora selecciona lo que deseas hacer con esta tabla:");
            System.out.println("-------------------------------------");
            System.out.println("Seleccione la tabla con la que desea trabajar:");
            System.out.println("1. Añadir");
            System.out.println("2. Consultar");
            System.out.println("3. Eliminar");
            System.out.println("4. Editar");
            System.out.println("-------------------------------------");
            opcion = scanner.nextInt();
            scanner.nextLine();  // Consumir el siguiente '\n'
            String query;
            boolean act = true;
            switch (opcion) {
                case 1:
                    query = tablaTrabajo.añadir();
                    break;
                case 2:
                    act= false;
                    query = tablaTrabajo.consultar();
                    break;
                case 3:
                    query = tablaTrabajo.eliminar();
                    break;
                case 4:
                    query = tablaTrabajo.editar();
                    break;

                default:
                    System.out.println("Opción no válida.");
                    return;
            }
            conectorInterfaz.cargarControlador();
            conectorInterfaz.conectar();
            conectorInterfaz.ejecutarConsulta(query,act);
            conectorInterfaz.cerrarConexion();
        }

}
