package agence.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import agence.model.Login;

public class LoginDaoSql implements LoginDao
{

    private Connection connexion;

    public LoginDaoSql()
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
                    "jdbc:mysql://localhost:3306/vol", "root", "");
        }
        catch (SQLException e)
        {

            e.printStackTrace();
        }
    }

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
    public List<Login> findAll()
    {
        // Liste des clients que l'on va retourner
        List<Login> ListLogin = new ArrayList<Login>();

        try
        {

            /*
             * Connexion à la BDD
             */
            PreparedStatement ps = connexion
                    .prepareStatement("SELECT * FROM login");

            // 4. Execution de la requête
            ResultSet tuple = ps.executeQuery();
            // 5. Parcoutuple de l'ensemble des résultats (ResultSet) pour
            // récupérer les valeutuple des colonnes du tuple qui correspondent
            // aux
            // valeur des attributs de l'objet
            while (tuple.next())
            {
                // Creation d'un objet Client
                Login objLogin = new Login(tuple.getInt("id"));

                objLogin.setLogin(tuple.getString("login"));
                objLogin.setMotDePasse(tuple.getString("motDePasse"));
                objLogin.setAdmin(tuple.getInt("admin"));

                // Ajout du nouvel objet Client créé à la liste des clients
                ListLogin.add(objLogin);
            } // fin de la boucle de parcoutuple de l'ensemble des résultats

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        // Retourne la liste de toutes les clients
        return ListLogin;
    }

    @Override
    public Login findById(Integer id)
    {
        // Déclaration d'un objet Client
        Login objLogin = null;

        try
        {
            // Connexion à la BDD
            PreparedStatement ps = connexion
                    .prepareStatement("SELECT * FROM login WHERE id=?");
            // Cherche l'idVill voulu dans la BDD
            ps.setInt(1, id);

            // Récupération des résultats de la requête
            ResultSet tuple = ps.executeQuery();

            if (tuple.next())
            {
                objLogin = new Login(tuple.getInt("id"));
                objLogin.setLogin(tuple.getString("login"));
                objLogin.setMotDePasse(tuple.getString("motDePasse"));
                objLogin.setAdmin(tuple.getInt("admin"));
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return objLogin;
    }

}
