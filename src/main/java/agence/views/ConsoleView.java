package agence.views;

import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

/**
 * @author Seme
 */
public class ConsoleView
{
    /**
     * Le flux de sortie console
     */
    private PrintStream console;

    /**
     * Constructeur par défaut qui initialise le flux de sortie console
     */
    public ConsoleView()
    {
        console = System.out;
    }

    /**
     * Affiche le menu de l'application
     */
    public void displayMenu()
    {
        String fmtEntete = "|   %1$-13s|     %2$-13s|      %3$-12s|    %4$-14s|%n";
        String fmt = "|%1$-16s| %2$-17s| %3$-17s| %4$-17s|%n";
        String separateur = "+-------------------------------------------------------------------------+\n";
        console.printf("%s", separateur);
        console.printf(fmtEntete, "Afficher", "Modifier", "Créer", "Supprimer");
        console.printf("%s", separateur);
        console.printf(fmt, "1. Vol", "11. Vol", "12. Vol", "13. Vol");
        console.printf(fmt, "2. Client", "21. Client", "22. Client",
                "23. Client");
        console.printf(fmt, "3. Réservation", "31. Réservation",
                "32. Réservation", "33. Réservation");
        console.printf(fmt, "4. Compagnie", "41. Compagnie", "42. Compagnie",
                "43. Compagnie");
        console.printf("%s", separateur);
        console.printf("%s", "Pour quitter, option 0\n\n");
        console.printf("%s", "Veuillez choisir une option : ");
    }

    /**
     * Attend un retour chariot de l'utilisateur
     */
    public void waitNext()
    {
        console.println("Appuyer sur entrée pour afficher le menu.");
        try
        {
            System.in.read();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * @param <T>
     * @param test
     * @param liste
     */
    public <T> void afficherTestEtResultat(String test, List<T> liste)
    {
        afficherTest(test);
        if (liste != null)
        {
            afficherListe(liste);
        }
    }

    /**
     * @param <T>
     * @param test
     * @param t
     */
    public <T> void afficherTestEtResultat(String test, T t)
    {
        afficherTest(test);
        afficherResultat(t);
    }

    /**
     * @param string
     */
    private void afficherTest(String string)
    {
        String fmtEntete = "|";
        String separateur = "+-----------------------------------------------------------------------------------------------------------+\n";
        int avant = (separateur.length() - 2 - string.length()) / 2;
        int apres = separateur.length() - avant - 4;
        for (int i = 0; i < avant; i++)
        {
            fmtEntete += " ";
        }
        fmtEntete += "%1$-" + Integer.toString(apres) + "s |%n";

        console.printf("%s", separateur);
        console.printf(fmtEntete, string);
        console.printf("%s", separateur);

    }

    /**
     * @param <T>
     * @param listeAdresses
     */
    private <T> void afficherListe(List<T> liste)
    {
        for (T t : liste)
        {
            console.println(t.toString());
        }

    }

    /**
     * @param <T>
     * @param adresse
     */
    private <T> void afficherResultat(T objet)
    {
        if (objet != null)
        {
            console.println(objet.toString());
        }
        else
        {
            console.println("null");
        }
    }

    /**
     * @param listeBOs
     */
    public void displayVols(List<?> listeBOs)
    {
        afficherTestEtResultat("Liste des vols", listeBOs);
    }

}
