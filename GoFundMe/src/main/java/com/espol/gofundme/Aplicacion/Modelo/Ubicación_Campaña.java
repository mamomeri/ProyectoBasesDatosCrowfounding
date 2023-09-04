package com.espol.gofundme.Aplicacion.Modelo;

import com.espol.gofundme.Aplicacion.Modelo.IConsultable;
import com.espol.gofundme.Aplicacion.InterfazUsuario;
import java.util.List;
import java.util.Scanner;

public class Ubicación_Campaña implements IConsultable {
    private Integer ID;
    private String País;
    private String Ciudad;
    private InterfazUsuario manejadorConsola;
    private Scanner scanner;

    public Ubicación_Campaña() {
        manejadorConsola = new InterfazUsuario();
        scanner = new Scanner(System.in);
    }
    
    public Ubicación_Campaña(Integer ID, String País, String Ciudad) {
        this.ID = ID;
        this.País = País;
        this.Ciudad = Ciudad;
        manejadorConsola = new InterfazUsuario();
        scanner = new Scanner(System.in);
    }

    

    // Getters and setters
    // ... (Similar a las clases anteriores)

    @Override
    public String añadir() {
        System.out.println("Añadir Ubicación de Campaña: ");
        manejadorConsola.pedirParametro(Integer.class, "ID");
        manejadorConsola.pedirParametro(String.class, "País");
        manejadorConsola.pedirParametro(String.class, "Ciudad");
        List<Object> resultados = manejadorConsola.pedirParametrosConsola();
        this.ID = (Integer)resultados.get(0);
        this.País = (String)resultados.get(1);
        this.Ciudad = (String)resultados.get(2);
        resultados = null;
        String sql = "INSERT INTO Ubicación_Campaña (ID, País, Ciudad) VALUES (" +
             this.ID + ", " +
             "'" + this.País + "', " +
             "'" + this.Ciudad + "');";
        return sql;
    }

    @Override
    public String consultar() {
        System.out.println("Consultar Ubicación de Campaña por ID: ");
        manejadorConsola.pedirParametro(Integer.class, "ID");
        List<Object> resultados = manejadorConsola.pedirParametrosConsola();
        this.ID = (Integer)resultados.get(0);
        resultados = null;
        String sql = "SELECT * FROM Ubicación_Campaña WHERE ID = " + this.ID.toString() + ";";
        return sql;
    }

    @Override
    public String editar() {
        // Mostrar opciones de atributos que el usuario puede editar
        System.out.println("Editar Ubicación de Campaña:");
        System.out.println("1. País");
        System.out.println("2. Ciudad");
        System.out.print("Seleccione el número del atributo que desea editar: ");

        int opcion = scanner.nextInt();
        String campoAEditar = "";
        Object nuevoValor = null;
        Class<?> tipoDato = null;

        switch (opcion) {
            case 1:
                campoAEditar = "País";
                tipoDato = String.class;
                break;
            case 2:
                campoAEditar = "Ciudad";
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

        // Pedir al usuario el ID de la ubicación de la campaña a editar
        manejadorConsola.pedirParametro(Integer.class, "ID de la Ubicación de la Campaña a editar");
        resultados = manejadorConsola.pedirParametrosConsola();
        Integer ID = (Integer)resultados.get(0);

        // Generar el query SQL
        String sql = "UPDATE Ubicación_Campaña SET " + campoAEditar + " = ";

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
        System.out.println("Eliminar Ubicación de Campaña por ID: ");
        manejadorConsola.pedirParametro(Integer.class, "ID");
        List<Object> resultados = manejadorConsola.pedirParametrosConsola();
        this.ID = (Integer)resultados.get(0);
        resultados = null;
        String sql = "DELETE FROM Ubicación_Campaña WHERE ID = " + this.ID.toString() + ";";
        return sql;
    }
}
