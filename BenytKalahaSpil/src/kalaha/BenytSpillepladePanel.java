package kalaha;

import javax.swing.JFrame;

public class BenytSpillepladePanel
{
    public static void main(String[] args)
    {
        JFrame vindue = new JFrame("Kalaha");
	vindue.add(new SpillepladePanel());
	vindue.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	vindue.setSize(1564,542);
        vindue.setResizable(false);
	vindue.setVisible(true);
    }
}
