package kalaha;

public class Maal extends Hul
{
    private boolean maal;
    
    public String point()
    {
        return "Spiller: " + getSpillerNummer() + " har: " + getAntalKugler() + " point.";
    }
}