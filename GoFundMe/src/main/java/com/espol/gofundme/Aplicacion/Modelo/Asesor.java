package com.espol.gofundme.Aplicacion.Modelo;

import com.espol.gofundme.Aplicacion.Modelo.IConsultable;
import com.espol.gofundme.Aplicacion.InterfazUsuario;
import java.util.List;
import java.util.Scanner;
public class Asesor implements IConsultable {
    private Integer ID;
    private String nombre;
    private String email;
    private String contraseña;
    private String teléfono;
    private InterfazUsuario manejadorConsola;
    private Scanner scanner;

    public Asesor() {
        manejadorConsola = new InterfazUsuario();
        scanner = new Scanner(System.in);
    }
    
    public Asesor(int ID, String nombre, String email, String contraseña, String teléfono) {
        this.ID = ID;
        this.nombre = nombre;
        this.email = email;
        this.contraseña = contraseña;
        this.teléfono = teléfono;
        manejadorConsola = new InterfazUsuario();
    }
    

    // Getters and setters
    // ... (Similar a las clases anteriores)

    @Override
    public String añadir() {
        // Logic to add a new Asesor record
        
        System.out.println("Añadir Asesor: ");
        manejadorConsola.pedirParametro(Integer.class, "ID");
        manejadorConsola.pedirParametro(String.class, "nombre");
        manejadorConsola.pedirParametro(String.class, "email");
        manejadorConsola.pedirParametro(String.class, "contraseña");
        manejadorConsola.pedirParametro(String.class, "teléfono");
        List<Object> resultados = manejadorConsola.pedirParametrosConsola();
        this.ID = (int)resultados.get(0);
        this.nombre = (String)resultados.get(1);
        this.email = (String)resultados.get(2);
        this.contraseña = (String)resultados.get(3);
        this.teléfono = (String)resultados.get(4);
        resultados = null;
        String sql = "INSERT INTO Asesor (ID, nombre, email, contraseña, teléfono) VALUES (" +
             this.ID + ", " +
             "'" + this.nombre + "', " +
             "'" + this.email + "', " +
             "'" + this.contraseña + "', " +
             "'" + this.teléfono + "'" +
             ");";
        return sql;

    }
    
    @Override
    public String consultar() {
        System.out.println("Consultar Asesor por ID: ");
        manejadorConsola.pedirParametro(Integer.class, "ID");
        List<Object> resultados = manejadorConsola.pedirParametrosConsola();
        this.ID = (Integer)resultados.get(0);
        resultados = null;
        String sql = "SELECT * FROM Asesor WHERE ID = " + this.ID.toString()+");";
        return sql;
    }

    @Override
    public String editar() {
        // Mostrar opciones de atributos que el usuario puede editar
        System.out.println("Editar Asesor:");
        System.out.println("1. Nombre");
        System.out.println("2. Email");
        System.out.println("3. Contraseña");
        System.out.println("4. Teléfono");
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
                campoAEditar = "email";
                tipoDato = String.class;
                break;
            case 3:
                campoAEditar = "contraseña";
                tipoDato = String.class;
                break;
            case 4:
                campoAEditar = "teléfono";
                tipoDato = String.class;
                break;
            // Puedes agregar más casos si tienes atributos con tipos de datos Integer o Double
            default:
                System.out.println("Opción no válida.");
                return "";
        }

        // Pedir al usuario el nuevo valor para el atributo seleccionado
        manejadorConsola.pedirParametro(tipoDato, campoAEditar);
        List<Object> resultados = manejadorConsola.pedirParametrosConsola();
        nuevoValor = resultados.get(0);

        // Pedir al usuario el ID del asesor a editar
        manejadorConsola.pedirParametro(Integer.class, "ID del Asesor a editar");
        resultados = manejadorConsola.pedirParametrosConsola();
        Integer ID = (Integer)resultados.get(0);

        // Generar el query SQL
        String sql = "UPDATE Asesor SET " + campoAEditar + " = ";

        // Dependiendo del tipo de dato, formatear el valor correctamente para el SQL
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
        System.out.println("Eliminar Asesor por ID: ");
        manejadorConsola.pedirParametro(Integer.class, "ID");
        List<Object> resultados = manejadorConsola.pedirParametrosConsola();
        this.ID = (Integer)resultados.get(0);
        resultados = null;
        String sql = "DELETE FROM Asesor WHERE ID =" +this.ID.toString()+");";
        return sql;
    }
}
