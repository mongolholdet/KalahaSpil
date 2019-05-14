/*
    Lavet af: C. Bjørner, U. Esbjørn, M. Repnak, H. Warncke
 */
package kalaha;

import java.net.InetAddress;
import java.util.Scanner;

public class BenytKalahaSpil
{

    public static void main(String[] args) throws Exception
    {

        Scanner input = new Scanner(System.in);
        String inputString;
        String[] testString = new String[2];
        testString[0] = "10.16.163.121";
        testString[1] = InetAddress.getLocalHost().getHostAddress();
        Kalaha test = new Kalaha(2, 6, 0, testString, 41970);
        System.out.println(test.toData());

        while (true)
        {
            inputString = input.nextLine();
            test.tur(test.getSpiller(2), new Hul(inputString));
            System.out.println(test.toData());
        }
    }
}
