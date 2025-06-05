package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

      public class Cotizacion {

        public static void consultarCotizacion(String criptoId) {
            try {
                String endpoint = "https://api.coingecko.com/api/v3/simple/price?ids=" + criptoId + "&vs_currencies=usd";
                URL url = new URL(endpoint);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept", "application/json");

                if (conn.getResponseCode() != 200) {
                    System.out.println("Error: Código de respuesta " + conn.getResponseCode());
                    return;
                }

                BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
                String output;
                StringBuilder respuesta = new StringBuilder();
                while ((output = br.readLine()) != null) {
                    respuesta.append(output);
                }

                conn.disconnect();

                // Simplemente mostramos el JSON recibido
                System.out.println("Cotización actual: " + respuesta.toString());

            } catch (Exception e) {
                System.out.println("Error al consultar la cotización: " + e.getMessage());
            }
        }
    }


