package view.actionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ALServerListing implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent e)
	{
	     switch (e.getActionCommand())
	     {
	         case "manual":
	        	 System.out.println("button manual specify clicked");
	             break;
	         case "connect":
	        	 System.out.println("button connect clicked");
	        	 break;
	         case "cancel":
	        	 System.out.println("button cancel clicked");
	        	 break;
	         default:
	             throw new IllegalArgumentException("Invalid action");
	     }
	}
	
}
