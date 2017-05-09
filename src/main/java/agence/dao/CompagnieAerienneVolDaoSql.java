package agence.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import agence.model.CompagnieAerienneVol;

public class CompagnieAerienneVolDaoSql extends DaoSQL
        implements CompagnieAerienneVolDao
{
    private VolDaoSql volDAO = new VolDaoSql(connexion);
    private CompagnieAerienneDaoSql compagnieDAO = new CompagnieAerienneDaoSql(
            connexion);

    /**
     * @param connexion
     */
    public CompagnieAerienneVolDaoSql(Connection connexion)
    {
        super(connexion);
    }

    @Override
    public List<CompagnieAerienneVol> findAll()
    {
        // Liste des CompagnieAerienneVol que l'on va retourner
        List<CompagnieAerienneVol> compagnieaeriennevols = new ArrayList<CompagnieAerienneVol>();

        // Connexion à la BDD
        try
        {
            /*
             * Connexion à la BDD
             */
            preparedStatement = connexion
                    .prepareStatement("SELECT * FROM compagnie_aerienne_vol");
            // 4. Execution de la requête
            resultSet = preparedStatement.executeQuery();
            // 5. Parcours de l'ensemble des résultats (ResultSet) pour
            // récupérer les valeurs des colonnes du tuple qui correspondent aux
            // valeur des attributs de l'objet
            while (resultSet.next())
            {
                // Creation d'un objet Aeroport
                CompagnieAerienneVol compagnieaeriennevol = new CompagnieAerienneVol(
                        resultSet.getString("numero"),
                        resultSet.getBoolean("ouvert"));
                compagnieaeriennevol.setId(resultSet.getInt("id"));
                compagnieaeriennevol.setCompagnieAerienne(
                        compagnieDAO.findById(resultSet.getInt("idCompagnie")));
                compagnieaeriennevol
                        .setVol(volDAO.findById(resultSet.getInt("idVol")));
                // Ajout du nouvel objet Aeroport créé à la liste des élèves
                compagnieaeriennevols.add(compagnieaeriennevol);
            } // fin de la boucle de parcours de l'ensemble des résultats
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        // Retourne la liste de tous les aéroports
        return compagnieaeriennevols;
    }

    public CompagnieAerienneVol findById(Integer id)
    {
        CompagnieAerienneVol compagnieAerienneVol = null;
        try
        {

            preparedStatement = connexion.prepareStatement(
                    "SELECT * FROM compagnie_aerienne_vol where id=?");
            // Cherche l'idComp recherché dans la BDD
            preparedStatement.setInt(1, id);

            // Récupération des résultats de la requête
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next())
            {
                compagnieAerienneVol = new CompagnieAerienneVol(
                        resultSet.getString("numero"),
                        resultSet.getBoolean("ouvert"));
                compagnieAerienneVol.setId(resultSet.getInt("id"));
                compagnieAerienneVol
                        .setVol(volDAO.findById(resultSet.getInt("idVol")));
                compagnieAerienneVol.setCompagnieAerienne(
                        compagnieDAO.findById(resultSet.getInt("idCompagnie")));
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return compagnieAerienneVol;
    }

    /* (non-Javadoc)
     * @see agence.dao.Dao#create(java.lang.Object)
     */
    @Override
    public void create(CompagnieAerienneVol obj)
    {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see agence.dao.Dao#update(java.lang.Object)
     */
    @Override
    public CompagnieAerienneVol update(CompagnieAerienneVol obj)
    {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see agence.dao.Dao#delete(java.lang.Object)
     */
    @Override
    public void delete(CompagnieAerienneVol obj)
    {
        // TODO Auto-generated method stub
        
    }

}
