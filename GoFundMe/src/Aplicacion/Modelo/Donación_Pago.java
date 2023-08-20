package Aplicacion.Modelo;

import Aplicacion.Modelo.IConsultable;
import Aplicacion.InterfazUsuario;
import java.util.List;
import java.util.Scanner;

public class Donación_Pago implements IConsultable {
    private Integer ID_donacion;
    private Double monto;
    private String fecha_donacion;
    private String metodo_pago;
    private String fecha_pago;
    private Integer ID_usuario;
    private Integer ID_campaña;
    private InterfazUsuario manejadorConsola;
    private Scanner scanner;

    public Donación_Pago() {
        manejadorConsola = new InterfazUsuario();
        scanner = new Scanner(System.in);
    }
    
    public Donación_Pago(Integer ID_donacion, Double monto, String fecha_donacion, String metodo_pago, String fecha_pago, Integer ID_usuario, Integer ID_campaña) {
        this.ID_donacion = ID_donacion;
        this.monto = monto;
        this.fecha_donacion = fecha_donacion;
        this.metodo_pago = metodo_pago;
        this.fecha_pago = fecha_pago;
        this.ID_usuario = ID_usuario;
        this.ID_campaña = ID_campaña;
        manejadorConsola = new InterfazUsuario();
    }

    

    // Getters and setters
    // ... (Similar a las clases anteriores)

    @Override
    public String añadir() {
        System.out.println("Añadir Donación_Pago: ");
        manejadorConsola.pedirParametro(Integer.class, "ID_donacion");
        manejadorConsola.pedirParametro(Double.class, "monto");
        manejadorConsola.pedirParametro(String.class, "fecha_donacion");
        manejadorConsola.pedirParametro(String.class, "metodo_pago");
        manejadorConsola.pedirParametro(String.class, "fecha_pago");
        manejadorConsola.pedirParametro(Integer.class, "ID_usuario");
        manejadorConsola.pedirParametro(Integer.class, "ID_campaña");
        List<Object> resultados = manejadorConsola.pedirParametrosConsola();
        this.ID_donacion = (Integer)resultados.get(0);
        this.monto = (Double)resultados.get(1);
        this.fecha_donacion = (String)resultados.get(2);
        this.metodo_pago = (String)resultados.get(3);
        this.fecha_pago = (String)resultados.get(4);
        this.ID_usuario = (Integer)resultados.get(5);
        this.ID_campaña = (Integer)resultados.get(6);
        resultados = null;
        String sql = "INSERT INTO Donación_Pago (ID_donacion, monto, fecha_donacion, metodo_pago, fecha_pago, ID_usuario, ID_campaña) VALUES (" +
             this.ID_donacion + ", " +
             this.monto + ", " +
             "'" + this.fecha_donacion + "', " +
             "'" + this.metodo_pago + "', " +
             "'" + this.fecha_pago + "', " +
             this.ID_usuario + ", " +
             this.ID_campaña +
             ");";
        return sql;
    }
    
    @Override
    public String consultar() {
        System.out.println("Consultar Donación_Pago por ID_donacion: ");
        manejadorConsola.pedirParametro(Integer.class, "ID_donacion");
        List<Object> resultados = manejadorConsola.pedirParametrosConsola();
        this.ID_donacion = (Integer)resultados.get(0);
        resultados = null;
        String sql = "SELECT * FROM Donación_Pago WHERE ID_donacion = " + this.ID_donacion.toString() + ";";
        return sql;
    }

    @Override
    public String editar() {
        // Mostrar opciones de atributos que el usuario puede editar
        System.out.println("Editar Donación_Pago:");
        System.out.println("1. Monto");
        System.out.println("2. Fecha de donación");
        System.out.println("3. Método de pago");
        System.out.println("4. Fecha de pago");
        System.out.println("5. ID del usuario");
        System.out.println("6. ID de la campaña");
        System.out.print("Seleccione el número del atributo que desea editar: ");

        int opcion = scanner.nextInt();
        String campoAEditar = "";
        Object nuevoValor = null;
        Class<?> tipoDato = null;

        switch (opcion) {
            case 1:
                campoAEditar = "monto";
                tipoDato = Double.class;
                break;
            case 2:
                campoAEditar = "fecha_donacion";
                tipoDato = String.class;
                break;
            case 3:
                campoAEditar = "metodo_pago";
                tipoDato = String.class;
                break;
            case 4:
                campoAEditar = "fecha_pago";
                tipoDato = String.class;
                break;
            case 5:
                campoAEditar = "ID_usuario";
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
        manejadorConsola.pedirParametro(tipoDato, campoAEditar);
        List<Object> resultados = manejadorConsola.pedirParametrosConsola();
        nuevoValor = resultados.get(0);

        // Pedir al usuario el ID de la donación a editar
        manejadorConsola.pedirParametro(Integer.class, "ID de la Donación a editar");
        resultados = manejadorConsola.pedirParametrosConsola();
        Integer ID_donacion = (Integer)resultados.get(0);

        // Generar el query SQL
        String sql = "UPDATE Donación_Pago SET " + campoAEditar + " = ";

        if (tipoDato == String.class) {
            sql += "'" + nuevoValor.toString() + "'";
        } else {
            sql += nuevoValor.toString();
        }

        sql += " WHERE ID_donacion = " + ID_donacion.toString() + ";";

        return sql;
    }


    @Override
    public String eliminar() {
        System.out.println("Eliminar Donación_Pago por ID_donacion: ");
        manejadorConsola.pedirParametro(Integer.class, "ID_donacion");
        List<Object> resultados = manejadorConsola.pedirParametrosConsola();
        this.ID_donacion = (Integer)resultados.get(0);
        resultados = null;
        String sql = "DELETE FROM Donación_Pago WHERE ID_donacion = " + this.ID_donacion.toString() + ";";
        return sql;
    }
}
