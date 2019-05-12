/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kalaha;

import javax.swing.JFrame;

/**
 *
 * @author henri
 */
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
