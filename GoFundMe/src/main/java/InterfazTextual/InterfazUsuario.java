package InterfazTextual;

import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author DELL
 */
public class InterfazUsuario implements IUsuario{

    
    
    @Override
    public void MostrarInterfaz(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenido al sistema de Crowdfunding");
        System.out.println("-------------------------------------");
        System.out.println("Por favor, ingrese los siguientes datos:");
        
        System.out.println("Email: ");
        String email = scanner.nextLine();

        System.out.println("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.println("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea después de leer un entero

        System.out.println("Contraseña: ");
        String contraseña = scanner.nextLine();

        System.out.println("Tarjeta de crédito: ");
        String tarjetaCredito = scanner.nextLine();

        System.out.println("Tipo de usuario (donante/beneficiario): ");
        String tipoUsuario = scanner.nextLine();

        System.out.println("-------------------------------------");
        System.out.println("¡Registro exitoso!");
        System.out.println("Email: " + email);
        System.out.println("Nombre: " + nombre);
        System.out.println("ID: " + id);
        System.out.println("Contraseña: " + contraseña);
        System.out.println("Tarjeta de crédito: " + tarjetaCredito);
        System.out.println("Tipo de usuario: " + tipoUsuario);
    }
}
