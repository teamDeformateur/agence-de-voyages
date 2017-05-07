/**
 * 
 */
package agence;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

import agence.dao.AdresseDao;
import agence.dao.AdresseDaoSql;
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
import agence.model.Adresse;
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
                    /*
                     * Afficher vols
                     */
                    volDao = new VolDaoSql(connexion);
                    listeBOs = volDao.findAll();
                    console.displayVols(listeBOs);
                    break;
                case 11:
                    /*
                     * Modifier vol
                     */
                    break;
                case 12:
                    /*
                     * Créer vol
                     */
                    break;
                case 13:
                    /*
                     * Supprimer vol
                     */
                    break;
                case 2:
                {
                    /*
                     * Afficher clients
                     */
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
                    /*
                     * Modifier client
                     */
                    boolean annuler = false;
                    // boucle de saisie
                    do
                    {
                        console.displayMenuClient();
                        // Entrée
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
                    /*
                     * Créer client
                     */
                    // Il faut créer l'adresse avant de créer le client
                    Adresse nouvelleAdresse = interfaceCreerAdresse();
                    adresseDao = new AdresseDaoSql(connexion);
                    adresseDao.create(nouvelleAdresse);
                    // L'utilisateur crée le client
                    Client nouveauClient = interfaceCreerClient(
                            nouvelleAdresse);
                    // Si personne morale à insérer
                    if (nouveauClient instanceof ClientMoral)
                    {
                        clientDao = new ClientMoralDaoSql(connexion);
                        clientDao.create(nouveauClient);
                    }
                    // sinon, personne physique
                    else
                    {
                        clientDao = new ClientPhysiqueDaoSql(connexion);
                        clientDao.create(nouveauClient);
                    }
                    System.out.println("Client inséré.");
                    break;
                case 23:
                    /*
                     * Supprimer client
                     */
                    boolean annuler = false;
                    // boucle de saisie
                    do
                    {
                        console.displayMenuClient();
                        // Entrée
                        int choixClient = in.nextInt();
                        switch (choixClient)
                        {
                            case 1:
                                clientDao = new ClientMoralDaoSql(connexion);
                                interfaceSupprimerClient(clientDao);
                                annuler = true;
                                break;
                            case 2:
                                clientDao = new ClientPhysiqueDaoSql(connexion);
                                interfaceSupprimerClient(clientDao);
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
                case 3:
                    /*
                     * Afficher réservations
                     */
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
                    /*
                     * Afficher compagnies aériennes
                     */
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
                    /*
                     * Quitter l'application
                     */
                    System.out.println("Vous quittez l'application.");
                    quitter = true;
                    break;
            }
            // réinit. du scanner
            in.reset();
        }
        // Tant que l'utilisateur ne demande pas de quitter
        while (!quitter);
        // fermeture du scanner
        in.close();
    }

    /**
     * Gère les interactions entre l'utilisateur et le programme afin du
     * supprimer un client
     * 
     * @param clientDao DAO Client
     */
    private static void interfaceSupprimerClient(ClientDao clientDao)
    {
        System.out.println(
                "Veuillez saisir l'identifiant du client à supprimer :");
        Scanner in = new Scanner(System.in);
        int numCli = in.nextInt();
        // on vérifie que le client existe bel et bien
        Client clientASupprimer = clientDao.findById(numCli);
        if (clientASupprimer != null)
        // suppression du client
        {
            clientDao.delete(clientASupprimer);
            System.out.println("Client " + clientASupprimer.getNom() + " supprimé.");
        }
        else
        {
            System.err.println("Ce client n'existe pas.");
        }

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
     * Gére l'interface entre l'utilisateur et le programme afin de créer un
     * Client
     * 
     * @param adresse
     *            L'adresse du client
     * @return Le client créé par l'utilisateur
     */
    private static Client interfaceCreerClient(Adresse adresse)
    {
        // Le client a insérer
        Client clientAInserer;
        // init. des données
        String nom = null;
        String prenom = null;
        Long siret = null;
        String numTel = null;
        String numFax = null;
        String email = null;
        // saisie du nom
        nom = saisirDonnee("nom");
        // saisie du prenom
        prenom = saisirDonnee("prenom (laissez vide si personne morale)");
        try
        {
            // saisie du siret
            siret = Long.parseLong(
                    saisirDonnee("SIRET (laissez vide si personne physique)"));
        }
        catch (NumberFormatException e)
        {

        }
        // saisie du numTel
        numTel = saisirDonnee("numéro de téléphone");
        // saisie du numFax
        numFax = saisirDonnee("numéro de fax");
        // saisie de l'adresse email
        email = saisirDonnee("courriel");
        // si personne physique
        if (prenom != null)
        {
            clientAInserer = new ClientPhysique();
            ((ClientPhysique) clientAInserer).setPrenom(prenom);
        }
        else
        {
            clientAInserer = new ClientMoral();
            ((ClientMoral) clientAInserer).setSiret(siret);
        }
        // maj des infos génériques du client
        clientAInserer.setNom(nom);
        clientAInserer.setNumeroTel(numTel);
        clientAInserer.setNumeroFax(numFax);
        clientAInserer.setEmail(email);
        clientAInserer.setAdresse(adresse);
        // je retourne le client à insérer
        return clientAInserer;
    }

    /**
     * Interface entre l'utilisateur et le programme pour créer une adresse
     * 
     * @return L'adresse créée par l'utilisateur
     */
    private static Adresse interfaceCreerAdresse()
    {
        // L'adresse à créer
        Adresse adresseAInserer;
        // init. des données
        String adresse = null;
        String codePostal = null;
        String ville = null;
        String pays = null;
        // saisie de l'adresse
        adresse = saisirDonnee("adresse (numéro et rue)");
        // saisie du code postal
        codePostal = saisirDonnee("code postal");
        // saisie de la ville
        ville = saisirDonnee("ville");
        // saisie du pays
        pays = saisirDonnee("pays");
        adresseAInserer = new Adresse();
        // maj des infos de l'adresse
        adresseAInserer.setAdresse(adresse);
        adresseAInserer.setCodePostal(codePostal);
        adresseAInserer.setVille(ville);
        adresseAInserer.setPays(pays);
        // je retourne l'adresse à insérer
        return adresseAInserer;
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
     * Demande une saisie d'une donnée à l'utilisateur
     * 
     * @param nomDuChamp
     *            Donnée à renseigner
     * @return La valeur de la donnée
     */
    private static String saisirDonnee(String nomDuChamp)
    {
        System.out.printf("Saisir %s : ", nomDuChamp);
        String saisie = ConsoleView.lireConsole();
        if (saisie.length() > 0)
        {
            return saisie;
        }
        else
        {
            return null;
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
