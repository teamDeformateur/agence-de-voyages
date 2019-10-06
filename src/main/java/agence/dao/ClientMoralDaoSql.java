/**
 * 
 */
package agence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import agence.model.Client;
import agence.model.ClientMoral;

/**
 * @author Seme
 */
public class ClientMoralDaoSql extends ClientDaoSql
{
    /**
     * DAO adresse
     */
    private AdresseDaoSql adresseDAO = new AdresseDaoSql(connexion);

    /**
     * DAO Login
     */
    private LoginDaoSql loginDAO = new LoginDaoSql(connexion);

    /**
     * DAO Reservation
     */
    private ReservationDao reservationDao = new ReservationDaoSql(connexion);

    /**
     * @param connexion
     */
    public ClientMoralDaoSql(Connection connexion)
    {
        super(connexion);
    }

    /*
     * (non-Javadoc)
     * @see agence.dao.Dao#create(java.lang.Object)
     */
    @Override
    public void create(Client obj)
    {
        try
        {
            preparedStatement = connexion.prepareStatement(
                    "INSERT INTO client (nom, numTel, numFax, email, siret, idAdd) VALUES(?,?,?,?,?,?)");
            preparedStatement.setString(1, obj.getNom());
            preparedStatement.setString(2, obj.getNumeroTel());
            preparedStatement.setString(3, obj.getNumeroFax());
            preparedStatement.setString(4, obj.getEmail());
            preparedStatement.setLong(5, ((ClientMoral) obj).getSiret());
            preparedStatement.setInt(6, obj.getAdresse().getIdAdd());
            // insertion dans la BDD
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /*
     * (non-Javadoc)
     * @see agence.dao.Dao#delete(java.lang.Object)
     */
    @Override
    public void delete(Client obj)
    {
        // il faut supprimer le client
        try
        {
            preparedStatement = connexion
                    .prepareStatement("DELETE FROM client WHERE idClient = ?");
            preparedStatement.setInt(1, obj.getIdCli());
            // suppression
            int affectedRows = preparedStatement.executeUpdate();
            // si aucune ligne affectée
            if (affectedRows == 0)
            {
                throw new SQLException(
                        "Echec de la suppression du client. Aucune ligne affectée.");
            }
            // puis l'adresse
            adresseDAO.delete(obj.getAdresse());
        }
        catch (SQLException e)
        {
            System.err.println("Erreur lors de la suppression d'un client.");
            e.printStackTrace();
        }

    }

    public List<Client> findAll()
    {
        // Liste des clients que l'on va retourner
        List<Client> listeClients = new ArrayList<Client>();

        try
        {

            /*
             * Connexion à la BDD
             */
            preparedStatement = connexion.prepareStatement(
                    "SELECT * FROM client WHERE siret IS NOT NULL");

            // 4. Execution de la requête
            resultSet = preparedStatement.executeQuery();
            // 5. Parcoutuple de l'ensemble des résultats (ResultSet) pour
            // récupérer les valeutuple des colonnes du tuple qui correspondent
            // aux
            // valeur des attributs de l'objet
            while (resultSet.next())
            {
                // Creation d'un objet Client
                Client objClient = new ClientMoral(
                        resultSet.getInt("idClient"));

                objClient.setNom(resultSet.getString("nom"));
                objClient.setNumeroTel(resultSet.getString("numTel"));
                objClient.setNumeroFax(resultSet.getString("numFax"));
                objClient.setEmail(resultSet.getString("eMail"));
                ((ClientMoral) objClient).setSiret(resultSet.getLong("siret"));

                objClient.setAdresse(
                        adresseDAO.findById(resultSet.getInt("idAdd")));
                objClient
                        .setLogin(loginDAO.findById(resultSet.getInt("idLog")));
                // ajout des réservations
                objClient.setListeReservations(
                        reservationDao.findByClient(objClient));

                // Ajout du nouvel objet Client créé à la liste des clients
                listeClients.add(objClient);
            } // fin de la boucle de parcoutuple de l'ensemble des résultats

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        // Retourne la liste de toutes les clients
        return listeClients;
    }

    @Override
    public Client findById(Integer idCli)
    {
        // Déclaration d'un objet Client
        Client objClient = null;

        try
        {
            // Connexion à la BDD
            PreparedStatement ps = connexion.prepareStatement(
                    "SELECT * FROM client WHERE idClient=? AND siret IS NOT NULL");
            // Cherche l'idVill voulu dans la BDD
            ps.setInt(1, idCli);

            // Récupération des résultats de la requête
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next())
            {
                objClient = new ClientMoral(resultSet.getInt("idClient"));
                objClient.setNom(resultSet.getString("nom"));
                objClient.setNumeroTel(resultSet.getString("numTel"));
                objClient.setNumeroFax(resultSet.getString("numFax"));
                objClient.setEmail(resultSet.getString("eMail"));
                ((ClientMoral) objClient).setSiret(resultSet.getLong("siret"));

                objClient.setAdresse(
                        adresseDAO.findById(resultSet.getInt("idAdd")));
                objClient
                        .setLogin(loginDAO.findById(resultSet.getInt("idLog")));
                // ajout des réservations
                objClient.setListeReservations(
                        reservationDao.findByClient(objClient));
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return objClient;
    }

    /*
     * (non-Javadoc)
     * @see agence.dao.Dao#update(java.lang.Object)
     */
    @Override
    public Client update(Client obj)
    {
        try
        {
            preparedStatement = connexion.prepareStatement(
                    "UPDATE client SET nom=?,numTel=?,numFax=?,eMail=?,siret=? WHERE idClient = ?");

            preparedStatement.setLong(6, obj.getIdCli());

            preparedStatement.setString(1, obj.getNom());
            preparedStatement.setString(2, obj.getNumeroTel());
            preparedStatement.setString(3, obj.getNumeroFax());
            preparedStatement.setString(4, obj.getEmail());
            preparedStatement.setLong(5, ((ClientMoral) obj).getSiret());

            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            System.err.println(
                    "Erreur lors de la mise à jour de la personne morale.");
            e.printStackTrace();
        }

        return obj;
    }

}
