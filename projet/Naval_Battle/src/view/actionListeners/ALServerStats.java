package view.actionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ALServerStats implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent e)
	{
	     switch (e.getActionCommand())
	     {
	         case "back":
	        	 System.out.println("button back clicked");
	             break;
	         default:
	             throw new IllegalArgumentException("Invalid action");
	     }
	}
	
}
