package view;

import java.awt.Color;
import java.awt.Component;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.actionListeners.ALWaitPlayers;
import view.customSwingComp.JButtonDesign;
import view.utils.ComponentMover;

public class UIWaitPlayers extends JFrame
{
	private static final long serialVersionUID = -739742586451537622L;

	private static final String UI_NAME = "ui_waitPlayers";
	private static final String YOU_ARE_CONNECTED_TO = "Vous êtes connecté au serveur";
	private static final String ON = "sur";
	private static final String PLEASE_WAIT = "Veuillez patienter. En attente de joueurs...";
	private static final String DISCONNECT = "Déconnecter";
	
	private String serverName;
	private String IPAdress;
	
	public UIWaitPlayers(String serverName, String IPAdress) throws IOException
	{
		this.serverName = serverName;
		this.IPAdress = IPAdress;
		initUI();
	}
	
	private void initUI()
	{
		// frame settings
	    setResizable(false);
	    setUndecorated(true);
	    
	    // contents
	    JPanel pane = new JPanel();
        setContentPane(pane);
	    addComponents(pane);
	    
	    new ComponentMover(this, this.getContentPane());
	    
	    pack();
	    setLocationRelativeTo(null);
	}
	
	private void addComponents(JPanel pane)
	{
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
		
		// contour
		pane.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
		
		JPanel firstLine = new JPanel();
		JLabel firstLineText = new JLabel(YOU_ARE_CONNECTED_TO + " " + serverName + " " + ON + " " + IPAdress);
		firstLine.add(firstLineText);
		
		JPanel secondLine = new JPanel();
		JLabel secondLineText = new JLabel(PLEASE_WAIT);
		secondLine.add(secondLineText);

		JPanel thirdLine = new JPanel();
		JButtonDesign buttonDisconnect = new JButtonDesign(DISCONNECT);
		thirdLine.add(buttonDisconnect);
		
		firstLine.setAlignmentX(Component.CENTER_ALIGNMENT);
		secondLine.setAlignmentX(Component.CENTER_ALIGNMENT);
		buttonDisconnect.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		pane.add(firstLine);
		pane.add(secondLine);
		pane.add(thirdLine);
		
		buttonDisconnect.setActionCommand("disconnect");
		
		ALWaitPlayers al = new ALWaitPlayers();
		buttonDisconnect.addActionListener(al);
	}
}
