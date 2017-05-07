/**
 * 
 */
package agence.model;

/**
 * @author Seme
 */
public class ClientPhysique extends Client
{
    /**
     * Prénom
     */
    private String prenom;

    /**
     * @param idCli
     */
    public ClientPhysique(int idCli)
    {
        super(idCli);
    }

    /**
     * Constructeur par défaut
     */
    public ClientPhysique()
    {
        super();
    }

    /**
     * @return the prenom
     */
    public String getPrenom()
    {
        return prenom;
    }

    /**
     * @param prenom
     *            the prenom to set
     */
    public void setPrenom(String prenom)
    {
        this.prenom = prenom;
    }

    /*
     * (non-Javadoc)
     * @see agence.model.Client#toString()
     */
    @Override
    public String toString()
    {
        String reponse = "La personne physique : " + this.getNom() + " "
                + this.prenom + ", numéro de téléphone : " + this.getNumeroTel()
                + ", numéro de fax : " + this.getNumeroFax() + ",\n\t"
                + this.getAdresse();
        if (this.getListeReservations().size() > 0)
        {
            reponse += " \n\ta effectué la/les reservation(s) : \n";
            for (int i = 0; i < this.getListeReservations().size(); i++)
            {
                reponse += "\t- "
                        + this.getListeReservations().get(i).getNumero() + "\n";
            }
        }

        return reponse;
    }

}
