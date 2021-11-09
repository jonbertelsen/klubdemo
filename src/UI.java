import java.time.LocalDate;
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
            valg = inputTal("Vælg 1-4: ");

            switch (valg)
            {
                case 1:
                    System.out.println(klub.lavMedlemsliste());
                    break;
                case 2:
                    mnr = inputTal("Indtast medlemsnummer: ");
                    String navn = inputTekst("Indtast navn: ");
                    LocalDate fødselsdato = inputLocalDate("Indtast fødselsdato ");
                    String tlf = inputTekst("Indtast tlfnr: ");
                    String køn = inputTekst("Indtast køn (Mand/Kvinde): ");
                    klub.tilføjMedlem(new Medlem(mnr, navn, fødselsdato,tlf, køn));
                    break;
                case 3:
                    mnr = inputTal("Indtast medlemsnummer: ");
                    Medlem medlem = klub.findMedlem(mnr);
                    if (medlem != null)
                    {
                        System.out.println(medlem.lavBetalingsoversigt());
                    } else
                    {
                        System.out.println("Medlem med nr. " + mnr + " findes desværre ikke. Prøv igen.");
                    }
                    break;
                case 9:
                    running = false;
                    klub.gemMedlemmer("viking.csv");
                    System.out.println("Tak for denne gang");
                    break;
            }

        }
    }

    private static void udskrivMenu()
    {
        System.out.println("---------------- Menu -----------------------------------------------------------");
        System.out.println("| 1. Vis medlemmer | 2. Opret nyt medlem | 3. Vis betalingsoversigt | 9. Afslut | ");
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
