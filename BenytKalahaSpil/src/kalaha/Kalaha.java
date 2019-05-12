package kalaha;

import java.util.Arrays;

public class Kalaha
{
    private Spiller[] kalahaSpillere;
    private Spilleplade kalahaSpilleplade;
    private String[] IPAdresser;

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
    
        public void printSpillerInfor() // Ser ud til at sortere IP adresser rigtigt? 
    {
        for (Spiller spiller : kalahaSpillere)
        {
            System.out.println(spiller.get);
        }
    }

    public void tur(Spiller turSpiller, Hul valgtHul)
    {
        if (!valgtHul.toData().contains("{"))
        {
            turSpiller.setKuglerIHaand(valgtHul.getAntalKugler());
            valgtHul.setAntalKugler(0);
        }

        {
            if (turSpiller.getKuglerIHaand() == 0)
            {
                if (valgtHul.toData().contains("{") && valgtHul.getSpillerNummer() == turSpiller.getSpillerNummer())
                {
                    // En ny tur 
                }
                else
                {
                    // Næste Spiller
                }
            }
        }
    }

}
