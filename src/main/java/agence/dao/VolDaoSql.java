package agence.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import agence.model.Escale;
import agence.model.Vol;

public class VolDaoSql extends DaoSQL implements VolDao
{
    private AeroportDaoSql aeroportDAO = new AeroportDaoSql(connexion);
    private EscaleDao escaleDao = new EscaleDaoSql(connexion);

    /**
     * @param connexion
     */
    public VolDaoSql(Connection connexion)
    {
        super(connexion);
    }

    @Override
    public List<Vol> findAll()
    {
        // Liste des vols que l'on va retourner
        List<Vol> vols = new ArrayList<Vol>();
        // Connexion à la BDD
        try
        {

            preparedStatement = connexion.prepareStatement("SELECT * FROM vol");
            // 4. Execution de la requête
            resultSet = preparedStatement.executeQuery();
            // 5. Parcoutuple de l'ensemble des résultats (ResultSet) pour
            // récupérer les valeutuple des colonnes du tuple qui correspondent
            // aux
            // valeur des attributs de l'objet
            while (resultSet.next())
            {
                // Creation d'un objet Vol
                Vol vol = new Vol(resultSet.getInt("idVol"));
                vol.setDateArrivee(resultSet.getDate("dateArrivee"));
                vol.setDateDepart(resultSet.getDate("dateDepart"));
                vol.setHeureArrivee(resultSet.getTime("heureArrivee"));
                vol.setHeureDepart(resultSet.getTime("heureDepart"));
                vol.setAeroportArrivee(aeroportDAO
                        .findById(resultSet.getInt("idAeroportArrivee")));
                vol.setAeroportDepart(aeroportDAO
                        .findById(resultSet.getInt("idAeroportDepart")));
                /*
                 * Recherche des escales
                 */
                List<Escale> escales = escaleDao.findByVol(vol);
                if (escales != null)
                {
                    // liaison avec le vol
                    vol.setEscales(escales);
                }
                // Ajout du nouvel objet vol créé à la liste des vols
                vols.add(vol);
            } // fin de la boucle de parcoutuple de l'ensemble des résultats

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        // Retourne la liste de tous les aéroports
        return vols;
    }

    public Vol findById(Integer idVol)
    {
        // Déclaration d'un objet vol
        Vol vol = null;

        try
        {

            preparedStatement = connexion
                    .prepareStatement("SELECT * FROM vol where idVol=?");
            // Cherche l'idVol voulu dans la BDD
            preparedStatement.setInt(1, idVol);

            // Récupération des résultats de la requête
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next())
            {
                vol = new Vol(resultSet.getInt("idVol"));
                vol.setDateArrivee(resultSet.getDate("dateArrivee"));
                vol.setDateDepart(resultSet.getDate("dateDepart"));
                vol.setHeureArrivee(resultSet.getTime("heureArrivee"));
                vol.setHeureDepart(resultSet.getTime("heureDepart"));
                vol.setAeroportArrivee(aeroportDAO
                        .findById(resultSet.getInt("idAeroportArrivee")));
                vol.setAeroportDepart(aeroportDAO
                        .findById(resultSet.getInt("idAeroportDepart")));
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return vol;
    }
}
