package view.actionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.UIHome;

public class ALHome implements ActionListener
{
	private UIHome ui;
	
	public ALHome(UIHome ui)
	{
		this.ui = ui;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
	     switch (e.getActionCommand())
	     {
	         case "create":
	        	 System.out.println("button create server clicked");
	        	 ui.getButtonCreate().setVisible(false);
	             break;
	         case "stats":
	        	 System.out.println("button display server stats clicked");
	        	 ui.getButtonCreate().setVisible(true);
	        	 break;
	         case "list":
	        	 System.out.println("button list servers clicked");
	             break;
	         case "quit":
	        	 System.exit(0);
	        	 break;
	         default:
	             throw new IllegalArgumentException("Invalid action");
	     }
	}
	
}
