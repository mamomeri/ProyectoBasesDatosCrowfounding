package com.espol.gofundme.Aplicacion.Modelo;

import com.espol.gofundme.Aplicacion.InterfazUsuario;
import com.espol.gofundme.Aplicacion.Modelo.IConsultable;
import java.util.List;
import java.util.Scanner;

public class Asesor_Campaña implements IConsultable {
    private Integer ID;
    private Integer ID_Estado_legal;
    private Integer ID_asesor;
    private Integer ID_campaña;
    private InterfazUsuario manejadorConsola;
    private Scanner scanner;
    
    public Asesor_Campaña() {
        
        manejadorConsola = new InterfazUsuario();
        scanner = new Scanner(System.in);
    }
    
    public Asesor_Campaña(int ID, int ID_Estado_legal, int ID_asesor, int ID_campaña) {
        this.ID = ID;
        this.ID_Estado_legal = ID_Estado_legal;
        this.ID_asesor = ID_asesor;
        this.ID_campaña = ID_campaña;
    }
    
    // Getters and setters
    // ... (Similar a las clases anteriores)

    @Override
    public String añadir() {
        // Logic to add a new Asesor record
        
        System.out.println("Añadir Asesor campaña: ");
        manejadorConsola.pedirParametro(Integer.class, "ID");
        manejadorConsola.pedirParametro(Integer.class, "ID_Estado_legal");
        manejadorConsola.pedirParametro(Integer.class, "ID_asesor");
        manejadorConsola.pedirParametro(Integer.class, "ID_campaña");
        List<Object> resultados = manejadorConsola.pedirParametrosConsola();
        this.ID = (Integer)resultados.get(0);
        this.ID_Estado_legal = (Integer)resultados.get(1);
        this.ID_asesor = (Integer)resultados.get(2);
        this.ID_campaña = (Integer)resultados.get(3);
        
        String sql = "INSERT INTO Asesor (ID, ID_Estado_legal, ID_asesor, ID_campaña) VALUES (" +
             this.ID + ", " +  
             "'" + this.ID_Estado_legal + "', " +
             "'" + this.ID_asesor + "', " +
             "'" + this.ID_campaña + "'" +
             ");";
        return sql;

    }
    
    @Override
    public String consultar() {
        System.out.println("Consultar Asesor de campaña por ID: ");
        manejadorConsola.pedirParametro(Integer.class, "ID");
        List<Object> resultados = manejadorConsola.pedirParametrosConsola();
        this.ID = (Integer)resultados.get(0);
        resultados = null; 
        String sql = "SELECT * FROM Asesor_Campaña WHERE ID = " + this.ID.toString()+");";
        return sql;
    }

    @Override
    public String editar() {
        // Mostrar opciones de atributos que el usuario puede editar
        System.out.println("Editar Asesor de Campaña:");
        System.out.println("1. ID_Estado_legal");
        System.out.println("2. ID_asesor");
        System.out.println("3. ID_campaña");
        System.out.print("Seleccione el número del atributo que desea editar: ");

        int opcion = scanner.nextInt();
        String campoAEditar = "";

        switch (opcion) {
            case 1:
                campoAEditar = "ID_Estado_legal";
                break;
            case 2:
                campoAEditar = "ID_asesor";
                break;
            case 3:
                campoAEditar = "ID_campaña";
                break;
            default:
                System.out.println("Opción no válida.");
                return "";
        }

        // Pedir al usuario el nuevo valor para el atributo seleccionado
        manejadorConsola.pedirParametro(Integer.class, campoAEditar);
        List<Object> resultados = manejadorConsola.pedirParametrosConsola();
        Integer nuevoValor = (Integer)resultados.get(0);

        // Pedir al usuario el ID de la relación Asesor_Campaña a editar
        manejadorConsola.pedirParametro(Integer.class, "ID de Asesor_Campaña a editar");
        resultados = manejadorConsola.pedirParametrosConsola();
        Integer ID = (Integer)resultados.get(0);

        // Generar el query SQL
        String sql = "UPDATE Asesor_Campaña SET " + campoAEditar + " = " + nuevoValor.toString() +
                     " WHERE ID = " + ID.toString() + ";";

        return sql;
    }

    @Override
    public String eliminar() {
        System.out.println("Eliminar Asesor de Campaña por ID: ");
        manejadorConsola.pedirParametro(Integer.class, "ID");
        List<Object> resultados = manejadorConsola.pedirParametrosConsola();
        this.ID = (Integer)resultados.get(0);
        String sql = "DELETE FROM Asesor_Campaña WHERE ID =" +this.ID.toString()+");";
        return sql;
    }
}
