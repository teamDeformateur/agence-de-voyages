package agence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import agence.model.Adresse;
import agence.model.Client;

public class AdresseDaoSql extends DaoSQL implements AdresseDao
{

    /**
     * @param connexion
     */
    public AdresseDaoSql(Connection connexion)
    {
        super(connexion);
    }

    @Override
    public List<Adresse> findAll()
    {
        // Liste des clients que l'on va retourner
        List<Adresse> listeAdresses = new ArrayList<Adresse>();

        try
        {

            /*
             * Connexion à la BDD
             */
            preparedStatement = connexion
                    .prepareStatement("SELECT * FROM adresse");

            // 4. Execution de la requête
            resultSet = preparedStatement.executeQuery();
            // 5. Parcoutuple de l'ensemble des résultats (ResultSet) pour
            // récupérer les valeutuple des colonnes du tuple qui correspondent
            // aux
            // valeur des attributs de l'objet
            while (resultSet.next())
            {
                // Creation d'un objet Client
                Adresse objAdresse = new Adresse(resultSet.getInt("idAdd"));

                objAdresse.setAdresse(resultSet.getString("adresse"));
                objAdresse.setCodePostal(resultSet.getString("codePostal"));
                objAdresse.setVille(resultSet.getString("ville"));
                objAdresse.setPays(resultSet.getString("pays"));

                // Ajout du nouvel objet Client créé à la liste des clients
                listeAdresses.add(objAdresse);
            } // fin de la boucle de parcoutuple de l'ensemble des résultats

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        // Retourne la liste de toutes les clients
        return listeAdresses;
    }

    @Override
    public Adresse findById(Integer idAdd)
    {
        // Initialiser ma liste d'adresses
        Adresse adresse = null;
        try
        {
            /*
             * Etape 2 : Création du statement
             */
            statement = connexion.createStatement();

            /*
             * Etape 3 : Exécution de la requête SQL
             */
            resultSet = statement.executeQuery(
                    "SELECT * FROM adresse WHERE idAdd = " + idAdd);

            /*
             * Etape 4 : Parcours des résultats
             */
            if (resultSet.next())
            {
                // Chaque ligne du tableau de résultat peut être exploitée
                // cad, on va récupérer chaque valeur de chaque colonne
                // je crée l'objet adresse
                adresse = new Adresse();
                // appel des mutateurs
                adresse.setIdAdd(resultSet.getInt("idAdd"));
                adresse.setAdresse(resultSet.getString("adresse"));
                adresse.setCodePostal(resultSet.getString("codePostal"));
                adresse.setVille(resultSet.getString("ville"));
                adresse.setPays(resultSet.getString("pays"));
            }
        }
        catch (SQLException e)
        {
            System.err.println("Impossible de se connecter à la BDD.");
            e.printStackTrace();
        }
        // Je retourne l'adresse
        return adresse;
    }

    /*
     * (non-Javadoc)
     * @see agence.dao.AdresseDao#findByClient(agence.model.Client)
     */
    @Override
    public Adresse findByClient(Client client)
    {
        // Initialiser mon objet métier
        Adresse adresse = null;
        try
        {
            /*
             * Etape 2 : Création du statement
             */
            statement = connexion.createStatement();

            /*
             * Etape 3 : Exécution de la requête SQL
             */
            resultSet = statement.executeQuery("SELECT a.* " + "FROM adresse a "
                    + "INNER JOIN client c ON a.idAdd = c.idAdd "
                    + "WHERE idClient = " + client.getIdCli());

            /*
             * Etape 4 : Parcours des résultats
             */
            if (resultSet.next())
            {
                // Chaque ligne du tableau de résultat peut être exploitée
                // cad, on va récupérer chaque valeur de chaque colonne
                // je crée l'objet métier correspondant
                adresse = new Adresse();
                // appel des mutateurs
                adresse.setIdAdd(resultSet.getInt("idAdd"));
                adresse.setAdresse(resultSet.getString("adresse"));
                adresse.setCodePostal(resultSet.getString("codePostal"));
                adresse.setVille(resultSet.getString("ville"));
                adresse.setPays(resultSet.getString("pays"));
                // Je crée le lien entre le client et son adresse
                client.setAdresse(adresse);
            }

        }
        catch (SQLException e)
        {
            System.err.println("Impossible de se connecter à la BDD.");
            e.printStackTrace();
        }
        // Je retourne l'adresse
        return adresse;
    }

    /*
     * (non-Javadoc)
     * @see agence.dao.Dao#create(java.lang.Object)
     */
    @Override
    public void create(Adresse obj)
    {
        try
        {
            preparedStatement = connexion.prepareStatement(
                    "INSERT INTO adresse(adresse, codePostal, ville, pays) VALUES (?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, obj.getAdresse());
            preparedStatement.setString(2, obj.getCodePostal());
            preparedStatement.setString(3, obj.getVille());
            preparedStatement.setString(4, obj.getPays());

            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next())
            {
                // mise à jour de la clef de l'objet
                obj.setIdAdd(resultSet.getInt(1));
            }
            else
            {
                throw new SQLException(
                        "Echec de la création de l'adresse. Aucune adresse insérée.");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    /*
     * (non-Javadoc)
     * @see agence.dao.Dao#update(java.lang.Object)
     */
    @Override
    public Adresse update(Adresse obj)
    {
        try
        {
            PreparedStatement ps = connexion.prepareStatement(
                    "UPDATE `adresse` SET `adresse`=?,`codePostal`=?,`ville`=?,`pays`=? WHERE `idAdd`=?");

            ps.setInt(5, obj.getIdAdd());

            ps.setString(1, obj.getAdresse());
            ps.setString(2, obj.getCodePostal());
            ps.setString(3, obj.getVille());
            ps.setString(4, obj.getPays());

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0)
            {
                throw new SQLException(
                        "Echec de la mise à jour de l'adresse. Aucune ligne affectée.");
            }
        }
        catch (SQLException e)
        {
            System.err.println("Echec lors de la mise à jour de l'adresse.");
            e.printStackTrace();
        }

        return obj;
    }

    /*
     * (non-Javadoc)
     * @see agence.dao.Dao#delete(java.lang.Object)
     */
    @Override
    public void delete(Adresse obj)
    {
        try
        {
            preparedStatement = connexion
                    .prepareStatement("DELETE FROM adresse WHERE idAdd = ?");
            preparedStatement.setInt(1, obj.getIdAdd());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            System.err.println("Erreur lors de la suppression d'une adresse.");
            e.printStackTrace();
        }

    }

}
