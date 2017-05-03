package agence.dao;

import java.util.List;

import agence.model.Passager;
import agence.model.Reservation;

public interface ReservationDao extends Dao<Reservation, Integer>
{
    /**
     * Retourne la liste des réservations en fonction d'un passager
     * 
     * @param passager
     *            Le passager
     * @return Liste des réservations en fonction du passager passé en paramètre
     */
    public List<Reservation> findByPassager(Passager passager);
}
