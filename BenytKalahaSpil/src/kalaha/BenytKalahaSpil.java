/*
    Lavet af: C. Bjørner, U. Esbjørn, M. Repnak, H. Warncke
 */
package kalaha;

public class BenytKalahaSpil
{
    public static void main(String[] args) throws Exception
    {
        String[] testString = new String[4];
        testString[0]= "192.168.20.90";
        testString[1]= "192.168.20.91";
        testString[2]= "192.167.20.90";
        testString[3]= "10.16.23.56";
        Kalaha test = new Kalaha(2, 6, 0, testString, 41970);
        for(String TS :test.printIPAdresser())
        {
            System.out.println(TS);
        }
    }
}
