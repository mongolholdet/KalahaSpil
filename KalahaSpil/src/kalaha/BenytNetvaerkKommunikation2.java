    /*
    Lavet af: C. Bjørner, U. Esbjørn, M. Repnak, H. Warncke
 */
package kalaha;

import java.net.InetAddress;
import java.util.*;

public class BenytNetvaerkKommunikation2
{
    public static void main(String[] args) throws Exception
    {
        while (true)
        {
            Scanner input = new Scanner(System.in);
            System.out.println("Din IP adresse er: " + InetAddress.getLocalHost().getHostAddress().toString());
            System.out.println("Vil du modtager eller sende data? [S/M]");
            String kommando = input.nextLine();
            
            if (kommando.equals("S") || kommando.equals("s"))
            {
                System.out.println("indtast IP adresse på din modtager på det samme netvaerk:");
                String IPAdresser[] = new String[1];
                IPAdresser[0] = "10.16.165.156"; // input.nextLine(); //10.16.165.156
                NetvaerkKommunikation2 sendData = new NetvaerkKommunikation2(IPAdresser);
                sendData.sendSpilStatus("luder");
            }
            
            else if (kommando.equals("M") || kommando.equals("m"))
            {
                String IPAdresser[] = new String[1];
                IPAdresser[0] = InetAddress.getLocalHost().getHostAddress().toString();
                NetvaerkKommunikation2 modtagData = new NetvaerkKommunikation2(IPAdresser);
                modtagData.modtagSpilStatus();
            }
            
            else
            {
                System.out.println("Forkert input.");
            }
        }
    }
}
