package dao;

import models.Pizarra;

public class PizarraDAO {

    private static Pizarra pizarra = new Pizarra();

    public void update(Pizarra pizarra){
        this.pizarra = pizarra;
    }

    public Pizarra read(){
        return this.pizarra;
    }
}
