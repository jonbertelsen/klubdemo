import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Tools
{
    public static OrdbogsAdaptor ordbog = new OrdbogsAdaptor();

    public static Map<String, String> indlæsOrdbog(String filnavn)
    {
        Map<String, String> fraser = new HashMap<>();

        File csvFil = new File(filnavn);
        String line = "";
        String nøgle;
        String værdi;
        int førsteKommaPosition;

        try (Scanner scanner = new Scanner(csvFil))  // try-with-ressources
        {
            while (scanner.hasNext())
            {
                line = scanner.nextLine();
                førsteKommaPosition = line.indexOf(',');
                nøgle = line.substring(0, førsteKommaPosition);
                værdi = line.substring(førsteKommaPosition + 1);
                fraser.put(nøgle, værdi);
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Der er sket en fejl under indlæsning af en ordbog");
        }
        return fraser;
    }

    public static int inputTal(String ledetekst)
    {
        Scanner input = new Scanner(System.in);
        System.out.println(ledetekst);
        String line = input.nextLine();
        return Integer.parseInt(line);
    }

    public static String inputTekst(String ledetekst)
    {
        Scanner input = new Scanner(System.in);
        System.out.println(ledetekst);
        String line = input.nextLine();
        return line;
    }

    public static LocalDate inputLocalDate(String ledetekst)
    {
        // 2005-01-15
        Scanner input = new Scanner(System.in);
        System.out.println(ledetekst + " (åååå-mm-dd):");
        String line = input.nextLine();
        return LocalDate.parse(line);
    }
}
