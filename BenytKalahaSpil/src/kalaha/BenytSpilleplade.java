/*
    Lavet af: C. Bjørner, U. Esbjørn, M. Repnak, H. Warncke
 */
package kalaha;

import javax.swing.JFrame;

public class BenytSpilleplade
{

    public static void main(String[] args) throws Exception
    {
        /*
        // Yoinker lige dokumentet
        JFrame vindue = new JFrame("Kalaha");
	vindue.add(new SpillepladePanel());
	vindue.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	vindue.setSize(500,500);
	vindue.setVisible(true);
         */
        Spilleplade testSpil = new Spilleplade(2, 6, 0);
        System.out.println(testSpil.toString());
        System.out.println(testSpil.toData());
        /*
        System.out.println("Spiller test:");
        String[] test = new String[1];
        test[0] = "test";
        Spiller testSpiller = new Spiller(1, test, 41970);
        testSpiller.modtagSpilStatus();
         */

        String hulDataString = "[1.1.6],[1.2.6],[1.3.6],[1.4.6],[1.5.6],[1.6.6],{1.7.0},[2.8.6],[2.9.6],[2.10.6],[2.11.6],[2.12.6],[2.13.6],{2.14.0}";
        String[] hulDataStringArray = hulDataString.split(",");

        System.out.println("hulDataStringArray: ");
        for (String str : hulDataStringArray)
        {
            System.out.println(str);
        }
        
        int i = 0;
        while (true)
        {
            if (hulDataStringArray[i].contains(String.valueOf(2)) && hulDataStringArray[i].contains("{")) break;
            else i++;
        }
        String[] hulDataStringArrayArray = hulDataStringArray[i].split(".");
        System.out.println("i: "+i);
        
        System.out.println("hulDataStringArrayArray: " + hulDataStringArray[i]);
        for (String str : hulDataStringArrayArray)
        {
            System.out.println(str);
        }

    }
}
