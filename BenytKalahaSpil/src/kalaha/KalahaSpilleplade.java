/*
    Lavet af: C. Bjørner, U. Esbjørn, M. Repnak, H. Warncke
 */
package kalaha;

import java.util.ArrayList;
import java.util.Arrays;

public class KalahaSpilleplade
{
    
    private int antalSpillere;
    private int antalKugler;
    private int antalKuglerIMaal;
    private int antalHuller;
    private Spiller[] kalahaSpillere;
    private String[] IPAdresser;
    private int portNummer;
    private boolean startTur = true;
    private int sidsteHul = 0;
    private ArrayList<Hul> spillePladeHuller = new ArrayList<Hul>();
    
    public KalahaSpilleplade(int initAntalSpillere, int initAntalKugler, int initAntalKuglerIMaal, String initIPAdresser[], int initPortNummer) throws Exception
    {
        if (initAntalSpillere == 2)
        {
            antalSpillere = initAntalSpillere;
            antalKugler = initAntalKugler;
            antalKuglerIMaal = initAntalKuglerIMaal;
            IPAdresser = initIPAdresser;
            portNummer = initPortNummer;
            Arrays.sort(initIPAdresser);
            
            kalahaSpillere = new Spiller[initAntalSpillere + 1]; // Spiller bruger ikke spiller numre: 0..n, men 1..n
            //kalahaSpillere[0] = new Spiller(0, IPAdresser, portNummer);

            for (int i = 1; i < initAntalSpillere + 1; i++)
            {
                kalahaSpillere[i] = new Spiller(i, IPAdresser, portNummer);
                System.out.println(kalahaSpillere[i]);
                i++;
            }
            genererHuller();
        }
        
    }
    
    private void genererHuller()
    {
        antalHuller = (antalSpillere * 6) + antalSpillere;
        //Antallet af huller, med mål inkluderet (6 huller per spiller og et mål per spiller)
        double spillerNummer;
        
        for (int i = 1; i < antalHuller + 1; i++)
        {
            spillerNummer = Math.ceil((double) i / 7.0); // Math.ceil virker ikke hvis man bruger heltal, derfor cast til double
            if (spillerNummer == 0)
            {
                spillerNummer = 1;
                // "Doven" løsning til ovenstående spillerNummer kode. Da i starter på 0, kommer det første spillerNummer til at være lig med 0/6, som altså er 0, selvom 1 ønskes.
            }
            if (i % 7 != 0)
            {
                spillePladeHuller.add(new Hul(antalKugler, i, (int) spillerNummer));
            }
            else if (i % 7 == 0)
            {
                spillePladeHuller.add(new Maal(antalKuglerIMaal, i, (int) spillerNummer));
                // Hvert syvende hul skal være et Maal objekt
            }
        }
    }
    
    public String toData()
    {
        String data = new String();
        for (Hul hul : spillePladeHuller)
        {
            data += hul.toData() + ",";
        }
        return data.substring(0, data.length() - 1); // 
    }
    
    public String toHulData(Hul valgtHul)
    {
        return valgtHul.toData();
    }
    
    public Spiller getSpiller(int spillerNummer)
    {
        return kalahaSpillere[spillerNummer];
    }
    
    public Hul getHul(int hulNummer)
    {
        int i = 0;
        while (true)
        {
            if (hulNummer == spillePladeHuller.get(i).getHulNummer())
            {
                break;
            }
            i++;
        }
        return spillePladeHuller.get(i);
    }
    
