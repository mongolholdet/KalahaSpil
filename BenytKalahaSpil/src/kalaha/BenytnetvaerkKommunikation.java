/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kalaha;

import java.net.*;
import java.util.*;

public class BenytnetvaerkKommunikation
{

    public static void main(String[] args) throws Exception
    {
        String[] IPAdresse = new String[1];
        IPAdresse[0] = "192.168.0.20";
        netvaerkKommunikation testForbindelse = new netvaerkKommunikation(IPAdresse, 42069);
        while (true)
        {
            try
            {
                Scanner input = new Scanner(System.in);
                System.out.println("Din IP adresse er: " + testForbindelse.getLokalIPAdresse());
                System.out.println("Vil du modtager eller sende data? [S/M]");
                String kommando = input.nextLine();

                if (kommando.equals("S") || kommando.equals("s"))
                {
                    testForbindelse.sendData("Test af klassen");
                }

                else if (kommando.equals("M") || kommando.equals("m"))
                {
                    testForbindelse.modtagData();
                }

                else
                {
                    System.out.println("Forkert input.");
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
