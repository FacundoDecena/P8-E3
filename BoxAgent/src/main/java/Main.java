import models.Blackboard;
import utils.*;

import java.util.Random;

public class Main {

    private static int[] gridSolution = {
            9,8,4,5,7,6,2,1,3,
            5,1,3,4,8,2,9,6,7,
            7,2,6,1,3,9,5,4,8,
            6,3,1,9,4,7,8,5,2,
            4,9,5,2,6,8,3,7,1,
            8,7,2,3,5,1,6,9,4,
            2,5,7,6,1,3,4,8,9,
            3,6,8,7,9,4,1,2,5,
            1,4,9,8,2,5,7,3,6
    };

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
        int index = getRandom();
        int[] grid = blackboard.getGrid();
        if(grid[index] == 0)
            grid[index] = gridSolution[index];
        blackboard.setGrid(grid);
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

    private static int getRandom(){
        Random random = new Random();
        int low = 0;
        int high = 81;
        return random.nextInt(high-low) + low;
    }
}

