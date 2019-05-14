package kalaha;

import java.util.Arrays;

public class Kalaha
{

    private Spiller[] kalahaSpillere;
    private Spilleplade kalahaSpilleplade;
    private String[] IPAdresser;
    private int portNummer;
    private boolean startTur = true;
    private int sidsteHul = 0;

    public Kalaha(int initAntalSpillere, int initAntalKugler, int initAntalKuglerIMaal, String initIPAdresser[], int initPortNummer) throws Exception
    {
        Arrays.sort(initIPAdresser);
        IPAdresser = initIPAdresser;
        portNummer = initPortNummer;

        kalahaSpilleplade = new Spilleplade(initAntalSpillere, initAntalKugler, initAntalKuglerIMaal);
        System.out.println(kalahaSpilleplade.toData());

        kalahaSpillere = new Spiller[initAntalSpillere + 1]; // Spiller bruger ikke spiller numre: 0..n, men 1..n
        //kalahaSpillere[0] = new Spiller(0, IPAdresser, portNummer);

        for (int i = 1; i < initAntalSpillere + 1; i++)
        {
            kalahaSpillere[i] = new Spiller(i, IPAdresser, portNummer);
            System.out.println(kalahaSpillere[i]);
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

    /*
    public void printSpillerInfo() throws Exception// Ser ud til at sortere IP adresser rigtigt? 
    {
        for (Spiller spiller : kalahaSpillere)
        {
            System.out.println(spiller.getSpillerNummer() + spiller.getIPAdresse() + getSpillerPoint(spiller.getSpillerNummer()));
        }
    }
     */
    public Spiller getSpiller(int spillerNummer)
    {

        if (spillerNummer > 0)
        {
            return kalahaSpillere[spillerNummer];
        } else
        {
            return kalahaSpillere[0]; // Tom spiller
        }
    }

    public String getHul(int hulNummer)
    {
        String hulDataString = kalahaSpilleplade.toData();
        String[] hulDataStringArray = hulDataString.split(",");
        int i = 0;
        int hul = 0;
        while (true) // Skal lede efter spillernummeret i alle huller og derfra finde Målene
        {
            if (hulDataStringArray[i].contains("{") && hulDataStringArray[i].contains(String.valueOf(hulNummer))) // Er et Mål og indeholder det angivne tal.
            {
                String spillerString = hulDataStringArray[i].split("\\.")[0]; // Første felt i et Maal Hul, splittet efter ".", altså f.eks.{1 eller {10 
                hul = Integer.parseInt(spillerString.substring(1, spillerString.length())); // Sletter første element i det tidligere trin, altså {1 eller {10 bliver til 1 eller 10 
                if (hul == hulNummer)
                {
                    break;
                }
            }
            i++;
        }
        return hulDataStringArray[i];
    }

    public int getSpillerPoint(int spillerNummer)
    {
        String hulDataString = kalahaSpilleplade.toData();
        String[] hulDataStringArray = hulDataString.split(",");
        for (String lort : hulDataStringArray)
        {
            System.out.println(lort);
        }
        int i = 0;
        int spiller = 0;
        while (i < hulDataStringArray.length) // Skal lede efter spillernummeret i alle huller og derfra finde Målene
        {
            if (hulDataStringArray[i].contains("{") && hulDataStringArray[i].contains(String.valueOf(spillerNummer))) // Er et Mål og indeholder det angivne tal.
            {
                String spillerString = hulDataStringArray[i].split("\\.")[0]; // Første felt i et Maal Hul, splittet efter ".", altså f.eks.{1 eller {10 
                spiller = Integer.parseInt(spillerString.substring(1, spillerString.length())); // Sletter første element i det tidligere trin, altså {1 eller {10 bliver til 1 eller 10 
                if (spiller == spillerNummer)
                {
                    break;
                } else
                {
                    i++;
                }
            } else
            {
                i++;
            }
            System.out.println(i);
        }
        String pointString = hulDataStringArray[i].split("\\.")[2]; // Det rigtige felt er fundet, pointene skal nu findes, det er i tredje element
        int point = Integer.parseInt(pointString.substring(0, pointString.length() - 1));
        return point;
    }

    public int getAntalKuglerIHul(int hulNummer)
    {
        String hulDataString = kalahaSpilleplade.toData();

        String[] hulDataStringArray = hulDataString.split(",");
        String hulDataStringTrimmed = hulDataStringArray[hulNummer].substring(1, hulDataStringArray[hulNummer].length() - 1);
        String[] hulDataStringSplitted = hulDataStringTrimmed.split("\\.");

        return Integer.parseInt(hulDataStringSplitted[2]);
    }

    public String toData()
    {
        return kalahaSpilleplade.toData();
    }

        /*
        Brugsscenarier:
        Spiller starter, skal starte i sin del af banen.
        Spiller skal tømme starthullet.
        Spiller har startet spillet, skal lægge en kugle i et hul med et nummer et større end sidste hul.
        Spiller har stadigvæk kugler, men sidste hul er nået, næste hul er første hul.
        Spiller skal ikke sætte point i fjendens mål.
        Spiller skal sætte point i sit eget mål.
        Spiller skal tømme hul hvor sidste kugle lander og fortsætte.
        Spiller tømmer hånd i et tomt hul, afslut tur.
        Spiller tømmer hånd i mål, start en ny tur
         */
    public void tur(Spiller turSpiller, Hul valgtHul) throws Exception
    {
        if (valgtHul.toData().contains("[")) // Hvis der bliver valgt et normalt hul
        {
            if (startTur) // Hvis det er startturen
            {
                startTur = false;   //ikke starttur
                
                if (turSpiller.getSpillerNummer() == valgtHul.getSpillerNummer()) // Hvis hullet er på den rigtige side  
                {
                    turSpiller.setKuglerIHaand(valgtHul.getAntalKugler()); // Saml kuglerne op
                    valgtHul.setAntalKugler(0); // Tøm hullet
                    sidsteHul = valgtHul.getHulNummer(); // Gem at dette er det sidste brugte hul
                }
            }
            if (valgtHul.getHulNummer() == kalahaSpilleplade.getAntalHuller())  //
            {
                sidsteHul = 0;
            } else
            {
                if (valgtHul.getHulNummer() == sidsteHul + 1) // Hvis næste valgte hul er det næste hul på pladen.
                {
                    if (turSpiller.getKuglerIHaand() == 1) // hvis 
                    {

                        turSpiller.setKuglerIHaand(valgtHul.getAntalKugler() + turSpiller.getKuglerIHaand()); // Saml kuglerne op plus den man havde i hånden
                        valgtHul.setAntalKugler(0); // Tøm hullet
                        sidsteHul = valgtHul.getHulNummer(); // Gem at dette er det sidste brugte hul

                    } else
                    {
                        turSpiller.setKuglerIHaand(turSpiller.getKuglerIHaand() - 1); // Fjern en kugle fra hånden
                        valgtHul.setAntalKugler(valgtHul.getAntalKugler() + 1); // Læg en kugle i hullet
                        sidsteHul = valgtHul.getHulNummer(); // Gem at dette er det sidste brugte hul
                    }
                } else
                {
                    if (valgtHul.getHulNummer() == sidsteHul + 2) // Hvis det valgte hul springer to huller over
                    {
                        if ((sidsteHul + 1) % 7 == 0) // Er det sidste hul et mål?
                        {
                            turSpiller.setKuglerIHaand(turSpiller.getKuglerIHaand() - 1); // Fjern en kugle fra hånden
                            valgtHul.setAntalKugler(valgtHul.getAntalKugler() + 1); // Læg en kugle i hullet
                            sidsteHul = valgtHul.getHulNummer(); // Gem at dette er det sidste brugte hul
                        }
                    }
                }
            }
        }
        if (valgtHul.toData().contains("{")) // Hvis der bliver valgt et maal
        {

            if (valgtHul.getSpillerNummer() == turSpiller.getSpillerNummer()) // Hvis der bliver valgt eget hul
            {
                turSpiller.setKuglerIHaand(turSpiller.getKuglerIHaand() - 1); // Fjern en kugle fra hånden
                valgtHul.setAntalKugler(valgtHul.getAntalKugler() + 1); // Læg en kugle i hullet
                sidsteHul = valgtHul.getHulNummer();

                if (turSpiller.getKuglerIHaand() == 0) // Hvis der bliver afsluttet i mål, start forfra
                {
                    startTur = true;
                }
            }
        }

//        sidsteHul = kalahaSpilleplade.getAntalHuller() + 2; // Kan ikke bruges da 
        
    }
}

/*
if (valgtHul.toData().contains("[")) // Hvis der bliver valgt et normalt hul
        {
            if (startTur) // Hvis det er startturen
            {
                startTur = false;
                if (turSpiller.getSpillerNummer() == valgtHul.getSpillerNummer()) // Hvis hullet er på den rigtige side  
                {
                    turSpiller.setKuglerIHaand(valgtHul.getAntalKugler()); // Saml kuglerne op
                    valgtHul.setAntalKugler(0); // Tøm hullet
                    sidsteHul = valgtHul.getHulNummer(); // Gem at dette er det sidste brugte hul
                }
            }
            if (valgtHul.getHulNummer() == kalahaSpilleplade.getAntalHuller())
            {
                sidsteHul = 0;
            } 
            else
            {
                if (valgtHul.getHulNummer() == sidsteHul + 1) // Hvis næste valgte hul er det næste hul på pladen.
                {
                    if (turSpiller.getKuglerIHaand() == 1) // hvis 
                    {
                        if (valgtHul.getAntalKugler() == 0)
                        {
                            return false;
                        } else
                        {
                            turSpiller.setKuglerIHaand(valgtHul.getAntalKugler() + turSpiller.getKuglerIHaand()); // Saml kuglerne op plus den man havde i hånden
                            valgtHul.setAntalKugler(0); // Tøm hullet
                            sidsteHul = valgtHul.getHulNummer(); // Gem at dette er det sidste brugte hul
                            return true;
                        }
                    } else
                    {
                        turSpiller.setKuglerIHaand(turSpiller.getKuglerIHaand() - 1); // Fjern en kugle fra hånden
                        valgtHul.setAntalKugler(valgtHul.getAntalKugler() + 1); // Læg en kugle i hullet
                        sidsteHul = valgtHul.getHulNummer(); // Gem at dette er det sidste brugte hul
                        return true;

                    }
                } else
                {
                    if (valgtHul.getHulNummer() == sidsteHul + 2) // Hvis det valgte hul springer to huller over
                    {
                        if ((sidsteHul + 1) % 7 == 0) // Er det sidste hul et mål?
                        {
                            turSpiller.setKuglerIHaand(turSpiller.getKuglerIHaand() - 1); // Fjern en kugle fra hånden
                            valgtHul.setAntalKugler(valgtHul.getAntalKugler() + 1); // Læg en kugle i hullet
                            sidsteHul = valgtHul.getHulNummer(); // Gem at dette er det sidste brugte hul
                            return true;
                        }
                    }
                }
            }
        }
        if (valgtHul.toData().contains("{")) // Hvis der bliver valgt et maal
        {

            if (valgtHul.getSpillerNummer() == turSpiller.getSpillerNummer()) // Hvis der bliver valgt eget hul
            {
                turSpiller.setKuglerIHaand(turSpiller.getKuglerIHaand() - 1); // Fjern en kugle fra hånden
                valgtHul.setAntalKugler(valgtHul.getAntalKugler() + 1); // Læg en kugle i hullet
                sidsteHul = valgtHul.getHulNummer();

                if (turSpiller.getKuglerIHaand() == 0) // Hvis der bliver afsluttet i mål, start forfra
                {
                    startTur = true;
                    return true;
                }
            }
        }

//        sidsteHul = kalahaSpilleplade.getAntalHuller() + 2; // Kan ikke bruges da 
        return false;
    }
 */

 /*
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
                valgtHul.setAntalKugler(valgtHul.getAntalKugler() + 1);
                turSpiller.setKuglerIHaand(turSpiller.getKuglerIHaand() - 1);
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
            else if (valgtHul.getAntalKugler() == 0)
            {
                return false;
            }
        }
        return true;
 */
