package controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.template.velocity.VelocityTemplateEngine;
import util.Path;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class SudokuController {
    public static Route home = (Request req, Response res) ->{
        HashMap<String, Object> model = new HashMap<String, Object>();

        return new VelocityTemplateEngine().render(new ModelAndView(model, Path.Template.LAYOUT));
    };

    public static Route searchSolution = (Request req, Response res) ->{
        HashMap<String, Object> model = new HashMap<String, Object>();
        //  int[] grid = req.queryParams("grid");
        int[] grid = {
                0,8,0,5,7,6,2,0,0,
                0,0,0,4,0,2,0,0,0,
                0,0,0,0,3,9,5,4,8,
                6,3,0,9,0,0,8,5,2,
                0,9,0,2,0,0,3,7,0,
                8,0,0,0,5,0,6,9,4,
                2,5,7,6,0,3,4,8,9,
                3,0,8,7,0,0,0,2,5,
                0,4,0,0,0,0,0,0,6,
        };

        return new VelocityTemplateEngine().render(new ModelAndView(model, Path.Template.LAYOUT));
    };

    private static void post(String uri, String img) throws Exception{
        URL url = new URL(uri);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json; utf-8");
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoOutput(true);
        try(OutputStream os = connection.getOutputStream()){
            byte[] input = img.getBytes("utf-8");
            os.write(input, 0, input.length);
        }
        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response.toString());
        }
    }
}
