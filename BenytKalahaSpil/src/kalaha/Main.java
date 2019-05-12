package kalaha;

import javax.swing.JFrame;

public class Main
{
  
    public static void main(String[] args) throws Exception
    {
        JFrame vindue = new JFrame("Kalaha");
	vindue.add(new StartMenuPanel());
	vindue.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	vindue.setSize(300,370);
        vindue.setResizable(false);
	vindue.setVisible(true);
    }
}
