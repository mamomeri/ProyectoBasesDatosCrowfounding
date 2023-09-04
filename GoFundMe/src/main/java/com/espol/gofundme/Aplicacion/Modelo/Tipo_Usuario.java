
package com.espol.gofundme.Aplicacion.Modelo;

import com.espol.gofundme.Aplicacion.Modelo.IConsultable;
import com.espol.gofundme.Aplicacion.InterfazUsuario;
import java.util.List;
import java.util.Scanner;

public class Tipo_Usuario implements IConsultable {
    private Integer ID;
    private String nombre;
    private InterfazUsuario manejadorConsola;
    private Scanner scanner;

    public Tipo_Usuario() {
        manejadorConsola = new InterfazUsuario();
        scanner = new Scanner(System.in);
    }
    
    public Tipo_Usuario(Integer ID, String nombre) {
        this.ID = ID;
        this.nombre = nombre;
        manejadorConsola = new InterfazUsuario();
        scanner = new Scanner(System.in);
    }


    // Getters and setters
    // ... (Similar a las clases anteriores)

    @Override
    public String añadir() {
        System.out.println("Añadir Tipo de Usuario: ");
        manejadorConsola.pedirParametro(Integer.class, "ID");
        manejadorConsola.pedirParametro(String.class, "nombre");
        List<Object> resultados = manejadorConsola.pedirParametrosConsola();
        this.ID = (Integer)resultados.get(0);
        this.nombre = (String)resultados.get(1);
        resultados = null;
        String sql = "INSERT INTO Tipo_Usuario (ID, nombre) VALUES (" +
             this.ID + ", " +
             "'" + this.nombre + "');";
        return sql;
    }

    @Override
    public String consultar() {
        System.out.println("Consultar Tipo de Usuario por ID: ");
        manejadorConsola.pedirParametro(Integer.class, "ID");
        List<Object> resultados = manejadorConsola.pedirParametrosConsola();
        this.ID = (Integer)resultados.get(0);
        resultados = null;
        String sql = "SELECT * FROM Tipo_Usuario WHERE ID = " + this.ID.toString() + ";";
        return sql;
    }

    @Override
    public String editar() {
        // Mostrar opciones de atributos que el usuario puede editar
        System.out.println("Editar Tipo de Usuario:");
        System.out.println("1. Nombre");
        System.out.print("Seleccione el número del atributo que desea editar: ");

        int opcion = scanner.nextInt();
        String campoAEditar = "";
        Object nuevoValor = null;
        Class<?> tipoDato = null;

        switch (opcion) {
            case 1:
                campoAEditar = "nombre";
                tipoDato = String.class;
                break;
            default:
                System.out.println("Opción no válida.");
                return "";
        }

        // Pedir al usuario el nuevo valor para el atributo seleccionado
        manejadorConsola.pedirParametro(tipoDato, campoAEditar);
        List<Object> resultados = manejadorConsola.pedirParametrosConsola();
        nuevoValor = resultados.get(0);

        // Pedir al usuario el ID del tipo de usuario a editar
        manejadorConsola.pedirParametro(Integer.class, "ID del Tipo de Usuario a editar");
        resultados = manejadorConsola.pedirParametrosConsola();
        Integer ID = (Integer)resultados.get(0);

        // Generar el query SQL
        String sql = "UPDATE Tipo_Usuario SET " + campoAEditar + " = ";

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
        System.out.println("Eliminar Tipo de Usuario por ID: ");
        manejadorConsola.pedirParametro(Integer.class, "ID");
        List<Object> resultados = manejadorConsola.pedirParametrosConsola();
        this.ID = (Integer)resultados.get(0);
        resultados = null;
        String sql = "DELETE FROM Tipo_Usuario WHERE ID = " + this.ID.toString() + ";";
        return sql;
    }
}
