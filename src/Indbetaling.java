import java.time.LocalDate;

public class Indbetaling
{
    // Attributter
    private int beløb;
    private LocalDate dato;

    // Constructors
    public Indbetaling(int beløb)
    {
        this.beløb = beløb;
        this.dato = LocalDate.now();
    }

    // Gettere og Settere

    public int getBeløb()
    {
        return beløb;
    }

    public LocalDate getDato()
    {
        return dato;
    }


    // ToString etc

    @Override
    public String toString()
    {
        return "(" + beløb + " kr. " + dato + ")";
    }


    // Egne metoder
}

/*
Metoder:


*/
