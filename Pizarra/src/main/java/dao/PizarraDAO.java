package dao;

import models.Pizarra;

public class PizarraDAO {

    private static Pizarra pizarra = new Pizarra();

    public void update(Pizarra pizarra){
        PizarraDAO.pizarra = pizarra;
    }

    public Pizarra read(){
        return PizarraDAO.pizarra;
    }
}
