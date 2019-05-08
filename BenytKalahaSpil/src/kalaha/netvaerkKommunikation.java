package kalaha;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class netvaerkKommunikation
{
    private InetAddress[] IPAdresser;
    private int portNummer;

    //Implementerer kun at sende eller at modtage data skiftevis, men ikke samtidig.
    
    public netvaerkKommunikation(String[] initIPAdresser, int initportnummer) throws Exception
    {
        int taeller = 0;
        for(String initIPAdresse : initIPAdresser)
        {                
            IPAdresser[taeller] = InetAddress.getByName(initIPAdresse);
            taeller++;
        }
        portNummer = initportnummer;
    }

    /* Skal færdiggøres.
    public void testForbindelse()
    {
        try
        {
            for (InetAddress IPAdresse : IPAdresser)
            {
                sendData
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
*/

    public void sendData(String sendtData) throws Exception
    {
        try
        {
            for (InetAddress IPAdresse : IPAdresser)
            {
                Socket sendDataSocket = new Socket(IPAdresse, portNummer);
                PrintWriter dataUd = new PrintWriter(sendDataSocket.getOutputStream(), true);
                System.out.println("Data afsendt: " + sendtData);
                dataUd.close();
                sendDataSocket.close();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public String modtagData() throws Exception
    {
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
}
