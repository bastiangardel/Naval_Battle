package view.actionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ALWaitPlayers implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent e)
	{
	     switch (e.getActionCommand())
	     {
	         case "disconnect":
	        	 System.out.println("button disconnect clicked");
	             break;
	         default:
	             throw new IllegalArgumentException("Invalid action");
	     }
	}
	
}
