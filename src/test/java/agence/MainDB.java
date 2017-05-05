/**
 * 
 */
package agence;

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
import agence.views.ConsoleView;

/**
 * Classe principale de test de récupération de données via la BDD
 * 
 * @author Seme
 */
public class MainDB
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
        connexion = seConnecter();

        if (connexion != null)
        {
            // J'instancie le dao SQL des adresses
            AdresseDao adresseDao = new AdresseDaoSql(connexion);
            // J'appelle la méthode findAll pour récupérer toutes les adresses
            // stockées en BDD
            List<Adresse> listeAdresses = adresseDao.findAll();
            Adresse adresse = adresseDao.findById(1);
            console.afficherTestEtResultat("Liste des adresses", listeAdresses);
            console.afficherTestEtResultat("Une seule adresse", adresse);

            AeroportDao aeroportDao = new AeroportDaoSql(connexion);
            List<Aeroport> listeAeroports = aeroportDao.findAll();
            Aeroport aeroport = aeroportDao.findById(1);
            console.afficherTestEtResultat("Liste des aéroports",
                    listeAeroports);
            console.afficherTestEtResultat("Un seul aéroport", aeroport);

            ClientDao clientDao = new ClientMoralDaoSql(connexion);
            List<Client> listeClientsMoraux = clientDao.findAll();
            Client clientMoral = clientDao.findById(30);
            console.afficherTestEtResultat(
                    "Liste des clients personnes morales", listeClientsMoraux);
            console.afficherTestEtResultat("Un client personne morale",
                    clientMoral);

            clientDao = new ClientPhysiqueDaoSql(connexion);
            List<Client> listeClientsPhysiques = clientDao.findAll();
            console.afficherTestEtResultat(
                    "Liste des clients personne physique",
                    listeClientsPhysiques);
            Client clientPhysique = clientDao.findById(10);
            console.afficherTestEtResultat("Un seul client personne physique",
                    clientPhysique);
            // Je cherche une adresse en fonction d'un client
            adresse = adresseDao.findByClient(clientPhysique);
            System.out.println("Liaison client-adresse : " + clientPhysique);

            CompagnieAerienneDao compagnieAerienneDao = new CompagnieAerienneDaoSql(
                    connexion);
            List<CompagnieAerienne> listeCompagniesAeriennes = compagnieAerienneDao
                    .findAll();
            console.afficherTestEtResultat("Liste des compagnies aériennes",
                    listeCompagniesAeriennes);
            CompagnieAerienne compagnieAerienne = compagnieAerienneDao
                    .findById(2);
            console.afficherTestEtResultat("Une seule compagnie aérienne",
                    compagnieAerienne);

            CompagnieAerienneVolDao compagnieAerienneVolDao = new CompagnieAerienneVolDaoSql(
                    connexion);
            List<CompagnieAerienneVol> listeCompagniesAeriennesVol = compagnieAerienneVolDao
                    .findAll();
            console.afficherTestEtResultat("Liste des liens compagnie-vol",
                    listeCompagniesAeriennesVol);
            CompagnieAerienneVol compagnieAerienneVol = compagnieAerienneVolDao
                    .findById(2);
            console.afficherTestEtResultat("Un lien compagnie-vol",
                    compagnieAerienneVol);

            EscaleDao escaleDao = new EscaleDaoSql(connexion);
            List<Escale> listeEscales = escaleDao.findAll();
            console.afficherTestEtResultat("Liste des escales", listeEscales);
            Escale escale = escaleDao.findById(30);
            console.afficherTestEtResultat("Une seule escale", escale);

            LoginDao loginDao = new LoginDaoSql(connexion);
            List<Login> listeLogins = loginDao.findAll();
            console.afficherTestEtResultat("Liste des logins", listeLogins);
            Login login = loginDao.findById(2);
            console.afficherTestEtResultat("Un seul login", login);

            // J'instancie le dao SQL de l'objet métier à récupérer
            PassagerDao passagerDao = new PassagerDaoSql(connexion);
            // J'appelle la méthode findAll pour récupérer tous les BO de ce
            // type de la BDD
            List<Passager> listePassagers = passagerDao.findAll();
            console.afficherTestEtResultat("Liste des passagers",
                    listePassagers);
            Passager passager = passagerDao.findById(1);
            console.afficherTestEtResultat("Un seul passager", passager);

            // J'instancie le dao SQL de l'objet métier à récupérer
            ReservationDao reservationDao = new ReservationDaoSql(connexion);
            // J'appelle la méthode findAll pour récupérer tous les BO de ce
            // type de la BDD
            List<Reservation> listeReservations = reservationDao.findAll();
            console.afficherTestEtResultat("Liste des réservations",
                    listeReservations);
            Reservation reservation = reservationDao.findById(10);
            console.afficherTestEtResultat("Une seule réservation",
                    reservation);
            listeReservations = reservationDao.findByPassager(passager);

            VilleAeroportDao villeAeroportDao = new VilleAeroportDaoSql(
                    connexion);
            List<VilleAeroport> listeVilleAeroports = villeAeroportDao
                    .findAll();
            console.afficherTestEtResultat("Liste des liens ville-aéroport",
                    listeVilleAeroports);
            VilleAeroport villeAeroport = villeAeroportDao.findById(3);
            console.afficherTestEtResultat("Une seul lien ville-aéroport",
                    villeAeroport);

            VilleDao villeDao = new VilleDaoSQL(connexion);
            List<Ville> listeVilles = villeDao.findAll();
            console.afficherTestEtResultat("Liste des villes", listeVilles);
            Ville ville = villeDao.findById(2);
            console.afficherTestEtResultat("Une seule ville", ville);

            VolDao volDao = new VolDaoSql(connexion);
            List<Vol> listeVols = volDao.findAll();
            console.afficherTestEtResultat("Liste des vols", listeVols);
            Vol vol = volDao.findById(1);
            console.afficherTestEtResultat("Un seul vol", vol);

            console.afficherTestEtResultat("Fin des tests", null);

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
    static void seDeconnecter(Connection connexion)
    {
        try
        {
            // Fermeture de la connexion
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
    static Connection seConnecter()
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

}
