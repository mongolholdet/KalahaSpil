package kalaha;

public class Maal extends Hul
{
    public Maal(int initAntalKugler, int initSpillerNummer)
    {
        super(initAntalKugler, initSpillerNummer); 
    }

    private boolean maal;

    public int point()
    {
        return getAntalKugler();
    }
    
    public String toString()
    {
        return "Spiller: " + getSpillerNummer() + " har: " + getAntalKugler() + " point";
    }
}