package kalaha;

public class Hul
{
    private int antalKugler;
    private int spillerNummer;
    
    public Hul(int initAntalKugler, int initSpillerNummer)
    {
        antalKugler = initAntalKugler;
        spillerNummer = initSpillerNummer;
    }
    
    public int getAntalKugler()
    {
        return antalKugler;
    }
    
    public int getSpillerNummer()
    {
        return spillerNummer;
    } 
    
    public void setAntalKugler(int setAntalKugler)
    {
        antalKugler = setAntalKugler;
    }
    
    public void setSpillerNummer(int setSpillerNummer)
    {
        spillerNummer = setSpillerNummer;
    } 
    
    public String toString()
    {
        return "Hullet har: " + antalKugler + " kugler og tilhører spiller: " + spillerNummer;
    }
}
