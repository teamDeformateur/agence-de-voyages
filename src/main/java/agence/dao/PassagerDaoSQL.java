package agence.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import agence.model.Passager;

public class PassagerDaoSQL implements PassagerDao
{

    private Connection connexion;

    public PassagerDaoSQL()
    {
        /*
         * Connexion à la BDD
         */
        // 1. Chargement du driver
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            // TODO Auto-generated catch block
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
            // TODO Auto-generated catch block
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

    public List<Passager> findAll()
    {
        // Liste des passagers que l'on va retourner
        List<Passager> passagers = new ArrayList<Passager>();
        AdresseDaoSql adresseDAO = new AdresseDaoSql();
        try
        {
            PreparedStatement ps = connexion
                    .prepareStatement("SELECT * FROM Passager");
            // 4. Execution de la requête
            ResultSet tuple = ps.executeQuery();
            // 5. Parcoutuple de l'ensemble des résultats (ResultSet) pour
            // récupérer les valeutuple des colonnes du tuple qui correspondent
            // aux
            // valeur des attributs de l'objet
            while (tuple.next())
            {
                // Creation d'un objet passager
                Passager passager = new Passager(tuple.getInt("idPassager"));
                // MAJ des autres attributs de Passager
                passager.setNom(tuple.getString("nom"));
                passager.setPrenom(tuple.getString("prenom"));
                passager.setAdresse(adresseDAO.findById(tuple.getInt("idAdd")));
                // Ajout du nouvel objet réservation créé à la liste des
                // passagers
                passagers.add(passager);
            } // fin de la boucle de parcours de l'ensemble des résultats
            adresseDAO.fermetureConnexion();

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        // Retourne la liste de toutes les réservations
        return passagers;
    }

    public Passager findById(Integer idPas)
    {
        // Déclaration d'un objet reservation
        Passager passager = null;
        AdresseDaoSql adresseDAO = new AdresseDaoSql();

        try
        {
            PreparedStatement ps = connexion.prepareStatement(
                    "SELECT * FROM passager where idPassager=?");
            // Cherche l'idPas recherché dans la BDD
            ps.setInt(1, idPas);

            // Récupération des résultats de la requête
            ResultSet tuple = ps.executeQuery();

            if (tuple.next())
            {
                passager = new Passager(tuple.getInt("idPassager"));
                passager.setNom(tuple.getString("nom"));
                passager.setPrenom(tuple.getString("prenom"));
                passager.setAdresse(adresseDAO.findById(tuple.getInt("idAdd")));
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return passager;
    }

}
