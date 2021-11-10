import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class Medlem
{
    //region Attributter
    private int medlemsnummer;
    private String navn;
    private LocalDate fødselsdato;
    private String tlf;
    private String køn;


    private List<Indbetaling> indbetalingList = new ArrayList<>();
    //endregion

    //region Constructors
    public Medlem()
    {
    }

    public Medlem(int medlemsnummer, String navn)
    {
        this.medlemsnummer = medlemsnummer;
        this.navn = navn;
    }

    public Medlem(int medlemsnummer, String navn, LocalDate fødselsdato, String tlf, String køn)
    {
        setMedlemsnummer(medlemsnummer);
        setNavn(navn);
        setFødselsdato(fødselsdato);
        setTlf(tlf);
        setKøn(køn);
    }
    //endregion

    //region Gettere og Settere til alle attributter
    public int getMedlemsnummer()
    {
        return medlemsnummer;
    }

    public void setMedlemsnummer(int medlemsnummer)
    {
        this.medlemsnummer = medlemsnummer;
    }

    public String getNavn()
    {
        return navn;
    }

    public void setNavn(String navn)
    {
        this.navn = navn;
    }

    public LocalDate getFødselsdato()
    {
        return fødselsdato;
    }

    public void setFødselsdato(LocalDate fødselsdato)
    {
        this.fødselsdato = fødselsdato;
    }

    public String getTlf()
    {
        return tlf;
    }

    public void setTlf(String tlf)
    {
        this.tlf = tlf;
    }

    public String getKøn()
    {
        return køn;
    }

    public void setKøn(String køn)
    {
        if (køn.equals("Mand") || køn.equals("Kvinde"))
        {
            this.køn = køn;
        } else
        {
            this.køn = "Udefineret";
        }
    }

    public List<Indbetaling> getIndbetalingList()
    {
        return indbetalingList;
    }

    //endregion

    //region toString og lignende autogenerede metoder
    @Override
    public String toString()
    {
        return "Medlem{" +
                "medlemsnummer=" + medlemsnummer +
                ", navn='" + navn + '\'' +
                ", fødselsår=" + fødselsdato +
                ", tlf='" + tlf + '\'' +
                ", køn='" + køn + '\'' +
                '}' + "\n";
    }
    //endregion

    //region Egne metoder
    public String visMedlem()
    {
        return "Medlemsnummer: " + medlemsnummer +
                ", Navn: " + navn +
                ", Fødselsår: " + fødselsdato +
                ", Alder: " + beregnAlder() +
                ", Kontingent: " + beregnKontingent() +
                ", Indbetalinger: " + lavBetalingsoversigt() + "\n";
    }

    public long beregnAlder()
    {
        if (fødselsdato != null)
        {
            Period period = Period.between(fødselsdato, LocalDate.now());
            return period.getYears();
        } else
        {
            return 0;
        }
    }

    public int beregnKontingent()
    {
        long alder = beregnAlder();

        if (alder <= 17)
        {
            return 500;
        }
        if (alder >= 18 && alder <= 59)
        {
            return 1000;
        }
        return 175;
    }

    public void indsætBetaling(int beløb)
    {
        Indbetaling indbetaling = new Indbetaling(beløb);
        indbetalingList.add(indbetaling);
    }

    public String lavBetalingsoversigt()
    {
        String result = "";
        int sum = 0;

        for (Indbetaling indbetaling : indbetalingList)
        {
            result += indbetaling.toString();
            sum += indbetaling.getBeløb();
        }
        if (result.length() == 0)
        {
            result = "Har ingen indbetalinger endnu. Saldo: 0 kr.";
        } else
        {
            result += ". Saldo: " + sum + " kr.";
        }
        return result;
    }


    //endregion
}
