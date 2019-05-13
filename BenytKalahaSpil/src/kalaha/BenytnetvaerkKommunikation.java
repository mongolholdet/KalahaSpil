/*
    Lavet af: C. Bjørner, U. Esbjørn, M. Repnak, H. Warncke
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
                System.out.println("Vil du modtager eller sende data eller echo'e? [S/M/E]");
                String kommando = input.nextLine();

                if (kommando.equals("S") || kommando.equals("s"))
                {
                    testForbindelse.sendData("Test af klassen");
                }

                else if (kommando.equals("M") || kommando.equals("m"))
                {
                    testForbindelse.modtagData();
                }
                
                else if (kommando.equals("E") || kommando.equals("e"))
                {
                    testForbindelse.testForbindelse();
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
