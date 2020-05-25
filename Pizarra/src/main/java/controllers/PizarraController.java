package controllers;

import com.google.gson.Gson;
import dao.PizarraDAO;
import models.Pizarra;
import spark.Route;

public class PizarraController {

    private static PizarraDAO pDAO = new PizarraDAO();

    public static Route update = (request,response)->{
        String json = request.body();
        Gson gson = new Gson();
        Pizarra nuevaPizarra = gson.fromJson(json, Pizarra.class);
        pDAO.update(nuevaPizarra);
        return "200";
    };

    public static Route read = (request,response)->{
        Gson gson = new Gson();
        return gson.toJson(pDAO.read());
    };

}
