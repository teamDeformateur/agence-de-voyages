/**
 * 
 */
package agence;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

import agence.dao.AdresseDao;
import agence.dao.AeroportDao;
import agence.dao.ClientDao;
import agence.dao.ClientMoralDaoSql;
import agence.dao.ClientPhysiqueDaoSql;
import agence.dao.CompagnieAerienneDao;
import agence.dao.CompagnieAerienneDaoSql;
import agence.dao.CompagnieAerienneVolDao;
import agence.dao.EscaleDao;
import agence.dao.LoginDao;
import agence.dao.PassagerDao;
import agence.dao.ReservationDao;
import agence.dao.ReservationDaoSql;
import agence.dao.VilleAeroportDao;
import agence.dao.VilleDao;
import agence.dao.VolDao;
import agence.dao.VolDaoSql;
import agence.views.ConsoleView;

/**
 * @author Seme
 */
public class MainCRUD
{
    /*
     * Liste des vues
     */
    /**
     * Vue Console
     */
    public static ConsoleView console = new ConsoleView();

    /**
     * Connexion à la BDD
     */
    private static Connection connexion;

    public static void run()
    {
        /*
         * DAO
         */
        AdresseDao adresseDao;
        AeroportDao aeroportDao;
        ClientDao clientDao;
        CompagnieAerienneDao compagnieAerienneDao;
        CompagnieAerienneVolDao compagnieAerienneVolDao;
        EscaleDao escaleDao;
        ReservationDao reservationDao;
        LoginDao loginDao;
        PassagerDao passagerDao;
        VilleAeroportDao villeAeroportDao;
        VilleDao villeDao;
        VolDao volDao;
        /*
         * Interface graphique
         */
        boolean quitter = false;
        Scanner in = null;
        List<?> listeBOs;
        // boucle de saisie
        do
        {
            // Sortie
            console.displayMenu();
            // Entrée
            in = new Scanner(System.in);
            int choix = in.nextInt();
            switch (choix)
            {
                case 1:
                    volDao = new VolDaoSql(connexion);
                    listeBOs = volDao.findAll();
                    console.displayVols(listeBOs);
                    break;
                case 11:
                    break;
                case 12:
                    break;
                case 13:
                    break;
                case 2:
                    boolean annuler = false;
                    // boucle de saisie
                    do
                    {
                        console.displayMenuClient();
                        // Entrée
                        // in = new Scanner(System.in);
                        int choixClient = in.nextInt();
                        switch (choixClient)
                        {
                            case 1:
                                clientDao = new ClientMoralDaoSql(connexion);
                                listeBOs = clientDao.findAll();
                                console.displayClientsMoraux(listeBOs);
                                annuler = true;
                                break;
                            case 2:
                                clientDao = new ClientPhysiqueDaoSql(connexion);
                                listeBOs = clientDao.findAll();
                                console.displayClientsPhysiques(listeBOs);
                                annuler = true;
                                break;
                            case 0:
                                System.out.println("Annulation...");
                                annuler = true;
                                break;
                        }
                        in.reset();
                    }
                    while (!annuler);
                    break;
                case 21:
                    break;
                case 22:
                    break;
                case 23:
                    break;
                case 3:
                    reservationDao = new ReservationDaoSql(connexion);
                    listeBOs = reservationDao.findAll();
                    console.displayVols(listeBOs);
                    break;
                case 31:
                    break;
                case 32:
                    break;
                case 33:
                    break;
                case 4:
                    compagnieAerienneDao = new CompagnieAerienneDaoSql(
                            connexion);
                    listeBOs = compagnieAerienneDao.findAll();
                    console.displayCompagnies(listeBOs);
                    break;
                case 41:
                    break;
                case 42:
                    break;
                case 43:
                    break;
                case 0:
                    System.out.println("Vous quittez l'application.");
                    quitter = true;
                    break;
            }
            in.reset();
        }
        while (!quitter);
        in.close();
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        connexion = MainDB.seConnecter();

        if (connexion != null)
        {
            run();

            MainDB.seDeconnecter(connexion);
        }
    }

}
