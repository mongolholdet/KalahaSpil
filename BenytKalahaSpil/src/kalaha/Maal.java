package kalaha;

public class Maal extends Hul
{

    public Maal(int initAntalKugler, int initHulNummer, int initSpillerNummer)
    {
        super(initAntalKugler, initHulNummer, initSpillerNummer);
    }

    private boolean maal;

    public int point()
    {
        return getAntalKugler();
    }

    public String toString()
    {
        return "Spiller: " + getSpillerNummer() + " har: " + getAntalKugler() + " point i hul: " + getHulNummer();
    }

    public String toData()
    {
        return "{" + getSpillerNummer() + "." + getHulNummer() + "." + getAntalKugler() + "}";
    }
}
