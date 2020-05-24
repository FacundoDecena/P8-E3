package utils;

import models.Blackboard;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class HTTPMethods {

        public static void post(String uri, Blackboard grid) throws Exception{
            URL url = new URL(uri);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            try(OutputStream os = connection.getOutputStream()){
                String bb = new Gson().toJson(grid);
                byte[] input = bb.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }
            try(BufferedReader br = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println(response.toString());
            }
        }

        public static Blackboard get(String uri) throws Exception {
            Blackboard grid = new Blackboard();

            URL url = new URL(uri);

            HttpURLConnection connexion = (HttpURLConnection) url.openConnection();
            connexion.setRequestMethod("GET");

            BufferedReader rd = new BufferedReader(new InputStreamReader(connexion.getInputStream()));

            String linea;

            while ((linea = rd.readLine()) != null) {
                grid = new Gson().fromJson(linea, Blackboard.class);
            }
            rd.close();

            return grid;
        }

}
