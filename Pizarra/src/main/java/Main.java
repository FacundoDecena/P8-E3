import controllers.PizarraController;
import static spark.Spark.*;
import utils.Path;

public class Main {
    public static void main(String[] args){
        port(8888);
        post(Path.UPDATE, PizarraController.update);
        get(Path.READ, PizarraController.read);
    }
}
