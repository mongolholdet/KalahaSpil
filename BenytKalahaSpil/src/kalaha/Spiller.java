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
    private int kuglerIHaand;
    netvaerkKommunikation spillerKommunikation;

    public Spiller(String initIPAdresser[], int initPortNummer) throws Exception
    {
        spillerKommunikation = new netvaerkKommunikation(initIPAdresser, initPortNummer);
        //spillerKommunikation.testForbindelse();
        IPAdresser = initIPAdresser;
        portNummer = initPortNummer;
    }
    
    public int getKuglerIHaand()
    {
        return kuglerIHaand;
    }
    
    public void setKuglerIHaand(int kugler)
    {
        kuglerIHaand = kugler;
    }

    public boolean sendSpilStatus(Spilleplade spilStatus) throws Exception
    {
        return spillerKommunikation.sendData(spilStatus.toData()); // Ændrer toData til en Spilleplade?
    }

    public String modtagSpilStatus() throws Exception
    {
        String data = spillerKommunikation.modtagData();
        return null;
    }
}
