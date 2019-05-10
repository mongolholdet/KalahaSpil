/*
    Lavet af: C. Bjørner, U. Esbjørn, M. Repnak, H. Warncke
 */
package kalaha;

import javax.swing.JFrame;

public class BenytSpilleplade
{
    public static void main(String[] args)
    {
        JFrame vindue = new JFrame("Kalaha");
	vindue.add(new SpillepladePanel());
	vindue.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	vindue.setSize(420,69);
	vindue.setVisible(true);
    }
}
