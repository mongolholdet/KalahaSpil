/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kalaha;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 *
 * @author Markus
 */
public class BenytogVaerNetvaerkKommunikation
{

    public static void main(String[] args) throws Exception
    {
        while (true)
        {
            try
            {
                Scanner input = new Scanner(System.in);
                InetAddress IPAdresse = InetAddress.getByName("10.16.164.59");
                System.out.println("Din IP adresse er: " + InetAddress.getLocalHost().getHostAddress()); //Java2s network 
                System.out.println("Vil du modtager eller sende data? [S/M]");
                //while (true)
                //{
                String kommando = input.nextLine();

                
                    Socket SMSocket = new Socket(IPAdresse, 42069);
                    PrintWriter ud = new PrintWriter(SMSocket.getOutputStream(), true);
                    BufferedReader ind = new BufferedReader(new InputStreamReader(SMSocket.getInputStream()));

                    if (kommando.equals("S") || kommando.equals("s"))
                    {
                        ud.append("hej");
                    }

                    else if (kommando.equals("M") || kommando.equals("m"))
                    {
                        System.out.print(ind.read());
                    }

                    else
                    {
                        System.out.println("Forkert input.");
                    }
                 
                /*
                if (kommando.equals("S") || kommando.equals("s"))
                {
                    DatagramSocket ds = new DatagramSocket();
                    DatagramPacket dp = new DatagramPacket("hej".getBytes(), "hej".length(), IPAdresse, 42069);
                    ds.send(dp);
                    ds.close();
                }

                else if (kommando.equals("M") || kommando.equals("m"))
                {
                    DatagramSocket ds = new DatagramSocket(42069);
                    byte[] buf = new byte[1024];
                    DatagramPacket dp = new DatagramPacket(buf, 1024);
                    while (true)
                    {
                        ds.receive(dp);
                        //String str = new String(dp.getData(), 0, dp.getLength());
                        System.out.println(dp.getSocketAddress());
                    }
                    ds.close();
                }


                else
                {
                    System.out.println("Forkert input.");
                }
                }
*/
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        }
    }
}
