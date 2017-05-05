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
import agence.model.Client;
import agence.model.ClientMoral;
import agence.model.ClientPhysique;
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
                {
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
                }
                    break;
                case 21:
                {
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
                                interfaceModifierClient(clientDao);
                                annuler = true;
                                break;
                            case 2:
                                clientDao = new ClientPhysiqueDaoSql(connexion);
                                interfaceModifierClient(clientDao);
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
                }
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
     * @param clientDao
     */
    private static void interfaceModifierClient(ClientDao clientDao)
    {
        Scanner in;
        System.out.println(
                "Veuillez saisir l'identifiant du client que vous souhaitez modifier : ");
        in = new Scanner(System.in);
        String idCli = in.next();
        Client clientAModifier = clientDao.findById(Integer.parseInt(idCli));
        if (clientAModifier != null)
        {
            String nom = clientAModifier.getNom();
            String prenom = null;
            Long siret = null;
            // si c'est un client personne physique
            if (clientAModifier instanceof ClientPhysique)
            {
                prenom = ((ClientPhysique) clientAModifier).getPrenom();
            }
            else
            {
                siret = ((ClientMoral) clientAModifier).getSiret();
            }
            String numTel = clientAModifier.getNumeroTel();
            String numFax = clientAModifier.getNumeroFax();
            String courriel = clientAModifier.getEmail();

            /*
             * Changement du nom
             */
            clientAModifier.setNom(saisirChangement("nom", nom));

            /*
             * Changement du prénom ou du SIRET
             */
            if (prenom != null)
            {
                ((ClientPhysique) clientAModifier)
                        .setPrenom(saisirChangement("prénom", prenom));
            }
            else
            {
                ((ClientMoral) clientAModifier).setSiret(
                        Long.parseLong(saisirChangement("numéro SIRET",
                                Long.toString(siret))));
            }

            /*
             * Changement du numéro de téléphone
             */
            clientAModifier.setNumeroTel(
                    saisirChangement("numéro de téléphone", numTel));

            /*
             * Changement du numéro de fax
             */
            clientAModifier.setNumeroFax(
                    saisirChangement("numéro de télécopie", numFax));

            /*
             * Changement de l'adresse email
             */
            clientAModifier.setEmail(saisirChangement("courriel", courriel));

            // une fois qu'on a récupéré toutes les modifications,
            // on lance la fonction du DAO qui va mettre à jour
            clientDao.update(clientAModifier);
            System.out.println("Client modifié.");
            console.waitNext();
        }
        else
        {
            System.err.println("Aucun client avec cet identifiant.");
        }
    }

    /**
     * @param nomDuChamp
     * @param champ
     * @return
     */
    private static String saisirChangement(String nomDuChamp, String champ)
    {
        System.out.printf("Nouveau %s [%s] : ", nomDuChamp, champ);
        String saisie = ConsoleView.lireConsole();
        if (saisie.length() > 0 && !saisie.equals(champ))
        {
            return saisie;
        }
        else
        {
            return champ;
        }
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
