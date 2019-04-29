/*
    Lavet af: C. Bjørner, U. Esbjørn, M. Repnak, H. Warncke
 */
package kalaha;

import java.net.DatagramSocket;
        
public class NetvaerkKommunikation
{
    private int portNummer = 42069;
    private String spillerIPAdresser[];
    
    public NetvaerkKommunikation(String initspillerIPAdresser[])throws Exception
    {
        DatagramSocket kommunikationsSocket = new DatagramSocket(portNummer);
        setspillerIPAdresser(initspillerIPAdresser);

    }
    
    public void setspillerIPAdresser(String initspillerIPAdresser[])
    {
        if (spillerIPAdresser.length == initspillerIPAdresser.length)
        {
            spillerIPAdresser = initspillerIPAdresser;
        }
        else
        {
            System.out.println("Fejl, maengderne af IP-adresser stemmer ikke overens."); //Implementer log?
        }
    }
        
    public String[] getspillerIPAdresser()
    {
        return spillerIPAdresser;
    }
    
    public void sendSpilStatus()
    {
        
    }
   
}
