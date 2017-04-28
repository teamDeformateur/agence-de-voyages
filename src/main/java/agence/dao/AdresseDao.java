/**
 * 
 */
package agence.dao;

import agence.model.Adresse;

/**
 * @author ajc
 *
 */
public interface AdresseDao extends Dao<Adresse, Integer>
{

    /**
     * 
     */
    public Adresse findById(Integer idAdd);
}
