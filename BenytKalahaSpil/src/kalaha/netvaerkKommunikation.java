/*
    Lavet af: C. Bjørner, U. Esbjørn, M. Repnak, H. Warncke
 */
package kalaha;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
 

public class netvaerkKommunikation
{
    // Implementerer kun at sende eller at modtage data skiftevis, men ikke samtidig.
    private InetAddress[] IPAdresser;
    private int portNummer;

    public netvaerkKommunikation(String[] initIPAdresser, int initportnummer) throws Exception
    {
        setIPAdresser(initIPAdresser);
        portNummer = initportnummer;
    }

    public netvaerkKommunikation(int initportnummer) throws Exception 
    {
        // husk at sætte IP adressen senere hen.
        portNummer = initportnummer;
    }

    public void setIPAdresser(String[] initIPAdresser) throws Exception
    {
        // Sætter alle IP Adresser til at være lig med inputtet. Bruges når konstruktoren uden IP Adresser anvendes.
        int taeller = 0;
        IPAdresser = new InetAddress[initIPAdresser.length];
        for (String initIPAdresse : initIPAdresser)
        {
            IPAdresser[taeller] = InetAddress.getByName(initIPAdresse); // Får InetAdress objekter ud fra de givne Strings
            taeller++;
        }
    }

    public String getLokalIPAdresse() throws Exception
    {
        return InetAddress.getLocalHost().getHostAddress(); // Returnerer den lokale maskines IP Adresse
    }

    public boolean sendData(String sendtData) throws Exception
    {
        // Opretter et Socket objekt for at sende data til alle de ønskede IP Adresser 
        try
        {
            for (InetAddress IPAdresse : IPAdresser)
            {
                Socket sendDataSocket = new Socket(IPAdresse, portNummer);
                PrintWriter dataUd = new PrintWriter(sendDataSocket.getOutputStream(), true);
                dataUd.print(sendtData);
                System.out.println("Data afsendt: " + sendtData);
                dataUd.close();
                sendDataSocket.close();
                return true;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public String modtagData() throws Exception
    {
        // Opretter et ServerSocket og Socket objekt og modtager data på den angivne port
        try
        {
            ServerSocket modtagDataServerSocket = new ServerSocket(portNummer);
            Socket modtagDataSocket = modtagDataServerSocket.accept();
            BufferedReader dataInd = new BufferedReader(new InputStreamReader(modtagDataSocket.getInputStream()));
            String modtagetData = dataInd.readLine();
            System.out.println("Data modtaget: " + modtagetData);
            dataInd.close();
            modtagDataSocket.close();
            modtagDataServerSocket.close();
            return modtagetData;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return "Fejl i modtagelse af data.";
    }

    //Skal færdiggøres.
    public void testForbindelse()
    {
        // En "echo" funktion som tester om brugerne kan modtage og sende data.
        try
        {
            for (InetAddress IPAdresse : IPAdresser)
            {
                sendData("Virker du? Sendt fra: " + getLokalIPAdresse());
                System.out.println("Virker du? Sendt fra: " + getLokalIPAdresse());
                String modtagetData = modtagData();
                System.out.println(modtagetData);
                if (modtagetData.contains("Virker du? Sendt fra: "))
                {
                    sendData("Ja jeg virker. Sendt fra: " + getLokalIPAdresse());
                    System.out.println("Ja jeg virker. Sendt fra: " + getLokalIPAdresse());
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
