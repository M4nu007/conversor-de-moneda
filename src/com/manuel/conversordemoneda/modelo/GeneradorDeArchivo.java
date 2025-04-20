package com.manuel.conversordemoneda.modelo;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GeneradorDeArchivo {

    public void guardarHistorial(List<String> lista) {
        try {
            FileWriter escritura = new FileWriter("historial_de_conversiones.txt");
            for (String resultado : lista) {
                escritura.write(resultado);
                escritura.write("\n");
            }
            escritura.close();
            System.out.println("Historial de consultas guardado en 'historial_de_conversiones.txt'");
        } catch (IOException e) {
            System.out.println("Error al guardar el historial de consultas: " + e.getMessage());
        }
    }

}
