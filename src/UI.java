import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UI
{
    public static void hovedMenu(Klub klub)
    {
        boolean running = true;
        int valg;
        int mnr;

        while (running)
        {
            udskrivMenu();
            valg = Tools.inputTal(Tools.ordbog.hentFrase("menuvalg"));

            switch (valg)
            {
                case 1:
                    System.out.println(klub.lavMedlemsliste()); break;
                case 2:
                    opretMedlem(klub); break;
                case 3:
                    visBetalinger(klub); break;
                case 4:
                    Tools.ordbog.skiftOrdbog(); break;
                case 5:
                    visAntalMedlemmer(klub); break;
                case 6:
                    fjernMedlem(klub); break;
                case 7:
                    indsætBetaling(klub); break;
                case 8:
                    redigerMedlem(klub); break;
                case 9:
                    running = false;
                    klub.gemMedlemmer("viking.csv");
                    System.out.println("Tak for denne gang");
                    break;
            }

        }
    }

    private static void redigerMedlem(Klub klub)
    {
        boolean flereÆndringer = true;
        int medlemsNummer = Tools.inputTal(Tools.ordbog.hentFrase("indtastmedlemsnummer"));
        Medlem medlem = klub.findMedlem(medlemsNummer);
        if (medlem != null)
        {
            while (flereÆndringer)
            {
                int valg = Tools.inputTal("Hvad vil du ændre? 1) Navn 2) Fødselsdato 3) Tlf 4) Køn 5) Afslut: ");
                switch (valg)
                {
                    case 1:
                        String nytNavn = Tools.inputTekst("Indtast nyt navn: ");
                        medlem.setNavn(nytNavn);
                        break;
                    case 2:
                        LocalDate nyFødselsdato = Tools.inputLocalDate("Indtast ny dato (åååå-mm-dd)");
                        medlem.setFødselsdato(nyFødselsdato);
                        break;
                    case 3:
                        String nyTlf = Tools.inputTekst("Indtast nye tlf nr: ");
                        medlem.setTlf(nyTlf);
                        break;
                    case 4:
                        String nytKøn = Tools.inputTekst("Indtast nyt køn: ");
                        medlem.setKøn(nytKøn);
                        break;
                    case 5:
                        flereÆndringer = false;
                }
            }
        } else
        {
            System.out.println(String.format(Tools.ordbog.hentFrase("medlemikkefundet"), medlemsNummer));
        }
    }

    private static void indsætBetaling(Klub klub)
    {
        int medlemsNummer = Tools.inputTal(Tools.ordbog.hentFrase("indtastmedlemsnummer"));
        Medlem medlem = klub.findMedlem(medlemsNummer);
        if (medlem != null)
        {
            int beløb = Tools.inputTal("Indtast beløb: ");
            medlem.indsætBetaling(beløb);
        } else
        {
            System.out.println(String.format(Tools.ordbog.hentFrase("medlemikkefundet"), medlemsNummer));
        }
    }

    private static void fjernMedlem(Klub klub)
    {
        int medlemsNummer = Tools.inputTal(Tools.ordbog.hentFrase("indtastmedlemsnummer"));
        Medlem medlem = klub.findMedlem(medlemsNummer);
        if (medlem != null)
        {
            klub.fjernMedlem(medlem);
        } else
        {
            System.out.println(String.format(Tools.ordbog.hentFrase("medlemikkefundet"), medlemsNummer));
        }
    }

    private static void visAntalMedlemmer(Klub klub)
    {
        System.out.println(Tools.ordbog.hentFrase("antalmedlemmer") + klub.antalMedlemmer());
    }

    private static void visBetalinger(Klub klub)
    {
        int mnr;
        mnr = Tools.inputTal("Indtast medlemsnummer: ");
        Medlem medlem = klub.findMedlem(mnr);
        if (medlem != null)
        {
            System.out.println(medlem.lavBetalingsoversigt());
        } else
        {
            System.out.println("Medlem med nr. " + mnr + " findes desværre ikke. Prøv igen.");
        }
    }

    private static void opretMedlem(Klub klub)
    {
        int mnr;
        mnr = Tools.inputTal("Indtast medlemsnummer: ");
        String navn = Tools.inputTekst("Indtast navn: ");
        LocalDate fødselsdato = Tools.inputLocalDate("Indtast fødselsdato ");
        String tlf = Tools.inputTekst("Indtast tlfnr: ");
        String køn = Tools.inputTekst("Indtast køn (Mand/Kvinde): ");
        klub.tilføjMedlem(new Medlem(mnr, navn, fødselsdato,tlf, køn));
    }

    private static void udskrivMenu()
    {
        System.out.println(Tools.ordbog.hentFrase("menuheader"));
        System.out.println(Tools.ordbog.hentFrase("menupunkter"));
        System.out.println("---------------------------------------------------------------------------------");
    }

}
