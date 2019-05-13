/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kalaha;

public class Spiller
{
    private String[] IPAdresser;
    private int portNummer;
    private int spillerNummer;
    private int kuglerIHaand;
    netvaerkKommunikation spillerKommunikation;

    public Spiller(int initSpillerNummer, String initIPAdresser[], int initPortNummer) throws Exception
    {
        spillerKommunikation = new netvaerkKommunikation(initIPAdresser, initPortNummer);
        //spillerKommunikation.testForbindelse();
        spillerNummer = initSpillerNummer;
        IPAdresser = initIPAdresser;
        portNummer = initPortNummer;
    }

    public int getSpillerNummer()
    {
        return spillerNummer;
    }

    public int getKuglerIHaand()
    {
        return kuglerIHaand;
    }

    public String getIPAdresse() throws Exception
    {
        return spillerKommunikation.getLokalIPAdresse();
    }

    public void setKuglerIHaand(int kugler)
    {
        kuglerIHaand = kugler;
    }

    public boolean sendSpilStatus(Spilleplade spilStatus) throws Exception
    {
        return spillerKommunikation.sendData(spilStatus.toData()); // Ændrer toData til en Spilleplade?
    }

    public boolean sendHulStatus(Hul opdateretHul) throws Exception
    {
        return spillerKommunikation.sendData(opdateretHul.toData()); // Ændrer toData til en Spilleplade?
    }

    public String modtagSpilStatus() throws Exception
    {
        String data = spillerKommunikation.modtagData();
        return data;
    }
}
