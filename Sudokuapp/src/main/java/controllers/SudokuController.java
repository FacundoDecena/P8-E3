package controllers;

import com.google.gson.Gson;
import models.Blackboard;
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
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class SudokuController {
    public static Route home = (Request req, Response res) ->{
        HashMap<String, Object> model = new HashMap<String, Object>();
        model.put("opcion", "home");
        model.put("template", Path.Template.HOME);
        return new VelocityTemplateEngine().render(new ModelAndView(model, Path.Template.LAYOUT));
    };
    
    public static Route init = (Request req, Response res) ->{
        HashMap<String, Object> model = new HashMap<String, Object>();
        int[] bb = {
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
        model.put("opcion", "init");
        model.put("arreglo",bb);
        model.put("template", Path.Template.HOME);
        return new VelocityTemplateEngine().render(new ModelAndView(model, Path.Template.LAYOUT));
    };

    public static Route searchSolution = (Request req, Response res) ->{
        HashMap<String, Object> model = new HashMap<String, Object>();
        //  int[] bb = req.queryParams("grid");
        int[] bb = {
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

        Blackboard grid = new Blackboard();
        
        grid.setGrid(bb);
        System.out.println("Starting sudoku");
        post(Path.Web.getBLACKBOARD()+Path.Web.getUPDATE(),grid);
        System.out.println(grid);
        while (isNotComplete(grid.grid)) {
            grid = get(Path.Web.getBLACKBOARD()+Path.Web.getREAD());
        }
        System.out.println(grid);
        model.put("grid",grid.grid);
        model.put("opcion", "searchSolution");
        model.put("template", Path.Template.HOME);
        return new VelocityTemplateEngine().render(new ModelAndView(model, Path.Template.LAYOUT));
    };

    private static boolean isNotComplete(final int[] array) {

        boolean result = false;

        for(int i : array){
            if(i == 0){
                result = true;
                break;
            }
        }

        return result;
    }

    private static void post(String uri, Blackboard grid) throws Exception{
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

    private static Blackboard get(String uri) throws Exception {
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
