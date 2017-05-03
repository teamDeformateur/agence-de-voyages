package agence.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import agence.model.Adresse;

public class AdresseDaoSql implements AdresseDao
{
    /**
     * Connexion à la BDD
     */
    private Connection connexion;

    /**
     * Constructeur par défaut qui charge le driver et qui instancie la
     * connexion
     */
    public AdresseDaoSql()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {

            e.printStackTrace();
        }
        // 2. Créer la connexion à la base (on instancie l'objet connexion)
        try
        {
            connexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/agence", "user", "password");
        }
        catch (SQLException e)
        {

            e.printStackTrace();
        }
    }

    /**
     * Ferme la connexion à la BDD
     */
    public void fermetureConnexion()
    {
        try
        {
            connexion.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
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
            PreparedStatement ps = connexion
                    .prepareStatement("SELECT * FROM adresse");

            // 4. Execution de la requête
            ResultSet tuple = ps.executeQuery();
            // 5. Parcoutuple de l'ensemble des résultats (ResultSet) pour
            // récupérer les valeutuple des colonnes du tuple qui correspondent
            // aux
            // valeur des attributs de l'objet
            while (tuple.next())
            {
                // Creation d'un objet Client
                Adresse objAdresse = new Adresse(tuple.getInt("idAdd"));

                objAdresse.setAdresse(tuple.getString("adresse"));
                objAdresse.setCodePostal(tuple.getString("codePostal"));
                objAdresse.setVille(tuple.getString("ville"));
                objAdresse.setPays(tuple.getString("pays"));

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
             * Etape 0 : chargement du pilote
             */
            Class.forName("com.mysql.jdbc.Driver");

            /*
             * Etape 1 : se connecter à la BDD
             */
            Connection connexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/agence", "user", "password");

            /*
             * Etape 2 : Création du statement
             */
            Statement statement = connexion.createStatement();

            /*
             * Etape 3 : Exécution de la requête SQL
             */
            ResultSet resultSet = statement.executeQuery(
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

            /*
             * Etape 5 : je ferme la connexion à la BDD
             */
            connexion.close();
        }
        catch (ClassNotFoundException e)
        {
            System.err.println("Impossible de charger le pilote JDBC.");
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            System.err.println("Impossible de se connecter à la BDD.");
            e.printStackTrace();
        }
        // Je retourne l'adresse
        return adresse;
    }

}
