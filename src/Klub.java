import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Klub
{

    // Konstanter

    // TOKENS / BRIKKER
    private final String BEGIN = "BEGIN";
    private final String END = "END";
    private final String MEDLEMSNUMMER = "MEDLEMSNUMMER";
    private final String NAVN = "NAVN";
    private final String FØDSELSDATO = "FØDSELSDATO";
    private final String TLF = "TLF";
    private final String KØN = "KØN";
    private final String INDBETALINGERSTART = "INDBETALINGERSTART";
    private final String INDBETALINGERSLUT = "INDBETALINGERSLUT";
    private final String INDBETALINGSTART = "INDBETALINGSTART";
    private final String INDBETALINGSLUT = "INDBETALINGSLUT";
    private final String BELØB = "BELØB";
    private final String INDBETALINGSDATO = "INDBETALINGSDATO";

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

    public void fjernMedlem(Medlem medlem)
    {
        medlemList.remove(medlem);
    }

    public void gemMedlemmer(String filnavn)
    {
        try (PrintWriter writer = new PrintWriter(new File(filnavn)))
        {
            for (Medlem medlem : medlemList)
            {
                writer.println(BEGIN);
                writer.println(MEDLEMSNUMMER);
                writer.println(medlem.getMedlemsnummer());
                writer.println(FØDSELSDATO);
                writer.println(medlem.getFødselsdato());
                writer.println(NAVN);
                writer.println(medlem.getNavn());
                writer.println(TLF);
                writer.println(medlem.getTlf());
                writer.println(KØN);
                writer.println(medlem.getKøn());
                // Gem indbetalinger (hvis der nogle)
                if (medlem.getIndbetalingList().size() > 0)
                {
                    writer.println(INDBETALINGERSTART);
                    for (Indbetaling indbetaling : medlem.getIndbetalingList())
                    {
                        writer.println(INDBETALINGSTART);
                        writer.println(BELØB);
                        writer.println(indbetaling.getBeløb());
                        writer.println(INDBETALINGSDATO);
                        writer.println(indbetaling.getDato());
                        writer.println(INDBETALINGSLUT);
                    }
                    writer.println(INDBETALINGERSLUT);
                }
;               writer.println(END);
            }


        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

    }

    public void indlæsMedlemmer(String filnavn)
    {
        File csvFil = new File(filnavn);
        String line = "";
        String token = "";
        Medlem medlem = null;

        try (Scanner scanner = new Scanner(csvFil))  // try-with-ressources
        {
            while (scanner.hasNext()){
                line = scanner.nextLine();
                switch(line)
                {
                    case BEGIN:
                        token = BEGIN;
                        medlem = new Medlem();
                        break;
                    case MEDLEMSNUMMER:
                        token = MEDLEMSNUMMER;
                        break;
                    case NAVN:
                        token = NAVN;
                        break;
                    case FØDSELSDATO:
                        token = FØDSELSDATO;
                        break;
                    case TLF:
                        token = TLF;
                        break;
                    case KØN:
                        token = KØN;
                        break;
                    case INDBETALINGERSTART:
                        indlæsIndbetalinger(scanner, medlem.getIndbetalingList());
                        break;
                    case END:
                        medlemList.add(medlem);
                        break;
                    default:
                        switch (token)
                        {
                            case MEDLEMSNUMMER:
                                medlem.setMedlemsnummer(Integer.parseInt(line));
                                break;
                            case NAVN:
                                medlem.setNavn(line);
                                break;
                            case FØDSELSDATO:
                                medlem.setFødselsdato(LocalDate.parse(line));
                                break;
                            case TLF:
                                medlem.setTlf(line);
                                break;
                            case KØN:
                                medlem.setKøn(line);
                                break;
                        }

                }
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

    }

    private void indlæsIndbetalinger(Scanner scanner, List<Indbetaling> indbetalingList)
    {
        String line = "";
        String token = "";
        Indbetaling indbetaling = null;
        boolean flereIndbetalinger = true;

        while (flereIndbetalinger && scanner.hasNext())
        {
            line = scanner.nextLine();

            switch (line)
            {
                case INDBETALINGSTART:
                    indbetaling = new Indbetaling();
                    token = INDBETALINGSTART;
                    break;
                case BELØB:
                    token = BELØB;
                    break;
                case INDBETALINGSDATO:
                    token = INDBETALINGSDATO;
                    break;
                case INDBETALINGSLUT:
                    token = INDBETALINGSLUT;
                    indbetalingList.add(indbetaling);
                    break;
                case INDBETALINGERSLUT:
                    flereIndbetalinger = false;
                    break;
                default:
                    switch (token)
                    {
                        case BELØB:
                            indbetaling.setBeløb(Integer.parseInt(line));
                            break;
                        case INDBETALINGSDATO:
                            indbetaling.setDato(LocalDate.parse(line));
                            break;
                    }
            }
        }


    }
}
