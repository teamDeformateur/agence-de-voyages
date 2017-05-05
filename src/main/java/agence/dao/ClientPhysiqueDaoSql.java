/**
 * 
 */
package agence.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import agence.model.Client;
import agence.model.ClientPhysique;

/**
 * @author Seme
 */
public class ClientPhysiqueDaoSql extends ClientDaoSql
{
    /**
     * @param connexion
     */
    public ClientPhysiqueDaoSql(Connection connexion)
    {
        super(connexion);
    }

    private AdresseDaoSql adresseDAO = new AdresseDaoSql(connexion);
    private LoginDaoSql loginDAO = new LoginDaoSql(connexion);
    private ReservationDao reservationDao = new ReservationDaoSql(connexion);

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
                    "SELECT * FROM client WHERE siret IS NULL");

            // 4. Execution de la requête
            resultSet = preparedStatement.executeQuery();
            // 5. Parcoutuple de l'ensemble des résultats (ResultSet) pour
            // récupérer les valeutuple des colonnes du tuple qui correspondent
            // aux
            // valeur des attributs de l'objet
            while (resultSet.next())
            {
                // Creation d'un objet Client
                Client objClient = new ClientPhysique(
                        resultSet.getInt("idClient"));

                objClient.setNom(resultSet.getString("nom"));
                ((ClientPhysique) objClient)
                        .setPrenom(resultSet.getString("prenom"));
                objClient.setNumeroTel(resultSet.getString("numTel"));
                objClient.setNumeroFax(resultSet.getString("numFax"));
                objClient.setEmail(resultSet.getString("eMail"));

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
            preparedStatement = connexion.prepareStatement(
                    "SELECT * FROM client WHERE idClient=? AND siret IS NULL");
            // Cherche l'idVill voulu dans la BDD
            preparedStatement.setInt(1, idCli);

            // Récupération des résultats de la requête
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next())
            {
                objClient = new ClientPhysique(resultSet.getInt("idClient"));
                objClient.setNom(resultSet.getString("nom"));
                ((ClientPhysique) objClient)
                        .setPrenom(resultSet.getString("prenom"));
                objClient.setNumeroTel(resultSet.getString("numTel"));
                objClient.setNumeroFax(resultSet.getString("numFax"));
                objClient.setEmail(resultSet.getString("eMail"));

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

}
