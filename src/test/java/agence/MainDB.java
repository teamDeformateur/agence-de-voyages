/**
 * 
 */
package agence;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import agence.dao.AdresseDao;
import agence.dao.AdresseDaoSql;
import agence.dao.AeroportDao;
import agence.dao.AeroportDaoSql;
import agence.dao.ClientDao;
import agence.dao.ClientMoralDaoSql;
import agence.dao.ClientPhysiqueDaoSql;
import agence.dao.CompagnieAerienneDao;
import agence.dao.CompagnieAerienneDaoSql;
import agence.dao.CompagnieAerienneVolDao;
import agence.dao.CompagnieAerienneVolDaoSql;
import agence.dao.Dao;
import agence.dao.DaoSQL;
import agence.dao.EscaleDao;
import agence.dao.EscaleDaoSql;
import agence.dao.LoginDao;
import agence.dao.LoginDaoSql;
import agence.dao.PassagerDao;
import agence.dao.PassagerDaoSql;
import agence.dao.ReservationDao;
import agence.dao.ReservationDaoSql;
import agence.dao.VilleAeroportDao;
import agence.dao.VilleAeroportDaoSql;
import agence.dao.VilleDao;
import agence.dao.VilleDaoSQL;
import agence.dao.VolDao;
import agence.dao.VolDaoSql;
import agence.model.Adresse;
import agence.model.Aeroport;
import agence.model.Client;
import agence.model.CompagnieAerienne;
import agence.model.CompagnieAerienneVol;
import agence.model.Escale;
import agence.model.Login;
import agence.model.Passager;
import agence.model.Reservation;
import agence.model.Ville;
import agence.model.VilleAeroport;
import agence.model.Vol;

/**
 * Classe principale de test de récupération de données via la BDD
 * 
 * @author Seme
 */
public class MainDB
{

    /**
     * Méthode principale qui va faire appel à toutes les méthodes de
     * récupération
     * de mes DAO SQL :
     * - findAll
     * - findById
     * 
     * @param args
     *            Arguments passés en paramètres d'entrée
     */
    public static void main(String[] args)
    {
        Connection connexion = seConnecter();

        if (connexion != null)
        {

            // J'instancie le dao SQL des adresses
            AdresseDao adresseDao = new AdresseDaoSql(connexion);
            // J'appelle la méthode findAll pour récupérer toutes les adresses
            // stockées en BDD
            List<Adresse> listeAdresses = adresseDao.findAll();
            Adresse adresse = adresseDao.findById(1);
            afficherTestEtResultat("Liste des adresses", listeAdresses);
            afficherTestEtResultat("Une seule adresse", adresse);

            AeroportDao aeroportDao = new AeroportDaoSql(connexion);
            List<Aeroport> listeAeroports = aeroportDao.findAll();
            Aeroport aeroport = aeroportDao.findById(1);
            afficherTestEtResultat("Liste des aéroports", listeAeroports);
            afficherTestEtResultat("Un seul aéroport", aeroport);

            ClientDao clientDao = new ClientMoralDaoSql(connexion);
            List<Client> listeClientsMoraux = clientDao.findAll();
            Client clientMoral = clientDao.findById(30);
            afficherTestEtResultat("Liste des clients personnes morales",
                    listeClientsMoraux);
            afficherTestEtResultat("Un client personne morale", clientMoral);

            clientDao = new ClientPhysiqueDaoSql(connexion);
            List<Client> listeClientsPhysiques = clientDao.findAll();
            afficherTestEtResultat("Liste des clients personne physique",
                    listeClientsPhysiques);
            Client clientPhysique = clientDao.findById(10);
            afficherTestEtResultat("Un seul client personne physique",
                    clientPhysique);
            // Je cherche une adresse en fonction d'un client
            adresse = adresseDao.findByClient(clientPhysique);
            System.out.println("Liaison client-adresse : " + clientPhysique);

            CompagnieAerienneDao compagnieAerienneDao = new CompagnieAerienneDaoSql(
                    connexion);
            List<CompagnieAerienne> listeCompagniesAeriennes = compagnieAerienneDao
                    .findAll();
            afficherTestEtResultat("Liste des compagnies aériennes",
                    listeCompagniesAeriennes);
            CompagnieAerienne compagnieAerienne = compagnieAerienneDao
                    .findById(2);
            afficherTestEtResultat("Une seule compagnie aérienne",
                    compagnieAerienne);

            CompagnieAerienneVolDao compagnieAerienneVolDao = new CompagnieAerienneVolDaoSql(
                    connexion);
            List<CompagnieAerienneVol> listeCompagniesAeriennesVol = compagnieAerienneVolDao
                    .findAll();
            afficherTestEtResultat("Liste des liens compagnie-vol",
                    listeCompagniesAeriennesVol);
            CompagnieAerienneVol compagnieAerienneVol = compagnieAerienneVolDao
                    .findById(2);
            afficherTestEtResultat("Un lien compagnie-vol",
                    compagnieAerienneVol);

            EscaleDao escaleDao = new EscaleDaoSql(connexion);
            List<Escale> listeEscales = escaleDao.findAll();
            afficherTestEtResultat("Liste des escales", listeEscales);
            Escale escale = escaleDao.findById(30);
            afficherTestEtResultat("Une seule escale", escale);

            LoginDao loginDao = new LoginDaoSql(connexion);
            List<Login> listeLogins = loginDao.findAll();
            afficherTestEtResultat("Liste des logins", listeLogins);
            Login login = loginDao.findById(2);
            afficherTestEtResultat("Un seul login", login);

            // J'instancie le dao SQL de l'objet métier à récupérer
            PassagerDao passagerDao = new PassagerDaoSql(connexion);
            // J'appelle la méthode findAll pour récupérer tous les BO de ce
            // type de la BDD
            List<Passager> listePassagers = passagerDao.findAll();
            afficherTestEtResultat("Liste des passagers", listePassagers);
            Passager passager = passagerDao.findById(1);
            afficherTestEtResultat("Un seul passager", passager);

            // J'instancie le dao SQL de l'objet métier à récupérer
            ReservationDao reservationDao = new ReservationDaoSql(connexion);
            // J'appelle la méthode findAll pour récupérer tous les BO de ce
            // type de la BDD
            List<Reservation> listeReservations = reservationDao.findAll();
            afficherTestEtResultat("Liste des réservations", listeReservations);
            Reservation reservation = reservationDao.findById(10);
            afficherTestEtResultat("Une seule réservation", reservation);
            listeReservations = reservationDao.findByPassager(passager);

            VilleAeroportDao villeAeroportDao = new VilleAeroportDaoSql(
                    connexion);
            List<VilleAeroport> listeVilleAeroports = villeAeroportDao
                    .findAll();
            afficherTestEtResultat("Liste des liens ville-aéroport",
                    listeVilleAeroports);
            VilleAeroport villeAeroport = villeAeroportDao.findById(3);
            afficherTestEtResultat("Une seul lien ville-aéroport",
                    villeAeroport);

            VilleDao villeDao = new VilleDaoSQL(connexion);
            List<Ville> listeVilles = villeDao.findAll();
            afficherTestEtResultat("Liste des villes", listeVilles);
            Ville ville = villeDao.findById(2);
            afficherTestEtResultat("Une seule ville", ville);

            VolDao volDao = new VolDaoSql(connexion);
            List<Vol> listeVols = volDao.findAll();
            afficherTestEtResultat("Liste des vols", listeVols);
            Vol vol = volDao.findById(1);
            afficherTestEtResultat("Un seul vol", vol);

            libererResultatsDaoSql(new Dao[]
            { adresseDao, clientDao, aeroportDao, compagnieAerienneDao,
                    compagnieAerienneVolDao, escaleDao, loginDao, passagerDao,
                    reservationDao, villeAeroportDao, villeDao, volDao });

            seDeconnecter(connexion);
        }
    }

