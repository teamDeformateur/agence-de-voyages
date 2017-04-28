package agence;

import agence.dao.AeroportDaoSQL;
import agence.dao.EscaleDaoSql;
import agence.dao.VolDaoSql;

public class Main
{

    public static void main(String[] args)
    {
        AeroportDaoSQL aeroport = new AeroportDaoSQL();
        aeroport.findAll();
        aeroport.findById(1);
        aeroport.fermetureConnexion();

        EscaleDaoSql escale = new EscaleDaoSql();
        escale.findAll();
        escale.findById(30);
        aeroport.fermetureConnexion();

        VolDaoSql vol = new VolDaoSql();
        vol.findAll();
        vol.findById(1);
        vol.fermetureConnexion();

        vol.toString();
        aeroport.toString();

    }

}
