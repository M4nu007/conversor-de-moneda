package com.manuel.conversordemoneda.modelo;

import java.util.Scanner;

public class EntradaUsuario {
    private final Scanner lectura = new Scanner(System.in);

    public double leerMontoAConvertir() {
        while (true) {
            try {

                System.out.println("Ingresa el valor que deseas convertir:");
                double monto = Double.parseDouble(lectura.nextLine());

                if (monto <= 0) {
                    System.out.println("El monto debe ser mayor a 0.");
                } else {
                    return monto;
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Ingresa un número válido.");
            }
        }
    }
}
