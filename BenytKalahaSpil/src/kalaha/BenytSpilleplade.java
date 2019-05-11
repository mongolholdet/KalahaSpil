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
        
        System.out.println("Spiller test:");
        String[] test = new String[1];
        test[0] = "test";
        Spiller testSpiller = new Spiller(test, 41970);
        testSpiller.modtagSpilStatus();
    }
}
