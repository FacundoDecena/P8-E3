package models;

import lombok.Data;

@Data
public class Pizarra {
    public int[] grid;

    public Pizarra(){
        this.grid = new int[81];
    }
}
