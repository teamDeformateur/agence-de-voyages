package agence.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import agence.model.Ville;

public class VilleDaoSQL extends DaoSQL implements VilleDao
{
    /**
     * @param connexion
     */
    public VilleDaoSQL(Connection connexion)
    {
        super(connexion);
    }

    @Override
    public List<Ville> findAll()
    {
        // Liste des villes que l'on va retourner
        List<Ville> villes = new ArrayList<Ville>();
        // Connexion à la BDD
        try
        {
            /*
             * Connexion à la BDD
             */
            preparedStatement = connexion
                    .prepareStatement("SELECT * FROM ville");
            // 4. Execution de la requête
            resultSet = preparedStatement.executeQuery();
            // 5. Parcoutuple de l'ensemble des résultats (ResultSet) pour
            // récupérer les valeutuple des colonnes du tuple qui correspondent
            // aux
            // valeur des attributs de l'objet
            while (resultSet.next())
            {
                // Creation d'un objet Ville
                Ville ville = new Ville(resultSet.getInt("id"),
                        resultSet.getString("nom"));
                // Ajout du nouvel objet Ville créé à la liste des villes
                villes.add(ville);
            } // fin de la boucle de parcoutuple de l'ensemble des résultats

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        // Retourne la liste de toutes les villes
        return villes;
    }

    public Ville findById(Integer id)
    {
        // Déclaration d'un objet ville
        Ville ville = null;

        try
        {
            // Connexion à la BDD
            preparedStatement = connexion
                    .prepareStatement("SELECT * FROM ville where id=?");
            // Cherche l'idVill voulu dans la BDD
            preparedStatement.setInt(1, id);

            // Récupération des résultats de la requête
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next())
            {
                ville = new Ville(resultSet.getInt("id"),
                        resultSet.getString("nom"));
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return ville;
    }

    /* (non-Javadoc)
     * @see agence.dao.Dao#create(java.lang.Object)
     */
    @Override
    public void create(Ville obj)
    {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see agence.dao.Dao#update(java.lang.Object)
     */
    @Override
    public Ville update(Ville obj)
    {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see agence.dao.Dao#delete(java.lang.Object)
     */
    @Override
    public void delete(Ville obj)
    {
        // TODO Auto-generated method stub
        
    }

}
