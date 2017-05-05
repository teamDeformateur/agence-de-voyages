package agence.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import agence.model.CompagnieAerienne;

public class CompagnieAerienneDaoSql extends DaoSQL
        implements CompagnieAerienneDao
{
    /**
     * @param connexion
     */
    public CompagnieAerienneDaoSql(Connection connexion)
    {
        super(connexion);
    }

    @Override
    public List<CompagnieAerienne> findAll()
    {
        // Liste des compagnies aeriennes que l'on va retourner
        List<CompagnieAerienne> compagniesAeriennes = new ArrayList<CompagnieAerienne>();
        // Connexion à la BDD
        try
        {
            /*
             * Connexion à la BDD
             */
            preparedStatement = connexion
                    .prepareStatement("SELECT * FROM compagnie_aerienne");
            // 4. Execution de la requête
            resultSet = preparedStatement.executeQuery();
            // 5. Parcoutuple de l'ensemble des résultats (ResultSet) pour
            // récupérer les valeutuple des colonnes du tuple qui correspondent
            // aux
            // valeur des attributs de l'objet
            while (resultSet.next())
            {
                // Creation d'un objet compagnieAerienne
                CompagnieAerienne compagnieAerienne = new CompagnieAerienne(
                        resultSet.getInt("id"), resultSet.getString("nom"));
                // Ajout du nouvel objet compagnieAerienne créé à la liste des
                // compagniesAeriennes
                compagniesAeriennes.add(compagnieAerienne);
            } // fin de la boucle de parcoutuple de l'ensemble des résultats

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        // Retourne la liste de tous les aéroports
        return compagniesAeriennes;
    }

    public CompagnieAerienne findById(Integer id)
    {
        // Déclaration d'un objet compagnieAerienne
        CompagnieAerienne compagnieAerienne = null;

        try
        {
            preparedStatement = connexion.prepareStatement(
                    "SELECT * FROM compagnie_aerienne where id=?");
            // Cherche l'idComp recherché dans la BDD
            preparedStatement.setInt(1, id);

            // Récupération des résultats de la requête
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next())
            {
                compagnieAerienne = new CompagnieAerienne(
                        resultSet.getInt("id"), resultSet.getString("nom"));
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return compagnieAerienne;
    }

}
