package com.espol.gofundme.Aplicacion.Modelo;

import com.espol.gofundme.Aplicacion.Modelo.IConsultable;
import com.espol.gofundme.Aplicacion.InterfazUsuario;
import java.util.List;
import java.util.Scanner;

public class Campaña implements IConsultable {
    private Integer ID;
    private String nombre;
    private String historia;
    private Double montoObjetivo;
    private String linkImagen;
    private Integer ID_ubicacion;
    private InterfazUsuario manejadorConsola;
    private Scanner scanner;

    public Campaña() {
        manejadorConsola = new InterfazUsuario();
        scanner = new Scanner(System.in);
    }
    
    public Campaña(Integer ID, String nombre, String historia, Double montoObjetivo, String linkImagen, Integer ID_ubicacion) {
        this.ID = ID;
        this.nombre = nombre;
        this.historia = historia;
        this.montoObjetivo = montoObjetivo;
        this.linkImagen = linkImagen;
        this.ID_ubicacion = ID_ubicacion;
        manejadorConsola = new InterfazUsuario();
    }

    

    // Getters and setters will be similar to previous classes

    @Override
    public String añadir() {
        System.out.println("Añadir Campaña: ");
        manejadorConsola.pedirParametro(Integer.class, "ID");
        manejadorConsola.pedirParametro(String.class, "nombre");
        manejadorConsola.pedirParametro(String.class, "historia");
        manejadorConsola.pedirParametro(Double.class, "montoObjetivo");
        manejadorConsola.pedirParametro(String.class, "linkImagen");
        manejadorConsola.pedirParametro(Integer.class, "ID_ubicacion");
        List<Object> resultados = manejadorConsola.pedirParametrosConsola();
        this.ID = (Integer)resultados.get(0);
        this.nombre = (String)resultados.get(1);
        this.historia = (String)resultados.get(2);
        this.montoObjetivo = (Double)resultados.get(3);
        this.linkImagen = (String)resultados.get(4);
        this.ID_ubicacion = (Integer)resultados.get(5);
        resultados = null;
        String sql = "INSERT INTO Campaña (ID, nombre, historia, montoObjetivo, linkImagen, ID_ubicacion) VALUES (" +
             this.ID + ", " +
             "'" + this.nombre + "', " +
             "'" + this.historia + "', " +
             this.montoObjetivo + ", " +
             "'" + this.linkImagen + "', " +
             this.ID_ubicacion +
             ");";
        return sql;
    }
    
    @Override
    public String consultar() {
        System.out.println("Consultar Campaña por ID: ");
        manejadorConsola.pedirParametro(Integer.class, "ID");
        List<Object> resultados = manejadorConsola.pedirParametrosConsola();
        this.ID = (Integer)resultados.get(0);
        resultados = null;
        String sql = "SELECT * FROM Campaña WHERE ID = " + this.ID.toString() + ";";
        return sql;
    }

    @Override
    public String editar() {
        // Mostrar opciones de atributos que el usuario puede editar
        System.out.println("Editar Campaña:");
        System.out.println("1. Nombre");
        System.out.println("2. Historia");
        System.out.println("3. Monto Objetivo");
        System.out.println("4. Link de Imagen");
        System.out.println("5. ID de Ubicación");
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
            case 2:
                campoAEditar = "historia";
                tipoDato = String.class;
                break;
            case 3:
                campoAEditar = "montoObjetivo";
                tipoDato = Double.class;
                break;
            case 4:
                campoAEditar = "linkImagen";
                tipoDato = String.class;
                break;
            case 5:
                campoAEditar = "ID_ubicacion";
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

        // Pedir al usuario el ID de la campaña a editar
        manejadorConsola.pedirParametro(Integer.class, "ID de la Campaña a editar");
        resultados = manejadorConsola.pedirParametrosConsola();
        Integer ID = (Integer)resultados.get(0);

        // Generar el query SQL
        String sql = "UPDATE Campaña SET " + campoAEditar + " = ";

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
        System.out.println("Eliminar Campaña por ID: ");
        manejadorConsola.pedirParametro(Integer.class, "ID");
        List<Object> resultados = manejadorConsola.pedirParametrosConsola();
        this.ID = (Integer)resultados.get(0);
        resultados = null;
        String sql = "DELETE FROM Campaña WHERE ID = " + this.ID.toString() + ";";
        return sql;
    }
}
