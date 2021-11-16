import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UI
{

    private static Map<String, String> fraser = new HashMap<>();

    public static void hovedMenu(Klub klub)
    {
        opretSprogLeksikon("engelsk");


        boolean running = true;
        int valg;
        int mnr;


        while (running)
        {
            udskrivMenu();
            valg = inputTal(fraser.get("menuvalg"));

            switch (valg)
            {
                case 1:
                    System.out.println(klub.lavMedlemsliste()); break;
                case 2:
                    opretMedlem(klub); break;
                case 3:
                    visBetalinger(klub); break;
                case 4:
                    skiftSprog(); break;
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
        int medlemsNummer = inputTal(fraser.get("indtastmedlemsnummer"));
        Medlem medlem = klub.findMedlem(medlemsNummer);
        if (medlem != null)
        {
            // Hvad vil du ændre?
            // 1) Navn 2) Fødselsdato 3) Tlf 4) Køn
            String nytNavn = inputTekst("Indtast nyt navn: ");
            medlem.setNavn(nytNavn);
        } else
        {
            System.out.println(String.format(fraser.get("medlemikkefundet"), medlemsNummer));
        }
    }

    private static void indsætBetaling(Klub klub)
    {
        int medlemsNummer = inputTal(fraser.get("indtastmedlemsnummer"));
        Medlem medlem = klub.findMedlem(medlemsNummer);
        if (medlem != null)
        {
            int beløb = inputTal("Indtast beløb: ");
            medlem.indsætBetaling(beløb);
        } else
        {
            System.out.println(String.format(fraser.get("medlemikkefundet"), medlemsNummer));
        }
    }

    private static void fjernMedlem(Klub klub)
    {
        int medlemsNummer = inputTal(fraser.get("indtastmedlemsnummer"));
        Medlem medlem = klub.findMedlem(medlemsNummer);
        if (medlem != null)
        {
            klub.fjernMedlem(medlem);
        } else
        {
            System.out.println(String.format(fraser.get("medlemikkefundet"), medlemsNummer));
        }
    }

    private static void visAntalMedlemmer(Klub klub)
    {
        System.out.println(fraser.get("antalmedlemmer") + klub.antalMedlemmer());
    }

    private static void visBetalinger(Klub klub)
    {
        int mnr;
        mnr = inputTal("Indtast medlemsnummer: ");
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
        mnr = inputTal("Indtast medlemsnummer: ");
        String navn = inputTekst("Indtast navn: ");
        LocalDate fødselsdato = inputLocalDate("Indtast fødselsdato ");
        String tlf = inputTekst("Indtast tlfnr: ");
        String køn = inputTekst("Indtast køn (Mand/Kvinde): ");
        klub.tilføjMedlem(new Medlem(mnr, navn, fødselsdato,tlf, køn));
    }

    private static void skiftSprog()
    {
        int sprog = inputTal(fraser.get("sprogvalg"));
        switch (sprog)
        {
            case 1: opretSprogLeksikon("dansk"); break;
            case 2: opretSprogLeksikon("engelsk"); break;
            default:
                System.out.println("Det sprog findes ikke");
        }

    }

    private static void opretSprogLeksikon(String sprog)
    {
        fraser.clear();
        switch (sprog)
        {
            case "dansk":
                fraser.put("menuheader", "---------------- Hovedmenu -----------------------------------------------------------");
                fraser.put("menupunkter", "| 1. Vis medlemmer | 2. Opret nyt medlem | 3. Vis betalingsoversigt | 4. Skrift sprog | 5. Vis antal medlemmer | 6. Fjern medlem | 7. Indsæt betaling | 9. Afslut | ");
                fraser.put("menuvalg", "Vælg (1-9): ");
                fraser.put("sprogvalg", "Vælg sprog (1 for dansk eller 2 for engelsk): ");
                fraser.put("antalmedlemmer", "Antal medlemmer: ");
                fraser.put("indtastmedlemsnummer", "Indtast medlemsnummer: ");
                fraser.put("medlemikkefundet", "Medlemsnummer %d findes ikke");
                fraser.put("indtastbeløb", "Indtast beløb: ");
                break;
            case "engelsk":
                fraser.put("menuheader", "---------------- Main menu -----------------------------------------------------------");
                fraser.put("menupunkter", "| 1. Show members | 2. Create new member | 3. Show payments | 4. Change language | 5. Show number of members | 6. Remove member | 7. Deposit payment |  9. Exit | ");
                fraser.put("menuvalg", "Choose (1-9): ");
                fraser.put("sprogvalg", "Pick your language (1 for danish or 2 for english): ");
                fraser.put("antalmedlemmer", "Number of members: ");
                fraser.put("indtastmedlemsnummer", "Enter member ID: ");
                fraser.put("medlemikkefundet", "Member ID %d doesn't exist");
                fraser.put("indtastbeløb", "Enter amount: ");
                break;

        }
    }

    private static void udskrivMenu()
    {
        System.out.println(fraser.get("menuheader"));
        System.out.println(fraser.get("menupunkter"));
        System.out.println("---------------------------------------------------------------------------------");
    }

    private static int inputTal(String ledetekst)
    {
        Scanner input = new Scanner(System.in);
        System.out.println(ledetekst);
        String line = input.nextLine();
        return Integer.parseInt(line);
    }

    private static String inputTekst(String ledetekst)
    {
        Scanner input = new Scanner(System.in);
        System.out.println(ledetekst);
        String line = input.nextLine();
        return line;
    }

    private static LocalDate inputLocalDate(String ledetekst)
    {
        // 2005-01-15
        Scanner input = new Scanner(System.in);
        System.out.println(ledetekst + " (åååå-mm-dd):");
        String line = input.nextLine();
        return LocalDate.parse(line);
    }

}
