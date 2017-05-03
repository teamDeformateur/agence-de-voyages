/**
 * 
 */
package agence.dao;

import agence.model.Adresse;

/**
 * Interface qui hérite de Dao afin de préciser quel sera le type de donnée
 * récupéré.
 * En l'occurrence : Adresse
 * De plus, on renseigne le type de la clé : ici, Integer
 * 
 * @author seme
 */
public interface AdresseDao extends Dao<Adresse, Integer>
{

}
