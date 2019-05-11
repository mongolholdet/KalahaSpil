/*
    Lavet af: C. Bjørner, U. Esbjørn, M. Repnak, H. Warncke
 */
package kalaha;

import javax.swing.JFrame;

public class BenytSpilleplade
{
    public static void main(String[] args)
    {
        /*
        // Yoinker lige dokumentet
        JFrame vindue = new JFrame("Kalaha");
	vindue.add(new SpillepladePanel());
	vindue.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	vindue.setSize(500,500);
	vindue.setVisible(true);
        */
        Spilleplade testSpil = new Spilleplade(2, 6);
    }
}
