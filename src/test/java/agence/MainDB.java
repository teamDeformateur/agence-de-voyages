/**
 * 
 */
package agence;

import java.util.List;

import agence.dao.AdresseDao;
import agence.dao.AdresseDaoSql;
import agence.dao.AeroportDao;
import agence.dao.AeroportDaoSQL;
import agence.dao.ClientDaoSql;
import agence.dao.ClientMoralDaoSql;
import agence.dao.ClientPhysiqueDaoSql;
import agence.dao.CompagnieAerienneDao;
import agence.dao.CompagnieAerienneDaoSQL;
import agence.dao.CompagnieAerienneVolDao;
import agence.dao.CompagnieAerienneVolDaoSQL;
import agence.dao.DaoSQL;
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
        // J'instancie le dao SQL des adresses
        AdresseDao adresseDao = new AdresseDaoSql();
        // J'appelle la méthode findAll pour récupérer toutes les adresses
        // stockées en BDD
        List<Adresse> listeAdresses = adresseDao.findAll();
        Adresse adresse = adresseDao.findById(1);

        AeroportDaoSQL aeroportDao = new AeroportDaoSQL();
        List<Aeroport> listeAeroports = aeroportDao.findAll();
        Aeroport aeroport = aeroportDao.findById(1);
        aeroportDao.fermetureConnexion();
        
        ClientDaoSql clientDaoSql = new ClientMoralDaoSql();
        List<Client> listeClientsMoraux = clientDaoSql.findAll();
        Client clientMoral = clientDaoSql.findById(30);
        
        clientDaoSql = new ClientPhysiqueDaoSql();
        List<Client> listeClientsPhysiques = clientDaoSql.findAll();
        Client clientPhysique = clientDaoSql.findById(10);
        
        CompagnieAerienneDao compagnieAerienneDao = new CompagnieAerienneDaoSQL();
        List<CompagnieAerienne> listeCompagniesAeriennes = compagnieAerienneDao.findAll();
        CompagnieAerienne compagnieAerienne = compagnieAerienneDao.findById(2);
        
        CompagnieAerienneVolDao compagnieAerienneVolDao = new CompagnieAerienneVolDaoSQL();
        List<CompagnieAerienneVol> listeCompagniesAeriennesVol = compagnieAerienneVolDao.findAll();
        CompagnieAerienneVol compagnieAerienneVol = compagnieAerienneVolDao.findById(2);
        
        EscaleDaoSql escaleDao = new EscaleDaoSql();
        List<Escale> listeEscales = escaleDao.findAll();
        Escale escale = escaleDao.findById(30);
        escaleDao.fermetureConnexion();
        
        LoginDao loginDaoSql = new LoginDaoSql();
        List<Login> listeLogins = loginDaoSql.findAll();
        Login login = loginDaoSql.findById(2);

        // J'instancie le dao SQL de l'objet métier à récupérer
        PassagerDao passagerDao = new PassagerDaoSql();
        // J'appelle la méthode findAll pour récupérer tous les BO de ce type de
        // la BDD
        List<Passager> listePassagers = passagerDao.findAll();
        Passager passager = passagerDao.findById(1);

        // J'instancie le dao SQL de l'objet métier à récupérer
        ReservationDao reservationDao = new ReservationDaoSql();
        // J'appelle la méthode findAll pour récupérer tous les BO de ce type de
        // la BDD
        List<Reservation> listeReservations = reservationDao.findAll();
        Reservation reservation = reservationDao.findById(10);
        listeReservations = reservationDao.findByPassager(passager);
        
        VilleAeroportDao villeAeroportDao = new VilleAeroportDaoSql();
        List<VilleAeroport> listeVilleAeroports = villeAeroportDao.findAll();
        VilleAeroport villeAeroport = villeAeroportDao.findById(3);
        
        VilleDao villeDao = new VilleDaoSQL();
        List<Ville> listeVilles = villeDao.findAll();
        Ville ville = villeDao.findById(2);
        

        VolDaoSql volDao = new VolDaoSql();
        List<Vol> listeVols = volDao.findAll();
        Vol vol = volDao.findById(1);
        volDao.fermetureConnexion();

    }

}
