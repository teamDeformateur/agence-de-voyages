package agence.dao;

import java.sql.Connection;

public abstract class ClientDaoSql extends DaoSQL implements ClientDao
{

    /**
     * @param connexion
     */
    public ClientDaoSql(Connection connexion)
    {
        super(connexion);
    }

}
