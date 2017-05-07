/**
 * 
 */
package agence.model;

/**
 * @author Seme
 */
public class ClientMoral extends Client
{
    /**
     * numéro SIRET (15 chiffres)
     */
    private long siret;

    /**
     * @param idCli
     */
    public ClientMoral(int idCli)
    {
        super(idCli);
    }

    /**
     * Constructeur par défaut
     */
    public ClientMoral()
    {
        super();
    }

    /**
     * @return the siret
     */
    public long getSiret()
    {
        return siret;
    }

    /**
     * @param siret
     *            the siret to set
     */
    public void setSiret(long siret)
    {
        this.siret = siret;
    }

    /*
     * (non-Javadoc)
     * @see agence.model.Client#toString()
     */
    @Override
    public String toString()
    {
        String reponse = "La personne morale : " + this.getNom()
                + ", de siret : " + this.siret + ", numéro de téléphone : "
                + this.getNumeroTel() + ", numéro de fax : "
                + this.getNumeroFax() + ",\n\t" + this.getAdresse();
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
