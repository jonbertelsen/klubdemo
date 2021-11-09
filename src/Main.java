public class Main {

    public static void main(String[] args) {

       Klub viking = new Klub("Viking");
       viking.indlæsMedlemmer("viking.csv");
       Medlem m1 = viking.findMedlem(3);  // Skal kunne returnere "The Hound i et objekt
       m1.indsætBetaling(1000);
       m1.indsætBetaling(250);
       m1.indsætBetaling(-100);

       UI.hovedMenu(viking);
    }

    // TODO: Gemme indbetalinger
    // TODO: Indlæse indbetalinger
    // TODO: Fjerne et medlem
    // TODO: Tilføje en indbetaling til et medlem
    // TODO: Rediger medlemsoplysninger
    // TODO: Udskriv antal medlemmer

}
