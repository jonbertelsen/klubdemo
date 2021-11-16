public class Main {

    public static void main(String[] args) {

       Klub viking = new Klub("Viking");
       viking.indlæsMedlemmer("viking.csv");

//       Medlem m1 = viking.findMedlem(3);  // Skal kunne returnere "The Hound i et objekt
//       m1.indsætBetaling(1000);
//       m1.indsætBetaling(250);
//       m1.indsætBetaling(-100);

       UI.hovedMenu(viking);
    }

    // DONE: Gemme indbetalinger
    // DONE: Indlæse indbetalinger
    // DONE: Skift sprog, så man kan bruge flere sprog i programmet
    // DONE: flyt switch kode ud i metoder (xsmall)
    // DONE: Udskriv antal medlemmer (xsmall)
    // DONE: Fjerne et medlem (small)
    // DONE: Tilføje en indbetaling til et medlem (medium)
    // TODO: Rediger medlemsoplysninger (large)

    // TODO: Lav Switch om til command pattern (large)


}