    public void tur(Spiller turSpiller, Hul valgtHul) throws Exception
    {
        if (valgtHul.getAntalKugler() != 0 && startTur && valgtHul.toData().contains("["))
        {
            turSpiller.setKuglerIHaand(turSpiller.getKuglerIHaand() + valgtHul.getAntalKugler()); // Saml kuglerne op
            valgtHul.setAntalKugler(0); // Tøm hullet, virker ikke af mystiske årsager
            startTur = false;

            while (turSpiller.getKuglerIHaand() >= 1)    // Så længe spiller har kugler
            {
                // Sæt hul til det næste, VIRKER
                if (valgtHul.getHulNummer() < spillePladeHuller.size())
                {
                    // Hvis sidste hul
                    if(valgtHul.getHulNummer() + 1 == spillePladeHuller.size())
                    {
                        // Gå til første hul
                        valgtHul = getHul(1);
                    }
                    // Ellers gå til næste hul
                    else
                    {
                        valgtHul = getHul(valgtHul.getHulNummer() + 1);
                    }
                }
                
                // Sidste kugle
                if(turSpiller.getKuglerIHaand() == 1)
                {
                    // ved mål
                    if(valgtHul.toData().contains("{") && turSpiller.getSpillerNummer() == valgtHul.getSpillerNummer())
                    {
                        startTur = true;
                    }
                    // ved ikke tomt hul
                    else if(valgtHul.getAntalKugler() != 0)
                    {
                        // tag kugler fra næste hul
                        turSpiller.setKuglerIHaand(turSpiller.getKuglerIHaand() + valgtHul.getAntalKugler()); // Saml kuglerne op
                        valgtHul.setAntalKugler(0); // Tøm hullet
                        sidsteHul = valgtHul.getHulNummer() - 1;
                        startTur = true;
                        break;
                    }
                    // ved tomt hul
                    else
                    {
                        break;
                    }
                }
                // Læg kugle i hul
                turSpiller.setKuglerIHaand(turSpiller.getKuglerIHaand() - 1);
                valgtHul.setAntalKugler(valgtHul.getAntalKugler() + 1);
            }
        }
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
                    for (int i = 0; i < spillePladeHuller.size(); i++)
                    {
                        if (valgtHul.getHulNummer() == spillePladeHuller.get(i).getHulNummer());
                        {
                            spillePladeHuller.set(i, valgtHul);
                            break;
                        }
                    }
                    sidsteHul = valgtHul.getHulNummer(); // Gem at dette er det sidste brugte hul
                }
            }
            else
            {
                if (valgtHul.getHulNummer() == sidsteHul + 1) // Hvis næste valgte hul er det næste hul på pladen.
                {
                    if (turSpiller.getKuglerIHaand() == 1) // hvis 
                    {
                        turSpiller.setKuglerIHaand(valgtHul.getAntalKugler() + turSpiller.getKuglerIHaand()); // Saml kuglerne op plus den man havde i hånden
                        valgtHul.setAntalKugler(0); // Tøm hullet
                        for (int i = 0; i < spillePladeHuller.size(); i++)
                        {
                            if (valgtHul.getHulNummer() == spillePladeHuller.get(i).getHulNummer());
                            {
                                spillePladeHuller.set(i, valgtHul);
                                break;
                            }
                        }
                        sidsteHul = valgtHul.getHulNummer(); // Gem at dette er det sidste brugte hul

                    }
                    else
                    {
                        turSpiller.setKuglerIHaand(turSpiller.getKuglerIHaand() - 1); // Fjern en kugle fra hånden
                        valgtHul.setAntalKugler(valgtHul.getAntalKugler() + 1); // Læg en kugle i hullet
                        for (int i = 0; i < spillePladeHuller.size(); i++)
                        {
                            if (valgtHul.getHulNummer() == spillePladeHuller.get(i).getHulNummer());
                            {
                                spillePladeHuller.set(i, valgtHul);
                                break;
                            }
                        }
                        sidsteHul = valgtHul.getHulNummer(); // Gem at dette er det sidste brugte hul
                    }
                }
                else
                {
                    if (valgtHul.getHulNummer() == sidsteHul + 2) // Hvis det valgte hul springer to huller over
                    {
                        if ((sidsteHul + 1) % 7 == 0) // Er det sidste hul et mål?
                        {
                            turSpiller.setKuglerIHaand(turSpiller.getKuglerIHaand() - 1); // Fjern en kugle fra hånden
                            valgtHul.setAntalKugler(valgtHul.getAntalKugler() + 1); // Læg en kugle i hullet
                            for (int i = 0; i < spillePladeHuller.size(); i++)
                            {
                                if (valgtHul.getHulNummer() == spillePladeHuller.get(i).getHulNummer());
                                {
                                    spillePladeHuller.set(i, valgtHul);
                                    break;
                                }
                            }
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
                for (int i = 0; i < spillePladeHuller.size(); i++)
                {
                    if (valgtHul.getHulNummer() == spillePladeHuller.get(i).getHulNummer());
                    {
                        spillePladeHuller.set(i, valgtHul);
                        
                    }
                }
                sidsteHul = valgtHul.getHulNummer();

                if (turSpiller.getKuglerIHaand() == 0) // Hvis der bliver afsluttet i mål, start forfra
                {
                    startTur = true;
                }
            }
        }
 */
