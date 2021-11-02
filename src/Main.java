import java.time.LocalDate;
import java.time.Month;


public class Main {

    public static void main(String[] args) {

       Klub viking = new Klub("Viking");

       viking.tilføjMedlem(new Medlem(1, "John Snow", LocalDate.of(2007, Month.JUNE, 21), "+463434343", "Mand"));
       viking.tilføjMedlem(new Medlem(2, "Khaleeesi", LocalDate.of(1981, Month.FEBRUARY, 5), "+012323232", "Mother Dragons"));
       viking.tilføjMedlem(new Medlem(3, "The Hound", LocalDate.of(1945, Month.MAY, 13), "+4523232343", "Mand"));

       Medlem m1 = viking.findMedlem(3);  // Skal kunne returnere "The Hound i et objekt
       m1.indsætBetaling(1000);
       m1.indsætBetaling(250);
       m1.indsætBetaling(-100);

       System.out.println(m1.lavBetalingsoversigt());

       System.out.println("********* Medlemsliste *************");

       System.out.println(viking.lavMedlemsliste());

       System.out.println("Antal medlemmer: " + viking.antalMedlemmer());

    }
}
