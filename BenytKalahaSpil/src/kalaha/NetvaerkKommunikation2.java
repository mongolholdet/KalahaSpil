package kalaha;

import java.net.*;
import java.io.*;

public class NetvaerkKommunikation2 extends Thread
{

    // Forsøg 2 med input og outputstreams
    private ServerSocket serverSocket;
    private int portNummer = 42069;
    private int timeout = 10000;
    private String spillerIPAdresser[];

    public NetvaerkKommunikation2(String initspillerIPAdresser[]) throws IOException
    {
        spillerIPAdresser = initspillerIPAdresser;
        serverSocket = new ServerSocket(portNummer);
        serverSocket.setSoTimeout(timeout);
    }

    public void setspillerIPAdresser(String initspillerIPAdresser[])
    {
        if (initspillerIPAdresser.length > 0)
        {
            spillerIPAdresser = new String[initspillerIPAdresser.length];
            System.arraycopy(initspillerIPAdresser, 0, spillerIPAdresser, 0, initspillerIPAdresser.length);
        }
        else
        {
            System.out.println("Fejl, maengderne af IP-adresser stemmer ikke overens."); //Implementer log?
        }
    }

    public String[] getSpillerIPAdresser()
    {
        return spillerIPAdresser;
    }

    public void sendSpilStatus(String besked) throws Exception // Send data til alle brugere inkluderet.
    {
        while (true)
        {
            try
            {
                for (String modtager : spillerIPAdresser)
                {
                    System.out.println("Sender data på port: " + serverSocket.getLocalPort() + " og IP adresse: " + modtager);
                    Socket klient = new Socket(modtager, portNummer);
                    System.out.println("Forbundet med: " + klient.getRemoteSocketAddress());
                    DataOutputStream sendDataStream = new DataOutputStream(klient.getOutputStream());
                    sendDataStream.writeBytes(besked);
                    sendDataStream.flush();
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

    }

    public void modtagSpilStatus()
    {
        while (true)
        {
            try
            {
                System.out.println("Venter på at modtage data på port: " + serverSocket.getLocalPort());
                Socket server = serverSocket.accept(); // Venter på at der bliver lavet en forbindelse med socketen. Blokerende kode
                System.out.println("Forbundet med: " + server.getRemoteSocketAddress());
                DataInputStream modtagDataStream = new DataInputStream(server.getInputStream());
                byte[] modtagetData = modtagDataStream.readAllBytes();
                System.out.println(modtagetData.toString());
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

}
