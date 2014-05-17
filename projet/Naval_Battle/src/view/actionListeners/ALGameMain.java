package view.actionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ALGameMain implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent e)
	{
	     switch (e.getActionCommand())
	     {
	         case "send":
	        	 System.out.println("button send clicked");
	             break;
	         default:
	             throw new IllegalArgumentException("Invalid action");
	     }
	}
	
}
