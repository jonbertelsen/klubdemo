import java.util.ArrayList;
import java.util.List;

public class Klub
{
    // Attributter

    private String navn;
    private List<Medlem> medlemList = new ArrayList<>();

    // Constructors

    public Klub(String navn)
    {
        this.navn = navn;
    }

    // Getters and setters

    public String getNavn()
    {
        return navn;
    }

    public void setNavn(String navn)
    {
        this.navn = navn;
    }

    // Egne metoder

    public void tilføjMedlem(Medlem nytMedlem)
    {
        medlemList.add(nytMedlem);
    }

    public int antalMedlemmer()
    {
        return medlemList.size();
    }

    public String lavMedlemsliste()
    {
        String medlemsListe = "";

        for (Medlem medlem : medlemList)
        {
            medlemsListe = medlemsListe + medlem.visMedlem();
        }

        return medlemsListe;
    }

    public Medlem findMedlem(int medlemsnummer)
    {
        for (Medlem medlem : medlemList)
        {
            if (medlem.getMedlemsnummer() == medlemsnummer)
            {
                return medlem;
            }
        }
        return null;
    }
}
