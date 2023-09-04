package com.espol.gofundme.Aplicacion;


import java.util.Scanner;

public class InputRequest<T> {
    private Class<T> type;
    private String message;

    public InputRequest(Class<T> type, String message) {
        this.type = type;
        this.message = message;
    }

    public Object requestInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Escriba el parámetro ");
        System.out.print(message);
        System.out.print(": ");

        if (type.equals(Integer.class)) {
            return scanner.nextInt();
        } else if (type.equals(String.class)) {
            return scanner.nextLine();
        }

        return null;
    }

    public String getMessage() {
        return message;
    }
}
