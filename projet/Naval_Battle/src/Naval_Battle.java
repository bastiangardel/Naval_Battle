import java.io.IOException;

import javax.swing.SwingUtilities;

import view.UIAskPass;
import view.UIGameMain;
import view.UIHome;
import view.UIManualConnect;
import view.UIServerCreation;
import view.UIUsernameConf;
import view.UIWaitPlayers;

public class Naval_Battle
{
	private static void displayHomeGUI() throws IOException
	{
        UIHome home = new UIHome();
        home.setVisible(true);
//        UIAskPass askpass = new UIAskPass();
//        askpass.setVisible(true);
//		UIWaitPlayers waitplayers = new UIWaitPlayers("testServer", "testIpAdr");
//		waitplayers.setVisible(true);
//		UIManualConnect manualconnect = new UIManualConnect();
//		manualconnect.setVisible(true);
//		UIUsernameConf usernameconf = new UIUsernameConf();
//		usernameconf.setVisible(true);
//		UIGameMain gamemain = new UIGameMain();
//		gamemain.setVisible(true);
		UIServerCreation servercreation = new UIServerCreation();
		servercreation.setVisible(true);
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
