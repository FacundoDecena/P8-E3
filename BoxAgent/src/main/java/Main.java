import models.Blackboard;
import utils.*;

public class Main {

    public static void main(String[]args) throws Exception {
        while(true) {
            Blackboard blackboard = HTTPMethods.get(Path.READ);
            if(!isComplete(blackboard)) {
                Blackboard newBlackboard = searchSolution(blackboard);
                HTTPMethods.post(Path.UPDATE, newBlackboard);
            }
        }
    }

    private static Blackboard searchSolution(Blackboard blackboard) {
        //Logica del agente por cuadriculas
        return blackboard;
    }

    private static boolean isComplete(Blackboard blackboard){
        int[] grid = blackboard.getGrid();
        for(int i:grid){
            if(grid[i] == 0){
                return false;
            }
        }
        return true;
    }
}

