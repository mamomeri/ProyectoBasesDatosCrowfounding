package com.espol.gofundme.Aplicacion.Modelo;

import com.espol.gofundme.Aplicacion.Modelo.IConsultable;
import com.espol.gofundme.Aplicacion.InterfazUsuario;
import java.util.List;
import java.util.Scanner;

public class Usuario implements IConsultable {
    private Integer ID;
    private String nombre;
    private String email;
    private String contraseña;
    private String tarjetaDebito;
    private Integer ID_tipo_usuario;
    private Integer ID_campaña;  
    private InterfazUsuario manejadorConsola;
    private Scanner scanner;

    public Usuario() {
        manejadorConsola = new InterfazUsuario();
        scanner = new Scanner(System.in);
    }
    
    public Usuario(Integer ID, String nombre, String email, String contraseña, String tarjetaDebito, Integer ID_tipo_usuario, Integer ID_campaña) {
        this.ID = ID;
        this.nombre = nombre;
        this.email = email;
        this.contraseña = contraseña;
        this.tarjetaDebito = tarjetaDebito;
        this.ID_tipo_usuario = ID_tipo_usuario;
        this.ID_campaña = ID_campaña;
        manejadorConsola = new InterfazUsuario();
        scanner = new Scanner(System.in);
    }

    

    // Getters and setters
    // ... (Similar a las clases anteriores)

    @Override
    public String añadir() {
        System.out.println("Añadir Usuario: ");
        manejadorConsola.pedirParametro(Integer.class, "ID");
        manejadorConsola.pedirParametro(String.class, "nombre");
        manejadorConsola.pedirParametro(String.class, "email");
        manejadorConsola.pedirParametro(String.class, "contraseña");
        manejadorConsola.pedirParametro(String.class, "tarjetaDebito");
        manejadorConsola.pedirParametro(Integer.class, "ID_tipo_usuario");
        manejadorConsola.pedirParametro(Integer.class, "ID_campaña");
        List<Object> resultados = manejadorConsola.pedirParametrosConsola();
        this.ID = (Integer)resultados.get(0);
        this.nombre = (String)resultados.get(1);
        this.email = (String)resultados.get(2);
        this.contraseña = (String)resultados.get(3);
        this.tarjetaDebito = (String)resultados.get(4);
        this.ID_tipo_usuario = (Integer)resultados.get(5);
        this.ID_campaña = (Integer)resultados.get(6);
        resultados = null;
        String sql = "INSERT INTO Usuario (ID, nombre, email, contraseña, tarjetaDebito, ID_tipo_usuario, ID_campaña) VALUES (" +
             this.ID + ", " +
             "'" + this.nombre + "', " +
             "'" + this.email + "', " +
             "'" + this.contraseña + "', " +
             "'" + this.tarjetaDebito + "', " +
             (this.ID_tipo_usuario != null ? this.ID_tipo_usuario : "NULL") + ", " +
             (this.ID_campaña != null ? this.ID_campaña : "NULL") + 
             ");";
        return sql;
    }

    @Override
    public String consultar() {
        System.out.println("Consultar Usuario por ID: ");
        manejadorConsola.pedirParametro(Integer.class, "ID");
        List<Object> resultados = manejadorConsola.pedirParametrosConsola();
        this.ID = (Integer)resultados.get(0);
        resultados = null;
        String sql = "SELECT * FROM Usuario WHERE ID = " + this.ID.toString() + ";";
        return sql;
    }

    @Override
    public String editar() {
        System.out.println("Editar Usuario:");
        System.out.println("1. Nombre");
        System.out.println("2. Email");
        System.out.println("3. Contraseña");
        System.out.println("4. Tarjeta Debito");
        System.out.println("5. Tipo de Usuario (ID)");
        System.out.println("6. Campaña (ID)");
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
                campoAEditar = "tarjetaDebito";
                tipoDato = String.class;
                break;
            case 5:
                campoAEditar = "ID_tipo_usuario";
                tipoDato = Integer.class;
                break;
            case 6:
                campoAEditar = "ID_campaña";
                tipoDato = Integer.class;
                break;
            default:
                System.out.println("Opción no válida.");
                return "";
        }

        // Pedir al usuario el nuevo valor para el atributo seleccionado
        manejadorConsola.pedirParametro(tipoDato, "Nuevo valor para " + campoAEditar);
        List<Object> resultados = manejadorConsola.pedirParametrosConsola();
        nuevoValor = resultados.get(0);

        // Pedir al usuario el ID del usuario a editar
        manejadorConsola.pedirParametro(Integer.class, "ID del Usuario a editar");
        resultados = manejadorConsola.pedirParametrosConsola();
        Integer ID = (Integer)resultados.get(0);

        // Generar el query SQL
        String sql = "UPDATE Usuario SET " + campoAEditar + " = ";

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
        System.out.println("Eliminar Usuario por ID: ");
        manejadorConsola.pedirParametro(Integer.class, "ID");
        List<Object> resultados = manejadorConsola.pedirParametrosConsola();
        this.ID = (Integer)resultados.get(0);
        resultados = null;
        String sql = "DELETE FROM Usuario WHERE ID = " + this.ID.toString() + ";";
        return sql;
    }
}
