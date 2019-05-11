/*
    Lavet af: C. Bjørner, U. Esbjørn, M. Repnak, H. Warncke
 */
package kalaha;

import java.util.ArrayList;

public class Spilleplade
{
    private int antalSpillere;
    private int antalKugler;
    private int antalKuglerIMaal;
    private ArrayList<Hul> spillePladeHuller = new ArrayList<Hul>();

    public Spilleplade(int initAntalSpillere, int initAntalKugler, int initAntalKuglerIMaal)
    {
        if (initAntalSpillere == 2)
        {
            antalSpillere = initAntalSpillere;
            antalKugler = initAntalKugler;
            antalKuglerIMaal = initAntalKuglerIMaal;
            genererHuller();
        }

        if (initAntalSpillere == 4)// Skal implementeres i GUI delen først, laver alligevel en spillerplade med 2 spillere
        {
            antalSpillere = 4;
            antalKugler = initAntalKugler;
            antalKuglerIMaal = initAntalKuglerIMaal;
            genererHuller();
        }
    }

    public int getAntalSpillere()
    {
        return antalSpillere;  
    }

    private void genererHuller()
    {
        int antalHuller = (antalSpillere * 6) + antalSpillere;
        //Antallet af huller, med mål inkluderet (6 huller per spiller og et mål per spiller)

        double spillerNummer;

        for (int i = 0; i < antalHuller; i++)
        {
            spillerNummer = Math.ceil((double)i / (double)6);
            // afrundet et kommatal opad til et heltal som repræsenterer en spiller. Alle kommatal mindre og lig med 1 er lig med 1 og alle kommatal større end 1 men mindre eller lig med 2 er lig med 2, osv.
            if (spillerNummer == 0)
            {
                spillerNummer = 1;
                // "Doven" løsning til ovenstående spillerNummer kode. Da i starter på 0, kommer det første spillerNummer til at være lig med 0/6, som altså er 0, selvom 1 ønskes.
            }
            if (i % 7 == 0)
            {
                spillePladeHuller.add(new Maal(antalKuglerIMaal, i, (int)spillerNummer));
                // Hvert syvende hul skal være et Maal objekt
            }
            else
            {
                spillePladeHuller.add(new Hul(antalKugler, i, (int)spillerNummer));
            }
        }
        //System.out.println(spillePladeHuller);
    }

    public String toString()
    {
        return spillePladeHuller.toString();
    }

    public String toData()
    {
        String data = new String();
        for (Hul hul : spillePladeHuller)
        {
            data += hul.toData() + ",";
        }
        return data.substring(0, data.length() - 1);
    }
}
