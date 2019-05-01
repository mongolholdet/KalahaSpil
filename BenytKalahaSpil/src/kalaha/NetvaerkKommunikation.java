/*
    Lavet af: C. Bjørner, U. Esbjørn, M. Repnak, H. Warncke
 */
package kalaha;

import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;

public class NetvaerkKommunikation
{

    private int portNummer = 42069;
    private String spillerIPAdresser[];

    public NetvaerkKommunikation(String initspillerIPAdresser[])
    {
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

    public void sendSpilStatus(String besked, String IPAdresse) throws Exception // Send data til alle brugere inkluderet i 
    {
        DatagramSocket sendDataSocket = new DatagramSocket(portNummer); // Opretter et DatagramSocket objekt til at sende DatagramPacket objekter

        for (String spillerIPAdresse : spillerIPAdresser)
        {
            try
            {
                InetAddress IP = InetAddress.getByAddress(IPAdresse.getBytes()); // Genererer IP addresse objekt
                DatagramPacket beskedPacket = new DatagramPacket(besked.getBytes(), besked.length(), IP, portNummer); // Laver en DatagramPacket "packet" med den ønskede besked, dens længde, modtagers IP og portnummer.
                sendDataSocket.send(beskedPacket); // Sender beskeden med den oprettede DatagramSocket
                sendDataSocket.close(); // Lukker socketen
            }
            catch (Exception e) // Fanger og printer et StackTrace til den fundne Exception
            {
                e.printStackTrace();
            }
        }
    }

    public String modtagSpilStatus() throws Exception
    {
        DatagramSocket modtagDataSocket = new DatagramSocket(portNummer); // Opretter et DatagramSocket objekt til at sende DatagramPacket objekter

        try
        {
            byte[] modtagBuffer = new byte[2048];
            DatagramPacket modtagetBeskedPacket = new DatagramPacket(modtagBuffer, modtagBuffer.length); // Laver en DatagramPacket "packet" til at modtage en besked med ønskede byte array og dens længde..
            modtagDataSocket.receive(modtagetBeskedPacket); // Modtager beskeden med den oprettede DatagramSocket
            String modtagetDataString = new String(modtagetBeskedPacket.getData(), 0, modtagetBeskedPacket.getLength()); // Konverterer den modtagne data til en String 
            modtagDataSocket.close(); // Lukker socketen
            return modtagetDataString;

        }
        catch (Exception e) // Fanger og printer et StackTrace til den fundne Exception
        {
            e.printStackTrace();
            return null;
        }
    }
}
