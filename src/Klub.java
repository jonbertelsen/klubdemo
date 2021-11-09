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
}
