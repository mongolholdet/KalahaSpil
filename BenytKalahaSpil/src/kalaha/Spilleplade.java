/*
    Lavet af: C. Bjørner, U. Esbjørn, M. Repnak, H. Warncke
 */
package kalaha;

import java.util.ArrayList;

public class Spilleplade
{

    private int antalSpillere;
    private int antalKugler;

    public Spilleplade(int initAntalSpillere, int initAntalKugler)
    {
        if (initAntalSpillere == 2)
        {
            antalSpillere = initAntalSpillere;
            antalKugler = initAntalKugler;
            genererHuller();
        }

        if (initAntalSpillere == 4)
        {
            // Skal implementeres i GUI delen først.
            antalSpillere = 2;//initAntalSpillere;
            antalKugler = initAntalKugler;
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
        System.out.println(antalHuller);
        //Antallet af huller, med mål inkluderet (6 huller per spiller og et mål per spiller)
        double spillerNummer = 1;
        ArrayList<Hul> spillePladeHuller = new ArrayList<Hul>();
        for (int i = 0; i < antalHuller; i++)
        {


            if (i % 7 == 0)
            {
                spillePladeHuller.add(new Maal(0, (int)spillerNummer));
                            System.out.println("Maal oprettet: " + spillerNummer);

            }
            else
            {
                spillePladeHuller.add(new Hul(antalKugler, (int)spillerNummer));
            }            spillerNummer = Math.ceil((double)i / (double)7);
            if (spillerNummer == 0)
            {
                spillerNummer = 1;
            }
            
            System.out.println("Spillernummer: " + spillerNummer);
                        System.out.println("i: " + i);

        }
        System.out.println(spillePladeHuller);
    }
}