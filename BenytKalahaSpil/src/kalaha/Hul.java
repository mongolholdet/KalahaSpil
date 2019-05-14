package kalaha;

public class Hul
{

    private int antalKugler;
    private int hulNummer;
    private int spillerNummer;

    public Hul(int initAntalKugler, int initHulNummer, int initSpillerNummer)
    {
        antalKugler = initAntalKugler;
        hulNummer = initHulNummer;
        spillerNummer = initSpillerNummer;
    }

    public Hul(String hulData)
    {
        String[] hulDataArray = hulData.split("\\.");
        spillerNummer = Integer.parseInt(hulDataArray[0].substring(1, hulDataArray[0].length()));
        hulNummer = Integer.parseInt(hulDataArray[1]);
        antalKugler = Integer.parseInt(hulDataArray[2].substring(0, hulDataArray[2].length() - 1));
    }

    public int getAntalKugler()
    {
        return antalKugler;
    }

    public int getHulNummer()
    {
        return hulNummer;
    }

    public int getSpillerNummer()
    {
        return spillerNummer;
    }

    public void setAntalKugler(int setAntalKugler)
    {
        antalKugler = setAntalKugler;
    }

    public void setHulNummer(int setAntalKugler)
    {
        hulNummer = setAntalKugler;
    }

    public void setSpillerNummer(int setSpillerNummer)
    {
        spillerNummer = setSpillerNummer;
    }

    public String toString()
    {
        return "Hullet har: " + antalKugler + " kugler, nummeret: " + hulNummer + " og tilhører spiller: " + spillerNummer;
    }

    public String toData()
    {
        return "[" + spillerNummer + "." + hulNummer + "." + antalKugler + "]";
    }
}
