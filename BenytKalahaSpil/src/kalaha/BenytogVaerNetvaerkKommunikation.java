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
            Scanner input = new Scanner(System.in);
            InetAddress IPAdresse = InetAddress.getByName("10.16.165.156");
            System.out.println("Din IP adresse er: " + InetAddress.getLocalHost().getHostAddress()); //Java2s network 
            System.out.println("Vil du modtager eller sende data? [S/M]");
            String kommando = input.nextLine();

            if (kommando.equals("S") || kommando.equals("s"))
            {
                Socket SMSocket = new Socket(IPAdresse, 42069);
                PrintWriter ud = new PrintWriter(SMSocket.getOutputStream(), true);
                BufferedReader ind = new BufferedReader(new InputStreamReader(SMSocket.getInputStream()));
            }

            else if (kommando.equals("M") || kommando.equals("m"))
            {

            }

            else
            {
                System.out.println("Forkert input.");
            }
        }
    }
}