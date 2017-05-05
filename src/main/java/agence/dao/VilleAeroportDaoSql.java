package agence.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import agence.model.VilleAeroport;

public class VilleAeroportDaoSql extends DaoSQL implements VilleAeroportDao
{
    private AeroportDaoSql aeroportDAO = new AeroportDaoSql(connexion);
    private VilleDaoSQL villeDAO = new VilleDaoSQL(connexion);

    /**
     * @param connexion
     */
    public VilleAeroportDaoSql(Connection connexion)
    {
        super(connexion);
    }

    @Override
    public List<VilleAeroport> findAll()
    {
        List<VilleAeroport> villeAeroports = new ArrayList<VilleAeroport>();

        try
        {
            /*
             * Connexion à la BDD
             */

            preparedStatement = connexion
                    .prepareStatement("SELECT * FROM ville_aeroport");
            // 4. Execution de la requête
            resultSet = preparedStatement.executeQuery();
            // 5. Parcoutuple de l'ensemble des résultats (ResultSet) pour
            // récupérer les valeutuple des colonnes du tuple qui correspondent
            // aux
            // valeur des attributs de l'objet
            while (resultSet.next())
            {
                // Creation d'un objet Aeroport
                VilleAeroport villeAeroport = new VilleAeroport(
                        resultSet.getInt("id"));
                villeAeroport.setVille(
                        villeDAO.findById(resultSet.getInt("idVille")));
                villeAeroport.setAeroport(
                        aeroportDAO.findById(resultSet.getInt("idAeroport")));
                // Ajout du nouvel objet Aeroport créé à la liste des aéroports
                villeAeroports.add(villeAeroport);
            } // fin de la boucle de parcoutuple de l'ensemble des résultats

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        // Retourne la liste de tous les aéroports
        return villeAeroports;
    }

    @Override
    public VilleAeroport findById(Integer id)
    {
        // Déclaration d'un objet aeroport
        VilleAeroport villeAeroport = null;

        try
        {

            preparedStatement = connexion.prepareStatement(
                    "SELECT * FROM ville_aeroport where id=?");
            // Cherche l'idAero voulu dans la BDD
            preparedStatement.setInt(1, id);

            // Récupération des résultats de la requête
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next())
            {
                villeAeroport = new VilleAeroport(resultSet.getInt("id"));
                villeAeroport.setVille(
                        villeDAO.findById(resultSet.getInt("idVille")));
                villeAeroport.setAeroport(
                        aeroportDAO.findById(resultSet.getInt("idAeroport")));

            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return villeAeroport;
    }

}
