package view.actionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.UIHome;
import view.UIServerCreation;

public class ALServerCreation implements ActionListener
{
	private UIServerCreation ui;
	
	public ALServerCreation(UIServerCreation ui)
	{
		this.ui = ui;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		switch (e.getActionCommand())
		{
			case "addPass":
				System.out.println("checkbox add pass checked");
				break;
			case "create":
				System.out.println("button create clicked");
				break;
			case "cancel":
				System.out.println("button cancel clicked");
				break;
			default:
				throw new IllegalArgumentException("Invalid action");
		}
	}
	
}
