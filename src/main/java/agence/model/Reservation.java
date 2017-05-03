package agence.model;

import java.util.Date;

/**
 * Réservation d'un vol pour un passager effectuée par un client
 * 
 * @author Seme
 */
public class Reservation
{

    /**
     * Id de la réservation
     */
    private int idRes;
    /**
     * Date de la réservation
     */
    private Date date;
    /**
     * Numéro de la réservation
     */
    private String numero;
    /**
     * Etat de la réservation (annulée ou confirmée)
     */
    private EtatReservation etat;
    /**
     * La référence du passager
     */
    private Passager passager;

    /**
     * Constructeur
     * 
     * @param idRes
     *            Identifiant technique de la réservation
     */
    private int idPas;
    /**
     * id du client
     */
    private int idCli;

    /**
     * Vol associé à la réservation
     */
    private Vol vol;
    private Passager passager;
    private Client client;

    public Reservation(int idRes)
    {
        this.idRes = idRes;
    }

    public Client getClient()
    {
        return client;
    }

    public Date getDate()
    {
        return date;
    }

    public EtatReservation getEtat()
    {
        return etat;
    }

    public int getIdCli()
    {
        return idCli;
    }

    public int getIdPas()
    {
        return idPas;
    }

    /**
     * 
     */
    public Reservation()
    {
        // TODO Auto-generated constructor stub
    }

    /**
     * @return the idRes
     */
    public int getIdRes()
    {
        return idRes;
    }

    /**
     * @param idRes
     *            the idRes to set
     */
    public void setIdRes(int idRes)
    {
        this.idRes = idRes;
    }

    /**
     * @return the date
     */
    public Date getDate()
    {
        return date;
    }

    public Passager getPassager()
    {
        return passager;
    }

    public Vol getVol()
    {
        return vol;
    }

    public void setClient(Client client)
    {
        this.client = client;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    /**
     * @return the numero
     */
    public String getNumero()
    {
        return numero;
    }

    public void setIdCli(int idCli)
    {
        this.idCli = idCli;
    }

    public void setIdPas(int idPas)
    {
        this.numero = numero;
    }

    /**
     * @return the etat
     */
    public EtatReservation getEtat()
    {
        return etat;
    }

    /**
     * @param etat
     *            the etat to set
     */
    public void setEtat(EtatReservation etat)
    {
        this.etat = etat;
    }

    /**
     * @return the passager
     */
    public Passager getPassager()
    {
        return passager;
    }

    /**
     * @param passager
     *            the passager to set
     */
    public void setPassager(Passager passager)
    {
        this.passager = passager;
    }

    public void setVol(Vol vol)
    {
        this.vol = vol;
    }

    public String toString()
    {
        String reponse = "La Reservation : " + this.numero
                + " a été effectuée par le Client : \n" + client.getNom() + " "
                + client.getPrenom() + "\nElle porte sur le vol de "
                + vol.getAeroportDepart().getNom() + " à "
                + vol.getAeroportArrivee().getNom()
                + ".\nElle concerne le passager :\n" + passager.getNom() + " "
                + passager.getPrenom();

        return reponse;
    }

}
