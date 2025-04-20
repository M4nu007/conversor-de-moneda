package com.manuel.conversordemoneda.modelo;

import com.google.gson.Gson;
import io.github.cdimascio.dotenv.Dotenv;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class TasaDeCambio {
    private final Dotenv dotenv = Dotenv.load();

    public RespuestaTipoDeCambio convertirMoneda(String base, String destino) {
        String apiKey = dotenv.get("API_KEY");
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/"+apiKey+"/pair/"+ base + "/" + destino);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            RespuestaTipoDeCambio respuesta =  new Gson().fromJson(response.body(), RespuestaTipoDeCambio.class);

            if (!"success".equalsIgnoreCase(respuesta.result())) {
                String error = respuesta.errorType() != null ? respuesta.errorType() : "Error desconocido";
                throw new RuntimeException("Error en la conversión: " + error);
            }
            return respuesta;

        } catch (Exception e) {
            throw new RuntimeException("No se pudo obtener la conversión: " + e.getMessage(), e);
        }

    }

}
