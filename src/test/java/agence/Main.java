package agence;

import agence.dao.AeroportDaoSQL;
import agence.dao.EscaleDaoSql;
import agence.dao.VolDaoSql;

import java.util.List;

import agence.dao.AdresseDao;
import agence.dao.AdresseDaoFile;
import agence.dao.PassagerDao;
import agence.dao.PassagerDaoFile;
import agence.dao.ReservationDao;
import agence.dao.ReservationDaoFile;
import agence.model.Adresse;
import agence.model.Passager;
import agence.model.Reservation;

public class Main
{

    public static void main(String[] args)
    {
        AdresseDao adresseDao = new AdresseDaoFile();
        List<Adresse> adresses = adresseDao.findAll();
        Adresse adresse = adresseDao.findById(2);
        AeroportDaoSQL aeroport = new AeroportDaoSQL();
        aeroport.findAll();
        aeroport.findById(1);
        aeroport.fermetureConnexion();

        PassagerDao passagerDao = new PassagerDaoFile();
        List<Passager> passagers = passagerDao.findAll();
        Passager passager = passagerDao.findById(1);

        ReservationDao reservationDao = new ReservationDaoFile();
        List<Reservation> reservations = reservationDao.findAll();
        Reservation reservation = reservationDao.findById(2);
        // Recherche par passager
        List<Reservation> reservationsPassager = reservationDao.findByPassager(passager);
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