    /**
     * Se déconnecte de la BDD
     * 
     * @param connexion
     */
    private static void seDeconnecter(Connection connexion)
    {
        try
        {
            connexion.close();
        }
        catch (SQLException e)
        {
            System.err.println(
                    "Problème lors de la fermeture de la connexion à la BDD.");
            e.printStackTrace();
        }
    }

    /**
     * Se connecte à la BDD
     * 
     * @return Une connexion à la BDD
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private static Connection seConnecter()
    {
        Connection connexion = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            // 2. Créer la connexion à la base (on instancie l'objet connexion)
            connexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/agence", "user", "password");
        }
        catch (ClassNotFoundException e)
        {
            System.err.println("Impossible de charger le pilote JDBC.");
            System.err.println(e.toString());
        }
        catch (SQLException e)
        {
            System.err.println("Erreur lors de la connexion à la BDD.");
            System.err.println(e.toString());
        }

        return connexion;
    }

    /**
     * Ferme les connexions ouvertes par les DAO passés en paramètres
     * 
     * @param listeDao
     */
    private static void libererResultatsDaoSql(Dao<?, ?>[] listeDao)
    {
        for (Dao<?, ?> dao : listeDao)
        {
            ((DaoSQL) dao).libererResultats();
        }
    }

    /**
     * @param <T>
     * @param test
     * @param t
     */
    private static <T> void afficherTestEtResultat(String test, T t)
    {
        afficherTest(test);
        afficherResultat(t);
    }

    /**
     * @param <T>
     * @param test
     * @param liste
     */
    private static <T> void afficherTestEtResultat(String test, List<T> liste)
    {
        afficherTest(test);
        afficherListe(liste);

    }

    /**
     * @param <T>
     * @param adresse
     */
    private static <T> void afficherResultat(T objet)
    {
        System.out.println(objet.toString());
    }

    /**
     * @param string
     */
    private static void afficherTest(String string)
    {
        String fmtEntete = "|";
        String separateur = "+-----------------------------------------------------------------------------------------------------------+\n";
        // String separateur = "+----------+\n";
        int avant = (separateur.length() - 2 - string.length()) / 2;
        int apres = separateur.length() - avant - 4;
        for (int i = 0; i < avant; i++)
        {
            fmtEntete += " ";
        }
        fmtEntete += "%1$-" + Integer.toString(apres) + "s |%n";

        PrintStream console = System.out;
        console.printf("%s", separateur);
        console.printf(fmtEntete, string);
        console.printf("%s", separateur);

    }

    /**
     * @param <T>
     * @param listeAdresses
     */
    private static <T> void afficherListe(List<T> liste)
    {
        for (T t : liste)
        {
            System.out.println(t.toString());
        }

    }

}
