package Aplicacion.Modelo;

import Aplicacion.Modelo.IConsultable;
import Aplicacion.InterfazUsuario;
import java.util.List;
import java.util.Scanner;

public class Comentario implements IConsultable {
    private Integer ID;
    private String fecha;
    private String contenido;
    private Integer ID_usuario;
    private Integer ID_campaña;
    private InterfazUsuario manejadorConsola;
    private Scanner scanner;

    public Comentario() {
        manejadorConsola = new InterfazUsuario();
        scanner = new Scanner(System.in);
    }
    
    public Comentario(Integer ID, String fecha, String contenido, Integer ID_usuario, Integer ID_campaña) {
        this.ID = ID;
        this.fecha = fecha;
        this.contenido = contenido;
        this.ID_usuario = ID_usuario;
        this.ID_campaña = ID_campaña;
        manejadorConsola = new InterfazUsuario();
    }

    

    // Getters and setters

    // ... (Similar a las clases anteriores)

    @Override
    public String añadir() {
        System.out.println("Añadir Comentario: ");
        manejadorConsola.pedirParametro(Integer.class, "ID");
        manejadorConsola.pedirParametro(String.class, "fecha");
        manejadorConsola.pedirParametro(String.class, "contenido");
        manejadorConsola.pedirParametro(Integer.class, "ID_usuario");
        manejadorConsola.pedirParametro(Integer.class, "ID_campaña");
        List<Object> resultados = manejadorConsola.pedirParametrosConsola();
        this.ID = (Integer)resultados.get(0);
        this.fecha = (String)resultados.get(1);
        this.contenido = (String)resultados.get(2);
        this.ID_usuario = (Integer)resultados.get(3);
        this.ID_campaña = (Integer)resultados.get(4);
        resultados = null;
        String sql = "INSERT INTO Comentario (ID, fecha, contenido, ID_usuario, ID_campaña) VALUES (" +
             this.ID + ", " +
             "'" + this.fecha + "', " +
             "'" + this.contenido + "', " +
             this.ID_usuario + ", " +
             this.ID_campaña +
             ");";
        return sql;
    }
    
    @Override
    public String consultar() {
        System.out.println("Consultar Comentario por ID: ");
        manejadorConsola.pedirParametro(Integer.class, "ID");
        List<Object> resultados = manejadorConsola.pedirParametrosConsola();
        this.ID = (Integer)resultados.get(0);
        resultados = null;
        String sql = "SELECT * FROM Comentario WHERE ID = " + this.ID.toString() + ";";
        return sql;
    }

    @Override
    public String editar() {
      
        System.out.println("Editar Comentario:");
        System.out.println("1. Fecha");
        System.out.println("2. Contenido");
        System.out.println("3. ID del usuario");
        System.out.println("4. ID de la campaña");
        System.out.print("Seleccione el número del atributo que desea editar: ");

        int opcion = scanner.nextInt();
        String campoAEditar = "";
        Object nuevoValor = null;
        Class<?> tipoDato = null;

        switch (opcion) {
            case 1:
                campoAEditar = "fecha";
                tipoDato = String.class;
                break;
            case 2:
                campoAEditar = "contenido";
                tipoDato = String.class;
                break;
            case 3:
                campoAEditar = "ID_usuario";
                tipoDato = Integer.class;
                break;
            case 4:
                campoAEditar = "ID_campaña";
                tipoDato = Integer.class;
                break;
            default:
                System.out.println("Opción no válida.");
                return "";
        }

        // Pedir al usuario el nuevo valor para el atributo seleccionado
        manejadorConsola.pedirParametro(tipoDato, campoAEditar);
        List<Object> resultados = manejadorConsola.pedirParametrosConsola();
        nuevoValor = resultados.get(0);

        // Pedir al usuario el ID del comentario a editar
        manejadorConsola.pedirParametro(Integer.class, "ID del Comentario a editar");
        resultados = manejadorConsola.pedirParametrosConsola();
        Integer ID = (Integer)resultados.get(0);

        // Generar el query SQL
        String sql = "UPDATE Comentario SET " + campoAEditar + " = ";

        if (tipoDato == String.class) {
            sql += "'" + nuevoValor.toString() + "'";
        } else {
            sql += nuevoValor.toString();
        }

        sql += " WHERE ID = " + ID.toString() + ";";

        return sql;
    }


    @Override
    public String eliminar() {
        System.out.println("Eliminar Comentario por ID: ");
        manejadorConsola.pedirParametro(Integer.class, "ID");
        List<Object> resultados = manejadorConsola.pedirParametrosConsola();
        this.ID = (Integer)resultados.get(0);
        resultados = null;
        String sql = "DELETE FROM Comentario WHERE ID = " + this.ID.toString() + ";";
        return sql;
    }
}
