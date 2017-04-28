package agence.model;

public enum EtatReservation
{
    OUVERT("Ouvert"), FERME("Fermé");

    private final String label;

    private EtatReservation(String label)
    {
        this.label = label;
    }

    public String getLabel()
    {
        return label;
    }

}
