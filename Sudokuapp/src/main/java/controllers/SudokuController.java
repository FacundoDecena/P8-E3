package controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.template.velocity.VelocityTemplateEngine;
import util.Path;

import java.util.HashMap;

public class SudokuController {
    public static Route home = (Request req, Response res) ->{
        HashMap<String, Object> model = new HashMap<String, Object>();

        return new VelocityTemplateEngine().render(new ModelAndView(model, Path.Template.LAYOUT));
    };
}
