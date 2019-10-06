package agence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import agence.model.Escale;
import agence.model.Vol;

public class EscaleDaoSql extends DaoSQL implements EscaleDao
{
	private AeroportDaoSql aeroportDAO = new AeroportDaoSql(connexion);
	
    /**
     * @param connexion
     */
    public EscaleDaoSql(Connection connexion)
    {
        super(connexion);
    }

    @Override
    public List<Escale> findAll()
    {
        // Liste des escales que l'on va retourner
        List<Escale> escales = new ArrayList<Escale>();

        VolDaoSql volDAO = new VolDaoSql(connexion);

        try
        {
            // connexion
            preparedStatement = connexion
                    .prepareStatement("SELECT * FROM escale");
            // 4. Execution de la requête
            resultSet = preparedStatement.executeQuery();
            // 5. Parcoutuple de l'ensemble des résultats (ResultSet) pour
            // récupérer les valeutuple des colonnes du tuple qui correspondent
            // aux
            // valeur des attributs de l'objet
            while (resultSet.next())
            {
                // Creation d'un objet escale
                Escale escale = new Escale(resultSet.getInt("idEscale"));
                escale.setDateArrivee(resultSet.getDate("dateArrivee"));
                escale.setDateDepart(resultSet.getDate("dateDepart"));
                escale.setHeureArrivee(resultSet.getTime("heureArrivee"));
                escale.setHeureDepart(resultSet.getTime("heureDepart"));
                // ajout des id Adress
                escale.setVol(volDAO.findById(resultSet.getInt("idVol")));
                // ajout des aeroports
                escale.setAeoroport(
                        aeroportDAO.findById(resultSet.getInt("idAeroport")));
                // Ajout du nouvel objet Aeroport créé à la liste des aéroports
                escales.add(escale);
            } // fin de la boucle de parcoutuple de l'ensemble des résultats
              // ajout des vols;

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        // Retourne la liste de toutes les escales
        return escales;
    }

    public Escale findById(Integer idEscale)
    {
        Escale escale = new Escale();
        VolDaoSql volDAO = new VolDaoSql(connexion);
        try
        {

            preparedStatement = connexion
                    .prepareStatement("SELECT * FROM escale where idEscale=?");
            // Cherche l'idVol voulu dans la BDD
            preparedStatement.setInt(1, idEscale);

            // Récupération des résultats de la requête
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next())
            {
                escale.setIdEscale(resultSet.getInt("idEscale"));
                escale.setDateArrivee(resultSet.getDate("dateArrivee"));
                escale.setDateDepart(resultSet.getDate("dateDepart"));
                escale.setHeureArrivee(resultSet.getTime("heureArrivee"));
                escale.setHeureDepart(resultSet.getTime("heureDepart"));
                escale.setVol(volDAO.findById(resultSet.getInt("idVol")));
                escale.setAeoroport(
                        aeroportDAO.findById(resultSet.getInt("idAeroport")));
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return escale;
    }

    /*
     * (non-Javadoc)
     * @see agence.dao.EscaleDao#findByVol(agence.model.Vol)
     */
    @Override
    public List<Escale> findByVol(Vol vol)
    {
        // Liste des escales que l'on va retourner
        List<Escale> escales = new ArrayList<Escale>();
        AeroportDaoSql aeroportDAO = new AeroportDaoSql(connexion);
        try
        {
            // connexion
            PreparedStatement ps = connexion
                    .prepareStatement("SELECT * " + "FROM escale e "
                            + "INNER JOIN vol v ON e.idVol = v.idVol "
                            + "WHERE v.idVol = ?");
            ps.setInt(1, vol.getIdVol());
            // 4. Execution de la requête
            ResultSet tuple = ps.executeQuery();
            // 5. Parcoutuple de l'ensemble des résultats (ResultSet) pour
            // récupérer les valeutuple des colonnes du tuple qui correspondent
            // aux
            // valeur des attributs de l'objet
            while (tuple.next())
            {
                // Creation d'un objet escale
                Escale escale = new Escale(tuple.getInt("idEscale"));
                escale.setDateArrivee(tuple.getDate("dateArrivee"));
                escale.setDateDepart(tuple.getDate("dateDepart"));
                escale.setHeureArrivee(tuple.getTime("heureArrivee"));
                escale.setHeureDepart(tuple.getTime("heureDepart"));
                // ajout des id Adress
                escale.setVol(vol);
                // ajout des aeroports
                escale.setAeoroport(
                        aeroportDAO.findById(tuple.getInt("idAeroport")));
                // Ajout du nouvel objet escale créé à la liste des escales
                escales.add(escale);
            } // fin de la boucle de parcoutuple de l'ensemble des résultats
              // ajout des vols;

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        // Retourne la liste de toutes les escales
        return escales;
    }

    /* (non-Javadoc)
     * @see agence.dao.Dao#create(java.lang.Object)
     */
    @Override
    public void create(Escale obj)
    {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see agence.dao.Dao#update(java.lang.Object)
     */
    @Override
    public Escale update(Escale obj)
    {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see agence.dao.Dao#delete(java.lang.Object)
     */
    @Override
    public void delete(Escale obj)
    {
        // TODO Auto-generated method stub
        
    }

}
