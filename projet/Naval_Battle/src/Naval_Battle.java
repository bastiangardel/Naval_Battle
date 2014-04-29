import java.io.IOException;

import javax.swing.SwingUtilities;

import view.UIAskPass;
import view.UIHome;

public class Naval_Battle
{
	private static void displayHomeGUI() throws IOException
	{
        // UIHome home = new UIHome();
        // home.setVisible(true);
        UIAskPass askpass = new UIAskPass();
        askpass.setVisible(true);
        
	}
	
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
            @Override
            public void run()
            {
            	try
            	{
					displayHomeGUI();
				}
            	catch (IOException e)
            	{
					e.printStackTrace();
				}
            }
        });
	}
}
