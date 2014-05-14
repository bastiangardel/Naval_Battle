package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.UIManager;

import view.actionListeners.ALHome;
import view.customSwingComp.JButtonDesign;
import view.customSwingComp.JFrameWithBGimg;
import view.utils.ComponentMover;

public class UIHome extends JFrameWithBGimg
{
	private static final long serialVersionUID = 1433671331229689918L;
	
	private static final String UI_NAME = "ui_home";
	private static final String CREATE_GAME = "Créer une partie";
	private static final String SERVER_STATS = "Statistiques du serveur";
	private static final String LIST_SERVERS = "Afficher la liste des serveurs";
	private static final String QUIT = "Quitter";
	
	private JButtonDesign buttonCreate;
	
	public UIHome() throws IOException
	{
		super("pictures/backgrounds/HomeBackground.jpg", 411, 500);
		initUI();
	}
	
	public JButtonDesign getButtonCreate()
	{
		return buttonCreate;
	}
	
	private void initUI()
	{
		// frame settings
		setTitle("Battleships");
	    setResizable(false);
	    setUndecorated(true);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    
	    // contents
	    JLabel pane = getBG();
        setContentPane(pane);
	    addComponents(pane);
	    
        @SuppressWarnings("unused")
		ComponentMover cm = new ComponentMover(this, this.getContentPane());
        
        pack();
	    setLocationRelativeTo(null);
	}
	
	private void addComponents(JLabel pane)
	{
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
		
		// contour
		pane.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
		
		// space on top
		pane.add(Box.createRigidArea(new Dimension(0, 320)));
		
		buttonCreate = addCenteredButton(CREATE_GAME, pane);
		pane.add(Box.createRigidArea(new Dimension(0, 10))); // space between buttons
		JButtonDesign buttonStats = addCenteredButton(SERVER_STATS, pane);
		pane.add(Box.createRigidArea(new Dimension(0, 10))); // space between buttons		
		JButtonDesign buttonList = addCenteredButton(LIST_SERVERS, pane);
		pane.add(Box.createRigidArea(new Dimension(0, 20))); // space between buttons
		JButtonDesign buttonExit = new JButtonDesign(QUIT);
		buttonExit.setAlignmentX(Component.CENTER_ALIGNMENT);
		pane.add(buttonExit);
		
		buttonCreate.setActionCommand("create");
		buttonStats.setActionCommand("stats");
		buttonList.setActionCommand("list");
		buttonExit.setActionCommand("quit");
		
		ActionListener al = new ALHome(this);
		buttonCreate.addActionListener(al);
		buttonStats.addActionListener(al);
		buttonList.addActionListener(al);
		buttonExit.addActionListener(al);
	}
	
	private JButtonDesign addCenteredButton(String text, Container container)
	{
		JButtonDesign button = new JButtonDesign(text);
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		button.setFont(new Font(UIManager.getDefaults().getFont("TabbedPane.font").getFontName(), Font.PLAIN, 16));
		container.add(button);
		
		return button;
	}
}
