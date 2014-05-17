import java.io.IOException;

import javax.swing.SwingUtilities;

import view.UIAskPass;
import view.UIGameMain;
import view.UIMainWindow;
import view.UIManualConnect;
import view.UIServerCreation;
import view.UIServerStats;
import view.UIServersListing;
import view.UIUsernameConf;
import view.UIWaitPlayers;

public class Naval_Battle
{
	private static void displayHomeGUI() throws IOException
	{
        UIMainWindow home = new UIMainWindow();
        home.setVisible(true);
        UIAskPass askpass = new UIAskPass();
        askpass.setVisible(true);
		UIWaitPlayers waitplayers = new UIWaitPlayers("testServer", "testIpAdr");
		waitplayers.setVisible(true);
		UIManualConnect manualconnect = new UIManualConnect();
		manualconnect.setVisible(true);
		UIUsernameConf usernameconf = new UIUsernameConf();
		usernameconf.setVisible(true);
		UIGameMain gamemain = new UIGameMain();
		gamemain.setVisible(true);
		UIServerCreation servercreation = new UIServerCreation();
		servercreation.setVisible(true);
		UIServersListing serverlisting = new UIServersListing();
		serverlisting.setVisible(true);
        UIServerStats serverstats = new UIServerStats();
        serverstats.setVisible(true);
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
