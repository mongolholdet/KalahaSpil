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
        KalahaSpilleplade test = new KalahaSpilleplade(2, 6, 0, testString, 41970);
        System.out.println(test.toData());
        while (true)
        {
            inputString = input.nextLine();
            test.tur(test.getSpiller(1), new Hul(inputString));
            System.out.println( test.getSpiller(1).getKuglerIHaand());
            System.out.println(test.toData());
        }
    }
}
