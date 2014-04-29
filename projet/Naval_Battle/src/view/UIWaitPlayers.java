package view;

import java.awt.Color;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
	    
	    @SuppressWarnings("unused")
		ComponentMover cm = new ComponentMover(this, this.getContentPane());
	    
	    pack();
	    setLocationRelativeTo(null);
	}
	
	private void addComponents(JPanel pane)
	{
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
		
		// contour
		pane.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
		
		JLabel firstLine = new JLabel(YOU_ARE_CONNECTED_TO + serverName + ON + IPAdress);
//		JLabel 
		
	}
}
