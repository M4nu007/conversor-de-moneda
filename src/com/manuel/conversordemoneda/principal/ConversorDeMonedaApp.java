package com.manuel.conversordemoneda.principal;

import com.manuel.conversordemoneda.modelo.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConversorDeMonedaApp {
    public static void main(String[] args) {
        Scanner lectura = new Scanner(System.in);
        Menu menu = new Menu();
        TasaDeCambio conversion = new TasaDeCambio();
        List<String> historial = new ArrayList<>();
        GeneradorDeArchivo generador = new GeneradorDeArchivo();
        DateTimeFormatter formatoElegido = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        int opcionElegida = 0;

        while(opcionElegida != 8){
            menu.mostrarMenuInicial();

            try{
                opcionElegida = Integer.parseInt(lectura.nextLine());
                if(opcionElegida == 8){
                    System.out.println("Gracias por usar el conversor. ¡Hasta luego!");
                    break;
                }

                String codigoOrigen = "", codigoDestino = "";


                switch (opcionElegida) {
                    case 1:
                        codigoOrigen = "USD";
                        codigoDestino = "PEN";
                        break;
                    case 2:
                        codigoOrigen = "PEN";
                        codigoDestino = "USD";
                        break;
                    case 3:
                        codigoOrigen = "USD";
                        codigoDestino = "ARS";
                        break;
                    case 4:
                        codigoOrigen = "ARS";
                        codigoDestino = "USD";
                        break;
                    case 5:
                        codigoOrigen = "USD";
                        codigoDestino = "COP";
                        break;
                    case 6:
                        codigoOrigen = "COP";
                        codigoDestino = "USD";
                        break;
                    case 7:
                        menu.mostrarMenuMasOpciones();
                        System.out.println("Ingresa el código de moneda base (ej. USD): ");
                        codigoOrigen = lectura.nextLine().toUpperCase();
                        System.out.println("Ingresa el código de moneda codigoDestino (ej. EUR): ");
                        codigoDestino = lectura.nextLine().toUpperCase();

                        break;
                    default:
                        System.out.println("❌ Opción inválida. Intente nuevamente.");
                        continue;
                }

                EntradaUsuario entradaUsuario = new EntradaUsuario();
                double monto = entradaUsuario.leerMontoAConvertir();

                RespuestaTipoDeCambio respuestaTipoDeCambio = conversion.convertirMoneda(codigoOrigen, codigoDestino);
                double resultado = respuestaTipoDeCambio.conversion_rate() * monto;

                String marcaDeTiempo = LocalDateTime.now().format(formatoElegido);
                String registro = String.format("%s - %.2f [%s] => %.2f [%s]", marcaDeTiempo, monto, codigoOrigen, resultado, codigoDestino);
                historial.add(registro);

                System.out.printf("✅ %.2f [%s] equivale al valor final de =>>> %.2f [%s]%n", monto, codigoOrigen, resultado, codigoDestino);

            }catch (NumberFormatException e){
                System.out.println("⚠️ Ingrese un número válido.");
            } catch(Exception e){
                System.out.println("❌ Error: " + e.getMessage());
            }

        }
        generador.guardarHistorial(historial);
    }

}
