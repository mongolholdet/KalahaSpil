package kalaha;

import java.util.Arrays;

public class Kalaha
{
    private Spiller[] kalahaSpillere;
    private Spilleplade kalahaSpilleplade;
    private String[] IPAdresser;
    private boolean startTur = true;

    public Kalaha(int initAntalSpillere, int initAntalKugler, int initAntalKuglerIMaal, String initIPAdresser[], int initPortNummer) throws Exception
    {
        Arrays.sort(initIPAdresser);
        IPAdresser = initIPAdresser;

        kalahaSpilleplade = new Spilleplade(initAntalSpillere, initAntalKugler, initAntalKuglerIMaal);

        kalahaSpillere = new Spiller[initAntalSpillere + 1]; // Spiller bruger ikke spiller numre: 0..n, men 1..n
        for (int i = 1; i < initAntalSpillere + 1; i++)
        {
            kalahaSpillere[i] = new Spiller(i, initIPAdresser, initPortNummer);
            i++;
        }
    }

    public void printIPAdresser() // Ser ud til at sortere IP adresser rigtigt? 
    {
        for (String IPadresse : IPAdresser)
        {
            System.out.println(IPadresse);
        }
    }

    public void printSpillerInfo() throws Exception// Ser ud til at sortere IP adresser rigtigt? 
    {
        for (Spiller spiller : kalahaSpillere)
        {
            System.out.println(spiller.getSpillerNummer() + spiller.getIPAdresse() + getSpillerPoint(spiller.getSpillerNummer()));
        }
    }

    public int getSpillerPoint(int spillerNummer)
    {
        String hulDataString = kalahaSpilleplade.toData();
        String[] hulDataStringArray = hulDataString.split(",");

        int i = 0;
        while (true)
        {
            if (hulDataStringArray[i].contains(String.valueOf(spillerNummer)) && hulDataStringArray[i].contains("{"))
            {
                break;
            }
            else
            {
                i++;
            }
        }
        String pointString = hulDataStringArray[i].split("\\.")[2];
        int point = Integer.parseInt(pointString.substring(0, pointString.length() - 1));
        return point;
    }

    public boolean tur(Spiller turSpiller, Hul valgtHul) throws Exception
    {
        if (valgtHul.toData().contains("[") && valgtHul.getAntalKugler() != 0) // Hvis spiller vælger et normalt hul som ikke er tomt
        {
            if (startTur && valgtHul.getSpillerNummer() == turSpiller.getSpillerNummer())
            {
                turSpiller.setKuglerIHaand(valgtHul.getAntalKugler());
                valgtHul.setAntalKugler(0);
                startTur = false; // Spiller bliver "løsladt" til resten af banen
                turSpiller.sendHulStatus(valgtHul);
                return true;
            }

            else if (turSpiller.getKuglerIHaand() > 0)
            {
                valgtHul.setAntalKugler(valgtHul.getAntalKugler()+1);
                turSpiller.setKuglerIHaand(turSpiller.getKuglerIHaand()-1);
                startTur = false; // Spiller bliver "løsladt" til resten af banen
                return true;
            }
        }

        if (turSpiller.getKuglerIHaand() == 0) // Spiller afslutter i eget mål
        {
            if (startTur == false && valgtHul.toData().contains("{") && valgtHul.getSpillerNummer() == turSpiller.getSpillerNummer())
            {
                startTur = true; // Spiller skal starte i sin egne del af banen
                turSpiller.sendHulStatus(valgtHul);
                return true;
            }
            else
            {
                return false;
            }
        }
        return true;
    }
}
