package view.actionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.UIMainWindow;

public class ALMainWindow implements ActionListener
{
	private UIMainWindow ui;
	
	public ALMainWindow(UIMainWindow ui)
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
	         case "nick":
	        	 System.out.println("button change nick clicked");
	        	 break;
	         case "quit":
	        	 System.out.println("button quit clicked");
	        	 System.exit(0);
	        	 break;
	         default:
	             throw new IllegalArgumentException("Invalid action");
	     }
	}
	
}
