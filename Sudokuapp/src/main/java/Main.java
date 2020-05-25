import controllers.SudokuController;
import util.Path;

import static spark.Spark.get;

public class Main {
    public static void main(String[] args) {
        //staticFileLocation("/css");
        get(Path.Web.getHOME(), SudokuController.home);
        get(Path.Web.getINIT(), SudokuController.init);
        get(Path.Web.getSOLVE(), SudokuController.searchSolution);
        
        
    }
}
