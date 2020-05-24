package models;

import lombok.Data;

@Data
public class Blackboard {
    public int[] grid;

    public Blackboard(){
        this.grid = new int[81];
    }
}
