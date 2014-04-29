package view.actionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ALUsernameConf implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent e)
	{
	     switch (e.getActionCommand())
	     {
	         case "modify":
	        	 System.out.println("button modify clicked");
	             break;
	         case "cancel":
	        	 System.out.println("button cancel clicked");
	        	 break;
	         default:
	             throw new IllegalArgumentException("Invalid action");
	     }
	}
	
}
