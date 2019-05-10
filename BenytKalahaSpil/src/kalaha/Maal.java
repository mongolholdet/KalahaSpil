package kalaha;

public class Maal extends Hul
{
    public Maal(int initAntalKugler, int initSpillerNummer)
    {
        super(initAntalKugler, initSpillerNummer);
    }

    private boolean maal;

    public String point()
    {
        return "Spiller: " + getSpillerNummer() + " har: " + getAntalKugler() + " point.";
    }
}
