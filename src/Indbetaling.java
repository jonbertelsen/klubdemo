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

    public Indbetaling()
    {
        this.dato = LocalDate.now();
    }

    // Gettere og Settere

    public void setBeløb(int beløb)
    {
        this.beløb = beløb;
    }

    public void setDato(LocalDate dato)
    {
        this.dato = dato;
    }

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
