/**
 * 
 */
package agence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Seme
 */
public abstract class DaoSQL
{
    protected Connection connexion = null;
    protected ResultSet resultSet = null;
    protected Statement statement = null;
    protected PreparedStatement preparedStatement = null;

    /**
     * Constructeur par défaut qui charge le driver et instancie la connexion
     */
    public DaoSQL(Connection connexion)
    {
        this.connexion = connexion;
    }

    /**
     * Libère les objets de la BDD
     */
    public void libererResultats()
    {
        try
        {
            if (resultSet != null)
            {
                resultSet.close();
            }
            if (preparedStatement != null)
            {
                preparedStatement.close();
            }
            if (statement != null)
            {
                statement.close();
            }
        }
        catch (SQLException e)
        {
            System.err.println(
                    "Problème lors de la libération des résultats de la BDD.");
        }
    }
}
